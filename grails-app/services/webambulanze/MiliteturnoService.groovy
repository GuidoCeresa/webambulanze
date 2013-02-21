package webambulanze

class MiliteturnoService {

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def croceService

    //--cancella tutti i records di Militeturno (dell'anno corrente)
    //--ricalcola tutti i turni e crea i records
    def calcola() {
        Croce croce = croceService.getCroceCorrente()
        Date primoGennaio = Lib.creaData1Gennaio()

        cancella(croce, primoGennaio)
        ricalcola(croce, primoGennaio)
        aggiornaMiliti(croce, primoGennaio)
    }// fine del metodo

    //--cancella tutti i records di Militeturno (dell'anno corrente)
    def cancella(Croce croce, Date primoGennaio) {
        Militeturno.findAllByCroceAndGiornoGreaterThan(croce, primoGennaio)*.delete(flush: true)
    }// fine del metodo

    //--ricalcola tutti i turni e crea i records
    def ricalcola(Croce croce, Date primoGennaio) {
        Militeturno militeturno
        Milite milite
        HashMap mappa
        Turno turno
        Date giorno
        Funzione funzione
        String nickMilite
        int ore
        LinkedHashMap mappaMiliti = mappaMiliti(croce)
        def listaTurni = Turno.findAllByCroceAndGiornoGreaterThan(croce, primoGennaio)

        listaTurni?.each {
            turno = (Turno) it
            giorno = turno.giorno
            funzione = turno.funzione1
            milite = turno.militeFunzione1
            nickMilite = milite.toString()
            ore = turno.oreMilite1
            militeturno = Militeturno.findOrCreateByCroceAndMiliteAndGiornoAndTurnoAndFunzioneAndOre(
                    croce,
                    milite,
                    giorno,
                    turno,
                    funzione,
                    ore).save(flush: true)

//            militeturno = new Militeturno()
//            militeturno.croce = croce
//            militeturno.milite = milite
//            militeturno.giorno = giorno
//            militeturno.turno = turno
//            militeturno.funzione = funzione
//            militeturno.ore = ore
//           def a= militeturno.save(flush: true)
            def stop2
        } // fine del ciclo each

//        listaTurni?.each {
//            turno = (Turno) it
//            giorno = turno.giorno
//            funzione = turno.funzione1
//            milite = turno.militeFunzione1
//            nickMilite = milite.toString()
//            ore = turno.oreMilite1
//            mappa = (HashMap) mappaMiliti.get(nickMilite)
//            mappa.put('milite', milite)
//            mappa.put('giorno', giorno)
//            mappa.put('turno', turno)
//            mappa.put('funzione', funzione)
//            mappa.put('ore', ore)
//            mappaMiliti.put(nickMilite, mappa)
//        } // fine del ciclo each

//        mappaMiliti?.each {
//            mappa = (HashMap) it.getValue()
//            milite = (Milite) mappa.get('milite')
//        } // fine del ciclo each

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
            listaTurni = Militeturno.findAllByCroceAndMiliteAndGiornoGreaterThan(croce, milite,primoGennaio)
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

} // end of Service Class
