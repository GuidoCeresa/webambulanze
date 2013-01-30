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
        redirect(action: "list", params: params)
    } // fine del metodo

    def list(Integer max) {
        def campiLista = ['id', 'authority']

        params.max = Math.min(max ?: 10, 100)
        [ruoloInstanceList: Ruolo.list(params), ruoloInstanceTotal: Ruolo.count(), campiLista: campiLista]
    }

    def create() {
        [ruoloInstance: new Ruolo(params)]
    } // fine del metodo

    def save() {
        def ruoloInstance = new Ruolo(params)
        if (!ruoloInstance.save(flush: true)) {
            render(view: "create", model: [ruoloInstance: ruoloInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'ruolo.label', default: 'Ruolo'), ruoloInstance.id])
        redirect(action: "show", id: ruoloInstance.id)
    } // fine del metodo

    def show(Long id) {
        def ruoloInstance = Ruolo.get(id)
        if (!ruoloInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ruolo.label', default: 'Ruolo'), id])
            redirect(action: "list")
            return
        }

        [ruoloInstance: ruoloInstance]
    } // fine del metodo

    def edit(Long id) {
        def ruoloInstance = Ruolo.get(id)
        if (!ruoloInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ruolo.label', default: 'Ruolo'), id])
            redirect(action: "list")
            return
        }

        [ruoloInstance: ruoloInstance]
    } // fine del metodo

    def update(Long id, Long version) {
        def ruoloInstance = Ruolo.get(id)
        if (!ruoloInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ruolo.label', default: 'Ruolo'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (ruoloInstance.version > version) {
                ruoloInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'ruolo.label', default: 'Ruolo')] as Object[],
                        "Another user has updated this Ruolo while you were editing")
                render(view: "edit", model: [ruoloInstance: ruoloInstance])
                return
            }
        }

        ruoloInstance.properties = params

        if (!ruoloInstance.save(flush: true)) {
            render(view: "edit", model: [ruoloInstance: ruoloInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'ruolo.label', default: 'Ruolo'), ruoloInstance.id])
        redirect(action: "show", id: ruoloInstance.id)
    } // fine del metodo

    def delete(Long id) {
        def ruoloInstance = Ruolo.get(id)
        if (!ruoloInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'ruolo.label', default: 'Ruolo'), id])
            redirect(action: "list")
            return
        }

        try {
            ruoloInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'ruolo.label', default: 'Ruolo'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'ruolo.label', default: 'Ruolo'), id])
            redirect(action: "show", id: id)
        }
    } // fine del metodo

} // fine della controller classe
