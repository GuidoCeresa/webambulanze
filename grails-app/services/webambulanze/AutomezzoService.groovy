package webambulanze

class AutomezzoService {

    public static ArrayList getAllTarga() {
        ArrayList lista
        lista = Automezzo.executeQuery("select distinct a.targa from Automezzo a")

        return lista
    }// fine del metodo

    public static ArrayList getAllSigla() {
        ArrayList lista
        lista = Automezzo.executeQuery("select distinct a.sigla from Automezzo a")

        return lista
    }// fine del metodo

} // end of Service Class
