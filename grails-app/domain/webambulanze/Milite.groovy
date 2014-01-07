package webambulanze

class Milite {

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def militeService

    //--croce di riferimento
    Croce croce

    //--dati anagrafici
    String nome = ''
    String cognome = ''
    String telefonoCellulare = ''
    String telefonoFisso = ''
    String email = ''
    String note = ''
    Date dataNascita = null

    //--dati associazione
    boolean dipendente = false
    boolean attivo = true

    //--scadenza certificati
    //--data di scadenza del certificato BSD
    //--se non valorizzata, il milite non ha acquisito il certificato
    Date scadenzaBLSD = null
    //--data di scadenza del certificato Trauma
    //--se non valorizzata, il milite non ha acquisito il certificato
    Date scadenzaTrauma = null
    //--data di scadenza del certificato Non Trauma
    //--se non valorizzata, il milite non ha acquisito il certificato
    Date scadenzaNonTrauma = null

    int oreAnno
    int turniAnno
    int oreExtra

    /**
     * regolazione delle proprietà di ogni campo
     * l'ordine con cui vengono elencati qui, viene rispettato nella lista e nella scheda standard
     * la possibilità di avere valori nulli, di default è false
     */
    static constraints = {
        croce(nullable: false, blank: false, display: false)
        cognome(nullable: false, blank: false)
        nome(nullable: false, blank: false)
        dataNascita(nullable: true)
        telefonoCellulare(nullable: true, blank: true)
        telefonoFisso(nullable: true, blank: true)
        email(email: true, nullable: true, blank: true)
        scadenzaBLSD(nullable: true)
        scadenzaTrauma(nullable: true)
        scadenzaNonTrauma(nullable: true)
        note(widget: 'textarea', nullable: true, blank: true)
        dipendente(nullable: true)
        attivo(nullable: true)
        oreAnno(display: false)
        turniAnno(display: false)
        oreExtra(nullable: true)
    } // end of static constraints

    static mapping = {
        note type: 'text'
    } // end of static mapping

    /**
     * valore di testo restituito per una istanza della classe
     */
    String toString() {
        cognome + ' ' + nome
    } // end of toString

    String getCognomeNome() {
        cognome + ' ' + nome
    } // end of toString

    String getNomeCognome() {
        nome + ' ' + cognome
    } // end of toString


    String getCognomeNomePuntato() {
        String base = cognome
        String aggiunta

        if (nome) {
            aggiunta = nome
            aggiunta = Lib.primaMaiuscola(aggiunta)
            aggiunta = aggiunta.substring(0, 1)
            base += '.'
            base += aggiunta
        }// fine del blocco if

        return base
    } // end of toString

    String getNomeCognomePuntato() {
        String base = nome
        String aggiunta

        if (cognome) {
            aggiunta = cognome
            aggiunta = Lib.primaMaiuscola(aggiunta)
            aggiunta = aggiunta.substring(0, 1)
            base += '.'
            base += aggiunta
        }// fine del blocco if

        return base
    } // end of toString

    /**
     * metodo chiamato automaticamente da Grails
     * prima di creare un nuovo record
     */
    def beforeInsert = {
        cognome = primaMaiuscola(cognome)
        nome = primaMaiuscola(nome)
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

    /**
     * Forza il primo carattere della stringa a maiuscolo
     *
     * @param entrata stringa in ingresso
     * @return uscita string in uscita
     */
    public static String primaMaiuscola(String entrata) {
        // variabili e costanti locali di lavoro
        String uscita = entrata
        String primo
        String rimanente

        if (entrata) {
            primo = entrata.substring(0, 1)
            primo = primo.toUpperCase()
            rimanente = entrata.substring(1)
            uscita = primo + rimanente
        }// fine del blocco if

        // valore di ritorno
        return uscita
    } // fine della closure

    /**
     * Forza il primo carattere della stringa a maiuscolo
     * Forza tutti gli altri caratteri della stringa a minuscolo
     *
     * @param entrata stringa in ingresso
     * @return uscita string in uscita
     */
    public static String soloPrimaMaiuscola(String entrata) {
        return primaMaiuscola(entrata.toLowerCase())
    } // fine della closure

} // fine della domain classe
