package webambulanze

class Croce {

    //--sigla di riferimento interna
    String sigla = ''

    //--descrizione completa
    String descrizione = ''

    //--riferimento (presidente, responsabile)
    String riferimento

    //--indirizzo
    String indirizzo

    //--recapito principale
    String telefono

    //--posta
    String email = ''

    //--settings
    Settings settings

    //--note
    String note

    /**
     * regolazione delle proprietà di ogni campo
     * l'ordine con cui vengono elencati qui, viene rispettato nella lista e nella scheda standard
     * la possibilità di avere valori nulli, di default è false
     */
    static constraints = {
        sigla(nullable: false,blank: false, unique: true)
        descrizione(nullable: false, blank: false)
        riferimento(nullable: false, blank: true)
        indirizzo(nullable: false, blank: true)
        telefono(nullable: false, blank: true)
        email(email: true)
        settings(nullable: true)
        note(widget: 'textarea', nullable: false, blank: true)
    } // end of static constraints

    /*+
     *
     *
     */
    static mapping = {
        note type: 'text'
    } // end of static mapping

    /**
     * valore di testo restituito per una istanza della classe
     */
    String toString() {
        return sigla
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

