package webambulanze

class Militeturno {

    //--tabella di incrocio - ridondante, ma velocizza la presentazione - costruita in background
    Croce croce //--ridondante, ma semplifica i filtri
    Milite milite
    Date giorno //--ridondante, ma semplifica i filtri
    Turno turno
    Funzione funzione
    int ore //--ridondante, ma semplifica i filtri
    String dettaglio

    /**
     * regolazione delle proprietà di ogni campo
     * l'ordine con cui vengono elencati qui, viene rispettato nella lista e nella scheda standard
     * la possibilità di avere valori nulli, di default è false
     */
    static constraints = {
        croce(nullable: false, blank: false)
        milite(nullable: false, blank: false)
        giorno(blank: false)
        turno(nullable: false, blank: false)
        funzione(blank: true)
        ore(blank: true)
        dettaglio(nullable: true, blank: true)
    } // end of static constraints

    static mapping = {
    } // end of static mapping

    /**
     * valore di testo restituito per una istanza della classe
     * @todo da regolare (obbligatorio)
     */
    String toString() {
        return milite.toString()
    } // end of toString

    /**
     * metodo chiamato automaticamente da Grails
     * prima di creare un nuovo record
     */
    def beforeInsert = {
        def stop
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
