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
                'milite',
                'funzione']

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
                lista = Militefunzione.findAll("from Militefunzione order by croce_id,milite_id")
                campiLista = ['id', 'croce'] + campiLista
            } else {
                if (!params.sort) {
                    params.sort = 'id'
                }// fine del blocco if-else
                lista = Militefunzione.findAllByCroce(croce, params)
            }// fine del blocco if-else
        } else {
            lista = Militefunzione.findAll(params)
        }// fine del blocco if-else

        render(view: 'list', model: [militefunzioneInstanceList: lista, militefunzioneInstanceTotal: 0, campiLista: campiLista], params: params)
    } // fine del metodo

    def create() {
        Croce croce = croceService.getCroce(request)
        params.siglaCroce = croce
        def listaMiliti = null
        def listaFunzioni = null

        if (croce) {
            listaMiliti = Milite.findAllByCroce(croce, [sort: 'cognome'])
            listaFunzioni = Funzione.findAllByCroce(croce, [sort: 'ordine'])
        }// fine del blocco if

        render(view: 'create', model: [
                listaMiliti: listaMiliti,
                listaFunzioni: listaFunzioni,
                militefunzioneInstance: new Militefunzione(params)
        ], params: params)
    } // fine del metodo

    def save() {
        Croce croce = croceService.getCroce(request)
        def militefunzioneInstance = new Militefunzione(params)

        if (croce) {
            params.siglaCroce = croce.sigla
            if (!militefunzioneInstance.croce) {
                militefunzioneInstance.croce = croce
            }// fine del blocco if
        }// fine del blocco if

        if (!militefunzioneInstance.save(flush: true)) {
            render(view: 'create', model: [militefunzioneInstance: militefunzioneInstance], params: params)
            return
        }// fine del blocco if

        flash.message = message(code: 'default.created.message', args: [message(code: 'militefunzione.label', default: 'Militefunzione'), militefunzioneInstance.id])
        redirect(action: 'show', id: militefunzioneInstance.id)
    } // fine del metodo

    def show(Long id) {
        def militefunzioneInstance = Militefunzione.get(id)

        if (!militefunzioneInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'militefunzione.label', default: 'Militefunzione'), id])
            redirect(action: 'list')
            return
        }// fine del blocco if

        params.siglaCroce = croceService.getSiglaCroce(request)
        render(view: 'show', model: [militefunzioneInstance: militefunzioneInstance], params: params)
    } // fine del metodo

    def edit(Long id) {
        Croce croce = croceService.getCroce(request)
        def militefunzioneInstance = Militefunzione.get(id)
        def listaMiliti = null
        def listaFunzioni = null

        if (croce) {
            listaMiliti = Milite.findAllByCroce(croce, [sort: 'cognome'])
            listaFunzioni = Funzione.findAllByCroce(croce, [sort: 'ordine'])
        }// fine del blocco if

        if (!militefunzioneInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'militefunzione.label', default: 'Militefunzione'), id])
            redirect(action: 'list')
            return
        }// fine del blocco if

        params.siglaCroce = croceService.getSiglaCroce(request)
        render(view: 'edit', model: [
                listaMiliti: listaMiliti,
                listaFunzioni: listaFunzioni,
                militefunzioneInstance: militefunzioneInstance
        ], params: params)
    } // fine del metodo

    def update(Long id, Long version) {
        def militefunzioneInstance = Militefunzione.get(id)

        if (!militefunzioneInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'militefunzione.label', default: 'Militefunzione'), id])
            redirect(action: 'list')
            return
        }// fine del blocco if

        params.siglaCroce = croceService.getSiglaCroce(request)
        if (version != null) {
            if (militefunzioneInstance.version > version) {
                militefunzioneInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'militefunzione.label', default: 'Militefunzione')] as Object[],
                        "Another user has updated this Militefunzione while you were editing")
                render(view: 'edit', model: [militefunzioneInstance: militefunzioneInstance], params: params)
                return
            }// fine del blocco if
        }// fine del blocco if

        militefunzioneInstance.properties = params

        if (!militefunzioneInstance.save(flush: true)) {
            render(view: 'edit', model: [militefunzioneInstance: militefunzioneInstance], params: params)
            return
        }// fine del blocco if

        flash.message = message(code: 'default.updated.message', args: [message(code: 'militefunzione.label', default: 'Militefunzione'), militefunzioneInstance.id])
        redirect(action: 'show', id: militefunzioneInstance.id)
    } // fine del metodo

    def delete(Long id) {
        def militefunzioneInstance = Militefunzione.get(id)

        if (!militefunzioneInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'militefunzione.label', default: 'Militefunzione'), id])
            redirect(action: 'list')
            return
        }// fine del blocco if

        try {
            militefunzioneInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'militefunzione.label', default: 'Militefunzione'), id])
            redirect(action: 'list')
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'militefunzione.label', default: 'Militefunzione'), id])
            redirect(action: 'show', id: id)
        }
    } // fine del metodo

} // fine della controller classe
