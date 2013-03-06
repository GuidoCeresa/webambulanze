package webambulanze

/**
 * Created with IntelliJ IDEA.
 * User: Gac
 * Date: 19-1-13
 * Time: 19:19
 */
public enum Evento {

    generico('evento generico', ''),
    tabellone('modifiche al tabellone', ''),
    tabelloneVistoTurno('visto il turno nel tabellone', ''),
    statistiche('effettuato il ricalcolo delle statistiche', ''),

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

    utenteCreato('Creato un nuovo', ''),
    utenteModificato('Modificato l', ''),

    turnoCreando('Creazione di un nuovo turno', ''),
    turnoCreandoExtra('Creazione di un nuovo turno extra oltre a quelli già esistenti', ''),
    turnoCreato('È stato creato un nuovo turno', ''),
    turnoAnnullatoNuovo('Il nuovo turno non è stato creato', ''),
    turnoModificato('È stato modificato il turno', ''),
    turnoNonModificato('Non è stato modificato il turno', ''),
    turnoEliminato('È stato eliminato il turno', '')

    String avviso
    String posta

    Evento(String avviso, String posta) {
        this.avviso = avviso
        this.posta = posta ? posta : avviso
    }// fine del metodo costruttore

}// fine della classe Enumeration
