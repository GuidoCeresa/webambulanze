package webambulanze

import grails.plugins.springsecurity.Secured
import org.springframework.security.core.context.SecurityContextHolder

class GenController {

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def springSecurityService

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def rememberMeServices

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def croceService

    //--sigla della croce corrente
    public static String SIGLA_CROCE = 'nessuna'

    //--tabellone turni
    @Secured([Cost.ROLE_MILITE])
    def index() {
        redirect(controller: 'turno', action: 'tabellone')
    } // fine del metodo

    //--selezione della croce su cui ritornare
    def logoutselection() {
        String siglaCroce = GenController.SIGLA_CROCE

        if (siglaCroce.equals(Cost.CROCE_ALGOS)) {
            redirect(action: 'selezionaCroce')
        }// fine del blocco if

        if (siglaCroce.equals(Cost.CROCE_DEMO)) {
            redirect(action: 'selezionaCroceDemo')
        }// fine del blocco if

        if (siglaCroce.equals(Cost.CROCE_PUBBLICA)) {
            redirect(action: 'selezionaCrocePAVT')
        }// fine del blocco if

        if (siglaCroce.equals(Cost.CROCE_ROSSA_FIDENZA)) {
            redirect(action: 'selezionaCroceRossaFidenza')
        }// fine del blocco if

        if (siglaCroce.equals(Cost.CROCE_ROSSA_PONTETARO)) {
            redirect(action: 'selezionaCroceRossaPonteTaro')
        }// fine del blocco if
    } // fine del metodo

    //--riparte come algos
    def logoutalgos() {
        selezionaCroceBase(Cost.CROCE_ALGOS)

        //--va al menu base
        render(controller: 'gen', view: 'home')
    } // fine del metodo

    //--chiamata dai menu delle liste e form
    //--va al menu base
    def home() {
        params.siglaCroce = session[Cost.SESSIONE_SIGLA_CROCE]

        render(controller: 'gen', view: 'home', params: params)
    } // fine del metodo

    //--selezione iniziale della croce su cui operare
    //--seleziona la necessità del login
    //--regola la schermata iniziale
    def selezionaCroceBase(String siglaCroce) {

        //--pulizia
        SecurityContextHolder.clearContext()
        rememberMeServices.logout request, response, null

        //--selezione iniziale della croce su cui operare
        session[Cost.SESSIONE_SIGLA_CROCE] = siglaCroce

    } // fine del metodo

    //--chiamata senza specificazione, parte la croce demo
    //--selezione iniziale della croce su cui operare
    //--seleziona la necessità del login
    //--regola la schermata iniziale
    def selezionaCroce() {
        //--regolazioni generali
        selezionaCroceDemo()
    } // fine del metodo

    //--chiamata da URL = algos
    //--selezione iniziale della croce interna su cui operare
    //--regola la schermata iniziale
    @Secured([Cost.ROLE_ADMIN])
    def selezionaCroceAlgos() {
        //--regolazioni generali
        selezionaCroceBase(Cost.CROCE_ALGOS)

        selezionaCroceAlgosSicura()
    } // fine del metodo

    //--chiamata da URL = algos
    //--selezione iniziale della croce interna su cui operare
    //--seleziona la necessità del login
    //--regola la schermata iniziale
    def selezionaCroceAlgosSicura() {
        params.siglaCroce = session[Cost.SESSIONE_SIGLA_CROCE]
        String startController = croceService.getStartController((String) params.siglaCroce)

        if (startController) {
            //--va alla schermata specifica
            redirect(controller: startController, params: params)
        } else {
            //--va al menu base
            render(controller: 'gen', view: 'home', params: params)
        }// fine del blocco if-else
    } // fine del metodo

    def logoutdemo() {
        //--regolazioni generali
        selezionaCroceBase(Cost.CROCE_DEMO)

        params.siglaCroce = session[Cost.SESSIONE_SIGLA_CROCE]
        String startController = croceService.getStartController((String) params.siglaCroce)

        if (startController) {
            //--va alla schermata specifica
            redirect(controller: startController, params: params)
        } else {
            //--va al menu base
            render(controller: 'gen', view: 'home', params: params)
        }// fine del blocco if-else
    } // fine del metodo

    //--chiamata da URL = demo
    //--selezione iniziale della croce dimostrativa su cui operare
    //--seleziona la necessità del login
    //--regola la schermata iniziale
    def selezionaCroceDemo() {
        //--regolazioni generali
        selezionaCroceBase(Cost.CROCE_DEMO)

        springSecurityService.reauthenticate(Cost.DEMO_OSPITE, Cost.DEMO_PASSWORD)

        params.siglaCroce = session[Cost.SESSIONE_SIGLA_CROCE]
        String startController = croceService.getStartController((String) params.siglaCroce)

        if (startController) {
            //--va alla schermata specifica
            redirect(controller: startController, params: params)
        } else {
            //--va al menu base
            render(controller: 'gen', view: 'home', params: params)
        }// fine del blocco if-else
    } // fine del metodo

    //--chiamata da URL = pubblica
    //--selezione iniziale della croce su cui operare
    //--seleziona la necessità del login
    //--regola la schermata iniziale
    def selezionaCrocePAVT() {
        //--regolazioni generali
        selezionaCroceBase(Cost.CROCE_PUBBLICA)

        params.siglaCroce = session[Cost.SESSIONE_SIGLA_CROCE]
        String startController = croceService.getStartController((String) params.siglaCroce)

        if (startController) {
            //--va alla schermata specifica
            redirect(controller: startController, params: params)
        } else {
            //--va al menu base
            render(controller: 'gen', view: 'home', params: params)
        }// fine del blocco if-else
    } // fine del metodo

    //--chiamata da URL = croce rossa fidenza
    //--selezione iniziale della croce su cui operare
    //--seleziona la necessità del login
    //--regola la schermata iniziale
    def selezionaCroceRossaFidenza() {
        //--regolazioni generali
        selezionaCroceBase(Cost.CROCE_ROSSA_FIDENZA)

        params.siglaCroce = session[Cost.SESSIONE_SIGLA_CROCE]
        String startController = croceService.getStartController((String) params.siglaCroce)

        if (startController) {
            //--va alla schermata specifica
            redirect(controller: startController, params: params)
        } else {
            //--va al menu base
            render(controller: 'gen', view: 'home', params: params)
        }// fine del blocco if-else
    } // fine del metodo

    //--chiamata da URL = croce rossa ponte taro
    //--selezione iniziale della croce su cui operare
    //--seleziona la necessità del login
    //--regola la schermata iniziale
    def selezionaCroceRossaPonteTaro() {
        //--regolazioni generali
        selezionaCroceBase(Cost.CROCE_ROSSA_PONTETARO)

        springSecurityService.reauthenticate(Cost.CRPT_OSPITE, Cost.CRPT_PASSWORD)

        params.siglaCroce = session[Cost.SESSIONE_SIGLA_CROCE]
        String startController = croceService.getStartController((String) params.siglaCroce)

        if (startController) {
            //--va alla schermata specifica
            redirect(controller: startController, params: params)
        } else {
            //--va al menu base
            render(controller: 'gen', view: 'home', params: params)
        }// fine del blocco if-else
    } // fine del metodo

} // fine della controller classe
