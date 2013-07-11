package webambulanze

/**
 * Created with IntelliJ IDEA.
 * User: Gac
 * Date: 3-6-13
 * Time: 19:59
 */
public enum TipoViaggio {

    auto118('118', 'Servizio automedica del 118'),
    ordinario('ord', 'Servizio ambulanza ordinario (per adesso non funziona)'),
    dializzati('dia', 'Servizio trasporto dializzati (per adesso non funziona)'),
    interno('int', 'Servizio interno (per adesso non funziona)')

    String sigla
    String nome

    TipoViaggio(String sigla, String nome) {
        this.sigla = sigla
        this.nome = nome
    }// fine del metodo costruttore

    /**
     * valore di testo restituito per una istanza della classe
     */
    String toString() {
        return sigla
    } // end of toString

    public static ArrayList getListaNomi() {
        ArrayList lista = new ArrayList()
        String nome

        values()?.each {
            nome = it.nome
            lista.add(nome)
        }// fine del ciclo each

        return lista
    }// fine del metodo statico

    public static TipoViaggio getDaSigla(String sigla) {
        TipoViaggio tipoViaggio = null
        String siglaCorrente

        values()?.each {
            siglaCorrente = it.sigla
            if (siglaCorrente.equals(sigla)) {
                tipoViaggio = it
            }// fine del blocco if
        }// fine del ciclo each

        return tipoViaggio
    }// fine del metodo statico

}// fine della classe Enumeration
