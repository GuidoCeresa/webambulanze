package webambulanze

/**
 * Created with IntelliJ IDEA.
 * User: Gac
 * Date: 19-4-13
 * Time: 08:56
 */
public enum Organizzazione {
    nessuna('Nessuna', '', '#bbaacc', ''),
    cri('Croce Rossa Italiana', 'CRI_8.png', '#ff0000', 'Croce Rossa Italiana'),
    anpas('Pubblica Assistenza', 'ANPAS.png', '#339966', 'Associazione Nazionale Pubbliche Assistenze'),
    misericordia('Misericordia', 'Misericordie.png', '', "Confederazione Nazionale delle Misericordie d'Italia")

    String nome
    String fileLogo
    String colore
    String wiki

    Organizzazione(String nome, String fileLogo, String colore, String wiki) {
        this.nome = nome
        this.fileLogo = fileLogo
        this.colore = colore
        this.wiki = 'http://it.wikipedia.org/wiki/' + wiki
    }// fine del metodo costruttore

    /**
     * valore di testo restituito per una istanza della classe
     */
    String toString() {
        return nome
    } // end of toString

}// fine della classe Enumeration
