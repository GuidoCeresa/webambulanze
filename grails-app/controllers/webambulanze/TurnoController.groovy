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

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def springSecurityService

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def logoService

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def militeService

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def croceService

    static Date dataInizio
    static Date dataFine

    static int giorniVisibili = 7
    static int delta = giorniVisibili - 1
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    @Secured([Cost.ROLE_MILITE])
    def tabellone = {
        flash.message = ''
        dataInizio = AmbulanzaTagLib.creaDataOggi()
        dataFine = (dataInizio + delta).toTimestamp()
        render(view: 'tabellone', model: [dataInizio: dataInizio, dataFine: dataFine])
    }

    @Secured([Cost.ROLE_MILITE])
    def tabCorrente = {
        //      flash.message = ''
        logoService.setInfo(Evento.tabellone)
        render(view: 'tabellone', model: [dataInizio: dataInizio, dataFine: dataFine])
    }

    @Secured([Cost.ROLE_MILITE])
    def tabellonePrima = {
        flash.message = ''
        dataInizio -= giorniVisibili
        dataFine -= giorniVisibili
        render(view: 'tabellone', model: [dataInizio: dataInizio, dataFine: dataFine])
    }

    @Secured([Cost.ROLE_MILITE])
    def tabelloneOggi = {
        flash.message = ''
        dataInizio = AmbulanzaTagLib.creaDataOggi()
        dataFine = (dataInizio + delta).toTimestamp()
        render(view: 'tabellone', model: [dataInizio: dataInizio, dataFine: dataFine])
    }

    @Secured([Cost.ROLE_MILITE])
    def tabelloneLunedi = {
        flash.message = ''
        dataInizio = AmbulanzaTagLib.creaDataLunedi()
        dataFine = (dataInizio + delta).toTimestamp()
        render(view: 'tabellone', model: [dataInizio: dataInizio, dataFine: dataFine])
    }

    @Secured([Cost.ROLE_MILITE])
    def tabelloneDopo = {
        flash.message = ''
        dataInizio += giorniVisibili
        dataFine += giorniVisibili
        render(view: 'tabellone', model: [dataInizio: dataInizio, dataFine: dataFine])
    }

    @Secured([Cost.ROLE_MILITE])
    def newTurno = {
        String tipoTurnoTxt
        boolean nuovoTurno = false
        String giornoNum
        Turno nuovoOppureEsistente = null
        TipoTurno tipoTurno = null
        Croce croce = grailsApplication.mainContext.servletContext.croce
        Date giorno = Lib.creaData1Gennaio()
        int offSet
        String giornoTxt = ''

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

        if (croce && giorno && tipoTurno) {
            nuovoOppureEsistente = Turno.findByCroceAndTipoTurnoAndGiorno(croce, tipoTurno, giorno)
            if (nuovoOppureEsistente) {
                if (nuovoOppureEsistente.tipoTurno.sigla.equals(Cost.EXTRA)) {
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
    } // fine della closure

    @Secured([Cost.ROLE_MILITE])
    def fillTurno = {
        String turnoIdTxt
        long turnoId
        Turno turnoInstance = null

        if (params.turnoId) {
            turnoIdTxt = params.turnoId
            turnoId = Long.decode(turnoIdTxt)
            turnoInstance = Turno.findById(turnoId)
        }// fine del blocco if

        params.nuovoTurno = false
        params.turno = turnoInstance
        //      redirect(action: 'newFillTurno', params: params)
        newFillTurno(turnoInstance, false)
    } // fine della closure

    @Secured([Cost.ROLE_MILITE])
    def newFillTurno(Turno turnoInstance, boolean nuovoTurno) {
//        boolean nuovoTurno = false
//        Turno turnoInstance = null

//        if (params.nuovoTurno) {
//            nuovoTurno = params.nuovoTurno
//        }// fine del blocco if
//        if (params.turno) {
//            turnoInstance = params.turno
//        }// fine del blocco if

        render(view: 'fillTurno', model: [turnoInstance: turnoInstance, nuovoTurno: nuovoTurno])
    }

    def uscitaSenzaModifiche = {
        Turno turnoInstance = null
        boolean nuovoTurno = false
        String value
        String testo

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
    }

    @Secured([Cost.ROLE_ADMIN])
    def dettaglioTurno = {
        Turno turnoInstance = null
        if (params.id) {
            turnoInstance = Turno.get(params.id)
        }// fine del blocco if

        flash.message = 'Visione diretta del turno (solo per admin)'
        redirect(action: 'show', id: turnoInstance.id)
    }

    def index() {
        redirect(action: 'tabellone', params: params)
    } // fine del metodo

    def list(Integer max) {
        def lista
        Croce croce
        String sigla
        def campiLista = [
                'tipoTurno',
                'giorno',
                'inizio',
                'fine',
                'localitàExtra',
                'note',
                'assegnato']

        if (!params.sort) {
            params.sort = 'giorno'
        }// fine del blocco if-else
        if (params.order) {
            if (params.order == 'asc') {
                params.order = 'desc'
            } else {
                params.order = 'asc'
            }// fine del blocco if-else
        } else {
            params.order = 'asc'
        }// fine del blocco if-else

        if (grailsApplication.mainContext.servletContext.croce) {
            croce = grailsApplication.mainContext.servletContext.croce
            sigla = croce.sigla
            if (sigla.equals(Cost.CROCE_ALGOS)) {
                lista = Turno.findAll(params)
                campiLista = ['id', 'croce'] + campiLista
            } else {
                lista = Turno.findAllByCroce(croce, params)
            }// fine del blocco if-else
        } else {
            lista = Turno.findAll(params)
        }// fine del blocco if-else

        [turnoInstanceList: lista, turnoInstanceTotal: 0, campiLista: campiLista]
    }

    @Secured([Cost.ROLE_ADMIN])
    def create() {
        [turnoInstance: new Turno(params)]
    } // fine del metodo

    @Secured([Cost.ROLE_ADMIN])
    def save() {
        def turnoInstance = new Turno(params)
        if (!turnoInstance.save(flush: true)) {
            render(view: "create", model: [turnoInstance: turnoInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'turno.label', default: 'Turno'), turnoInstance.id])
        redirect(action: 'show', id: turnoInstance.id)
    } // fine del metodo

    def show(Long id) {
        def turnoInstance = Turno.get(id)
        if (!turnoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'turno.label', default: 'Turno'), id])
            redirect(action: "list")
            return
        }

        [turnoInstance: turnoInstance]
    } // fine del metodo

    def edit(Long id) {
        def turnoInstance = Turno.get(id)
        if (!turnoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'turno.label', default: 'Turno'), id])
            redirect(action: "list")
            return
        }

        [turnoInstance: turnoInstance]
    } // fine del metodo

    @Secured([Cost.ROLE_MILITE])
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
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (turnoInstance.version > version) {
                turnoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                        [message(code: 'turno.label', default: 'Turno')] as Object[],
                        "Another user has updated this Turno while you were editing")
                render(view: "edit", model: [turnoInstance: turnoInstance])
                return
            }
        }
        turnoInstance.properties = params

        if (controllaErrori(turnoInstance)) {
            render(view: 'tabellone', model: [dataInizio: dataInizio, dataFine: dataFine])
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
                render(view: "edit", model: [turnoInstance: turnoInstance])
                return
            }
        }
        this.controllaAnomalie(turnoInstance, nuovoTurno)

        redirect(action: 'tabCorrente')
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

    private boolean controllaErrori(Turno turno) {
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
        String testoErrore
        int numMaxFunz = 4
        String campo
        ArrayList listaTmp = new ArrayList()
        HashMap mapTmp = new HashMap()
        boolean isAdmin = militeService.isLoggatoAdminOrMore()
        boolean isControlloModificaTempoTrascorso = croceService.isControlloModificaTempoTrascorso()
        int maxMinutiTrascorsiModifica = croceService.getMaxMinutiTrascorsiModifica()
        long oldTime
        long actualTime
        int minutiTrascorsi
        boolean modificatoMilite
        boolean tempoScaduto = false
        String milFunz
        String modFunz
        Milite milite
        String oldMiliteIdTxt
        String militeIdTxt = ''
        Timestamp tempo

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

        //--controllo che non sia  un admin
        //--controllo che ci sia il flag dei settings
        //--controllo del tempo trascorso
        if (!isAdmin) {
            if (isControlloModificaTempoTrascorso) {
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
                        minutiTrascorsi = actualTime - oldTime
                        minutiTrascorsi = minutiTrascorsi / 1000
                        minutiTrascorsi = minutiTrascorsi / 60
                        if (minutiTrascorsi > maxMinutiTrascorsiModifica) {
                            tempoScaduto = true
                        }// fine del blocco if
                    }// fine del blocco if
                } // fine del ciclo for

                if (tempoScaduto) {
                    testoErrore = 'Non puoi più modificare il turno, dopo 30 minuti'
                    listaErrori.add(testoErrore)
                }// fine del blocco if
            }// fine del blocco if
        }// fine del blocco if

        return listaErrori
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

    def delete(Long id) {
        def turnoInstance = Turno.get(id)
        TipoTurno tipoTurno = turnoInstance.tipoTurno
        Date giorno = turnoInstance.giorno
        String tipo = ''
        String giornoTxt = ''
        def listaLog
        Logo logo

        if (!turnoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'turno.label', default: 'Turno'), id])
            redirect(action: "list")
            return
        }
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
