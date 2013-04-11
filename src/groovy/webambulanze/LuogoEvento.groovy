package webambulanze

/**
 * Created with IntelliJ IDEA.
 * User: Gac
 * Date: 11-4-13
 * Time: 07:59
 */
public enum LuogoEvento {
    S('S-Strada'),
    P('P-Uffici ed esercizi pubblici'),
    Y('Y-Impianti sportivi'),
    K('K-Casa'),
    L('L-Impianti lavorativi'),
    Q('Q-Scuole'),
    Z('Z-Altri luoghi')

    String nome

    LuogoEvento(String nome) {
        this.nome = nome
    }// fine del metodo costruttore

    /**
     * valore di testo restituito per una istanza della classe
     */
    String toString() {
        return nome
    } // end of toString

}// fine della classe Enumeration
