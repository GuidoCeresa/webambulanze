package webambulanze

class UtenteService {

    //--recupera i nomi di tutti gli utenti ESCLUSO il programatore
    public ArrayList utentiCustodiOrMore(Croce croce, def params) {
        ArrayList lista = new ArrayList()

        if (croce) {
            lista = Utente.findAllByCroce(croce, params)
            lista = eliminaProgrammatore(lista)
        }// fine del blocco if

        return lista
    }// fine del metodo

    //--recupera i nomi di tutti gli utenti ESCLUSO il programatore
    public ArrayList utentiCustodiOrMore(def params) {
        ArrayList lista = new ArrayList()
        Croce croce

        if (croce) {
            lista = Utente.findAll(params)
            lista = eliminaProgrammatore(lista)
        }// fine del blocco if

        return lista
    }// fine del metodo

    //--elimina il programatore
    private static ArrayList eliminaProgrammatore(ArrayList listaIn) {
        ArrayList listaOut = listaIn
        String nick
        Utente utente
        def objProg

        if (listaIn) {
            listaIn?.each {
                utente = (Utente) it
                nick = utente.username
                if (nick.equals(Cost.PROG_NICK)) {
                    objProg = utente
                }// fine del blocco if
            } // fine del ciclo each

            if (objProg) {
                listaOut.remove(objProg)
            }// fine del blocco if
        }// fine del blocco if

        return listaOut
    }// fine del metodo

} // end of Service Class
