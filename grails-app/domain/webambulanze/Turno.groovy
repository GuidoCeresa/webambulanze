package webambulanze

import java.sql.Timestamp

class Turno {
    //--croce di riferimento
    Croce croce

    //--tipologia del turno
    TipoTurno tipoTurno

    //--giorno di svolgimento del turno (giorno iniziale se termina il mattino dopo)
    //--ore e minuti sono sempre a zero
    Date giorno
    //--giorno, ora e minuto di inizio turno
    Date inizio
    //--giorno, ora e minuto di fine turno
    Date fine

    //--numero variabile di funzioni previste per il tipo di turno
    //--massimo hardcoded di 4
    Funzione funzione1
    Funzione funzione2
    Funzione funzione3
    Funzione funzione4

    //--numero variabile di militi assegnati alle funzioni previste per il tipo di turno
    //--massimo hardcoded di 4
    Milite militeFunzione1 = null
    Milite militeFunzione2 = null
    Milite militeFunzione3 = null
    Milite militeFunzione4 = null

    //--ultima modifica effettuata per le funzioni previste per il tipo di turno
    //--massimo hardcoded di 4
    //--serve per bloccare le modifiche dopo un determinatpo intervallo di tempo
    Timestamp modificaFunzione1 = null
    Timestamp modificaFunzione2 = null
    Timestamp modificaFunzione3 = null
    Timestamp modificaFunzione4 = null

    //--durata del turno per ogni milite
    //--massimo hardcoded di 4
    int oreMilite1 = 0
    int oreMilite2 = 0
    int oreMilite3 = 0
    int oreMilite4 = 0

    //--eventuali problemi di presenza del milite nel turno
    //--serve per evidenziare il problema nel tabellone
    //--massimo hardcoded di 4
    boolean problemiFunzione1 = false
    boolean problemiFunzione2 = false
    boolean problemiFunzione3 = false
    boolean problemiFunzione4 = false

    //--motivazione del turno
    String titoloExtra = ''
    //--nome evidenziato della località
    String localitàExtra = ''
    //--descrizione dei viaggi extra
    String note = ''
    //--turno previsto (vuoto) oppure assegnato (militi inseriti)
    boolean assegnato = false

    // regolazione delle proprietà di ogni campo
    // l'ordine con cui vengono elencati qui, viene rispettato nella lista e nella scheda standard
    // la possibilità di avere valori nulli, di default è false
    static constraints = {
        croce(nullable: false, blank: false, display: false)
        tipoTurno(nullable: false)
        giorno(nullable: false)
        inizio(nullable: false)
        fine(nullable: false)
        funzione1(nullable: true)
        funzione2(nullable: true)
        funzione3(nullable: true)
        funzione4(nullable: true)
        militeFunzione1(nullable: true)
        militeFunzione2(nullable: true)
        militeFunzione3(nullable: true)
        militeFunzione4(nullable: true)
        modificaFunzione1(nullable: true)
        modificaFunzione2(nullable: true)
        modificaFunzione3(nullable: true)
        modificaFunzione4(nullable: true)
        oreMilite1(nullable: true)
        oreMilite2(nullable: true)
        oreMilite3(nullable: true)
        oreMilite4(nullable: true)
        problemiFunzione1()
        problemiFunzione2()
        problemiFunzione3()
        problemiFunzione4()
        titoloExtra(nullable: false, blank: true)
        localitàExtra(nullable: false, blank: true)
        note(widget: 'textarea', nullable: true, blank: true)
        assegnato()
    } // end of static constraints

    static mapping = {
        note type: 'text'
    } // end of static mapping

    /**
     * valore di testo restituito per una istanza della classe
     */
    String toString() {
//        return tipoTurno.sigla + '/' + Lib.presentaDataNum(giorno)
//        return tipoTurno.descrizione + ' - ' + Lib.presentaDataCompleta(giorno)
        return Lib.presentaDataCompleta(giorno) + ' - ' + tipoTurno.descrizione
    } // end of toString

    //--pacchetto di funzioni previste in questo turno
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

    /**
     * metodo chiamato automaticamente da Grails
     * prima di creare un nuovo record
     */
    def beforeInsert = {
        int numMaxFunz = 4
        String milFunz
        String modFunz

        //--le funzioni hardcoded sono al massimo 4
        for (int k = 1; k <= numMaxFunz; k++) {
            milFunz = 'militeFunzione' + k
            modFunz = 'modificaFunzione' + k
            if (this."${milFunz}") {
                this."${modFunz}" = new Date().toTimestamp()
            }// fine del blocco if
        } // fine del ciclo for

        if (!note) {
            note = ''
        }// fine del blocco if
    } // end of def beforeInsert

    /**
     * metodo chiamato automaticamente da Grails
     * prima di registrare un record esistente
     */
    def beforeUpdate = {
        int numMaxFunz = 4
        String milFunz
        String modFunz

        //--le funzioni hardcoded sono al massimo 4
        for (int k = 1; k <= numMaxFunz; k++) {
            milFunz = 'militeFunzione' + k
            modFunz = 'modificaFunzione' + k
            if (this.isDirty("${milFunz}")) {
                if (this."${milFunz}") {
                    this."${modFunz}" = new Date().toTimestamp()
                } else {
                    this."${modFunz}" = null
                }// fine del blocco if-else
            }// fine del blocco if
        } // fine del ciclo for

        if (!note) {
            note = ''
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

} // fine della domain classe
