package webambulanze

class Militestatistiche {

    //--tabella di incrocio - ridondante, ma velocizza la presentazione - costruita in background
    Croce croce //--ridondante, ma semplifica i filtri
    String anno
    Milite milite
    Date ultimo
    int delta
    String status
    int turni = 0
    int ore = 0
    int oreExtra
    int funz1 = 0
    int funz2 = 0
    int funz3 = 0
    int funz4 = 0
    int funz5 = 0
    int funz6 = 0
    int funz7 = 0
    int funz8 = 0
    int funz9 = 0
    int funz10 = 0
    int funz11 = 0
    int funz12 = 0
    int funz13 = 0
    int funz14 = 0
    int funz15 = 0
    int funz16 = 0
    int funz17 = 0
    int funz18 = 0
    int funz19 = 0
    int funz20 = 0

    /**
     * regolazione delle proprietà di ogni campo
     * l'ordine con cui vengono elencati qui, viene rispettato nella lista e nella scheda standard
     * la possibilità di avere valori nulli, di default è false
     */
    static constraints = {
        croce(nullable: false, blank: false)
        anno(blank: false)
        milite(nullable: false, blank: false)
        ultimo(nullable: true)
        delta(blank: true)
        status(nullable: true, blank: true)
        turni(nullable: true)
        ore(nullable: true)
        oreExtra(nullable: true)
        funz1(blank: true)
        funz2(blank: true)
        funz3(blank: true)
        funz4(blank: true)
        funz5(blank: true)
        funz6(blank: true)
        funz7(blank: true)
        funz8(blank: true)
        funz9(blank: true)
        funz10(blank: true)
        funz11(blank: true)
        funz12(blank: true)
        funz13(blank: true)
        funz14(blank: true)
        funz15(blank: true)
        funz16(blank: true)
        funz17(blank: true)
        funz18(blank: true)
        funz19(blank: true)
        funz20(blank: true)
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
