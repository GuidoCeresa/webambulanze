package webambulanze

class AutomezzoService {

    private static String TAG_TARGA = 'targa'
    private static String TAG_SIGLA = 'sigla'

    public static ArrayList getAllTarga() {
        return getAllTarga((Croce) null)
    }// fine del metodo

    public static ArrayList getAllTarga(Croce croce) {
        return getAllTargaSigla(croce, TAG_TARGA)
    }// fine del metodo


    public static ArrayList getAllSigla() {
        return getAllSigla((Croce) null)
    }// fine del metodo

    public static ArrayList getAllSigla(Croce croce) {
        return getAllTargaSigla(croce, TAG_SIGLA)
    }// fine del metodo

    private static ArrayList getAllTargaSigla(Croce croce, String tag) {
        ArrayList lista
        long croceId = 0

        if (croce) {
            croceId = croce.id
        }// fine del blocco if

        if (croceId > 0) {
            lista = Automezzo.executeQuery("select distinct a.${tag} from Automezzo a where croce_id=${croceId}")
        } else {
            lista = Automezzo.executeQuery("select distinct a.${tag} from Automezzo a")
        }// fine del blocco if-else

        return lista
    }// fine del metodo

} // end of Service Class
