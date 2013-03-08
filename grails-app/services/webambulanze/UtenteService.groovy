package webambulanze

class UtenteService {

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def logoService

    //--avviso conseguente alle modifiche effettuate
    def avvisoModifiche = { mappa, Utente utente ->
        ArrayList listaMessaggi = new ArrayList()
        String value
        String dettaglio = ''

        if (mappa.pass) {
            value = (String) mappa.pass
            if (!value.equals(utente.pass)) {
                dettaglio += 'Modificata la password di '
                dettaglio += utente.milite
                dettaglio += ' da '
                dettaglio += utente.pass
                dettaglio += ' a '
                dettaglio += mappa.pass
                listaMessaggi.add(dettaglio)
                logoService.setWarn(Evento.utenteModificato, utente.milite, dettaglio)
            }// fine del blocco if
        }// fine del blocco if

        return listaMessaggi
    }// fine del metodo

    //--recupera i nomi di tutti gli utenti ESCLUSO il programatore
    public ArrayList tuttiQuelliDellaCroceSenzaProgrammatore(Croce croce, def params) {
        ArrayList lista = new ArrayList()

        if (croce) {
            lista = Utente.findAllByCroce(croce, params)
            lista = eliminaProgrammatore(lista)
        }// fine del blocco if

        return lista
    }// fine del metodo

    //--recupera i nomi di tutti gli utenti ESCLUSO il programatore
    public ArrayList tuttiSenzaProgrammatore(def params) {
        ArrayList lista = new ArrayList()

        lista = Utente.findAll(params)
        lista = eliminaProgrammatore(lista)
        lista = spostaOspiteInFondo(lista)

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
                if (nick.equals(Cost.PROG_NICK_CRF)) {
                    objProg = utente
                }// fine del blocco if
                if (nick.equals(Cost.PROG_NICK_CRPT)) {
                    objProg = utente
                }// fine del blocco if
            } // fine del ciclo each

            if (objProg) {
                listaOut.remove(objProg)
            }// fine del blocco if
        }// fine del blocco if

        return listaOut
    }// fine del metodo

    //--sposta in fondo un eventuale nome del programmatore
    public ArrayList spostaProgrammatoreInFondo(ArrayList listaUtenti) {

        if (listaUtenti) {
            if (listaUtenti[0].equals(Cost.PROG_NICK_CRF)) {
                listaUtenti.remove(0)
                listaUtenti.add(Cost.PROG_NICK_CRF)
            }// fine del blocco if
            if (listaUtenti[0].equals(Cost.PROG_NICK_CRPT)) {
                listaUtenti.remove(0)
                listaUtenti.add(Cost.PROG_NICK_CRPT)
            }// fine del blocco if
            if (listaUtenti[0].equals(Cost.PROG_NICK_DEMO)) {
                listaUtenti.remove(0)
                listaUtenti.add(Cost.PROG_NICK_DEMO)
            }// fine del blocco if
        }// fine del blocco if

        return listaUtenti
    }// fine del metodo

    //--sposta in fondo un eventuale ospite
    public ArrayList spostaOspiteInFondo(ArrayList listaUtenti) {

        if (listaUtenti) {
            if (listaUtenti[0].equals(Cost.DEMO_OSPITE)) {
                listaUtenti.remove(0)
                listaUtenti.add(Cost.DEMO_OSPITE)
            }// fine del blocco if
            if (listaUtenti[0].equals(Cost.CRPT_OSPITE)) {
                listaUtenti.remove(0)
                listaUtenti.add(Cost.CRPT_OSPITE)
            }// fine del blocco if
        }// fine del blocco if

        return listaUtenti
    }// fine del metodo

} // end of Service Class
