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

class TurnoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def springSecurityService
    def logoService
    def militeService
    def croceService
    def turnoService

    static Date dataInizio
    static Date dataFine

    static int giorniVisibili = 7
    static int delta = giorniVisibili - 1

    @Secured([Cost.ROLE_MILITE, Cost.ROLE_OSPITE])
    def indexSecured() {
        redirect(action: 'tabellone', params: params)
    } // fine del metodo

    def index() {
        redirect(action: 'tabellone', params: params)
    } // fine del metodo

    def tabellone = {
        params.siglaCroce = croceService.getSiglaCroce(request)
        flash.message = ''
        flash.listaErrori = null
        dataInizio = AmbulanzaTagLib.creaDataOggi()
        dataFine = (dataInizio + delta).toTimestamp()
        def a = params
        def user = springSecurityService.principal
        def currUser = springSecurityService.getCurrentUser()
        render(view: 'tabellone', model: [dataInizio: dataInizio, dataFine: dataFine], params: params)
    }// fine della closure

    def tabCorrente = {
        params.siglaCroce = croceService.getSiglaCroce(request)
        flash.errors = null
        flash.listaErrori = null
        render(view: 'tabellone', model: [dataInizio: dataInizio, dataFine: dataFine], params: params)
    }// fine della closure

    def tabellonePrima = {
        params.siglaCroce = croceService.getSiglaCroce(request)
        flash.message = ''
        dataInizio -= giorniVisibili
        dataFine -= giorniVisibili
        render(view: 'tabellone', model: [dataInizio: dataInizio, dataFine: dataFine], params: params)
    }// fine della closure

    def tabelloneOggi = {
        params.siglaCroce = croceService.getSiglaCroce(request)
        flash.message = ''
        dataInizio = AmbulanzaTagLib.creaDataOggi()
        dataFine = (dataInizio + delta).toTimestamp()
        render(view: 'tabellone', model: [dataInizio: dataInizio, dataFine: dataFine], params: params)
    }// fine della closure

    def tabelloneLunedi = {
        params.siglaCroce = croceService.getSiglaCroce(request)
        flash.message = ''
        dataInizio = AmbulanzaTagLib.creaDataLunedi()
        dataFine = (dataInizio + delta).toTimestamp()
        render(view: 'tabellone', model: [dataInizio: dataInizio, dataFine: dataFine], params: params)
    }// fine della closure

    def tabelloneDopo = {
        params.siglaCroce = croceService.getSiglaCroce(request)
        flash.message = ''
        dataInizio += giorniVisibili
        dataFine += giorniVisibili
        render(view: 'tabellone', model: [dataInizio: dataInizio, dataFine: dataFine], params: params)
    }// fine della closure

    @Secured([Cost.ROLE_MILITE])
    def newTurno = {
        boolean continua = false
        String tipoTurnoTxt
        boolean nuovoTurno = false
        String giornoNum
        Turno nuovoOppureEsistente = null
        TipoTurno tipoTurno = null
        Croce croce = croceService.getCroce(request)
        Date giorno = Lib.creaData1Gennaio()
        int offSet
        String giornoTxt = ''
        def user = springSecurityService.principal
        Utente currUser = (Utente) springSecurityService.getCurrentUser()

        if (params.giorno) {
            giornoNum = params.giorno
            offSet = Integer.decode(giornoNum) - 1
            giorno = giorno + offSet
            giornoTxt = Lib.presentaDataCompleta(giorno)
        }// fine del blocco if
        if (params.tipoTurno) {
            tipoTurnoTxt = params.tipoTurno
            tipoTurno = TipoTurno.findByCroceAndSigla(croce, tipoTurnoTxt)
        }// fine del blocco if

        if (turnoService?.isPossibileCreareTurno(croce, giorno, tipoTurno)) {
            continua = true
        } else {
            flash.errors = 'Non è possibile creare un turno per una data passata'
            redirect(action: 'tabellone', model: [dataInizio: dataInizio, dataFine: dataFine], params: params)
            return
        }// fine del blocco if-else

        if (continua && croce && giorno && tipoTurno) {
            nuovoOppureEsistente = Turno.findByCroceAndTipoTurnoAndGiorno(croce, tipoTurno, giorno)
            if (nuovoOppureEsistente) {
                if (tipoTurno.multiplo) {
                    nuovoOppureEsistente = Lib.creaTurno(croce, tipoTurno, giorno)
                    flash.message = logoService.setWarn(Evento.turnoCreandoExtra, nuovoOppureEsistente)
                    nuovoTurno = true
                } else {
                    flash.message = message(code: 'turno.new.esiste.message', args: [tipoTurno.descrizione, giornoTxt])
                }// fine del blocco if-else
            } else {
                nuovoOppureEsistente = Lib.creaTurno(croce, tipoTurno, giorno)
                if (nuovoOppureEsistente.tipoTurno.sigla.equals(Cost.EXTRA)) {
                    flash.message = logoService.setInfo(Evento.turnoCreandoExtra, (Turno) nuovoOppureEsistente)
                } else {
                    flash.message = logoService.setInfo(Evento.turnoCreando, (Turno) nuovoOppureEsistente)
                }// fine del blocco if-else
                nuovoTurno = true
            }// fine del blocco if-else
        } else {
            flash.message = message(code: 'turno.new.fallito.message', args: [tipoTurno.descrizione, giornoTxt])
        }// fine del blocco if-else

        newFillTurno(nuovoOppureEsistente, nuovoTurno)
    }// fine della closure

    @Secured([Cost.ROLE_MILITE])
    def fillTurno = {
        String turnoIdTxt
        long turnoId
        Turno turnoInstance = null

        def a = request
        def b = request.contextPath
        def c = request.pathInfo
        def d = request.queryString
        def e = request.remoteUser
        def f = request.requestURI
        def g = request.userPrincipal
        def user = springSecurityService.principal
        def currUser = springSecurityService.getCurrentUser()

        if (params.turnoId) {
            turnoIdTxt = params.turnoId
            turnoId = Long.decode(turnoIdTxt)
            turnoInstance = Turno.findById(turnoId)
        }// fine del blocco if

        //--log
        Milite milite = militeService.getMiliteLoggato()

        params.nuovoTurno = false
        params.turno = turnoInstance
        //      redirect(action: 'newFillTurno', params: params)
        newFillTurno(turnoInstance, false)
    }// fine della closure

    @Secured([Cost.ROLE_MILITE])
    def newFillTurno(Turno turnoInstance, boolean nuovoTurno) {
        params.siglaCroce = croceService.getSiglaCroce(request)
        boolean turniSecured = croceService.isTurniSecured(params.siglaCroce)

        render(view: 'fillTurno', model: [
                turnoInstance: turnoInstance,
                nuovoTurno: nuovoTurno,
                turniSecured: turniSecured
        ], params: params)
    }// fine del metodo

    def uscitaSenzaModifiche = {
        Turno turnoInstance = null
        boolean nuovoTurno = false
        String value
        String testo

        def a = request
        def b = request.contextPath
        def c = request.pathInfo
        def d = request.queryString
        def e = request.remoteUser
        def f = request.requestURI
        def g = request.userPrincipal

        if (params.nuovoTurno) {
            value = params.nuovoTurno
            if (value.equals('true')) {
                nuovoTurno = true
            }// fine del blocco if
        }// fine del blocco if

        if (params.id) {
            turnoInstance = Turno.get(params.id)
        }// fine del blocco if

        if (nuovoTurno) {
            if (turnoInstance) {
                flash.message = logoService.setWarn(Evento.turnoAnnullatoNuovo, turnoInstance)
                cancella(turnoInstance)
            }// fine del blocco if
        } else {
            testo = logoService.setWarn(Evento.turnoNonModificato, turnoInstance)
            testo += ' di ' + turnoInstance.tipoTurno.descrizione
            testo += ' per il giorno ' + Lib.presentaDataCompleta(turnoInstance.giorno)
            flash.message = testo
        }// fine del blocco if-else

        redirect(action: 'tabCorrente')
    }// fine della closure

    @Secured([Cost.ROLE_ADMIN])
    def dettaglioTurno = {
        Turno turnoInstance = null
        if (params.id) {
            turnoInstance = Turno.get(params.id)
        }// fine del blocco if

        flash.message = 'Visione diretta del turno (solo per admin)'
        redirect(action: 'show', id: turnoInstance.id)
    }// fine della closure


    def list(Integer max) {
        def lista
        Croce croce = croceService.getCroce(request)
        def campiLista = [
                'tipoTurno',
                'giorno',
                'inizio',
                'fine',
                'localitàExtra',
                'note',
                'assegnato']

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
                lista = Turno.findAll(params)
                lista = Turno.findAll("from Turno order by croce_id,giorno")
                campiLista = ['id', 'croce'] + campiLista
            } else {
                if (!params.sort) {
                    params.sort = 'giorno'
                }// fine del blocco if-else
                lista = Turno.findAllByCroce(croce, params)
            }// fine del blocco if-else
        } else {
            lista = Turno.findAll(params)
        }// fine del blocco if-else

        render(view: 'list', model: [turnoInstanceList: lista, turnoInstanceTotal: 0, campiLista: campiLista], params: params)
    } // fine del metodo

    @Secured([Cost.ROLE_ADMIN])
    def create() {
        params.siglaCroce = croceService.getSiglaCroce(request)

        render(view: 'create', model: [turnoInstance: new Turno(params)], params: params)
    } // fine del metodo

    @Secured([Cost.ROLE_ADMIN])
    def save() {
        Croce croce = croceService.getCroce(request)
        def turnoInstance = new Turno(params)

        if (croce) {
            params.siglaCroce = croce.sigla
            if (!turnoInstance.croce) {
                turnoInstance.croce = croce
            }// fine del blocco if
        }// fine del blocco if

        if (!turnoInstance.save(flush: true)) {
            render(view: 'create', model: [turnoInstance: turnoInstance], params: params)
            return
        }// fine del blocco if

        flash.message = message(code: 'default.created.message', args: [message(code: 'turno.label', default: 'Turno'), turnoInstance.id])
        redirect(action: 'show', id: turnoInstance.id)
    } // fine del metodo

    def show(Long id) {
        def turnoInstance = Turno.get(id)

        if (!turnoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'turno.label', default: 'Turno'), id])
            redirect(action: 'list')
            return
        }// fine del blocco if

        params.siglaCroce = croceService.getSiglaCroce(request)
        render(view: 'show', model: [turnoInstance: turnoInstance], params: params)
    } // fine del metodo

    def edit(Long id) {
        def turnoInstance = Turno.get(id)

        if (!turnoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'turno.label', default: 'Turno'), id])
            redirect(action: 'list')
            return
        }// fine del blocco if

        render(view: 'edit', model: [turnoInstance: turnoInstance], params: params)
    } // fine del metodo

    @Secured([Cost.ROLE_MILITE])
    def updateSecured(Long id, Long version) {
        update(id, version)
    } // update del metodo

    def update(Long id, Long version) {
        boolean nuovoTurno = false
        String value

        if (params.nuovoTurno) {
            value = params.nuovoTurno
            if (value.equals('true')) {
                nuovoTurno = true
            }// fine del blocco if
        }// fine del blocco if

        def turnoInstance = Turno.get(id)
        if (!turnoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'turno.label', default: 'Turno'), id])
            redirect(action: 'list')
            return
        }// fine del blocco if

        params.siglaCroce = croceService.getSiglaCroce(request)
        if (version != null) {
            if (turnoInstance.version > version) {
                turnoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'turno.label', default: 'Turno')] as Object[],
                        "Another user has updated this Turno while you were editing")
                render(view: 'edit', model: [turnoInstance: turnoInstance], params: params)
                return
            }// fine del blocco if
        }// fine del blocco if

        def oldParams = Lib.cloneMappa(turnoInstance.properties)
        turnoInstance.properties = params

        flash.message = ''
        flash.errors = null
        flash.listaErrori = null

        if (isEsistonoErrori(turnoInstance)) {
            turnoInstance.properties = oldParams
            render(view: 'fillTurno', model: [turnoInstance: turnoInstance, nuovoTurno: nuovoTurno], params: params)
            return
        }// fine del blocco if

        //--assegnazioni dei militi alle funzioni
        regolaFunzioniTurno(turnoInstance)

        //--ore impegnate per ogni milite
        //--inserisce la durata del turno se manca il valore
        //--azzera il valore se manca il milite
        regolaOreTurno(turnoInstance)

        //--non è affatto chiaro perché la PATCH funzioni!
        //--di solito c'è un giro solo, ma andava in errore per valori NULL del campo oreMilite1/2/3/4
        if (!turnoInstance.save(flush: true)) {
            if (!turnoInstance.save(flush: true)) {
                render(view: 'edit', model: [turnoInstance: turnoInstance], params: params)
                return
            }// fine del blocco if
        }// fine del blocco if

        this.controllaAnomalie(turnoInstance, nuovoTurno)

        redirect(action: 'tabCorrente')
    } // fine del metodo

    @Secured([Cost.ROLE_MILITE])
    def delete(Long id) {
        def turnoInstance = Turno.get(id)
        TipoTurno tipoTurno = turnoInstance.tipoTurno
        Date giorno = turnoInstance.giorno
        def listaLog
        Logo logo

        if (!turnoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'turno.label', default: 'Turno'), id])
            redirect(action: 'list')
            return
        }// fine del blocco if

        cancella(turnoInstance)
        flash.message = message(code: 'turno.deleted.message', args: descGiorno(turnoInstance))
        logoService.setWarn(Evento.turnoEliminato, tipoTurno, giorno)
        redirect(action: 'tabCorrente')
    } // fine del metodo

    def cancella(Turno turno) {
        def listaLog
        Logo logo

        try {
            listaLog = Logo.findAllByTurno(turno)
            listaLog?.each {
                logo = (Logo) it
                logo.turno = null
                logo.save(flush: true)
            } // fine del ciclo each

            turno.delete(flush: true)
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'turno.not.deleted.message')
        }
    } // fine del metodo

    //--controlla che tutte le funzioni obbligatorie siano state assegnate
    //--pone a true il flag ''assegnato'' del turno
    //--controlla che non ci sia il flag di avviso e/o la durata del singolo milite sia maggiore del turno
    private void regolaFunzioniTurno(Turno turno) {
        TipoTurno tipoTurno
        boolean assegnato = false
        Funzione funzione
        ArrayList listaFunzioni = null
        boolean assegnatoSingolo
        ArrayList<Boolean> listaAssegnate
        int obbligatorie = turno.tipoTurno.funzioniObbligatorie

        if (turno) {
            listaFunzioni = turno.getListaFunzioni()
        }// fine del blocco if

        if (listaFunzioni) {
            listaAssegnate = new ArrayList<Boolean>()
            for (int k = 1; k <= listaFunzioni.size(); k++) {
                listaAssegnate.add(regolaFunzione(turno, k))
            } // fine del ciclo for
            assegnato = true
            for (int k = 0; k < obbligatorie; k++) {
                if (!listaAssegnate[k]) {
                    assegnato = false
                }// fine del blocco if
            } // fine del ciclo for
        }// fine del blocco if

        turno.assegnato = assegnato
    } // fine del metodo

    //--controlla che non ci sia il flag di avviso e/o la durata del singolo milite sia maggiore del turno
    private boolean regolaFunzione(Turno turno, int pos) {
        boolean assegnata = false
        Milite milite = null
        String militeIdTxt
        long militeId = 0
        String milFunz = 'militeFunzione' + pos
        String milFunzId = 'militeFunzione' + pos + '_id'
        String probFunz = 'problemiFunzione' + pos
        String oreMilFunz = 'oreMilite' + pos
        int oreMilite = 0
        int durataTurno = 1000 //un numero grande a piacere :-)

        if (turno && pos > 0) {
            if (params."${milFunzId}") {
                militeIdTxt = params."${milFunzId}"
                try { // prova ad eseguire il codice
                    militeId = Long.decode(militeIdTxt)
                } catch (Exception unErrore) { // intercetta l'errore
                    def stop
                }// fine del blocco try-catch
                if (militeId > 0) {
                    milite = Milite.get(militeId)
                }// fine del blocco if
                if (milite) {
                    turno."${milFunz}" = milite
                }// fine del blocco if

                if (milite) {
                    if (params."${probFunz}") {
                        durataTurno = Lib.getDurataOre(turno.inizio, turno.fine)
                        if (params."${oreMilFunz}") {
                            oreMilite = Integer.decode(params."${oreMilFunz}")
                        }// fine del blocco if

                        if (oreMilite >= durataTurno) {
                            assegnata = true
                        }// fine del blocco if
                    } else {
                        assegnata = true
                    }// fine del blocco if-else
                }// fine del blocco if
            } else {
                turno."${milFunz}" = null
            }// fine del blocco if-else
        }// fine del blocco if

        return assegnata
    } // fine del metodo

    //--ore impegnate per ogni milite
    //--inserisce la durata del turno se manca il valore
    //--azzera il valore se manca il milite
    private static void regolaOreTurno(Turno turno) {
        TipoTurno tipoTurno
        ArrayList listaFunzioni = null

        if (turno) {
            tipoTurno = turno.tipoTurno
        }// fine del blocco if

        if (tipoTurno) {
            listaFunzioni = tipoTurno.getListaFunzioni()
        }// fine del blocco if

        if (listaFunzioni) {
            for (int k = 0; k < listaFunzioni.size(); k++) {
                regolaOra(turno, k + 1)
            } // fine del ciclo for
        }// fine del blocco if
    } // fine del metodo

    //--inserisce la durata del turno se manca il valore
    //--azzera il valore se manca il milite
    private static void regolaOra(Turno turno, int pos) {
        String milFunz = 'militeFunzione' + pos
        String ora = 'oreMilite' + pos
        boolean militeEsiste = false
        boolean valoreCampoVuoto = true
        TipoTurno tipoTurno = turno.tipoTurno
        int valoreTipoTurno = 0

        if (tipoTurno) {
            valoreTipoTurno = tipoTurno.durata
        }// fine del blocco if

        if (turno."${milFunz}") {
            militeEsiste = true
        }// fine del blocco if

        if (turno."${ora}") {
            valoreCampoVuoto = false
        }// fine del blocco if

        if (!militeEsiste) {
            turno."${ora}" = 0
        } else {
            if (valoreCampoVuoto) {
                turno."${ora}" = valoreTipoTurno
            }// fine del blocco if
        }// fine del blocco if-else

    } // fine del metodo

    private boolean isEsistonoErrori(Turno turno) {
        boolean isEsistonoErrori = false
        ArrayList listaErrori = this.esistonoErrori(turno, params)

        if (listaErrori && listaErrori.size() > 0) {
            isEsistonoErrori = true
            logoService.setWarn(Evento.turnoModificato, turno)
            flash.message = ''
            if (listaErrori.size() == 1) {
                flash.errors = listaErrori[0]
            } else {
                flash.listaErrori = listaErrori
            }// fine del blocco if-else
        } else {
            flash.message = message(code: 'turno.not.modified.message', args: descGiorno(turno))
        }// fine del blocco if-else

        return isEsistonoErrori
    } // fine del metodo

    private void controllaAnomalie(Turno turno, boolean nuovoTurno) {
        def listaErrori = esistonoAnomalie(turno)
        String testo

        if (listaErrori && listaErrori.size() > 0) {
            logoService.setWarn(Evento.turnoModificato, turno)
            flash.listaErrori = listaErrori
            flash.message = ''
        } else {
            if (nuovoTurno) {
                testo = logoService.setWarn(Evento.turnoCreato, turno)
            } else {
                testo = logoService.setWarn(Evento.turnoModificato, turno)
            }// fine del blocco if-else
            testo += ' di ' + turno.tipoTurno.descrizione
            testo += ' per il giorno ' + Lib.presentaDataCompleta(turno.giorno)
            flash.message = testo
        }// fine del blocco if-else
    } // fine del metodo

    //--controlla che i dati siano ''accettabili''
    private ArrayList esistonoErrori(Turno turno, mappa) {
        ArrayList listaErrori = new ArrayList()
        String testoErrore = ''
        int numMaxFunz = 4
        String campo
        ArrayList listaTmp = new ArrayList()
        HashMap mapTmp = new HashMap()
        boolean isAdmin = militeService.isLoggatoAdminOrMore()
        ControlloTemporale controlloModifica = croceService.getControlloModifica(request)

        //--le funzioni hardcoded sono al massimo 4
        for (int k = 1; k <= numMaxFunz; k++) {
            campo = 'militeFunzione' + k + '_id'
            if (mappa."${campo}") {
                listaTmp.add(mappa."${campo}")
                mapTmp.put(mappa."${campo}", null)
            }// fine del blocco if
        } // fine del ciclo for

        //--nomi doppi
        if (listaTmp.size() != mapTmp.size()) {
            testoErrore = 'Non puoi segnare un milite due volte nello stesso turno'
            listaErrori.add(testoErrore)
        }// fine del blocco if

        //--controllo che non sia un admin
        //--controllo che ci sia il flag dei settings
        //--controllo del tempo trascorso
        //--controllo del blocco settimanale
        if (!isAdmin) {
            switch (controlloModifica) {
                case ControlloTemporale.tempoTrascorso:
                    testoErrore = getErroreTempoTrascorso(turno, 4, mappa)
                    break
                case 2:
                case ControlloTemporale.tempoMancante:

                    break
                case 3:
                case ControlloTemporale.bloccoSettimanale:
                    testoErrore = getErroreBloccoSettimanale(turno, 4, mappa)

                    break
                default: // caso non definito
                    break
            } // fine del blocco switch
            if (testoErrore) {
                listaErrori.add(testoErrore)
            }// fine del blocco if
        }// fine del blocco if

        //numero massimo di ore inseribili
        if (croceService.fissaLimiteMassimoSingoloTurno(request)) {
            int ore1 = turno.oreMilite1
            int ore2 = turno.oreMilite2
            int ore3 = turno.oreMilite3
            int ore4 = turno.oreMilite4
            int max = croceService.oreMassimeSingoloTurno(request)
            if (ore1 > max || ore2 > max || ore3 > max || ore4 > max) {
                testoErrore = 'Non puoi inserire nel turno un numero di ore così elevato'
                listaErrori.add(testoErrore)
            }// fine del blocco if
        }// fine del blocco if

        return listaErrori
    } // fine del metodo

    private String getErroreTempoTrascorso(Turno turno, int numMaxFunz, mappa) {
        String testoErrore = ''
        long actualTime
        long oldTime
        long milliSecondiTrascorsi
        long secondiTrascorsi
        long minutiTrascorsi
        String oldMiliteIdTxt
        String militeIdTxt
        String campo
        String milFunz
        String modFunz
        Milite milite
        boolean modificatoMilite
        Timestamp tempo
        int maxMinutiTrascorsiModifica = croceService.maxMinutiTrascorsiModifica(request)
        boolean tempoScaduto = false

        actualTime = new Date().time
        for (int k = 1; k <= numMaxFunz; k++) {
            oldMiliteIdTxt = ''
            militeIdTxt = ''
            oldTime = actualTime
            campo = 'militeFunzione' + k + '_id'
            milFunz = 'militeFunzione' + k
            modFunz = 'modificaFunzione' + k
            milite = turno."${milFunz}"
            if (milite) {
                oldMiliteIdTxt = milite.id.toString()
            }// fine del blocco if
            if (mappa."${campo}") {
                militeIdTxt = mappa."${campo}"
            }// fine del blocco if

            modificatoMilite = (!militeIdTxt.equals(oldMiliteIdTxt))

            if (modificatoMilite) {
                tempo = turno."${modFunz}"
                if (tempo) {
                    oldTime = tempo.time
                }// fine del blocco if
                milliSecondiTrascorsi = actualTime - oldTime
                secondiTrascorsi = milliSecondiTrascorsi / 1000
                minutiTrascorsi = secondiTrascorsi / 60
                if (minutiTrascorsi > maxMinutiTrascorsiModifica) {
                    tempoScaduto = true
                }// fine del blocco if
            }// fine del blocco if
        } // fine del ciclo for

        if (tempoScaduto) {
            testoErrore = 'Non puoi modificare il turno dopo che sono passati più di ' + maxMinutiTrascorsiModifica + ' minuti da quando ti sei segnato'
        }// fine del blocco if

        return testoErrore
    } // fine del metodo

    private static String getErroreBloccoSettimanale(Turno turno, int numMaxFunz, mappa) {
        String testoErrore = ''
        long actualTime
        long oldTime
        String oldMiliteIdTxt
        String militeIdTxt
        String campo
        String milFunz
        String modFunz
        Milite milite
        boolean modificatoMilite
        int settimanaCorrente
        int settimanaTurno
        boolean iniziataSettimanaCorrente = false

        for (int k = 1; k <= numMaxFunz; k++) {
            modificatoMilite = false
            oldMiliteIdTxt = ''
            militeIdTxt = ''
            campo = 'militeFunzione' + k + '_id'
            milFunz = 'militeFunzione' + k
            milite = turno."${milFunz}"
            if (milite) {
                oldMiliteIdTxt = milite.id.toString()
            }// fine del blocco if
            if (mappa."${campo}") {
                militeIdTxt = mappa."${campo}"
            }// fine del blocco if

            if (milite && milite.id) {
                modificatoMilite = (!militeIdTxt.equals(oldMiliteIdTxt))
            }// fine del blocco if

            if (modificatoMilite) {
                settimanaCorrente = Lib.getNumSettimanaOdierna()
                settimanaTurno = Lib.getNumSettimanaTurno(turno)
                if (settimanaCorrente >= settimanaTurno) {
                    iniziataSettimanaCorrente = true
                }// fine del blocco if
            }// fine del blocco if
        } // fine del ciclo for

        if (iniziataSettimanaCorrente) {
            testoErrore = 'Non puoi modificare il turno della settimana corrente'
        }// fine del blocco if

        return testoErrore
    } // fine del metodo

    //--controlla che i dati siano ''congruenti''
    private static ArrayList esistonoAnomalie(Turno turno) {
        ArrayList listaErrori = new ArrayList()
        String testoErrore
        Date giorno
        Date inizio
        Date fine
        int numGiorno
        int numInizio
        int numFine

        //--prepara i dati
        if (turno) {
            giorno = turno.giorno
            inizio = turno.inizio
            fine = turno.fine
        }// fine del blocco if

        //--prepara i dati
        if (giorno && inizio && fine) {
            numGiorno = Lib.getNumGiorno(giorno)
            numInizio = Lib.getNumGiorno(inizio)
            numFine = Lib.getNumGiorno(fine)
        }// fine del blocco if

        //--inizio deve essere nello stesso giorno
        if (numGiorno && numInizio && numFine) {
            if (numInizio != numGiorno) {
                testoErrore = "Il turno "
                testoErrore += turno.tipoTurno.descrizione
                testoErrore += " di "
                testoErrore += Lib.presentaDataCompleta(giorno)
                testoErrore += " è stato modificato, ma la data di inizio turno non coincide col giorno"
                listaErrori.add(testoErrore)
            }// fine del blocco if
        }// fine del blocco if

        //--fine deve essere nello stesso giorno o al massimo il giorno successivo
        if (numGiorno && numInizio && numFine) {
            if ((numFine < numGiorno) || (numFine > numGiorno + 1)) {
                testoErrore = "Il turno "
                testoErrore += turno.tipoTurno.descrizione
                testoErrore += " di "
                testoErrore += Lib.presentaDataCompleta(giorno)
                testoErrore += " è stato modificato, ma la data di fine turno non coincide col giorno"
                listaErrori.add(testoErrore)
            }// fine del blocco if
        }// fine del blocco if

        return listaErrori
    } // fine del metodo


    private static descGiorno(Turno turno) {
        TipoTurno tipoTurno
        Date giorno
        String descTipo = ''
        String giornoTxt = ''

        if (turno) {
            tipoTurno = turno.tipoTurno
            giorno = turno.giorno
        }// fine del blocco if

        if (tipoTurno) {
            descTipo = tipoTurno.descrizione
        }// fine del blocco if

        if (giorno) {
            giornoTxt = Lib.presentaDataCompleta(giorno)
        }// fine del blocco if

        if (descTipo && giornoTxt) {
            return [descTipo, giornoTxt]
        }// fine del blocco if
    } // fine del metodo

} // fine della controller classe
