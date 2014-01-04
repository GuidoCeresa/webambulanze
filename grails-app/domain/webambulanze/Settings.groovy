package webambulanze

class Settings {

    //--croce di riferimento
    Croce croce

    //--login preliminare al tabellone ed al menu
    //--impedita anche la visione iniziale
    //--@deprecated
    boolean startLogin = false

    //--nome dell'eventuale controller da invocare
    //--automaticamente alla partenza del programma.
    //--se non definita, va al menu (home)
    //--@deprecated
    String startController = 'home'

    //--flag per controllare quali controller
    //--mostrare nella gsp inziale
    //--se falso, mostra solo quelli utilizzabili dal Utente finale
    //--@deprecated
    boolean allControllers = false

    //--nomi dei controllers da
    //--mostrare nella gsp inziale
    //--@deprecated
    String controlli = ''

    //--mostra solo i militi abilitati alla funzione
    //--oppure tutti
    boolean mostraSoloMilitiFunzione = true

    //--mostra prima i militi abilitati alla funzione e poi tutti gli altri
    //--oppure li mostra tutti insieme in ordine alfabetico
    //--ha senso solo se il flag  mostraSoloMilitiFunzione è false
    boolean mostraMilitiFunzioneAndAltri = true

    //--se un milite può inserire nel turno solo il suo nome
    //--o può inserire anche quello di un altro
    boolean militePuoInserireAltri = true

    //--se un milite può modificare nel turno solo il suo nome
    //--o può modificare anche quello di un altro
    boolean militePuoModificareAltri = true

    //--se un milite può cancellare dal turno solo il suo nome
    //--o può cancellare anche quello di un altro
    boolean militePuoCancellareAltri = true

    //--tipo di controllo temporale sulla modifica
    //--nessuno = nessun controllo
    //--tempoTrascorso = tempo trascorso dall'ultima modifica (in minuti)
    //--tempoMancante = tempo mancante all'inizio evento (in giorni)
    ControlloTemporale tipoControlloModifica = ControlloTemporale.nessuno

    //--Massimo numero di minuti trascorsi dall'ultima modifica
    //--sotto il quale non si possono più effettuare modifiche
    int maxMinutiTrascorsiModifica = 0

    //--Minimo numero di giorni mancanti all'evento
    //--dopo il quale non si possono più effettuare modifiche
    int minGiorniMancantiModifica = 0

    //--tipo di controllo temporale sulla cancellazione
    //--nessuno = nessun controllo
    //--tempoTrascorso = tempo trascorso dall'ultima cancellazione (in minuti)
    //--tempoMancante = tempo mancante all'inizio evento (in giorni)
    ControlloTemporale tipoControlloCancellazione = ControlloTemporale.nessuno

    //--Massimo numero di minuti trascorsi dall'ultima cancellazione
    //--sotto il quale non si può più effettuare la cancellazione
    int maxMinutiTrascorsiCancellazione = 0

    //--Minimo numero di giorni mancanti all'evento
    //--dopo il quale non si può più effettuare la cancellazione
    int minGiorniMancantiCancellazione = 0

    //--se nel form del turno l'orario di inizio e fine turno è modificabile
    //--negli extra è SEMPRE modificabile
    boolean isOrarioTurnoModificabileForm = false

    //--abilita il ricalcolo notturno delle statistiche
    //--cancella e ricostruisce (sola per la croce selezionata), due tavole di statistiche
    boolean isCalcoloNotturnoStatistiche = false

    //--regola in maniera automatica il flag Enabled (sola per la croce selezionata)
    //--per abilitare o meno il login
    //--in funzione del flag verde/rosso delle statistiche dei turni effettuati
    //--in pratica esclude automaticamente chi non raggiunge la soglia minima di turni
    boolean isDisabilitazioneAutomaticaLogin = false

    //--flag per controllare il numero massimo di ore inserite dal milite per un singolo turno
    boolean fissaLimiteMassimoSingoloTurno = false

    //--numero massimo di ore inserite dal milite per un singolo turno
    //--ha senso solo se fissaLimiteMassimoSingoloTurno è true
    int oreMassimeSingoloTurno = 0

    //--abilitazione al modulo
    boolean usaModuloViaggi = false

    //--progressivo dei servizi effettuati da tutti i mezzi della croce
    //--aggiornato automaticamente da ogni nuovo viaggio
    //--se modificato da qui, occorre un avviso
    int numeroServiziEffettuati = 0

    //--protezione del tabellone che viene mostrato solo DOPO aver effettuato il Login
    boolean tabelloneSecured = false

    //--protezione delle modifiche ai turni che vengono abilitate solo DOPO aver effettuato il Login
    boolean turniSecured = true

    //--mostra il tabellone alla partenza; in caso contrario va alla home
    boolean mostraTabellonePartenza = true

    //--i blocchi si applicano solo alle funzioni dei militi obbligatori (2 o 3) piuttosto che a tutti (4)
    boolean bloccaSoloFunzioniObbligatorie = false

    //--il milite può creare un nuovo turno standard
    boolean militePuoCreareTurnoStandard = false

    //--il milite può creare un nuovo turno extra
    boolean militePuoCreareTurnoExtra = true

    //--flag per inserire un turno ripetitivo ogni settimana
    boolean isTurnoSettimanale = false

    //--flag per presentare i militi secondo nome e cognome
    //--di default falso: si presenta secondo il cognome
    boolean usaNomeCognome = false

    //--flag per presentare una lista di militi nel nuovo viaggio, con selezionato quello in turno
    //--di default falso: si presenta solo il milite in turno (se esiste) per nome e cognome
    boolean usaListaMilitiViaggi = false

    //--flag per suggerire il kilometraggio in un nuovo viaggio incrementato di 1
    boolean suggerisceKilometroViaggio = false

    /**
     * regolazione delle proprietà di ogni campo
     * l'ordine con cui vengono elencati qui, viene rispettato nella lista e nella scheda standard
     * la possibilità di avere valori nulli, di default è false
     */
    static constraints = {
        croce(unique: true, nullable: false, blank: false)
        startLogin()
        startController()
        allControllers()
        controlli(nullable: true, blank: true)
        mostraSoloMilitiFunzione()
        mostraMilitiFunzioneAndAltri()
        militePuoInserireAltri()
        militePuoModificareAltri()
        militePuoCancellareAltri()
        tipoControlloModifica()
        maxMinutiTrascorsiModifica()
        minGiorniMancantiModifica()
        tipoControlloCancellazione()
        maxMinutiTrascorsiCancellazione()
        minGiorniMancantiCancellazione()
        isOrarioTurnoModificabileForm()
        isCalcoloNotturnoStatistiche()
        isDisabilitazioneAutomaticaLogin()
        fissaLimiteMassimoSingoloTurno()
        oreMassimeSingoloTurno()
        usaModuloViaggi()
        numeroServiziEffettuati()
        tabelloneSecured()
        turniSecured()
        mostraTabellonePartenza()
        bloccaSoloFunzioniObbligatorie()
        militePuoCreareTurnoStandard()
        militePuoCreareTurnoExtra()
        isTurnoSettimanale()
        usaNomeCognome()
        usaListaMilitiViaggi()
        suggerisceKilometroViaggio()
    } // end of static constraints

    /*+
     *
     *
     */
    static mapping = {
    } // end of static mapping

    /**
     * valore di testo restituito per una istanza della classe
     */
    String toString() {
        return croce
    } // end of toString

    /**
     * metodo chiamato automaticamente da Grails
     * prima di creare un nuovo record
     */
    def beforeInsert = {
        if (!controlli) {
            controlli = ''
        }// fine del blocco if
    } // end of def beforeInsert

    /**
     * metodo chiamato automaticamente da Grails
     * prima di registrare un record esistente
     */
    def beforeUpdate = {
        if (!controlli) {
            controlli = ''
        }// fine del blocco if
    } // end of def beforeUpdate

    /**
     * metodo chiamato automaticamente da Grails
     * prima di cancellare un record
     */
    def beforeDelete = {
    } // end of def beforeDelete

    /**
     * metodo chiamato automaticamente da Grails
     * dopo che il record è stato letto dal database e
     * le proprietà dell'oggetto sono state aggiornate
     */
    def onLoad = {
    } // end of def onLoad

    /**
     * restituisce la proprietà per l'istanza selezionata
     */
    public static boolean startLogin(String siglaCroce) {
        boolean startLogin = false
        Croce croce = Croce.findBySigla(siglaCroce)
        Settings setting

        if (croce) {
            setting = croce.settings
            if (setting) {
                startLogin = setting.startLogin
            }// fine del blocco if
        }// fine del blocco if

        return startLogin
    } // fine del metodo

    /**
     * restituisce la proprietà per l'istanza selezionata
     */
    public static String startController(String siglaCroce) {
        String startController = ''
        Croce croce = Croce.findBySigla(siglaCroce)
        Settings setting

        if (croce) {
            setting = croce.settings
            if (setting) {
                startController = setting.startController
            }// fine del blocco if
        }// fine del blocco if

        return startController
    } // fine del metodo

    /**
     * restituisce la proprietà per l'istanza selezionata
     */
    public static boolean allControllers(String siglaCroce) {
        boolean allControllers = false
        Croce croce = Croce.findBySigla(siglaCroce)
        Settings setting

        if (croce) {
            setting = croce.settings
            if (setting) {
                allControllers = setting.allControllers
            }// fine del blocco if
        }// fine del blocco if

        return allControllers
    } // fine del metodo

    /**
     * restituisce la proprietà per l'istanza selezionata
     */
    public static String controlli(String siglaCroce) {
        String controlli = ''
        Croce croce = Croce.findBySigla(siglaCroce)
        Settings setting

        if (croce) {
            setting = croce.settings
            if (setting) {
                controlli = setting.controlli
            }// fine del blocco if
        }// fine del blocco if

        return controlli
    } // fine del metodo

} // fine della domain classe

