package webambulanze

class TipoTurno {

    //--croce di riferimento
    Croce croce

    //--sigla di riferimento interna
    String sigla = ''

    //--descrizione per il tabellone
    String descrizione = ''

    //--ordine di presentazione nel tabellone
    int ordine = 0

    //--durata del turno (in ore)
    int durata = 0

    //--ora prevista (normale) di inizio turno
    int oraInizio

    //--minuti previsti (normali) di inizio turno
    //--nella GUI la scelta viene bloccata ai quarti d'ora
    int minutiInizio = 0

    //--ora prevista (normale) di fine turno
    int oraFine

    //--minuti previsti (normali) di fine turno
    //--nella GUI la scelta viene bloccata ai quarti d'ora
    int minutiFine = 0

    //--ultimo turno di un eventuale raggruppamento a video (nel tabellone)
    //boolean ultimo = false

    //--Primo turno di un eventuale raggruppamento a video (nel tabellone)
    boolean primo = false

    //--turno a cavallo della mezzanotte - termina il giorno successivo
    boolean fineGiornoSuccessivo = false

    //--visibilità nel tabellone
    boolean visibile = true

    //--orario predefinito (avis, centralino ed extra non ce l'hanno)
    boolean orario = true

    //--possibilità di occorrenze multiple (extra)
    boolean multiplo = false

    //--numero di militi/funzioni obbligatorie
    int funzioniObbligatorie = 0

    //--elenco delle funzioni previste per questo tipo di turno
    //--massimo hardcoded di 4
    //--l'ordine determina la presentazione in scheda turno
    Funzione funzione1
    Funzione funzione2
    Funzione funzione3
    Funzione funzione4

    // regolazione delle proprietà di ogni campo
    // l'ordine con cui vengono elencati qui, viene rispettato nella lista e nella scheda standard
    // la possibilità di avere valori nulli, di default è false
    static constraints = {
        croce(nullable: false, blank: false)
        sigla(nullable: false, blank: false)
        descrizione(nullable: true, blank: true)
        ordine()
        durata(display: false)
        oraInizio()
        minutiInizio(display: false)
        oraFine()
        minutiFine(display: false)
        primo()
        fineGiornoSuccessivo()
        visibile()
        orario()
        multiplo()
        funzioniObbligatorie()
        funzione1(nullable: true, blank: true)
        funzione2(nullable: true, blank: true)
        funzione3(nullable: true, blank: true)
        funzione4(nullable: true, blank: true)
    } // end of static constraints

    //--pacchetto di funzioni previste in questo tipo di turno
    public ArrayList<Funzione> getListaFunzioni() {
        ArrayList<Funzione> listaFunzioni = new ArrayList<Funzione>()
        String funz

        for (int k = 1; k <= 4; k++) {
            funz = 'funzione' + k
            if (this."${funz}") {
                listaFunzioni.add((Funzione) this."${funz}")
            }// fine del blocco if
        } // fine del ciclo for

        return listaFunzioni
    }

    //--numero di funzioni previste in questo tipo di turno
    public int numFunzioni() {
        int num
        ArrayList<Funzione> listaFunzioni = this.getListaFunzioni()

        if (listaFunzioni) {
            num = listaFunzioni.size()
        }// fine del blocco if

        return num
    }

    // valore di testo restituito per una istanza della classe
    String toString() {
        sigla
    } // end of toString

    /**
     * metodo chiamato automaticamente da Grails
     * prima di creare un nuovo record
     */
    def beforeInsert = {
        if (!durata) {
            durata = (fineGiornoSuccessivo) ? (24 + oraFine - oraInizio) : (oraFine - oraInizio)
        }// fine del blocco if
    } // end of def beforeInsert

    /**
     * metodo chiamato automaticamente da Grails
     * prima di registrare un record esistente
     */
    def beforeUpdate = {
        if (!durata) {
            durata = (fineGiornoSuccessivo) ? (24 + oraFine - oraInizio) : (oraFine - oraInizio)
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

} // end of Class
