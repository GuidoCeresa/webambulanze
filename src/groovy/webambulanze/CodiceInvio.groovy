package webambulanze

/**
 * Created with IntelliJ IDEA.
 * User: Gac
 * Date: 11-4-13
 * Time: 14:25
 */
public enum CodiceInvio {
    B('B-Bianco'),
    V('V-Verde'),
    G('G-Giallo'),
    R('R-Rosso')

    String nome

    CodiceInvio(String nome) {
        this.nome = nome
    }// fine del metodo costruttore

    /**
     * valore di testo restituito per una istanza della classe
     */
    String toString() {
        return nome
    } // end of toString

}// fine della classe Enumeration
