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

class MilitefunzioneController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    } // fine del metodo

    def list(Integer max) {
        def lista
        Croce croce
        String sigla
        def campiLista = [
                'milite',
                'funzione']

        if (!params.sort) {
            params.sort = 'id'
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
                lista = Militefunzione.findAll(params)
                campiLista = ['id', 'croce'] + campiLista
            } else {
                lista = Militefunzione.findAllByCroce(croce, params)
            }// fine del blocco if-else
        } else {
            lista = Militefunzione.findAll(params)
        }// fine del blocco if-else

        [militefunzioneInstanceList: lista, militefunzioneInstanceTotal: 0, campiLista: campiLista]
    }

    def create() {
        [militefunzioneInstance: new Militefunzione(params)]
    } // fine del metodo

    def save() {
        def militefunzioneInstance = new Militefunzione(params)
        if (!militefunzioneInstance.save(flush: true)) {
            render(view: "create", model: [militefunzioneInstance: militefunzioneInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'militefunzione.label', default: 'Militefunzione'), militefunzioneInstance.id])
        redirect(action: "show", id: militefunzioneInstance.id)
    } // fine del metodo

    def show(Long id) {
        def militefunzioneInstance = Militefunzione.get(id)
        if (!militefunzioneInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'militefunzione.label', default: 'Militefunzione'), id])
            redirect(action: "list")
            return
        }

        [militefunzioneInstance: militefunzioneInstance]
    } // fine del metodo

    def edit(Long id) {
        def militefunzioneInstance = Militefunzione.get(id)
        if (!militefunzioneInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'militefunzione.label', default: 'Militefunzione'), id])
            redirect(action: "list")
            return
        }

        [militefunzioneInstance: militefunzioneInstance]
    } // fine del metodo

    def update(Long id, Long version) {
        def militefunzioneInstance = Militefunzione.get(id)
        if (!militefunzioneInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'militefunzione.label', default: 'Militefunzione'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (militefunzioneInstance.version > version) {
                militefunzioneInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'militefunzione.label', default: 'Militefunzione')] as Object[],
                        "Another user has updated this Militefunzione while you were editing")
                render(view: "edit", model: [militefunzioneInstance: militefunzioneInstance])
                return
            }
        }

        militefunzioneInstance.properties = params

        if (!militefunzioneInstance.save(flush: true)) {
            render(view: "edit", model: [militefunzioneInstance: militefunzioneInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'militefunzione.label', default: 'Militefunzione'), militefunzioneInstance.id])
        redirect(action: "show", id: militefunzioneInstance.id)
    } // fine del metodo

    def delete(Long id) {
        def militefunzioneInstance = Militefunzione.get(id)
        if (!militefunzioneInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'militefunzione.label', default: 'Militefunzione'), id])
            redirect(action: "list")
            return
        }

        try {
            militefunzioneInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'militefunzione.label', default: 'Militefunzione'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'militefunzione.label', default: 'Militefunzione'), id])
            redirect(action: "show", id: id)
        }
    } // fine del metodo

} // fine della controller classe
