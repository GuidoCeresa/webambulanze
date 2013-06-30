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

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def utenteService

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
                'utente',
                'ruolo']

        if (!params.sort) {
            params.sort = 'utente'
        }// fine del blocco if-else

        if (croce) {
            params.siglaCroce = croce.sigla
            if (params.siglaCroce.equals(Cost.CROCE_ALGOS)) {
                lista = UtenteRuolo.findAll("from UtenteRuolo order by ruolo,utente")
            } else {
                if (!params.sort) {
                    params.sort = 'utente'
                }// fine del blocco if-else
                lista = utenteService.tuttiUtentiRuoloDellaCroceSenzaProgrammatore(croce)
            }// fine del blocco if-else
        } else {
            lista = utenteService.tuttiSenzaProgrammatore(params)
        }// fine del blocco if-else

        render(view: 'list', model: [utenteRuoloInstanceList: lista, utenteRuoloInstanceTotal: 0, campiLista: campiLista], params: params)
    } // fine del metodo

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
        def utenteRuoloInstance = UtenteRuolo.get(24)
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
