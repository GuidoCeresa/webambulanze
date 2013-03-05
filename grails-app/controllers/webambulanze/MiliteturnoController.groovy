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
class MiliteturnoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def croceService

    def index() {
        redirect(action: 'list', params: params)
    } // fine del metodo

    def list(Integer max) {
        def lista
        Croce croce = croceService.getCroceCorrente(session)
        def campiLista = [
                'milite',
                'giorno',
                'turno',
                'funzione',
                'ore',
                'dettaglio']

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

        if (croce) {
            params.siglaCroce = croce.sigla
            if (params.siglaCroce.equals(Cost.CROCE_ALGOS)) {
                lista = Militeturno.findAll("from Militeturno order by croce_id,milite_id")
                campiLista = ['id', 'croce'] + campiLista
            } else {
                if (!params.sort) {
                    params.sort = 'milite'
                }// fine del blocco if-else
                lista = Militeturno.findAllByCroce(croce, params)
            }// fine del blocco if-else
        } else {
            lista = Militeturno.findAll(params)
        }// fine del blocco if-else

        render(view: 'list', model: [militeturnoInstanceList: lista, militeturnoInstanceTotal: 0, campiLista: campiLista, campiExtra: null], params: params)
    } // fine del metodo

    def dettagli(Integer recNumber) {
        def lista
        Milite milite
        Croce croce = croceService.getCroceCorrente(session)
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

        if (croce) {
            params.siglaCroce = croce.sigla
            if (params.siglaCroce.equals(Cost.CROCE_ALGOS)) {
                lista = Militeturno.findAll("from Militeturno order by croce_id,milite_id")
                campiLista = ['id', 'croce'] + campiLista
            } else {
                lista = Militeturno.findAllByCroceAndMilite(croce, milite, params)
            }// fine del blocco if-else
        } else {
            lista = Militeturno.findAll(params)
        }// fine del blocco if-else

        render(view: 'list', model: [militeturnoInstanceList: lista, militeturnoInstanceTotal: 0, campiLista: campiLista], params: params)
    } // fine del metodo

    @Secured([Cost.ROLE_PROG])
    def create() {
        params.siglaCroce = session[Cost.SESSIONE_SIGLA_CROCE]

        render(view: 'create', model: [militeturnoInstance: new Militeturno(params)], params: params)
    } // fine del metodo

    @Secured([Cost.ROLE_PROG])
    def save() {
        Croce croce = croceService.getCroceCorrente(session)
        def militeturnoInstance = new Militeturno(params)

        if (croce) {
            params.siglaCroce = croce.sigla
            if (!militeturnoInstance.croce) {
                militeturnoInstance.croce = croce
            }// fine del blocco if
        }// fine del blocco if

        if (!militeturnoInstance.save(flush: true)) {
            render(view: 'create', model: [militeturnoInstance: militeturnoInstance], params: params)
            return
        }// fine del blocco if

        flash.message = message(code: 'default.created.message', args: [message(code: 'militeturno.label', default: 'Militeturno'), militeturnoInstance.id])
        redirect(action: 'show', id: militeturnoInstance.id)
    } // fine del metodo

    @Secured([Cost.ROLE_PROG])
    def show(Long id) {
        def militeturnoInstance = Militeturno.get(id)

        if (!militeturnoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'militeturno.label', default: 'Militeturno'), id])
            redirect(action: 'list')
            return
        }// fine del blocco if

        params.siglaCroce = session[Cost.SESSIONE_SIGLA_CROCE]
        render(view: 'show', model: [militeturnoInstance: militeturnoInstance], params: params)
    } // fine del metodo

    @Secured([Cost.ROLE_PROG])
    def edit(Long id) {
        def militeturnoInstance = Militeturno.get(id)

        if (!militeturnoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'militeturno.label', default: 'Militeturno'), id])
            redirect(action: 'list')
            return
        }// fine del blocco if

        params.siglaCroce = session[Cost.SESSIONE_SIGLA_CROCE]
        render(view: 'edit', model: [militeturnoInstance: militeturnoInstance], params: params)
    } // fine del metodo

    @Secured([Cost.ROLE_PROG])
    def update(Long id, Long version) {
        def militeturnoInstance = Militeturno.get(id)

        if (!militeturnoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'militeturno.label', default: 'Militeturno'), id])
            redirect(action: 'list')
            return
        }// fine del blocco if

        params.siglaCroce = session[Cost.SESSIONE_SIGLA_CROCE]
        if (version != null) {
            if (militeturnoInstance.version > version) {
                militeturnoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'militeturno.label', default: 'Militeturno')] as Object[],
                        "Another user has updated this Militeturno while you were editing")
                render(view: 'edit', model: [militeturnoInstance: militeturnoInstance], params: params)
                return
            }// fine del blocco if
        }// fine del blocco if

        militeturnoInstance.properties = params

        if (!militeturnoInstance.save(flush: true)) {
            render(view: 'edit', model: [militeturnoInstance: militeturnoInstance], params: params)
            return
        }// fine del blocco if

        flash.message = message(code: 'default.updated.message', args: [message(code: 'militeturno.label', default: 'Militeturno'), militeturnoInstance.id])
        redirect(action: 'show', id: militeturnoInstance.id)
    } // fine del metodo

    @Secured([Cost.ROLE_PROG])
    def delete(Long id) {
        def militeturnoInstance = Militeturno.get(id)

        if (!militeturnoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'militeturno.label', default: 'Militeturno'), id])
            redirect(action: 'list')
            return
        }// fine del blocco if

        try {
            militeturnoInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'militeturno.label', default: 'Militeturno'), id])
            redirect(action: 'list')
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'militeturno.label', default: 'Militeturno'), id])
            redirect(action: 'show', id: id)
        }
    } // fine del metodo

} // fine della controller classe
