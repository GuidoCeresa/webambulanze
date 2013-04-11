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
class AutomezzoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def croceService

    def index() {
        redirect(action: 'list', params: params)
    } // fine del metodo

    def list(Integer max) {
        def lista
        Croce croce = croceService.getCroce(request)
        def campiLista = [
                'tipo',
                'dataAcquisto',
                'targa',
                'sigla',
                'contakilometri']

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
                lista = Automezzo.findAll("from Automezzo order by croce_id,dataAcquisto")
                campiLista = ['id', 'croce'] + campiLista
            } else {
                if (!params.sort) {
                    params.sort = 'dataAcquisto'
                }// fine del blocco if-else
                lista = Automezzo.findAllByCroce(croce, params)
            }// fine del blocco if-else
        } else {
            lista = Automezzo.findAll(params)
        }// fine del blocco if-else

        render(view: 'list', model: [automezzoInstanceList: lista, automezzoInstanceTotal: 0, campiLista: campiLista], params: params)
    } // fine del metodo

    @Secured([Cost.ROLE_ADMIN])
    def create() {
        [automezzoInstance: new Automezzo(params)]
    } // fine del metodo

    @Secured([Cost.ROLE_ADMIN])
    def save() {
        def automezzoInstance = new Automezzo(params)
        Croce croce = croceService.getCroce(request)

        if (croce) {
            params.siglaCroce = croce.sigla
            if (!automezzoInstance.croce) {
                automezzoInstance.croce = croce
            }// fine del blocco if
        }// fine del blocco if

        if (!automezzoInstance.save(flush: true)) {
            render(view: "create", model: [automezzoInstance: automezzoInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'automezzo.label', default: 'Automezzo'), automezzoInstance.id])
        redirect(action: "show", id: automezzoInstance.id)
    } // fine del metodo

    def show(Long id) {
        def automezzoInstance = Automezzo.get(id)
        if (!automezzoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'automezzo.label', default: 'Automezzo'), id])
            redirect(action: "list")
            return
        }

        [automezzoInstance: automezzoInstance]
    } // fine del metodo

    @Secured([Cost.ROLE_ADMIN])
    def edit(Long id) {
        def automezzoInstance = Automezzo.get(id)
        if (!automezzoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'automezzo.label', default: 'Automezzo'), id])
            redirect(action: "list")
            return
        }

        [automezzoInstance: automezzoInstance]
    } // fine del metodo

    @Secured([Cost.ROLE_ADMIN])
    def update(Long id, Long version) {
        def automezzoInstance = Automezzo.get(id)
        if (!automezzoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'automezzo.label', default: 'Automezzo'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (automezzoInstance.version > version) {
                automezzoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'automezzo.label', default: 'Automezzo')] as Object[],
                        "Another user has updated this Automezzo while you were editing")
                render(view: "edit", model: [automezzoInstance: automezzoInstance])
                return
            }
        }

        automezzoInstance.properties = params

        if (!automezzoInstance.save(flush: true)) {
            render(view: "edit", model: [automezzoInstance: automezzoInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'automezzo.label', default: 'Automezzo'), automezzoInstance.id])
        redirect(action: "show", id: automezzoInstance.id)
    } // fine del metodo

    @Secured([Cost.ROLE_PROG])
    def delete(Long id) {
        def automezzoInstance = Automezzo.get(id)
        if (!automezzoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'automezzo.label', default: 'Automezzo'), id])
            redirect(action: "list")
            return
        }

        try {
            automezzoInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'automezzo.label', default: 'Automezzo'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'automezzo.label', default: 'Automezzo'), id])
            redirect(action: "show", id: id)
        }
    } // fine del metodo

} // fine della controller classe
