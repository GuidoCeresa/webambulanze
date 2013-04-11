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

class ViaggioController {

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
                'giorno',
                'inizio',
                'fine',
        ]

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
                lista = Viaggio.findAll("from Viaggio order by croce_id,giorno")
                campiLista = ['id', 'croce'] + campiLista
            } else {
                if (!params.sort) {
                    params.sort = 'giorno'
                }// fine del blocco if-else
                lista = Viaggio.findAllByCroce(croce, params)
            }// fine del blocco if-else
        } else {
            lista = Viaggio.findAll(params)
        }// fine del blocco if-else

        render(view: 'list', model: [viaggioInstanceList: lista, viaggioInstanceTotal: 0, campiLista: campiLista], params: params)
    } // fine del metodo

    def create() {
        params.siglaCroce = croceService.getSiglaCroce(request)
        render(view: 'selezione', params: params)

//        [viaggioInstance: new Viaggio(params)]
    } // fine del metodo

    def nuovoServizio118() {
        render(view: 'create118', model: [viaggioInstance: new Viaggio(params)], params: params)
    } // fine del metodo

    def nuovoServizioOrdinario() {
        render(view: 'createOrdinario', model: [viaggioInstance: new Viaggio(params)], params: params)
    } // fine del metodo

    def nuovoServizioDializzati() {
        render(view: 'createDializzato', model: [viaggioInstance: new Viaggio(params)], params: params)
    } // fine del metodo

    def save() {
        def viaggioInstance = new Viaggio(params)
        if (!viaggioInstance.save(flush: true)) {
            render(view: "create", model: [viaggioInstance: viaggioInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'viaggio.label', default: 'Viaggio'), viaggioInstance.id])
        redirect(action: "show", id: viaggioInstance.id)
    } // fine del metodo

    def show(Long id) {
        def viaggioInstance = Viaggio.get(id)
        if (!viaggioInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'viaggio.label', default: 'Viaggio'), id])
            redirect(action: "list")
            return
        }

        [viaggioInstance: viaggioInstance]
    } // fine del metodo

    def edit(Long id) {
        def viaggioInstance = Viaggio.get(id)
        if (!viaggioInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'viaggio.label', default: 'Viaggio'), id])
            redirect(action: "list")
            return
        }

        [viaggioInstance: viaggioInstance]
    } // fine del metodo

    def update(Long id, Long version) {
        def viaggioInstance = Viaggio.get(id)
        if (!viaggioInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'viaggio.label', default: 'Viaggio'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (viaggioInstance.version > version) {
                viaggioInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'viaggio.label', default: 'Viaggio')] as Object[],
                        "Another user has updated this Viaggio while you were editing")
                render(view: "edit", model: [viaggioInstance: viaggioInstance])
                return
            }
        }

        viaggioInstance.properties = params

        if (!viaggioInstance.save(flush: true)) {
            render(view: "edit", model: [viaggioInstance: viaggioInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'viaggio.label', default: 'Viaggio'), viaggioInstance.id])
        redirect(action: "show", id: viaggioInstance.id)
    } // fine del metodo

    def delete(Long id) {
        def viaggioInstance = Viaggio.get(id)
        if (!viaggioInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'viaggio.label', default: 'Viaggio'), id])
            redirect(action: "list")
            return
        }

        try {
            viaggioInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'viaggio.label', default: 'Viaggio'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'viaggio.label', default: 'Viaggio'), id])
            redirect(action: "show", id: id)
        }
    } // fine del metodo

} // fine della controller classe
