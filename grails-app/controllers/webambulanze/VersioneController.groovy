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

class VersioneController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def croceService
    def utenteService
    def springSecurityService

    def index() {
        redirect(action: "list", params: params)
    } // fine del metodo

    def list(Integer max) {
        def lista
        Croce croce = croceService.getCroce(request)
        Utente currUser = (Utente) springSecurityService.getCurrentUser()

        def campiLista = [
                'numero',
                'giorno',
                'titolo',
                'descrizione']

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
                lista = Versione.findAll("from Versione order by croce_id")
                campiLista = ['id', 'croce'] + campiLista
            } else {
                if (utenteService.isProgrammatore(currUser)) {
                    lista = Versione.findAll()
                    campiLista = ['id', 'croce'] + campiLista
                } else {
                    lista = Versione.findAllByCroce(croce, params)
                }// fine del blocco if-else
            }// fine del blocco if-else
        } else {
            lista = Versione.findAll(params)
        }// fine del blocco if-else

        render(view: 'list', model: [versioneInstanceList: lista, versioneInstanceTotal: 0, campiLista: campiLista], params: params)
    } // fine del metodo

    def create() {
        [versioneInstance: new Versione(params)]
    } // fine del metodo

    def save() {
        def versioneInstance = new Versione(params)
        if (!versioneInstance.save(flush: true)) {
            render(view: "create", model: [versioneInstance: versioneInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'versione.label', default: 'Versione'), versioneInstance.id])
        redirect(action: "show", id: versioneInstance.id)
    } // fine del metodo

    def show(Long id) {
        def versioneInstance = Versione.get(id)
        if (!versioneInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'versione.label', default: 'Versione'), id])
            redirect(action: "list")
            return
        }

        [versioneInstance: versioneInstance]
    } // fine del metodo

    def edit(Long id) {
        def versioneInstance = Versione.get(id)
        if (!versioneInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'versione.label', default: 'Versione'), id])
            redirect(action: "list")
            return
        }

        [versioneInstance: versioneInstance]
    } // fine del metodo

    def update(Long id, Long version) {
        def versioneInstance = Versione.get(id)
        if (!versioneInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'versione.label', default: 'Versione'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (versioneInstance.version > version) {
                versioneInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'versione.label', default: 'Versione')] as Object[],
                        "Another user has updated this Versione while you were editing")
                render(view: "edit", model: [versioneInstance: versioneInstance])
                return
            }
        }

        versioneInstance.properties = params

        if (!versioneInstance.save(flush: true)) {
            render(view: "edit", model: [versioneInstance: versioneInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'versione.label', default: 'Versione'), versioneInstance.id])
        redirect(action: "show", id: versioneInstance.id)
    } // fine del metodo

    def delete(Long id) {
        def versioneInstance = Versione.get(id)
        if (!versioneInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'versione.label', default: 'Versione'), id])
            redirect(action: "list")
            return
        }

        try {
            versioneInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'versione.label', default: 'Versione'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'versione.label', default: 'Versione'), id])
            redirect(action: "show", id: id)
        }
    } // fine del metodo

} // fine della controller classe
