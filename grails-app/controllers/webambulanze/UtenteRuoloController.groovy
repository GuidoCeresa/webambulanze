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

import org.springframework.dao.DataIntegrityViolationException

class UtenteRuoloController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    } // fine del metodo

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [utenteRuoloInstanceList: UtenteRuolo.list(params), utenteRuoloInstanceTotal: UtenteRuolo.count()]
    }

    def create() {
        [utenteRuoloInstance: new UtenteRuolo(params)]
    } // fine del metodo

    def save() {
        def utenteRuoloInstance = new UtenteRuolo(params)
        if (!utenteRuoloInstance.save(flush: true)) {
            render(view: "create", model: [utenteRuoloInstance: utenteRuoloInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'utenteRuolo.label', default: 'UtenteRuolo'), utenteRuoloInstance.id])
        redirect(action: "show", id: utenteRuoloInstance.id)
    } // fine del metodo

    def show(Long id) {
        def utenteRuoloInstance = UtenteRuolo.get(id)
        if (!utenteRuoloInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'utenteRuolo.label', default: 'UtenteRuolo'), id])
            redirect(action: "list")
            return
        }

        [utenteRuoloInstance: utenteRuoloInstance]
    } // fine del metodo

    def edit(Long id) {
        def utenteRuoloInstance = UtenteRuolo.get(id)
        if (!utenteRuoloInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'utenteRuolo.label', default: 'UtenteRuolo'), id])
            redirect(action: "list")
            return
        }

        [utenteRuoloInstance: utenteRuoloInstance]
    } // fine del metodo

    def update(Long id, Long version) {
        def utenteRuoloInstance = UtenteRuolo.get(id)
        if (!utenteRuoloInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'utenteRuolo.label', default: 'UtenteRuolo'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (utenteRuoloInstance.version > version) {
                utenteRuoloInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'utenteRuolo.label', default: 'UtenteRuolo')] as Object[],
                        "Another user has updated this UtenteRuolo while you were editing")
                render(view: "edit", model: [utenteRuoloInstance: utenteRuoloInstance])
                return
            }
        }

        utenteRuoloInstance.properties = params

        if (!utenteRuoloInstance.save(flush: true)) {
            render(view: "edit", model: [utenteRuoloInstance: utenteRuoloInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'utenteRuolo.label', default: 'UtenteRuolo'), utenteRuoloInstance.id])
        redirect(action: "show", id: utenteRuoloInstance.id)
    } // fine del metodo

    def delete(Long id) {
        def utenteRuoloInstance = UtenteRuolo.get(id)
        if (!utenteRuoloInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'utenteRuolo.label', default: 'UtenteRuolo'), id])
            redirect(action: "list")
            return
        }

        try {
            utenteRuoloInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'utenteRuolo.label', default: 'UtenteRuolo'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'utenteRuolo.label', default: 'UtenteRuolo'), id])
            redirect(action: "show", id: id)
        }
    } // fine del metodo

} // fine della controller classe
