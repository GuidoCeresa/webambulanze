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

    //--controllo di funzioni da utilizzare SOLAMENTE in fase di sviluppo
    private static boolean SVILUPPO = false;

    //--usato per controllare la creazione automatica delle password
    private static int numUtentiRossa = 0

    //--variabili temporaneee per passare i riferimenti da una tavola all'altra
    private static ArrayList<Funzione> funzDemo = []
    private static ArrayList<Funzione> funzOrdinarioPAVT = []
    private static ArrayList<Funzione> funz118PAVT = []
    private static ArrayList<Funzione> funzAutomedicaCRF = []
    private static ArrayList<Funzione> funzAmbulanzaCRF = []

    // private static String DIR_PATH = '/Users/Gac/Documents/IdeaProjects/webambulanze/grails-app/webambulanze/'
    private static String DIR_PATH = 'http://77.43.32.198:80/ambulanze/'

    //--metodo invocato direttamente da Grails
    def init = { servletContext ->
        iniezioneVariabili(servletContext)
        creazioneCroceInterna()
        creazioneCroceDemo()
        creazioneCroci()
        securitySetup()
        securitySetupDemo()
        securitySetupRossa()

        configurazioneCroceInterna()
        configurazioneCroceDemo()
        configurazioneCroci()
        linkInternoAziende()
        funzioniCroci()
        militiDemo()
        militiPubblica()
        militiRossa()
        //    militiNascita()
        militiBLSD()
        utentiRossa() //password
//        militiFunzioni()
        tipiDiTurno()
        turni2013Demo()
        turni2013Rossa()
        //      turniSporchiRossaSoloDebug()
    }// fine della closure

    //--iniezione di alcune variabili generali visibili in tutto il programma
    //--valori di default che vengono modificati a seconda delle regolazioni nei Settings della croce
    private static void iniezioneVariabili(servletContext) {
        // inietta una variabile per selezionare la croce interessata
        servletContext.croce = Croce.findBySigla(CROCE_ALGOS)

        // login obbligatorio alla partenza del programma
        // ANCHE prima della presentazione del tabellone o del menu
        servletContext.startLogin = false

        //--seleziona la videata iniziale
        // nome dell'eventuale controller da invocare
        // automaticamente alla partenza del programma.
        // parte il metodo di default del controller.
        // se non definita visualizza un elenco dei moduli/controller visibili
        servletContext.startController = ''

        //--seleziona (flag booleano) se mostrare tutti i controllers nella videata Home
        servletContext.allControllers = false

        //--seleziona (lista di stringhe) i controllers da mostrare nella videata Home
        //--ha senso solo se il flag allControllers è false
        servletContext.controlli = ''
    }// fine del metodo

    //--creazione inziale della croce virtuale
    //--crea solo il record vuoto per farla apparire in lista e nei filtri
    private static void creazioneCroceInterna() {
        Croce croce

        //--Interna
        croce = Croce.findOrCreateBySigla(CROCE_ALGOS)
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
    }// fine del metodo

    //--creazione inziale di una croce dimostrativa
    //--crea solo il record vuoto per farla apparire in lista e nei filtri
    private static void creazioneCroceDemo() {
        Croce croce

        //--Interna
        croce = Croce.findOrCreateBySigla(CROCE_DEMO)
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
    }// fine del metodo

    //--creazione inziale delle due (2) croci attuali (21-12-12)
    //--le crea SOLO se non esistono già
    //--modifica le proprietà coi valori di default se non sono già stati modificati
    private static void creazioneCroci() {
        Croce croce

        //--Pubblica
        croce = Croce.findOrCreateBySigla(CROCE_PUBBLICA)
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

        //--Croce Rossa
        croce = Croce.findOrCreateBySigla(CROCE_ROSSA)
        if (!croce.descrizione) {
            croce.descrizione = 'Croce Rossa Italiana - Comitato Locale di Fidenza'
        }// fine del blocco if
        if (!croce.riferimento) {
            croce.riferimento = 'Rita Tanzi'
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
            croce.note = 'Rita Tanzi (348 6052310), Paolo Biazzi (328 4820471) e Massimiliano Abati'
        }// fine del blocco if
        croce.save(failOnError: true)
    }// fine del metodo

    //--creazione ruoli generali di accesso alle autorizzazioni gestite dal security-plugin
    //--occorre SEMPRE un ruolo ROLE_PROG
    //--occorre SEMPRE un ruolo ROLE_ADMIN
    //--occorre SEMPRE un ruolo ROLE_MILITE
    //--occorre SEMPRE un accesso come programmatore
    //--occorre SEMPRE un accesso come admin
    //--occorre SEMPRE un accesso come milite
    //--li crea SOLO se non esistono già
    private static void securitySetup() {
        Utente utente
        String nick
        String pass
        Ruolo.findOrCreateByAuthority(ROLE_PROG).save(failOnError: true)
        Ruolo custodeRole = Ruolo.findOrCreateByAuthority(ROLE_CUSTODE).save(failOnError: true)
        Ruolo adminRole = Ruolo.findOrCreateByAuthority(ROLE_ADMIN).save(failOnError: true)
        Ruolo militeRole = Ruolo.findOrCreateByAuthority(ROLE_MILITE).save(failOnError: true)
        Ruolo ospiteRole = Ruolo.findOrCreateByAuthority(ROLE_OSPITE).save(failOnError: true)

        // programmatore generale
        utente = newUtente(CROCE_ALGOS, ROLE_PROG, 'gac', 'fulvia')
        if (custodeRole && adminRole && militeRole && ospiteRole && utente) {
            UtenteRuolo.findOrCreateByRuoloAndUtente(custodeRole, utente).save(failOnError: true)
            UtenteRuolo.findOrCreateByRuoloAndUtente(adminRole, utente).save(failOnError: true)
            UtenteRuolo.findOrCreateByRuoloAndUtente(militeRole, utente).save(failOnError: true)
            UtenteRuolo.findOrCreateByRuoloAndUtente(ospiteRole, utente).save(failOnError: true)
        }// fine del blocco if

        // altro programmatore generale (per mostrarlo nell'elenco)
        utente = newUtente(CROCE_ALGOS, ROLE_PROG, '---', 'fulvia')
        if (custodeRole && adminRole && militeRole && ospiteRole && utente) {
            UtenteRuolo.findOrCreateByRuoloAndUtente(custodeRole, utente).save(failOnError: true)
            UtenteRuolo.findOrCreateByRuoloAndUtente(adminRole, utente).save(failOnError: true)
            UtenteRuolo.findOrCreateByRuoloAndUtente(militeRole, utente).save(failOnError: true)
            UtenteRuolo.findOrCreateByRuoloAndUtente(ospiteRole, utente).save(failOnError: true)
        }// fine del blocco if

        // accesso anonimo (per mostrarlo nell'elenco)
        //--non funziona !!!
//        utente = newUtente(CROCE_ALGOS, ROLE_OSPITE, 'Ospite', 'ospite')
//        if (custodeRole && adminRole && militeRole && ospiteRole && utente) {
//            UtenteRuolo.findOrCreateByRuoloAndUtente(custodeRole, utente).save(failOnError: true)
//            UtenteRuolo.findOrCreateByRuoloAndUtente(adminRole, utente).save(failOnError: true)
//            UtenteRuolo.findOrCreateByRuoloAndUtente(militeRole, utente).save(failOnError: true)
//            UtenteRuolo.findOrCreateByRuoloAndUtente(ospiteRole, utente).save(failOnError: true)
//        }// fine del blocco if

        // ruoli singoli per i test di funzionamento
        newUtente(CROCE_ALGOS, ROLE_PROG, 'gacp', 'prog')
        newUtente(CROCE_ALGOS, ROLE_CUSTODE, 'gacc', 'custode')
        newUtente(CROCE_ALGOS, ROLE_ADMIN, 'gaca', 'admin')
        newUtente(CROCE_ALGOS, ROLE_MILITE, 'gacm', 'milite')
        newUtente(CROCE_ALGOS, ROLE_OSPITE, 'gaco', 'ospite')

    }// fine del metodo

    //--creazione accessi per la croce
    //--occorre SEMPRE un accesso come admin
    //--occorre SEMPRE un accesso come utente
    //--li crea SOLO se non esistono già
    private static void securitySetupDemo() {
        Utente utente
        String nick
        String pass
        Ruolo custodeRole = Ruolo.findOrCreateByAuthority(ROLE_CUSTODE).save(failOnError: true)
        Ruolo adminRole = Ruolo.findOrCreateByAuthority(ROLE_ADMIN).save(failOnError: true)
        Ruolo militeRole = Ruolo.findOrCreateByAuthority(ROLE_MILITE).save(failOnError: true)
        Ruolo ospiteRole = Ruolo.findOrCreateByAuthority(ROLE_OSPITE).save(failOnError: true)

        // programmatore generale
//        utente = newUtente(CROCE_DEMO, ROLE_PROG, 'gac', 'fulvia')
//        if (custodeRole && adminRole && militeRole && ospiteRole && utente) {
//            UtenteRuolo.findOrCreateByRuoloAndUtente(custodeRole, utente).save(failOnError: true)
//            UtenteRuolo.findOrCreateByRuoloAndUtente(adminRole, utente).save(failOnError: true)
//        }// fine del blocco if

        // milite (anonimo)
        newUtente(CROCE_DEMO, ROLE_MILITE, DEMO_OSPITE, DEMO_PASSWORD)
    }// fine del metodo

    //--creazione accessi per la croce
    //--occorre SEMPRE un accesso come admin
    //--occorre SEMPRE un accesso come utente
    //--li crea SOLO se non esistono già
    private static void securitySetupRossa() {
        Utente utente
        String nick
        String pass
        Ruolo custodianRole = Ruolo.findOrCreateByAuthority(ROLE_CUSTODE).save(failOnError: true)
        Ruolo adminRole = Ruolo.findOrCreateByAuthority(ROLE_ADMIN).save(failOnError: true)
        Ruolo militeRole = Ruolo.findOrCreateByAuthority(ROLE_MILITE).save(failOnError: true)

        // programmatore generale
        newUtente(CROCE_ROSSA, ROLE_PROG, PROG_NICK, PROG_PASS)

        // custode
        utente = newUtente(CROCE_ROSSA, ROLE_CUSTODE, 'Biazzi Paolo', 'biazzi123')
        numUtentiRossa++
        if (custodianRole && adminRole && militeRole && utente) {
            UtenteRuolo.findOrCreateByRuoloAndUtente(adminRole, utente).save(failOnError: true)
            UtenteRuolo.findOrCreateByRuoloAndUtente(militeRole, utente).save(failOnError: true)
        }// fine del blocco if

        // admin
        utente = newUtente(CROCE_ROSSA, ROLE_ADMIN, 'Tanzi Rita', 'tanzi123')
        numUtentiRossa++
        if (custodianRole && adminRole && militeRole && utente) {
            UtenteRuolo.findOrCreateByRuoloAndUtente(militeRole, utente).save(failOnError: true)
        }// fine del blocco if

        // admin
        utente = newUtente(CROCE_ROSSA, ROLE_ADMIN, 'Abati Massimiliano', 'abati123')
        numUtentiRossa++
        if (custodianRole && adminRole && militeRole && utente) {
            UtenteRuolo.findOrCreateByRuoloAndUtente(militeRole, utente).save(failOnError: true)
        }// fine del blocco if
    }// fine del metodo

    //--creazione record di configurazione (settings)
    //--lo crea SOLO se non esiste già
    private static void configurazioneCroceInterna() {
        Croce croce
        Settings setting

        //--Interna
        croce = Croce.findBySigla(CROCE_ALGOS)
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
                setting.save(failOnError: true)
            }// fine del blocco if
        }// fine del blocco if
    }// fine del metodo

    //--creazione record di configurazione (settings)
    //--lo crea SOLO se non esiste già
    private static void configurazioneCroceDemo() {
        Croce croce
        Settings setting

        //--Interna
        croce = Croce.findBySigla(CROCE_DEMO)
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
                setting.save(failOnError: true)
            }// fine del blocco if
        }// fine del blocco if
    }// fine del metodo

    //--creazione record di configurazione (settings)
    //--uno per ogni croce
    //--lo crea SOLO se non esiste già
    private static void configurazioneCroci() {
        Croce croce
        Settings setting

        //--aggiunta solo per la croce citata
        croce = Croce.findBySigla(CROCE_PUBBLICA)
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
                setting.save(failOnError: true)
            }// fine del blocco if
        }// fine del blocco if

        //--aggiunta solo per la croce citata
        croce = Croce.findBySigla(CROCE_ROSSA)
        if (croce) {
            setting = Settings.findByCroce(croce)
            if (setting == null) {
                setting = new Settings(croce: croce)
                setting.startLogin = true
                setting.startController = 'turno'
                setting.allControllers = false
                setting.controlli = ''
                setting.mostraSoloMilitiFunzione = false
                setting.mostraMilitiFunzioneAndAltri = true
                setting.militePuoInserireAltri = false
                setting.militePuoModificareAltri = false
                setting.militePuoCancellareAltri = false
                setting.tipoControlloModifica = ControlloTemporale.tempoTrascorso
                setting.maxMinutiTrascorsiModifica = 30
                setting.minGiorniMancantiModifica = 0
                setting.tipoControlloCancellazione = ControlloTemporale.tempoTrascorso
                setting.maxMinutiTrascorsiCancellazione = 30
                setting.minGiorniMancantiCancellazione = 0
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

    //--creazione funzioni per ogni croce
    //--ogni croce ha SEMPRE diverse funzioni
    //--li crea SOLO se non esistono già
    private static void funzioniCroci() {

        //--aggiunta solo per la croce Demo
        funzDemo.add(newFunzione(CROCE_DEMO, 'aut', 'Aut', 'Autista', 1, ''))
        funzDemo.add(newFunzione(CROCE_DEMO, 'sec', 'Sec', 'Soccorritore', 2, ''))
        funzDemo.add(newFunzione(CROCE_DEMO, 'ter', 'Ter', 'Aiuto', 3, ''))

        //--aggiunta solo per la croce Pubblica
        //--turni ordinari
        newFunzTidoneOrd('autistaord', 'Aut Ord', 'Autista', 1, 'secondoord, terzoord')
        newFunzTidoneOrd('secondoord', '2° ord', 'Secondo', 2, 'terzoord')
        newFunzTidoneOrd('terzoord', '3° ord', 'Terzo', 3, '')
        //--turni 118
        newFunzTidone118('autista118', 'Aut 118', 'Autista 188', 4, 'secondo118, terzo118')
        newFunzTidone118('secondo118', '2° 118', 'Secondo 188', 5, 'terzo118')
        newFunzTidone118('terzo118', '3° 188', 'Terzo 118', 6, '')

        //--aggiunta solo per la croce Rossa
        //--turni automedica
        newFunzRossaMedica('aut-msa', 'Aut MSA', 'Autista automedica', 1, 'pri-msa, sec-msa')
        newFunzRossaMedica('pri-msa', '1° soc', 'Primo soccorritore automedica', 2, 'sec-msa')
        newFunzRossaMedica('sec-msa', '2° soc', 'Secondo soccorritore automedica', 3, '')
        //--turni ambulanza
        newFunzRossaAmb('aut-amb', 'Aut Amb', 'Autista ambulanza', 4, 'pri-amb, sec-amb, ter-amb')
        newFunzRossaAmb('pri-amb', '1° soc', 'Primo soccorritore ambulanza', 5, 'sec-amb, ter-amb')
        newFunzRossaAmb('sec-amb', '2° soc', 'Secondo soccorritore ambulanza', 6, 'ter-amb')
        newFunzRossaAmb('ter-amb', '3° soc', 'Terzo soccorritore ambulanza', 7, '')
    }// fine del metodo

    //--crea una funzione funzione per la croce
    //--la crea SOLO se non esiste già
    private static void newFunzTidoneOrd(
            String sigla,
            String siglaVisibile,
            String descrizione,
            int ordine,
            String funzioniAutomatiche) {
        funzOrdinarioPAVT.add(newFunzione(CROCE_PUBBLICA, sigla, siglaVisibile, descrizione, ordine, funzioniAutomatiche))
    }// fine del metodo

    //--crea una funzione funzione per la croce
    //--la crea SOLO se non esiste già
    private static void newFunzTidone118(
            String sigla,
            String siglaVisibile,
            String descrizione,
            int ordine,
            String funzioniAutomatiche) {
        funz118PAVT.add(newFunzione(CROCE_PUBBLICA, sigla, siglaVisibile, descrizione, ordine, funzioniAutomatiche))
    }// fine del metodo

    //--crea una funzione funzione per la croce
    //--la crea SOLO se non esiste già
    private static void newFunzRossaMedica(
            String sigla,
            String siglaVisibile,
            String descrizione,
            int ordine,
            String funzioniAutomatiche) {

        funzAutomedicaCRF.add(newFunzione(CROCE_ROSSA, sigla, siglaVisibile, descrizione, ordine, funzioniAutomatiche))
    }// fine del metodo

    //--crea una funzione funzione per la croce
    //--la crea SOLO se non esiste già
    private static void newFunzRossaAmb(
            String sigla,
            String siglaVisibile,
            String descrizione,
            int ordine,
            String funzioniAutomatiche) {

        funzAmbulanzaCRF.add(newFunzione(CROCE_ROSSA, sigla, siglaVisibile, descrizione, ordine, funzioniAutomatiche))
    }// fine del metodo

    //--crea una funzione funzione per la croce
    //--la crea SOLO se non esiste già
    private static Funzione newFunzione(
            String siglaCroce,
            String sigla,
            String siglaVisibile,
            String descrizione,
            int ordine,
            String funzioniAutomatiche) {
        Funzione funzione = null
        Croce croce = Croce.findBySigla(siglaCroce)

        if (croce) {
            funzione = Funzione.findOrCreateByCroceAndSigla(croce, sigla)
            funzione.siglaVisibile = siglaVisibile
            funzione.descrizione = descrizione
            funzione.ordine = ordine
            funzione.funzioniDipendenti = funzioniAutomatiche

            funzione.save(failOnError: true)
        }// fine del blocco if
        return funzione
    }// fine del metodo

    //--creazione delle tipologie di turni per ogni croce
    //--ogni croce può avere tipologie differenti
    //--li crea SOLO se non esistono già
    private static void tipiDiTurno() {

        //--aggiunta solo per la croce Demo
        newTipoTurno(CROCE_DEMO, 'mat', 'mattino', 1, 8, 14, false, true, true, false, 1, funzDemo)
        newTipoTurno(CROCE_DEMO, 'pom', 'pomeriggio', 2, 14, 20, false, true, true, false, 1, funzDemo)
        newTipoTurno(CROCE_DEMO, 'notte', 'notte', 3, 20, 8, true, true, true, false, 1, funzDemo)

        //--aggiunta solo per la croce Pubblica
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

        //--aggiunta solo per la croce Rossa
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
        newTipoTurno(CROCE_ROSSA, sigla, desc, ord, oraIni, oraFine, next, vis, orario, mult, funzObb, funzAutomedicaCRF)
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
        newTipoTurno(CROCE_ROSSA, sigla, desc, ord, oraIni, oraFine, next, vis, orario, mult, funzObb, funzAmbulanzaCRF)
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
            tipo = TipoTurno.findOrCreateByCroceAndSigla(croce, sigla)
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

        Milite milite

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
    //--elenco disponibile in csv con nomi tutti maiuscoli
    //--li crea SOLO se non esistono già
    private static void militiRossa() {
        Croce croce = Croce.findBySigla(CROCE_ROSSA)
        String nomeFileSoci = 'crfsoci'
        String nomeFileTelefoni = 'crftel'
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
        String tagVero = 'X'
        String blsTxt = ''
        String alsTxt = ''
        String autTxt = ''
        boolean bls = false
        boolean als = false
        boolean aut = false
        Funzione funzioneAutistaAmbulanza = Funzione.findByCroceAndSigla(croce, funzAmbulanzaCRF[0].sigla)
        Funzione funzioneAutistaAutomedica = Funzione.findByCroceAndSigla(croce, funzAutomedicaCRF[0].sigla)

        //--esegue solo se il database (della Rossa) è vuoto
        if (Milite.findAllByCroce(croce).size() > 0) {
            return
        }// fine del blocco if

        //--nominativi
        righe = LibFile.leggeCsv(DIR_PATH + nomeFileSoci)
        righe?.each {
            mappa = (Map) it
            if (mappa.nome) {
                nome = mappa.nome
            }// fine del blocco if
            nome = nome.trim()
            nome = Milite.soloPrimaMaiuscola(nome)
            if (mappa.cognome) {
                cognome = mappa.cognome
            }// fine del blocco if
            cognome = cognome.trim()
            cognome = Milite.soloPrimaMaiuscola(cognome)
            if (mappa.data) {
                stringaNascita = mappa.data
                if (stringaNascita) {
                    try { // prova ad eseguire il codice
                        dataNascita = new Date(stringaNascita)
                    } catch (Exception unErrore) { // intercetta l'errore
                        log.error unErrore
                    }// fine del blocco try-catch
                }// fine del blocco if
            }// fine del blocco if

            milite = Milite.findOrCreateByCroceAndNomeAndCognome(croce, nome, cognome)
            if (dataNascita && !milite.dataNascita) {
                milite.dataNascita = dataNascita
            }// fine del blocco if
            if (!milite.telefonoFisso) {
                milite.telefonoFisso = ''
            }// fine del blocco if
            if (!milite.telefonoCellulare) {
                milite.telefonoCellulare = ''
            }// fine del blocco if
            if (!milite.note) {
                milite.note = ''
            }// fine del blocco if

            milite.save(failOnError: true)
        } // fine del ciclo each

        //--telefoni
        righe = LibFile.leggeCsv(DIR_PATH + nomeFileTelefoni)
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
                cognomenome = cognomenome.toLowerCase()
                cognomenome = Milite.soloPrimaMaiuscola(cognomenome)
                if (cognomenome.indexOf(' ') > 0) {
                    cognomenome = cognomenome.substring(0, cognomenome.indexOf(' '))
                }// fine del blocco if
                cognomenome = cognomenome.trim()
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

            milite = Milite.findByCroceAndCognome(croce, cognomenome)

            if (milite) {
                if (!milite.telefonoCellulare) {
                    milite.telefonoCellulare = cellulare
                }// fine del blocco if
                if (!milite.telefonoFisso) {
                    milite.telefonoFisso = fisso
                }// fine del blocco if
                if (aut) {
                    for (int k = 0; k < funzAmbulanzaCRF.size(); k++) {
                        Militefunzione.findOrCreateByCroceAndMiliteAndFunzione(croce, milite, funzAmbulanzaCRF[k]).save(flush: true)
                    } // fine del ciclo for
                }// fine del blocco if
                if (aut && als) {
                    for (int k = 0; k < funzAutomedicaCRF.size(); k++) {
                        Militefunzione.findOrCreateByCroceAndMiliteAndFunzione(croce, milite, funzAutomedicaCRF[k]).save(flush: true)
                    } // fine del ciclo for
                }// fine del blocco if

                //--di default almeno il livello più basso lo metto
                int numFunzAmb = funzAmbulanzaCRF.size() - 1
                Militefunzione.findOrCreateByCroceAndMiliteAndFunzione(croce, milite, funzAmbulanzaCRF[numFunzAmb]).save(flush: true)

                milite.save(failOnError: true)
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

        if (SVILUPPO) {
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

        if (SVILUPPO) {
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

    //--creazione dei record utenti
    //--uno per ogni milite
    //--nickname=cognomeNome
    //--password=cognome(minuscolo) + 3 cifre numeriche random
    //--li crea SOLO se non esistono già
    private static void utentiRossa() {
        ArrayList listaUtenti
        ArrayList listaMiliti
        Croce croce = Croce.findBySigla(CROCE_ROSSA)
        Milite milite

        if (croce) {
            listaUtenti = Utente.findAllByCroce(croce)
            if (listaUtenti.size() == numUtentiRossa) {
                listaMiliti = Milite.findAllByCroce(croce)
                listaMiliti?.each {
                    milite = (Milite) it
                    newUtenteMilite(CROCE_ROSSA, milite)
                } // fine del ciclo each
            }// fine del blocco if
        }// fine del blocco if
    }// fine del metodo

    //--creazione delle tabelle di incrocio funzioni per ogni milite
    //--le crea SOLO se non esistono già
    private static void militiFunzioni() {
        Croce croce
        Milite milite
        Funzione funzione
        def listaMiliti
        def listaFunzioni

        //--aggiunta solo per la croce citata
        croce = Croce.findBySigla(CROCE_ROSSA)
        if (SVILUPPO && croce) {
            listaFunzioni = Funzione.findAllByCroce(croce)
            listaMiliti = Milite.findAllByCroce(croce)

            if (listaFunzioni && listaMiliti) {
                listaMiliti?.each {
                    milite = it
                    listaFunzioni?.each {
                        funzione = it
                        Militefunzione.findOrCreateByCroceAndMiliteAndFunzione(croce, milite, funzione).save(failOnError: true)
                    } // fine del ciclo each
                } // fine del ciclo each
            }// fine del blocco if
        }
    }// fine del metodo

    //--creazione dei turni vuoti per il 2013
    //--li crea SOLO se non esistono già
    private static void turni2013Demo() {
        Croce croce
        Date primoGennaio2013 = Lib.creaData1Gennaio()
        def listaTipoTurno
        def listaTurni

        croce = Croce.findBySigla(CROCE_DEMO)
        if (croce) {
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
        }// fine del blocco if
    }// fine del metodo

    //--creazione dei turni vuoti per il 2013
    //--li crea SOLO se non esistono già
    private static void turni2013Rossa() {
        Croce croce
        Date primoGennaio2013 = Lib.creaData1Gennaio()
        def listaTipoTurno
        def listaTurni

        croce = Croce.findBySigla(CROCE_ROSSA)
        if (croce) {
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

    private static void turniSporchiRossaSoloDebug() {
        Croce croce = Croce.findBySigla(CROCE_ROSSA)
        Turno turno
        TipoTurno tipoTurno
        Date giorno
        Funzione funzione
        Milite milite

        //--19 gennaio
        tipoTurno = TipoTurno.findBySigla('msa-pom')
        giorno = Lib.creaData(19)
        milite = Milite.findById(170)
        funzione = Funzione.findByCroceAndSigla(croce, 'aut-msa')
        turno = Turno.findByCroceAndTipoTurnoAndGiorno(croce, tipoTurno, giorno)
        if (turno) {
            turno.funzione1 = funzione
            turno.militeFunzione1 = milite
            turno.save(flush: true)
        }// fine del blocco if

        //--20 gennaio
        tipoTurno = TipoTurno.findBySigla('msa-mat')
        giorno = Lib.creaData(20)
        milite = Milite.findById(180)
        funzione = Funzione.findByCroceAndSigla(croce, 'pri-msa')
        turno = Turno.findByCroceAndTipoTurnoAndGiorno(croce, tipoTurno, giorno)
        if (turno) {
            turno.funzione1 = funzione
            turno.militeFunzione1 = milite
            turno.save(flush: true)
        }// fine del blocco if
        milite = Milite.findById(185)
        funzione = Funzione.findByCroceAndSigla(croce, 'sec-msa')
        turno = Turno.findByCroceAndTipoTurnoAndGiorno(croce, tipoTurno, giorno)
        if (turno) {
            turno.funzione2 = funzione
            turno.militeFunzione2 = milite
            turno.save(flush: true)
        }// fine del blocco if

    }

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

        if (nick && password && croce && ruolo) {
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
        nick = cognome + ' ' + nome
        password = cognome.toLowerCase() + '123'

        newUtente(siglaCroce, ROLE_MILITE, nick, password, milite)
    }// fine del metodo

    def destroy = {
    }// fine della closure

}// fine della classe

