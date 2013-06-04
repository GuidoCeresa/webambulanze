package webambulanze

/**
 * Created with IntelliJ IDEA.
 * User: Gac
 * Date: 3-6-13
 * Time: 19:59
 */
public enum TipoViaggio {

    auto118('Servizio automedica del 118'),
    ordinario('Servizio ambulanza ordinario (per adesso non funziona)'),
    dializzati('Servizio trasporto dializzati (per adesso non funziona)'),
    interno('Servizio interno (per adesso non funziona)')

    String nome

    TipoViaggio(String nome) {
        this.nome = nome
    }// fine del metodo costruttore

    /**
     * valore di testo restituito per una istanza della classe
     */
    String toString() {
        return nome
    } // end of toString

}// fine della classe Enumeration
