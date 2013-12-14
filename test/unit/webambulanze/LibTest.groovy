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

        richiesto = 'sab'
        ottenuto = Lib.getGiornoSettimana()
        assert ottenuto == richiesto
    } // fine del test

} // fine della classe di test
