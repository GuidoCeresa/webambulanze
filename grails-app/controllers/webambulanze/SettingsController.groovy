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

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def croceService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: 'list', params: params)
    } // fine del metodo

    def list(Integer max) {
        def lista
        Croce croce = croceService.getCroceCorrente(session)
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
                if (!params.sort) {
                    params.sort = 'croce'
                }// fine del blocco if-else
                lista = Settings.findAll()
                campiLista = ['id', 'croce'] + campiLista
            } else {
                lista = Settings.findAllByCroce(croce)
            }// fine del blocco if-else
        } else {
            lista = Settings.findAll()
        }// fine del blocco if-else

        render(view: 'list', model: [settingsInstanceList: lista, utenteInstsettingsInstanceTotalanceTotal: 0, campiLista: campiLista], params: params)
    } // fine del metodo

    @Secured([Cost.ROLE_PROG])
    def create() {
        params.siglaCroce = session[Cost.SESSIONE_SIGLA_CROCE]
        if (params.siglaCroce && params.siglaCroce.equals(Cost.CROCE_ALGOS)) {
            render(view: 'create', model: [settingsInstance: new Settings(params)], params: params)
        } else {
            redirect(action: 'list')
        }// fine del blocco if-else
    } // fine del metodo

    @Secured([Cost.ROLE_PROG])
    def save() {
        def settingsInstance = new Settings(params)

        params.siglaCroce = session[Cost.SESSIONE_SIGLA_CROCE]
        if (!settingsInstance.save(flush: true)) {
            render(view: 'create', model: [croceInstance: settingsInstance], params: params)
            return
        }// fine del blocco if

        flash.message = message(code: 'default.created.message', args: [message(code: 'settings.label', default: 'Settings'), settingsInstance.id])
        redirect(action: 'show', id: settingsInstance.id)
    } // fine del metodo

    def show(Long id) {
        def settingsInstance = Settings.get(id)

        if (!settingsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'settings.label', default: 'Settings'), id])
            redirect(action: 'list')
            return
        }// fine del blocco if

        params.siglaCroce = session[Cost.SESSIONE_SIGLA_CROCE]
        render(view: 'show', model: [settingsInstance: settingsInstance], params: params)
    } // fine del metodo

    @Secured([Cost.ROLE_PROG])
    def edit(Long id) {
        def settingsInstance = Settings.get(id)

        if (!settingsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'settings.label', default: 'Settings'), id])
            redirect(action: 'list')
            return
        }// fine del blocco if

        params.siglaCroce = session[Cost.SESSIONE_SIGLA_CROCE]
        render(view: 'edit', model: [settingsInstance: settingsInstance], params: params)
    } // fine del metodo

    @Secured([Cost.ROLE_PROG])
    def update(Long id, Long version) {
        def settingsInstance = Settings.get(id)

        if (!settingsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'settings.label', default: 'Settings'), id])
            redirect(action: 'list')
            return
        }// fine del blocco if

        params.siglaCroce = session[Cost.SESSIONE_SIGLA_CROCE]
        if (version != null) {
            if (settingsInstance.version > version) {
                settingsInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'settings.label', default: 'Settings')] as Object[],
                        "Another user has updated this Settings while you were editing")
                render(view: 'edit', model: [settingsInstance: settingsInstance], params: params)
                return
            }// fine del blocco if
        }// fine del blocco if

        settingsInstance.properties = params

        if (!settingsInstance.save(flush: true)) {
            render(view: 'edit', model: [settingsInstance: settingsInstance], params: params)
            return
        }// fine del blocco if

        flash.message = message(code: 'default.updated.message', args: [message(code: 'settings.label', default: 'Settings'), settingsInstance.id])
        redirect(action: 'show', id: settingsInstance.id)
    } // fine del metodo

    @Secured([Cost.ROLE_PROG])
    def delete(Long id) {
        def settingsInstance = Settings.get(id)

        if (!settingsInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'settings.label', default: 'Settings'), id])
            redirect(action: 'list')
            return
        }// fine del blocco if

        try {
            settingsInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'settings.label', default: 'Settings'), id])
            redirect(action: 'list')
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'settings.label', default: 'Settings'), id])
            redirect(action: 'show', id: id)
        }
    } // fine del metodo

} // fine della controller classe
