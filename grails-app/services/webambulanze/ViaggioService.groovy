package webambulanze

class ViaggioService {

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
    public ArrayList listaAutisti(Croce croce) {
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
    public ArrayList listaSocDae(Croce croce) {
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
    public ArrayList listaSoccorritori(Croce croce) {
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
    public ArrayList listaBarellieri(Croce croce) {
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

} // end of Service Class
