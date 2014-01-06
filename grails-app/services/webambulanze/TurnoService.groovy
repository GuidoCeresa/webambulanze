package webambulanze

class TurnoService {

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def militeService
    def croceService

    //--recupera i turni dell'anno in corso
    public static ArrayList getTurniAnno(Croce croce) {
        ArrayList listaTurni = null
        def turni
        Date oggi = new Date()
        Date primoGennaio = Lib.creaData1Gennaio()

        turni = Turno.findAllByCroceAndGiornoBetween(croce, primoGennaio, oggi)

        if (turni) {
            listaTurni = new ArrayList()
            turni?.each {
                listaTurni.add(it.toString())
            } // fine del ciclo each
        }// fine del blocco if

        return listaTurni
    }// fine del metodo

    //--recupera i turni dell'anno in corso
    public static ArrayList getTurniAnnoId(Croce croce) {
        ArrayList listaTurniId = null
        def turni
        Turno turno
        Date oggi = new Date()
        Date primoGennaio = Lib.creaData1Gennaio()

        turni = Turno.findAllByCroceAndGiornoBetween(croce, primoGennaio, oggi)

        if (turni) {
            listaTurniId = new ArrayList()
            turni?.each {
                turno = (Turno) it
                listaTurniId.add(turno.id)
            } // fine del ciclo each
        }// fine del blocco if

        return listaTurniId
    }// fine del metodo

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
    //--se la data del turno da creare è precedente od uguale alla data corrente (turni standard) il milite non può creare il turno
    //--se la data del turno da creare è precedente alla data corrente (turni extra) il milite non può creare il turno
    //--se la data del turno da creare è successiva alla data corrente (turni standard)
    //--se la data del turno da creare è uguale o successiva alla data corrente (turni extra)
    //--    il milite può creare un nuovo turno (standard) solo se la croce ha il relativo flag abilitato (da sviluppare)
    //--    il milite può creare un nuovo turno (extra) solo se la croce ha il relativo flag abilitato (da sviluppare)
    //--i militi non possono MAI creare turni per date antecedenti quella corrente
    //--gli ospiti non possono mai creare turni
    public boolean isPossibileCreareTurno(Croce croce, Date giornoNuovoTurno, TipoTurno tipoTurno) {
        boolean possibileCreareTurno = false
        Date giornoCorrente = new Date()
        int numGiornoCorrente
        int numGiornoNuovoTurno
        boolean giornoBloccato = false
        boolean militePuoCreareTurnoStandard = false
        boolean militePuoCreareTurnoExtra = false

        if (croce) {
            militePuoCreareTurnoStandard = croceService.militePuoCreareTurnoStandard(croce)
            militePuoCreareTurnoExtra = croceService.militePuoCreareTurnoExtra(croce)
        }// fine del blocco if

        //--nel giorno corrente i militi non possono mai creare nuovi turni standard
        //--nel giorno corrente i militi possono creare nuovi turni extra (sa hanno il flag della croce abilitato)
        numGiornoNuovoTurno = Lib.getNumGiornoAssoluto(giornoNuovoTurno)
        numGiornoCorrente = Lib.getNumGiornoAssoluto(new Date())
        if (tipoTurno.multiplo) {
            if (numGiornoNuovoTurno < numGiornoCorrente) {
                giornoBloccato = true
            }// fine del blocco if
        } else {
            if (numGiornoNuovoTurno <= numGiornoCorrente) {
                giornoBloccato = true
            }// fine del blocco if
        }// fine del blocco if-else

        if (militeService?.isLoggatoAdminOrMore()) {
            return true
        }// fine del blocco if

        if (militeService?.isLoggatoMilite()) {
            if (giornoBloccato) {
                possibileCreareTurno = false
            } else {
                if (tipoTurno.multiplo) {
                    if (militePuoCreareTurnoExtra) {
                        possibileCreareTurno = true
                    } else {
                        possibileCreareTurno = false
                    }// fine del blocco if-else
                } else {
                    if (militePuoCreareTurnoStandard) {
                        possibileCreareTurno = true
                    } else {
                        possibileCreareTurno = false
                    }// fine del blocco if-else
                }// fine del blocco if-else
            }// fine del blocco if-else
            return possibileCreareTurno
        }// fine del blocco if

        if (croce && croce.sigla.equals(Cost.CROCE_DEMO)) {
            if (giornoBloccato) {
                possibileCreareTurno = false
            } else {
                possibileCreareTurno = true
            }// fine del blocco if-else
        }// fine del blocco if-else

        return possibileCreareTurno
    }// fine del metodo


} // end of Service Class
