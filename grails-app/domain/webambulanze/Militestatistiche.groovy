package webambulanze

class Militestatistiche {

    //--tabella di incrocio - ridondante, ma velocizza la presentazione - costruita in background
    Croce croce //--ridondante, ma semplifica i filtri
    Milite milite
    int turni = 0
    int ore = 0
    int funz1 = 0
    int funz2 = 0
    int funz3 = 0
    int funz4 = 0
    int funz5 = 0
    int funz6 = 0
    int funz7 = 0
    int funz8 = 0
    int funz9 = 0

    /**
     * regolazione delle proprietà di ogni campo
     * l'ordine con cui vengono elencati qui, viene rispettato nella lista e nella scheda standard
     * la possibilità di avere valori nulli, di default è false
     */
    static constraints = {
        croce(nullable: false, blank: false)
        milite(nullable: false, blank: false)
        turni(nullable: true)
        ore(nullable: true)
        funz1(blank: true)
        funz2(blank: true)
        funz3(blank: true)
        funz4(blank: true)
        funz5(blank: true)
        funz6(blank: true)
        funz7(blank: true)
        funz8(blank: true)
        funz9(blank: true)
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
