package webambulanze

import liquibase.util.csv.opencsv.CSVReader

/**
 * Created with IntelliJ IDEA.
 * User: Gac
 * Date: 23-12-12
 * Time: 06:25
 */
class LibFile {

    private static String csvSuffix = '.csv'

    // Legge un file formattato csv
    // Legge la prima riga dei titoli SOLO per creare le mappe
    // Crea una mappa (titolo=valore) per ogni riga (esclusi i titoli)
    // Titolo e valore sono SEMPRE stringhe
    // Restituisce, per ogni riga, una mappa con TUTTE le colonne
    // Eventualmente vuote
    public static ArrayList leggeCsv(String filePath) {
        ArrayList righe = null
        def titoli = null
        LinkedHashMap mappa
        CSVReader reader
        List listaRighe = null
        def singolaRiga

        // Controllo suffisso
        if (!filePath.endsWith(csvSuffix)) {
            filePath += csvSuffix
        }// fine del blocco if

        try { // prova ad eseguire il codice
            InputStreamReader stream = new InputStreamReader(new URL(filePath).openStream(), "UTF-8")
            reader = new CSVReader(stream)
        } catch (Exception unErrore) { // intercetta l'errore
            //log.error 'Non ho trovato il file '+filePath
        }// fine del blocco try-catch

        if (reader) {
            titoli = reader.readNext()
            listaRighe = reader.readAll()
        }// fine del blocco if

        if (listaRighe) {
            righe = new ArrayList()
            listaRighe.each {
                singolaRiga = it
                mappa = new LinkedHashMap()
                for (int k = 0; k < titoli.length; k++) {
                    mappa.put(titoli[k], singolaRiga[k])
                } // fine del ciclo for
                righe.add(mappa)
            } // fine del ciclo each
        }// fine del blocco if

        // valore di ritorno
        return righe
    }// fine della closure

} // fine della classe
