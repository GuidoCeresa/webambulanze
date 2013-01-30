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

    militeCreato('creato un nuovo milite', ''),
    militeModificato('modificato il milite', ''),
    militeModificatoTelefono('modificato il telefono del milite', ''),
    militeModificataEmail('modificata la eMail del milite', ''),
    militeModificataNascita('modificata la data di nascita del milite', ''),
    militeModificatoAttivo('modificato il flag attivo/disattivo del milite', ''),
    militeModificataScadenzaBLS('modificata la scadenza del brevetto BLSD del milite', ''),
    militeModificataScadenzaTrauma('modificata la scadenza del brevetto ALS del milite', ''),
    militeModificataScadenzaNonTrauma('modificata la scadenza del brevetto NonTrauma del milite', ''),
    militeModificateFunzioni('modificate le funzioni del milite', ''),

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
