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

@Secured([Cost.ROLE_CUSTODE])
class UtenteController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def utenteService

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def logoService

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def croceService

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def militeService

    def index() {
        redirect(action: 'list', params: params)
    } // fine del metodo

    def list(Integer max) {
        def lista
        Croce croce = croceService.getCroce(session)
        def campiLista = [
                'username',
                'pass',
                'enabled',
                'accountExpired',
                'accountLocked',
                'passwordExpired']

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
                lista = Utente.findAll("from Utente order by croce_id,username")
                campiLista = ['id', 'croce'] + campiLista
            } else {
                if (!params.sort) {
                    params.sort = 'username'
                }// fine del blocco if-else
                lista = utenteService.tuttiQuelliDellaCroceSenzaProgrammatore(croce, params)
            }// fine del blocco if-else
        } else {
            lista = utenteService.tuttiSenzaProgrammatore(params)
        }// fine del blocco if-else

        render(view: 'list', model: [utenteInstanceList: lista, utenteInstanceTotal: 0, campiLista: campiLista], params: params)
    } // fine del metodo

    //--ATTENZIONE - se si ricreano le viste, occorre modificare  -form.gsp
    //--mettendo from="${lista}" nel primo campo
    def create() {
        def lista = null
        Croce croce = croceService.getCroce(session)

        if (!params.sort) {
            params.sort = 'username'
        }// fine del blocco if

        if (croce) {
            params.siglaCroce = croce.sigla
            lista = militeService.allMilitiDellaCroce(croce)
        }// fine del blocco if

        render(view: 'create', model: [utenteInstance: new Utente(params), lista: lista], params: params)
    } // fine del metodo

    def save() {
        Croce croce = croceService.getCroce(session)
        def utenteInstance = new Utente(params)

        if (croce) {
            params.siglaCroce = croce.sigla
            utenteInstance.croce = croce
        }// fine del blocco if

        if (!utenteInstance.save(flush: true)) {
            render(view: 'create', model: [utenteInstance: utenteInstance], params: params)
            return
        }// fine del blocco if

        if (utenteInstance.milite) {
            flash.message = logoService.setWarn(Evento.utenteCreato, utenteInstance.milite)
        }// fine del blocco if

        Ruolo ruoloMilite = Ruolo.findByAuthority(Cost.ROLE_MILITE)
        UtenteRuolo.create(utenteInstance, ruoloMilite, true)

        flash.message = message(code: 'default.created.message', args: [message(code: 'utente.label', default: 'Utente'), utenteInstance.id])
        redirect(action: 'show', id: utenteInstance.id)
    } // fine del metodo

    def show(Long id) {
        params.siglaCroce = session[Cost.SESSIONE_SIGLA_CROCE]

        def utenteInstance = Utente.get(id)

        if (!utenteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'utente.label', default: 'Utente'), id])
            redirect(action: 'list')
            return
        }// fine del blocco if

        render(view: 'show', model: [utenteInstance: utenteInstance], params: params)
    } // fine del metodo

    //--ATTENZIONE - se si ricreano le viste, occorre modificare  -form.gsp
    //--mettendo from="${lista}" nel primo campo
    def edit(Long id) {
        Croce croce = croceService.getCroce(session)
        def lista = null

        def utenteInstance = Utente.get(id)

        if (!utenteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'utente.label', default: 'Utente'), id])
            redirect(action: 'list')
            return
        }// fine del blocco if

        if (croce) {
            params.siglaCroce = croce.sigla
            lista = militeService.allMilitiDellaCroce(croce)
        }// fine del blocco if

        render(view: 'edit', model: [utenteInstance: utenteInstance, lista: lista], params: params)
    } // fine del metodo

    def update(Long id, Long version) {
        params.siglaCroce = session[Cost.SESSIONE_SIGLA_CROCE]

        def utenteInstance = Utente.get(id)

        if (!utenteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'utente.label', default: 'Utente'), id])
            redirect(action: "list")
            return
        }// fine del blocco if

        if (version != null) {
            if (utenteInstance.version > version) {
                utenteInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'utente.label', default: 'Utente')] as Object[],
                        "Another user has updated this Utente while you were editing")
                render(view: 'edit', model: [utenteInstance: utenteInstance], params: params)
                return
            }// fine del blocco if
        }// fine del blocco if

        flash.listaMessaggi = utenteService.avvisoModifiche(params, utenteInstance)
        utenteInstance.properties = params

        if (!utenteInstance.save(flush: true)) {
            render(view: 'edit', model: [utenteInstance: utenteInstance], params: params)
            return
        }// fine del blocco if

        redirect(action: 'show', id: utenteInstance.id)
    } // fine del metodo

    @Secured([Cost.ROLE_PROG])
    def delete(Long id) {
        def utenteInstance = Utente.get(id)

        if (!utenteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'utente.label', default: 'Utente'), id])
            redirect(action: "list")
            return
        }// fine del blocco if

        try {
            utenteInstance.delete()
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'utente.label', default: 'Utente'), id])
            redirect(action: 'list')
        }
        catch (DataIntegrityViolationException e) {
            try { // prova ad eseguire il codice
                //  String query = "delete from utente_ruolo where utente_id=" + utenteInstance.id
                //  Ruolo ruolo = Ruolo.get(4)
                //  UtenteRuolo.remove utenteInstance, ruolo
                //  utenteInstance.delete()
            } catch (Exception unErrore) { // intercetta l'errore
                log.error unErrore
            }// fine del blocco try-catch
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'utente.label', default: 'Utente'), id])
            redirect(action: 'show', id: id)
        }
    } // fine del metodo

} // fine della controller classe
