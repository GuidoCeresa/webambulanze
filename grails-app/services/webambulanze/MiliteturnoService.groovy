package webambulanze

class MiliteturnoService {

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def croceService
    def funzioneService
    def logoService

    /**
     * Elabora le statistiche dei turni effettuati da ciascun Milite di una croce
     *
     * Chiamato da MilitestatisticheController
     * Opera sulla croce della sessione corrente
     * Opera su tutti gli anni previsti
     *
     * Cancella tutti i records di Militeturno
     * Ricalcola tutti i turni
     * Crea i records di Militeturno
     * Cancella tutti i records di Militestatistiche
     * Crea i records di Militestatistiche
     */
    def calcola(request) {
        Croce croce = croceService.getCroce(request)

        if (croce) {
            calcola(croce)
        }// fine del blocco if
    }// fine del metodo

    /**
     * Elabora le statistiche dei turni effettuati da ciascun Milite di una croce
     *
     * Opera sulla croce della sessione corrente
     * Opera su tutti gli anni previsti
     *
     * Cancella tutti i records di Militeturno
     * Ricalcola tutti i turni
     * Crea i records di Militeturno
     * Cancella tutti i records di Militestatistiche
     * Crea i records di Militestatistiche
     *
     * @param croce selezionata
     */
    public void calcola(Croce croce) {
        String[] anni = Cost.ANNI

        anni?.each {
            calcola(croce, it)
        } // fine del ciclo each
    }// fine del metodo

    /**
     * Elabora le statistiche dei turni effettuati da ciascun Milite di una croce
     *
     * Opera sulla croce della sessione corrente
     * Opera sui records dell'anno corrente
     *
     * Cancella tutti i records di Militeturno
     * Ricalcola tutti i turni
     * Crea i records di Militeturno
     * Cancella tutti i records di Militestatistiche
     * Crea i records di Militestatistiche
     *
     * @param croce selezionata
     * @param anno selezionato
     */
    public void calcola(Croce croce, String anno) {
        Date inizio = Lib.creaData1Gennaio(anno)
        Date fine = Lib.creaData31Dicembre(anno)

        calcola(croce, anno, inizio, fine)
    }// fine del metodo

    /**
     * Elabora le statistiche dei turni effettuati da ciascun Milite
     *
     * Chiamato da CalcolaJob
     * Opera su tutte le croci che hanno il flag abilitato
     * Opera sui records dell'anno corrente
     *
     * Cancella tutti i records di Militeturno
     * Ricalcola tutti i turni
     * Crea i records di Militeturno
     * Cancella tutti i records di Militestatistiche
     * Crea i records di Militestatistiche
     */
    public void calcola() {
        Croce croce
        ArrayList listaCroci = Croce.findAll()

        listaCroci?.each {
            croce = (Croce) it
            if (croceService.isCalcoloNotturnoStatistiche(croce)) {
                calcolaCorrente(croce)
            }// fine del blocco if
        } // fine del ciclo each
    }// fine del metodo

    /**
     * Elabora le statistiche dei turni effettuati da ciascun Milite di una croce
     *
     * Opera sui records dell'anno corrente
     *
     * Cancella tutti i records di Militeturno
     * Ricalcola tutti i turni
     * Crea i records di Militeturno
     * Cancella tutti i records di Militestatistiche
     * Crea i records di Militestatistiche
     *
     * @param croce selezionata
     */
    public void calcolaCorrente(Croce croce) {
        Date inizio = Lib.creaData1Gennaio()
        Date fine = Lib.creaDataOggi()
        String anno = Lib.getAnno(inizio)

        calcola(croce, anno, inizio, fine)
        logoService.setInfo(croce, Evento.statistiche)
    }// fine del metodo

    /**
     * Elabora le statistiche dei turni effettuati da ciascun Milite di una croce nel periodo
     *
     * Cancella tutti i records di Militeturno
     * Ricalcola tutti i turni
     * Crea i records di Militeturno
     * Cancella tutti i records di Militestatistiche
     * Crea i records di Militestatistiche
     *
     * @param croce selezionata
     * @param anno selezionato
     * @param inizio del periodo da considerare - di solito il 1° gennaio
     * @param fine del periodo da considerare:
     *      la data corrente per l'anno in corso
     *      il 31 dicembre per gli anni passati
     */
    public void calcola(Croce croce, String anno, Date inizio, Date fine) {
        //--Opera per un periodo (intervallo di tempo)
        cancellaMiliteTurno(croce, inizio, fine)
        ricalcolaMiliteTurno(croce, inizio, fine)
        aggiornaMiliti(croce, inizio, fine)

        //--Opera per un anno intero
        cancellaMiliteStatistiche(croce, anno)
        ricalcolaMiliteStatistiche(croce, anno, inizio, fine)
    }// fine del metodo

    /**
     * Cancella tutti i records di Militeturno per la croce selezionata e per il periodo
     * Opera per un periodo (intervallo di tempo)
     *
     * @param croce selezionata
     * @param inizio del periodo da considerare - di solito il 1° gennaio
     * @param fine del periodo da considerare:
     *      la data corrente per l'anno in corso
     *      il 31 dicmbre per gli anni passati
     */
    private static void cancellaMiliteTurno(Croce croce, Date inizio, Date fine) {
//        Militeturno.findAllByCroceAndGiornoBetween(croce, inizio, fine)*.delete(flush: true)
        def lista = Militeturno.findAllByCroceAndGiornoBetween(croce, inizio, fine)

        lista?.each {
            it.delete()
        } // fine del ciclo each
    }// fine del metodo

    /**
     * Ricalcola tutti i turni effettuati dai Militi della croce nel periodo
     * Crea i records di Militeturno
     * Opera per un periodo (intervallo di tempo)
     *
     * @param croce selezionata
     * @param inizio del periodo da considerare - di solito il 1° gennaio
     * @param fine del periodo da considerare:
     *      la data corrente per l'anno in corso
     *      il 31 dicmbre per gli anni passati
     */
    private static void ricalcolaMiliteTurno(Croce croce, Date inizio, Date fine) {
        ArrayList listaTurni
        ArrayList listaTurni1
        ArrayList listaTurni2
        ArrayList listaTurni3
        ArrayList listaTurni4
        Milite milite
        Turno turno
        Date giorno
        Funzione funzione
        String dettaglio
        int ore
        listaTurni1 = Turno.findAllByCroceAndGiornoBetweenAndMiliteFunzione1IsNotNull(croce, inizio, fine)
        listaTurni2 = Turno.findAllByCroceAndGiornoBetweenAndMiliteFunzione2IsNotNull(croce, inizio, fine)
        listaTurni3 = Turno.findAllByCroceAndGiornoBetweenAndMiliteFunzione3IsNotNull(croce, inizio, fine)
        listaTurni4 = Turno.findAllByCroceAndGiornoBetweenAndMiliteFunzione4IsNotNull(croce, inizio, fine)
        listaTurni = listaTurni1 + listaTurni2 + listaTurni3 + listaTurni4

        listaTurni?.each {
            turno = (Turno) it
            giorno = turno.giorno

            //--prima funzione
            funzione = turno.funzione1
            milite = turno.militeFunzione1
            ore = turno.oreMilite1
            dettaglio = ''
            if (turno.militeFunzione2) {
                dettaglio += turno.militeFunzione2.toString()
            }// fine del blocco if
            if (turno.militeFunzione3) {
                dettaglio += ', ' + turno.militeFunzione3.toString()
            }// fine del blocco if
            if (turno.militeFunzione4) {
                dettaglio += ', ' + turno.militeFunzione4.toString()
            }// fine del blocco if
            registra(croce, milite, giorno, turno, funzione, ore, dettaglio)

            //--seconda funzione
            funzione = turno.funzione2
            milite = turno.militeFunzione2
            ore = turno.oreMilite2
            dettaglio = ''
            if (turno.militeFunzione1) {
                dettaglio += turno.militeFunzione1.toString()
            }// fine del blocco if
            if (turno.militeFunzione3) {
                dettaglio += ', ' + turno.militeFunzione3.toString()
            }// fine del blocco if
            if (turno.militeFunzione4) {
                dettaglio += ', ' + turno.militeFunzione4.toString()
            }// fine del blocco if
            registra(croce, milite, giorno, turno, funzione, ore, dettaglio)

            //--terza funzione
            funzione = turno.funzione3
            milite = turno.militeFunzione3
            ore = turno.oreMilite3
            dettaglio = ''
            if (turno.militeFunzione1) {
                dettaglio += turno.militeFunzione1.toString()
            }// fine del blocco if
            if (turno.militeFunzione2) {
                dettaglio += ', ' + turno.militeFunzione2.toString()
            }// fine del blocco if
            if (turno.militeFunzione4) {
                dettaglio += ', ' + turno.militeFunzione4.toString()
            }// fine del blocco if
            registra(croce, milite, giorno, turno, funzione, ore, dettaglio)

            //--quarta funzione
            funzione = turno.funzione4
            milite = turno.militeFunzione4
            ore = turno.oreMilite4
            dettaglio = ''
            if (turno.militeFunzione1) {
                dettaglio += turno.militeFunzione1.toString()
            }// fine del blocco if
            if (turno.militeFunzione2) {
                dettaglio += ', ' + turno.militeFunzione2.toString()
            }// fine del blocco if
            if (turno.militeFunzione3) {
                dettaglio += ', ' + turno.militeFunzione3.toString()
            }// fine del blocco if
            registra(croce, milite, giorno, turno, funzione, ore, dettaglio)
        } // fine del ciclo each
    }// fine del metodo

    /**
     * Crea il singolo record di Militeturno
     */
    private static Militeturno registra(Croce croce,
                                        Milite milite,
                                        Date giorno,
                                        Turno turno,
                                        Funzione funzione,
                                        int ore,
                                        String dettaglio) {
        Militeturno militeturno
        militeturno = Militeturno.findOrCreateByCroceAndMiliteAndGiornoAndTurnoAndFunzioneAndOreAndDettaglio(
                croce,
                milite,
                giorno,
                turno,
                funzione,
                ore,
                dettaglio).save(flush: true)

        return militeturno
    }// fine del metodo

    /**
     * Aggiorna i records dei Militi della croce nel periodo
     * Opera per un periodo (intervallo di tempo)
     *
     * Recupera per ogni milite tutti i records di Militeturno
     * Aggiorna i valori di ore e turni
     *
     * @param croce selezionata
     * @param inizio del periodo da considerare - di solito il 1° gennaio
     * @param fine del periodo da considerare:
     *      la data corrente per l'anno in corso
     *      il 31 dicmbre per gli anni passati
     */
    private static void aggiornaMiliti(Croce croce, Date inizio, Date fine) {
        def listaMiliti = null
        Milite milite
        def listaTurni
        Militeturno militeTurno
        int contTurni
        int contOre

        if (croce) {
            listaMiliti = Milite.findAllByCroce(croce, [sort: 'cognome', order: 'asc'])
        }// fine del blocco if

        listaMiliti?.each {
            milite = (Milite) it
            contTurni = 0
            contOre = 0
            listaTurni = Militeturno.findAllByCroceAndMiliteAndGiornoBetween(croce, milite, inizio, fine)
            listaTurni?.each {
                militeTurno = (Militeturno) it
                contTurni++
                contOre += militeTurno.ore
            } // fine del ciclo each
            milite.turniAnno = contTurni
            milite.oreAnno = contOre
            milite.save(flush: true)
        } // fine del ciclo each
    }// fine del metodo

    /**
     * Cancella tutti i records di Militestatistiche per la croce selezionata e per l'anno
     * Opera per un anno intero
     *
     * @param croce selezionata
     * @param anno selezionato
     */
    private static void cancellaMiliteStatistiche(Croce croce, String anno) {
//        Militestatistiche.findAllByCroceAndAnno(croce, anno)*.delete(flush: true)
        def lista = Militestatistiche.findAllByCroceAndAnno(croce, anno)

        lista?.each {
            it.delete()
        } // fine del ciclo each
    }// fine del metodo

    /**
     * Ricalcola le statistiche dei Militi di una croce per l'anno
     * Opera per un anno intero
     *
     * Crea i records di Militestatistiche (1 per Milite) in base a quelli di Militeturno
     * Controlla la frequenza (2 al mese) e mette in verde od in rosso il numero di turni
     *
     * @param croce selezionata
     * @param anno selezionato
     * @param inizio del periodo da considerare - di solito il 1° gennaio
     * @param fine del periodo da considerare:
     *      la data corrente per l'anno in corso
     *      il 31 dicmbre per gli anni passati
     */
    private void ricalcolaMiliteStatistiche(Croce croce, String anno, Date inizio, Date fine) {
        Militestatistiche militestatistiche
        Milite milite
//        Date ultimoTurno = Lib.creaData1Gennaio(anno)
        Date ultimoTurno = null
        Date dataCorrente = new Date()
        int delta = 0
        int numUltimoTurno
        int numDataCorrente
        Funzione funzione
        String siglaFunzione
        int turni
        int ore
        int oreFunz
        def listaMiliti = Milite.findAllByCroce(croce)
        def listaMiliteTurni
        LinkedHashMap mappaSiglaFunzioni = funzioneService.mappaSiglaFunzioni(croce)
        String nome
        int cont
        int numAnnoCorrente = Integer.decode(Lib.getAnno(dataCorrente))
        int numAnnoSelezionato = Integer.decode(anno)
        boolean annoCorrente = (numAnnoSelezionato == numAnnoCorrente)

        listaMiliti?.each {
            milite = (Milite) it
            ultimoTurno = null
            delta = 0
            turni = 0
            ore = 0
            oreFunz = 0
            listaMiliteTurni = Militeturno.findAllByCroceAndMiliteAndGiornoBetween(croce, milite, inizio, fine, [sort: 'giorno', order: 'asc'])

            mappaSiglaFunzioni?.each {
                it.value = 0
            } // fine del ciclo each

            listaMiliteTurni?.each {
                turni++
                ore += it.ore
                funzione = it.funzione
                siglaFunzione = funzione.sigla
                if (mappaSiglaFunzioni.containsKey(siglaFunzione)) {
                    oreFunz = it.ore + (int) mappaSiglaFunzioni.get(siglaFunzione)
                    mappaSiglaFunzioni.put(siglaFunzione, oreFunz)
                }// fine del blocco if
                if (it.giorno < dataCorrente) {
                    ultimoTurno = it.giorno
                }// fine del blocco if
            } // fine del ciclo each

            //--controllo delle date
            if (annoCorrente) {
                numDataCorrente = Lib.getNumGiornoAssoluto(dataCorrente)
                if (ultimoTurno) {
                    numUltimoTurno = Lib.getNumGiornoAssoluto(ultimoTurno)
                    delta = (numDataCorrente - numUltimoTurno)
                } else {
                    delta = 0
                }// fine del blocco if-else
            }// fine del blocco if

            militestatistiche = new Militestatistiche()
            militestatistiche.croce = croce
            militestatistiche.anno = anno
            militestatistiche.milite = milite
            militestatistiche.ultimo = ultimoTurno
            militestatistiche.delta = delta
            militestatistiche.oreExtra = milite.oreExtra
            militestatistiche.status = turni < Lib.turniNecessari(milite.oreExtra) ? Cost.STATUS_ROSSO : Cost.STATUS_VERDE
            militestatistiche.turni = turni
            militestatistiche.ore = ore
            cont = 0
            mappaSiglaFunzioni?.each {
                cont++
                nome = 'funz' + cont
                oreFunz = (int) it.value
                if (cont <= 20) {
                    militestatistiche."${nome}" = oreFunz
                }// fine del blocco if
            } // fine del ciclo each
            militestatistiche.save(flush: true)
        } // fine del ciclo each
    }// fine del metodo

    //--mappa (vuota) per ogni militi con i campi necessari
    def LinkedHashMap mappaMiliti(Croce croce) {
        LinkedHashMap mappaMiliti = new LinkedHashMap()
        HashMap mappa = null
        Milite milite
        def listaMiliti = null

        if (croce) {
            listaMiliti = Milite.findAllByCroce(croce, [sort: 'cognome', order: 'asc'])
        }// fine del blocco if

        listaMiliti?.each {
            milite = (Milite) it
            mappa = new HashMap()
            mappa.put('milite', null)
            mappa.put('giorno', null)
            mappa.put('turno', null)
            mappa.put('funzione', null)
            mappa.put('ore', 0)
            mappaMiliti.put(milite.toString(), mappa)
        } // fine del ciclo each

        return mappaMiliti
    }// fine del metodo

    //--mappa (vuota) per le funzioni della croce
    //--ogni funzione ha nome ed un intero per il totale delle ore
    def LinkedHashMap mappaFunzioni(Croce croce) {
        LinkedHashMap mappaFunzioni = new LinkedHashMap()

        if (croce) {
            mappaFunzioni = m
        }// fine del blocco if

        listaMiliti?.each {
            milite = (Milite) it
            mappa = new HashMap()
            mappa.put('milite', null)
            mappa.put('giorno', null)
            mappa.put('turno', null)
            mappa.put('funzione', null)
            mappa.put('ore', 0)
            mappaMiliti.put(milite.toString(), mappa)
        } // fine del ciclo each

        return mappaMiliti
    }// fine del metodo
} // end of Service Class
