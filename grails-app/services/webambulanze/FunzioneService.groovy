package webambulanze

class FunzioneService {


    def campiExtra(grailsApplication) {
        def campiExtra = null
        Croce croce
        String sigla

        if (grailsApplication.mainContext.servletContext.croce) {
            croce = grailsApplication.mainContext.servletContext.croce
            sigla = croce.sigla
            if (!sigla.equals(Cost.CROCE_ALGOS)) {
                campiExtra = this.campiExtraPerCroce(croce)
            }// fine del blocco if-else
        }// fine del blocco if

        return campiExtra
    }

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
