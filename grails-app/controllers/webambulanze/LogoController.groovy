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
class LogoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    } // fine del metodo

    def list(Integer max) {
        def lista
        Croce croce
        String sigla
        def campiLista = [
                'time',
                'utente',
                'ruolo',
                'evento',
                'livello',
                'milite',
                'tipoTurno',
                'turno',
                'giorno'
        ]
        if (!params.sort) {
            params.sort = 'time'
        }// fine del blocco if-else
        if (params.order) {
            if (params.order == 'asc') {
                params.order = 'desc'
            } else {
                params.order = 'asc'
            }// fine del blocco if-else
        } else {
            params.order = 'desc'
        }// fine del blocco if-else

        if (grailsApplication.mainContext.servletContext.croce) {
            croce = grailsApplication.mainContext.servletContext.croce
            sigla = croce.sigla
            if (sigla.equals(Cost.CROCE_ALGOS)) {
                lista = Logo.findAll(params)
                campiLista = ['id', 'croceLogo'] + campiLista
            } else {
                lista = Logo.findAllByCroceLogo(croce, params)
            }// fine del blocco if-else
        } else {
            lista = Logo.findAll(params)
        }// fine del blocco if-else

        [logoInstanceList: lista, logoInstanceTotal: 0, campiLista: campiLista]
    }

    @Secured([Cost.ROLE_PROG])
    def create() {
        [logoInstance: new Logo(params)]
    } // fine del metodo

    @Secured([Cost.ROLE_PROG])
    def save() {
        def logoInstance = new Logo(params)
        if (!logoInstance.save(flush: true)) {
            render(view: "create", model: [logoInstance: logoInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'logo.label', default: 'Logo'), logoInstance.id])
        redirect(action: "show", id: logoInstance.id)
    } // fine del metodo

    def show(Long id) {
        def logoInstance = Logo.get(id)
        if (!logoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'logo.label', default: 'Logo'), id])
            redirect(action: "list")
            return
        }

        [logoInstance: logoInstance]
    } // fine del metodo

    @Secured([Cost.ROLE_PROG])
    def edit(Long id) {
        def logoInstance = Logo.get(id)
        if (!logoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'logo.label', default: 'Logo'), id])
            redirect(action: "list")
            return
        }

        [logoInstance: logoInstance]
    } // fine del metodo

    @Secured([Cost.ROLE_PROG])
    def update(Long id, Long version) {
        def logoInstance = Logo.get(id)
        if (!logoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'logo.label', default: 'Logo'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (logoInstance.version > version) {
                logoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'logo.label', default: 'Logo')] as Object[],
                        "Another user has updated this Logo while you were editing")
                render(view: "edit", model: [logoInstance: logoInstance])
                return
            }
        }

        logoInstance.properties = params

        if (!logoInstance.save(flush: true)) {
            render(view: "edit", model: [logoInstance: logoInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'logo.label', default: 'Logo'), logoInstance.id])
        redirect(action: "show", id: logoInstance.id)
    } // fine del metodo

    @Secured([Cost.ROLE_PROG])
    def delete(Long id) {
        def logoInstance = Logo.get(id)
        if (!logoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'logo.label', default: 'Logo'), id])
            redirect(action: "list")
            return
        }

        try {
            logoInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'logo.label', default: 'Logo'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'logo.label', default: 'Logo'), id])
            redirect(action: "show", id: id)
        }
    } // fine del metodo

} // fine della controller classe
