package webambulanze

/**
 * Created with IntelliJ IDEA.
 * User: Gac
 * Date: 19-12-13
 * Time: 07:10
 */
public enum Festivi {

    capodanno(1, 1),
    epifania(6, 6),
    carnevale(41, 0),
    pasqua(90, 110),
    pasquetta(91, 111),
    liberazione(115, 115),
    lavoro(121, 121),
    repubblica(153, 153),
    ferragosto(227, 227),
    ognissanti(305, 305),
    immacolata(342, 342),
    natale(359, 359),
    stefano(360, 360)

    private int anno13
    private int anno14

    /**
     * Costruttore con parametri.
     */
    Festivi(int anno13, int anno14) {
        this.setAnno13(anno13)
        this.setAnno14(anno14)
    }// fine del metodo costruttore

    //--restituisce tutti e solo i giorni festivi dell'anno
    static public ArrayList<Integer> all2013() {
        ArrayList<Integer> giorni = new ArrayList<Integer>()
        ArrayList<Festivi> festivi = values()
        Festivi festivo
        int numProgressivoGiorno

        festivi.each {
            festivo = it
            numProgressivoGiorno = festivo.getAnno13()

            if (numProgressivoGiorno > 0) {
                giorni.add(numProgressivoGiorno)
            }// fine del blocco if
        } // fine del ciclo each

        return giorni
    }// fine del metodo statico

    //--restituisce tutti e solo i giorni festivi dell'anno
    static public ArrayList<Integer> all2014() {
        ArrayList<Integer> giorni = new ArrayList<Integer>()
        ArrayList<Festivi> festivi = values()
        Festivi festivo
        int numProgressivoGiorno

        festivi.each {
            festivo = it
            numProgressivoGiorno = festivo.getAnno14()

            if (numProgressivoGiorno > 0) {
                giorni.add(numProgressivoGiorno)
            }// fine del blocco if
        } // fine del ciclo each

        return giorni
    }// fine del metodo statico

    //--restituisce tutti e solo i giorni festivi dell'anno
    static public ArrayList<Integer> all(String anno) {
        ArrayList<Integer> giorni = new ArrayList<Integer>()
        ArrayList anniValidi = ['2013', '2014']

        if (anno && anno in anniValidi) {
            switch (anno) {
                case anniValidi[0]:
                    giorni = all2013()
                    break
                case anniValidi[1]:
                    giorni = all2014()
                    break
                default: // caso non definito
                    break
            } // fine del blocco switch
        }// fine del blocco if

        return giorni
    }// fine del metodo statico

    int getAnno13() {
        return anno13
    }

    void setAnno13(int anno13) {
        this.anno13 = anno13
    }

    int getAnno14() {
        return anno14
    }

    void setAnno14(int anno14) {
        this.anno14 = anno14
    }
} // fine della classe
