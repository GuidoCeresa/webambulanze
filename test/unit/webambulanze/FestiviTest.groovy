package webambulanze

/**
 * Created with IntelliJ IDEA.
 * User: Gac
 * Date: 19-12-13
 * Time: 07:09
 */
class FestiviTest extends GroovyTestCase {

    // Setup logic here
    void setUp() {
    } // fine del metodo iniziale

    // Tear down logic here
    void tearDown() {
    } // fine del metodo iniziale

    void testGiorni2013() {
        def ottenuto

        ottenuto = Festivi.all2013()
        assert ottenuto != null
        assert ottenuto.size() == 13
    } // fine del test

    void testGiorni2014() {
        def ottenuto

        ottenuto = Festivi.all2014()
        assert ottenuto != null
        assert ottenuto.size() == 12
    } // fine del test

    void testGiorni() {
        def ottenuto
        String anno

        anno = '2013'
        ottenuto = Festivi.all(anno)
        assert ottenuto != null
        assert ottenuto.size() == 13

        anno = '2014'
        ottenuto = Festivi.all(anno)
        assert ottenuto != null
        assert ottenuto.size() == 12
    } // fine del test

    //--tipicamente in posizione variabile
    void testPosizionePasqua() {
        int richiesto //progressivo dell'anno
        int ottenuto  //progressivo dell'anno
        int pos       //posizione nell'array dei giorni festivi
        String anno
        ArrayList festivi

        anno = '2013'
        pos = 3
        richiesto = 90
        festivi = Festivi.all(anno)
        assert festivi != null
        assert festivi.size() == 13
        ottenuto = festivi[pos]
        assert ottenuto > 0
        assert ottenuto == richiesto

        anno = '2014'
        pos = 2
        richiesto = 110
        festivi = Festivi.all(anno)
        assert festivi != null
        assert festivi.size() == 12
        ottenuto = festivi[pos]
        assert ottenuto > 0
        assert ottenuto == richiesto
    } // fine del test
} // fine della classe di test
