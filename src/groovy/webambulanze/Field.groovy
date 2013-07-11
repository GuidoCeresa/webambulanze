package webambulanze

/**
 * Created with IntelliJ IDEA.
 * User: Gac
 * Date: 9-7-13
 * Time: 14:53
 */
public enum Field {

    testoObbligatorioModificabileLinkato(true, true, true),
    testoObbligatorioModificabile(true, true, false),
    testo(false, false, false),
    testoLink(false, false, true),
    testoObbEdit(true, true, false),
    testoEdit(false, true, false),
    oraMin(true, true, false),
    lista(true, true, false),

    boolean richiesto
    boolean modificabile
    boolean linkato

    Field(boolean richiesto, boolean modificabile, boolean linkato) {
        this.richiesto = richiesto
        this.modificabile = modificabile
        this.linkato = linkato
    }// fine del metodo costruttore

}// fine della classe Enumeration
