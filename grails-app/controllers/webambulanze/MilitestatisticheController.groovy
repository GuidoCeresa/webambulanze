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
class MilitestatisticheController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def militeService

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def funzioneService

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def militeturnoService

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def croceService

    def index() {
        redirect(action: 'list', params: params)
    } // fine del metodo

    def list(int max) {
        def lista = null
        Croce croce = croceService.getCroceCorrente(session)
        Milite milite
        HashMap mappa = new HashMap()
        mappa.put('titolo', 'nomignolo')
        mappa.put('campo', 'database')
        def campiLista = [[:],
                'milite',
                'status',
                'turni',
                'ore']
        def campiExtra

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
                lista = Militestatistiche.findAll("from Militestatistiche order by croce_id,milite_id")
                campiLista = ['id', 'croce'] + campiLista
            } else {
                if (!params.sort) {
                    params.sort = 'milite'
                }// fine del blocco if-else
                if (militeService.isLoggatoAdminOrMore()) {
                    lista = Militestatistiche.findAllByCroce(croce, params)
                } else {
                    milite = militeService.militeLoggato
                    if (milite) {
                        lista = Militestatistiche.findByMilite(milite)
                    }// fine del blocco if
                }// fine del blocco if-else
                campiExtra = funzioneService.campiExtraPerCroce(croce)
                if (campiExtra) {
                    for (int k = 1; k <= campiExtra.size(); k++) {
                        mappa = ['titolo': campiExtra.get(k - 1), 'campo': 'funz' + "${k}"]
                        campiLista.add(mappa)
                    } // fine del ciclo for
                }// fine del blocco if

            }// fine del blocco if-else
        } else {
            lista = Milite.findAll(params)
        }// fine del blocco if-else

        //--elimina il primo elemento della lista che serviva solo per evitare che fosse una lista di stringhe
        campiLista.remove([:])

        render(view: 'list', model: [militestatisticheInstanceList: lista, militestatisticheInstanceTotal: 0, campiLista: campiLista, campiExtra: null], params: params)
    } // fine del metodo

    def calcola() {
        militeturnoService.calcola(session)
        redirect(action: 'list', params: params)
    } // fine del metodo

    @Secured([Cost.ROLE_PROG])
    def create() {
        params.siglaCroce = session[Cost.SESSIONE_SIGLA_CROCE]

        render(view: 'create', model: [militestatisticheInstance: new Militestatistiche(params)], params: params)
    } // fine del metodo

    @Secured([Cost.ROLE_PROG])
    def save() {
        Croce croce = croceService.getCroceCorrente(session)
        def militestatisticheInstance = new Militestatistiche(params)

        if (croce) {
            params.siglaCroce = croce.sigla
            if (!militestatisticheInstance.croce) {
                militestatisticheInstance.croce = croce
            }// fine del blocco if
        }// fine del blocco if

        if (!militestatisticheInstance.save(flush: true)) {
            render(view: 'create', model: [militestatisticheInstance: militestatisticheInstance], params: params)
            return
        }// fine del blocco if

        flash.message = message(code: 'default.created.message', args: [message(code: 'militestatistiche.label', default: 'Militestatistiche'), militestatisticheInstance.id])
        redirect(action: 'show', id: militestatisticheInstance.id)
    } // fine del metodo

    @Secured([Cost.ROLE_PROG])
    def show(Long id) {
        def militestatisticheInstance = Militestatistiche.get(id)

        if (!militestatisticheInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'militestatistiche.label', default: 'Militestatistiche'), id])
            redirect(action: 'list')
            return
        }// fine del blocco if

        params.siglaCroce = session[Cost.SESSIONE_SIGLA_CROCE]
        render(view: 'show', model: [militestatisticheInstance: militestatisticheInstance], params: params)
    } // fine del metodo

    @Secured([Cost.ROLE_PROG])
    def edit(Long id) {
        def militestatisticheInstance = Militestatistiche.get(id)

        if (!militestatisticheInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'militestatistiche.label', default: 'Militestatistiche'), id])
            redirect(action: 'list')
            return
        }// fine del blocco if

        params.siglaCroce = session[Cost.SESSIONE_SIGLA_CROCE]
        render(view: 'edit', model: [militestatisticheInstance: militestatisticheInstance], params: params)
    } // fine del metodo

    @Secured([Cost.ROLE_PROG])
    def update(Long id, Long version) {
        def militestatisticheInstance = Militestatistiche.get(id)

        if (!militestatisticheInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'militestatistiche.label', default: 'Militestatistiche'), id])
            redirect(action: 'list')
            return
        }// fine del blocco if

        params.siglaCroce = session[Cost.SESSIONE_SIGLA_CROCE]
        if (version != null) {
            if (militestatisticheInstance.version > version) {
                militestatisticheInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'militestatistiche.label', default: 'Militestatistiche')] as Object[],
                        "Another user has updated this Militestatistiche while you were editing")
                render(view: 'edit', model: [militestatisticheInstance: militestatisticheInstance], params: params)
                return
            }
        }

        militestatisticheInstance.properties = params

        if (!militestatisticheInstance.save(flush: true)) {
            render(view: 'edit', model: [militestatisticheInstance: militestatisticheInstance], params: params)
            return
        }// fine del blocco if

        flash.message = message(code: 'default.updated.message', args: [message(code: 'militestatistiche.label', default: 'Militestatistiche'), militestatisticheInstance.id])
        redirect(action: 'show', id: militestatisticheInstance.id)
    } // fine del metodo

    @Secured([Cost.ROLE_PROG])
    def delete(Long id) {
        def militestatisticheInstance = Militestatistiche.get(id)

        if (!militestatisticheInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'militestatistiche.label', default: 'Militestatistiche'), id])
            redirect(action: 'list')
            return
        }// fine del blocco if

        try {
            militestatisticheInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'militestatistiche.label', default: 'Militestatistiche'), id])
            redirect(action: 'list')
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'militestatistiche.label', default: 'Militestatistiche'), id])
            redirect(action: 'show', id: id)
        }
    } // fine del metodo

} // fine della controller classe
