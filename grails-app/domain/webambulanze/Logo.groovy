package webambulanze

import java.sql.Timestamp

class Logo {

    Croce croceLogo = null
    Timestamp time
    Utente utente
    Ruolo ruolo
    Evento evento
    Livello livello
    Milite milite
    TipoTurno tipoTurno
    Turno turno
    Date giorno
    String dettaglio

    /**
     * regolazione delle proprietà di ogni campo
     * l'ordine con cui vengono elencati qui, viene rispettato nella lista e nella scheda standard
     * la possibilità di avere valori nulli, di default è false
     */
    static constraints = {
        croceLogo(nullable: false, blank: false)
        time(nullable: true)
        utente(nullable: true)
        ruolo(nullable: true)
        evento(nullable: false)
        livello(nullable: false)
        milite(nullable: true)
        tipoTurno(nullable: true)
        turno(nullable: true)
        giorno(nullable: true)
        dettaglio(nullable: true, blank: true)
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
        if (!time) {
            time = new Date().toTimestamp()
        }// fine del blocco if
    } // end of def beforeInsert

    /**
     * metodo chiamato automaticamente da Grails
     * prima di registrare un record esistente
     */
    def beforeUpdate = {
        if (!time) {
            time = new Date().toTimestamp()
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
