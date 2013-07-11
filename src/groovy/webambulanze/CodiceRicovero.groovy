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

    /**
     * lista di tutti gli elementi dell'Enumeration
     */
    static ArrayList getAll() {
        ArrayList lista = new ArrayList()

        values()?.each {
            lista.add(it.nome)
        } // fine del ciclo each

        return lista
    }// fine del metodo

    /**
     * elemento di default nelle liste
     */
    static String get() {
        String value = ''
        ArrayList lista = getAll()
        int posDefault = 2

        if (lista) {
            value = lista.get(posDefault - 1)
        }// fine del blocco if

        return value
    }// fine del metodo

    public static CodiceRicovero getDaNome(String nome) {
        CodiceRicovero codiceRicovero = null
        String nomeCorrente

        values()?.each {
            nomeCorrente = it.nome
            if (nomeCorrente.equals(nome)) {
                codiceRicovero = it
            }// fine del blocco if
        }// fine del ciclo each

        return codiceRicovero
    }// fine del metodo statico

}// fine della classe Enumeration
