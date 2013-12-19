package webambulanze

/**
 * Created with IntelliJ IDEA.
 * User: Gac
 * Date: 14-12-13
 * Time: 09:11
 */
class LibTest extends GroovyTestCase {

    // Setup logic here
    void setUp() {
    } // fine del metodo iniziale

    // Tear down logic here
    void tearDown() {
    } // fine del metodo iniziale

    void testGiornoDellaSettimana() {
        String richiesto
        String ottenuto
        GregorianCalendar cal
        long millisec
        Date unaData

        cal = new GregorianCalendar(2013, 9, 31, 0, 0, 0)
        millisec = cal.getTimeInMillis()
        unaData = new Date(millisec)
        richiesto = 'gio'
        ottenuto = Lib.getGiornoSettimana(unaData)
        assert ottenuto == richiesto

        cal = new GregorianCalendar(2013, 11, 17, 0, 0, 0)
        millisec = cal.getTimeInMillis()
        unaData = new Date(millisec)
        richiesto = 'mar'
        ottenuto = Lib.getGiornoSettimana(unaData)
        assert ottenuto == richiesto
    } // fine del test

    void testPrimoGennaio() {
        Date richiesto
        Date ottenuto
        GregorianCalendar cal
        long millisec
        Date primoGennaio

        cal = new GregorianCalendar(2013, 0, 1, 0, 0, 0)
        millisec = cal.getTimeInMillis()
        primoGennaio = new Date(millisec)

        ottenuto = Lib.creaData1Gennaio()
        assert ottenuto != null
        assert ottenuto == primoGennaio

        ottenuto = Lib.creaData1Gennaio('2013')
        assert ottenuto != null
        assert ottenuto == primoGennaio

        cal = new GregorianCalendar(2014, 0, 1, 0, 0, 0)
        millisec = cal.getTimeInMillis()
        primoGennaio = new Date(millisec)

        ottenuto = Lib.creaData1Gennaio('2014')
        assert ottenuto != null
        assert ottenuto == primoGennaio
    } // fine del test

    void testTestoBreveGiorniFeriali() {
        String richiesto
        String ottenuto
        GregorianCalendar cal
        long millisec
        Date unaData

        cal = new GregorianCalendar(2013, 11, 19, 0, 0, 0)
        millisec = cal.getTimeInMillis()
        unaData = new Date(millisec)
        richiesto = 'gio'
        ottenuto = Lib.getGiornoSettimana(unaData)
        assert ottenuto == richiesto

        assert Lib.isFeriale(unaData)       //giovedì
        assert Lib.isFeriale(unaData + 1)     //venerdì
        assert Lib.isFestivo(unaData + 2)     //sabato
        assert Lib.isFestivo(unaData + 3)     //domenica
        assert Lib.isFeriale(unaData + 4)     //lunedì
        assert Lib.isFeriale(unaData + 5)     //martedì
        assert Lib.isFeriale(unaData + 6)     //mercoledì
    } // fine del test

    void testNumeroGiorniFeriali() {
        String richiesto
        String ottenuto
        GregorianCalendar cal
        long millisec
        Date unaData

        cal = new GregorianCalendar(2013, 11, 22, 0, 0, 0)
        millisec = cal.getTimeInMillis()
        unaData = new Date(millisec)
        richiesto = 'dom'
        ottenuto = Lib.getGiornoSettimana(unaData)
        assert ottenuto == richiesto

        assert Lib.isDomenica(unaData)  //domenica
        assert Lib.isFestivo(unaData)  //domenica
        assert Lib.isLunedi(unaData + 1)        //lunedì
        assert Lib.isFeriale(unaData + 1)        //lunedì
        assert Lib.isMartedi(unaData + 2)   //martedì
        assert Lib.isFeriale(unaData + 2)   //martedì
        assert Lib.isMercoledi(unaData + 3) //mercoledì
        assert Lib.isFeriale(unaData + 3) //mercoledì
        assert Lib.isGiovedi(unaData + 4)   //giovedì
        assert Lib.isFeriale(unaData + 4)   //giovedì
        assert Lib.isVenerdi(unaData + 5)   //venerdì
        assert Lib.isFeriale(unaData + 5)   //venerdì
        assert Lib.isSabato(unaData + 6)    //sabato
        assert Lib.isFestivo(unaData + 6)    //sabato
    } // fine del test

    void testFestivoAnno() {
        String richiesto
        String ottenuto
        GregorianCalendar cal
        long millisec
        Date unaData
        ArrayList festivi2013 = Festivi.all2013()
        ArrayList festivi2014 = Festivi.all2014()

        //giovedi 21 febbraio 2013
        cal = new GregorianCalendar(2013, 1, 21, 0, 0, 0)
        millisec = cal.getTimeInMillis()
        unaData = new Date(millisec)
        richiesto = 'gio'
        ottenuto = Lib.getGiornoSettimana(unaData)
        assert ottenuto == richiesto
        assert Lib.isGiovedi(unaData)   //giovedì
        assert Lib.isFeriale(unaData)   //giovedì

        //domenica 31 marzo 2013 pasqua
        cal = new GregorianCalendar(2013, 2, 31, 0, 0, 0)
        millisec = cal.getTimeInMillis()
        unaData = new Date(millisec)
        richiesto = 'dom'
        ottenuto = Lib.getGiornoSettimana(unaData)
        assert ottenuto == richiesto
        assert Lib.isDomenica(unaData)   //domenica
        assert Lib.isFestivoAnno(unaData, festivi2013)   //domenica

        //lunedi 1 aprile 2013 pasquetta
        cal = new GregorianCalendar(2013, 3, 1, 0, 0, 0)
        millisec = cal.getTimeInMillis()
        unaData = new Date(millisec)
        richiesto = 'lun'
        ottenuto = Lib.getGiornoSettimana(unaData)
        assert ottenuto == richiesto
        assert Lib.isLunedi(unaData)   //lunedi
        assert Lib.isFestivoAnno(unaData, festivi2013)   //lunedi

        //sabato 21 settembre 2013
        cal = new GregorianCalendar(2013, 8, 21, 0, 0, 0)
        millisec = cal.getTimeInMillis()
        unaData = new Date(millisec)
        richiesto = 'sab'
        ottenuto = Lib.getGiornoSettimana(unaData)
        assert ottenuto == richiesto
        assert Lib.isSabato(unaData)   //sabato
        assert Lib.isFestivoAnno(unaData, festivi2013)   //sabato

        //venerdi 16 maggio 2014
        cal = new GregorianCalendar(2014, 4, 16, 0, 0, 0)
        millisec = cal.getTimeInMillis()
        unaData = new Date(millisec)
        richiesto = 'ven'
        ottenuto = Lib.getGiornoSettimana(unaData)
        assert ottenuto == richiesto
        assert Lib.isVenerdi(unaData)   //venerdi
        assert Lib.isFeriale(unaData)   //venerdi

        //domenica 20 aprile 2014 pasqua
        cal = new GregorianCalendar(2014, 3, 20, 0, 0, 0)
        millisec = cal.getTimeInMillis()
        unaData = new Date(millisec)
        richiesto = 'dom'
        ottenuto = Lib.getGiornoSettimana(unaData)
        assert ottenuto == richiesto
        assert Lib.isDomenica(unaData)   //domenica
        assert Lib.isFestivoAnno(unaData, festivi2014)   //domenica

        //lunedi 21 aprile 2014 pasquetta
        cal = new GregorianCalendar(2014, 3, 21, 0, 0, 0)
        millisec = cal.getTimeInMillis()
        unaData = new Date(millisec)
        richiesto = 'lun'
        ottenuto = Lib.getGiornoSettimana(unaData)
        assert ottenuto == richiesto
        assert Lib.isLunedi(unaData)   //lunedi
        assert Lib.isFestivoAnno(unaData, festivi2014)   //lunedi

        //venerdi 25 aprile 2014 liberazione
        cal = new GregorianCalendar(2014, 3, 25, 0, 0, 0)
        millisec = cal.getTimeInMillis()
        unaData = new Date(millisec)
        richiesto = 'ven'
        ottenuto = Lib.getGiornoSettimana(unaData)
        assert ottenuto == richiesto
        assert Lib.isVenerdi(unaData)   //venerdi
        assert Lib.isFestivoAnno(unaData, festivi2014)   //venerdi

        //sabato 2 agosto 2014
        cal = new GregorianCalendar(2014, 7, 2, 0, 0, 0)
        millisec = cal.getTimeInMillis()
        unaData = new Date(millisec)
        richiesto = 'sab'
        ottenuto = Lib.getGiornoSettimana(unaData)
        assert ottenuto == richiesto
        assert Lib.isSabato(unaData)   //sabato
        assert Lib.isFestivoAnno(unaData, festivi2014)   //sabato

        //domenica 3 agosto 2014
        cal = new GregorianCalendar(2014, 7, 3, 0, 0, 0)
        millisec = cal.getTimeInMillis()
        unaData = new Date(millisec)
        richiesto = 'dom'
        ottenuto = Lib.getGiornoSettimana(unaData)
        assert ottenuto == richiesto
        assert Lib.isDomenica(unaData)   //domenica
        assert Lib.isFestivoAnno(unaData, festivi2014)   //domenica

        //lunedi 4 agosto 2014
        cal = new GregorianCalendar(2014, 7, 4, 0, 0, 0)
        millisec = cal.getTimeInMillis()
        unaData = new Date(millisec)
        richiesto = 'lun'
        ottenuto = Lib.getGiornoSettimana(unaData)
        assert ottenuto == richiesto
        assert Lib.isLunedi(unaData)   //lunedi
        assertFalse(Lib.isFestivoAnno(unaData, festivi2014))   //lunedi
    } // fine del test

    void testFestivoAnnoNonSpecificato() {
        String richiesto
        String ottenuto
        GregorianCalendar cal
        long millisec
        Date unaData
        String festivi2013 = '2013'
        String festivi2014 = '2014'

        //giovedi 21 febbraio 2013
        cal = new GregorianCalendar(2013, 1, 21, 0, 0, 0)
        millisec = cal.getTimeInMillis()
        unaData = new Date(millisec)
        richiesto = 'gio'
        ottenuto = Lib.getGiornoSettimana(unaData)
        assert ottenuto == richiesto
        assert Lib.isGiovedi(unaData)   //giovedì
        assert Lib.isFeriale(unaData)   //giovedì

        //domenica 31 marzo 2013 pasqua
        cal = new GregorianCalendar(2013, 2, 31, 0, 0, 0)
        millisec = cal.getTimeInMillis()
        unaData = new Date(millisec)
        richiesto = 'dom'
        ottenuto = Lib.getGiornoSettimana(unaData)
        assert ottenuto == richiesto
        assert Lib.isDomenica(unaData)   //domenica
        assert Lib.isFestivoAnno(unaData, festivi2013)   //domenica

        //lunedi 1 aprile 2013 pasquetta
        cal = new GregorianCalendar(2013, 3, 1, 0, 0, 0)
        millisec = cal.getTimeInMillis()
        unaData = new Date(millisec)
        richiesto = 'lun'
        ottenuto = Lib.getGiornoSettimana(unaData)
        assert ottenuto == richiesto
        assert Lib.isLunedi(unaData)   //lunedi
        assert Lib.isFestivoAnno(unaData, festivi2013)   //lunedi

        //sabato 21 settembre 2013
        cal = new GregorianCalendar(2013, 8, 21, 0, 0, 0)
        millisec = cal.getTimeInMillis()
        unaData = new Date(millisec)
        richiesto = 'sab'
        ottenuto = Lib.getGiornoSettimana(unaData)
        assert ottenuto == richiesto
        assert Lib.isSabato(unaData)   //sabato
        assert Lib.isFestivoAnno(unaData, festivi2013)   //sabato

        //venerdi 16 maggio 2014
        cal = new GregorianCalendar(2014, 4, 16, 0, 0, 0)
        millisec = cal.getTimeInMillis()
        unaData = new Date(millisec)
        richiesto = 'ven'
        ottenuto = Lib.getGiornoSettimana(unaData)
        assert ottenuto == richiesto
        assert Lib.isVenerdi(unaData)   //venerdi
        assert Lib.isFeriale(unaData)   //venerdi

        //domenica 20 aprile 2014 pasqua
        cal = new GregorianCalendar(2014, 3, 20, 0, 0, 0)
        millisec = cal.getTimeInMillis()
        unaData = new Date(millisec)
        richiesto = 'dom'
        ottenuto = Lib.getGiornoSettimana(unaData)
        assert ottenuto == richiesto
        assert Lib.isDomenica(unaData)   //domenica
        assert Lib.isFestivoAnno(unaData, festivi2014)   //domenica

        //lunedi 21 aprile 2014 pasquetta
        cal = new GregorianCalendar(2014, 3, 21, 0, 0, 0)
        millisec = cal.getTimeInMillis()
        unaData = new Date(millisec)
        richiesto = 'lun'
        ottenuto = Lib.getGiornoSettimana(unaData)
        assert ottenuto == richiesto
        assert Lib.isLunedi(unaData)   //lunedi
        assert Lib.isFestivoAnno(unaData, festivi2014)   //lunedi

        //venerdi 25 aprile 2014 liberazione
        cal = new GregorianCalendar(2014, 3, 25, 0, 0, 0)
        millisec = cal.getTimeInMillis()
        unaData = new Date(millisec)
        richiesto = 'ven'
        ottenuto = Lib.getGiornoSettimana(unaData)
        assert ottenuto == richiesto
        assert Lib.isVenerdi(unaData)   //venerdi
        assert Lib.isFestivoAnno(unaData, festivi2014)   //venerdi

        //sabato 2 agosto 2014
        cal = new GregorianCalendar(2014, 7, 2, 0, 0, 0)
        millisec = cal.getTimeInMillis()
        unaData = new Date(millisec)
        richiesto = 'sab'
        ottenuto = Lib.getGiornoSettimana(unaData)
        assert ottenuto == richiesto
        assert Lib.isSabato(unaData)   //sabato
        assert Lib.isFestivoAnno(unaData, festivi2014)   //sabato

        //domenica 3 agosto 2014
        cal = new GregorianCalendar(2014, 7, 3, 0, 0, 0)
        millisec = cal.getTimeInMillis()
        unaData = new Date(millisec)
        richiesto = 'dom'
        ottenuto = Lib.getGiornoSettimana(unaData)
        assert ottenuto == richiesto
        assert Lib.isDomenica(unaData)   //domenica
        assert Lib.isFestivoAnno(unaData, festivi2014)   //domenica

        //lunedi 4 agosto 2014
        cal = new GregorianCalendar(2014, 7, 4, 0, 0, 0)
        millisec = cal.getTimeInMillis()
        unaData = new Date(millisec)
        richiesto = 'lun'
        ottenuto = Lib.getGiornoSettimana(unaData)
        assert ottenuto == richiesto
        assert Lib.isLunedi(unaData)   //lunedi
        assertFalse(Lib.isFestivoAnno(unaData, festivi2014))   //lunedi
    } // fine del test

    void testPreFestivoAnno() {
        String richiesto
        String ottenuto
        GregorianCalendar cal
        long millisec
        Date unaData
        String festivi2013 = '2013'
        String festivi2014 = '2014'

        //giovedi 21 febbraio 2013
        cal = new GregorianCalendar(2013, 1, 21, 0, 0, 0)
        millisec = cal.getTimeInMillis()
        unaData = new Date(millisec)
        richiesto = 'gio'
        ottenuto = Lib.getGiornoSettimana(unaData)
        assert ottenuto == richiesto
        assert Lib.isGiovedi(unaData)   //giovedì
        assertFalse(Lib.isPreFestivoAnno(unaData,festivi2013))   //giovedì

        //venerdi 22 febbraio 2013
        cal = new GregorianCalendar(2013, 1, 22, 0, 0, 0)
        millisec = cal.getTimeInMillis()
        unaData = new Date(millisec)
        richiesto = 'ven'
        ottenuto = Lib.getGiornoSettimana(unaData)
        assert ottenuto == richiesto
        assert Lib.isVenerdi(unaData)   //venerdi
        assert Lib.isPreFestivoAnno(unaData,festivi2013)   //venerdi

        //domenica 31 marzo 2013 pasqua
        cal = new GregorianCalendar(2013, 2, 31, 0, 0, 0)
        millisec = cal.getTimeInMillis()
        unaData = new Date(millisec)
        richiesto = 'dom'
        ottenuto = Lib.getGiornoSettimana(unaData)
        assert ottenuto == richiesto
        assert Lib.isDomenica(unaData)   //domenica
        assert Lib.isPreFestivoAnno(unaData, festivi2013)   //domenica

        //lunedi 1 aprile 2013 pasquetta
        cal = new GregorianCalendar(2013, 3, 1, 0, 0, 0)
        millisec = cal.getTimeInMillis()
        unaData = new Date(millisec)
        richiesto = 'lun'
        ottenuto = Lib.getGiornoSettimana(unaData)
        assert ottenuto == richiesto
        assert Lib.isLunedi(unaData)   //lunedi
        assertFalse(Lib.isPreFestivoAnno(unaData, festivi2013))   //lunedi

        //sabato 21 settembre 2013
        cal = new GregorianCalendar(2013, 8, 21, 0, 0, 0)
        millisec = cal.getTimeInMillis()
        unaData = new Date(millisec)
        richiesto = 'sab'
        ottenuto = Lib.getGiornoSettimana(unaData)
        assert ottenuto == richiesto
        assert Lib.isSabato(unaData)   //sabato
        assert Lib.isPreFestivoAnno(unaData, festivi2013)   //sabato

        //venerdi 16 maggio 2014
        cal = new GregorianCalendar(2014, 4, 16, 0, 0, 0)
        millisec = cal.getTimeInMillis()
        unaData = new Date(millisec)
        richiesto = 'ven'
        ottenuto = Lib.getGiornoSettimana(unaData)
        assert ottenuto == richiesto
        assert Lib.isVenerdi(unaData)   //venerdi
        assert Lib.isPreFestivoAnno(unaData,festivi2014)   //venerdi

        //domenica 20 aprile 2014 pasqua
        cal = new GregorianCalendar(2014, 3, 20, 0, 0, 0)
        millisec = cal.getTimeInMillis()
        unaData = new Date(millisec)
        richiesto = 'dom'
        ottenuto = Lib.getGiornoSettimana(unaData)
        assert ottenuto == richiesto
        assert Lib.isDomenica(unaData)   //domenica
        assert Lib.isPreFestivoAnno(unaData, festivi2014)   //domenica

        //lunedi 21 aprile 2014 pasquetta
        cal = new GregorianCalendar(2014, 3, 21, 0, 0, 0)
        millisec = cal.getTimeInMillis()
        unaData = new Date(millisec)
        richiesto = 'lun'
        ottenuto = Lib.getGiornoSettimana(unaData)
        assert ottenuto == richiesto
        assert Lib.isLunedi(unaData)   //lunedi
        assertFalse(Lib.isPreFestivoAnno(unaData, festivi2014))   //lunedi

        //giovedi 24 aprile 2014 liberazione
        cal = new GregorianCalendar(2014, 3, 24, 0, 0, 0)
        millisec = cal.getTimeInMillis()
        unaData = new Date(millisec)
        richiesto = 'gio'
        ottenuto = Lib.getGiornoSettimana(unaData)
        assert ottenuto == richiesto
        assert Lib.isGiovedi( unaData)   //giovedi
        assert Lib.isPreFestivoAnno(unaData, festivi2014)   //giovedi

        //venerdi 25 aprile 2014 liberazione
        cal = new GregorianCalendar(2014, 3, 25, 0, 0, 0)
        millisec = cal.getTimeInMillis()
        unaData = new Date(millisec)
        richiesto = 'ven'
        ottenuto = Lib.getGiornoSettimana(unaData)
        assert ottenuto == richiesto
        assert Lib.isVenerdi(unaData)   //venerdi
        assert Lib.isPreFestivoAnno(unaData, festivi2014)   //venerdi

        //sabato 2 agosto 2014
        cal = new GregorianCalendar(2014, 7, 2, 0, 0, 0)
        millisec = cal.getTimeInMillis()
        unaData = new Date(millisec)
        richiesto = 'sab'
        ottenuto = Lib.getGiornoSettimana(unaData)
        assert ottenuto == richiesto
        assert Lib.isSabato(unaData)   //sabato
        assert Lib.isPreFestivoAnno(unaData, festivi2014)   //sabato

        //domenica 3 agosto 2014
        cal = new GregorianCalendar(2014, 7, 3, 0, 0, 0)
        millisec = cal.getTimeInMillis()
        unaData = new Date(millisec)
        richiesto = 'dom'
        ottenuto = Lib.getGiornoSettimana(unaData)
        assert ottenuto == richiesto
        assert Lib.isDomenica(unaData)   //domenica
        assertFalse (Lib.isPreFestivoAnno(unaData, festivi2014))   //domenica

        //lunedi 4 agosto 2014
        cal = new GregorianCalendar(2014, 7, 4, 0, 0, 0)
        millisec = cal.getTimeInMillis()
        unaData = new Date(millisec)
        richiesto = 'lun'
        ottenuto = Lib.getGiornoSettimana(unaData)
        assert ottenuto == richiesto
        assert Lib.isLunedi(unaData)   //lunedi
        assertFalse (Lib.isPreFestivoAnno(unaData, festivi2014))   //lunedi
    } // fine del test

} // fine della classe di test
