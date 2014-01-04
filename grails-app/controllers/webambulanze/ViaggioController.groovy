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

import java.sql.Timestamp

@Secured([Cost.ROLE_MILITE])
class ViaggioController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def croceService

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def automezzoService

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def turnoService

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def viaggioService

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def militeService

    def index() {
        redirect(action: 'list', params: params)
    } // fine del metodo

    def list(Integer max) {
        def lista
        Croce croce = croceService.getCroce(request)
        def campiLista = [
                'numeroServizio',
                'numeroViaggio',
                'numeroBolla',
                'tipoViaggio',
                'giorno',
                'automezzo',
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
                if (!params.sort) {
                    params.sort = 'giorno'
                }// fine del blocco if-else
                lista = Viaggio.findAllByCroce(croce, params)
            }// fine del blocco if-else
        } else {
            lista = Viaggio.findAll(params)
        }// fine del blocco if-else

        flash.errors = ''
        render(view: 'list', model: [titoloLista: 'prot', viaggioInstanceList: lista, viaggioInstanceTotal: 0, campiLista: campiLista], params: params)
    } // fine del metodo

    def listaMezzo(Long id) {
        def lista
        String titolo
        String nomeMezzo = ''
        Croce croce = croceService.getCroce(request)
        Automezzo mezzo = null
        def campiLista = [
                'numeroServizio',
                'numeroViaggio',
                'numeroBolla',
                'tipoViaggio',
                'giorno',
                'chilometriPercorsi',
                'chilometriArrivo',
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

        if (id) {
            mezzo = Automezzo.findById(id)
            nomeMezzo = mezzo.targa
        }// fine del blocco if

        if (croce) {
            params.siglaCroce = croce.sigla
            if (params.siglaCroce.equals(Cost.CROCE_ALGOS)) {
                lista = Viaggio.findAll("from Viaggio order by croce_id,giorno")
                campiLista = ['id', 'croce'] + campiLista
            } else {
                lista = Viaggio.findAllByAutomezzo(mezzo)
            }// fine del blocco if-else
        } else {
            lista = Viaggio.findAll(params)
        }// fine del blocco if-else

        flash.errors = ''
        titolo = "Viaggi del mezzo ${nomeMezzo}"
        render(view: 'list', model: [titolo: titolo, viaggioInstanceList: lista, viaggioInstanceTotal: 0, campiLista: campiLista], params: params)
    } // fine del metodo

    def create() {
        Croce croce = croceService.getCroce(request)
        params.siglaCroce = croceService.getSiglaCroce(request)
        ArrayList listaTipologieViaggi = TipoViaggio.getListaNomi()
        ArrayList listaAutomezzi = automezzoService.getAllTarga(croce)
        ArrayList listaUltimiTurni = turnoService.getLastTwoDays(croce)
        ArrayList listaUltimiTurniId = turnoService.getLastTwoDaysId(croce)
        String tipoSelezionato

        //--selezione suggerita
        tipoSelezionato = listaTipologieViaggi[0]

        //        render(view: 'selezionetipo', params: params)
        render(view: 'selezione', model: [
                listaTipologieViaggi: listaTipologieViaggi,
                tipoSelezionato: tipoSelezionato,
                listaAutomezzi: listaAutomezzi,
                listaUltimiTurni: listaUltimiTurni,
                listaUltimiTurniId: listaUltimiTurniId],
                params: params)
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
        Turno turno
        long turnoId = 0
        long automezzoId = 0
        Settings settingsCroce
        Date giorno = new Date()
        Timestamp inizio = giorno.toTimestamp()
        Timestamp fine = giorno.toTimestamp()
        Funzione funzAut = Funzione.findByCroceAndSigla(croce, Cost.CRPT_FUNZIONE_AUT_118)
        //@todo ASSOLUTAMENTE PROVVISORIO
        Funzione funzSocDae = Funzione.findByCroceAndSigla(croce, Cost.CRPT_FUNZIONE_DAE)
        Funzione funzSoc = Funzione.findByCroceAndSigla(croce, Cost.CRPT_FUNZIONE_SOC)
        Funzione funzBar = Funzione.findByCroceAndSigla(croce, Cost.CRPT_FUNZIONE_BAR)
        ArrayList listaAutisti = viaggioService.listaMilitiAbilitati(croce, funzAut)
        ArrayList listaSocDae = viaggioService.listaMilitiAbilitati(croce, funzSocDae)
        ArrayList listaSoccorritori = viaggioService.listaMilitiAbilitati(croce, funzSoc)
        ArrayList listaBarellieri = viaggioService.listaMilitiAbilitati(croce, funzBar)
        String autistaTurno = ''
        String siglaTurno = ''
        String siglaAutomezzo = ''
        String tipoForm = 'Crea viaggio 118'

        //--controlla che sia selezionato il tipo di viaggio
        if (params.tipoViaggio[1].equals('null')) {
            flash.errors = "Devi selezionare una tipologia di servizio, prima di proseguire"
            redirect(action: 'create')
            return
        }// fine del blocco if

        if (params.auto) {
            automezzo = Automezzo.findByCroceAndTarga(croce, params.auto)
            if (automezzo) {
                automezzoId = automezzo.id
                siglaAutomezzo = automezzo.targa
                params.automezzo = automezzo
                params.numeroViaggio = automezzo.numeroViaggiEffettuati + 1
                params.chilometriPartenza = automezzo.chilometriTotaliPercorsi
                params.chilometriArrivo = automezzo.chilometriTotaliPercorsi
            } else {
                flash.errors = "Devi selezionare un automezzo, prima di proseguire"
                redirect(action: 'create')
                return
            }// fine del blocco if-else
        }// fine del blocco if
        if (croce) {
            settingsCroce = croce.settings
            if (settingsCroce) {
                params.numeroServizio = settingsCroce.numeroServiziEffettuati + 1
            }// fine del blocco if
        }// fine del blocco if

        if (params.turno && !params.turno.equals('null')) {
            turnoId = Long.decode(params.turno)
            if (turnoId) {
                turno = Turno.findById(turnoId)
                if (turno) {
                    siglaTurno = turno.tipoTurno?.descrizione
                    if (turno.militeFunzione1) {
                        params.autistaEmergenza = turno.militeFunzione1
                        autistaTurno = turno.militeFunzione1.toString()
                    }// fine del blocco if
                    if (turno.militeFunzione2) {
                        params.soccorritoreDae = turno.militeFunzione2
                    }// fine del blocco if
                    if (turno.militeFunzione3) {
                        params.soccorritore = turno.militeFunzione3
                    }// fine del blocco if
                    if (turno.militeFunzione4) {
                        params.barelliereAffiancamento = turno.militeFunzione4
                    }// fine del blocco if
                }// fine del blocco if
            }// fine del blocco if
        } else {
//            flash.errors = "Devi selezionare un turno, prima di proseguire"
//            redirect(action: 'create')
//            return
        }// fine del blocco if-else
        def a = params

        //--valori suggeriti
        if (true) {
            params.tipoViaggio = TipoViaggio.auto118
            params.codiceInvio = CodiceInvio.verde
            params.luogoEvento = LuogoEvento.Z
            params.codiceRicovero = CodiceRicovero.normale
            params.patologia = Patologia.C20
        }// fine del blocco if

        if (true) {
            params.giorno = giorno
            //params.inizio = inizio
            //params.fine = fine
        }// fine del blocco if

        //--valori suggeriti
        //--equipaggio
        if (true) {
            params.tipoViaggio = TipoViaggio.auto118
            params.codiceInvio = CodiceInvio.verde
            params.luogoEvento = LuogoEvento.Z
            params.codiceRicovero = CodiceRicovero.normale
            params.patologia = Patologia.C20
        }// fine del blocco if

        if (true) {
            //    if (tipoViaggio.equals('118')) {
            render(view: 'fillViaggio', model: [
                    // viaggioInstance: new Viaggio(params),
                    tipoForm: tipoForm,
                    tipoViaggio: TipoViaggio.auto118.toString(), //@todo provvisorio
                    automezzoId: params.automezzo.id,
                    turnoId: turnoId],
                    params: params)
//            render(view: 'create118', model: [
//                    viaggioInstance: new Viaggio(params),
//                    turnoId: turnoId,
//                    siglaTurno: siglaTurno,
//                    automezzoId: automezzoId,
//                    siglaAutomezzo: siglaAutomezzo,
//                    listaAutisti: listaAutisti,
//                    listaSocDae: listaSocDae,
//                    listaSoccorritori: listaSoccorritori,
//                    listaBarellieri: listaBarellieri,
//                    autistaTurno: autistaTurno],
//                    params: params)
        } else {
            render(view: 'selezionemancante', params: params)
        }// fine del blocco if-else
    } // fine del metodo

    def save() {
        Milite milite = null
        Turno turno

        if (params.list) {
            redirect(action: 'list')
            return
        }// fine del blocco if
        def pippoz = params

        if (params.turnoId) {
            turno = Turno.findById(params.turnoId)
        }// fine del blocco if

        params.tipoViaggio = TipoViaggio.auto118   //@todo ASSOLUTAMENTE PROVVISORIO

        if (params.tipoViaggio) {
//            params.tipoViaggio = TipoViaggio.getDaSigla(params.tipoViaggio)  //@todo rimettere
        }// fine del blocco if

        if (params.automezzoId) {
            params.automezzo = Automezzo.findById(params.automezzoId)
        }// fine del blocco if

        //@todo da controllare
        if (!params.giorno) {
            params.giorno = new Date()
        }// fine del blocco if

        //@todo da controllare
        if (!params.inizio) {
            params.inizio = params.giorno
        }// fine del blocco if

        //@todo da controllare
        if (!params.fine) {
            params.fine = params.giorno
        }// fine del blocco if

        if (params.codiceInvio) {
            params.codiceInvio = CodiceInvio.getDaNome(params.codiceInvio)//@todo rimettere
        }// fine del blocco if

        if (params.luogoEvento) {
            params.luogoEvento = LuogoEvento.getDaNome(params.luogoEvento)  //@todo rimettere
        }// fine del blocco if

        if (params.patologia) {
            params.patologia = Patologia.getDaNome(params.patologia)  //@todo rimettere
        }// fine del blocco if

        if (params.codiceRicovero) {
            params.codiceRicovero = CodiceRicovero.getDaNome(params.codiceRicovero) //@todo rimettere
        }// fine del blocco if

        if (params.chilometriPartenza) {
            params.chilometriPartenza = Integer.decode(params.chilometriPartenza)
        }// fine del blocco if

        if (params.chilometriArrivo) {
            params.chilometriArrivo = Integer.decode(params.chilometriArrivo)
        }// fine del blocco if

        if (params.militeFunzione1) {
            params.militeFunzione1 = Milite.findById(params.militeFunzione1)
        } else {
            params.militeFunzione1 = Milite.get(1)
        }// fine del blocco if-else

        if (params.militeFunzione2) {
            params.militeFunzione2 = Milite.findById(params.militeFunzione2)
        } else {
            params.militeFunzione2 = Milite.get(1)
        }// fine del blocco if-else

        if (params.militeFunzione3) {
            params.militeFunzione3 = Milite.findById(params.militeFunzione3)
        } else {
            params.militeFunzione3 = Milite.get(1)
        }// fine del blocco if-else

        if (params.militeFunzione4) {
            params.militeFunzione4 = Milite.findById(params.militeFunzione4)
        } else {
            params.militeFunzione4 = Milite.get(1)
        }// fine del blocco if-else

        def viaggioInstance = new Viaggio(params)
        Croce croce = croceService.getCroce(request)

        if (croce) {
            params.siglaCroce = croce.sigla
            if (!viaggioInstance.croce) {
                viaggioInstance.croce = croce
            }// fine del blocco if
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
        flash.message = 'Registrato il servizio n° ' + viaggioInstance.numeroServizio
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

        def pippoz = params

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
