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

    def index() {
        redirect(action: "list", params: params)
    } // fine del metodo

    def list(Integer max) {
        def lista
        Croce croce
        String sigla
        def campiLista = [
                'username',
                'pass',
                'enabled',
                'accountExpired',
                'accountLocked',
                'passwordExpired']

        if (!params.sort) {
            params.sort = 'username'
        }// fine del blocco if-else
        if (params.order) {
            if (params.order=='asc') {
                params.order ='desc'
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
                lista = Utente.findAll(params)
                campiLista = ['id', 'croce'] + campiLista
            } else {
                lista = Utente.findAllByCroce(croce, params)
            }// fine del blocco if-else
        } else {
            lista = Utente.findAll(params)
        }// fine del blocco if-else

        [utenteInstanceList: lista, utenteInstanceTotal: 0, campiLista: campiLista]
    }

    def create() {
        [utenteInstance: new Utente(params)]
    } // fine del metodo

    def save() {
        def utenteInstance = new Utente(params)
        if (!utenteInstance.save(flush: true)) {
            render(view: "create", model: [utenteInstance: utenteInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'utente.label', default: 'Utente'), utenteInstance.id])
        redirect(action: "show", id: utenteInstance.id)
    } // fine del metodo

    def show(Long id) {
        def utenteInstance = Utente.get(id)
        if (!utenteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'utente.label', default: 'Utente'), id])
            redirect(action: "list")
            return
        }

        [utenteInstance: utenteInstance]
    } // fine del metodo

    def edit(Long id) {
        def utenteInstance = Utente.get(id)
        if (!utenteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'utente.label', default: 'Utente'), id])
            redirect(action: "list")
            return
        }

        [utenteInstance: utenteInstance]
    } // fine del metodo

    def update(Long id, Long version) {
        def utenteInstance = Utente.get(id)
        if (!utenteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'utente.label', default: 'Utente'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (utenteInstance.version > version) {
                utenteInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'utente.label', default: 'Utente')] as Object[],
                        "Another user has updated this Utente while you were editing")
                render(view: "edit", model: [utenteInstance: utenteInstance])
                return
            }
        }

        utenteInstance.properties = params

        if (!utenteInstance.save(flush: true)) {
            render(view: "edit", model: [utenteInstance: utenteInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'utente.label', default: 'Utente'), utenteInstance.id])
        redirect(action: "show", id: utenteInstance.id)
    } // fine del metodo

    def delete(Long id) {
        def utenteInstance = Utente.get(id)
        if (!utenteInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'utente.label', default: 'Utente'), id])
            redirect(action: "list")
            return
        }

        try {
            utenteInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'utente.label', default: 'Utente'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'utente.label', default: 'Utente'), id])
            redirect(action: "show", id: id)
        }
    } // fine del metodo

} // fine della controller classe
