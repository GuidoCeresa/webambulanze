package webambulanze

class Viaggio {
    //--croce di riferimento
    Croce croce

    //--giorno di svolgimento del viaggio (giorno iniziale se termina il mattino dopo)
    //--ore e minuti sono sempre a zero
    Date giorno
    //--giorno, ora e minuto di inizio viaggio
    Date inizio
    //--giorno, ora e minuto di fine viaggio
    Date fine

    LuogoEvento luogoEvento
    Patologia patologia
    CodiceInvio codiceInvio
    CodiceRicovero codiceRicovero

    String nomePaziente
    String indirizzoPaziente
    String cittaPaziente
    String etaPaziente

    String prelievo
    String ricovero

    String numeroCartellino
    int numeroServizio
    int numeroBolla

    /**
     * regolazione delle proprietà di ogni campo
     * l'ordine con cui vengono elencati qui, viene rispettato nella lista e nella scheda standard
     * la possibilità di avere valori nulli, di default è false
     */
    static constraints = {
        croce()
        giorno()
        inizio()
        fine()
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
    } // end of def beforeInsert

    /**
     * metodo chiamato automaticamente da Grails
     * prima di registrare un record esistente
     */
    def beforeUpdate = {
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
