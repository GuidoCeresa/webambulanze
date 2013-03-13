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
    private static boolean SVILUPPO_CROCE_ROSSA_PONTE_TARO = true;

    //--controllo di funzioni da utilizzare SOLAMENTE in fase di sviluppo
    private static boolean ESISTE_COLLEGAMENTO_INTERNET = true;

    //--usato per controllare la creazione automatica delle password
    private static int numUtentiRossaFidenza = 0
    private static int numUtentiRossaPonteTaro = 0

    //--variabili temporaneee per passare i riferimenti da una tavola all'altra
    private static ArrayList<Funzione> funzDemo = []
    private static ArrayList<Funzione> funzOrdinarioPAVT = []
    private static ArrayList<Funzione> funz118PAVT = []
    private static ArrayList<Funzione> funzAutomedicaCRF = []
    private static ArrayList<Funzione> funzAmbulanzaCRF = []
    private static ArrayList<Funzione> funzCRPT = []

    // private static String DIR_PATH = '/Users/Gac/Documents/IdeaProjects/webambulanze/grails-app/webambulanze/'
    private static String DIR_PATH = 'http://77.43.32.198:80/ambulanze/'

    def grailsApplication
    def sessionFactory

    //--metodo invocato direttamente da Grails
    def init = { servletContext ->

        //--cancella tutto il database
//        resetCompleto()

        iniezioneVariabili(servletContext)

        //--ruoli
        securitySetupRuoli()

        //--croce interna
        //      croceAlgos()

        //--croce demo
        //croceDemo()

        //--croce tidone
        //  croceTidone()

        //--croce rossa fidenza
        //croceRossaFidenza()

        //--croce rossa pontetaro
        if (SVILUPPO_CROCE_ROSSA_PONTE_TARO) {
            resetCroce(CROCE_ROSSA_PONTETARO)
        }// fine del blocco if
        croceRossaPontetaro()

        //--creazione del collegamento tra croce e settings
        linkInternoAziende()
    }// fine della closure

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
            } // fine del ciclo each
        }// fine del blocco if

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
        utentiRossa() //password
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
        Croce croce = Croce.findBySigla(CROCE_PUBBLICA)

        if (!croce) {
            croce = new Croce(sigla: CROCE_PUBBLICA)
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
        String nickSuffix = '/' + CROCE_ROSSA_PONTETARO.toLowerCase()
        String pass
        Ruolo adminRole
        Ruolo militeRole

        // programmatore generale (sempre presente)    @todo ?
        // newUtente(CROCE_ROSSA_PONTETARO, ROLE_PROG, PROG_NICK_CRPT, PROG_PASS)

        if (SVILUPPO_CROCE_ROSSA_PONTE_TARO) {
            adminRole = Ruolo.findOrCreateByAuthority(ROLE_ADMIN).save(failOnError: true)
            militeRole = Ruolo.findOrCreateByAuthority(ROLE_MILITE).save(failOnError: true)

            // custode
            nick = 'Michelini Mauro' + nickSuffix
            pass = 'michelini123'
            utente = newUtente(CROCE_ROSSA_PONTETARO, ROLE_CUSTODE, nick, pass)
            numUtentiRossaPonteTaro++
            if (adminRole && militeRole && utente) {
                UtenteRuolo.findOrCreateByRuoloAndUtente(adminRole, utente).save(failOnError: true)
                UtenteRuolo.findOrCreateByRuoloAndUtente(militeRole, utente).save(failOnError: true)
            }// fine del blocco if

            // admin
            nick = 'Gallo Gennaro' + nickSuffix
            pass = 'gallo123'
            utente = newUtente(CROCE_ROSSA_PONTETARO, ROLE_ADMIN, nick, pass)
            numUtentiRossaPonteTaro++
            if (militeRole && utente) {
                UtenteRuolo.findOrCreateByRuoloAndUtente(militeRole, utente).save(failOnError: true)
            }// fine del blocco if

            // admin
            nick = 'Pessina Giovanni' + nickSuffix
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
        Croce croce = Croce.findBySigla(CROCE_PUBBLICA)
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
            newFunzRossaMedica('pri-msa', '1° soc', 'Primo soccorritore automedica', 2, 'sec-msa')
            newFunzRossaMedica('sec-msa', '2° soc', 'Secondo soccorritore automedica', 3, '')
            //--turni ambulanza
            newFunzRossaAmb('aut-amb', 'Aut Amb', 'Autista ambulanza', 4, 'pri-amb, sec-amb, ter-amb')
            newFunzRossaAmb('pri-amb', '1° soc', 'Primo soccorritore ambulanza', 5, 'sec-amb, ter-amb')
            newFunzRossaAmb('sec-amb', '2° soc', 'Secondo soccorritore ambulanza', 6, 'ter-amb')
            newFunzRossaAmb('ter-amb', '3° soc', 'Terzo soccorritore ambulanza', 7, '')
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
        funzOrdinarioPAVT.add(newFunzione(CROCE_PUBBLICA, siglaInterna, siglaVisibile, descrizione, ordine, funzioniAutomatiche))
    }// fine del metodo

    //--crea una funzione funzione per la croce
    //--la crea SOLO se non esiste già
    private static void newFunzTidone118(
            String siglaInterna,
            String siglaVisibile,
            String descrizione,
            int ordine,
            String funzioniAutomatiche) {
        funz118PAVT.add(newFunzione(CROCE_PUBBLICA, siglaInterna, siglaVisibile, descrizione, ordine, funzioniAutomatiche))
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
        newTipoTurno(CROCE_PUBBLICA, sigla, desc, ord, oraIni, oraFine, next, vis, orario, mult, funzObb, funzOrdinarioPAVT)
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
        newTipoTurno(CROCE_PUBBLICA, sigla, desc, ord, oraIni, oraFine, next, vis, orario, mult, funzObb, funz118PAVT)
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
        Croce croce = Croce.findBySigla(CROCE_PUBBLICA)
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

        if (!SVILUPPO_CROCE_ROSSA_PONTE_TARO) {
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
        Croce croce = Croce.findBySigla(CROCE_PUBBLICA)
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
    private static void utentiRossa() {
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
            utente = Utente.findOrCreateByUsername(nick)
            utente.croce = croce
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
        nick = cognome + ' ' + nome + '/' + siglaCroce.toLowerCase()
        password = cognome.toLowerCase() + '123'

        newUtente(siglaCroce, ROLE_MILITE, nick, password, milite)
    }// fine del metodo

    def destroy = {
    }// fine della closure

}// fine della classe

