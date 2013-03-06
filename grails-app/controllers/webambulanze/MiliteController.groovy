/* Created by Algos s.r.l. */
/* Date: mag 2012 */
/* Questo file è stato installato dal plugin AlgosBase */
/* Tipicamente NON verrà più sovrascritto dalle successive release del plugin */
/* in quanto POTREBBE essere personalizzato in questa applicazione */
/* Se vuoi che le prossime release del plugin sovrascrivano questo file, */
/* perdendo tutte le modifiche effettuate qui, */
/* regola a true il flag di controllo flagOverwrite© */
/* flagOverwrite = false */

package webambulanze

import grails.plugins.springsecurity.Secured
import org.springframework.dao.DataIntegrityViolationException

@Secured([Cost.ROLE_MILITE])
class MiliteController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    private static boolean EDIT_VERSO_LISTA = true

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def militeService

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def funzioneService

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def logoService

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def croceService

    def index() {
        redirect(action: 'list', params: params)
    } // fine del metodo

    //-deprecated
    def statistiche() {
        def lista
        Croce croce
        String sigla
        def campiLista = [
                'cognome',
                'nome',
                'turniAnno',
                'oreAnno']
        def campiExtra = null

        if (params.order) {
            if (params.order == 'asc') {
                params.order = 'desc'
            } else {
                params.order = 'asc'
            }// fine del blocco if-else
        } else {
            params.order = 'asc'
        }// fine del blocco if-else

        if (croce) {
            params.siglaCroce = croce.sigla
            if (params.siglaCroce.equals(Cost.CROCE_ALGOS)) {
                lista = Milite.findAll("from Milite order by croce_id,cognome")
                campiLista = ['id', 'croce'] + campiLista
            } else {
                if (!params.sort) {
                    params.sort = 'cognome'
                }// fine del blocco if-else
                if (militeService.isLoggatoAdminOrMore()) {
                    lista = Milite.findAllByCroce(croce, params)
                } else {
                    lista = militeService.militeLoggato
                }// fine del blocco if-else
                campiExtra = funzioneService.campiExtraPerCroce(croce)
            }// fine del blocco if-else
        } else {
            lista = Milite.findAll(params)
        }// fine del blocco if-else

        render(view: 'militeturno', model: [
                militeInstanceList: lista,
                militeInstanceTotal: 0,
                campiLista: campiLista,
                campiExtra: campiExtra],
                params: params)
    } // fine del metodo

    def list(Integer max) {
        def lista
        Croce croce = croceService.getCroce(session)
        def campiLista = [
                'cognome',
                'nome',
                'telefonoCellulare',
                'scadenzaBLSD',
                'scadenzaTrauma',
                'scadenzaNonTrauma']
        def campiExtra = null

        if (params.order) {
            if (params.order == 'asc') {
                params.order = 'desc'
            } else {
                params.order = 'asc'
            }// fine del blocco if-else
        } else {
            params.order = 'asc'
        }// fine del blocco if-else

        if (croce) {
            params.siglaCroce = croce.sigla
            if (params.siglaCroce.equals(Cost.CROCE_ALGOS)) {
                lista = Milite.findAll("from Milite order by croce_id,cognome")
                campiLista = ['id', 'croce'] + campiLista
            } else {
                if (!params.sort) {
                    params.sort = 'cognome'
                }// fine del blocco if-else
                if (militeService.isLoggatoAdminOrMore()) {
                    lista = Milite.findAllByCroce(croce, params)
                } else {
                    lista = militeService.militeLoggato
                }// fine del blocco if-else
                campiExtra = funzioneService.campiExtraPerCroce(croce)
            }// fine del blocco if-else
        } else {
            lista = Milite.findAll(params)
        }// fine del blocco if-else

        render(view: 'list', model: [
                militeInstanceList: lista,
                militeInstanceTotal: 0,
                campiLista: campiLista,
                campiExtra: campiExtra],
                params: params)
    } // fine del metodo

    @Secured([Cost.ROLE_ADMIN])
    def create() {
        params.siglaCroce = session[Cost.SESSIONE_SIGLA_CROCE]

        def campiExtra = funzioneService.campiExtra(session)
        render(view: 'create', model: [militeInstance: new Milite(params), campiExtra: campiExtra], params: params)
    } // fine del metodo

    @Secured([Cost.ROLE_ADMIN])
    def save() {
        Croce croce = croceService.getCroce(session)
        def militeInstance = new Milite(params)

        if (croce) {
            params.siglaCroce = croce.sigla
            if (!militeInstance.croce) {
                militeInstance.croce = croce
            }// fine del blocco if
        }// fine del blocco if

        if (!militeInstance.save(flush: true)) {
            render(view: 'create', model: [militeInstance: militeInstance], params: params)
            return
        }// fine del blocco if

        flash.message = logoService.setWarn(Evento.militeCreato, militeInstance)

        //--sicronizza le funzioni del milite nella tavola d'incrocio Militefunzione
        params.croce = militeInstance.croce
        militeService.registraFunzioni(params)
        militeService.regolaFunzioniAutomatiche(militeInstance)

        if (EDIT_VERSO_LISTA) {
            redirect(action: 'list')
        } else {
            redirect(action: 'show', id: militeInstance.id)
        }// fine del blocco if-else
    } // fine del metodo

    def show(Long id) {
        def militeInstance = Milite.get(id)
        def campiExtra = funzioneService.campiExtra(session)

        if (!militeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'milite.label', default: 'Milite'), id])
            redirect(action: 'list')
            return
        }// fine del blocco if

        params.siglaCroce = session[Cost.SESSIONE_SIGLA_CROCE]
        render(view: 'show', model: [militeInstance: militeInstance, campiExtra: campiExtra], params: params)
    } // fine del metodo

    @Secured([Cost.ROLE_ADMIN])
    def edit(Long id) {
        def militeInstance = Milite.get(id)
        def campiExtra = funzioneService.campiExtra(session)

        if (!militeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'milite.label', default: 'Milite'), id])
            redirect(action: 'list')
            return
        }// fine del blocco if

        params.siglaCroce = session[Cost.SESSIONE_SIGLA_CROCE]
        render(view: 'edit', model: [militeInstance: militeInstance, campiExtra: campiExtra], params: params)
    } // fine del metodo

    @Secured([Cost.ROLE_ADMIN])
    def update(Long id, Long version) {
        def militeInstance = Milite.get(id)

        if (!militeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'milite.label', default: 'Milite'), id])
            redirect(action: 'list')
            return
        }// fine del blocco if

        params.siglaCroce = session[Cost.SESSIONE_SIGLA_CROCE]
        if (version != null) {
            if (militeInstance.version > version) {
                militeInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'milite.label', default: 'Milite')] as Object[],
                        "Another user has updated this Milite while you were editing")
                render(view: 'edit', model: [militeInstance: militeInstance], params: params)
                return
            }// fine del blocco if
        }// fine del blocco if

        flash.listaMessaggi = militeService.avvisoModifiche(params, militeInstance)
        militeInstance.properties = params

        if (!militeInstance.save(flush: true)) {
            render(view: 'edit', model: [militeInstance: militeInstance], params: params)
            return
        }// fine del blocco if

        //--sicronizza le funzioni del milite nella tavola d'incrocio Militefunzione
        params.croce = militeInstance.croce
        militeService.registraFunzioni(params)
        militeService.regolaFunzioniAutomatiche(militeInstance)

        //  flash.message = message(code: 'default.updated.message', args: [message(code: 'milite.label', default: 'Milite'), militeInstance.id])
        if (EDIT_VERSO_LISTA) {
            redirect(action: 'list')
        } else {
            redirect(action: 'show', id: militeInstance.id)
        }// fine del blocco if-else
    } // fine del metodo

    @Secured([Cost.ROLE_PROG])
    def delete(Long id) {
        def militeInstance = Milite.get(id)

        if (!militeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'milite.label', default: 'Milite'), id])
            redirect(action: 'list')
            return
        }// fine del blocco if

        try {
            militeInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'milite.label', default: 'Milite'), id])
            redirect(action: 'list')
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'milite.label', default: 'Milite'), id])
            redirect(action: 'show', id: id)
        }
    } // fine del metodo

} // fine della controller classe
