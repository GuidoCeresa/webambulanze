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

@Secured([Cost.ROLE_ADMIN])
class SettingsController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    } // fine del metodo

    def list(Integer max) {
        def lista
        Croce croce
        String sigla
        def campiLista = [
                'startLogin',
                'startController',
                'allControllers',
                'controlli',
                'militePuoInserireAltri',
                'militePuoModificareAltri',
                'militePuoCancellareAltri',
                'tipoControlloModifica',
                'maxMinutiTrascorsiModifica',
                'minGiorniMancantiModifica',
                'tipoControlloCancellazione',
                'maxMinutiTrascorsiCancellazione',
                'minGiorniMancantiCancellazione']

        if (grailsApplication.mainContext.servletContext.croce) {
            croce = grailsApplication.mainContext.servletContext.croce
            sigla = croce.sigla
            if (sigla.equals(Cost.CROCE_ALGOS)) {
                lista = Settings.findAll()
                campiLista = ['id', 'croce'] + campiLista
            } else {
                lista = Settings.findAllByCroce(croce)
            }// fine del blocco if-else
        } else {
            lista = Settings.findAll()
        }// fine del blocco if-else

        [settingsInstanceList: lista, settingsInstanceTotal: 0, campiLista: campiLista]
    }

    @Secured([Cost.ROLE_PROG])
    def create() {
        [settingsInstance: new Settings(params)]
    } // fine del metodo

    @Secured([Cost.ROLE_PROG])
    def save() {
        def settingsInstance = new Settings(params)
        if (!settingsInstance.save(flush: true)) {
            render(view: "create", model: [settingsInstance: settingsInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'settings.label', default: 'Settings'), settingsInstance.id])
        redirect(action: "show", id: settingsInstance.id)
    } // fine del metodo

    def show(Long id) {
        def settingsInstance = Settings.get(id)
        if (!settingsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'settings.label', default: 'Settings'), id])
            redirect(action: "list")
            return
        }

        [settingsInstance: settingsInstance]
    } // fine del metodo

    @Secured([Cost.ROLE_PROG])
    def edit(Long id) {
        def settingsInstance = Settings.get(id)
        if (!settingsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'settings.label', default: 'Settings'), id])
            redirect(action: "list")
            return
        }

        [settingsInstance: settingsInstance]
    } // fine del metodo

    @Secured([Cost.ROLE_PROG])
    def update(Long id, Long version) {
        def settingsInstance = Settings.get(id)
        if (!settingsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'settings.label', default: 'Settings'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (settingsInstance.version > version) {
                settingsInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'settings.label', default: 'Settings')] as Object[],
                        "Another user has updated this Settings while you were editing")
                render(view: "edit", model: [settingsInstance: settingsInstance])
                return
            }
        }

        settingsInstance.properties = params

        if (!settingsInstance.save(flush: true)) {
            render(view: "edit", model: [settingsInstance: settingsInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'settings.label', default: 'Settings'), settingsInstance.id])
        redirect(action: "show", id: settingsInstance.id)
    } // fine del metodo

    @Secured([Cost.ROLE_PROG])
    def delete(Long id) {
        def settingsInstance = Settings.get(id)
        if (!settingsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'settings.label', default: 'Settings'), id])
            redirect(action: "list")
            return
        }

        try {
            settingsInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'settings.label', default: 'Settings'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'settings.label', default: 'Settings'), id])
            redirect(action: "show", id: id)
        }
    } // fine del metodo

} // fine della controller classe
