package webambulanze

class UtenteService {

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def logoService

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def croceService

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
    public ArrayList tuttiUtentiRuoloDellaCroceSenzaProgrammatore(Croce croce) {
        ArrayList listaCompletaTutteLeCroci = null
        ArrayList lista = null
        Ruolo ruoloProg = Ruolo.findByAuthority(Cost.ROLE_PROG)
        UtenteRuolo utenteRuolo
        Utente utente
        Croce croceUtente

        if (ruoloProg) {
            listaCompletaTutteLeCroci = UtenteRuolo.findAllByRuoloNotEqual(ruoloProg)
            if (listaCompletaTutteLeCroci) {
                lista = new ArrayList()
                listaCompletaTutteLeCroci.each {
                    utenteRuolo = (UtenteRuolo) it
                    utente = utenteRuolo.utente
                    croceUtente = utente.croce
                    if (croceUtente == croce) {
                        lista.add(utenteRuolo)
                    }// fine del blocco if
                } // fine del ciclo each
            }// fine del blocco if
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

    //--aggiunge in fondo l'username del programmatore
    public ArrayList addUsernameProg(ArrayList listaUtenti) {
        Utente utente

        if (listaUtenti) {
            utente = Utente.findByUsername(Cost.PROG_USERNAME)
            if (utente) {
                listaUtenti.add(utente.username)
            }// fine del blocco if
        }// fine del blocco if

        return listaUtenti
    }// fine del metodo

    //--aggiunge in fondo l'username del programmatore
    public ArrayList addNicknameProg(ArrayList listaUtenti) {
        Utente utente

        if (listaUtenti) {
            utente = Utente.findByUsername(Cost.PROG_USERNAME)
            if (utente) {
                listaUtenti.add(utente.nickname)
            }// fine del blocco if
        }// fine del blocco if

        return listaUtenti
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
        String sigla
        Utente utente

        sigla = listaUtenti[0]

        utente = Utente.findByUsername(sigla)
        if (isProgrammatore(utente)) {
            listaUtenti.remove(0)
            listaUtenti.add(sigla)
        }// fine del blocco if

        utente = Utente.findByNickname(sigla)
        if (isProgrammatore(utente)) {
            listaUtenti.remove(0)
            listaUtenti.add(sigla)
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

    //--controlla il ruolo
    public boolean isProgrammatore(Utente utente) {
        boolean programmatore = false
        Ruolo ruoloProg = Ruolo.findByAuthority(Cost.ROLE_PROG)
        def ruoli

        if (utente && ruoloProg) {
            ruoli = UtenteRuolo.findAllByUtenteAndRuolo(utente, ruoloProg)
        }// fine del blocco if

        if (ruoli && ruoli.size() > 0) {
            programmatore = true
        }// fine del blocco if

        return programmatore
    }// fine del metodo

    //--regola le abilitazioni di tutti gli utenti in funzione del flag verde/rosso
    //--del numero minimo obbligatorio di turni effettuati dall'inizio dell'anno
    //--espressamente richiesto da Rita (CRF) in data 21 maggio 2013
    public void regolaAbilitazioni() {
        Croce croce
        def listaCroci = Croce.findAll()

        if (listaCroci) {
            listaCroci?.each {
                croce = (Croce) it
                if (croceService.isCalcoloNotturnoStatistiche(croce)) {
                    if (croceService.isDisabilitazioneAutomaticaLogin(croce)) {
                        regolaAbilitazioni(croce)
                    }// fine del blocco if
                }// fine del blocco if
            } // fine del ciclo each
        }// fine del blocco if

    }// fine del metodo

    //--regola le abilitazioni di tutti gli utenti in funzione del flag verde/rosso
    //--del numero minimo obbligatorio di turni effettuati dall'inizio dell'anno
    //--espressamente richiesto da Rita (CRF) in data 21 maggio 2013
    public void regolaAbilitazioni(Croce croce) {
        def lista
        Militestatistiche militestatistiche
        Milite milite
        boolean abilitato = true
        def listaUtenti
        Utente utente

        lista = Militestatistiche.findAllByCroce(croce)
        if (lista) {
            lista?.each {
                militestatistiche = (Militestatistiche) it
                if (militestatistiche.status.equals(Cost.STATUS_ROSSO)) {
                    abilitato = false
                } else {
                    abilitato = true
                }// fine del blocco if-else
                milite = militestatistiche.milite
                if (milite) {
                    listaUtenti = Utente.findAllByCroceAndMilite(croce, milite)
                    listaUtenti?.each {
                        utente = (Utente) it
                        utente.enabled = abilitato
                        utente.save(flush: true)
                    } // fine del ciclo each
                }// fine del blocco if
            } // fine del ciclo each
        }// fine del blocco if
    }// fine del metodo

} // end of Service Class
