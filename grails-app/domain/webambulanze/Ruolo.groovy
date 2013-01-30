package webambulanze

class Ruolo {
    String authority

    static mapping = {
        cache true
    }

    static constraints = {
        authority blank: false, unique: true
    }

    /**
     * valore di testo restituito per una istanza della classe
     */
    String toString() {
        authority.substring(5)
    } // end of toString

}
