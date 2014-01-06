package webambulanze

class Viaggio {
    //--croce di riferimento
    Croce croce

    //--tipologia prestabilita
    TipoViaggio tipoViaggio

    //--automezzo utilizzato
    Turno turno

    //--automezzo utilizzato
    Automezzo automezzo

    //--giorno di svolgimento del viaggio (giorno iniziale se termina il mattino dopo)
    //--ore e minuti sono sempre a zero
    Date giorno
    //--giorno, ora e minuto di inizio viaggio
    Date inizio
    //--giorno, ora e minuto di fine viaggio
    Date fine

    CodiceInvio codiceInvio
    LuogoEvento luogoEvento
    Patologia patologia
    CodiceRicovero codiceRicovero

    String nomePaziente = ''
    String indirizzoPaziente = ''
    String cittaPaziente = ''
    String etaPaziente = ''

    String prelievo = ''
    String ricovero = ''

    String numeroCartellino         // senza automatismo - viene dalla CO
    int numeroBolla = 0          // senza automatismo - si legge dal blocchetto
    int numeroServizio = 0            // progressivo della croce
    int numeroViaggio = 0           // progressivo dell'automezzo

    //--Suggerito automaticamente quando si seleziona l'automezzo.
    //--Usa l'ultimo chilometraggio registrato.
    //--Modificabile dall'utente per forzatura.
    //--Segnalazione mail in caso di forzatura.
    int chilometriPartenza = 0

    //--Inserimento manuale
    int chilometriArrivo = 0

    //--Calcolati
    int chilometriPercorsi = 0

    Milite militeFunzione1
    Milite militeFunzione2
    Milite militeFunzione3
    Milite militeFunzione4

    /**
     * regolazione delle proprietà di ogni campo
     * l'ordine con cui vengono elencati qui, viene rispettato nella lista e nella scheda standard
     * la possibilità di avere valori nulli, di default è false
     */
    static constraints = {
        croce(nullable: false, blank: false, display: false)
        tipoViaggio(nullable: false)
        turno(nullable: false)
        automezzo(nullable: false)
        giorno()
        inizio()
        fine()
        nomePaziente(nullable: true)
        indirizzoPaziente(nullable: true)
        cittaPaziente(nullable: true)
        etaPaziente(nullable: true)
        prelievo(nullable: true)
        ricovero(nullable: true)
        militeFunzione1(nullable: true)
        militeFunzione2(nullable: true)
        militeFunzione3(nullable: true)
        militeFunzione4(nullable: true)
    } // end of static constraints

    static mapping = {
    } // end of static mapping

    /**
     * valore di testo restituito per una istanza della classe
     * @todo da regolare (obbligatorio)
     */
    String toString() {
        return ''
    } // end of toString

    /**
     * metodo chiamato automaticamente da Grails
     * prima di creare un nuovo record
     */
    def beforeInsert = {
        checkNull()
        beforeRegolaChilometri()
    } // end of def beforeInsert

    /**
     * metodo chiamato automaticamente da Grails
     * prima di registrare un record esistente
     */
    def beforeUpdate = {
        checkNull()
        beforeRegolaChilometri()
    } // end of def beforeUpdate

    /**
     * metodo chiamato prima di creare o modificare un record
     */
    private beforeRegolaChilometri() {
        if (chilometriPartenza && chilometriArrivo) {
            chilometriPercorsi = chilometriArrivo - chilometriPartenza
        }// fine del blocco if
    } // fine del metodo

    /**
     * metodo chiamato prima di creare o modificare un record
     */
    private checkNull() {
        if (!nomePaziente) {
            nomePaziente = ''
        }// fine del blocco if
        if (!indirizzoPaziente) {
            indirizzoPaziente = ''
        }// fine del blocco if
        if (!cittaPaziente) {
            cittaPaziente = ''
        }// fine del blocco if
        if (!etaPaziente) {
            etaPaziente = ''
        }// fine del blocco if
        if (!prelievo) {
            prelievo = ''
        }// fine del blocco if
        if (!ricovero) {
            ricovero = ''
        }// fine del blocco if

    } // fine del metodo

    /**
     * metodo chiamato automaticamente da Grails
     * dopo aver creato un nuovo record
     */
    def afterInsert = {
        afterRegolaChilometri()
    } // end of def beforeInsert

    /**
     * metodo chiamato automaticamente da Grails
     * dopo aver registrato un record esistente
     */
    def afterUpdate = {
        afterRegolaChilometri()
    } // end of def beforeUpdate

    /**
     * metodo chiamato dopo aver creato o modificato un record
     */
    public afterRegolaChilometri() {
    } // fine del metodo

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
