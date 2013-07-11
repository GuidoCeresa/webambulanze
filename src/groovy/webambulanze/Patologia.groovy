package webambulanze

/**
 * Created with IntelliJ IDEA.
 * User: Gac
 * Date: 11-4-13
 * Time: 07:58
 */
public enum Patologia {
    C01('C01-Traumatica'),
    C02('C02-Cardiologica'),
    C03('C03-Respiratoria'),
    C04('C04-Neurologica'),
    C05('C05-Psichiatrica'),
    C06('C06-Neoplastica'),
    C07('C07-Tossicologica'),
    C08('C08-Metabolica'),
    C09('C09-Gastroenterologica'),
    C10('C10-Urologica'),
    C11('C11-Oculistica'),
    C12('C12-Otorinolaringoiatrica'),
    C13('C13-Dermatologica'),
    C14('C14-Ostetrico-Ginecologica'),
    C15('C15-Infettiva'),
    C19('C19-Altra patologia'),
    C20('C20-Patologia non identificata')

    String nome

    Patologia(String nome) {
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

    public static Patologia getDaNome(String nome) {
        Patologia patologia = null
        String nomeCorrente

        values()?.each {
            nomeCorrente = it.nome
            if (nomeCorrente.equals(nome)) {
                patologia = it
            }// fine del blocco if
        }// fine del ciclo each

        return patologia
    }// fine del metodo statico

}// fine della classe Enumeration
