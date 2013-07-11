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
        int posDefault = lista.size()

        if (lista) {
            value = lista.get(posDefault - 1)
        }// fine del blocco if

        return value
    }// fine del metodo

    public static LuogoEvento getDaNome(String nome) {
        LuogoEvento luogo = null
        String nomeCorrente

        values()?.each {
            nomeCorrente = it.nome
            if (nomeCorrente.equals(nome)) {
                luogo = it
            }// fine del blocco if
        }// fine del ciclo each

        return luogo
    }// fine del metodo statico

}// fine della classe Enumeration
