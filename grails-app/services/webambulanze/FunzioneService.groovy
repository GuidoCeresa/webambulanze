package webambulanze

class FunzioneService {
    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def croceService

    //--recupera tutte le funzioni della croce attiva
    def campiExtra(grailsApplication) {
        def campiExtra = null
        String sigla
        Croce croce = croceService.getCroceCorrente()

        if (croce) {
            sigla = croce.sigla
            if (!sigla.equals(Cost.CROCE_ALGOS)) {
                campiExtra = this.campiExtraPerCroce(croce)
            }// fine del blocco if-else
        }// fine del blocco if

        return campiExtra
    }

    //--recupera tutte le funzioni della croce selezionata
    def campiExtraStatistichePerCroce(Croce croce) {
        def campiExtra = campiExtraPerCroce(croce)

        if (campiExtra) {
            //    campiExtra.add(0, Cost.CAMPO_ORE)
            //    campiExtra.add(0, Cost.CAMPO_TURNI)
        }// fine del blocco if

        return campiExtra
    }// fine del metodo

    //--recupera tutti i records per la croce selezionata
    def campiExtraPerCroce(Croce croce) {
        def campiExtra = []
        def lista = Funzione.findAllByCroce(croce, [order: 'sigla'])

        lista?.each {
            campiExtra += it.sigla
        } // fine del ciclo each

        return campiExtra
    }// fine del metodo

    //--recupera tutti i records per la croce selezionata
    def campiExtraPerCroce(long croceId) {
        def campiExtra = null
        Croce croce = Croce.findById(croceId)

        if (croce) {
            campiExtra = this.campiExtraPerCroce(croce)
        }// fine del blocco if

        return campiExtra
    }// fine del metodo

} // end of Service Class
