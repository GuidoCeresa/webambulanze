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
class TipoTurnoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def croceService

    def index() {
        redirect(action: "list", params: params)
    } // fine del metodo

    def list(Integer max) {
        def lista
        Croce croce = croceService.getCroce(request)
        def campiLista = [
                'ordine',
                'sigla',
                'descrizione',
                'durata',
                'oraInizio',
                'oraFine',
                'primo',
                'ultimo',
                'fineGiornoSuccessivo',
                'visibile',
                'orario',
                'multiplo',
                'funzioniObbligatorie',
                'funzione1',
                'funzione2',
                'funzione3',
                'funzione4']

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
                lista = TipoTurno.findAll("from TipoTurno order by croce_id,ordine")
                campiLista = ['id', 'croce'] + campiLista
            } else {
                if (!params.sort) {
                    params.sort = 'ordine'
                }// fine del blocco if-else
                lista = TipoTurno.findAllByCroce(croce, params)
            }// fine del blocco if-else
        } else {
            lista = TipoTurno.findAll(params)
        }// fine del blocco if-else

        render(view: 'list', model: [tipoTurnoInstanceList: lista, tipoTurnoInstanceTotal: 0, campiLista: campiLista], params: params)
    } // fine del metodo

    @Secured([Cost.ROLE_PROG])
    def create() {
        params.siglaCroce = croceService.getSiglaCroce(request)

        render(view: 'create', model: [tipoTurnoInstance: new TipoTurno(params)], params: params)
    } // fine del metodo

    @Secured([Cost.ROLE_PROG])
    def save() {
        Croce croce = croceService.getCroce(request)
        def tipoTurnoInstance = new TipoTurno(params)

        if (croce) {
            params.siglaCroce = croce.sigla
            if (!tipoTurnoInstance.croce) {
                tipoTurnoInstance.croce = croce
            }// fine del blocco if
        }// fine del blocco if

        if (!tipoTurnoInstance.save(flush: true)) {
            render(view: 'create', model: [tipoTurnoInstance: tipoTurnoInstance], params: params)
            return
        }// fine del blocco if

        flash.message = message(code: 'default.created.message', args: [message(code: 'tipoTurno.label', default: 'TipoTurno'), tipoTurnoInstance.id])
        redirect(action: 'show', id: tipoTurnoInstance.id)
    } // fine del metodo

    def show(Long id) {
        def tipoTurnoInstance = TipoTurno.get(id)

        if (!tipoTurnoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoTurno.label', default: 'TipoTurno'), id])
            redirect(action: 'list')
            return
        }// fine del blocco if

        params.siglaCroce = croceService.getSiglaCroce(request)
        render(view: 'show', model: [tipoTurnoInstance: tipoTurnoInstance], params: params)
    } // fine del metodo

    @Secured([Cost.ROLE_PROG])
    def edit(Long id) {
        def tipoTurnoInstance = TipoTurno.get(id)

        if (!tipoTurnoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoTurno.label', default: 'TipoTurno'), id])
            redirect(action: 'list')
            return
        }// fine del blocco if

        params.siglaCroce = croceService.getSiglaCroce(request)
        render(view: 'edit', model: [tipoTurnoInstance: tipoTurnoInstance], params: params)
    } // fine del metodo

    @Secured([Cost.ROLE_PROG])
    def update(Long id, Long version) {
        def tipoTurnoInstance = TipoTurno.get(id)

        if (!tipoTurnoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoTurno.label', default: 'TipoTurno'), id])
            redirect(action: 'list')
            return
        }// fine del blocco if

        params.siglaCroce = croceService.getSiglaCroce(request)
        if (version != null) {
            if (tipoTurnoInstance.version > version) {
                tipoTurnoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'tipoTurno.label', default: 'TipoTurno')] as Object[],
                        "Another user has updated this TipoTurno while you were editing")
                render(view: 'edit', model: [tipoTurnoInstance: tipoTurnoInstance], params: params)
                return
            }// fine del blocco if
        }// fine del blocco if

        tipoTurnoInstance.properties = params

        if (!tipoTurnoInstance.save(flush: true)) {
            render(view: 'edit', model: [tipoTurnoInstance: tipoTurnoInstance], params: params)
            return
        }// fine del blocco if

        flash.message = message(code: 'default.updated.message', args: [message(code: 'tipoTurno.label', default: 'TipoTurno'), tipoTurnoInstance.id])
        redirect(action: 'show', id: tipoTurnoInstance.id)
    } // fine del metodo

    @Secured([Cost.ROLE_PROG])
    def delete(Long id) {
        def tipoTurnoInstance = TipoTurno.get(id)

        if (!tipoTurnoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoTurno.label', default: 'TipoTurno'), id])
            redirect(action: 'list')
            return
        }// fine del blocco if

        try {
            tipoTurnoInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'tipoTurno.label', default: 'TipoTurno'), id])
            redirect(action: 'list')
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'tipoTurno.label', default: 'TipoTurno'), id])
            redirect(action: 'show', id: id)
        }
    } // fine del metodo

} // fine della controller classe
