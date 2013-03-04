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
class RuoloController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: 'list', params: params)
    } // fine del metodo

    def list(Integer max) {
        def lista = Ruolo.list(params)
        params.siglaCroce = session[Cost.SESSIONE_SIGLA_CROCE]

        if (!params.sort) {
            params.sort = 'id'
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

        def campiLista = [
                'id',
                'authority']

        render(view: 'list', model: [ruoloInstanceList: lista, ruoloInstanceTotal: 0, campiLista: campiLista], params: params)
    } // fine del metodo

    @Secured([Cost.ROLE_PROG])
    def create() {
        params.siglaCroce = session[Cost.SESSIONE_SIGLA_CROCE]

        render(view: 'create', model: [ruoloInstance: new Ruolo(params)], params: params)
    } // fine del metodo

    @Secured([Cost.ROLE_PROG])
    def save() {
        params.siglaCroce = session[Cost.SESSIONE_SIGLA_CROCE]

        def ruoloInstance = new Ruolo(params)
        if (!ruoloInstance.save(flush: true)) {
            render(view: 'create', model: [ruoloInstance: ruoloInstance], params: params)
            return
        }// fine del blocco if

        flash.message = message(code: 'default.created.message', args: [message(code: 'ruolo.label', default: 'Ruolo'), ruoloInstance.id])
        redirect(action: 'show', id: ruoloInstance.id)
    } // fine del metodo

    def show(Long id) {
        params.siglaCroce = session[Cost.SESSIONE_SIGLA_CROCE]

        def ruoloInstance = Ruolo.get(id)
        if (!ruoloInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ruolo.label', default: 'Ruolo'), id])
            redirect(action: 'list')
            return
        }// fine del blocco if

        render(view: 'show', model: [ruoloInstance: ruoloInstance], params: params)
    } // fine del metodo

    @Secured([Cost.ROLE_PROG])
    def edit(Long id) {
        params.siglaCroce = session[Cost.SESSIONE_SIGLA_CROCE]

        def ruoloInstance = Ruolo.get(id)
        if (!ruoloInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ruolo.label', default: 'Ruolo'), id])
            redirect(action: 'list')
            return
        }// fine del blocco if

        render(view: 'edit', model: [ruoloInstance: ruoloInstance], params: params)
    } // fine del metodo

    @Secured([Cost.ROLE_PROG])
    def update(Long id, Long version) {
        params.siglaCroce = session[Cost.SESSIONE_SIGLA_CROCE]

        def ruoloInstance = Ruolo.get(id)
        if (!ruoloInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ruolo.label', default: 'Ruolo'), id])
            redirect(action: 'list')
            return
        }// fine del blocco if

        if (version != null) {
            if (ruoloInstance.version > version) {
                ruoloInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'ruolo.label', default: 'Ruolo')] as Object[],
                        "Another user has updated this Ruolo while you were editing")
                render(view: 'edit', model: [ruoloInstance: ruoloInstance], params: params)
                return
            }// fine del blocco if
        }// fine del blocco if

        ruoloInstance.properties = params

        if (!ruoloInstance.save(flush: true)) {
            render(view: 'edit', model: [ruoloInstance: ruoloInstance], params: params)
            return
        }// fine del blocco if

        flash.message = message(code: 'default.updated.message', args: [message(code: 'ruolo.label', default: 'Ruolo'), ruoloInstance.id])
        redirect(action: 'show', id: ruoloInstance.id)
    } // fine del metodo

    @Secured([Cost.ROLE_PROG])
    def delete(Long id) {
        def ruoloInstance = Ruolo.get(id)
        if (!ruoloInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ruolo.label', default: 'Ruolo'), id])
            redirect(action: "list")
            return
        }// fine del blocco if

        try {
            ruoloInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'ruolo.label', default: 'Ruolo'), id])
            redirect(action: 'list')
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'ruolo.label', default: 'Ruolo'), id])
            redirect(action: 'show', id: id)
        }// fine del blocco if
    } // fine del metodo

} // fine della controller classe
