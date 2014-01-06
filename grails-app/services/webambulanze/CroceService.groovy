package webambulanze

import javax.servlet.http.Cookie

class CroceService {

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def springSecurityService

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

    //--regola il valore mantenuto nei Settings associati alla croce indicata
    private static void setInt(Croce croce, codice, int valore) {
        Settings settings

        if (croce) {
            settings = croce.settings
        }// fine del blocco if

        if (settings) {
            settings."${codice}" = valore
            settings.save(flush: true)
        }// fine del blocco if
    }// fine del metodo

    //--controlla il flag mantenuto nei Settings associati alla croce indicata
    private boolean isFlag(request, codice) {
        boolean flag = false
        Croce croce = getCroce(request)
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

    //--controlla il flag mantenuto nei Settings associati alla croce indicata
    //--recupera la croce
    private static boolean isFlag(String siglaCroce, codice) {
        boolean flag = false
        Croce croce

        if (siglaCroce) {
            croce = getCroce(siglaCroce)
        }// fine del blocco if

        if (croce) {
            flag = isFlag(croce, codice)
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
    public boolean isDisabilitazioneAutomaticaLogin(Croce croce) {
        return isFlag(croce, Cost.PREF_disabilitazioneAutomaticaLogin)
    }// fine del metodo

    //--controlla il flag mantenuto nei Settings associati alla croce corrente
    public boolean fissaLimiteMassimoSingoloTurno(Croce croce) {
        return isFlag(croce, Cost.PREF_fissaLimiteMassimoSingoloTurno)
    }// fine del metodo

    //--controlla il flag mantenuto nei Settings associati alla croce corrente
    public boolean fissaLimiteMassimoSingoloTurno(def request) {
        return fissaLimiteMassimoSingoloTurno(getCroce(request))
    }// fine del metodo

    //--controlla il flag mantenuto nei Settings associati alla croce corrente
    public int oreMassimeSingoloTurno(Croce croce) {
        return getInt(croce, Cost.PREF_oreMassimeSingoloTurno)
    }// fine del metodo

    //--controlla il flag mantenuto nei Settings associati alla croce corrente
    public int oreMassimeSingoloTurno(def request) {
        return oreMassimeSingoloTurno(getCroce(request))
    }// fine del metodo

    //--controlla il flag mantenuto nei Settings associati alla croce corrente
    public int maxMinutiTrascorsiModifica(def request) {
        return getInt(getCroce(request), Cost.PREF_maxMinutiTrascorsiModifica)
    }// fine del metodo

    //--controlla il flag mantenuto nei Settings associati alla croce corrente
    public int minGiorniMancantiModifica(def request) {
        return getInt(getCroce(request), Cost.PREF_minGiorniMancantiModifica)
    }// fine del metodo

    //--controlla il flag mantenuto nei Settings associati alla croce corrente
    public boolean bloccaSoloFunzioniObbligatorie(Croce croce) {
        return isFlag(croce, Cost.PREF_bloccaSoloFunzioniObbligatorie)
    }// fine del metodo

    //--controlla il flag mantenuto nei Settings associati alla croce corrente
    public boolean militePuoCreareTurnoStandard(Croce croce) {
        return isFlag(croce, Cost.PREF_militePuoCreareTurnoStandard)
    }// fine del metodo

    //--controlla il flag mantenuto nei Settings associati alla croce corrente
    public boolean militePuoCreareTurnoExtra(Croce croce) {
        return isFlag(croce, Cost.PREF_militePuoCreareTurnoExtra)
    }// fine del metodo

    //--controlla il parametro mantenuto nei Settings associati alla croce corrente
    public ControlloTemporale getControlloModifica(def request) {
        ControlloTemporale tipoControlloModifica = null
        Croce croce = getCroce(request)
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
    public boolean isControlloModificaTempoTrascorso(def request) {
        return (getControlloModifica(request) == ControlloTemporale.tempoTrascorso)
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

    //--restituisce la sigla della croce corrente
    public String getSiglaCroce(def request) {
        String siglaCroce = ''
        def listaCookies
        Cookie cookie

        if (request) {
            listaCookies = request.cookies
            listaCookies?.each {
                cookie = (Cookie) it
                if (cookie.name.equals(Cost.COOKIE_SIGLA_CROCE)) {
                    siglaCroce = cookie.value
                }// fine del blocco if
            } // fine del ciclo each
        }// fine del blocco if

        return siglaCroce
    }// fine del metodo

    //--restituisce la croce corrente
    public Croce getCroce(def request) {
        Croce croceCorrente = null
        String siglaCroce

        if (request) {
            siglaCroce = getSiglaCroce(request)
        }// fine del blocco if

        if (siglaCroce) {
            croceCorrente = Croce.findBySigla(siglaCroce)
        }// fine del blocco if

        return croceCorrente
    }// fine del metodo

    //--restituisce la croce corrente
    public static Croce getCroce(String siglaCroce) {
        Croce croce = null

        if (siglaCroce) {
            croce = Croce.findBySigla(siglaCroce)
        }// fine del blocco if

        return croce
    }// fine del metodo

    //--ruolo dell'utente al momento collegato
    //--la scala dei ruoli si base sull'ordine dei records
    //--per primo (record più basso) il ruolo più importante
    public Ruolo getMaxRuolo() {
        Ruolo ruolo = null
        Ruolo ruoloTmp = null
        def authentication = springSecurityService.authentication
        def listaRuoli
        def listaNumeri
        long min

        if (authentication) {
            listaNumeri = new ArrayList()
            listaRuoli = authentication.authorities
            listaRuoli?.each {
                ruoloTmp = Ruolo.findByAuthority((String) it)
                if (ruoloTmp) {
                    listaNumeri.add(ruoloTmp.id)
                }// fine del blocco if
            } // fine del ciclo each

            if (listaNumeri && listaNumeri.size() > 0) {
                min = (long) listaNumeri.min()
                ruolo = Ruolo.get(min)
            }// fine del blocco if
        }// fine del blocco if

        return ruolo
    }// fine del metodo

    //--controlla il flag mantenuto nei Settings associati alla croce
    public boolean usaModuloTurni(Croce croce) {
        return isFlag(croce, Cost.PREF_usaModuloTurni)
    }// fine del metodo

    //--controlla il flag mantenuto nei Settings associati alla croce
    public boolean usaModuloViaggi(Croce croce) {
        return isFlag(croce, Cost.PREF_usaModuloViaggi)
    }// fine del metodo

    //--controlla il flag mantenuto nei Settings associati alla croce
    public boolean usaModuloViaggi(String siglaCroce) {
        return isFlag(siglaCroce, Cost.PREF_usaModuloViaggi)
    }// fine del metodo

    //--controlla il flag mantenuto nei Settings associati alla croce
    public boolean isTabelloneSecured(String siglaCroce) {
        return isFlag(siglaCroce, Cost.PREF_isTabelloneSecured)
    }// fine del metodo

    //--controlla il flag mantenuto nei Settings associati alla croce
    public boolean isTurniSecured(String siglaCroce) {
        return isFlag(siglaCroce, Cost.PREF_isTurniSecured)
    }// fine del metodo

    //--controlla il flag mantenuto nei Settings associati alla croce
    public boolean mostraTabellonePartenza(String siglaCroce) {
        return isFlag(siglaCroce, Cost.PREF_mostraTabellonePartenza)
    }// fine del metodo

    //--controlla il flag mantenuto nei Settings associati alla croce
    public boolean isTurnoSettimanale(String siglaCroce) {
        if (siglaCroce instanceof String) {
            return isFlag(siglaCroce, Cost.PREF_isTurnoSettimanale)
        } else {
            return false
        }// fine del blocco if-else
    }// fine del metodo

    //--controlla il flag mantenuto nei Settings associati alla croce
    public boolean usaNomeCognome(String siglaCroce) {
        if (siglaCroce instanceof String) {
            return isFlag(siglaCroce, Cost.PREF_usaNomeCognome)
        } else {
            return false
        }// fine del blocco if-else
    }// fine del metodo

    //--controlla il flag mantenuto nei Settings associati alla croce
    public boolean usaListaMilitiViaggi(String siglaCroce) {
        if (siglaCroce instanceof String) {
            return isFlag(siglaCroce, Cost.PREF_usaListaMilitiViaggi)
        } else {
            return false
        }// fine del blocco if-else
    }// fine del metodo

    //--controlla il flag mantenuto nei Settings associati alla croce corrente
    public int getNumeroServiziEffettuati(Croce croce) {
        return getInt(croce, Cost.PREF_numeroServiziEffettuati)
    }// fine del metodo

    //--regola il flag mantenuto nei Settings associati alla croce corrente
    public void setNumeroServiziEffettuati(Croce croce, int valore) {
        setInt(croce, Cost.PREF_numeroServiziEffettuati, valore)
    }// fine del metodo

} // end of Service Class
