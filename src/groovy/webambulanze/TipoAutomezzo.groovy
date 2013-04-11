package webambulanze

/**
 * Created with IntelliJ IDEA.
 * User: Gac
 * Date: 11-4-13
 * Time: 14:49
 */
public enum TipoAutomezzo {

    amb('ambulanza di trasporto'),
    msb('mezzo di soccorso di base'),
    msa('mezzo di soccorso avanzato'),
    pulmino('pulmino'),
    automedica('automedica')

    String nome

    TipoAutomezzo(String nome) {
        this.nome = nome
    }// fine del metodo costruttore

    /**
     * valore di testo restituito per una istanza della classe
     */
    String toString() {
        return nome
    } // end of toString

}// fine della classe Enumeration
