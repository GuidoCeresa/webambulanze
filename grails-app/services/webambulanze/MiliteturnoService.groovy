package webambulanze

class MiliteturnoService {

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def croceService

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def funzioneService

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def logoService

    //--cancella tutti i records di Militeturno (dell'anno corrente)
    //--ricalcola tutti i turni
    //--crea i records di MiliteTurno
    //--cancella tutti i records di Militestatistiche (dell'anno corrente)
    //--crea i records di Militestatistiche
    //--opera sulla croce indicata
    def calcola(Croce croce) {
        Date oggi = Lib.creaDataOggi()
        Date primoGennaio = Lib.creaData1Gennaio()

        cancellaMiliteTurno(croce, primoGennaio)
        ricalcolaMiliteTurno(croce, primoGennaio, oggi)
        aggiornaMiliti(croce, primoGennaio)
        cancellaMiliteStatistiche(croce)
        ricalcolaMiliteStatistiche(croce)
        logoService.setInfo(croce, Evento.statistiche)
    }// fine del metodo

    //--cancella tutti i records di Militeturno (dell'anno corrente)
    //--ricalcola tutti i turni
    //--crea i records di MiliteTurno
    //--cancella tutti i records di Militestatistiche (dell'anno corrente)
    //--crea i records di Militestatistiche
    //--chiamato da CalcolaJob, opera su tutte le croci (col flag abilitato)
    def calcola() {
        Croce croce
        def listaCroci = Croce.findAll()

        if (listaCroci) {
            listaCroci?.each {
                croce = (Croce) it
                if (croceService.isCalcoloNotturnoStatistiche(croce)) {
                    calcola(croce)
                }// fine del blocco if
            } // fine del ciclo each
        }// fine del blocco if
    }// fine del metodo

    //--cancella tutti i records di Militeturno (dell'anno corrente)
    //--ricalcola tutti i turni
    //--crea i records di MiliteTurno
    //--cancella tutti i records di Militestatistiche (dell'anno corrente)
    //--crea i records di Militestatistiche
    //--opera sulla croce della sessione corrente (se ha il flag abilitato)
    def calcola(request) {
        Croce croce = croceService.getCroce(request)

        if (croce) {
            calcola(croce)
        }// fine del blocco if
    }// fine del metodo

    //--cancella tutti i records di Militeturno (dell'anno corrente)
    def cancellaMiliteTurno(Croce croce, Date primoGennaio) {
        Militeturno.findAllByCroceAndGiornoGreaterThan(croce, primoGennaio)*.delete(flush: true)
    }// fine del metodo

    //--ricalcola tutti i turni e crea i records
    def ricalcolaMiliteTurno(Croce croce, Date primoGennaio, Date oggi) {
        Militeturno militeturno
        Milite milite
        HashMap mappa
        Turno turno
        Date giorno
        Funzione funzione
        String dettaglio
        String nomeMilite
        String nomeOreMilite
        int ore
        def listaTurni = Turno.findAllByCroceAndGiornoBetween(croce, primoGennaio, oggi)

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

    def registra(Croce croce, Milite milite, Date giorno, Turno turno, Funzione funzione, int ore, String dettaglio) {
        Militeturno.findOrCreateByCroceAndMiliteAndGiornoAndTurnoAndFunzioneAndOreAndDettaglio(
                croce,
                milite,
                giorno,
                turno,
                funzione,
                ore,
                dettaglio).save(flush: true)
    }// fine del metodo

    //--aggiorna i records dei Militi
    //--recupera per ogni milite tutti i records di Militeturno
    //--aggiorna i valori di ore e turni
    def aggiornaMiliti(Croce croce, Date primoGennaio) {
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
            listaTurni = Militeturno.findAllByCroceAndMiliteAndGiornoGreaterThan(croce, milite, primoGennaio)
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

    //--cancella tutti i records di Militestatistiche (dell'anno corrente)
    def cancellaMiliteStatistiche(Croce croce) {
        def lista = Militestatistiche.findAllByCroce(croce)

        lista?.each {
            it.delete()
        } // fine del ciclo each
    }// fine del metodo

    //--crea i records di Militestatistiche (1 per Milite) in base a quelli di MiliteTurno
    //--controlla la frequenza (2 al mese) e mette in verde od in rosso il numero di turni
    def ricalcolaMiliteStatistiche(Croce croce) {
        Militeturno militeturno
        Militestatistiche militestatistiche
        Milite milite
        HashMap mappa
        Turno turno
        Date giorno
        Funzione funzione
        String siglaFunzione
        String nickMilite
        int turni
        int ore
        int oreFunz
        def listaMiliti = Milite.findAllByCroce(croce)
        def listaMiliteTurni
        LinkedHashMap mappaSiglaFunzioni = funzioneService.mappaSiglaFunzioni(croce)
        String nome
        int cont

        listaMiliti?.each {
            milite = (Milite) it
            turni = 0
            ore = 0
            oreFunz = 0
            listaMiliteTurni = Militeturno.findAllByCroceAndMilite(croce, milite)
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
            } // fine del ciclo each

            militestatistiche = new Militestatistiche()
            militestatistiche.croce = croce
            militestatistiche.milite = milite
            militestatistiche.oreExtra = milite.oreExtra
            militestatistiche.status = turni < Lib.turniNecessari() ? Cost.STATUS_ROSSO : Cost.STATUS_VERDE
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
