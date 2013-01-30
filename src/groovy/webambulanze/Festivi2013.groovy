package webambulanze

/**
 * Created with IntelliJ IDEA.
 * User: Gac
 * Date: 28-12-12
 * Time: 08:38
 */
public enum Festivi2013 {

    // 6 gennaio epifania               = 6  domenica
    // 10 febbraio carnevale            = 41 domenica
    // 31 marzo pasqua                  = 90 domenica
    // 1° aprile pasquetta              = 91 lunedì
    // 25 aprile liberazione            = 115 giovedì
    // 1° maggio festa del lavoro       = 121 mercoledì
    // 2 giugno festa della repubblica  = 153 domenica
    // 15 agosto ferragosto             = 227 giovedì
    // 1 novembre ognissanti            = 305 venerdì
    // 8 dicembre immacolata            = 342 domenica
    // 25 dicembre natale               = 359 mercoledì
    // 26 dicembre santo stefano        = 360 giovedì
    // 1 gennaio (attenzione al cambio di anno)


    epifania(6),
    carnevale(41),
    pasqua(90),
    pasquetta(91),
    liberazione(115),
    lavoro(121),
    repubblica(153),
    ferragosto(227),
    ognissanti(305),
    immacolata(342),
    natale(359),
    stefano(360),
    capodanno(366)

    public int giorno

    /**
     * Costruttore con parametri.
     */
    Festivi2013(int giorno) {
        this.giorno = giorno
    }// fine del metodo costruttore

    //--controlla il singolo giorno
    static public boolean isFestivo(int numeroGiornoInEsame) {
        boolean festivo = false
        def giorniFestivi
        int numeroGiornoFestivo
        int numeroGiornoPreFestivo

        giorniFestivi = Festivi2013.values()
        giorniFestivi?.each {
            numeroGiornoFestivo = it.giorno
            numeroGiornoPreFestivo = numeroGiornoFestivo - 1
            if (numeroGiornoPreFestivo == numeroGiornoInEsame) {
                festivo = true
            }// fine del blocco if
        }// fine del blocco each

        return festivo
    }// fine del metodo statico

    //--restituisce tutti e solo i giorni festivi dell'anno
    static public ArrayList allGiorni() {
        return Festivi2013.values()
    }// fine del metodo statico

} // fine della classe
