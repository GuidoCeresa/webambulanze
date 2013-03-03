package webambulanze

class CroceService {

    // la variabile/proprietà viene iniettata automaticamente
    def grailsApplication

    //--controlla il valore mantenuto nei Settings associati alla croce indicata
    //--recupera la croce corrente
    private int getInt(session, codice) {
        int value = 0
        Croce croce = getCroceCorrente(session)
        Settings settings

        if (croce) {
            settings = croce.settings
        }// fine del blocco if

        if (settings) {
            value = settings."${codice}"
        }// fine del blocco if

        return value
    }// fine del metodo

    //--controlla il flag mantenuto nei Settings associati alla croce indicata
    //--recupera la croce corrente
    private boolean isFlag(session, codice) {
        boolean flag = false
        Croce croce = getCroceCorrente(session)
        Settings settings

        if (croce) {
            settings = croce.settings
        }// fine del blocco if

        if (settings) {
            flag = settings."${codice}"
        }// fine del blocco if

        return flag
    }// fine del metodo

    //--controlla il flag mantenuto nei Settings associati alla croce indicata
    //--recupera la croce corrente
    private static boolean isFlag(Croce croce, codice) {
        boolean flag = false
        Settings settings

        if (croce) {
            settings = croce.settings
        }// fine del blocco if

        if (settings) {
            flag = settings."${codice}"
        }// fine del blocco if

        return flag
    }// fine del metodo

    //--controlla il flag mantenuto nei Settings associati alla croce corrente
    public boolean isMostraSoloMilitiFunzione(Croce croce) {
        return isFlag(croce, Cost.PREF_mostraSoloMilitiFunzione)
    }// fine del metodo

    //--controlla il flag mantenuto nei Settings associati alla croce corrente
    public boolean isMostraMilitiFunzioneAndAltri(Croce croce) {
        return isFlag(croce, Cost.PREF_mostraMilitiFunzioneAndAltri)
    }// fine del metodo

    //--controlla il flag mantenuto nei Settings associati alla croce corrente
    public boolean isMilitePuoInserireAltri(Croce croce) {
        return isFlag(croce, Cost.PREF_militePuoInserireAltri)
    }// fine del metodo

    //--controlla il flag mantenuto nei Settings associati alla croce corrente
    public boolean isMilitePuoModificareAltri(Croce croce) {
        return isFlag(croce, Cost.PREF_militePuoModificareAltri)
    }// fine del metodo

    //--controlla il flag mantenuto nei Settings associati alla croce corrente
    public boolean isMilitePuoCancellareAltri(Croce croce) {
        return isFlag(croce, Cost.PREF_militePuoCancellareAltri)
    }// fine del metodo

    //--controlla il flag mantenuto nei Settings associati alla croce corrente
    public boolean isOrarioTurnoModificabileForm(Croce croce) {
        return isFlag(croce, Cost.PREF_isOrarioTurnoModificabileForm)
    }// fine del metodo

    //--controlla il flag mantenuto nei Settings associati alla croce corrente
    public boolean isCalcoloNotturnoStatistiche(Croce croce) {
        return isFlag(croce, Cost.PREF_calcoloNotturnoStatistiche)
    }// fine del metodo

    //--controlla il flag mantenuto nei Settings associati alla croce corrente
    public boolean fissaLimiteMassimoSingoloTurno(Croce croce) {
        return isFlag(croce, Cost.PREF_fissaLimiteMassimoSingoloTurno)
    }// fine del metodo

    //--controlla il flag mantenuto nei Settings associati alla croce corrente
    public boolean fissaLimiteMassimoSingoloTurno(def session) {
        return fissaLimiteMassimoSingoloTurno(getCroceCorrente(session))
    }// fine del metodo

    //--controlla il flag mantenuto nei Settings associati alla croce corrente
    public int oreMassimeSingoloTurno(Croce croce) {
        return getInt(croce, Cost.PREF_oreMassimeSingoloTurno)
    }// fine del metodo

    //--controlla il flag mantenuto nei Settings associati alla croce corrente
    public int oreMassimeSingoloTurno(def session) {
        return oreMassimeSingoloTurno(getCroceCorrente(session))
    }// fine del metodo

    //--controlla il parametro mantenuto nei Settings associati alla croce corrente
    public ControlloTemporale getControlloModifica(session) {
        ControlloTemporale tipoControlloModifica = null
        Croce croce = getCroceCorrente(session)
        Settings settings

        if (croce) {
            settings = croce.settings
        }// fine del blocco if

        if (settings) {
            tipoControlloModifica = settings.tipoControlloModifica
        }// fine del blocco if

        return tipoControlloModifica
    }// fine del metodo

    //--controlla il parametro mantenuto nei Settings associati alla croce corrente
    public boolean isControlloModificaTempoTrascorso() {
        return (getControlloModifica() == ControlloTemporale.tempoTrascorso)
    }// fine del metodo

    //--controlla il parametro mantenuto nei Settings associati alla croce corrente
    public int getMaxMinutiTrascorsiModifica(session) {
        int maxMinutiTrascorsiModifica = 0
        Croce croce = getCroceCorrente(session)
        Settings settings

        if (croce) {
            settings = croce.settings
        }// fine del blocco if

        if (settings) {
            maxMinutiTrascorsiModifica = settings.maxMinutiTrascorsiModifica
        }// fine del blocco if

        return maxMinutiTrascorsiModifica
    }// fine del metodo


    //--restituisce la croce corrente
    public Croce getCroceCorrente(def session) {
        Croce croceCorrente = null
        Croce croceSessione = session['croce']
        String siglaCroce

        if (croceSessione) {
            siglaCroce = croceSessione.sigla
        }// fine del blocco if

        if (siglaCroce) {
            croceCorrente = Croce.findBySigla(siglaCroce)
        }// fine del blocco if

        return croceCorrente
    }// fine del metodo
} // end of Service Class
