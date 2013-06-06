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
class ViaggioController {

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
                'numeroServizio',
                'tipoViaggio',
                'giorno',
                'automezzo',
                'numeroBolla',
                'numeroViaggio',
                'chilometriPercorsi',
                'codiceInvio',
                'codiceRicovero',
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
                lista = Viaggio.findAll("from Viaggio order by croce_id,giorno")
            }// fine del blocco if-else
        } else {
            lista = Viaggio.findAll(params)
        }// fine del blocco if-else

        flash.errors = ''
        render(view: 'list', model: [viaggioInstanceList: lista, viaggioInstanceTotal: 0, campiLista: campiLista], params: params)
    } // fine del metodo

    def create() {
        params.siglaCroce = croceService.getSiglaCroce(request)
        render(view: 'selezionetipo', params: params)

//        [viaggioInstance: new Viaggio(params)]
    } // fine del metodo

    def nuovo118() {
        params.tipoViaggio = '118'
        redirect(action: 'selezionemezzo', params: params)
    } // fine del metodo

    def nuovoOrdinario() {
        params.tipoViaggio = 'ordinario'
        render(view: 'selezionemancante', params: params)
//        redirect(action: 'selezionemezzo', params: params)
    } // fine del metodo

    def nuovoDializzati() {
        params.tipoViaggio = 'dializzati'
        render(view: 'selezionemancante', params: params)
//        redirect(action: 'selezionemezzo', params: params)
    } // fine del metodo

    def nuovoInterno() {
        params.tipoViaggio = 'interno'
        render(view: 'selezionemancante', params: params)
//        redirect(action: 'selezionemezzo', params: params)
    } // fine del metodo

    def selezionemezzo() {
        params.siglaCroce = croceService.getSiglaCroce(request)
        def tipoViaggio = params.tipoViaggio
        render(view: 'selezionemezzo', model: [viaggioInstance: new Viaggio(params), tipoViaggio: tipoViaggio], params: params)
    } // fine del metodo

    def nuovoViaggio() {
        Croce croce = croceService.getCroce(request)
        params.siglaCroce = croceService.getSiglaCroce(request)
        String tipoViaggio = params.tipoViaggio
        Automezzo automezzo
        Settings settingsCroce

        //--valori recuperati dal DB
        if (params.automezzo) {
            automezzo = Automezzo.findById(Long.decode(params.automezzo.id))
            if (automezzo) {
                params.numeroViaggio = automezzo.numeroViaggiEffettuati + 1
                params.chilometriPartenza = automezzo.chilometriTotaliPercorsi
                params.chilometriArrivo = automezzo.chilometriTotaliPercorsi
            }// fine del blocco if
        }// fine del blocco if
        if (croce) {
            settingsCroce = croce.settings
            if (settingsCroce) {
                params.numeroServizio = settingsCroce.numeroServiziEffettuati + 1
            }// fine del blocco if
        }// fine del blocco if

        //--valori suggeriti
        if (true) {
            params.tipoViaggio = TipoViaggio.auto118
            params.codiceInvio = CodiceInvio.verde
            params.luogoEvento = LuogoEvento.Z
            params.codiceRicovero = CodiceRicovero.normale
            params.patologia = Patologia.C20
        }// fine del blocco if

        if (tipoViaggio.equals('118')) {
            render(view: 'create118', model: [viaggioInstance: new Viaggio(params)], params: params)
        } else {
            render(view: 'selezionemancante', params: params)
        }// fine del blocco if-else
    } // fine del metodo

    def pippoz() {
        def stop
    }

    def save() {
        if (params.list) {
            redirect(action: 'list')
            return
        }// fine del blocco if

        params.tipoViaggio = TipoViaggio.auto118   //@todo ASSOLUTAMENTE PROVVISORIO

        def viaggioInstance = new Viaggio(params)
        Croce croce = croceService.getCroce(request)

        if (croce) {
            params.siglaCroce = croce.sigla
            if (!viaggioInstance.croce) {
                viaggioInstance.croce = croce
            }// fine del blocco if
        }// fine del blocco if

        if (!viaggioInstance.giorno) {
            viaggioInstance.giorno = new Date()
        }// fine del blocco if

        if (!viaggioInstance.inizio) {
            //  viaggioInstance.inizio = viaggioInstance.giorno.toTimestamp()
        }// fine del blocco if

        if (!viaggioInstance.fine) {
            //  viaggioInstance.fine = viaggioInstance.giorno.toTimestamp()
        }// fine del blocco if

        //--controllo chilometraggio
        if (true) {
            if (viaggioInstance.chilometriArrivo == viaggioInstance.chilometriPartenza) {
                flash.errors = "Viaggio non registrato - Ti sei dimenticato di modificare i chilometri all'arrivo"
                render(view: 'create118', model: [viaggioInstance: viaggioInstance])
                return
            }// fine del blocco if
            if (viaggioInstance.chilometriArrivo < viaggioInstance.chilometriPartenza) {
                flash.errors = "Viaggio non registrato - I chilometri all'arrivo non possono essere minori di quelli alla partenza"
                render(view: 'create118', model: [viaggioInstance: viaggioInstance])
                return
            }// fine del blocco if
        }// fine del blocco if

        if (!viaggioInstance.save(flush: true)) {
            render(view: "create", model: [viaggioInstance: viaggioInstance])
            return
        }// fine del blocco if

        if (viaggioInstance) {
            afterRegolaAutomezzo(viaggioInstance.id)
            afterRegolaSettingsCroce(viaggioInstance.id)
        }// fine del blocco if

//        flash.message = message(code: 'default.created.message', args: [message(code: 'viaggio.label', default: 'Viaggio'), viaggioInstance.id])
        flash.message='Registrato il servizio n° '+ viaggioInstance.numeroServizio
        redirect(action: 'list')
    } // fine del metodo

    def show(Long id) {
        def viaggioInstance = Viaggio.get(id)
        if (!viaggioInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'viaggio.label', default: 'Viaggio'), id])
            redirect(action: 'list')
            return
        }

        [viaggioInstance: viaggioInstance]
    } // fine del metodo

    def edit(Long id) {
        def viaggioInstance = Viaggio.get(id)
        if (!viaggioInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'viaggio.label', default: 'Viaggio'), id])
            redirect(action: 'list')
            return
        }

        [viaggioInstance: viaggioInstance]
    } // fine del metodo

    def update(Long id, Long version) {
        def viaggioInstance = Viaggio.get(id)
        if (!viaggioInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'viaggio.label', default: 'Viaggio'), id])
            redirect(action: 'list')
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

        if (viaggioInstance) {
            afterRegolaAutomezzo(viaggioInstance.id)
            afterRegolaSettingsCroce(viaggioInstance.id)
        }// fine del blocco if

        flash.message = message(code: 'default.updated.message', args: [message(code: 'viaggio.label', default: 'Viaggio'), viaggioInstance.id])
        redirect(action: 'list')
    } // fine del metodo

    /**
     * metodo chiamato dopo aver creato o modificato un record
     */
    public afterRegolaAutomezzo(Long id) {
        Automezzo auto
        def viaggioInstance = Viaggio.get(id)

        if (viaggioInstance) {
            auto = viaggioInstance.automezzo
            if (auto) {
                auto.chilometriTotaliPercorsi = viaggioInstance.chilometriArrivo
                auto.numeroViaggiEffettuati = viaggioInstance.numeroViaggio
                auto.save(flush: true)
            }// fine del blocco if
        }// fine del blocco if
    } // fine del metodo

    /**
     * metodo chiamato dopo aver creato o modificato un record
     */
    public afterRegolaSettingsCroce(Long id) {
        Croce croce = croceService.getCroce(request)
        Settings settingsCroce
        def viaggioInstance = Viaggio.get(id)

        if (croce) {
            settingsCroce = croce.settings
            if (settingsCroce) {
                settingsCroce.numeroServiziEffettuati = viaggioInstance.numeroServizio
            }// fine del blocco if
        }// fine del blocco if
    } // fine del metodo

    @Secured([Cost.ROLE_PROG])
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
