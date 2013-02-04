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

    private static boolean EDIT_VERSO_LISTA = true

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def militeService

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def funzioneService

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def logoService

    def index() {
        redirect(action: 'list', params: params)
    } // fine del metodo

    def statistiche() {
        def lista
        Croce croce
        String sigla
        def campiLista = [
                'cognome',
                'nome']
        def campiExtra = null

        if (!params.sort) {
            params.sort = 'cognome'
        }// fine del blocco if-else
        if (params.order) {
            if (params.order == 'asc') {
                params.order = 'desc'
            } else {
                params.order = 'asc'
            }// fine del blocco if-else
        } else {
            params.order = 'asc'
        }// fine del blocco if-else

        if (grailsApplication.mainContext.servletContext.croce) {
            croce = grailsApplication.mainContext.servletContext.croce
            sigla = croce.sigla
            if (sigla.equals(Cost.CROCE_ALGOS)) {
                lista = Milite.findAll(params)
                campiLista = ['id', 'croce'] + campiLista
            } else {
                lista = Milite.findAllByCroce(croce, params)
                campiExtra = funzioneService.campiExtraStatistichePerCroce(croce)
            }// fine del blocco if-else
        } else {
            lista = Milite.findAll(params)
        }// fine del blocco if-else

        render(view: 'militeturno', model: [militeInstanceList: lista, militeInstanceTotal: 0, campiLista: campiLista, campiExtra: campiExtra])
    } // fine del metodo

    def list(Integer max) {
        def lista
        Croce croce
        String sigla
        def campiLista = [
                'cognome',
                'nome',
                'telefonoCellulare',
                'scadenzaBLSD',
                'scadenzaTrauma',
                'scadenzaNonTrauma']
        def campiExtra = null

        if (!params.sort) {
            params.sort = 'cognome'
        }// fine del blocco if-else
        if (params.order) {
            if (params.order == 'asc') {
                params.order = 'desc'
            } else {
                params.order = 'asc'
            }// fine del blocco if-else
        } else {
            params.order = 'asc'
        }// fine del blocco if-else

        if (grailsApplication.mainContext.servletContext.croce) {
            croce = grailsApplication.mainContext.servletContext.croce
            sigla = croce.sigla
            if (sigla.equals(Cost.CROCE_ALGOS)) {
                lista = Milite.findAll(params)
                campiLista = ['id', 'croce'] + campiLista
            } else {
                lista = Milite.findAllByCroce(croce, params)
                campiExtra = funzioneService.campiExtraPerCroce(croce)
            }// fine del blocco if-else
        } else {
            lista = Milite.findAll(params)
        }// fine del blocco if-else

        [militeInstanceList: lista, militeInstanceTotal: 0, campiLista: campiLista, campiExtra: campiExtra]
    }

    @Secured([Cost.ROLE_ADMIN])
    def create() {
        def campiExtra = funzioneService.campiExtra(grailsApplication)
        [militeInstance: new Milite(params), campiExtra: campiExtra]
    } // fine del metodo

    @Secured([Cost.ROLE_ADMIN])
    def save() {
        def militeInstance = new Milite(params)
        if (!militeInstance.croce) {
            militeInstance.croce = grailsApplication.mainContext.servletContext.croce
        }// fine del blocco if
        if (!militeInstance.save(flush: true)) {
            render(view: "create", model: [militeInstance: militeInstance])
            return
        }

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
        def campiExtra = funzioneService.campiExtra(grailsApplication)

        if (!militeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'milite.label', default: 'Milite'), id])
            redirect(action: 'list')
            return
        }

        [militeInstance: militeInstance, campiExtra: campiExtra]
    } // fine del metodo

    @Secured([Cost.ROLE_ADMIN])
    def edit(Long id) {
        def militeInstance = Milite.get(id)
        def campiExtra = funzioneService.campiExtra(grailsApplication)

        if (!militeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'milite.label', default: 'Milite'), id])
            redirect(action: "list")
            return
        }

        [militeInstance: militeInstance, campiExtra: campiExtra]
    } // fine del metodo

    @Secured([Cost.ROLE_ADMIN])
    def update(Long id, Long version) {
        def militeInstance = Milite.get(id)
        if (!militeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'milite.label', default: 'Milite'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (militeInstance.version > version) {
                militeInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'milite.label', default: 'Milite')] as Object[],
                        "Another user has updated this Milite while you were editing")
                render(view: "edit", model: [militeInstance: militeInstance])
                return
            }
        }
        flash.listaMessaggi = militeService.avvisoModifiche(params, militeInstance)
        militeInstance.properties = params

        if (!militeInstance.save(flush: true)) {
            render(view: "edit", model: [militeInstance: militeInstance])
            return
        }

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
            redirect(action: "list")
            return
        }

        try {
            militeInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'milite.label', default: 'Milite'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'milite.label', default: 'Milite'), id])
            redirect(action: "show", id: id)
        }
    } // fine del metodo

} // fine della controller classe
