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
class FunzioneController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def croceService

    def index() {
        redirect(action: 'list', params: params)
    } // fine del metodo

    def list(Integer max) {
        def lista
        Croce croce = croceService.getCroce(session)
        def campiLista = [
                'ordine',
                'sigla',
                'siglaVisibile',
                'descrizione',
                'funzioniDipendenti']

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
                lista = Funzione.findAll("from Funzione order by croce_id,ordine")
                campiLista = ['id', 'croce'] + campiLista
            } else {
                if (!params.sort) {
                    params.sort = 'ordine'
                }// fine del blocco if-else
                lista = Funzione.findAllByCroce(croce, params)
            }// fine del blocco if-else
        } else {
            lista = Funzione.findAll(params)
        }// fine del blocco if-else

        render(view: 'list', model: [funzioneInstanceList: lista, funzioneInstanceTotal: 0, campiLista: campiLista], params: params)
    } // fine del metodo

    @Secured([Cost.ROLE_PROG])
    def create() {
        params.siglaCroce = session[Cost.SESSIONE_SIGLA_CROCE]

        render(view: 'create', model: [funzioneInstance: new Funzione(params)], params: params)
    } // fine del metodo

    @Secured([Cost.ROLE_PROG])
    def save() {
        Croce croce = croceService.getCroce(session)
        def funzioneInstance = new Funzione(params)

        if (croce) {
            params.siglaCroce = croce.sigla
            if (!funzioneInstance.croce) {
                funzioneInstance.croce = croce
            }// fine del blocco if
        }// fine del blocco if

        if (!funzioneInstance.save(flush: true)) {
            render(view: 'create', model: [funzioneInstance: funzioneInstance], params: params)
            return
        }// fine del blocco if

        flash.message = message(code: 'default.created.message', args: [message(code: 'funzione.label', default: 'Funzione'), funzioneInstance.id])
        redirect(action: 'show', id: funzioneInstance.id)
    } // fine del metodo

    def show(Long id) {
        def funzioneInstance = Funzione.get(id)

        if (!funzioneInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'funzione.label', default: 'Funzione'), id])
            redirect(action: 'list')
            return
        }// fine del blocco if

        params.siglaCroce = session[Cost.SESSIONE_SIGLA_CROCE]
        render(view: 'show', model: [funzioneInstance: funzioneInstance], params: params)
    } // fine del metodo

    @Secured([Cost.ROLE_PROG])
    def edit(Long id) {
        def funzioneInstance = Funzione.get(id)

        if (!funzioneInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'funzione.label', default: 'Funzione'), id])
            redirect(action: 'list')
            return
        }// fine del blocco if

        params.siglaCroce = session[Cost.SESSIONE_SIGLA_CROCE]
        render(view: 'edit', model: [funzioneInstance: funzioneInstance], params: params)
    } // fine del metodo

    @Secured([Cost.ROLE_PROG])
    def update(Long id, Long version) {
        def funzioneInstance = Funzione.get(id)

        if (!funzioneInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'funzione.label', default: 'Funzione'), id])
            redirect(action: 'list')
            return
        }// fine del blocco if

        params.siglaCroce = session[Cost.SESSIONE_SIGLA_CROCE]
        if (version != null) {
            if (funzioneInstance.version > version) {
                funzioneInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'funzione.label', default: 'Funzione')] as Object[],
                        "Another user has updated this Funzione while you were editing")
                render(view: 'edit', model: [funzioneInstance: funzioneInstance], params: params)
                return
            }// fine del blocco if
        }// fine del blocco if

        funzioneInstance.properties = params

        if (!funzioneInstance.save(flush: true)) {
            render(view: 'edit', model: [funzioneInstance: funzioneInstance], params: params)
            return
        }// fine del blocco if

        flash.message = message(code: 'default.updated.message', args: [message(code: 'funzione.label', default: 'Funzione'), funzioneInstance.id])
        redirect(action: 'show', id: funzioneInstance.id)
    } // fine del metodo

    @Secured([Cost.ROLE_PROG])
    def delete(Long id) {
        def funzioneInstance = Funzione.get(id)

        if (!funzioneInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'funzione.label', default: 'Funzione'), id])
            redirect(action: 'list')
            return
        }// fine del blocco if

        try {
            funzioneInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'funzione.label', default: 'Funzione'), id])
            redirect(action: 'list')
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'funzione.label', default: 'Funzione'), id])
            redirect(action: 'show', id: id)
        }
    } // fine del metodo

} // fine della controller classe
