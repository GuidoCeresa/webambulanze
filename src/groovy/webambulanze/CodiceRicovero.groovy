package webambulanze

/**
 * Created with IntelliJ IDEA.
 * User: Gac
 * Date: 11-4-13
 * Time: 14:26
 */
public enum CodiceRicovero {

    rifiuto('0-Rifiuto ricovero'),
    normale('1-Normale'),
    urgente('2-Urgente'),
    emergenza('3-Emergenza'),
    deceduto('4-Deceduto')

    String nome

    CodiceRicovero(String nome) {
        this.nome = nome
    }// fine del metodo costruttore

    /**
     * valore di testo restituito per una istanza della classe
     */
    String toString() {
        return nome
    } // end of toString

}// fine della classe Enumeration
