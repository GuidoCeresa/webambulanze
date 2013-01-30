package webambulanze

/**
 * Created with IntelliJ IDEA.
 * User: Gac
 * Date: 3-10-12
 * Time: 07:59
 * To change this template use File | Settings | File Templates.
 */
/**
 * Classe Enumerazione.
 */
public enum Giorno {

    lunedì('lun', 'lunedì'),
    martedì('mar', 'martedì'),
    mercoledì('mer', 'mercoledì'),
    giovedì('gio', 'giovedì'),
    venerdì('ven', 'venerdì'),
    sabato('sab', 'sabato'),
    domenica('dom', 'domenica')

    String breve
    String lungo


    Giorno(String breve, String lungo) {
        this.breve = breve
        this.lungo = lungo
    }// fine del metodo costruttore

    // la settimana parte da lunedì che è il numero 1
    private static String getGiorno(int ord, boolean flagBreve) {
        String nome = ''

        ord = ord - 1
        if (ord < 0) {
            ord = 6
        }// fine del blocco if

        Giorno.values()?.each {
            if (it.ordinal() == ord) {
                if (flagBreve) {
                    nome = it.breve
                } else {
                    nome = it.lungo
                }// fine del blocco if-else
            }// fine del blocco if
        }// fine del ciclo each

        return nome
    }// fine del metodo statico


    public static String getShort(int ord) {
        return getGiorno(ord, true)
    }// fine del metodo statico


    public static String getLong(int ord) {
        return getGiorno(ord, false)
    }// fine del metodo statico


    public static String getAllShortString() {
        String stringa = ''
        String sep = ', '

        Giorno.values()?.each {
            stringa += it.getBreve()
            stringa += sep
        }// fine del ciclo each
        stringa = Lib.Txt.levaCoda(stringa, sep)

        return stringa
    }// fine del metodo statico


    public static String getAllLongString() {
        String stringa = ''
        String sep = ', '

        Giorno.values()?.each {
            stringa += it.getLungo()
            stringa += sep
        }// fine del ciclo each
        stringa = Lib.Txt.levaCoda(stringa, sep)

        return stringa
    }// fine del metodo statico


    public static ArrayList getAllShortList() {
        ArrayList lista = new ArrayList()

        Giorno.values()?.each {
            lista.add(it.getBreve())
        }// fine del ciclo each

        return lista
    }// fine del metodo statico


    public static ArrayList getAllLongList() {
        ArrayList lista = new ArrayList()

        Giorno.values()?.each {
            lista.add(it.getLungo())
        }// fine del ciclo each

        return lista
    }// fine del metodo statico


    public int getOrd() {
        return this.ordinal() + 1
    }// fine del metodo

}// fine della classe Enumeration
