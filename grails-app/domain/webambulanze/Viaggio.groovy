package webambulanze

class Viaggio {
    //--croce di riferimento
    Croce croce

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

    String nomePaziente
    String indirizzoPaziente
    String cittaPaziente
    String etaPaziente

    String prelievo
    String ricovero

    String numeroCartellino         // senza automatismo - viene dalla CO
    int numeroServizio              // progressivo della croce
    int numeroBolla                 // senza automatismo - si legge dal blocchetto
    int numeroViaggio = 0               // progressivo dell'automezzo

    //--Suggerito automaticamente quando si seleziona l'automezzo.
    //--Usa l'ultimo chilometraggio registrato.
    //--Modificabile dall'utente per forzatura.
    //--Segnalazione mail in caso di forzatura.
    int chilometriPartenza = 0

    //--Inserimento manuale
    int chilometriArrivo = 0

    //--Calcolati
    int chilometriPercorsi = 0

    Milite autistaEmergenza
    Milite soccorritoreDae
    Milite soccorritore
    Milite barelliereAffiancamento

    /**
     * regolazione delle proprietà di ogni campo
     * l'ordine con cui vengono elencati qui, viene rispettato nella lista e nella scheda standard
     * la possibilità di avere valori nulli, di default è false
     */
    static constraints = {
        croce(nullable: false, blank: false, display: false)
        giorno()
        inizio()
        fine()
        autistaEmergenza(nullable: false)
        soccorritoreDae(nullable: false)
        soccorritore(nullable: true)
        barelliereAffiancamento(nullable: true)
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
        beforeRegolaChilometri()
    } // end of def beforeInsert

    /**
     * metodo chiamato automaticamente da Grails
     * prima di registrare un record esistente
     */
    def beforeUpdate = {
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
