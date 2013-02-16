package webambulanze

class CroceService {

    // la variabile/proprietà viene iniettata automaticamente
    def grailsApplication

    //--controlla il flag mantenuto nei Settings associati alla croce indicata
    //--recupera la croce corrente
    private boolean isFlag(codice) {
        boolean flag = false
        Croce croce = getCroceCorrente()
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
    public boolean isMostraSoloMilitiFunzione() {
        return isFlag(Cost.PREF_mostraSoloMilitiFunzione)
    }// fine del metodo

    //--controlla il flag mantenuto nei Settings associati alla croce corrente
    public boolean isMostraMilitiFunzioneAndAltri() {
        return isFlag(Cost.PREF_mostraMilitiFunzioneAndAltri)
    }// fine del metodo

    //--controlla il flag mantenuto nei Settings associati alla croce corrente
    public boolean isMilitePuoInserireAltri() {
        return isFlag(Cost.PREF_militePuoInserireAltri)
    }// fine del metodo

    //--controlla il flag mantenuto nei Settings associati alla croce corrente
    public boolean isMilitePuoModificareAltri() {
        return isFlag(Cost.PREF_militePuoModificareAltri)
    }// fine del metodo

    //--controlla il flag mantenuto nei Settings associati alla croce corrente
    public boolean isMilitePuoCancellareAltri() {
        return isFlag(Cost.PREF_militePuoCancellareAltri)
    }// fine del metodo

    //--controlla il parametro mantenuto nei Settings associati alla croce corrente
    public ControlloTemporale getControlloModifica() {
        ControlloTemporale tipoControlloModifica = null
        Croce croce = getCroceCorrente()
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
    public int getMaxMinutiTrascorsiModifica() {
        int maxMinutiTrascorsiModifica = 0
        Croce croce = getCroceCorrente()
        Settings settings

        if (croce) {
            settings = croce.settings
        }// fine del blocco if

        if (settings) {
            maxMinutiTrascorsiModifica = settings.maxMinutiTrascorsiModifica
        }// fine del blocco if

        return maxMinutiTrascorsiModifica
    }// fine del metodo

    //--controlla il parametro mantenuto nei Settings associati alla croce corrente
    public boolean isOrarioTurnoModificabileForm() {
        boolean isOrarioTurnoModificabileForm = false
        Croce croce = getCroceCorrente()
        Settings settings

        if (croce) {
            settings = croce.settings
        }// fine del blocco if

        if (settings) {
            isOrarioTurnoModificabileForm = settings.isOrarioTurnoModificabileForm
        }// fine del blocco if

        return isOrarioTurnoModificabileForm
    }// fine del metodo

    //--restituisce la croce corrente
    private Croce getCroceCorrente() {
        Croce croceCorrente
        def context = grailsApplication.mainContext.servletContext

        return getCroceCorrente(context)
    }// fine del metodo

    //--restituisce la croce corrente
    private static Croce getCroceCorrente(def servletContext) {
        Croce croceCorrente = null
        Croce croceContesto = servletContext.croce
        String siglaCroce

        if (croceContesto) {
            siglaCroce = croceContesto.sigla
        }// fine del blocco if

        if (siglaCroce) {
            croceCorrente = Croce.findBySigla(siglaCroce)
        }// fine del blocco if

        return croceCorrente
    }// fine del metodo
} // end of Service Class
