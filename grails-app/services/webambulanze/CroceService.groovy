package webambulanze

class CroceService {

    //--controlla il valore mantenuto nei Settings associati alla croce indicata
    private static String getStr(Croce croce, codice) {
        String value = ''
        Settings settings

        if (croce) {
            settings = croce.settings
        }// fine del blocco if

        if (settings) {
            value = settings."${codice}"
        }// fine del blocco if

        return value
    }// fine del metodo

    //--controlla il valore mantenuto nei Settings associati alla croce indicata
    private static int getInt(Croce croce, codice) {
        int value = 0
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
    private boolean isFlag(session, codice) {
        boolean flag = false
        Croce croce = getCroce(session)
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
        return fissaLimiteMassimoSingoloTurno(getCroce(session))
    }// fine del metodo

    //--controlla il flag mantenuto nei Settings associati alla croce corrente
    public int oreMassimeSingoloTurno(Croce croce) {
        return getInt(croce, Cost.PREF_oreMassimeSingoloTurno)
    }// fine del metodo

    //--controlla il flag mantenuto nei Settings associati alla croce corrente
    public int oreMassimeSingoloTurno(def session) {
        return oreMassimeSingoloTurno(getCroce(session))
    }// fine del metodo

    //--controlla il flag mantenuto nei Settings associati alla croce corrente
    public int maxMinutiTrascorsiModifica(def session) {
        return getInt(getCroce(session), Cost.PREF_maxMinutiTrascorsiModifica)
    }// fine del metodo

    //--controlla il parametro mantenuto nei Settings associati alla croce corrente
    public ControlloTemporale getControlloModifica(def session) {
        ControlloTemporale tipoControlloModifica = null
        Croce croce = getCroce(session)
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
    public boolean isControlloModificaTempoTrascorso(def session) {
        return (getControlloModifica(session) == ControlloTemporale.tempoTrascorso)
    }// fine del metodo

    //--controlla il parametro mantenuto nei Settings associati alla croce
    public boolean isStartLogin(String siglaCroce) {
        Croce croce

        if (siglaCroce) {
            croce = this.getCroce(siglaCroce)
            return getStr(croce, Cost.PREF_startLogin)
        } else {
            return false
        }// fine del blocco if-else

    }// fine del metodo

    //--controlla il parametro mantenuto nei Settings associati alla croce
    public String getStartController(String siglaCroce) {
        Croce croce

        if (siglaCroce) {
            croce = this.getCroce(siglaCroce)
            return getStr(croce, Cost.PREF_startController)
        } else {
            return ''
        }// fine del blocco if-else

    }// fine del metodo

    //--controlla il parametro mantenuto nei Settings associati alla croce
    public boolean isAllControllers(String siglaCroce) {
        Croce croce

        if (siglaCroce) {
            croce = this.getCroce(siglaCroce)
            return getStr(croce, Cost.PREF_allControllers)
        } else {
            return false
        }// fine del blocco if-else

    }// fine del metodo

    //--controlla il parametro mantenuto nei Settings associati alla croce
    public String getControlli(String siglaCroce) {
        Croce croce

        if (siglaCroce) {
            croce = this.getCroce(siglaCroce)
            return getStr(croce, Cost.PREF_controlli)
        } else {
            return ''
        }// fine del blocco if-else

    }// fine del metodo

    //--restituisce la croce corrente
    public Croce getCroce(def session) {
        Croce croceCorrente = null
        String siglaCroce = session[Cost.SESSIONE_SIGLA_CROCE]

        if (siglaCroce) {
            croceCorrente = Croce.findBySigla(siglaCroce)
        }// fine del blocco if

        return croceCorrente
    }// fine del metodo

    //--restituisce la croce corrente
    public Croce getCroce(String siglaCroce) {
        Croce croce = null

        if (siglaCroce) {
            croce = Croce.findBySigla(siglaCroce)
        }// fine del blocco if

        return croce
    }// fine del metodo
} // end of Service Class
