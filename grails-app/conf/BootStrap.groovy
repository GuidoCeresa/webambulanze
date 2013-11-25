import webambulanze.*

/* Created by Algos s.r.l. */
/* Date: mag 2012 */
/* Questo file è stato installato dal plugin AlgosBase */
/* Tipicamente NON verrà più sovrascritto dalle successive release del plugin */
/* in quanto POTREBBE essere personalizzato in questa applicazione */
/* Se vuoi che le prossime release del plugin sovrascrivano questo file, */
/* perdendo tutte le modifiche effettuate qui, */
/* regola a true il flag di controllo flagOverwrite© */
/* flagOverwrite = false */

class BootStrap implements Cost {

    //--cancella tutto il database
    static boolean SONO_PROPRIO_SICURO_DI_CANCELLARE_TUTTO = false

    //--controllo di funzioni da utilizzare SOLAMENTE in fase di sviluppo
    private static boolean SVILUPPO_CROCE_ROSSA_FIDENZA = false;
    private static boolean SVILUPPO_CROCE_ROSSA_PONTE_TARO = false;
    private static boolean SVILUPPO_PUBBLICA_PIANORO = true;

    //--controllo di funzioni da utilizzare SOLAMENTE in fase di sviluppo
    private static boolean ESISTE_COLLEGAMENTO_INTERNET = true;

    //--usato per controllare la creazione automatica delle password
    private static int numUtentiRossaFidenza = 0
    private static int numUtentiRossaPonteTaro = 0
    private static int numUtentiPubblicaPianoro = 0

    //--variabili temporaneee per passare i riferimenti da una tavola all'altra
    private static ArrayList<Funzione> funzDemo = []
    private static ArrayList<Funzione> funzOrdinarioPAVT = []
    private static ArrayList<Funzione> funz118PAVT = []
    private static ArrayList<Funzione> funzAutomedicaCRF = []
    private static ArrayList<Funzione> funzAmbulanzaCRF = []
    private static ArrayList<Funzione> funzCRPT = []
    private static ArrayList<Funzione> funzPAP = []

    // private static String DIR_PATH = '/Users/Gac/Documents/IdeaProjects/webambulanze/grails-app/webambulanze/'
    private static String DIR_PATH = 'http://77.43.32.198:80/ambulanze/'

    def grailsApplication
    def sessionFactory

    //--metodo invocato direttamente da Grails
    //--tutte le aggiunte, modifiche, patch e nuove croci vengono inserite con una versione
    //--l'ordine di inserimento è FONDAMENTALE
    def init = { servletContext ->

        //--creazione della prima versione
        //--esegue solo se NON esiste già una versione col numero indicato
        if (installaVersione(1)) {
            tavolaVersioni()
        }// fine del blocco if

        //--modifica di un turno in CRF
        //--esegue solo se NON esiste già una versione col numero indicato
        //--esegue la modifica SOLO per i turni NON effettuati
        if (installaVersione(2)) {
            modificaTurnoFidenza()
        }// fine del blocco if

        //--aggiunta campo (visibile) nickname alla tavola Utente
        if (installaVersione(3)) {
            nickUtenteRossaFidenza()
        }// fine del blocco if

        //--elimina alcuni accessi e regola il nick
        if (installaVersione(4)) {
            fixSecurityAlgos()
        }// fine del blocco if

        //--croce rossa pontetaro
        if (installaVersione(5)) {
            resetCroce(CROCE_ROSSA_PONTETARO)
            croceRossaPontetaro()
        }// fine del blocco if

        //--aggiunge un flag a tutti i tipi di turno esistenti
        //--il flag serve per separare visivamente i vari turni all'interno del tabellone
        if (installaVersione(6)) {
            fixUltimoTipoTurno()
        }// fine del blocco if

        //--modifica di un turno in CRF
        //--esegue solo se NON esiste già una versione col numero indicato
        //--completa la modifica ANCHE per i turni effettuati
        if (installaVersione(7)) {
            modificaTurnoFidenzaEffettuati()
        }// fine del blocco if

        //--modifica dei tipi di turno in CRPT
        //--esegue solo se NON esiste già una versione col numero indicato
        //--modifica il numero di funzioniObbligatorie per i turni di dialisi
        //--aggiunge un nuovo tipo di turno ''Ordinario'' e modifica in parte quello esistente
        //--aggiunge un tipo di turno ''TurnoExtra'' per spezzare i turni di ambulanza
        if (installaVersione(8)) {
            modificaTurniPontetaro()
        }// fine del blocco if

        //--elimina tutti gli utenti programmatori eccetto uno
        //--ce ne dovrebbero essere 3. Uno lo mantiene (il primo) e cancella gli altri due
        if (installaVersione(9)) {
            fixProgrammatori()
        }// fine del blocco if

        //--patch ai tipi di turno in CRPT
        if (installaVersione(10)) {
            fixTurniPontetaro()
        }// fine del blocco if

        //--ulteriore patch ai tipi di turno in CRPT
        if (installaVersione(11)) {
            fixTurniPontetaroUlteriore()
        }// fine del blocco if

        //--spostamento in alto (dopo i 3 turni di ambulanza) del turno extra in CRPT
        if (installaVersione(12)) {
            fixExtraPontetaro()
        }// fine del blocco if

        //--aggiunge 3 funzioni per i servizi di sede a CRPT
        if (installaVersione(13)) {
            addFunzioniSedeCRPT()
        }// fine del blocco if

        //--aggiunge un tipo di turno a CRPT
        if (installaVersione(14)) {
            addServiziSedeCRPT()
        }// fine del blocco if

        //--flag ai militi dei servizi ufficio nella CRPT
        if (installaVersione(15)) {
            flagMilitiServiziCRPT()
        }// fine del blocco if

        //--aggiunge il controller iniziale che mancava
        if (installaVersione(16)) {
            fixControllerInizialePubblicaCastello()
        }// fine del blocco if

        //--regola il (nuovo) flag per tutte le croci
        if (installaVersione(17)) {
            flagModuloViaggi()
        }// fine del blocco if

        //--aggiunge (nuovo) flag per tutte le croci
        if (installaVersione(18)) {
            fixOrganizzazione()
        }// fine del blocco if

        //--fix descrizione croci dopo aggiunta organizzazione
        if (installaVersione(19)) {
            fixDescrizione()
        }// fine del blocco if

        //--fix nome presidente, custode ed amministratore
        if (installaVersione(20)) {
            fixCaricheFidenza()
        }// fine del blocco if

        //--fix nome presidente, custode ed amministratore
        if (installaVersione(21)) {
            fixCarichePonteTaro()
        }// fine del blocco if

        //--eliminato dalle liste popup del form del tabellone il milite NON attivo
        if (installaVersione(22)) {
            fixLevaMiliteNonAttivoDalleListePopup()
        }// fine del blocco if

        //--cancellazione del milite 'Don Alessandro' a Fidenza
        if (installaVersione(23)) {
            cancellaMiliteDonAlessandro()
        }// fine del blocco if

        //--creazione nuova authority per un custode a Fidenza
        if (installaVersione(24)) {
            fixPermessiFidenza()
        }// fine del blocco if

        //--ridisegnata lista moduli disponibili per i vari ruoli
        if (installaVersione(25)) {
            listaControllers()
        }// fine del blocco if

        //--aggiunto flag per disegnare bordo sopra i gruppi di turni
        if (installaVersione(26)) {
            fixFlagPrimoFidenza()
        }// fine del blocco if

        //--aggiunto flag per disegnare bordo sopra i gruppi di turni
        if (installaVersione(27)) {
            fixFlagPrimoPontetaro()
        }// fine del blocco if

        //--cancella un campo
        //--elimina il campo 'ultimo' della tavola 'TipoTurno'
        if (installaVersione(28)) {
            cancellaUltimo()
        }// fine del blocco if

        //--aggiunge altri militi alla croce 'demo'
        if (installaVersione(29)) {
            addMilitiDemo()
        }// fine del blocco if

        //--aggiunge 4 turni per la demo
        if (installaVersione(30)) {
            addTurniDemo()
        }// fine del blocco if

        //--Aggiunta una quarta funzione per i turni di ambulanza
        if (installaVersione(31)) {
            addFunzioneDemo()
        }// fine del blocco if

        //--Flag di barelliere a tutti i militi della demo
        if (installaVersione(32)) {
            addFlagBarelliereDemo()
        }// fine del blocco if

        //--Regolato a TRUE per Fidenza il nuovo flag di disabilitazione automatica turni
        if (installaVersione(33)) {
            fixFlagDisabilitazioneFidenza()
        }// fine del blocco if

        //--cancella un campo
        //--elimina il campo 'contakilometri' della tavola 'Viaggio'
        if (installaVersione(34)) {
            cancellaKilometro()
        }// fine del blocco if

        //--aggiunta di nuovi militi all'elenco militi per la Croce Rossa Ponte Taro
        if (installaVersione(35)) {
            militiRossaPonteTaroAggiuntivi()
        }// fine del blocco if

        //--controlloTemporale aggiunto per la Croce Rossa Ponte Taro
        if (installaVersione(36)) {
            rossaPonteTaroBloccoSettimanale()
        }// fine del blocco if

        //--modifica di un ruolo admin della croce rossa ponte taro
        if (installaVersione(37)) {
            fixRuoloCRPT()
        }// fine del blocco if

        //--corretto errore precedente
        if (installaVersione(38)) {
            fixErroreRuoloCRPT()
        }// fine del blocco if

        //--modifica di un ruolo admin della croce rossa fidenza
        if (installaVersione(39)) {
            fixRuoloCRF()
        }// fine del blocco if

        //--aggiunta dei viaggi alla demo
        //--modifica del flag
        if (installaVersione(40)) {
            addViaggiDemo()
        }// fine del blocco if

        //--aggiunta degli automezzi alla demo per operare sui viaggi
        if (installaVersione(41)) {
            addAutomezziDemo()
        }// fine del blocco if

        //--regolazione di due nuovi flag dei Settings per le Croci esistenti
        if (installaVersione(42)) {
            addFlagSetting()
        }// fine del blocco if

        //--regolazione di un ulteriore flag dei Settings per le Croci esistenti
        if (installaVersione(43)) {
            addTabelloneFlagSetting()
        }// fine del blocco if

        //--eliminazione utente .ospite. (non più necessario)
        if (installaVersione(44)) {
            deleteOspite()
        }// fine del blocco if

        //--creazione nuova croce
        if (installaVersione(45)) {
            creaPianoro()
        }// fine del blocco if

        //--creazione dei record utenti per la pubblica castello
//        if (installaVersione(99)) {
//            utentiPubblicacastello()
//        }// fine del blocco if

        // resetTurniPontetaro()

        //--cancella tutto il database
//        resetCompleto()

        iniezioneVariabili(servletContext)

        //--ruoli
        //securitySetupRuoli()

        //--croce interna
        //      croceAlgos()

        //--croce demo
        //croceDemo()

        //--croce tidone
        //  croceTidone()

        //--croce rossa fidenza
        //croceRossaFidenza()

        //--creazione del collegamento tra croce e settings
        //    linkInternoAziende()
    }// fine della closure

    private static boolean installaVersione(int numeroVersioneDaInstallare) {
        boolean installa = false
        int numeroVersioneEsistente = getVersione()

        if (numeroVersioneDaInstallare > numeroVersioneEsistente) {
            installa = true
        }// fine del blocco if

        return installa
    }// fine del metodo

    private static int getVersione() {
        int numero = 0
        def lista = Versione.findAll("from Versione order by numero desc")

        if (lista && lista.size() > 0) {
            numero = lista[0].numero
        }// fine del blocco if

        return numero
    }// fine del metodo

    //--creazione della prima versione
    private static tavolaVersioni() {
        newVersione('Versione', 'Creazione ed inserimento di questa tavola')
    }// fine del metodo

    //--cancella tutto il database
    private void resetCompleto() {
        def lista = null

        //--dipendenza incrociata tra settings e croci
        if (SONO_PROPRIO_SICURO_DI_CANCELLARE_TUTTO) {
            Croce croce
            Settings setting
            lista = Croce.findAll()
            lista?.each {
                croce = (Croce) it
                croce.settings = null
                croce.save(flush: true)
            } // fine del ciclo each
        }// fine del blocco if

        this.cancellaSingolaTavola('Settings')
        this.cancellaSingolaTavola('Logo')
        this.cancellaSingolaTavola('UtenteRuolo')
        this.cancellaSingolaTavola('Utente')
        this.cancellaSingolaTavola('Ruolo')
        this.cancellaSingolaTavola('Turno')
        this.cancellaSingolaTavola('TipoTurno')
        this.cancellaSingolaTavola('Funzione')
        this.cancellaSingolaTavola('Milite')
        this.cancellaSingolaTavola('Militefunzione')
        this.cancellaSingolaTavola('Militeturno')
        this.cancellaSingolaTavola('Militestatistiche')

        //--ultimo
        this.cancellaSingolaTavola('Croce')

        sessionFactory.currentSession.flush()
    }// fine del metodo

    //--cancella tutto il database
    private void resetCroce(String siglaCroce) {
        Croce croce = Croce.findBySigla(siglaCroce)

        if (croce) {
            croce.settings = null
            croce.save(flush: true)
            this.cancellaSingolaTavola(croce, 'Settings')
            this.cancellaLogo(croce)
            this.cancellaUtenteRuolo(croce)
            sessionFactory.currentSession.flush()
            this.cancellaSingolaTavola(croce, 'Utente')
            this.cancellaSingolaTavola(croce, 'Turno')
            this.cancellaSingolaTavola(croce, 'TipoTurno')
            this.cancellaSingolaTavola(croce, 'Militefunzione')
            this.cancellaSingolaTavola(croce, 'Militeturno')
            this.cancellaSingolaTavola(croce, 'Funzione')
            this.cancellaSingolaTavola(croce, 'Militestatistiche')
            this.cancellaSingolaTavola(croce, 'Milite')

            croce.delete()
            sessionFactory.currentSession.flush()
        }// fine del blocco if
    }// fine del metodo

    //--cancella la singola tavola
    private static void cancellaLogo(Croce croce) {
        def lista

        if (croce) {
            lista = Logo.findAllByCroceLogo(croce)
            lista?.each {
                it.delete()
            } // fine del ciclo each
        }// fine del blocco if
    }// fine del metodo

    //--cancella la singola tavola
    private static void cancellaUtenteRuolo(Croce croce) {
        def listaUtentiRuolo
        def listaUtenti = Utente.findAllByCroce(croce)

        if (croce && listaUtenti) {
            listaUtenti?.each {
                listaUtentiRuolo = UtenteRuolo.findAllByUtente(it)
                listaUtentiRuolo?.each {
                    it.delete()
                } // fine del ciclo each
            } // fine del ciclo each
        }// fine del blocco if
    }// fine del metodo

    //--cancella la singola tavola
    private void cancellaSingolaTavola(Croce croce, String nomeTavola) {
        Object obj
        Class clazz
        def lista

        if (croce && nomeTavola) {
            obj = grailsApplication.domainClasses.find { it.clazz.simpleName == nomeTavola }
            clazz = obj?.clazz
            lista = clazz?.findAllByCroce(croce)
            lista?.each {
                it.delete()
                def stop
            } // fine del ciclo each
        }// fine del blocco if
        sessionFactory.currentSession.flush()

        def stoppet
    }// fine del metodo

    //--cancella la singola tavola
    private void cancellaSingolaTavola(String nomeTavola) {
        Object obj
        Class clazz
        def lista

        if (SONO_PROPRIO_SICURO_DI_CANCELLARE_TUTTO) {
            obj = grailsApplication.domainClasses.find { it.clazz.simpleName == nomeTavola }
            clazz = obj?.clazz
            lista = clazz?.findAll()
            lista?.each {
                it.delete()
            } // fine del ciclo each
        }// fine del blocco if
    }// fine del metodo

    //--Croce interna virtuale
    private static void croceAlgos() {
        creazioneCroceInterna()
        configurazioneCroceInterna()
        securitySetupAlgos()
    }// fine del metodo

    //--Croce dimostrativa
    private static void croceDemo() {
        creazioneCroceDemo()
        configurazioneCroceDemo()
        securitySetupDemo()
        funzioniCroceDemo()
        tipiDiTurnoDemo()
        turni2013Demo()
        militiDemo()
    }// fine del metodo

    //--Croce pubblica
    private static void croceTidone() {
        creazioneCroceTidone()
        configurazioneCroceTidone()
        funzioniCroceTidone()
        tipiDiTurnoTidone()
        //--militi ed utenti (security)
        if (ESISTE_COLLEGAMENTO_INTERNET) {
            militiPubblica()
        }// fine del blocco if
    }// fine del metodo

    //--Croce rossa Fidenza
    private static void croceRossaFidenza() {
        creazioneCroceRossaFidenza()
        securitySetupRossaFidenza()
        configurazioneCroceRossaFidenza()
        funzioniCroceRossaFidenza()
        tipiDiTurnoRossaFidenza()
        turni2013RossaFidenza()
        //--militi ed utenti (security)
        if (ESISTE_COLLEGAMENTO_INTERNET) {
            militiRossaFidenza()
        }// fine del blocco if
        utentiRossaFidenza() //password
    }// fine del metodo

    //--Croce rossa Pontetaro
    private static void croceRossaPontetaro() {
        creazioneCroceRossaPonteTaro()
        configurazioneCroceRossaPonteTaro()
        securitySetupRossaPonteTaro()
        funzioniCroceRossaPonteTaro()
        tipiDiTurnoRossaPonteTaro()
        militiRossaPonteTaro()
        utentiRossaPonteTaro() //password
        newVersione(CROCE_ROSSA_PONTETARO, 'Creazione croce', 'Funzioni, tipiTurni, militi, utenti.')
    }// fine del metodo

    //--iniezione di alcune variabili generali visibili in tutto il programma
    //--valori di default che vengono modificati a seconda delle regolazioni nei Settings della croce
    private static void iniezioneVariabili(servletContext) {
        // inietta una variabile per selezionare la croce interessata
        servletContext.croce = Croce.findBySigla(CROCE_ALGOS)

        // login obbligatorio alla partenza del programma
        // ANCHE prima della presentazione del tabellone o del menu
        //       servletContext.startLogin = false

        //--seleziona la videata iniziale
        // nome dell'eventuale controller da invocare
        // automaticamente alla partenza del programma.
        // parte il metodo di default del controller.
        // se non definita visualizza un elenco dei moduli/controller visibili
        //       servletContext.startController = ''

        //--seleziona (flag booleano) se mostrare tutti i controllers nella videata Home
        //       servletContext.allControllers = false

        //--seleziona (lista di stringhe) i controllers da mostrare nella videata Home
        //--ha senso solo se il flag allControllers è false
        //       servletContext.controlli = ''
    }// fine del metodo

    //--Croce interna virtuale
    //--crea solo il record vuoto per farla apparire in lista e nei filtri
    //--creazione inziale della croce
    //--controlla SEMPRE
    //--modifica le proprietà coi valori di default se sono stati vuotati
    private static void creazioneCroceInterna() {
        Croce croce = Croce.findBySigla(CROCE_ALGOS)

        if (!croce) {
            croce = new Croce(sigla: CROCE_ALGOS)
        }// fine del blocco if

        if (croce) {
            if (!croce.descrizione) {
                croce.descrizione = 'Croce interna'
            }// fine del blocco if
            if (!croce.riferimento) {
                croce.riferimento = ''
            }// fine del blocco if
            if (!croce.indirizzo) {
                croce.indirizzo = ''
            }// fine del blocco if
            if (!croce.telefono) {
                croce.telefono = ''
            }// fine del blocco if
            if (!croce.email) {
                croce.email = ''
            }// fine del blocco if
            if (!croce.note) {
                croce.note = 'Croce virtuale per mostrare le croci tutte insieme'
            }// fine del blocco if
            croce.save(failOnError: true)
        }// fine del blocco if
    }// fine del metodo

    //--Croce Dimostrativa
    //--creazione inziale della croce
    //--controlla SEMPRE
    //--modifica le proprietà coi valori di default se sono stati vuotati
    private static void creazioneCroceDemo() {
        Croce croce = Croce.findBySigla(CROCE_DEMO)

        if (!croce) {
            croce = new Croce(sigla: CROCE_DEMO)
        }// fine del blocco if
        if (croce) {
            if (!croce.descrizione) {
                croce.descrizione = 'Croce dimostrativa'
            }// fine del blocco if
            if (!croce.riferimento) {
                croce.riferimento = ''
            }// fine del blocco if
            if (!croce.indirizzo) {
                croce.indirizzo = ''
            }// fine del blocco if
            if (!croce.telefono) {
                croce.telefono = ''
            }// fine del blocco if
            if (!croce.email) {
                croce.email = ''
            }// fine del blocco if
            if (!croce.note) {
                croce.note = 'Croce dimostrativa per mostrare le varie funzionalità possibili'
            }// fine del blocco if
            croce.save(failOnError: true)
        }// fine del blocco if
    }// fine del metodo

    //--Croce Pubblica
    //--creazione inziale della croce
    //--controlla SEMPRE
    //--modifica le proprietà coi valori di default se sono stati vuotati
    private static void creazioneCroceTidone() {
        Croce croce = Croce.findBySigla(CROCE_PUBBLICA_CASTELLO)

        if (!croce) {
            croce = new Croce(sigla: CROCE_PUBBLICA_CASTELLO)
        }// fine del blocco if

        if (croce) {
            if (!croce.descrizione) {
                croce.descrizione = 'Pubblica Assistenza Val Tidone'
            }// fine del blocco if
            if (!croce.riferimento) {
                croce.riferimento = 'Giuseppe Borlenghi'
            }// fine del blocco if
            if (!croce.indirizzo) {
                croce.indirizzo = 'via Morselli, 16 - 29015 Castel San Giovanni (PC)'
            }// fine del blocco if
            if (!croce.telefono) {
                croce.telefono = '0523 842229'
            }// fine del blocco if
            if (!croce.email) {
                croce.email = 'info@pavaltidone.it'
            }// fine del blocco if
            if (!croce.note) {
                croce.note = ''
            }// fine del blocco if
            croce.save(failOnError: true)
        }// fine del blocco if
    }// fine del metodo

    //--Croce Rossa Fidenza
    //--creazione inziale della croce
    //--controlla SEMPRE
    //--modifica le proprietà coi valori di default se sono stati vuotati
    private static void creazioneCroceRossaFidenza() {
        Croce croce = Croce.findBySigla(CROCE_ROSSA_FIDENZA)

        if (!croce) {
            croce = new Croce(sigla: CROCE_ROSSA_FIDENZA)
        }// fine del blocco if

        if (croce) {
            if (!croce.descrizione) {
                croce.descrizione = 'Croce Rossa Italiana - Comitato Locale di Fidenza'
            }// fine del blocco if
            if (!croce.riferimento) {
                croce.riferimento = 'Annarita Tanzi'
            }// fine del blocco if
            if (!croce.indirizzo) {
                croce.indirizzo = 'via la Bionda, 3 - 43036 Fidenza (PR)'
            }// fine del blocco if
            if (!croce.telefono) {
                croce.telefono = '0524 533264'
            }// fine del blocco if
            if (!croce.email) {
                croce.email = 'cl.fidenza@cri.it'
            }// fine del blocco if
            if (!croce.note) {
                croce.note = 'Annarita Tanzi (348 6052310), Paolo Biazzi (328 4820471) e Massimiliano Abati'
            }// fine del blocco if
            croce.save(failOnError: true)
        }// fine del blocco if
    }// fine del metodo

    //--Croce Rossa Ponte Taro
    //--creazione inziale della croce
    //--controlla SEMPRE
    //--modifica le proprietà coi valori di default se sono stati vuotati
    private static void creazioneCroceRossaPonteTaro() {
        Croce croce = Croce.findBySigla(CROCE_ROSSA_PONTETARO)

        if (!croce) {
            croce = new Croce(sigla: CROCE_ROSSA_PONTETARO)
        }// fine del blocco if

        if (croce) {
            if (!croce.descrizione) {
                croce.descrizione = 'Croce Rossa Italiana - Comitato Locale di Ponte Taro'
            }// fine del blocco if
            if (!croce.riferimento) {
                croce.riferimento = 'Mauro Michelini'
            }// fine del blocco if
            if (!croce.indirizzo) {
                croce.indirizzo = 'Via Gramsci, 1 - 43010 Ponte Taro (PR)'
            }// fine del blocco if
            if (!croce.telefono) {
                croce.telefono = '348 8979700'
            }// fine del blocco if
            if (!croce.email) {
                croce.email = 'presidente@cripontetaro.it'
            }// fine del blocco if
            if (!croce.note) {
                croce.note = ''
            }// fine del blocco if
            croce.save(failOnError: true)
        }// fine del blocco if
    }// fine del metodo

    //--creazione ruoli generali di accesso alle autorizzazioni gestite dal security-plugin
    //--occorre SEMPRE un ruolo ROLE_PROG
    //--occorre SEMPRE un ruolo ROLE_ADMIN
    //--occorre SEMPRE un ruolo ROLE_MILITE
    //--li crea SOLO se non esistono già
    private static void securitySetupRuoli() {
        Ruolo.findOrCreateByAuthority(ROLE_PROG).save(failOnError: true)
        Ruolo.findOrCreateByAuthority(ROLE_CUSTODE).save(failOnError: true)
        Ruolo.findOrCreateByAuthority(ROLE_ADMIN).save(failOnError: true)
        Ruolo.findOrCreateByAuthority(ROLE_MILITE).save(failOnError: true)
        Ruolo.findOrCreateByAuthority(ROLE_OSPITE).save(failOnError: true)
    }// fine del metodo

    //--creazione accessi per la croce demo
    //--occorre SEMPRE un accesso come programmatore
    //--occorre SEMPRE un accesso come admin
    //--occorre SEMPRE un accesso come milite
    //--li crea SOLO se non esistono già
    private static void securitySetupAlgos() {
        Utente utente

        //--ruoli
        Ruolo custodeRole = Ruolo.findOrCreateByAuthority(ROLE_CUSTODE).save(failOnError: true)
        Ruolo adminRole = Ruolo.findOrCreateByAuthority(ROLE_ADMIN).save(failOnError: true)
        Ruolo militeRole = Ruolo.findOrCreateByAuthority(ROLE_MILITE).save(failOnError: true)
        Ruolo ospiteRole = Ruolo.findOrCreateByAuthority(ROLE_OSPITE).save(failOnError: true)

        // ruolo di programmatore generale
        utente = newUtente(CROCE_ALGOS, ROLE_PROG, 'gac', 'fulvia')

        // altri ruoli
        if (custodeRole && adminRole && militeRole && ospiteRole && utente) {
            UtenteRuolo.findOrCreateByRuoloAndUtente(custodeRole, utente).save(failOnError: true)
            UtenteRuolo.findOrCreateByRuoloAndUtente(adminRole, utente).save(failOnError: true)
            UtenteRuolo.findOrCreateByRuoloAndUtente(militeRole, utente).save(failOnError: true)
            UtenteRuolo.findOrCreateByRuoloAndUtente(ospiteRole, utente).save(failOnError: true)
        }// fine del blocco if

    }// fine del metodo

    //--creazione accessi per la croce demo
    //--occorre SEMPRE un accesso come admin
    //--occorre SEMPRE un accesso come utente
    //--li crea SOLO se non esistono già
    private static void securitySetupDemo() {
        Utente utente
        Ruolo custodeRole
        Ruolo adminRole
        Ruolo militeRole

        // programmatore generale (sempre presente)
        utente = newUtente(CROCE_DEMO, ROLE_PROG, PROG_NICK_DEMO, PROG_PASS)
        if (utente) {
            custodeRole = Ruolo.findByAuthority(ROLE_CUSTODE).save(failOnError: true)
            adminRole = Ruolo.findByAuthority(ROLE_ADMIN).save(failOnError: true)
            militeRole = Ruolo.findByAuthority(ROLE_MILITE).save(failOnError: true)
            if (custodeRole && adminRole && militeRole && utente) {
                UtenteRuolo.findOrCreateByRuoloAndUtente(custodeRole, utente).save(failOnError: true)
                UtenteRuolo.findOrCreateByRuoloAndUtente(adminRole, utente).save(failOnError: true)
                UtenteRuolo.findOrCreateByRuoloAndUtente(militeRole, utente).save(failOnError: true)
            }// fine del blocco if
        }// fine del blocco if

        // milite (anonimo)
        utente = newUtente(CROCE_DEMO, ROLE_MILITE, DEMO_OSPITE, DEMO_PASSWORD_BOOT)
        utente.pass = DEMO_PASSWORD
        utente.save(flush: true)
    }// fine del metodo

    //--creazione accessi per la croce
    //--occorre SEMPRE un accesso come admin
    //--occorre SEMPRE un accesso come utente
    //--li crea SOLO se non esistono già
    private static void securitySetupRossaFidenza() {
        Utente utente
        String nick
        String pass
        Ruolo adminRole
        Ruolo militeRole

        // programmatore generale (sempre presente)
        newUtente(CROCE_ROSSA_FIDENZA, ROLE_PROG, PROG_NICK_CRF, PROG_PASS)

        if (SVILUPPO_CROCE_ROSSA_FIDENZA) {
            adminRole = Ruolo.findOrCreateByAuthority(ROLE_ADMIN).save(failOnError: true)
            militeRole = Ruolo.findOrCreateByAuthority(ROLE_MILITE).save(failOnError: true)

            // custode
            utente = newUtente(CROCE_ROSSA_FIDENZA, ROLE_CUSTODE, 'Biazzi Paolo', 'biazzi123')
            numUtentiRossaFidenza++
            if (adminRole && militeRole && utente) {
                UtenteRuolo.findOrCreateByRuoloAndUtente(adminRole, utente).save(failOnError: true)
                UtenteRuolo.findOrCreateByRuoloAndUtente(militeRole, utente).save(failOnError: true)
            }// fine del blocco if

            // admin
            utente = newUtente(CROCE_ROSSA_FIDENZA, ROLE_ADMIN, 'Tanzi Annarita', 'tanzi123')
            numUtentiRossaFidenza++
            if (militeRole && utente) {
                UtenteRuolo.findOrCreateByRuoloAndUtente(militeRole, utente).save(failOnError: true)
            }// fine del blocco if

            // admin
            utente = newUtente(CROCE_ROSSA_FIDENZA, ROLE_ADMIN, 'Abati Massimiliano', 'abati123')
            numUtentiRossaFidenza++
            if (militeRole && utente) {
                UtenteRuolo.findOrCreateByRuoloAndUtente(militeRole, utente).save(failOnError: true)
            }// fine del blocco if
        }// fine del blocco if
    }// fine del metodo

    //--creazione accessi per la croce
    //--occorre SEMPRE un accesso come admin
    //--occorre SEMPRE un accesso come utente
    //--li crea SOLO se non esistono già
    private static void securitySetupRossaPonteTaro() {
        Utente utente
        String nick
        String pass
        Ruolo adminRole
        Ruolo militeRole

        // programmatore generale (sempre presente)    @todo ?
        // newUtente(CROCE_ROSSA_PONTETARO, ROLE_PROG, PROG_NICK_CRPT, PROG_PASS)

        if (SVILUPPO_CROCE_ROSSA_PONTE_TARO) {
            adminRole = Ruolo.findOrCreateByAuthority(ROLE_ADMIN).save(failOnError: true)
            militeRole = Ruolo.findOrCreateByAuthority(ROLE_MILITE).save(failOnError: true)

            // custode
            nick = 'Michelini Mauro'
            pass = 'michelini123'
            utente = newUtente(CROCE_ROSSA_PONTETARO, ROLE_CUSTODE, nick, pass)
            numUtentiRossaPonteTaro++
            if (adminRole && militeRole && utente) {
                UtenteRuolo.findOrCreateByRuoloAndUtente(adminRole, utente).save(failOnError: true)
                UtenteRuolo.findOrCreateByRuoloAndUtente(militeRole, utente).save(failOnError: true)
            }// fine del blocco if

            // admin
            nick = 'Gallo Gennaro'
            pass = 'gallo123'
            utente = newUtente(CROCE_ROSSA_PONTETARO, ROLE_ADMIN, nick, pass)
            numUtentiRossaPonteTaro++
            if (militeRole && utente) {
                UtenteRuolo.findOrCreateByRuoloAndUtente(militeRole, utente).save(failOnError: true)
            }// fine del blocco if

            // admin
            nick = 'Pessina Giovanni'
            pass = 'pessina123'
            utente = newUtente(CROCE_ROSSA_PONTETARO, ROLE_ADMIN, nick, pass)
            numUtentiRossaPonteTaro++
            if (militeRole && utente) {
                UtenteRuolo.findOrCreateByRuoloAndUtente(militeRole, utente).save(failOnError: true)
            }// fine del blocco if

            // milite (anonimo)
            utente = newUtente(CROCE_ROSSA_PONTETARO, ROLE_MILITE, CRPT_OSPITE, CRPT_PASSWORD_BOOT)
            utente.pass = CRPT_PASSWORD
            utente.save(flush: true)
        }// fine del blocco if
    }// fine del metodo

    //--Croce interna virtuale
    //--creazione record di configurazione (settings)
    //--lo crea SOLO se non esiste già
    private static void configurazioneCroceInterna() {
        Croce croce = Croce.findBySigla(CROCE_ALGOS)
        Settings setting

        if (croce) {
            setting = Settings.findByCroce(croce)
            if (setting == null) {
                setting = new Settings(croce: croce)
                setting.startLogin = false
                setting.startController = ''
                setting.allControllers = true
                setting.controlli = ''
                setting.mostraSoloMilitiFunzione = true
                setting.mostraMilitiFunzioneAndAltri = true
                setting.militePuoInserireAltri = true
                setting.militePuoModificareAltri = true
                setting.militePuoCancellareAltri = true
                setting.tipoControlloModifica = ControlloTemporale.nessuno
                setting.maxMinutiTrascorsiModifica = 0
                setting.minGiorniMancantiModifica = 0
                setting.tipoControlloCancellazione = ControlloTemporale.nessuno
                setting.maxMinutiTrascorsiCancellazione = 0
                setting.minGiorniMancantiCancellazione = 0
                setting.isOrarioTurnoModificabileForm = false
                setting.isCalcoloNotturnoStatistiche = false
                setting.fissaLimiteMassimoSingoloTurno = false
                setting.oreMassimeSingoloTurno = 0
                setting.save(failOnError: true)
            }// fine del blocco if
        }// fine del blocco if
    }// fine del metodo

    //--Croce dimostrativa
    //--creazione record di configurazione (settings)
    //--lo crea SOLO se non esiste già
    private static void configurazioneCroceDemo() {
        Croce croce = Croce.findBySigla(CROCE_DEMO)
        Settings setting

        if (croce) {
            setting = Settings.findByCroce(croce)
            if (setting == null) {
                setting = new Settings(croce: croce)
                setting.startLogin = false
                setting.startController = 'turno'
                setting.allControllers = false
                setting.controlli = ''
                setting.mostraSoloMilitiFunzione = true
                setting.mostraMilitiFunzioneAndAltri = true
                setting.militePuoInserireAltri = true
                setting.militePuoModificareAltri = true
                setting.militePuoCancellareAltri = true
                setting.tipoControlloModifica = ControlloTemporale.nessuno
                setting.maxMinutiTrascorsiModifica = 0
                setting.minGiorniMancantiModifica = 0
                setting.tipoControlloCancellazione = ControlloTemporale.nessuno
                setting.maxMinutiTrascorsiCancellazione = 0
                setting.minGiorniMancantiCancellazione = 0
                setting.isOrarioTurnoModificabileForm = false
                setting.isCalcoloNotturnoStatistiche = true
                setting.fissaLimiteMassimoSingoloTurno = false
                setting.oreMassimeSingoloTurno = 0
                setting.save(failOnError: true)
            }// fine del blocco if
        }// fine del blocco if
    }// fine del metodo

    //--Croce pubblica tidone
    //--creazione record di configurazione (settings)
    //--lo crea SOLO se non esiste già
    private static void configurazioneCroceTidone() {
        Croce croce = Croce.findBySigla(CROCE_PUBBLICA_CASTELLO)
        Settings setting

        if (croce) {
            setting = Settings.findByCroce(croce)
            if (setting == null) {
                setting = new Settings(croce: croce)
                setting.startLogin = false
                setting.startController = ''
                setting.allControllers = false
                setting.controlli = ''
                setting.mostraSoloMilitiFunzione = true
                setting.mostraMilitiFunzioneAndAltri = true
                setting.militePuoInserireAltri = true
                setting.militePuoModificareAltri = true
                setting.militePuoCancellareAltri = true
                setting.tipoControlloModifica = ControlloTemporale.nessuno
                setting.maxMinutiTrascorsiModifica = 0
                setting.minGiorniMancantiModifica = 0
                setting.tipoControlloCancellazione = ControlloTemporale.nessuno
                setting.maxMinutiTrascorsiCancellazione = 0
                setting.minGiorniMancantiCancellazione = 0
                setting.isOrarioTurnoModificabileForm = false
                setting.isCalcoloNotturnoStatistiche = false
                setting.fissaLimiteMassimoSingoloTurno = false
                setting.oreMassimeSingoloTurno = 0
                setting.save(failOnError: true)
            }// fine del blocco if
        }// fine del blocco if
    }// fine del metodo

    //--Croce rossa fidenza
    //--creazione record di configurazione (settings)
    //--lo crea SOLO se non esiste già
    private static void configurazioneCroceRossaFidenza() {
        Croce croce = Croce.findBySigla(CROCE_ROSSA_FIDENZA)
        Settings setting

        if (croce) {
            setting = Settings.findByCroce(croce)
            if (setting == null) {
                setting = new Settings(croce: croce)
                setting.startLogin = true
                setting.startController = 'turno'
                setting.allControllers = false
                setting.controlli = ''
                setting.mostraSoloMilitiFunzione = true
                setting.mostraMilitiFunzioneAndAltri = false
                setting.militePuoInserireAltri = false
                setting.militePuoModificareAltri = false
                setting.militePuoCancellareAltri = false
                setting.tipoControlloModifica = ControlloTemporale.tempoTrascorso
                setting.maxMinutiTrascorsiModifica = 30
                setting.minGiorniMancantiModifica = 0
                setting.tipoControlloCancellazione = ControlloTemporale.tempoTrascorso
                setting.maxMinutiTrascorsiCancellazione = 30
                setting.minGiorniMancantiCancellazione = 0
                setting.isOrarioTurnoModificabileForm = false
                setting.isCalcoloNotturnoStatistiche = true
                setting.fissaLimiteMassimoSingoloTurno = true
                setting.oreMassimeSingoloTurno = 13
                setting.save(failOnError: true)
            }// fine del blocco if
        }// fine del blocco if
    }// fine del metodo

    //--Croce rossa ponte taro
    //--creazione record di configurazione (settings)
    //--lo crea SOLO se non esiste già
    private static void configurazioneCroceRossaPonteTaro() {
        Croce croce = Croce.findBySigla(CROCE_ROSSA_PONTETARO)
        Settings setting

        if (croce) {
            setting = Settings.findByCroce(croce)
            if (setting == null) {
                setting = new Settings(croce: croce)
                setting.startLogin = false
                setting.startController = 'turno'
                setting.allControllers = false
                setting.controlli = ''
                setting.mostraSoloMilitiFunzione = true
                setting.mostraMilitiFunzioneAndAltri = false
                setting.militePuoInserireAltri = false
                setting.militePuoModificareAltri = false
                setting.militePuoCancellareAltri = false
                setting.tipoControlloModifica = ControlloTemporale.tempoMancante
                setting.maxMinutiTrascorsiModifica = 0
                setting.minGiorniMancantiModifica = 7
                setting.tipoControlloCancellazione = ControlloTemporale.tempoMancante
                setting.maxMinutiTrascorsiCancellazione = 0
                setting.minGiorniMancantiCancellazione = 7
                setting.isOrarioTurnoModificabileForm = false
                setting.isCalcoloNotturnoStatistiche = false
                setting.fissaLimiteMassimoSingoloTurno = false
                setting.oreMassimeSingoloTurno = 0
                setting.save(failOnError: true)
            }// fine del blocco if
            croce.settings = setting
            croce.save(failOnError: true)
        }// fine del blocco if
    }// fine del metodo

    //--creazione link interno ad ogni croce per riferirsi al proprio setting
    //--uno per ogni croce
    //--lo crea SOLO se non esiste già
    //--prima è stata creata l'croce
    //--poi è stato creato il setting
    //--adesso si può inserire nella croce il riferimento al setting
    private static void linkInternoAziende() {
        ArrayList<Croce> croci = Croce.getAll()
        Settings setting
        Croce croce

        croci?.each {
            croce = (Croce) it
            setting = Settings.findByCroce(it)
            if (setting) {
                croce.settings = setting
                croce.save(failOnError: true)
            }// fine del blocco if
        }// fine del blocco each
    }// fine del metodo

    //--creazione funzioni per la croce demo
    //--ogni croce ha SEMPRE diverse funzioni
    //--li crea SOLO se non esistono già
    private static void funzioniCroceDemo() {
        funzDemo.add(newFunzione(CROCE_DEMO, 'aut', 'Aut', 'Autista', 1, ''))
        funzDemo.add(newFunzione(CROCE_DEMO, 'sec', 'Sec', 'Soccorritore', 2, ''))
        funzDemo.add(newFunzione(CROCE_DEMO, 'ter', 'Ter', 'Aiuto', 3, ''))
    }// fine del metodo

    //--creazione funzioni per la croce tidone
    //--ogni croce ha SEMPRE diverse funzioni
    //--li crea SOLO se non esistono già
    private static void funzioniCroceTidone() {
        //--turni ordinari
        newFunzTidoneOrd('autistaord', 'Aut Ord', 'Autista', 1, 'secondoord, terzoord')
        newFunzTidoneOrd('secondoord', '2° ord', 'Secondo', 2, 'terzoord')
        newFunzTidoneOrd('terzoord', '3° ord', 'Terzo', 3, '')
        //--turni 118
        newFunzTidone118('autista118', 'Aut 118', 'Autista 188', 4, 'secondo118, terzo118')
        newFunzTidone118('secondo118', '2° 118', 'Secondo 188', 5, 'terzo118')
        newFunzTidone118('terzo118', '3° 188', 'Terzo 118', 6, '')
    }// fine del metodo

    //--creazione funzioni per la croce rossa fidenza
    //--ogni croce ha SEMPRE diverse funzioni
    //--li crea SOLO se non esistono già
    private static void funzioniCroceRossaFidenza() {
        if (SVILUPPO_CROCE_ROSSA_FIDENZA) {
            //--turni automedica
            newFunzRossaMedica('aut-msa', 'Aut MSA', 'Autista automedica', 1, 'pri-msa, sec-msa')
            newFunzRossaMedica('pri-msa', '1° soc', 'Primo militeFunzione3 automedica', 2, 'sec-msa')
            newFunzRossaMedica('sec-msa', '2° soc', 'Secondo militeFunzione3 automedica', 3, '')
            //--turni ambulanza
            newFunzRossaAmb('aut-amb', 'Aut Amb', 'Autista ambulanza', 4, 'pri-amb, sec-amb, ter-amb')
            newFunzRossaAmb('pri-amb', '1° soc', 'Primo militeFunzione3 ambulanza', 5, 'sec-amb, ter-amb')
            newFunzRossaAmb('sec-amb', '2° soc', 'Secondo militeFunzione3 ambulanza', 6, 'ter-amb')
            newFunzRossaAmb('ter-amb', '3° soc', 'Terzo militeFunzione3 ambulanza', 7, '')
        }// fine del blocco if
    }// fine del metodo

    //--creazione funzioni per la croce rossa ponte taro
    //--ogni croce ha SEMPRE diverse funzioni
    //--li crea SOLO se non esistono già
    private static void funzioniCroceRossaPonteTaro() {
        if (SVILUPPO_CROCE_ROSSA_PONTE_TARO) {
            newFunzRossaPonteTaro('aut-118', 'Aut-eme', 'Autista emergenza', 1, 'aut-ord,soc,bar')
            newFunzRossaPonteTaro('aut-ord', 'Aut-ord', 'Autista ordinario', 2, 'soc,bar')
            newFunzRossaPonteTaro('dae', 'DAE', 'Soccorritore DAE', 3, 'soc,bar')
            newFunzRossaPonteTaro('soc', 'Soc', 'Soccorritore normale', 4, 'bar')
            newFunzRossaPonteTaro('bar', 'Bar-Aff', 'Barelliere-Affiancamento', 5, '')
        }// fine del blocco if
    }// fine del metodo

    //--crea una funzione funzione per la croce
    //--la crea SOLO se non esiste già
    private static void newFunzTidoneOrd(
            String siglaInterna,
            String siglaVisibile,
            String descrizione,
            int ordine,
            String funzioniAutomatiche) {
        funzOrdinarioPAVT.add(newFunzione(CROCE_PUBBLICA_CASTELLO, siglaInterna, siglaVisibile, descrizione, ordine, funzioniAutomatiche))
    }// fine del metodo

    //--crea una funzione funzione per la croce
    //--la crea SOLO se non esiste già
    private static void newFunzTidone118(
            String siglaInterna,
            String siglaVisibile,
            String descrizione,
            int ordine,
            String funzioniAutomatiche) {
        funz118PAVT.add(newFunzione(CROCE_PUBBLICA_CASTELLO, siglaInterna, siglaVisibile, descrizione, ordine, funzioniAutomatiche))
    }// fine del metodo

    //--crea una funzione funzione per la croce
    //--la crea SOLO se non esiste già
    private static void newFunzRossaMedica(
            String siglaInterna,
            String siglaVisibile,
            String descrizione,
            int ordine,
            String funzioniAutomatiche) {

        funzAutomedicaCRF.add(newFunzione(CROCE_ROSSA_FIDENZA, siglaInterna, siglaVisibile, descrizione, ordine, funzioniAutomatiche))
    }// fine del metodo

    //--crea una funzione funzione per la croce
    //--la crea SOLO se non esiste già
    private static void newFunzRossaAmb(
            String siglaInterna,
            String siglaVisibile,
            String descrizione,
            int ordine,
            String funzioniAutomatiche) {

        funzAmbulanzaCRF.add(newFunzione(CROCE_ROSSA_FIDENZA, siglaInterna, siglaVisibile, descrizione, ordine, funzioniAutomatiche))
    }// fine del metodo

    //--crea una funzione funzione per la croce
    //--la crea SOLO se non esiste già
    private static void newFunzRossaPonteTaro(
            String siglaInterna,
            String siglaVisibile,
            String descrizione,
            int ordine,
            String funzioniAutomatiche) {

        funzCRPT.add(newFunzione(CROCE_ROSSA_PONTETARO, siglaInterna, siglaVisibile, descrizione, ordine, funzioniAutomatiche))
    }// fine del metodo

    //--crea una funzione funzione per la croce
    //--la crea SOLO se non esiste già
    private static void newFunzPianoro(
            String siglaInterna,
            String siglaVisibile,
            String descrizione,
            int ordine,
            String funzioniAutomatiche) {

        funzPAP.add(newFunzione(CROCE_PUBBLICA_PIANORO, siglaInterna, siglaVisibile, descrizione, ordine, funzioniAutomatiche))
    }// fine del metodo

    //--crea una funzione funzione per la croce
    //--la crea SOLO se non esiste già
    private static Funzione newFunzione(
            String siglaCroce,
            String siglaInterna,
            String siglaVisibile,
            String descrizione,
            int ordine,
            String funzioniDipendenti) {
        Funzione funzione = null
        Croce croce = Croce.findBySigla(siglaCroce)

        if (croce) {
            funzione = Funzione.findOrCreateByCroceAndSigla(croce, siglaInterna)
            if (!funzione.siglaVisibile) {
                funzione.siglaVisibile = siglaVisibile
            }// fine del blocco if
            if (!funzione.descrizione) {
                funzione.descrizione = descrizione
            }// fine del blocco if
            if (!funzione.ordine) {
                funzione.ordine = ordine
            }// fine del blocco if
            if (!funzione.funzioniDipendenti) {
                funzione.funzioniDipendenti = funzioniDipendenti
            }// fine del blocco if

            funzione.save(failOnError: true)
        }// fine del blocco if
        return funzione
    }// fine del metodo

    //--creazione delle tipologie di turni per la croce demo
    //--ogni croce può avere tipologie differenti
    //--li crea SOLO se non esistono già
    private static void tipiDiTurnoDemo() {
        newTipoTurno(CROCE_DEMO, 'mat', 'mattino', 1, 8, 14, false, true, true, false, 1, funzDemo)
        newTipoTurno(CROCE_DEMO, 'pom', 'pomeriggio', 2, 14, 20, false, true, true, false, 1, funzDemo)
        newTipoTurno(CROCE_DEMO, 'notte', 'notte', 3, 20, 8, true, true, true, false, 1, funzDemo)
    }// fine del metodo

    //--creazione delle tipologie di turni per la croce tidone
    //--ogni croce può avere tipologie differenti
    //--li crea SOLO se non esistono già
    private static void tipiDiTurnoTidone() {
        //--ordinari escluso domenica
        //--118 esteso (mattino successivo alle 8) nei prefestivi
        newTipoTurnoOrdPAVT('ord-mat', 'ordinario mattino', 1, 7, 14, false, true, true, false, 2)
        newTipoTurno118PAVT('118-mat', '118 mattino', 2, 8, 14, false, true, true, false, 2)
        newTipoTurnoOrdPAVT('ord-pom', 'ordinario pomeriggio', 3, 7, 14, false, true, true, false, 2)
        newTipoTurno118PAVT('118-pom', '118 pomeriggio', 4, 7, 14, false, true, true, false, 2)
        newTipoTurno118PAVT('118-ser', '118 sera/notte', 5, 7, 14, true, true, true, false, 2)
        newTipoTurnoOrdPAVT('avis', 'avis', 6, 0, 0, false, true, false, false, 1)
        newTipoTurnoOrdPAVT('cent', 'centralino', 7, 0, 0, false, false, false, false, 1)
        newTipoTurnoOrdPAVT(Cost.EXTRA, 'extra/manifestazione', 8, 7, 14, false, true, false, true, 2)
    }// fine del metodo

    //--creazione delle tipologie di turni per la croce rossa fidenza
    //--ogni croce può avere tipologie differenti
    //--li crea SOLO se non esistono già
    private static void tipiDiTurnoRossaFidenza() {
        if (SVILUPPO_CROCE_ROSSA_FIDENZA) {
            //--turni automedica
            newTipoTurnoMedicRossa('msa-mat', 'automedica mattina', 1, 7, 13, false, true, true, false, 1)
            newTipoTurnoMedicRossa('msa-pom', 'automedica pomeriggio', 2, 13, 19, false, true, true, false, 1)
            newTipoTurnoMedicRossa('msa-notte', 'automedica notte', 3, 19, 7, true, true, true, false, 1)
            //--turni ambulanza
            //--ambulanza diurna (7-13 e 13-20) solo festivi
            newTipoTurnoAmbRossa('amb-mat', 'ambulanza mattina', 4, 7, 13, false, true, true, false, 2)
            newTipoTurnoAmbRossa('amb-pom', 'ambulanza pomeriggio', 4, 13, 20, false, true, true, false, 2)
            newTipoTurnoAmbRossa('amb-notte', 'ambulanza notte', 6, 20, 7, true, true, true, false, 2)
            newTipoTurnoAmbRossa('extra', 'extra/manifestazione', 7, 0, 0, false, true, false, true, 2)
        }// fine del blocco if
    }// fine del metodo

    //--creazione delle tipologie di turni per la croce rossa ponte taro
    //--ogni croce può avere tipologie differenti
    //--li crea SOLO se non esistono già
    private static void tipiDiTurnoRossaPonteTaro() {
        if (SVILUPPO_CROCE_ROSSA_PONTE_TARO) {
            newTipoTurnoCRPT('118-mat', 'ambulanza mattina', 1, 7, 14, false, true, true, false, 3, funzCRPT[0], funzCRPT[2], funzCRPT[3], funzCRPT[4])
            newTipoTurnoCRPT('118-pom', 'ambulanza pomeriggio', 2, 14, 21, false, true, true, false, 3, funzCRPT[0], funzCRPT[2], funzCRPT[3], funzCRPT[4])
            newTipoTurnoCRPT('118-notte', 'ambulanza notte', 3, 21, 7, true, true, true, false, 3, funzCRPT[0], funzCRPT[2], funzCRPT[3], funzCRPT[4])
            newTipoTurnoCRPT('dia-1a', 'dialisi 1 andata', 4, 0, 0, false, true, false, false, 1, funzCRPT[1], funzCRPT[4], funzCRPT[4], null)
            newTipoTurnoCRPT('dia-1r', 'dialisi 1 ritorno', 5, 0, 0, false, true, false, false, 1, funzCRPT[1], funzCRPT[4], funzCRPT[4], null)
            newTipoTurnoCRPT('dia-2a', 'dialisi 2 andata', 6, 0, 0, false, true, false, false, 1, funzCRPT[1], funzCRPT[4], funzCRPT[4], null)
            newTipoTurnoCRPT('dia-2r', 'dialisi 2 ritorno', 7, 0, 0, false, true, false, false, 1, funzCRPT[1], funzCRPT[4], funzCRPT[4], null)
            newTipoTurnoCRPT('ord', 'ordinario', 8, 0, 0, false, true, false, true, 1, funzCRPT[1], funzCRPT[3], funzCRPT[4], null)
        }// fine del blocco if
    }// fine del metodo

    //--regola il tipo di turno coi parametri indicati
    //--registra il tipo di turno
    //--lo crea SOLO se non esiste già
    private static void newTipoTurnoOrdPAVT(
            String sigla,
            String desc,
            int ord,
            int oraIni,
            int oraFine,
            boolean next,
            boolean vis,
            boolean orario,
            boolean mult,
            int funzObb) {
        newTipoTurno(CROCE_PUBBLICA_CASTELLO, sigla, desc, ord, oraIni, oraFine, next, vis, orario, mult, funzObb, funzOrdinarioPAVT)
    }// fine del metodo

    //--regola il tipo di turno coi parametri indicati
    //--registra il tipo di turno
    //--lo crea SOLO se non esiste già
    private static void newTipoTurno118PAVT(
            String sigla,
            String desc,
            int ord,
            int oraIni,
            int oraFine,
            boolean next,
            boolean vis,
            boolean orario,
            boolean mult,
            int funzObb) {
        newTipoTurno(CROCE_PUBBLICA_CASTELLO, sigla, desc, ord, oraIni, oraFine, next, vis, orario, mult, funzObb, funz118PAVT)
    }// fine del metodo

    //--regola il tipo di turno coi parametri indicati
    //--registra il tipo di turno
    //--lo crea SOLO se non esiste già
    private static void newTipoTurnoMedicRossa(
            String sigla,
            String desc,
            int ord,
            int oraIni,
            int oraFine,
            boolean next,
            boolean vis,
            boolean orario,
            boolean mult,
            int funzObb) {
        newTipoTurno(CROCE_ROSSA_FIDENZA, sigla, desc, ord, oraIni, oraFine, next, vis, orario, mult, funzObb, funzAutomedicaCRF)
    }// fine del metodo

    //--regola il tipo di turno coi parametri indicati
    //--registra il tipo di turno
    //--lo crea SOLO se non esiste già
    private static void newTipoTurnoAmbRossa(
            String sigla,
            String desc,
            int ord,
            int oraIni,
            int oraFine,
            boolean next,
            boolean vis,
            boolean orario,
            boolean mult,
            int funzObb) {
        newTipoTurno(CROCE_ROSSA_FIDENZA, sigla, desc, ord, oraIni, oraFine, next, vis, orario, mult, funzObb, funzAmbulanzaCRF)
    }// fine del metodo

    //--regola il tipo di turno coi parametri indicati
    //--registra il tipo di turno
    //--lo crea SOLO se non esiste già
    private static void newTipoTurnoRossaPonteTaro(
            String sigla,
            String desc,
            int ord,
            int oraIni,
            int oraFine,
            boolean next,
            boolean vis,
            boolean orario,
            boolean mult,
            int funzObb) {
        newTipoTurno(CROCE_ROSSA_PONTETARO, sigla, desc, ord, oraIni, oraFine, next, vis, orario, mult, funzObb, funzCRPT)
    }// fine del metodo

    //--regola il tipo di turno coi parametri indicati
    //--registra il tipo di turno
    //--lo crea SOLO se non esiste già
    private static void newTipoTurnoPianoro(
            String sigla,
            String desc,
            int ord,
            int oraIni,
            int oraFine,
            boolean next,
            boolean vis,
            boolean orario,
            boolean mult,
            int funzObb) {
        newTipoTurno(CROCE_PUBBLICA_PIANORO, sigla, desc, ord, oraIni, oraFine, next, vis, orario, mult, funzObb, funzPAP)
    }// fine del metodo

    //--regola il tipo di turno coi parametri indicati
    //--registra il tipo di turno
    //--lo crea SOLO se non esiste già
    private static void newTipoTurno(
            String siglaCroce,
            String sigla,
            String descrizione,
            int ordine,
            int oraInizio,
            int oraFine,
            boolean fineGiornoSuccessivo,
            boolean visibile,
            boolean orario,
            boolean multiplo,
            int funzioniObbligatorie,
            ArrayList<Funzione> funzioni) {
        Croce croce = Croce.findBySigla(siglaCroce)
        TipoTurno tipo
        String funz

        if (croce) {
            tipo = TipoTurno.findOrCreateByCroceAndSigla(croce, sigla).save(failOnError: true)
            tipo.descrizione = descrizione
            tipo.ordine = ordine
            tipo.oraInizio = oraInizio
            tipo.oraFine = oraFine
            tipo.fineGiornoSuccessivo = fineGiornoSuccessivo
            tipo.visibile = visibile
            tipo.orario = orario
            tipo.multiplo = multiplo
            tipo.funzioniObbligatorie = funzioniObbligatorie

            for (int k = 1; k <= 4; k++) {
                funz = 'funzione' + k
                if (funzioni.size() >= k) {
                    tipo."${funz}" = funzioni[k - 1]
                } else {
                    tipo."${funz}" = null
                }// fine del blocco if-else
            } // fine del ciclo for

            tipo.save(failOnError: true)
        }// fine del blocco if
    }// fine del metodo

    //--regola il tipo di turno coi parametri indicati
    //--registra il tipo di turno
    //--lo crea SOLO se non esiste già
    private static void newTipoTurnoCRPT(
            String sigla,
            String descrizione,
            int ordine,
            int oraInizio,
            int oraFine,
            boolean fineGiornoSuccessivo,
            boolean visibile,
            boolean orario,
            boolean multiplo,
            int funzioniObbligatorie,
            Funzione funz1,
            Funzione funz2,
            Funzione funz3,
            Funzione funz4) {
        Croce croce = Croce.findBySigla(CROCE_ROSSA_PONTETARO)
        TipoTurno tipo
        String funz

        if (croce) {
            tipo = TipoTurno.findOrCreateByCroceAndSigla(croce, sigla).save(failOnError: true)
            tipo.descrizione = descrizione
            tipo.ordine = ordine
            tipo.oraInizio = oraInizio
            tipo.oraFine = oraFine
            tipo.fineGiornoSuccessivo = fineGiornoSuccessivo
            tipo.visibile = visibile
            tipo.orario = orario
            tipo.multiplo = multiplo
            tipo.funzioniObbligatorie = funzioniObbligatorie
            if (funz1) {
                tipo.funzione1 = funz1
            }// fine del blocco if
            if (funz2) {
                tipo.funzione2 = funz2
            }// fine del blocco if
            if (funz3) {
                tipo.funzione3 = funz3
            }// fine del blocco if
            if (funz4) {
                tipo.funzione4 = funz4
            }// fine del blocco if

            tipo.save(failOnError: true)
        }// fine del blocco if
    }// fine del metodo

    //--creazione dell'elenco militi per la croce demo
    //--elenco disponibile in csv
    //--li crea SOLO se non esistono già
    private static void militiDemo() {
        Croce croce = Croce.findBySigla(CROCE_DEMO)
        String nomeFile = 'demo'
        def righe
        String nome
        String cognome
        Map mappa
        righe = LibFile.leggeCsv(DIR_PATH + nomeFile)
        def listaMiliti
        Milite milite

        if (!ESISTE_COLLEGAMENTO_INTERNET) {
            return
        }// fine del blocco if

        if (!croce) {
            return
        }// fine del blocco if

        //--prosegue solo se il database è vuoto
        if (Milite.findAllByCroce(croce).size() > 0) {
            return
        }// fine del blocco if

        righe?.each {
            mappa = (Map) it
            nome = mappa.nome
            cognome = mappa.cognome
            milite = Milite.findOrCreateByCroceAndNomeAndCognome(croce, nome, cognome).save(failOnError: true)
        } // fine del ciclo each

        //--funzioni
        listaMiliti = Milite.findAllByCroce(croce)
        if (listaMiliti) {
            int numAutisti = 2
            if (listaMiliti.size() >= numAutisti) {
                for (int k = 0; k < numAutisti; k++) {
                    Militefunzione.findOrCreateByCroceAndMiliteAndFunzione(croce, listaMiliti[k], funzDemo[0]).save(flush: true)
                    Militefunzione.findOrCreateByCroceAndMiliteAndFunzione(croce, listaMiliti[k], funzDemo[1]).save(flush: true)
                    Militefunzione.findOrCreateByCroceAndMiliteAndFunzione(croce, listaMiliti[k], funzDemo[2]).save(flush: true)
                } // fine del ciclo for
            }// fine del blocco if
            int numSecondi = 4
            if (listaMiliti.size() >= numSecondi) {
                for (int k = numAutisti; k < numSecondi; k++) {
                    Militefunzione.findOrCreateByCroceAndMiliteAndFunzione(croce, listaMiliti[k], funzDemo[1]).save(flush: true)
                    Militefunzione.findOrCreateByCroceAndMiliteAndFunzione(croce, listaMiliti[k], funzDemo[2]).save(flush: true)
                } // fine del ciclo for
            }// fine del blocco if
            int numTerzi = 6
            if (listaMiliti.size() >= numTerzi) {
                for (int k = numSecondi; k < numTerzi; k++) {
                    Militefunzione.findOrCreateByCroceAndMiliteAndFunzione(croce, listaMiliti[k], funzDemo[2]).save(flush: true)
                } // fine del ciclo for
            }// fine del blocco if
        }// fine del blocco if
    }// fine del metodo

    //--creazione dell'elenco militi per la Pubblica Assistenza Val Tidone
    //--elenco disponibile in csv
    //--li crea SOLO se non esistono già
    private static void militiPubblica() {
        Croce croce = Croce.findBySigla(CROCE_PUBBLICA_CASTELLO)
        String nomeFile = 'pavt'
        Milite milite
        def righe
        String nome
        String cognome
        String telFisso
        String telCellulare
        Map mappa
        righe = LibFile.leggeCsv(DIR_PATH + nomeFile)

        if (!righe) {
            // log 'file non trovato'
        }// fine del blocco if

        if (!croce) {
            return
        }// fine del blocco if

        //--prosegue solo se il database è vuoto
        if (Milite.findAllByCroce(croce).size() > 0) {
            return
        }// fine del blocco if

        righe?.each {
            mappa = (Map) it
            nome = mappa.nome
            cognome = mappa.cognome
            telFisso = mappa.telCellulare
            telCellulare = mappa.telCellulare

            milite = Milite.findOrCreateByCroceAndNomeAndCognome(croce, nome, cognome)
            milite.telefonoFisso = telFisso
            milite.telefonoCellulare = telCellulare
            if (!milite.note) {
                milite.note = ''
            }// fine del blocco if

            milite.save(failOnError: true)
        } // fine del ciclo each
    }// fine del metodo

    //--creazione dell'elenco militi per la Croce Rossa Fidenza
    //--elenco disponibile in csv
    //--li crea SOLO se non esistono già
    private static void militiRossaFidenza() {
        Croce croce = Croce.findBySigla(CROCE_ROSSA_FIDENZA)
        String nomeFileSoci = 'crfdefinitivo'
        def righe
        String nome = ''
        String cognome = ''
        String stringaNascita
        Date dataNascita = null
        Map mappa
        Milite milite
        String cognomenome = ''
        String cellulare = ''
        String fisso = ''
        String tagVero = 'x'
        String blsTxt = ''
        String alsTxt = ''
        String autTxt = ''
        boolean bls = false
        boolean als = false
        boolean aut = false

        if (!croce) {
            return
        }// fine del blocco if

        //--prosegue solo se il database è vuoto
        if (Milite.findAllByCroce(croce).size() > 0) {
            return
        }// fine del blocco if

        if (!SVILUPPO_CROCE_ROSSA_FIDENZA) {
            return
        }// fine del blocco if

        //--telefoni
        righe = LibFile.leggeCsv(DIR_PATH + nomeFileSoci)
        righe?.each {
            cognomenome = ''
            cellulare = ''
            fisso = ''
            blsTxt = ''
            alsTxt = ''
            autTxt = ''
            bls = false
            als = false
            aut = false

            mappa = (Map) it

            if (mappa.cognomenome) {
                cognomenome = mappa.cognomenome
                cognomenome = cognomenome.trim()
                if (cognomenome.indexOf(' ') > 0) {
                    nome = cognomenome.substring(cognomenome.indexOf(' '))
                    cognome = cognomenome.substring(0, cognomenome.indexOf(' '))
                    nome = nome.trim()
                    cognome = cognome.trim()
                }// fine del blocco if
            }// fine del blocco if

            if (mappa.cellulare) {
                cellulare = mappa.cellulare
            }// fine del blocco if
            if (mappa.fisso) {
                fisso = mappa.fisso
            }// fine del blocco if
            if (mappa.bls) {
                blsTxt = mappa.bls
                if (blsTxt && blsTxt.equals(tagVero)) {
                    bls = true
                }// fine del blocco if
            }// fine del blocco if
            if (mappa.als) {
                alsTxt = mappa.als
                if (alsTxt && alsTxt.equals(tagVero)) {
                    als = true
                }// fine del blocco if
            }// fine del blocco if
            if (mappa.aut) {
                autTxt = mappa.aut
                if (autTxt && autTxt.equals(tagVero)) {
                    aut = true
                }// fine del blocco if
            }// fine del blocco if

            milite = Milite.findOrCreateByCroceAndNomeAndCognome(croce, nome, cognome)

            if (milite) {
                if (!milite.telefonoCellulare) {
                    milite.telefonoCellulare = cellulare
                }// fine del blocco if
                if (!milite.telefonoFisso) {
                    milite.telefonoFisso = fisso
                }// fine del blocco if
                milite.save(failOnError: true)
                if (aut) {
                    Militefunzione.findOrCreateByCroceAndMiliteAndFunzione(croce, milite, funzAutomedicaCRF[0]).save(flush: true)
                    Militefunzione.findOrCreateByCroceAndMiliteAndFunzione(croce, milite, funzAmbulanzaCRF[0]).save(flush: true)
                }// fine del blocco if
                if (als) {
                    Militefunzione.findOrCreateByCroceAndMiliteAndFunzione(croce, milite, funzAutomedicaCRF[1]).save(flush: true)
                    Militefunzione.findOrCreateByCroceAndMiliteAndFunzione(croce, milite, funzAutomedicaCRF[2]).save(flush: true)
                    Militefunzione.findOrCreateByCroceAndMiliteAndFunzione(croce, milite, funzAmbulanzaCRF[1]).save(flush: true)
                    Militefunzione.findOrCreateByCroceAndMiliteAndFunzione(croce, milite, funzAmbulanzaCRF[2]).save(flush: true)
                    Militefunzione.findOrCreateByCroceAndMiliteAndFunzione(croce, milite, funzAmbulanzaCRF[3]).save(flush: true)
                }// fine del blocco if
                if (bls) {
                    Militefunzione.findOrCreateByCroceAndMiliteAndFunzione(croce, milite, funzAmbulanzaCRF[1]).save(flush: true)
                    Militefunzione.findOrCreateByCroceAndMiliteAndFunzione(croce, milite, funzAmbulanzaCRF[2]).save(flush: true)
                    Militefunzione.findOrCreateByCroceAndMiliteAndFunzione(croce, milite, funzAmbulanzaCRF[3]).save(flush: true)
                }// fine del blocco if

                //--di default almeno il livello più basso lo metto
                int numFunzAmb = funzAmbulanzaCRF.size() - 1
                Militefunzione.findOrCreateByCroceAndMiliteAndFunzione(croce, milite, funzAmbulanzaCRF[numFunzAmb]).save(flush: true)
            }// fine del blocco if

        } // fine del ciclo each
    }// fine del metodo

    //--creazione dell'elenco militi per la Croce Rossa Ponte Taro
    //--elenco disponibile in csv
    //--li crea SOLO se non esistono già
    private static void militiRossaPonteTaro() {
        Croce croce = Croce.findBySigla(CROCE_ROSSA_PONTETARO)
        String nomeFileSoci = 'pontetaro'
        def righe
        String nome = ''
        String cognome = ''
        String patente = ''
        String telefono = ''
        String fisso = ''
        String mail = ''
        String dataNascitaTxt = ''
        Date dataNascita = null
        Map mappa
        Milite milite
        String daeTxt = ''
        String barTxt = ''
        String socTxt = ''
        String tagVero = 'SI'
        String tagFalso = 'NO'
        boolean autEme = false
        boolean autOrd = false
        boolean dae = false
        boolean soc = false
        boolean bar = false
        String tagPunto = '.'
        int pos
        String prima
        String dopo
        String tagSpazio = ' '

        if (!ESISTE_COLLEGAMENTO_INTERNET) {
            return
        }// fine del blocco if

        if (!croce) {
            return
        }// fine del blocco if

        //--prosegue solo se il database è vuoto
        if (Milite.findAllByCroce(croce).size() > 0) {
            return
        }// fine del blocco if

        //--telefoni
        righe = LibFile.leggeCsv(DIR_PATH + nomeFileSoci)
        righe?.each {
            cognome = ''
            nome = ''
            patente = ''
            telefono = ''
            fisso = ''
            mail = ''
            dataNascitaTxt = ''
            daeTxt = ''
            barTxt = ''
            autEme = false
            autOrd = false
            dae = false
            soc = false
            bar = false

            mappa = (Map) it

            if (mappa.Cognome) {
                cognome = mappa.Cognome
                cognome = cognome.trim()
                cognome = Lib.primaMaiuscola(cognome)
            }// fine del blocco if

            if (mappa.Nome) {
                nome = mappa.Nome
                nome = nome.trim()
                nome = Lib.primaMaiuscola(nome)
            }// fine del blocco if

            if (mappa.Telefono) {
                telefono = mappa.Telefono
                if (telefono.contains(tagPunto)) {
                    pos = telefono.indexOf(tagPunto)
                    prima = telefono.substring(0, pos)
                    dopo = telefono.substring(++pos)
                    telefono = prima + tagSpazio + dopo
                }// fine del blocco if
            }// fine del blocco if

            if (mappa.Fisso) {
                fisso = mappa.Fisso
                if (fisso.contains(tagPunto)) {
                    pos = fisso.indexOf(tagPunto)
                    prima = fisso.substring(0, pos)
                    dopo = fisso.substring(++pos)
                    fisso = prima + tagSpazio + dopo
                }// fine del blocco if
            }// fine del blocco if

            if (mappa.Patente) {
                patente = mappa.Patente
                if (patente.equals('5')) {
                    autEme = true
                }// fine del blocco if
                if (patente.equals('4')) {
                    autOrd = true
                }// fine del blocco if
            }// fine del blocco if

            if (mappa.Dae) {
                daeTxt = mappa.Dae
                if (daeTxt.equals(tagVero)) {
                    dae = true
                }// fine del blocco if
            }// fine del blocco if

            if (mappa.soccoritore) {
                socTxt = mappa.soccoritore
                if (socTxt.equals(tagVero)) {
                    soc = true
                }// fine del blocco if
            }// fine del blocco if

            if (mappa.Barelliere) {
                barTxt = mappa.Barelliere
                if (barTxt.equals(tagVero)) {
                    bar = true
                }// fine del blocco if
            }// fine del blocco if

            if (mappa.Mail) {
                mail = mappa.Mail
                if (mail.equals(tagFalso)) {
                    mail = ''
                }// fine del blocco if
            }// fine del blocco if

            if (mappa.dataNascita) {
                dataNascitaTxt = mappa.dataNascita
                if (dataNascitaTxt) {
                    dataNascita = Lib.creaData(dataNascitaTxt)
                }// fine del blocco if
            }// fine del blocco if

            milite = Milite.findOrCreateByCroceAndNomeAndCognome(croce, nome, cognome)
            if (!milite.telefonoCellulare) {
                milite.telefonoCellulare = telefono
            }// fine del blocco if
            if (!milite.telefonoFisso) {
                milite.telefonoFisso = fisso
            }// fine del blocco if
            if (!milite.email) {
                milite.email = mail
            }// fine del blocco if
            if (!milite.dataNascita) {
                milite.dataNascita = dataNascita
            }// fine del blocco if
            milite.save(failOnError: true)
            if (milite) {
                if (autEme) {
                    Militefunzione.findOrCreateByCroceAndMiliteAndFunzione(croce, milite, funzCRPT[0]).save(flush: true)
                    Militefunzione.findOrCreateByCroceAndMiliteAndFunzione(croce, milite, funzCRPT[1]).save(flush: true)
                    Militefunzione.findOrCreateByCroceAndMiliteAndFunzione(croce, milite, funzCRPT[3]).save(flush: true)
                    Militefunzione.findOrCreateByCroceAndMiliteAndFunzione(croce, milite, funzCRPT[4]).save(flush: true)
                }// fine del blocco if
                if (autOrd) {
                    Militefunzione.findOrCreateByCroceAndMiliteAndFunzione(croce, milite, funzCRPT[1]).save(flush: true)
                    Militefunzione.findOrCreateByCroceAndMiliteAndFunzione(croce, milite, funzCRPT[3]).save(flush: true)
                    Militefunzione.findOrCreateByCroceAndMiliteAndFunzione(croce, milite, funzCRPT[4]).save(flush: true)
                }// fine del blocco if
                if (dae) {
                    Militefunzione.findOrCreateByCroceAndMiliteAndFunzione(croce, milite, funzCRPT[2]).save(flush: true)
                    Militefunzione.findOrCreateByCroceAndMiliteAndFunzione(croce, milite, funzCRPT[3]).save(flush: true)
                    Militefunzione.findOrCreateByCroceAndMiliteAndFunzione(croce, milite, funzCRPT[4]).save(flush: true)
                }// fine del blocco if
                if (soc) {
                    Militefunzione.findOrCreateByCroceAndMiliteAndFunzione(croce, milite, funzCRPT[3]).save(flush: true)
                    Militefunzione.findOrCreateByCroceAndMiliteAndFunzione(croce, milite, funzCRPT[4]).save(flush: true)
                }// fine del blocco if
                if (bar) {
                    Militefunzione.findOrCreateByCroceAndMiliteAndFunzione(croce, milite, funzCRPT[4]).save(flush: true)
                }// fine del blocco if
            }// fine del blocco if

        } // fine del ciclo each
    }// fine del metodo

    //--aggiunta a tutti i militi di una data di nascita fittizia a scopo sviluppo
    //--la aggiunge SOLO in sviluppo (debug)
    //--la aggiunge SOLO per la pubblica
    private static void militiNascita() {
        ArrayList lista = null
        Croce croce = Croce.findBySigla(CROCE_PUBBLICA_CASTELLO)
        if (croce) {
            lista = Milite.findAllByCroce(croce)
        }
        Milite milite
        Date data = new Date()
        int offset = 0

        if (false) {
            lista?.each {
                milite = (Milite) it
                data = data - offset++
                milite.dataNascita = data
                milite.save(flush: true)
            } // fine del ciclo each
        }
    }// fine del metodo

    //--aggiunta a tutti i militi delle date fittizie di scadenza abilitazioni; a scopo sviluppo
    //--la aggiunge SOLO in sviluppo (debug)
    private static void militiBLSD() {
        ArrayList lista = Milite.all
        Milite milite
        Date data = new Date()
        int offset = 0

        if (false) {
            lista?.each {
                milite = (Milite) it
                data = data + offset++
                milite.scadenzaBLSD = data
                milite.scadenzaTrauma = data + 1
                milite.scadenzaNonTrauma = data + 2
                milite.save(flush: true)
            } // fine del ciclo each
        }
    }// fine del metodo

    //--creazione dei record utenti per la croce rossa
    //--uno per ogni milite
    //--nickname=cognomeNome
    //--password=cognome(minuscolo) + 3 cifre numeriche random
    //--li crea SOLO se non esistono già
    private static void utentiRossaFidenza() {
        Croce croce = Croce.findBySigla(CROCE_ROSSA_FIDENZA)
        ArrayList listaUtenti
        ArrayList listaMiliti
        Milite milite

        if (!croce) {
            return
        }// fine del blocco if

        if (!SVILUPPO_CROCE_ROSSA_FIDENZA) {
            return
        }// fine del blocco if

        listaUtenti = Utente.findAllByCroce(croce)
        if (listaUtenti.size() > numUtentiRossaFidenza) {
            listaMiliti = Milite.findAllByCroce(croce)
            listaMiliti?.each {
                milite = (Milite) it
                newUtenteMilite(CROCE_ROSSA_FIDENZA, milite)
            } // fine del ciclo each
        }// fine del blocco if
    }// fine del metodo

    //--creazione dei record utenti per la croce rossa Pontetaro
    //--uno per ogni milite
    //--nickname=cognomeNome
    //--password=cognome(minuscolo) + 3 cifre numeriche random
    //--li crea SOLO se non esistono già
    private static void utentiRossaPonteTaro() {
        Croce croce = Croce.findBySigla(CROCE_ROSSA_PONTETARO)
        ArrayList listaUtenti
        ArrayList listaMiliti
        Milite milite
        String nickname

        if (!croce) {
            return
        }// fine del blocco if

        if (!SVILUPPO_CROCE_ROSSA_PONTE_TARO) {
            return
        }// fine del blocco if

        listaUtenti = Utente.findAllByCroce(croce)
        if (listaUtenti.size() > numUtentiRossaPonteTaro) {
            listaMiliti = Milite.findAllByCroce(croce)
            listaMiliti?.each {
                milite = (Milite) it
                newUtenteMilite(CROCE_ROSSA_PONTETARO, milite)
//                nickname = milite.cognome + ' ' + milite.nome
//                if (!Utente.findByNickname(nickname)) {
//                    newUtenteMilite(CROCE_ROSSA_PONTETARO, milite)
//                }// fine del blocco if
            } // fine del ciclo each
        }// fine del blocco if
    }// fine del metodo

    //--creazione dei turni vuoti per il 2013
    //--li crea SOLO se non esistono già
    private static void turni2013Demo() {
        Croce croce = Croce.findBySigla(CROCE_DEMO)
        Date primoGennaio2013 = Lib.creaData1Gennaio()
        def listaTipoTurno
        def listaTurni

        if (!croce) {
            return
        }// fine del blocco if

        listaTurni = Turno.findAllByCroceAndGiornoGreaterThan(croce, primoGennaio2013)
        listaTipoTurno = TipoTurno.findAllByCroce(croce, [sort: "ordine", order: "asc"])
        if (listaTurni.size() < 100) {
            if (listaTipoTurno) {
                for (int k = 0; k < 365; k++) {
                    listaTipoTurno.each {
                        creaTurnoVuotoDemo(croce, it, k)
                    } // fine del ciclo each
                } // fine del ciclo for
            }// fine del blocco if
        }// fine del blocco if
    }// fine del metodo

    //--creazione dei turni vuoti per il 2013
    //--li crea SOLO se non esistono già
    private static void turni2013RossaFidenza() {
        Croce croce = Croce.findBySigla(CROCE_ROSSA_FIDENZA)
        Date primoGennaio2013 = Lib.creaData1Gennaio()
        def listaTipoTurno
        def listaTurni

        if (!croce) {
            return
        }// fine del blocco if

        if (!SVILUPPO_CROCE_ROSSA_FIDENZA) {
            return
        }// fine del blocco if

        listaTurni = Turno.findAllByCroceAndGiornoGreaterThan(croce, primoGennaio2013)
        listaTipoTurno = TipoTurno.findAllByCroce(croce, [sort: "ordine", order: "asc"])
        if (listaTurni.size() < 100) {
            if (listaTipoTurno) {
                for (int k = 0; k < 365; k++) {
                    listaTipoTurno.each {
                        creaTurnoVuotoRossa(croce, it, k)
                    } // fine del ciclo each
                } // fine del ciclo for
            }// fine del blocco if
        }// fine del blocco if
    }// fine del metodo

    private static creaTurnoVuotoRossa(Croce croce, tipoTurno, delta) {
        Date primoGennaio2013 = Lib.creaData1Gennaio()
        Date giorno
        int settimana
        String sigla = tipoTurno.sigla

        giorno = primoGennaio2013 + delta
        settimana = Lib.getNumSettimana(giorno)

        if (sigla.equals('msa-mat') || sigla.equals('msa-pom') || sigla.equals('msa-notte') || sigla.equals('amb-notte')) {
            Lib.creaTurno(croce, tipoTurno, giorno)
        }// fine del blocco if

        //-- sabato=7, domenica=1
        if (sigla.equals('amb-mat') || sigla.equals('amb-pom')) {
            if (settimana == 7 || settimana == 1) {
                Lib.creaTurno(croce, tipoTurno, giorno)
            }// fine del blocco if
        }// fine del blocco if

    }// fine del metodo

    private static creaTurnoVuotoDemo(Croce croce, tipoTurno, delta) {
        Date primoGennaio2013 = Lib.creaData1Gennaio()
        Date giorno
        int settimana
        String sigla = tipoTurno.sigla

        giorno = primoGennaio2013 + delta
        settimana = Lib.getNumSettimana(giorno)

        //-- tutti i giorni
        if (sigla.equals('mat') || sigla.equals('pom')) {
            Lib.creaTurno(croce, tipoTurno, giorno)
        }// fine del blocco if

        //-- venerdi(6) notte e sabato(7) notte
        if (sigla.equals('notte')) {
            if (settimana == 6 || settimana == 7) {
                Lib.creaTurno(croce, tipoTurno, giorno)
            }// fine del blocco if
        }// fine del blocco if
    }// fine del metodo


    private void creaTurniVuoti2013() {
        Date primoGennaio = Lib.creaData1GennaioNextYear();
        Date primoInizio = Lib.creaData1GennaioNextYear();
        Date primaFine = Lib.creaData1GennaioNextYear();
        def turni2013 = Turno.findAllByGiornoGreaterThan(primoGennaio);
        def turni = TipoTurno.findAll([sort: "ordine", order: "asc"])
        Date giorno = null
        Date inizio = null
        Date fine = null
        boolean creaTurno
        TipoTurno tipoTurno
        int giornoProgressivoAnno

        if (turni2013.size() < 1) {
            for (int k = 0; k < 365; k++) {
                turni?.each {
                    tipoTurno = it
                    creaTurno = true
                    giorno = primoGennaio + k
                    inizio = primoInizio + k
                    fine = primaFine + k

                    inizio = Lib.setOra(inizio, tipoTurno.getOraInizio())
                    fine = Lib.setOra(fine, tipoTurno.getOraFine())

                    // turni avis, extra e centralino creati solo on-demand
                    if (it.sigla.equals(Turni.avis.sigla) || it.sigla.equals(Turni.extra.sigla) || it.sigla.equals(Turni.centralino.sigla)) {
                        creaTurno = false
                    }// fine del blocco if

                    // domenica niente ordinario mattina e pomeriggio
                    if (it.sigla.equals(Turni.ordMat.sigla) || it.sigla.equals(Turni.ordPom.sigla)) {
                        if (giorno.day == 0) {
                            creaTurno = false
                        }// fine del blocco if
                    }// fine del blocco if

                    //durata del turno notturno venerdì e sabato sera
                    if (it.sigla.equals(Turni.s118Sera.sigla)) {
                        //sposta dalle ore zero (mezzanotte) alle 8 del mattino
                        if (giorno.day == 5 || giorno.day == 6) {
                            fine = Lib.setOra(fine, 8)
                        }// fine del blocco if
                    }// fine del blocco if

                    //giorni festivi oltre a sabato e domenica
                    // 6 gennaio epifania               = 6  domenica
                    // 10 febbraio carnevale            = 41 domenica
                    // 31 marzo pasqua                  = 90 domenica
                    // 1° aprile pasquetta              = 91 lunedì
                    // 25 aprile liberazione            = 115 giovedì
                    // 1° maggio festa del lavoro       = 121 mercoledì
                    // 2 giugno festa della repubblica  = 153 domenica
                    // 15 agosto ferragosto             = 227 giovedì
                    // 1 novembre ognissanti            = 305 venerdì
                    // 8 dicembre immacolata            = 342 domenica
                    // 25 dicembre natale               = 359 mercoledì
                    // 26 dicembre santo stefano        = 360 giovedì
                    // 1 gennaio (attenzione al cambio di anno)
                    if (it.sigla.equals(Turni.s118Sera.sigla)) {
                        //sposta dalle ore zero (mezzanotte) alle 8 del mattino
                        giornoProgressivoAnno = Lib.getGiornoAnno(giorno)
                        if (Festivi2013.isFestivo(giornoProgressivoAnno)) {
                            fine = Lib.setOra(fine, 8)
                        }// fine del blocco if
                    }// fine del blocco if

                    if (creaTurno) {
                        Turno nuovoTurno = new Turno(giorno: giorno, tipoTurno: it, inizio: inizio, fine: fine).save(failOnError: true)
                    }// fine del blocco if

                } // fine del ciclo for
            }
        } else {

        }// fine del blocco if-else
    }// fine del metodo

    //--crea un utente con croce, ruolo, nick e password indicati
    //--crea la tavola di incrocio
    //--lo crea SOLO se non esiste già
    //--senza collegamento ad un milite
    private static Utente newUtente(String siglaCroce, String siglaRuolo, String nick, String password) {
        return newUtente(siglaCroce, siglaRuolo, nick, password, null)
    }// fine del metodo

    //--crea un utente con croce, ruolo, nick e password indicati
    //--crea la tavola di incrocio
    //--lo crea SOLO se non esiste già
    private static Utente newUtente(String siglaCroce, String siglaRuolo, String nick, String password, Milite milite) {
        Utente utente = null
        Croce croce = Croce.findBySigla(siglaCroce)
        Ruolo ruolo = Ruolo.findByAuthority(siglaRuolo)

        if (nick && croce && ruolo) {
            utente = Utente.findOrCreateByNickname(nick)
            utente.croce = croce
            utente.username = nick + '/' + croce.sigla.toLowerCase()
            utente.password = password
            utente.pass = password
            utente.enabled = true
            if (milite) {
                utente.milite = milite
            }// fine del blocco if

            utente.save(flush: true)
            if (utente) {
                UtenteRuolo.findOrCreateByRuoloAndUtente(ruolo, utente).save(failOnError: true)
            }// fine del blocco if
        }// fine del blocco if

        return utente
    }// fine del metodo

    //--crea un utente (ruolo Milite) con croce, nick e password indicati
    //--crea la tavola di incrocio
    //--lo crea SOLO se non esiste già
    private static void newUtenteMilite(String siglaCroce, Milite milite) {
        String nome
        String cognome
        String nick
        String password

        nome = milite.nome.trim()
        cognome = milite.cognome.trim()
        nick = cognome + ' ' + nome
        password = cognome.toLowerCase() + '123'
        newUtente(siglaCroce, ROLE_MILITE, nick, password, milite)
    }// fine del metodo

    //--crea una versione
    //--lo crea SOLO se non esiste già
    private static void newVersione(String siglaCroce, String titolo, String descrizione) {
        Versione versione
        int numero = getVersione() + 1
        Date giorno = new Date()
        Croce croce = Croce.findBySigla(siglaCroce)

        versione = new Versione()
        versione.numero = numero
        versione.croce = croce
        versione.giorno = giorno
        versione.titolo = titolo
        versione.descrizione = descrizione
        versione.save(flush: true)

    }// fine del metodo

    //--crea una versione
    //--lo crea SOLO se non esiste già
    private static void newVersione(String titolo, String descrizione) {
        newVersione(CROCE_ALGOS, titolo, descrizione)
    }// fine del metodo

    //--crea una versione
    //--lo crea SOLO se non esiste già
    private static void newVersione(String titolo) {
        newVersione(titolo, '')
    }// fine del metodo

    //--modifica di un turno in CRF
    //--richiesta di spostare dalle 7 alle 6 l'inizio turno ambulanza mattino a Fidenza
    //--esegue la modifica SOLO per i turni NON effettuati
    private static modificaTurnoFidenza() {
        Croce croce = Croce.findBySigla(CROCE_ROSSA_FIDENZA)
        String siglaTurnoMattino = 'amb-mat'
        String siglaTurnoNotte = 'amb-notte'
        TipoTurno turnoMattino
        TipoTurno turnoNotte
        def listaTurniMattina
        def listaTurniNotte
        Date inizio
        Date fine

        turnoMattino = TipoTurno.findByCroceAndSigla(croce, siglaTurnoMattino)
        turnoNotte = TipoTurno.findByCroceAndSigla(croce, siglaTurnoNotte)

        if (turnoMattino && turnoNotte) {
            //--modifico il turno richiesto
            turnoMattino.oraInizio = 6
            turnoMattino.oraFine = 13
            turnoMattino.durata = 7
            turnoMattino.save(flush: true)

            //--modifico il turno correlato
            turnoNotte.oraInizio = 20
            turnoNotte.oraFine = 6
            turnoNotte.durata = 10
            turnoNotte.save(flush: true)

            //--modifico tutti i turni già esistenti che NON hanno ancora militi segnati
            listaTurniMattina = Turno.findAllByTipoTurnoAndMiliteFunzione1AndMiliteFunzione2AndMiliteFunzione3AndMiliteFunzione4(turnoMattino, null, null, null, null)
            listaTurniMattina?.each {
                inizio = it.inizio
                fine = it.fine
                it.inizio = Lib.setOra(inizio, turnoMattino.oraInizio)
                it.fine = Lib.setOra(fine, turnoMattino.oraFine)
                it.save(flush: true)
            } // fine del ciclo each
            listaTurniNotte = Turno.findAllByTipoTurnoAndMiliteFunzione1AndMiliteFunzione2AndMiliteFunzione3AndMiliteFunzione4(turnoNotte, null, null, null, null)
            listaTurniNotte?.each {
                inizio = it.inizio
                fine = it.fine
                it.inizio = Lib.setOra(inizio, turnoNotte.oraInizio)
                it.fine = Lib.setOra(fine, turnoNotte.oraFine)
                it.save(flush: true)
            } // fine del ciclo each

            newVersione(CROCE_ROSSA_FIDENZA, 'Turno mattino', 'Modifica inizio turno ambulanza mattino: dalle ore 7 alle 6. Cambio del tipoTurno per tutti i turni non ancora effettuati.')
        }// fine del blocco if
    }// fine del metodo

    //--aggiunta campo (visibile) nickname alla tavola Utente
    //--lo crea SOLO se non esiste già
    private static void nickUtenteRossaFidenza() {
        Utente utente
        Croce croce = Croce.findBySigla(CROCE_ROSSA_FIDENZA)
        def lista = Utente.findAllByCroce(croce)
        String tagAggiuntivo = '/crf'
        String username
        String nick

        lista?.each {
            utente = (Utente) it
            username = utente.username
            nick = username
            if (!username.endsWith(tagAggiuntivo)) {
                username += tagAggiuntivo
            }// fine del blocco if
            utente.username = username
            utente.nickname = nick
            utente.save(flush: true)
        } // fine del ciclo each

        newVersione(CROCE_ROSSA_FIDENZA, 'Username', 'Aggiunto il suffisso /crf')
    }// fine del metodo

    //--elimina alcuni utenti e regola il nick
    private static void fixSecurityAlgos() {
        Croce croce = Croce.findBySigla(CROCE_ALGOS)
        def listaUtenti = Utente.findAllByCroce(croce)
        Utente utente
        String tagProg = 'gac'
        String tagAlgos = tagProg + '/' + croce.sigla.toLowerCase()
        def lista

        //--cancella tutti meno uno
        listaUtenti.each {
            utente = (Utente) it
            if (!utente.username.equals(tagProg)) {
                lista = UtenteRuolo.findAllByUtente(utente)
                lista?.each {
                    it.delete(flush: true)
                } // fine del ciclo each
                utente.delete(flush: true)
            }// fine del blocco if
        } // fine del ciclo each

        //--regola il nick dell'unico accesso rimasto
        utente = Utente.findByUsername(tagProg)
        if (utente) {
            utente.username = tagAlgos
            utente.nickname = tagProg
            utente.save(flush: true)
        }// fine del blocco if

        newVersione(CROCE_ALGOS, 'Security algos', 'Elimina alcuni utenti e regola il nick')
    }// fine del metodo

    //--aggiunge un flag a tutti i tipi di turno esistenti
    //--il flag serve per separare visivamente i vari turni all'interno del tabellone
    private static void fixUltimoTipoTurno() {
        Croce croce
        def lista
        TipoTurno tipoTurno

        //--demo
        resetUltimoTipoTurno(CROCE_DEMO)

        //--tidone
        resetUltimoTipoTurno(CROCE_PUBBLICA_CASTELLO)

        //--fidenza
        resetUltimoTipoTurno(CROCE_ROSSA_FIDENZA)
        ultimoTipoTurno('msa-notte')
        ultimoTipoTurno('amb-notte')

        //--pontetaro
        resetUltimoTipoTurno(CROCE_ROSSA_PONTETARO)
        ultimoTipoTurno('118-notte')
        ultimoTipoTurno('dia-2r')

        newVersione(CROCE_ALGOS, 'TipoTurno', 'Aggiunge un flag a tutti i tipi di turni esistenti. Serve per separare visivamente i vari turni nel tabellone.')
    }// fine del metodo

    //--aggiunge un flag a tutti i tipi di turno esistenti
    //--il flag serve per separare visivamente i vari turni all'interno del tabellone
    private static void resetUltimoTipoTurno(String siglaCroce) {
        Croce croce = Croce.findBySigla(siglaCroce)
        def lista
        TipoTurno tipoTurno

        if (croce) {
            lista = TipoTurno.findAllByCroce(croce)
            lista?.each {
                tipoTurno = (TipoTurno) it
                tipoTurno.ultimo = false
                tipoTurno.save(flush: true)
            } // fine del ciclo each
        }// fine del blocco if
    }// fine del metodo

    //--aggiunge un flag a tutti i tipi di turno esistenti
    //--il flag serve per separare visivamente i vari turni all'interno del tabellone
    private static void ultimoTipoTurno(String siglaTipoTurno) {
        TipoTurno tipoTurno = TipoTurno.findBySigla(siglaTipoTurno)

        if (tipoTurno) {
            tipoTurno.ultimo = true
            tipoTurno.save(flush: true)
        }// fine del blocco if
    }// fine del metodo

    //--modifica di un turno in CRF
    //--richiesta di spostare dalle 7 alle 6 l'inizio turno ambulanza mattino a Fidenza
    //--completa la modifica ANCHE per i turni effettuati
    private static modificaTurnoFidenzaEffettuati() {
        Croce croce = Croce.findBySigla(CROCE_ROSSA_FIDENZA)
        String siglaTurnoMattino = 'amb-mat'
        String siglaTurnoNotte = 'amb-notte'
        TipoTurno turnoMattino
        TipoTurno turnoNotte
        def listaTurniMattina
        def listaTurniNotte
        Date inizio
        Date fine

        turnoMattino = TipoTurno.findByCroceAndSigla(croce, siglaTurnoMattino)
        turnoNotte = TipoTurno.findByCroceAndSigla(croce, siglaTurnoNotte)

        if (turnoMattino && turnoNotte) {
            //--modifico tutti i turni già esistenti che NON hanno ancora militi segnati
            listaTurniMattina = Turno.findAllByTipoTurno(turnoMattino)
            listaTurniMattina?.each {
                inizio = it.inizio
                fine = it.fine
                it.inizio = Lib.setOra(inizio, turnoMattino.oraInizio)
                it.fine = Lib.setOra(fine, turnoMattino.oraFine)
                it.save(flush: true)
            } // fine del ciclo each
            listaTurniNotte = Turno.findAllByTipoTurno(turnoNotte)
            listaTurniNotte?.each {
                inizio = it.inizio
                fine = it.fine
                it.inizio = Lib.setOra(inizio, turnoNotte.oraInizio)
                it.fine = Lib.setOra(fine, turnoNotte.oraFine)
                it.save(flush: true)
            } // fine del ciclo each

            newVersione(CROCE_ROSSA_FIDENZA, 'Turno mattino', 'Ulteriore cambio del tipoTurno per tutti i turni già ancora effettuati.')
        }// fine del blocco if
    }// fine del metodo

    //--modifica dei tipi di turno in CRPT
    //--modifica il numero di funzioniObbligatorie per i turni di dialisi
    //--aggiunge un nuovo tipo di turno ''Ordinario'' e modifica in parte quello esistente
    //--aggiunge un tipo di turno ''TurnoExtra'' per spezzare i turni di ambulanza
    private static void modificaTurniPontetaro() {
        Croce croce = Croce.findBySigla(CROCE_ROSSA_PONTETARO)
        TipoTurno dialisiUnoAndata
        TipoTurno dialisiUnoRitorno
        TipoTurno dialisiDueAndata
        TipoTurno dialisiDueRitorno
        TipoTurno ordinario
        TipoTurno nuovoOrdinarioDoppio

        //--modifica il numero di funzioniObbligatorie per i turni di dialisi
        //--dialisi 1, andata e ritorno, hanno solo 1 Milite obbligatorio
        dialisiUnoAndata = TipoTurno.findByCroceAndSigla(croce, CRPT_TIPO_TURNO_DIALISI_UNO_ANDATA)
        if (dialisiUnoAndata) {
            dialisiUnoAndata.funzioniObbligatorie = 1
            dialisiUnoAndata.save(flush: true)
        }// fine del blocco if
        dialisiUnoRitorno = TipoTurno.findByCroceAndSigla(croce, CRPT_TIPO_TURNO_DIALISI_UNO_RITORNO)
        if (dialisiUnoRitorno) {
            dialisiUnoRitorno.funzioniObbligatorie = 1
            dialisiUnoRitorno.save(flush: true)
        }// fine del blocco if

        //--modifica il numero di funzioniObbligatorie per i turni di dialisi
        //--dialisi 2, andata e ritorno, hanno 2 Militi obbligatori
        dialisiDueAndata = TipoTurno.findByCroceAndSigla(croce, CRPT_TIPO_TURNO_DIALISI_DUE_ANDATA)
        if (dialisiDueAndata) {
            dialisiDueAndata.funzioniObbligatorie = 2
            dialisiDueAndata.save(flush: true)
        }// fine del blocco if
        dialisiDueRitorno = TipoTurno.findByCroceAndSigla(croce, CRPT_TIPO_TURNO_DIALISI_DUE_RITORNO)
        if (dialisiDueRitorno) {
            dialisiDueRitorno.funzioniObbligatorie = 2
            dialisiDueRitorno.save(flush: true)
        }// fine del blocco if

        //--modifica il numero di funzioniObbligatorie per il tipo di turno ordinario
        //--ordinario ha solo 1 Milite obbligatorio
        ordinario = TipoTurno.findByCroceAndSigla(croce, CRPT_TIPO_TURNO_ORDINARIO_OLD)
        if (ordinario) {
            ordinario.funzioniObbligatorie = 1
            ordinario.save(flush: true)
        }// fine del blocco if

        //--modifica la sigla e la descrizione per il tipo di turno ordinario
        ordinario = TipoTurno.findByCroceAndSigla(croce, CRPT_TIPO_TURNO_ORDINARIO_OLD)
        if (ordinario) {
            ordinario.sigla = CRPT_TIPO_TURNO_ORDINARIO_SINGOLO
            ordinario.descrizione = 'Ordinario singolo'
            ordinario.save(flush: true)
        }// fine del blocco if

        //--crea un nuovo tipo di turno ordinario
        newTipoTurnoCRPT(CRPT_TIPO_TURNO_ORDINARIO_DOPPIO, 'Ordinario doppio', 9, 0, 0, false, true, false, true, 2, funzCRPT[1], funzCRPT[3], funzCRPT[4], null)

        //--crea un nuovo tipo di turno extra per spezzare i turni di ambulanza se necessario
        newTipoTurnoCRPT(CRPT_TIPO_TURNO_EXTRA, 'Extra ambulanza', 10, 0, 0, false, true, true, true, 3, funzCRPT[0], funzCRPT[2], funzCRPT[3], funzCRPT[4])

        newVersione(CROCE_ROSSA_PONTETARO, 'Tipi turni', 'Funzioni obbligatorie dialisi, raddoppio turni ordinari e nuyovi turni extra per spezzare i turni Ambulanza.')
    }// fine del metodo

    //--elimina tutti gli utenti programmatori eccetto uno
    //--ce ne dovrebbero essere 3. Uno lo mantiene (il primo) e cancella gli altri due
    private static void fixProgrammatori() {
        Utente utenteProg
        Utente utente
        long utenteProgId = 0
        long utenteId = 0
        String oldTag = 'gac'
        Ruolo ruoloProg = Ruolo.findByAuthority(ROLE_PROG)
        UtenteRuolo uteRole
        def listaUtenteRuolo
        def listaProg
        def lista

        //--primo che rimane
        //--fix username
        //--fix nickname
        utenteProg = Utente.findByNickname(oldTag)
        if (utenteProg) {
            utenteProgId = utenteProg.id
            utenteProg.nickname = PROG_NICK
            utenteProg.save(flush: true)
            utenteProg.username = PROG_USERNAME
            utenteProg.save(flush: true)
        }// fine del blocco if

        //--recupera la lista dei programmatori e li elimina tutti tranne uno
        //--recupera la lista dell'incrocio utente/ruolo e li elimina tutti tranne uno
        listaProg = Utente.findAllByPass(PROG_PASS)
        listaProg?.each {
            utente = (Utente) it
            utenteId = utente.id
            if (utenteId != utenteProgId) {

                //--elimina i records di incrocio
                listaUtenteRuolo = UtenteRuolo.findAllByUtente(utente)
                listaUtenteRuolo?.each {
                    it.delete(flush: true)
                } // fine del ciclo each

                //--elimina eventuali riferimenti all'utente prima di poterlo cancellare
                lista = Logo.findAllByUtente(utente)
                lista?.each {
                    it.utente = null
                    it.save(flush: true)
                } // fine del ciclo each

                //--camncella l'utente
                utente.delete(flush: true)
            }// fine del blocco if
        } // fine del ciclo each

        newVersione(CROCE_ALGOS, 'Security prog', 'Regola username e nick di un programmatore ed elimina tutti gli altri.')
    }// fine del metodo

    //--patch ai tipi di turno in CRPT
    //-- sostituzione nei turni dia-2a e dia-2r della 2° funzione bar in soc
    private static void fixTurniPontetaro() {
        Croce croce = Croce.findBySigla(CROCE_ROSSA_PONTETARO)
        TipoTurno dialisiDueAndata
        TipoTurno dialisiDueRitorno
        TipoTurno ordinarioSingolo
        TipoTurno ordinarioDoppio
        TipoTurno extraAmbulanza
        Funzione aut118 = Funzione.findByCroceAndSigla(croce, CRPT_FUNZIONE_AUT_118)
        Funzione autOrd = Funzione.findByCroceAndSigla(croce, CRPT_FUNZIONE_AUT_ORD)
        Funzione dae = Funzione.findByCroceAndSigla(croce, CRPT_FUNZIONE_DAE)
        Funzione soccorritore = Funzione.findByCroceAndSigla(croce, CRPT_FUNZIONE_SOC)
        Funzione barelliere = Funzione.findByCroceAndSigla(croce, CRPT_FUNZIONE_BAR)

        //-- sostituzione nei turni dia-2a e dia-2r della 2° funzione bar in soc
        dialisiDueAndata = TipoTurno.findByCroceAndSigla(croce, CRPT_TIPO_TURNO_DIALISI_DUE_ANDATA)
        if (dialisiDueAndata && soccorritore) {
            dialisiDueAndata.funzione2 = soccorritore
            dialisiDueAndata.save(flush: true)
        }// fine del blocco if
        dialisiDueRitorno = TipoTurno.findByCroceAndSigla(croce, CRPT_TIPO_TURNO_DIALISI_DUE_RITORNO)
        if (dialisiDueRitorno && soccorritore) {
            dialisiDueRitorno.funzione2 = soccorritore
            dialisiDueRitorno.save(flush: true)
        }// fine del blocco if

        //-- sostituzione nel turno ordinario singolo della 2° funzione soc in bar
        ordinarioSingolo = TipoTurno.findByCroceAndSigla(croce, CRPT_TIPO_TURNO_ORDINARIO_SINGOLO)
        if (ordinarioSingolo && barelliere) {
            ordinarioSingolo.funzione2 = barelliere
            ordinarioSingolo.save(flush: true)
        }// fine del blocco if

        //-- aggiunta delle funzioni nel turno ordinario doppio (mancavano)
        ordinarioDoppio = TipoTurno.findByCroceAndSigla(croce, CRPT_TIPO_TURNO_ORDINARIO_DOPPIO)
        if (ordinarioDoppio && autOrd && soccorritore && barelliere) {
            ordinarioDoppio.funzione1 = autOrd
            ordinarioDoppio.funzione2 = soccorritore
            ordinarioDoppio.funzione3 = barelliere
            ordinarioDoppio.save(flush: true)
        }// fine del blocco if

        //-- aggiunta delle funzioni nel turno extra ambulanza (mancavano)
        //-- modifica del flag orario
        extraAmbulanza = TipoTurno.findByCroceAndSigla(croce, CRPT_TIPO_TURNO_EXTRA)
        if (ordinarioDoppio && aut118 && dae && soccorritore && barelliere) {
            extraAmbulanza.funzione1 = aut118
            extraAmbulanza.funzione2 = dae
            extraAmbulanza.funzione3 = soccorritore
            extraAmbulanza.funzione4 = barelliere
            extraAmbulanza.orario = false
            extraAmbulanza.save(flush: true)
        }// fine del blocco if

        newVersione(CROCE_ROSSA_PONTETARO, 'Patch tipi turni', 'Modificata seconda funzione e orario in alcuni tipi di turno.')
    }// fine del metodo

    //--ulteriore patch ai tipi di turno in CRPT
    //--sostituzione nei turni dia-1a e dia-1r  e ordinario singolo della 2° funzione bar in soc
    private static void fixTurniPontetaroUlteriore() {
        Croce croce = Croce.findBySigla(CROCE_ROSSA_PONTETARO)
        TipoTurno dialisiUnoAndata
        TipoTurno dialisiUnoRitorno
        TipoTurno ordinarioSingolo
        Funzione soccorritore = Funzione.findByCroceAndSigla(croce, Cost.CRPT_FUNZIONE_SOC)

        //-- sostituzione nei turni dia-1a e dia-1r della 2° funzione bar in soc
        dialisiUnoAndata = TipoTurno.findByCroceAndSigla(croce, CRPT_TIPO_TURNO_DIALISI_UNO_ANDATA)
        if (dialisiUnoAndata && soccorritore) {
            dialisiUnoAndata.funzione2 = soccorritore
            dialisiUnoAndata.save(flush: true)
        }// fine del blocco if
        dialisiUnoRitorno = TipoTurno.findByCroceAndSigla(croce, CRPT_TIPO_TURNO_DIALISI_UNO_RITORNO)
        if (dialisiUnoRitorno && soccorritore) {
            dialisiUnoRitorno.funzione2 = soccorritore
            dialisiUnoRitorno.save(flush: true)
        }// fine del blocco if

        //-- sostituzione nel turno ordinario singolo della 2° funzione bar in soc
        ordinarioSingolo = TipoTurno.findByCroceAndSigla(croce, CRPT_TIPO_TURNO_ORDINARIO_SINGOLO)
        if (ordinarioSingolo && ordinarioSingolo) {
            ordinarioSingolo.funzione2 = soccorritore
            ordinarioSingolo.save(flush: true)
        }// fine del blocco if

        newVersione(CROCE_ROSSA_PONTETARO, 'Patch tipi turni', 'Modificata seconda funzione in alcuni tipi di turno.')
    }// fine del metodo

    //--spostamento in alto (dopo i 3 turni di ambulanza) del turno extra in CRPT
    //--modifica l'ordine di apparizione di tutti i turni
    private static void fixExtraPontetaro() {
        Croce croce = Croce.findBySigla(CROCE_ROSSA_PONTETARO)
        TipoTurno tipoTurno
        TipoTurno ambulanzaNotte
        TipoTurno extra

        //--modifica l'ordine di apparizione di tutti i turni
        ordineTurnoCRPT(CRPT_TIPO_TURNO_AMBULANZA_MATTINO, 1)
        ordineTurnoCRPT(CRPT_TIPO_TURNO_AMBULANZA_POMERIGGIO, 2)
        ordineTurnoCRPT(CRPT_TIPO_TURNO_AMBULANZA_NOTTE, 3)
        ordineTurnoCRPT(CRPT_TIPO_TURNO_EXTRA, 4)
        ordineTurnoCRPT(CRPT_TIPO_TURNO_DIALISI_UNO_ANDATA, 5)
        ordineTurnoCRPT(CRPT_TIPO_TURNO_DIALISI_UNO_RITORNO, 6)
        ordineTurnoCRPT(CRPT_TIPO_TURNO_DIALISI_DUE_ANDATA, 7)
        ordineTurnoCRPT(CRPT_TIPO_TURNO_DIALISI_DUE_RITORNO, 8)
        ordineTurnoCRPT(CRPT_TIPO_TURNO_ORDINARIO_SINGOLO, 9)
        ordineTurnoCRPT(CRPT_TIPO_TURNO_ORDINARIO_DOPPIO, 10)

        //--sposta la barra blu sotto l'extra
        ambulanzaNotte = TipoTurno.findByCroceAndSigla(croce, CRPT_TIPO_TURNO_AMBULANZA_NOTTE)
        if (ambulanzaNotte) {
            ambulanzaNotte.ultimo = false
            ambulanzaNotte.save(flush: true)
        }// fine del blocco if
        extra = TipoTurno.findByCroceAndSigla(croce, CRPT_TIPO_TURNO_EXTRA)
        if (extra) {
            extra.ultimo = true
            extra.save(flush: true)
        }// fine del blocco if

        newVersione(CROCE_ROSSA_PONTETARO, 'Posizione extra', 'Spostato in alto il turno extra, dopo i 3 turni di ambulnza.')
    }// fine del metodo

    //--aggiunge 3 funzioni per i servizi di sede a CRPT
    private static void addFunzioniSedeCRPT() {
        newFunzRossaPonteTaro(CRPT_FUNZIONE_CENTRALINO, 'Cent', 'Centralino', 6, '')
        newFunzRossaPonteTaro(CRPT_FUNZIONE_PULIZIE, 'Pul', 'Pulizie', 7, '')
        newFunzRossaPonteTaro(CRPT_FUNZIONE_UFFICIO, 'Uff', 'Ufficio', 8, '')

        newVersione(CROCE_ROSSA_PONTETARO, 'Funzioni sede', 'Aggiunta di 3 funzioni per la sede.')
    }// fine del metodo

    //--aggiunge un tipo di turno a CRPT
    private static void addServiziSedeCRPT() {
        Croce croce = Croce.findBySigla(CROCE_ROSSA_PONTETARO)
        Funzione centralino = Funzione.findByCroceAndSigla(croce, CRPT_FUNZIONE_CENTRALINO)
        Funzione pulizie = Funzione.findByCroceAndSigla(croce, CRPT_FUNZIONE_PULIZIE)
        Funzione ufficio = Funzione.findByCroceAndSigla(croce, CRPT_FUNZIONE_UFFICIO)

        newTipoTurnoCRPT(CRPT_TIPO_TURNO_SERVIZI, 'Servizi sede', 11, 0, 0, false, true, false, false, 1, centralino, pulizie, ufficio, null)

        newVersione(CROCE_ROSSA_PONTETARO, 'Servizi sede', 'Aggiunta di un tipo di turno per le funzioni della sede.')
    }// fine del metodo

    //--flag ai militi dei servizi ufficio nella CRPT
    private static void flagMilitiServiziCRPT() {
        Croce croce = Croce.findBySigla(CROCE_ROSSA_PONTETARO)
        Funzione centralino = Funzione.findByCroceAndSigla(croce, CRPT_FUNZIONE_CENTRALINO)
        Funzione pulizie = Funzione.findByCroceAndSigla(croce, CRPT_FUNZIONE_PULIZIE)
        Funzione ufficio = Funzione.findByCroceAndSigla(croce, CRPT_FUNZIONE_UFFICIO)
        Milite milite

        //--centralino
        milite = Milite.findByCroceAndCognome(croce, 'Scafaro')
        if (milite && centralino) {
            Militefunzione.findOrCreateByCroceAndMiliteAndFunzione(croce, milite, centralino).save(flush: true)
        }// fine del blocco if
        milite = Milite.findByCroceAndCognome(croce, 'Bravi')
        if (milite && centralino) {
            Militefunzione.findOrCreateByCroceAndMiliteAndFunzione(croce, milite, centralino).save(flush: true)
        }// fine del blocco if
        milite = Milite.findByCroceAndCognome(croce, 'Ricci')
        if (milite && centralino) {
            Militefunzione.findOrCreateByCroceAndMiliteAndFunzione(croce, milite, centralino).save(flush: true)
        }// fine del blocco if
        milite = Milite.findByCroceAndCognome(croce, 'Beatrizzotti')
        if (milite && centralino) {
            Militefunzione.findOrCreateByCroceAndMiliteAndFunzione(croce, milite, centralino).save(flush: true)
        }// fine del blocco if

        //--pulizie
        milite = Milite.findByCroceAndCognome(croce, 'Fornia')
        if (milite && pulizie) {
            Militefunzione.findOrCreateByCroceAndMiliteAndFunzione(croce, milite, pulizie).save(flush: true)
        }// fine del blocco if
        milite = Milite.findByCroceAndCognome(croce, 'Pettenati')
        if (milite && pulizie) {
            Militefunzione.findOrCreateByCroceAndMiliteAndFunzione(croce, milite, pulizie).save(flush: true)
        }// fine del blocco if

        //--ufficio
        milite = Milite.findByCroceAndCognome(croce, 'Carraglia')
        if (milite && ufficio) {
            Militefunzione.findOrCreateByCroceAndMiliteAndFunzione(croce, milite, ufficio).save(flush: true)
        }// fine del blocco if
        milite = Milite.findByCroceAndCognome(croce, 'Betti')
        if (milite && ufficio) {
            Militefunzione.findOrCreateByCroceAndMiliteAndFunzione(croce, milite, ufficio).save(flush: true)
        }// fine del blocco if

        newVersione(CROCE_ROSSA_PONTETARO, 'Servizi sede', 'Flag per i militi abilitati ai servizi di sede.')
    }// fine del metodo

    //--modifica l'ordine di apparizione del turno
    private static void ordineTurnoCRPT(String siglaTurno, int ordine) {
        Croce croce = Croce.findBySigla(CROCE_ROSSA_PONTETARO)
        TipoTurno tipoTurno

        tipoTurno = TipoTurno.findByCroceAndSigla(croce, siglaTurno)
        if (tipoTurno) {
            tipoTurno.ordine = ordine
            tipoTurno.save(flush: true)
        }// fine del blocco if
    }// fine del metodo

    private void resetTurniPontetaro() {
        Croce croce = Croce.findBySigla(CROCE_ROSSA_PONTETARO)

        this.cancellaSingolaTavola('Logo')
        def turni = Turno.findAllByCroce(croce)
        turni?.each {
            it.delete(flush: true)
        } // fine del ciclo each

    }// fine del metodo

    //--aggiunge il controller iniziale che mancava
    private static void fixControllerInizialePubblicaCastello() {
        Croce croce = Croce.findBySigla(CROCE_PUBBLICA_CASTELLO)
        Settings setting

        if (croce) {
            setting = croce.settings
        }// fine del blocco if

        if (setting) {
            if (setting.startController == '') {
                setting.startController = 'turno'
                setting.save(flush: true)
            }// fine del blocco if
        }// fine del blocco if

        newVersione(CROCE_PUBBLICA_CASTELLO, 'Inizio', 'Fix controller iniziale mancante.')
    }// fine del metodo

    //--creazione dei record utenti per la pubblica castello
    //--uno per ogni milite
    //--nickname=cognomeNome
    //--password=cognome(minuscolo) + 3 cifre numeriche
    //--li crea SOLO se non esistono già
    private static void utentiPubblicacastello() {
        Croce croce = Croce.findBySigla(CROCE_PUBBLICA_CASTELLO)
        ArrayList listaUtenti
        ArrayList listaMiliti
        Milite milite

        if (!croce) {
            return
        }// fine del blocco if

        listaUtenti = Utente.findAllByCroce(croce)
        if (listaUtenti && listaUtenti.size() < 1) {
            listaMiliti = Milite.findAllByCroce(croce)
            listaMiliti?.each {
                milite = (Milite) it
                newUtenteMilite(CROCE_ROSSA_FIDENZA, milite)
            } // fine del ciclo each
        }// fine del blocco if
    }// fine del metodo

    //--regola il (nuovo) flag per tutte le croci
    private static void flagModuloViaggi() {

        regolaFlagModuloViaggiSingolaCroce(CROCE_ALGOS, true)
        regolaFlagModuloViaggiSingolaCroce(CROCE_DEMO, false)
        regolaFlagModuloViaggiSingolaCroce(CROCE_PUBBLICA_CASTELLO, false)
        regolaFlagModuloViaggiSingolaCroce(CROCE_ROSSA_FIDENZA, false)
        regolaFlagModuloViaggiSingolaCroce(CROCE_ROSSA_PONTETARO, true)

        newVersione(CROCE_ALGOS, 'Moduli', 'Modulo viaggi per le varie croci.')
    }// fine del metodo

    //--regola il (nuovo) flag per tutte le croci
    private static void regolaFlagModuloViaggiSingolaCroce(String siglaCroce, boolean usaModuloViaggi) {
        Croce croce
        Settings setting

        croce = Croce.findBySigla(siglaCroce)
        if (croce) {
            setting = croce.settings
            if (setting) {
                setting.usaModuloViaggi = usaModuloViaggi
                setting.save(flush: true)
            }// fine del blocco if
        }// fine del blocco if
    }// fine del metodo

    //--aggiunge (nuovo) flag per tutte le croci
    private static void fixOrganizzazione() {
        Croce croce

        croce = Croce.findBySigla(CROCE_ALGOS)
        if (croce) {
            croce.organizzazione = Organizzazione.nessuna
        }// fine del blocco if

        croce = Croce.findBySigla(CROCE_DEMO)
        if (croce) {
            croce.organizzazione = Organizzazione.nessuna
        }// fine del blocco if

        croce = Croce.findBySigla(CROCE_PUBBLICA_CASTELLO)
        if (croce) {
            croce.organizzazione = Organizzazione.anpas
        }// fine del blocco if

        croce = Croce.findBySigla(CROCE_ROSSA_FIDENZA)
        if (croce) {
            croce.organizzazione = Organizzazione.cri
        }// fine del blocco if

        croce = Croce.findBySigla(CROCE_ROSSA_PONTETARO)
        if (croce) {
            croce.organizzazione = Organizzazione.cri
        }// fine del blocco if

        newVersione(CROCE_ALGOS, 'Organizzazione', "Aggiunge l'organizzazione per tutte croci.")
    }// fine del metodo

    //--fix descrizione croci dopo aggiunta organizzazione
    private static void fixDescrizione() {
        Croce croce

        croce = Croce.findBySigla(CROCE_PUBBLICA_CASTELLO)
        if (croce) {
            croce.descrizione = 'Val Tidone'
            croce.save(flush: true)
        }// fine del blocco if

        croce = Croce.findBySigla(CROCE_ROSSA_FIDENZA)
        if (croce) {
            croce.descrizione = 'Comitato Locale di Fidenza'
            croce.save(flush: true)
        }// fine del blocco if

        croce = Croce.findBySigla(CROCE_ROSSA_PONTETARO)
        if (croce) {
            croce.descrizione = 'Comitato Locale di Ponte Taro'
            croce.save(flush: true)
        }// fine del blocco if

        newVersione(CROCE_ALGOS, 'Organizzazione', "Aggiunge l'organizzazione per tutte croci.")
    }// fine del metodo

    //--fix nome presidente, custode ed amministratore
    private static void fixCaricheFidenza() {
        Croce croce

        croce = Croce.findBySigla(CROCE_ROSSA_FIDENZA)
        if (croce) {
            croce.presidente = 'Rita Tanzi'
            croce.riferimento = 'Paolo Biazzi'
            croce.custode = 'Paolo Biazzi'
            croce.amministratori = 'Paolo Biazzi, Rita Tanzi, Massimiliano Abati'
            croce.save(flush: true)
        }// fine del blocco if

        newVersione(CROCE_ROSSA_FIDENZA, 'Profilo', "Fix nome presidente, custode ed amministratore")
    }// fine del metodo

    //--fix nome presidente, custode ed amministratore
    private static void fixCarichePonteTaro() {
        Croce croce

        croce = Croce.findBySigla(CROCE_ROSSA_PONTETARO)
        if (croce) {
            croce.presidente = 'Mauro Michelini'
            croce.riferimento = 'Mauro Michelini'
            croce.custode = 'Mauro Michelini'
            croce.amministratori = 'Mauro Michelini'
            croce.save(flush: true)
        }// fine del blocco if

        newVersione(CROCE_ROSSA_PONTETARO, 'Profilo', "Fix nome presidente, custode ed amministratore")
    }// fine del metodo

    //--eliminato dalle liste popup del form del tabellone il milite NON attivo
    private static void fixLevaMiliteNonAttivoDalleListePopup() {
        newVersione(CROCE_ALGOS, 'Tabellone', "Eliminato dalle liste popup del form del tabellone il milite NON attivo")
    }// fine del metodo

    //--cancellazione del milite 'Don Alessandro' a Fidenza
    private static void cancellaMiliteDonAlessandro() {
        Croce croce
        Milite milite

        croce = Croce.findBySigla(CROCE_ROSSA_FIDENZA)
        if (croce) {
            milite = Milite.findByCognomeAndNome('Don', 'Alessandro')
            if (milite) {
                MiliteService.cancellaMilite(milite)
            }// fine del blocco if
        }// fine del blocco if

        newVersione(CROCE_ROSSA_FIDENZA, 'Milite', "Cancellato Don Alessandro")
    }// fine del metodo

    //--creazione nuova authority per un custode a Fidenza
    private static void fixPermessiFidenza() {
        Croce croce = Croce.findBySigla(CROCE_ROSSA_FIDENZA)
        Ruolo ruoloCustode = Ruolo.findByAuthority(ROLE_CUSTODE)
        String nickName = 'Tanzi Annarita'
        Utente utente

        if (croce && ruoloCustode) {
            utente = Utente.findOrCreateByCroceAndNickname(croce, nickName)
            if (utente) {
                UtenteRuolo.findOrCreateByRuoloAndUtente(ruoloCustode, utente).save(failOnError: true)
            }// fine del blocco if
        }// fine del blocco if

        newVersione(CROCE_ROSSA_FIDENZA, 'Custode', "Abilitazione come custode di Tanzi Annarita ")
    }// fine del metodo

    //--Ridisegnata lista moduli disponibili per i vari ruoli
    private static void listaControllers() {
        newVersione(CROCE_ALGOS, 'Moduli', 'Ridisegnata lista moduli disponibili per i vari ruoli')
    }// fine del metodo

    //--aggiunto flag per disegnare bordo sopra i gruppi di turni di Fidenza
    private static void fixFlagPrimoFidenza() {
        Croce croce = Croce.findBySigla(CROCE_ROSSA_FIDENZA)
        TipoTurno tipoTurno

        if (croce) {
            tipoTurno = TipoTurno.findByCroceAndSigla(croce, CRF_TIPO_TURNO_AMBULANZA_MATTINO)
            if (tipoTurno) {
                tipoTurno.primo = true
                tipoTurno.save(flush: true)
            }// fine del blocco if

            tipoTurno = TipoTurno.findByCroceAndSigla(croce, CRPT_TIPO_TURNO_EXTRA)
            if (tipoTurno) {
                tipoTurno.primo = true
                tipoTurno.save(flush: true)
            }// fine del blocco if
        }// fine del blocco if

        newVersione(CROCE_ROSSA_FIDENZA, 'Tipi turni', 'Aggiunto flag per disegnare bordo blu sopra i gruppi di turni')
    }// fine del metodo

    //--aggiunto flag per disegnare bordo sopra i gruppi di turni di Pontetaro
    private static void fixFlagPrimoPontetaro() {
        Croce croce = Croce.findBySigla(CROCE_ROSSA_PONTETARO)
        TipoTurno tipoTurno

        if (croce) {
            tipoTurno = TipoTurno.findByCroceAndSigla(croce, CRPT_TIPO_TURNO_DIALISI_UNO_ANDATA)
            if (tipoTurno) {
                tipoTurno.primo = true
                tipoTurno.save(flush: true)
            }// fine del blocco if

            tipoTurno = TipoTurno.findByCroceAndSigla(croce, CRPT_TIPO_TURNO_ORDINARIO_SINGOLO)
            if (tipoTurno) {
                tipoTurno.primo = true
                tipoTurno.save(flush: true)
            }// fine del blocco if
        }// fine del blocco if

        newVersione(CROCE_ROSSA_PONTETARO, 'Tipi turni', 'Aggiunto flag per disegnare bordo blu sopra i gruppi di turni')
    }// fine del metodo

    //--cancella un campo
    //--elimina il campo 'ultimo' della tavola 'TipoTurno'
    //--il campo era eliminato dalla Domain Class, ma occorre cancellarlo anche dal DB
    //--altrimenti non si riesce a creare un nuovo record
    private static void cancellaUltimo() {
        //@todo devi eliminarlo con MSQLQueryBrowser
        newVersione(CROCE_ALGOS, 'DB', "Eliminato il campo 'ultimo' della tavola 'TipoTurno'")
    }// fine del metodo

    //--aggiunge altri militi alla croce 'demo'
    private static void addMilitiDemo() {
        Croce croce = Croce.findBySigla(CROCE_DEMO)
        Milite milite
        Funzione autista = Funzione.findByCroceAndSigla(croce, DEMO_FUNZIONE_AUT)
        Funzione secondo = Funzione.findByCroceAndSigla(croce, DEMO_FUNZIONE_SEC)
        Funzione terzo = Funzione.findByCroceAndSigla(croce, DEMO_FUNZIONE_TER)

        if (croce && autista && secondo && terzo) {
            milite = Milite.findOrCreateByCroceAndNomeAndCognome(croce, 'Marcello', 'Aguzzini').save(failOnError: true)
            Militefunzione.findOrCreateByCroceAndMiliteAndFunzione(croce, milite, autista).save(failOnError: true)
            Militefunzione.findOrCreateByCroceAndMiliteAndFunzione(croce, milite, secondo).save(failOnError: true)
            Militefunzione.findOrCreateByCroceAndMiliteAndFunzione(croce, milite, terzo).save(failOnError: true)

            milite = Milite.findOrCreateByCroceAndNomeAndCognome(croce, 'Lucia', 'Beretta').save(failOnError: true)
            Militefunzione.findOrCreateByCroceAndMiliteAndFunzione(croce, milite, autista).save(failOnError: true)
            Militefunzione.findOrCreateByCroceAndMiliteAndFunzione(croce, milite, secondo).save(failOnError: true)
            Militefunzione.findOrCreateByCroceAndMiliteAndFunzione(croce, milite, terzo).save(failOnError: true)

            milite = Milite.findOrCreateByCroceAndNomeAndCognome(croce, 'Paolo', 'Rubino').save(failOnError: true)
            Militefunzione.findOrCreateByCroceAndMiliteAndFunzione(croce, milite, autista).save(failOnError: true)
            Militefunzione.findOrCreateByCroceAndMiliteAndFunzione(croce, milite, secondo).save(failOnError: true)
            Militefunzione.findOrCreateByCroceAndMiliteAndFunzione(croce, milite, terzo).save(failOnError: true)

            milite = Milite.findOrCreateByCroceAndNomeAndCognome(croce, 'Adriano', 'Scotti').save(failOnError: true)
            Militefunzione.findOrCreateByCroceAndMiliteAndFunzione(croce, milite, secondo).save(failOnError: true)
            Militefunzione.findOrCreateByCroceAndMiliteAndFunzione(croce, milite, terzo).save(failOnError: true)

            milite = Milite.findOrCreateByCroceAndNomeAndCognome(croce, 'Rosaria', 'Esposito').save(failOnError: true)
            Militefunzione.findOrCreateByCroceAndMiliteAndFunzione(croce, milite, secondo).save(failOnError: true)
            Militefunzione.findOrCreateByCroceAndMiliteAndFunzione(croce, milite, terzo).save(failOnError: true)

            milite = Milite.findOrCreateByCroceAndNomeAndCognome(croce, 'Alfonso', 'Cazzaniga').save(failOnError: true)
            Militefunzione.findOrCreateByCroceAndMiliteAndFunzione(croce, milite, secondo).save(failOnError: true)
            Militefunzione.findOrCreateByCroceAndMiliteAndFunzione(croce, milite, terzo).save(failOnError: true)

            milite = Milite.findOrCreateByCroceAndNomeAndCognome(croce, 'Sergio', 'Dallora').save(failOnError: true)
            Militefunzione.findOrCreateByCroceAndMiliteAndFunzione(croce, milite, terzo).save(failOnError: true)

            milite = Milite.findOrCreateByCroceAndNomeAndCognome(croce, 'Flavia', 'Milanesi').save(failOnError: true)
            Militefunzione.findOrCreateByCroceAndMiliteAndFunzione(croce, milite, terzo).save(failOnError: true)

            milite = Milite.findOrCreateByCroceAndNomeAndCognome(croce, 'Giulio', 'Sartori').save(failOnError: true)
            Militefunzione.findOrCreateByCroceAndMiliteAndFunzione(croce, milite, terzo).save(failOnError: true)

            milite = Milite.findOrCreateByCroceAndNomeAndCognome(croce, 'Umberto', 'Terzino').save(failOnError: true)
            Militefunzione.findOrCreateByCroceAndMiliteAndFunzione(croce, milite, terzo).save(failOnError: true)

            milite = Milite.findOrCreateByCroceAndNomeAndCognome(croce, 'Lorenzo', 'Brambilla').save(failOnError: true)
            Militefunzione.findOrCreateByCroceAndMiliteAndFunzione(croce, milite, terzo).save(failOnError: true)

            milite = Milite.findOrCreateByCroceAndNomeAndCognome(croce, 'Daniela', 'Rubicondi').save(failOnError: true)
            Militefunzione.findOrCreateByCroceAndMiliteAndFunzione(croce, milite, terzo).save(failOnError: true)
        }// fine del blocco if

        newVersione(CROCE_DEMO, 'Militi', 'Aggiunti alcuni militi immaginari')
    }// fine del metodo

    //--aggiunge 4 turni per la demo
    //--modifica la sigla dei 3 turni esistenti
    private static void addTurniDemo() {
        Croce croce = Croce.findBySigla(CROCE_DEMO)
        TipoTurno automedicaMattino = TipoTurno.findByCroceAndSigla(croce, DEMO_TIPO_TURNO_AUTOMEDICA_MATTINO_OLD)
        TipoTurno automedicaPomeriggio = TipoTurno.findByCroceAndSigla(croce, DEMO_TIPO_TURNO_AUTOMEDICA_POMERIGGIO_OLD)
        TipoTurno automedicaNotte = TipoTurno.findByCroceAndSigla(croce, DEMO_TIPO_TURNO_AUTOMEDICA_NOTTE_OLD)
        Funzione autista = Funzione.findByCroceAndSigla(croce, DEMO_FUNZIONE_AUT)
        Funzione secondo = Funzione.findByCroceAndSigla(croce, DEMO_FUNZIONE_SEC)
        Funzione terzo = Funzione.findByCroceAndSigla(croce, DEMO_FUNZIONE_TER)
        TipoTurno nuovoTurno

        if (croce && automedicaMattino) {
            automedicaMattino.sigla = DEMO_TIPO_TURNO_AUTOMEDICA_MATTINO
            automedicaMattino.descrizione = 'Automedica mattino'
            automedicaMattino.save(flush: true)
        }// fine del blocco if

        if (croce && automedicaPomeriggio) {
            automedicaPomeriggio.sigla = DEMO_TIPO_TURNO_AUTOMEDICA_POMERIGGIO
            automedicaPomeriggio.descrizione = 'Automedica pomeriggio'
            automedicaPomeriggio.save(flush: true)
        }// fine del blocco if

        if (croce && automedicaNotte) {
            automedicaNotte.sigla = DEMO_TIPO_TURNO_AUTOMEDICA_NOTTE
            automedicaNotte.descrizione = 'Automedica notte'
            automedicaNotte.save(flush: true)
        }// fine del blocco if

        if (autista && secondo && terzo) {
            nuovoTurno = TipoTurno.findOrCreateByCroceAndSigla(croce, DEMO_TIPO_TURNO_AMBULANZA_MATTINO).save(failOnError: true)
            nuovoTurno.descrizione = 'Ambulanza mattino'
            nuovoTurno.ordine = 4
            nuovoTurno.durata = 6
            nuovoTurno.oraInizio = 6
            nuovoTurno.minutiInizio = 0
            nuovoTurno.oraFine = 12
            nuovoTurno.minutiFine = 0
            nuovoTurno.primo = true
            nuovoTurno.fineGiornoSuccessivo = false
            nuovoTurno.visibile = true
            nuovoTurno.orario = true
            nuovoTurno.multiplo = false
            nuovoTurno.funzioniObbligatorie = 1
            nuovoTurno.funzione1 = autista
            nuovoTurno.funzione2 = secondo
            nuovoTurno.funzione3 = terzo
            nuovoTurno.funzione4 = null
            nuovoTurno.save(flush: true)

            nuovoTurno = TipoTurno.findOrCreateByCroceAndSigla(croce, DEMO_TIPO_TURNO_AMBULANZA_POMERIGGIO)
            nuovoTurno.descrizione = 'Ambulanza pomeriggio'
            nuovoTurno.ordine = 5
            nuovoTurno.oraInizio = 12
            nuovoTurno.oraFine = 18
            nuovoTurno.durata = 6
            nuovoTurno.funzione1 = autista
            nuovoTurno.funzione2 = secondo
            nuovoTurno.funzione3 = terzo
            nuovoTurno.funzioniObbligatorie = 1
            nuovoTurno.save(flush: true)

            nuovoTurno = TipoTurno.findOrCreateByCroceAndSigla(croce, DEMO_TIPO_TURNO_AMBULANZA_NOTTE)
            nuovoTurno.descrizione = 'Ambulanza notte'
            nuovoTurno.ordine = 6
            nuovoTurno.oraInizio = 18
            nuovoTurno.oraFine = 6
            nuovoTurno.durata = 12
            nuovoTurno.funzione1 = autista
            nuovoTurno.funzione2 = secondo
            nuovoTurno.funzione3 = terzo
            nuovoTurno.funzioniObbligatorie = 1
            nuovoTurno.fineGiornoSuccessivo = true
            nuovoTurno.save(flush: true)

            nuovoTurno = TipoTurno.findOrCreateByCroceAndSigla(croce, DEMO_TIPO_TURNO_EXTRA)
            nuovoTurno.descrizione = 'Extra'
            nuovoTurno.ordine = 7
            nuovoTurno.primo = true
            nuovoTurno.orario = false
            nuovoTurno.funzione1 = autista
            nuovoTurno.funzione2 = secondo
            nuovoTurno.funzione3 = terzo
            nuovoTurno.funzioniObbligatorie = 1
            nuovoTurno.multiplo = true
            nuovoTurno.save(flush: true)
        }// fine del blocco if

        newVersione(CROCE_DEMO, 'Turni', 'Aggiunti 4 tipi di turni')
    }// fine del metodo

    //--Aggiunta una quarta funzione per i turni di ambulanza
    private static void addFunzioneDemo() {
        Croce croce = Croce.findBySigla(CROCE_DEMO)
        Funzione bar
        TipoTurno turno

        newFunzione(CROCE_DEMO, DEMO_FUNZIONE_BAR, 'Bar', 'Barelliere', 4, '')

        if (croce) {
            bar = Funzione.findByCroceAndSigla(croce, DEMO_FUNZIONE_BAR)
            if (bar) {
                turno = TipoTurno.findByCroceAndSigla(croce, DEMO_TIPO_TURNO_AMBULANZA_MATTINO)
                if (turno) {
                    turno.funzione4 = bar
                    turno.save(flush: true)
                }// fine del blocco if
                turno = TipoTurno.findByCroceAndSigla(croce, DEMO_TIPO_TURNO_AMBULANZA_POMERIGGIO)
                if (turno) {
                    turno.funzione4 = bar
                    turno.save(flush: true)
                }// fine del blocco if
                turno = TipoTurno.findByCroceAndSigla(croce, DEMO_TIPO_TURNO_AMBULANZA_NOTTE)
                if (turno) {
                    turno.funzione4 = bar
                    turno.save(flush: true)
                }// fine del blocco if
            }// fine del blocco if
        }// fine del blocco if

        newVersione(CROCE_DEMO, 'Funzione', 'Aggiunta una quarta funzione per i turni di ambulanza')
    }// fine del metodo

    //--Flag di barelliere a tutti i militi della demo
    private static void addFlagBarelliereDemo() {
        Croce croce = Croce.findBySigla(CROCE_DEMO)
        Funzione bar
        def militiDemo
        Milite milite

        if (croce) {
            bar = Funzione.findByCroceAndSigla(croce, DEMO_FUNZIONE_BAR)
            if (bar) {
                militiDemo = Milite.findAllByCroce(croce)
                militiDemo?.each {
                    milite = (Milite) it
                    Militefunzione.findOrCreateByCroceAndMiliteAndFunzione(croce, milite, bar).save(flush: true)
                } // fine del ciclo each
            }// fine del blocco if
        }// fine del blocco if

        newVersione(CROCE_DEMO, 'Funzione', 'Aggiunto flag di barelliere a tutti i militi della demo')
    }// fine del metodo

    //--Regolato a TRUE per Fidenza il nuovo flag di disabilitazione automatica turni
    private static void fixFlagDisabilitazioneFidenza() {
        Croce croce = Croce.findBySigla(CROCE_ROSSA_FIDENZA)
        Settings settingsFidenza

        if (croce) {
            settingsFidenza = croce.settings

            if (settingsFidenza) {
                settingsFidenza.isDisabilitazioneAutomaticaLogin = true
                settingsFidenza.save(flush: true)
            }// fine del blocco if
        }// fine del blocco if

        newVersione(CROCE_ROSSA_FIDENZA, 'Settings', 'Aggiunto per tutti il flag isDisabilitazioneAutomaticaLogin e regolato a true per CRF')
    }// fine del metodo

    //--cancella un campo
    //--salva i valori del vecchio campo 'contakilometri' nel nuovo 'chilometriTotaliPercorsi'
    //--elimina il campo 'contakilometri' della tavola 'Automezzo'
    //--il campo era eliminato dalla Domain Class, ma occorre cancellarlo anche dal DB
    //--altrimenti non si riesce a creare un nuovo record
    private static void cancellaKilometro() {
        Croce croce = Croce.findBySigla(CROCE_ROSSA_PONTETARO)
        Automezzo automezzo

        if (croce) {
            automezzo = Automezzo.findByCroceAndSigla(croce, 'PR 154')
            if (automezzo && automezzo.chilometriTotaliPercorsi == 0) {
                automezzo.chilometriTotaliPercorsi = 55833
                automezzo.save(flush: true)
            }// fine del blocco if
            automezzo = Automezzo.findByCroceAndSigla(croce, 'PR 159')
            if (automezzo && automezzo.chilometriTotaliPercorsi == 0) {
                automezzo.chilometriTotaliPercorsi = 118009
                automezzo.save(flush: true)
            }// fine del blocco if
            automezzo = Automezzo.findByCroceAndSigla(croce, 'PR 152')
            if (automezzo && automezzo.chilometriTotaliPercorsi == 0) {
                automezzo.chilometriTotaliPercorsi = 160499
                automezzo.save(flush: true)
            }// fine del blocco if
            automezzo = Automezzo.findByCroceAndSigla(croce, 'PR 153')
            if (automezzo && automezzo.chilometriTotaliPercorsi == 0) {
                automezzo.chilometriTotaliPercorsi = 7559
                automezzo.save(flush: true)
            }// fine del blocco if
            automezzo = Automezzo.findByCroceAndSigla(croce, 'PR 155')
            if (automezzo && automezzo.chilometriTotaliPercorsi == 0) {
                automezzo.chilometriTotaliPercorsi = 40573
                automezzo.save(flush: true)
            }// fine del blocco if
        }// fine del blocco if

        //@todo devi eliminarlo con MSQLQueryBrowser
        newVersione(CROCE_ROSSA_PONTETARO, 'DB', "Eliminato il campo 'contakilometri' della tavola 'Automezzo'")
    }// fine del metodo

    //--aggiunta di nuovi militi all'elenco militi per la Croce Rossa Ponte Taro
    //--elenco disponibile in csv
    //--li crea SOLO se non esistono già
    private static void militiRossaPonteTaroAggiuntivi() {
        Croce croce = Croce.findBySigla(CROCE_ROSSA_PONTETARO)
        String nomeFileSoci = 'crptnew'
        def righe
        String nominativo = ''
        int pos = 0
        String nome = ''
        String cognome = ''
        String telefono = ''
        String fisso = ''
        String mail = ''
        String dataNascitaTxt = ''
        Date dataNascita = null
        Map mappa
        Milite milite
        String tagSep = '-'
        String prima
        String dopo
        String tagSpazio = ' '
        Funzione bar = Funzione.findByCroceAndSigla(croce, CRPT_FUNZIONE_BAR)

        if (!ESISTE_COLLEGAMENTO_INTERNET) {
            return
        }// fine del blocco if

        if (!croce) {
            return
        }// fine del blocco if

        righe = LibFile.leggeCsv(DIR_PATH + nomeFileSoci)
        righe?.each {
            nominativo = ''
            cognome = ''
            nome = ''
            telefono = ''
            fisso = ''
            mail = ''
            dataNascitaTxt = ''

            mappa = (Map) it

            if (mappa.NOMINATIVO) {
                nominativo = mappa.NOMINATIVO
                pos = nominativo.indexOf(' ')
                if (pos) {
                    cognome = nominativo.substring(0, pos)
                    cognome = cognome.trim()
                    cognome = Lib.primaMaiuscola(cognome)
                    nome = nominativo.substring(pos + 1)
                    nome = nome.trim()
                    nome = Lib.primaMaiuscola(nome)
                }// fine del blocco if
            }// fine del blocco if

            if (mappa.cellulare) {
                telefono = mappa.cellulare
                pos = 3
                prima = telefono.substring(0, pos)
                dopo = telefono.substring(pos)
                telefono = prima + tagSpazio + dopo
            }// fine del blocco if

            if (mappa.fisso) {
                fisso = mappa.fisso
                if (fisso.contains(tagSep)) {
                    pos = fisso.indexOf(tagSep)
                    prima = fisso.substring(0, pos)
                    dopo = fisso.substring(pos + 1)
                    fisso = prima + dopo
                }// fine del blocco if
                pos = 4
                prima = fisso.substring(0, pos)
                dopo = fisso.substring(pos)
                fisso = prima + tagSpazio + dopo
            }// fine del blocco if

            if (mappa.MAIL) {
                mail = mappa.MAIL
                if (mail.equals(' ')) {
                    mail = ''
                }// fine del blocco if
            }// fine del blocco if

            if (mappa.nascita) {
                dataNascitaTxt = mappa.nascita
                if (dataNascitaTxt) {
                    dataNascita = Lib.creaData(dataNascitaTxt)
                }// fine del blocco if
            }// fine del blocco if

            milite = Milite.findOrCreateByCroceAndNomeAndCognome(croce, nome, cognome)
            if (!milite.telefonoCellulare) {
                milite.telefonoCellulare = telefono
            }// fine del blocco if
            if (!milite.telefonoFisso) {
                milite.telefonoFisso = fisso
            }// fine del blocco if
            if (!milite.email) {
                milite.email = mail
            }// fine del blocco if
            if (!milite.dataNascita) {
                milite.dataNascita = dataNascita
            }// fine del blocco if
            milite.save(failOnError: true)
            if (milite) {
                Militefunzione.findOrCreateByCroceAndMiliteAndFunzione(croce, milite, bar).save(flush: true)
            }// fine del blocco if
        } // fine del ciclo each

        newVersione(CROCE_ROSSA_PONTETARO, 'Militi', 'Aggiunta militi nuovo corso')
    }// fine del metodo

    //--controlloTemporale aggiunto per la Croce Rossa Ponte Taro
    private static void rossaPonteTaroBloccoSettimanale() {
        Croce croce = Croce.findBySigla(CROCE_ROSSA_PONTETARO)
        Settings settings

        if (croce) {
            settings = croce.settings
            if (settings) {
                settings.tipoControlloModifica = ControlloTemporale.bloccoSettimanale
                settings.minGiorniMancantiModifica = 0
                settings.tipoControlloCancellazione = ControlloTemporale.bloccoSettimanale
                settings.minGiorniMancantiCancellazione = 0
                settings.save(flush: true)
            }// fine del blocco if
        }// fine del blocco if

        newVersione(CROCE_ROSSA_PONTETARO, 'Controlli', 'Aggiunta controllo temporale bloccoSettimanale')
    }// fine del metodo

    //--modifica di un ruolo admin della croce rossa ponte taro
    private static void fixRuoloCRPT() {
        String nickOld = 'Gallo Gennaro'
        String nickNew = 'Giulivi Scilla'
        Croce croce = Croce.findBySigla(CROCE_ROSSA_PONTETARO)
        Ruolo ruoloAdmin = Ruolo.findByAuthority(ROLE_ADMIN)
        Ruolo ruoloMilite = Ruolo.findByAuthority(ROLE_MILITE)
        Utente utenteGallo = Utente.findByCroceAndNickname(croce, nickOld)
        Utente utenteScilla = Utente.findByCroceAndNickname(croce, nickNew)
        UtenteRuolo utenteRuolo

        if (ruoloAdmin && utenteGallo) {
            utenteRuolo = UtenteRuolo.findByRuoloAndUtente(ruoloAdmin, utenteGallo)
            if (utenteRuolo && ruoloMilite) {
                utenteRuolo.delete(flush: true)
                UtenteRuolo.findOrCreateByRuoloAndUtente(ruoloMilite, utenteGallo).save(failOnError: true)
            }// fine del blocco if
        }// fine del blocco if

        if (ruoloMilite && utenteScilla) {
            utenteRuolo = UtenteRuolo.findByRuoloAndUtente(ruoloMilite, utenteScilla)
            if (utenteRuolo && ruoloAdmin) {
                utenteRuolo.delete(flush: true)
                UtenteRuolo.findOrCreateByRuoloAndUtente(ruoloAdmin, utenteScilla).save(failOnError: true)
            }// fine del blocco if
        }// fine del blocco if

        newVersione(CROCE_ROSSA_PONTETARO, 'Security', 'Cambiato admin da Gallo -> Giulivi')
    }// fine del metodo

    //--corretto errore precedente
    //--avevo modificato il ruolo in admin, cancellando quello del milite
    //--da rimettere
    private static void fixErroreRuoloCRPT() {
        String nick = 'Giulivi Scilla'
        Croce croce = Croce.findBySigla(CROCE_ROSSA_PONTETARO)
        Ruolo ruoloMilite = Ruolo.findByAuthority(ROLE_MILITE)
        Utente utente = Utente.findByCroceAndNickname(croce, nick)

        if (ruoloMilite && utente) {
            UtenteRuolo.findOrCreateByRuoloAndUtente(ruoloMilite, utente).save(failOnError: true)
        }// fine del blocco if


        newVersione(CROCE_ROSSA_PONTETARO, 'Security', 'Ripristinato ruolo milite a Giulivi')
    }// fine del metodo

    //--modifica di un ruolo admin della croce rossa fidenza
    private static void fixRuoloCRF() {
        String nick = 'Porcari Stefano'
        Croce croce = Croce.findBySigla(CROCE_ROSSA_FIDENZA)
        Ruolo ruoloAdmin = Ruolo.findByAuthority(ROLE_ADMIN)
        Utente utente = Utente.findByCroceAndNickname(croce, nick)

        if (ruoloAdmin && utente) {
            UtenteRuolo.findOrCreateByRuoloAndUtente(ruoloAdmin, utente).save(failOnError: true)
        }// fine del blocco if

        newVersione(CROCE_ROSSA_FIDENZA, 'Security', 'Creato admin Porcari Stefano')
    }// fine del metodo

    //--aggiunta dei viaggi alla demo
    //--modifica del flag
    private static void addViaggiDemo() {
        Croce croce = Croce.findBySigla(CROCE_DEMO)
        Settings settings

        if (croce) {
            settings = croce.settings
            if (settings) {
                settings.usaModuloViaggi = true
                settings.save(flush: true)
            }// fine del blocco if
        }// fine del blocco if

        newVersione(CROCE_DEMO, 'Viaggi', 'Modifica del flag per abilitare i viaggi')
    }// fine del metodo

    //--aggiunta degli automezzi alla demo per operare sui viaggi
    private static void addAutomezziDemo() {
        Croce croce = Croce.findBySigla(CROCE_DEMO)
        Automezzo mezzo

        if (croce) {
            mezzo = Automezzo.findOrCreateByCroceAndSigla(croce, 'A4')
            if (mezzo) {
                mezzo.tipo = TipoAutomezzo.amb
                mezzo.chilometriTotaliPercorsi = 43821
                mezzo.dataAcquisto = new Date()
                mezzo.targa = 'VR342576'
                mezzo.descrizione = 'ambulanza'
                mezzo.numeroViaggiEffettuati = 78
                mezzo.save(flush: true)
            }// fine del blocco if
            mezzo = Automezzo.findOrCreateByCroceAndSigla(croce, 'A5')
            if (mezzo) {
                mezzo.tipo = TipoAutomezzo.automedica
                mezzo.chilometriTotaliPercorsi = 81545
                mezzo.dataAcquisto = new Date()
                mezzo.targa = 'EB347YT'
                mezzo.descrizione = 'automedica'
                mezzo.numeroViaggiEffettuati = 43
                mezzo.save(flush: true)
            }// fine del blocco if
            mezzo = Automezzo.findOrCreateByCroceAndSigla(croce, 'A6')
            if (mezzo) {
                mezzo.tipo = TipoAutomezzo.pulmino
                mezzo.chilometriTotaliPercorsi = 127890
                mezzo.dataAcquisto = new Date()
                mezzo.targa = 'DG389FM'
                mezzo.descrizione = 'pulmino sociale'
                mezzo.numeroViaggiEffettuati = 388
                mezzo.save(flush: true)
            }// fine del blocco if
        }// fine del blocco if

        //newVersione(CROCE_DEMO, 'Automezzi', 'Creazione di 3 mezzi')
    }// fine del metodo

    //--regolazione di due nuovi flag dei Settings per le Croci esistenti
    private static void addFlagSetting() {

        //--algos
        regolaFlagSecured(CROCE_ALGOS, true, true)

        //--demo
        regolaFlagSecured(CROCE_DEMO, false, false)

        //--castello
        regolaFlagSecured(CROCE_PUBBLICA_CASTELLO, false, true)

        //--fidenza
        regolaFlagSecured(CROCE_ROSSA_FIDENZA, true, true)

        //--pontetaro
        regolaFlagSecured(CROCE_ROSSA_PONTETARO, false, true)

        newVersione(CROCE_ALGOS, 'Settings', 'Aggiunti due flag per controllare il login alla partenza')
    }// fine del metodo

    //--regolazione base per una singola croce
    private static void regolaFlagSecured(String siglaCroce, boolean tabelloneSecured, boolean turniSecured) {
        Croce croce
        Settings settings

        //--algos
        croce = Croce.findBySigla(siglaCroce)
        if (croce) {
            settings = croce.settings
        }// fine del blocco if

        if (settings) {
            settings.tabelloneSecured = tabelloneSecured
            settings.turniSecured = turniSecured
            settings.save(flush: true)
        }// fine del blocco if
    }// fine del metodo

    //--regolazione di un ulteriore flag dei Settings per le Croci esistenti
    private static void addTabelloneFlagSetting() {

        //--algos
        regolaFlagTabellone(CROCE_ALGOS, false)

        //--demo
        regolaFlagTabellone(CROCE_DEMO, true)

        //--castello
        regolaFlagTabellone(CROCE_PUBBLICA_CASTELLO, false)

        //--fidenza
        regolaFlagTabellone(CROCE_ROSSA_FIDENZA, true)

        //--pontetaro
        regolaFlagTabellone(CROCE_ROSSA_PONTETARO, true)

        newVersione(CROCE_ALGOS, 'Settings', 'Aggiunti di un flag per controllare la videata da mostrare alla partenza')
    }// fine del metodo

    //--regolazione base per una singola croce
    private static void regolaFlagTabellone(String siglaCroce, boolean mostraTabellonePartenza) {
        Croce croce
        Settings settings

        //--algos
        croce = Croce.findBySigla(siglaCroce)
        if (croce) {
            settings = croce.settings
        }// fine del blocco if

        if (settings) {
            settings.mostraTabellonePartenza = mostraTabellonePartenza
            settings.save(flush: true)
        }// fine del blocco if
    }// fine del metodo

    //--eliminazione utente .ospite. (non più necessario)
    private static void deleteOspite() {
        String tagOspite = '.ospite.'
        Croce croce
        Utente utenteOspite
        UtenteRuolo utenteRuolo
        def logo

        croce = Croce.findBySigla(CROCE_ROSSA_PONTETARO)
        if (croce) {
            utenteOspite = Utente.findByCroceAndNickname(croce, tagOspite)
            if (utenteOspite) {
                utenteRuolo = UtenteRuolo.findByUtente(utenteOspite)
                if (utenteRuolo) {
                    utenteRuolo.delete(flush: true)
                }// fine del blocco if
                logo = Logo.findAllByUtente(utenteOspite)
                if (logo instanceof Logo) {
                    logo.utente = null
                    logo.save(flush: true)
                } else {
                    logo?.each {
                        it.utente = null
                        it.save(flush: true)
                    } // fine del ciclo each
                }// fine del blocco if-else
                utenteOspite.delete(flush: true)
            }// fine del blocco if
        }// fine del blocco if

        newVersione(CROCE_ROSSA_PONTETARO, 'Utenti', 'Eliminato utente .ospite.')
    }// fine del metodo

    //--creazione nuova croce
    private void creaPianoro() {
        if (SVILUPPO_PUBBLICA_PIANORO) {
            resetCroce(CROCE_PUBBLICA_PIANORO)
        }// fine del blocco if
        creazionePubblicaPianoro()
        configurazionePubblicaPianoro()
        securitySetupPubblicaPianoro()
        funzioniPubblicaPianoro()
        tipiDiTurnoPubblicaPianoro()
        militiPubblicaPianoro()
        turniDicembrePianoro()
        newVersione(CROCE_PUBBLICA_PIANORO, 'Creazione croce', 'Funzioni, tipiTurni, militi, utenti.')
    }// fine del metodo

    //--Pubblica Assistenza Pianoro
    //--creazione inziale della croce
    //--controlla SEMPRE
    //--modifica le proprietà coi valori di default se sono stati svuotati
    private static void creazionePubblicaPianoro() {
        Croce croce = Croce.findBySigla(CROCE_PUBBLICA_PIANORO)

        if (!croce) {
            croce = new Croce(sigla: CROCE_PUBBLICA_PIANORO)
        }// fine del blocco if

        if (croce) {
            if (!croce.organizzazione) {
                croce.organizzazione = Organizzazione.anpas
            }// fine del blocco if
            if (!croce.descrizione) {
                croce.descrizione = 'Pubblica Assistenza Pianoro'
            }// fine del blocco if
            if (!croce.presidente) {
                croce.presidente = 'Riccardo Piloni'
            }// fine del blocco if
            if (!croce.riferimento) {
                croce.riferimento = 'Silvano Piana'
            }// fine del blocco if
            if (!croce.indirizzo) {
                croce.indirizzo = 'Via del Lavoro 15 - 40065 Pianoro (BO)'
            }// fine del blocco if
            if (!croce.telefono) {
                croce.telefono = '051 774540'
            }// fine del blocco if
            if (!croce.email) {
                croce.email = 'presidente@pubblicapianoro.it'
            }// fine del blocco if
            if (!croce.custode) {
                croce.custode = 'Silvano Piana'
            }// fine del blocco if
            if (!croce.amministratori) {
                croce.amministratori = 'Silvano Piana'
            }// fine del blocco if
            if (!croce.note) {
                croce.note = ''
            }// fine del blocco if
            croce.save(failOnError: true)
        }// fine del blocco if
    }// fine del metodo

    //--Pubblica Assistenza Pianoro
    //--creazione record di configurazione (settings)
    //--lo crea SOLO se non esiste già
    //--controlla SEMPRE
    private static void configurazionePubblicaPianoro() {
        Croce croce = Croce.findBySigla(CROCE_PUBBLICA_PIANORO)
        Settings setting

        if (SVILUPPO_PUBBLICA_PIANORO && croce) {
            setting = Settings.findByCroce(croce)
            if (setting == null) {
                setting = new Settings(croce: croce)
                setting.startLogin = true
                setting.startController = 'turno'
                setting.allControllers = false
                setting.controlli = ''
                setting.mostraSoloMilitiFunzione = true
                setting.mostraMilitiFunzioneAndAltri = false
                setting.militePuoInserireAltri = false
                setting.militePuoModificareAltri = false
                setting.militePuoCancellareAltri = false
                setting.tipoControlloModifica = ControlloTemporale.tempoMancante
                setting.maxMinutiTrascorsiModifica = 0
                setting.minGiorniMancantiModifica = 2
                setting.tipoControlloCancellazione = ControlloTemporale.tempoMancante
                setting.maxMinutiTrascorsiCancellazione = 0
                setting.minGiorniMancantiCancellazione = 2
                setting.isOrarioTurnoModificabileForm = false
                setting.isCalcoloNotturnoStatistiche = false
                setting.fissaLimiteMassimoSingoloTurno = false
                setting.oreMassimeSingoloTurno = 0
                setting.usaModuloViaggi = false
                setting.numeroServiziEffettuati = 0
                setting.tabelloneSecured = true
                setting.turniSecured = true
                setting.mostraTabellonePartenza = true
                setting.save(failOnError: true)
            }// fine del blocco if
        }// fine del blocco if
    }// fine del metodo

    //--creazione accessi per la croce
    //--occorre SEMPRE un accesso come admin
    //--occorre SEMPRE un accesso come utente
    //--li crea SOLO se non esistono già
    private static void securitySetupPubblicaPianoro() {
        Utente utente
        String nick
        String pass
        Ruolo adminRole
        Ruolo militeRole

        if (SVILUPPO_PUBBLICA_PIANORO) {
            adminRole = Ruolo.findOrCreateByAuthority(ROLE_ADMIN).save(failOnError: true)
            militeRole = Ruolo.findOrCreateByAuthority(ROLE_MILITE).save(failOnError: true)

            // custode
            nick = 'Piana Silvano'
            pass = 'piana987'
            utente = newUtente(CROCE_PUBBLICA_PIANORO, ROLE_CUSTODE, nick, pass)
            numUtentiPubblicaPianoro++
            if (adminRole && militeRole && utente) {
                UtenteRuolo.findOrCreateByRuoloAndUtente(adminRole, utente).save(failOnError: true)
                UtenteRuolo.findOrCreateByRuoloAndUtente(militeRole, utente).save(failOnError: true)
            }// fine del blocco if

            // dipendente generico
            // serve per permettere ai dipendenti di vedere i turni (bloccati per i senza password)
            // senza potersi segnare
            nick = 'dipendenti'
            pass = 'dip987'
            newUtente(CROCE_PUBBLICA_PIANORO, ROLE_MILITE, nick, pass)
            numUtentiPubblicaPianoro++
        }// fine del blocco if
    }// fine del metodo

    //--creazione funzioni per la pubblica assistenza Pianoro
    //--ogni croce ha SEMPRE diverse funzioni
    //--li crea SOLO se non esistono già
    private static void funzioniPubblicaPianoro() {
        if (SVILUPPO_PUBBLICA_PIANORO) {
            newFunzPianoro(PAP_FUNZIONE_AUT, 'Aut', 'Autista', 1, PAP_FUNZIONE_SOC + ',' + PAP_FUNZIONE_BAR + ',' + PAP_FUNZIONE_BAR_2)
            newFunzPianoro(PAP_FUNZIONE_SOC, 'Soc', 'Soccorritore', 2, PAP_FUNZIONE_BAR + ',' + PAP_FUNZIONE_BAR_2)
            newFunzPianoro(PAP_FUNZIONE_BAR, 'Bar', 'Barelliere', 3, '')
            newFunzPianoro(PAP_FUNZIONE_BAR_2, 'Bar2', 'Barelliere', 3, '')
        }// fine del blocco if
    }// fine del metodo

    //--creazione delle tipologie di turni per la pubblica assistenza Pianoro
    //--ogni croce può avere tipologie differenti
    //--li crea SOLO se non esistono già
    private static void tipiDiTurnoPubblicaPianoro() {
        if (SVILUPPO_PUBBLICA_PIANORO) {
            newTipoTurnoPianoro(PAP_TIPO_TURNO_LUNVEN_NOTTE, 'amb lun-ven notte', 1, 0, 7, false, true, true, false, 2)
            newTipoTurnoPianoro(PAP_TIPO_TURNO_LUNVEN_MATTINA, 'amb lun-ven mattina', 2, 7, 14, false, true, true, false, 2)
            newTipoTurnoPianoro(PAP_TIPO_TURNO_LUNVEN_POMERIGGIO, 'amb lun-ven pomeriggio', 3, 14, 17, false, true, true, false, 2)
            newTipoTurnoPianoro(PAP_TIPO_TURNO_LUNVEN_POMERIGGIOSERA, 'amb lun-ven pom/sera', 4, 17, 20, false, true, true, false, 2)
            newTipoTurnoPianoro(PAP_TIPO_TURNO_LUNVEN_SERA, 'amb lun-ven sera', 5, 20, 24, false, true, true, false, 2)
            newTipoTurnoPianoro(PAP_TIPO_TURNO_SABDOM_NOTTE, 'amb sab-dom notte', 6, 0, 8, false, true, true, false, 2)
            newTipoTurnoPianoro(PAP_TIPO_TURNO_SABDOM_MATTINA, 'amb sab-dom mattina', 7, 8, 13, false, true, true, false, 2)
            newTipoTurnoPianoro(PAP_TIPO_TURNO_SABDOM_POMERIGGIO, 'amb sab-dom pomeriggio', 8, 13, 19, false, true, true, false, 2)
            newTipoTurnoPianoro(PAP_TIPO_TURNO_SABDOM_SERA, 'amb sab-dom sera', 9, 19, 24, false, true, true, false, 2)
        }// fine del blocco if
    }// fine del metodo

    //--aggiunta militi
    //--controlla SEMPRE
    private static void militiPubblicaPianoro() {
        Croce croce = Croce.findBySigla(CROCE_PUBBLICA_PIANORO)
        Milite milite
        Funzione autista = Funzione.findByCroceAndSigla(croce, PAP_FUNZIONE_AUT)
        Funzione soccorritore = Funzione.findByCroceAndSigla(croce, PAP_FUNZIONE_SOC)
        Funzione barelliere = Funzione.findByCroceAndSigla(croce, PAP_FUNZIONE_BAR)
        Funzione barelliere2 = Funzione.findByCroceAndSigla(croce, PAP_FUNZIONE_BAR_2)

        if (croce && autista && soccorritore && barelliere) {
            milite = Milite.findOrCreateByCroceAndNomeAndCognome(croce, 'Silvano', 'Piana').save(failOnError: true)
            Militefunzione.findOrCreateByCroceAndMiliteAndFunzione(croce, milite, autista).save(failOnError: true)
            Militefunzione.findOrCreateByCroceAndMiliteAndFunzione(croce, milite, soccorritore).save(failOnError: true)
            Militefunzione.findOrCreateByCroceAndMiliteAndFunzione(croce, milite, barelliere).save(failOnError: true)
            Militefunzione.findOrCreateByCroceAndMiliteAndFunzione(croce, milite, barelliere2).save(failOnError: true)
        }// fine del blocco if
    }// fine del metodo

    //--creazione dei turni vuoti per dicembre 2013
    //--li crea SOLO se non esistono già
    private static void turniDicembrePianoro() {
        Croce croce = Croce.findBySigla(CROCE_PUBBLICA_PIANORO)
        int primoDicembre = 335

        if (SVILUPPO_PUBBLICA_PIANORO && croce) {
            creaTurnoVuotoPianoro(croce, 335)
            for (int k = primoDicembre; k <= 365; k++) {
                creaTurnoVuotoPianoro(croce, k)
            } // fine del ciclo for
        }// fine del blocco if
    }// fine del metodo

    private static creaTurnoVuotoPianoro(Croce croce, int delta) {
        Date primoGennaio2013 = Lib.creaData1Gennaio()
        Date giorno
        int numGiornoSettimanale

        giorno = primoGennaio2013 + delta - 1
        numGiornoSettimanale = Lib.getNumSettimana(giorno)

        //-- sabato: numGiornoSettimanale=7
        //-- domenica: numGiornoSettimanale=1
        //-- 8 dic: delta=342
        //-- 25 dic: delta=359
        //-- 26 dic: delta=360
        if (numGiornoSettimanale == 7 || numGiornoSettimanale == 1 || delta == 342 || delta == 359 || delta == 360) {
            creaTurnoVuotoFestivoPianoro(croce, giorno)
        } else {
            creaTurnoVuotoFerialePianoro(croce, giorno)
        }// fine del blocco if-else
    }// fine del metodo

    private static creaTurnoVuotoFerialePianoro(Croce croce, Date giorno) {
        Lib.creaTurno(croce, TipoTurno.findByCroceAndSigla(croce, PAP_TIPO_TURNO_LUNVEN_NOTTE), giorno)
        Lib.creaTurno(croce, TipoTurno.findByCroceAndSigla(croce, PAP_TIPO_TURNO_LUNVEN_MATTINA), giorno)
        Lib.creaTurno(croce, TipoTurno.findByCroceAndSigla(croce, PAP_TIPO_TURNO_LUNVEN_POMERIGGIO), giorno)
        Lib.creaTurno(croce, TipoTurno.findByCroceAndSigla(croce, PAP_TIPO_TURNO_LUNVEN_POMERIGGIOSERA), giorno)
        Lib.creaTurno(croce, TipoTurno.findByCroceAndSigla(croce, PAP_TIPO_TURNO_LUNVEN_SERA), giorno)
    }// fine del metodo

    private static creaTurnoVuotoFestivoPianoro(Croce croce, Date giorno) {
        Lib.creaTurno(croce, TipoTurno.findByCroceAndSigla(croce, PAP_TIPO_TURNO_SABDOM_NOTTE), giorno)
        Lib.creaTurno(croce, TipoTurno.findByCroceAndSigla(croce, PAP_TIPO_TURNO_SABDOM_MATTINA), giorno)
        Lib.creaTurno(croce, TipoTurno.findByCroceAndSigla(croce, PAP_TIPO_TURNO_SABDOM_POMERIGGIO), giorno)
        Lib.creaTurno(croce, TipoTurno.findByCroceAndSigla(croce, PAP_TIPO_TURNO_SABDOM_SERA), giorno)
    }// fine del metodo

    def destroy = {
    }// fine della closure

}// fine della classe

