package webambulanze

/**
 * Created with IntelliJ IDEA.
 * User: Gac
 * Date: 19-1-13
 * Time: 19:19
 */
public enum Evento {

    generico('evento generico', 'evento generico'),
    tabellone('modifiche al tabellone', 'modifiche al tabellone'),

    militeCreato('Creato un nuovo', ''),
    militeModificato('Modificato il', ''),
    militeModificatoNome('Modificato il nome del', ''),
    militeModificatoCognome('Modificato il cognome del', ''),
    militeModificatoTelefono('Modificato il telefono del', ''),
    militeModificataEmail('Modificata la eMail del', ''),
    militeModificataNascita('Modificata la data di nascita del', ''),
    militeModificatoAttivo('Modificato il flag attivo/disattivo del', ''),
    militeModificataScadenzaBLS('Modificata la scadenza del brevetto BLSD del', ''),
    militeModificataScadenzaTrauma('Modificata la scadenza del brevetto ALS del', ''),
    militeModificataScadenzaNonTrauma('Modificata la scadenza del brevetto NonTrauma del', ''),
    militeModificateFunzioni('Modificate le funzioni del milite', ''),

    creatoNuovoTurno('nuovo turno', 'nuovo turno'),
    nuovoTurnoAnnullato('il nuovo turno non è stato creato', 'il nuovo turno non è stato creato'),
    turnoModificato('modifica turno', 'modifica turno'),
    turnoNonModificato('il turno non è stato modificato', 'il turno non è stato modificato'),
    turnoEliminato('il turno è stato eliminato', 'il turno è stato eliminato')

    String avviso
    String posta


    Evento(String avviso, String posta) {
        this.avviso = avviso
        this.posta = posta ? posta : avviso
    }// fine del metodo costruttore

}// fine della classe Enumeration
