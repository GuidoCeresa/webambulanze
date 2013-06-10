package webambulanze

class TurnoService {

    //--recupera i turni degli ultimi due giorni
    public static ArrayList getLastTwoDays(Croce croce) {
        ArrayList listaTurni = null
        def turni
        Date giornoIniziale = new Date()
        Date oggi = new Date()

        //--due giorni
        giornoIniziale--
        giornoIniziale--

        turni = Turno.findAllByCroceAndGiornoBetween(croce, giornoIniziale, oggi)

        if (turni) {
            listaTurni = new ArrayList()
            turni?.each {
                listaTurni.add(it.toString())
            } // fine del ciclo each
        }// fine del blocco if

        return listaTurni
    }// fine del metodo

    //--recupera i turni degli ultimi due giorni
    public static ArrayList getLastTwoDaysDescrizione(Croce croce) {
        ArrayList listaTurni = null
        def turni
        Date giornoIniziale = new Date()
        Date oggi = new Date()
        Turno turno
        TipoTurno tipoTurno

        //--due giorni
        giornoIniziale--
        giornoIniziale--

        turni = Turno.findAllByCroceAndGiornoBetween(croce, giornoIniziale, oggi)

        if (turni) {
            listaTurni = new ArrayList()
            turni?.each {
                turno = (Turno) it
                tipoTurno = turno.tipoTurno
                if (tipoTurno) {
                    listaTurni.add(tipoTurno.descrizione)
                }// fine del blocco if
            } // fine del ciclo each
        }// fine del blocco if

        return listaTurni
    }// fine del metodo

    //--recupera i turni degli ultimi due giorni
    public static ArrayList getLastTwoDaysId(Croce croce) {
        ArrayList listaTurniId = null
        def turni
        Date giornoIniziale = new Date()
        Date oggi = new Date()
        Turno turno

        //--due giorni
        giornoIniziale--
        giornoIniziale--

        turni = Turno.findAllByCroceAndGiornoBetween(croce, giornoIniziale, oggi)

        if (turni) {
            listaTurniId = new ArrayList()
            turni?.each {
                turno = (Turno) it
                listaTurniId.add(turno.id)
            } // fine del ciclo each
        }// fine del blocco if

        return listaTurniId
    }// fine del metodo

} // end of Service Class
