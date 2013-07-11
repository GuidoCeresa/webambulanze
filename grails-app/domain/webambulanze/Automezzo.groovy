package webambulanze

class Automezzo {

    //--croce di riferimento
    Croce croce

    //--tipologia dell'ambulanza
    def TipoAutomezzo tipo

    //--eventuale data di acquisto
    Date dataAcquisto

    //--targa stradale
    String targa = ''

    //--eventuale sigla di riferimento interna (CRI od altro)
    String sigla = ''

    //--marca, modello, allestimento
    String descrizione = ''

    //--riferimenti di un'eventuale donazione
    String donazione = ''

    //--aggiornato automaticamente da ogni nuovo viaggio
    //--se modificato da qui, occorre un avviso
    int chilometriTotaliPercorsi = 0

    //--progressivo dei viaggi effettuati dal mezzo
    //--aggiornato automaticamente da ogni nuovo viaggio
    //--se modificato da qui, occorre un avviso
    int numeroViaggiEffettuati = 0

    /**
     * regolazione delle proprietà di ogni campo
     * l'ordine con cui vengono elencati qui, viene rispettato nella lista e nella scheda standard
     * la possibilità di avere valori nulli, di default è false
     */
    static constraints = {
        croce(nullable: false, blank: false, display: false)
        tipo(nullable: false)
        dataAcquisto(nullable: true)
        sigla(nullable: true, blank: true)
        targa(nullable: false, blank: false)
        descrizione(widget: 'textarea', nullable: true, blank: true)
        donazione(widget: 'textarea', nullable: true, blank: true)
        chilometriTotaliPercorsi(nullable: false)
        numeroViaggiEffettuati(nullable: false)
    } // end of static constraints

    static mapping = {
        descrizione type: 'text'
        donazione type: 'text'
    } // end of static mapping

    /**
     * valore di testo restituito per una istanza della classe
     * @todo da regolare (obbligatorio)
     */
    String toString() {
        return targa
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
