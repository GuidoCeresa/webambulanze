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

    //--sigla della croce corrente
    public static String SIGLA_CROCE = 'nessuna'

    // la property viene iniettata automaticamente
    def grailsApplication

    //--tabellone turni
    @Secured([Cost.ROLE_MILITE])
    def index() {
        redirect(controller: 'turno', action: 'tabellone')
    } // fine del metodo

    //--selezione della croce su cui ritornare
    def logoutselection() {
        String croce = SIGLA_CROCE

        if (croce && croce.equals(Cost.CROCE_ALGOS)) {
            redirect(action: 'selezionaCroce')
        }// fine del blocco if

        if (croce && croce.equals(Cost.CROCE_DEMO)) {
            redirect(action: 'selezionaCroceDemo')
        }// fine del blocco if

        if (croce && croce.equals(Cost.CROCE_PUBBLICA)) {
            redirect(action: 'selezionaCrocePAVT')
        }// fine del blocco if

        if (croce && croce.equals(Cost.CROCE_ROSSA)) {
            redirect(action: 'selezionaCroceCRF')
        }// fine del blocco if
    }

    //--riparte come algos
    def logoutalgos() {
        selezionaCroceBase(Cost.CROCE_ALGOS)

        //--va al menu base
        render(controller: 'gen', view: 'home')
    }

    //--chiamata dai menu delle liste e form
    def home() {
        //--va al menu base
        render(controller: 'gen', view: 'home')
    } // fine del metodo

    //--selezione iniziale della croce su cui operare
    //--seleziona la necessità del login
    //--regola la schermata iniziale
    def selezionaCroceBase(String croce) {
        SIGLA_CROCE = croce

        //--pulizia
        session.invalidate()
        SecurityContextHolder.clearContext()
        rememberMeServices.logout request, response, null

        //--selezione iniziale della croce su cui operare
        grailsApplication.mainContext.servletContext.croce = Croce.findBySigla(croce)

        //--seleziona la necessità del login
        grailsApplication.mainContext.servletContext.startLogin = Settings.startLogin(croce)

        //--seleziona la videata iniziale
        grailsApplication.mainContext.servletContext.startController = Settings.startController(croce)

        //--seleziona (flag booleano) se mostrare tutti i controllers nella videata Home
        grailsApplication.mainContext.servletContext.allControllers = Settings.allControllers(croce)

        //--seleziona (lista di stringhe) i controllers da mostrare nella videata Home
        grailsApplication.mainContext.servletContext.controlli = Settings.controlli(croce)
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
    def selezionaCroceAlgos() {
        //--regolazioni generali
        selezionaCroceBase(Cost.CROCE_ALGOS)

        selezionaCroceAlgosSicura()
    } // fine del metodo

    //--chiamata da URL = algos
    //--selezione iniziale della croce interna su cui operare
    //--seleziona la necessità del login
    //--regola la schermata iniziale
    @Secured([Cost.ROLE_ADMIN])
    def selezionaCroceAlgosSicura() {
        if (grailsApplication.mainContext.servletContext.startController) {
            //--va alla schermata specifica
            redirect(controller: grailsApplication.mainContext.servletContext.startController)
        } else {
            //--va al menu base
            render(controller: 'gen', view: 'home')
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

        if (grailsApplication.mainContext.servletContext.startController) {
            //--va alla schermata specifica
            redirect(controller: grailsApplication.mainContext.servletContext.startController)
        } else {
            //--va al menu base
            render(controller: 'gen', view: 'home')
        }// fine del blocco if-else
    } // fine del metodo

    //--chiamata da URL = pubblica
    //--selezione iniziale della croce su cui operare
    //--seleziona la necessità del login
    //--regola la schermata iniziale
    def selezionaCrocePAVT() {
        //--regolazioni generali
        selezionaCroceBase(Cost.CROCE_PUBBLICA)

        if (grailsApplication.mainContext.servletContext.startController) {
            //--va alla schermata specifica
            redirect(controller: grailsApplication.mainContext.servletContext.startController)
        } else {
            //--va al menu base
            render(controller: 'gen', view: 'home')
        }// fine del blocco if-else
    } // fine del metodo

    //--chiamata da URL = croce rossa
    //--selezione iniziale della croce su cui operare
    //--seleziona la necessità del login
    //--regola la schermata iniziale
//    @Secured([Cost.ROLE_MILITE])
    def selezionaCroceCRF() {
        //--regolazioni generali
        selezionaCroceBase(Cost.CROCE_ROSSA)

        if (grailsApplication.mainContext.servletContext.startController) {
            //--va alla schermata specifica
            redirect(controller: grailsApplication.mainContext.servletContext.startController)
        } else {
            //--va al menu base
            render(controller: 'gen', view: 'home')
        }// fine del blocco if-else
    } // fine del metodo

}
