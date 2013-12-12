package webambulanze

class TurnoService {

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def militeService

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

    //--controlla se il turno può essere creato
    //--il prog, il custode e l'admin possono SEMPRE creare un turno
    //--se la data del turno da creare è uguale o successiva alla data corrente
    //--    il milite può creare un nuovo turno (standard) solo se la croce ha il relativo flag abilitato (da sviluppare)
    //--    il milite può creare un nuovo turno (extra) solo se la croce ha il relativo flag abilitato (da sviluppare)
    //--i militi non possono MAI creare turni per date antecedenti quella corrente
    //--gli ospiti non possono mai creare turni
    public boolean isPossibileCreareTurno(Croce croce, Date giorno, TipoTurno tipoTurno) {
        boolean possibileCreareTurno = false
        Date giornoCorrente = new Date()
        boolean giornoScaduto = true

        if (giornoCorrente < giorno) {
            giornoScaduto = false
        }// fine del blocco if

        if (militeService?.isLoggatoAdminOrMore()) {
            return true
        }// fine del blocco if

        if (militeService?.isLoggatoMilite()) {
            if (giornoScaduto) {
                possibileCreareTurno = false
            } else {
                if (tipoTurno.multiplo) {
                    possibileCreareTurno = true
                } else {
                    possibileCreareTurno = false
                }// fine del blocco if-else
            }// fine del blocco if-else
            return possibileCreareTurno
        }// fine del blocco if

        if (militeService?.isLoggatoOspite()) {
            return false
        }// fine del blocco if

        return possibileCreareTurno
    }// fine del metodo

} // end of Service Class
