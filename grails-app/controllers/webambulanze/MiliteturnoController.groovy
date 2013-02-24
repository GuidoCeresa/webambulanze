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

class MiliteturnoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def militeturnoService

    def index() {
        redirect(action: 'list', params: params)
    } // fine del metodo

    def list(Integer max) {
        def lista
        Croce croce
        String sigla

        def campiLista = [
                'milite',
                'giorno',
                'turno',
                'funzione',
                'ore',
                'dettaglio']

        if (!params.sort) {
            params.sort = 'milite'
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
        if (params.sort.equals('milite')) {
            params.sort = 'milite.cognome'
        }// fine del blocco if

        if (grailsApplication.mainContext.servletContext.croce) {
            croce = grailsApplication.mainContext.servletContext.croce
            sigla = croce.sigla
            if (sigla.equals(Cost.CROCE_ALGOS)) {
                lista = Militeturno.findAll(params)
                campiLista = ['id', 'croce'] + campiLista
            } else {
                lista = Militeturno.findAllByCroce(croce, params)
            }// fine del blocco if-else
        } else {
            lista = Militeturno.findAll(params)
        }// fine del blocco if-else

        [militeturnoInstanceList: lista, militeturnoInstanceTotal: 0, campiLista: campiLista]
    }

    def dettagli(Integer recNumber) {
        def lista
        Croce croce
        String sigla
        Milite milite

        def campiLista = [
                'milite',
                'giorno',
                'turno',
                'funzione',
                'ore',
                'dettaglio']

        //recNumber=params.id
        params.sort = 'giorno'
        params.order = 'desc'
        milite = Milite.get(params.id)

        if (grailsApplication.mainContext.servletContext.croce) {
            croce = grailsApplication.mainContext.servletContext.croce
            sigla = croce.sigla
            if (sigla.equals(Cost.CROCE_ALGOS)) {
                lista = Militeturno.findAll(params)
                campiLista = ['id', 'croce'] + campiLista
            } else {
                lista = Militeturno.findAllByCroceAndMilite(croce, milite, params)
            }// fine del blocco if-else
        } else {
            lista = Militeturno.findAll(params)
        }// fine del blocco if-else

        render(view: 'list', model: [militeturnoInstanceList: lista, militeturnoInstanceTotal: 0, campiLista: campiLista])
    } // fine del metodo

    def create() {
        [militeturnoInstance: new Militeturno(params)]
    } // fine del metodo

    def save() {
        def militeturnoInstance = new Militeturno(params)
        if (!militeturnoInstance.save(flush: true)) {
            render(view: "create", model: [militeturnoInstance: militeturnoInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'militeturno.label', default: 'Militeturno'), militeturnoInstance.id])
        redirect(action: "show", id: militeturnoInstance.id)
    } // fine del metodo

    def show(Long id) {
        def militeturnoInstance = Militeturno.get(id)
        if (!militeturnoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'militeturno.label', default: 'Militeturno'), id])
            redirect(action: "list")
            return
        }

        [militeturnoInstance: militeturnoInstance]
    } // fine del metodo

    def edit(Long id) {
        def militeturnoInstance = Militeturno.get(id)
        if (!militeturnoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'militeturno.label', default: 'Militeturno'), id])
            redirect(action: "list")
            return
        }

        [militeturnoInstance: militeturnoInstance]
    } // fine del metodo

    def update(Long id, Long version) {
        def militeturnoInstance = Militeturno.get(id)
        if (!militeturnoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'militeturno.label', default: 'Militeturno'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (militeturnoInstance.version > version) {
                militeturnoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'militeturno.label', default: 'Militeturno')] as Object[],
                        "Another user has updated this Militeturno while you were editing")
                render(view: "edit", model: [militeturnoInstance: militeturnoInstance])
                return
            }
        }

        militeturnoInstance.properties = params

        if (!militeturnoInstance.save(flush: true)) {
            render(view: "edit", model: [militeturnoInstance: militeturnoInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'militeturno.label', default: 'Militeturno'), militeturnoInstance.id])
        redirect(action: "show", id: militeturnoInstance.id)
    } // fine del metodo

    def delete(Long id) {
        def militeturnoInstance = Militeturno.get(id)
        if (!militeturnoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'militeturno.label', default: 'Militeturno'), id])
            redirect(action: "list")
            return
        }

        try {
            militeturnoInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'militeturno.label', default: 'Militeturno'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'militeturno.label', default: 'Militeturno'), id])
            redirect(action: "show", id: id)
        }
    } // fine del metodo

} // fine della controller classe
