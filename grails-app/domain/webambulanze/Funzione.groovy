package webambulanze

class Funzione {

    //--croce di riferimento
    Croce croce

    //--ordine di presentazione nelle liste
    int ordine = 0

    //--sigla di riferimento interna
    String sigla = ''

    //--descrizione per il tabellone
    String siglaVisibile = ''

    //--descrizione per il tabellone
    String descrizione = ''

    //--elenco funzioni dipendenti da questa
    //--stringa separata da virgole, contenente un elenco delle sigle
    //--delle altre funzioni di questa croce collegate a questa funzione.
    //--serve per abilitare automaticamente alle altre funzioni quando si sceglie questa funzione
    String funzioniDipendenti = ''

    // regolazione delle proprietà di ogni campo
    // l'ordine con cui vengono elencati qui, viene rispettato nella lista e nella scheda standard
    // la possibilità di avere valori nulli, di default è false
    static constraints = {
        croce(nullable: false, blank: false)
        ordine()
        sigla(nullable: false, blank: false)
        siglaVisibile(nullable: false, blank: false)
        descrizione(nullable: false, blank: false)
        funzioniDipendenti(nullable: false, blank: true)
    } // end of static constraints

    // valore di testo restituito per una istanza della classe
    String toString() {
        sigla
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

} // end of Class
