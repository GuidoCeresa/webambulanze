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
        if (params.order) {
            if (params.order == 'asc') {
                params.order = 'desc'
            } else {
                params.order = 'asc'
            }// fine del blocco if-else
        } else {
            params.order = 'desc'
        }// fine del blocco if-else

        if (croce) {
            params.siglaCroce = croce.sigla
            if (params.siglaCroce.equals(Cost.CROCE_ALGOS)) {
                lista = Logo.findAll("from Logo order by croce_logo_id,time")
                campiLista = ['id', 'croceLogo'] + campiLista
            } else {
                if (!params.sort) {
                    params.sort = 'time'
                }// fine del blocco if-else
                lista = Logo.findAllByCroceLogo(croce, params)
            }// fine del blocco if-else
        } else {
            lista = Logo.findAll(params)
        }// fine del blocco if-else

        render(view: 'list', model: [logoInstanceList: lista, logoInstanceTotal: 0, campiLista: campiLista], params: params)
    } // fine del metodo

    @Secured([Cost.ROLE_PROG])
    def create() {
        params.siglaCroce = croceService.getSiglaCroce(request)
        if (params.siglaCroce && !params.siglaCroce.equals(Cost.CROCE_ALGOS)) {
            render(view: 'create', model: [logoInstance: new Logo(params)], params: params)
        } else {
            redirect(action: 'list')
        }// fine del blocco if-else
    } // fine del metodo

    @Secured([Cost.ROLE_PROG])
    def save() {
        def logoInstance = new Logo(params)

        params.siglaCroce = croceService.getSiglaCroce(request)
        if (!logoInstance.save(flush: true)) {
            render(view: 'create', model: [logoInstance: logoInstance], params: params)
            return
        }// fine del blocco if

        flash.message = message(code: 'default.created.message', args: [message(code: 'logo.label', default: 'Logo'), logoInstance.id])
        redirect(action: 'show', id: logoInstance.id)
    } // fine del metodo

    def show(Long id) {
        def logoInstance = Logo.get(id)

        if (!logoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'logo.label', default: 'Logo'), id])
            redirect(action: 'list')
            return
        }// fine del blocco if

        params.siglaCroce = croceService.getSiglaCroce(request)
        render(view: 'show', model: [logoInstance: logoInstance], params: params)
    } // fine del metodo

    @Secured([Cost.ROLE_PROG])
    def edit(Long id) {
        def logoInstance = Logo.get(id)

        if (!logoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'logo.label', default: 'Logo'), id])
            redirect(action: 'list')
            return
        }// fine del blocco if

        params.siglaCroce = croceService.getSiglaCroce(request)
        render(view: 'edit', model: [logoInstance: logoInstance], params: params)
    } // fine del metodo

    @Secured([Cost.ROLE_PROG])
    def update(Long id, Long version) {
        def logoInstance = Logo.get(id)

        if (!logoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'logo.label', default: 'Logo'), id])
            redirect(action: 'list')
            return
        }// fine del blocco if

        params.siglaCroce = croceService.getSiglaCroce(request)
        if (version != null) {
            if (logoInstance.version > version) {
                logoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'logo.label', default: 'Logo')] as Object[],
                        "Another user has updated this Logo while you were editing")
                render(view: 'edit', model: [logoInstance: logoInstance], params: params)
                return
            }// fine del blocco if
        }// fine del blocco if

        logoInstance.properties = params

        if (!logoInstance.save(flush: true)) {
            render(view: 'edit', model: [logoInstance: logoInstance], params: params)
            return
        }// fine del blocco if

        flash.message = message(code: 'default.updated.message', args: [message(code: 'logo.label', default: 'Logo'), logoInstance.id])
        redirect(action: 'show', id: logoInstance.id)
    } // fine del metodo

    @Secured([Cost.ROLE_PROG])
    def delete(Long id) {
        def logoInstance = Logo.get(id)

        if (!logoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'logo.label', default: 'Logo'), id])
            redirect(action: 'list')
            return
        }// fine del blocco if

        try {
            logoInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'logo.label', default: 'Logo'), id])
            redirect(action: 'list')
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'logo.label', default: 'Logo'), id])
            redirect(action: 'show', id: id)
        }
    } // fine del metodo

} // fine della controller classe
