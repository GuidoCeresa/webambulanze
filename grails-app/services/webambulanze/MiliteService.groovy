package webambulanze

class MiliteService {

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def funzioneService

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def springSecurityService

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def logoService

    //--recupera tutte le funzioni abilitate per il milite selezionato
    def ArrayList funzioniPerMilite(Croce croce, Milite milite) {
        ArrayList funzioniAttiveDelMilite = null

        if (milite) {
            funzioniAttiveDelMilite = Militefunzione.findAllByCroceAndMilite(croce, milite)
        }// fine del blocco if

        return funzioniAttiveDelMilite
    }// fine del metodo

    //--recupera i nomi di tutte le funzioni abilitate per il milite selezionato
    def ArrayList nomeFunzioniPerMilite(Croce croce, Milite milite) {
        ArrayList nomeFunzioniAttiveDelMilite = new ArrayList()
        ArrayList funzioniAttiveDelMilite
        Militefunzione milFunz = null
        String nome

        if (milite) {
            funzioniAttiveDelMilite = this.funzioniPerMilite(croce, milite)
            if (funzioniAttiveDelMilite) {
                funzioniAttiveDelMilite?.each {
                    milFunz = (Militefunzione) it
                    nome = milFunz.funzione.toString()
                    nomeFunzioniAttiveDelMilite.add(nome)
                } // fine del ciclo each
            }// fine del blocco if
        }// fine del blocco if

        return nomeFunzioniAttiveDelMilite
    }// fine del metodo

    //--regola le funzioni automatiche per il milite selezionato
    //--se è abilitata una funzione (adesso o prima), vengono comunque abilitate tutte quelle sotto
    //--se è disabilitata una funzione, quelle sotto rimangono intoccate
    //--chiamata DOPO che il milite è stato registrato
    def regolaFunzioniAutomatiche(Milite milite) {
        Croce croce = null
        ArrayList funzioniPerCroce = null
        ArrayList<Funzione> funzioniDipendenti
        Funzione funzione
        Funzione funzioneDipendente
        def isAbilitataFunzionePerQuestoMilite

        if (milite) {
            croce = milite.croce
            funzioniPerCroce = Funzione.findAllByCroce(croce)
        }// fine del blocco if

        funzioniPerCroce?.each {
            funzione = (Funzione) it
            isAbilitataFunzionePerQuestoMilite = Militefunzione.findByMiliteAndFunzione(milite, funzione)
            if (isAbilitataFunzionePerQuestoMilite) {
                funzioniDipendenti = this.funzioniDipendenti(croce, funzione)
                funzioniDipendenti?.each {
                    funzioneDipendente = (Funzione) it
                    Militefunzione.findOrCreateByCroceAndMiliteAndFunzione(croce, milite, funzioneDipendente).save(flush: true)
                } // fine del ciclo each
            }// fine del blocco if
        } // fine del ciclo each

    }// fine del metodo

    //--recupera i nomi di tutte le funzioni automaticamente abilitate da quella selezionata
    public ArrayList<Funzione> funzioniDipendenti(Croce croce, Funzione funzione) {
        ArrayList<Funzione> funzioniDipendenti = new ArrayList<Funzione>()
        String stringaNomiFunzioniDipendenti
        def listaNomiFunzioni
        String siglaFunzione

        if (funzione) {
            stringaNomiFunzioniDipendenti = funzione.funzioniDipendenti
            if (stringaNomiFunzioniDipendenti) {
                listaNomiFunzioni = stringaNomiFunzioniDipendenti.split(',')
                listaNomiFunzioni?.each {
                    siglaFunzione = it
                    siglaFunzione = siglaFunzione.trim()
                    funzione = Funzione.findByCroceAndSigla(croce, siglaFunzione)
                    if (funzione) {
                        funzioniDipendenti.add(funzione)
                    }// fine del blocco if
                } // fine del ciclo each
            }// fine del blocco if
        }// fine del blocco if

        return funzioniDipendenti
    }// fine del metodo

    //--avviso conseguente alle modifiche effettuate
    def avvisoModifiche = { mappa, Milite milite ->
        ArrayList listaMessaggi = new ArrayList()
        String value
        Date data

        if (mappa.nome) {
            value = (String) mappa.nome
            if (!value.equals(milite.nome)) {
                listaMessaggi.add(logoService.setInfo(Evento.militeModificatoNome, milite))
            }// fine del blocco if
        }// fine del blocco if

        if (mappa.cognome) {
            value = (String) mappa.cognome
            if (!value.equals(milite.cognome)) {
                listaMessaggi.add(logoService.setInfo(Evento.militeModificatoCognome, milite))
            }// fine del blocco if
        }// fine del blocco if

        if (mappa.telefonoCellulare) {
            value = (String) mappa.telefonoCellulare
            if (!value.equals(milite.telefonoCellulare)) {
                listaMessaggi.add(logoService.setInfo(Evento.militeModificatoTelefono, milite))
            }// fine del blocco if
        }// fine del blocco if


        if (mappa.telefonoFisso) {
            value = (String) mappa.telefonoFisso
            if (!value.equals(milite.telefonoFisso)) {
                listaMessaggi.add(logoService.setInfo(Evento.militeModificatoTelefono, milite))
            }// fine del blocco if
        }// fine del blocco if

        if (mappa.email) {
            value = (String) mappa.email
            if (!value.equals(milite.email)) {
                listaMessaggi.add(logoService.setInfo(Evento.militeModificataEmail, milite))
            }// fine del blocco if
        }// fine del blocco if

        if (mappa.dataNascita) {
            data = mappa.dataNascita
            if (data && data != milite.dataNascita) {
                listaMessaggi.add(logoService.setInfo(Evento.militeModificataNascita, milite))
            }// fine del blocco if
        }// fine del blocco if

        if (mappa.scadenzaBLSD) {
            data = mappa.scadenzaBLSD
            if (data && data != milite.scadenzaBLSD) {
                listaMessaggi.add(logoService.setInfo(Evento.militeModificataScadenzaBLS, milite))
            }// fine del blocco if
        }// fine del blocco if

        if (mappa.scadenzaTrauma) {
            data = mappa.scadenzaTrauma
            if (data && data != milite.scadenzaTrauma) {
                listaMessaggi.add(logoService.setInfo(Evento.militeModificataScadenzaTrauma, milite))
            }// fine del blocco if
        }// fine del blocco if

        if (mappa.scadenzaNonTrauma) {
            data = mappa.scadenzaNonTrauma
            if (data && data != milite.scadenzaNonTrauma) {
                listaMessaggi.add(logoService.setInfo(Evento.militeModificataScadenzaNonTrauma, milite))
            }// fine del blocco if
        }// fine del blocco if

        return listaMessaggi
    }// fine del metodo

    //--registra lo stato delle funzioni abilitate (true, false) per il milite selezionato
    //--sincronizza la tavola d'incrocio
    def registraFunzioni = { mappa, militeTmp ->
        String croceIdTxt
        long croceId
        Milite milite
        Croce croce = null
        def campiExtraCroce
        String militeIdTxt
        long militeId
        String campo
        def value
        Funzione funzione

        if (mappa.croce.id) {
            croceIdTxt = mappa.croce.id
            croceId = Long.decode(croceIdTxt)
            croce = Croce.findById(croceId)
            campiExtraCroce = funzioneService.campiExtraPerCroce(croceId)
        }// fine del blocco if

        if (mappa.id) {
            militeIdTxt = mappa.id
            militeId = Long.decode(militeIdTxt)
            milite = Milite.findById(militeId)
        }// fine del blocco if
        if (milite == null) {
            milite = militeTmp
        }// fine del blocco if

        if (campiExtraCroce && milite && croce) {
            this.cancellaAllFunzioniMilite(milite)
            campiExtraCroce.each {
                campo = it
                value = mappa."${campo}"
                if (value) {
                    funzione = Funzione.findByCroceAndSigla(croce, campo)
                    new Militefunzione(croce: croce, milite: milite, funzione: funzione).save(flush: true)
                }// fine del blocco if
            } // fine del ciclo each
        }// fine del blocco if
    }// fine del metodo

    //--disabilita tutte le funzioni per il milite selezionato
    //--cancella tutti i records della tavola d'incrocio per il milite selezionato
    def cancellaAllFunzioniMilite(Milite milite) {
        if (milite) {
            Militefunzione.findAllByMilite(milite)*.delete(flush: true)
        }// fine del blocco if
    }// fine del metodo

    //--restituisce il milite attualmente loggato
    public Milite getMiliteLoggato() {
        Milite milite = null
        def currUser
        Utente utente

        //--user della classe mia
        currUser = springSecurityService.getCurrentUser()
        if (currUser instanceof Utente) {
            utente = (Utente) currUser
        }// fine del blocco if

        if (utente) {
            milite = utente.milite
        }// fine del blocco if

        return milite
    }// fine del metodo

    //--lista dei militi abilitati alla funzione
    public ArrayList listaMilitiAbilitati(Croce croce, Funzione funzione) {
        ArrayList listaMilitiAbilitati = null
        ArrayList listaAllMiliti = null
        Milite milite

        if (croce) {
            listaAllMiliti = Milite.findAllByCroceAndAttivo(croce, true, [sort: 'cognome', order: 'asc'])
        }// fine del blocco if

        if (funzione && listaAllMiliti) {
            listaMilitiAbilitati = new ArrayList()
            listaAllMiliti?.each {
                milite = (Milite) it
                if (Militefunzione.findByCroceAndMiliteAndFunzione(croce, milite, funzione)) {
                    listaMilitiAbilitati.add(milite)
                }// fine del blocco if
            } // fine del ciclo each
        }// fine del blocco if

        return listaMilitiAbilitati
    }// fine del metodo

    //--lista dei militi abilitati alla funzione
    //--seguiti da tutti i rimanenti
    public ArrayList listaMilitiAbilitatiAndAltri(Croce croce, Funzione funzione) {
        ArrayList listaMilitiAbilitati = null
        ArrayList listaMilitiAltri = null
        ArrayList listaAllMiliti = null
        Milite milite

        if (croce) {
            listaAllMiliti = Milite.findAllByCroceAndAttivo(croce, true, [sort: 'cognome', order: 'asc'])
        }// fine del blocco if

        if (funzione && listaAllMiliti) {
            listaMilitiAbilitati = new ArrayList()
            listaMilitiAltri = new ArrayList()
            listaAllMiliti?.each {
                milite = (Milite) it
                if (Militefunzione.findByCroceAndMiliteAndFunzione(croce, milite, funzione)) {
                    listaMilitiAbilitati.add(milite)
                }// fine del blocco if
            } // fine del ciclo each
            listaAllMiliti?.each {
                milite = (Milite) it
                if (!Militefunzione.findByCroceAndMiliteAndFunzione(croce, milite, funzione)) {
                    listaMilitiAltri.add(milite)
                }// fine del blocco if
            } // fine del ciclo each

            listaMilitiAbilitati = listaMilitiAbilitati + listaMilitiAltri
        }// fine del blocco if

        return listaMilitiAbilitati
    }// fine del metodo

    //--controlla se l'utente attualmente loggato è abilitato alla funzione
    public boolean isAbilitatoFunzione(Funzione funzione) {
        boolean abilitato = false
        Milite milite = getMiliteLoggato()
        Croce croce
        def milFunz

        if (funzione) {
            croce = funzione.croce
        }// fine del blocco if

        if (croce && milite && funzione) {
            milFunz = Militefunzione.findByCroceAndMiliteAndFunzione(croce, milite, funzione)
        }// fine del blocco if

        if (milFunz) {
            abilitato = true
        }// fine del blocco if

        return abilitato
    }// fine del metodo

    //--controlla se l'utente attualmente loggato ha il ruolo previsto
    private boolean isLoggatoNelRuolo(String ruoloPrevisto) {
        boolean risposta = false
        def authentication = springSecurityService.authentication
        def ruoli
        String ruolo
        String userName

        if (authentication) {
            ruoli = authentication.authorities
            ruoli?.each {
                ruolo = (String) it
                if (ruolo.equals(ruoloPrevisto)) {
                    risposta = true
                }// fine del blocco if
            } // fine del ciclo each
        }// fine del blocco if

        //--patch
        def principale
        if (authentication) {
            principale = authentication.principal
            if (principale instanceof String) {
                if (userName && userName.contains('anonymousUser')) {
                    risposta = false
                }// fine del blocco if
            } else {
                userName = principale.username
                if (userName && userName.contains('ospite')) {
                    risposta = false
                }// fine del blocco if
            }// fine del blocco if-else
        }// fine del blocco if

        return risposta
    }// fine del metodo

    //--controlla se l'utente attualmente loggato è un programmatore
    public boolean isLoggatoProgrammatore() {
        return this.isLoggatoNelRuolo(Cost.ROLE_PROG)
    }// fine del metodo

    //--controlla se l'utente attualmente loggato è un custode
    public boolean isLoggatoCustode() {
        return this.isLoggatoNelRuolo(Cost.ROLE_CUSTODE)
    }// fine del metodo

    //--controlla se l'utente attualmente loggato è un custode
    //--oppure un ruolo superiore (programmatore)
    public boolean isLoggatoCustodeOrMore() {
        return this.isLoggatoCustode() || this.isLoggatoProgrammatore()
    }// fine del metodo

    //--controlla se l'utente attualmente loggato è un admin
    public boolean isLoggatoAdmin() {
        return this.isLoggatoNelRuolo(Cost.ROLE_ADMIN)
    }// fine del metodo

    //--controlla se l'utente attualmente loggato è un admin
    //--oppure un ruolo superiore (custode o programmatore)
    public boolean isLoggatoAdminOrMore() {
        return this.isLoggatoAdmin() || this.isLoggatoCustodeOrMore()
    }// fine del metodo

    //--controlla se l'utente attualmente loggato è un milite
    public boolean isLoggatoMilite() {
        return this.isLoggatoNelRuolo(Cost.ROLE_MILITE)
    }// fine del metodo

    //--controlla se l'utente attualmente loggato è un milite
    //--oppure un ruolo superiore (admin o custode o programmatore)
    public boolean isLoggatoMiliteOrMore() {
        return this.isLoggatoMilite() || this.isLoggatoAdminOrMore()
    }// fine del metodo

    //--controlla se l'utente attualmente loggato è un ospite
    public boolean isLoggatoOspite() {
        return this.isLoggatoNelRuolo(Cost.ROLE_OSPITE)
    }// fine del metodo

    //--turni del milite effettuati a partire dal 1° gennaio anno corrente
    //--@todo manca controllo anno
    public int turniAnno(Milite milite) {
        int turni = 0
        Croce croce = null
        def lista

        if (milite) {
            croce = milite.croce
        }// fine del blocco if

        if (milite && croce) {
            lista = Militeturno.findAllByCroceAndMilite(croce, milite)
            if (lista) {
                turni = lista.size()
            }// fine del blocco if
        }// fine del blocco if

        return turni
    }// fine del metodo

    //--recupera i nomi di tutti i militi
    //--in ordine alfabetico
    public ArrayList allMilitiDellaCroce(Croce croce) {
        ArrayList listaMiliti = new ArrayList()

        if (croce) {
            listaMiliti = Milite.findAllByCroce(croce, [sort: 'cognome'])
        }// fine del blocco if

        return listaMiliti
    }// fine del metodo

    //--recupera i nomi di tutti i militi
    //--in ordine alfabetico
    //--aggiunge la posssibilità del campo vuoto (valore  nullo se voglio creare l'accesso ad un non-milite)
    public ArrayList allMilitiDellaCroceVuoto(Croce croce) {
        ArrayList listaMiliti = allMilitiDellaCroce(croce)

        if (listaMiliti) {
            listaMiliti.add(new Milite(croce: croce, nome: 'nome', cognome: 'cognome'))
        }// fine del blocco if

        return listaMiliti
    }// fine del metodo

    //--recupera i nomi di tutti gli militi
    //--in ordine alfabetico
    //--riga vuota iniziale (per il valore nullo)
    public ArrayList allNomiMilitiDellaCroce(Croce croce) {
        ArrayList listaNomiMiliti = new ArrayList()
        ArrayList listaMiliti

        if (croce) {
            listaMiliti = allMilitiDellaCroce(croce)
        }// fine del blocco if

        if (listaMiliti) {
            listaMiliti?.each {
                listaNomiMiliti.add(it.toString())
            } // fine del ciclo each
            listaMiliti.add(0, '')
        }// fine del blocco if

        return listaNomiMiliti
    }// fine del metodo

    public static cancellaMilite(Milite milite) {
        def listaRecords
        Logo logo
        Utente utente
        Turno turno
        Militestatistiche militeStatistiche
        Militeturno militeTurno

        if (milite) {
            listaRecords = Logo.findAllByMilite(milite)
            listaRecords?.each {
                logo = (Logo) it
                logo.milite = null
                logo.save(flush: true)
            } // fine del ciclo each

            listaRecords = Utente.findAllByMilite(milite)
            listaRecords?.each {
                utente = (Utente) it
                utente.milite = null
                utente.save(flush: true)
            } // fine del ciclo each

            listaRecords = Militestatistiche.findAllByMilite(milite)
            listaRecords?.each {
                militeStatistiche = (Militestatistiche) it
                militeStatistiche.delete(flush: true)
            } // fine del ciclo each

            listaRecords = Militeturno.findAllByMilite(milite)
            listaRecords?.each {
                militeTurno = (Militeturno) it
                militeTurno.delete(flush: true)
            } // fine del ciclo each

            listaRecords = Turno.findAllByMiliteFunzione1OrMiliteFunzione2OrMiliteFunzione3OrMiliteFunzione4(milite, milite, milite, milite)
            listaRecords?.each {
                turno = (Turno) it
                if (turno.militeFunzione1 == milite) {
                    turno.militeFunzione1 = null
                }// fine del blocco if
                if (turno.militeFunzione2 == milite) {
                    turno.militeFunzione2 = null
                }// fine del blocco if
                if (turno.militeFunzione3 == milite) {
                    turno.militeFunzione3 = null
                }// fine del blocco if
                if (turno.militeFunzione4 == milite) {
                    turno.militeFunzione4 = null
                }// fine del blocco if
                turno.save(flush: true)
            } // fine del ciclo each

            try { // prova ad eseguire il codice
                milite.delete(flush: true)
            } catch (Exception unErrore) { // intercetta l'errore
                def errore
            }// fine del blocco try-catch
        }// fine del blocco if
    }// fine del metodo

} // end of Service Class

