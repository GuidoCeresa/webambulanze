package webambulanze

import grails.plugins.springsecurity.Secured
import org.springframework.security.core.context.SecurityContextHolder

import javax.servlet.http.Cookie

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

    def help() {
        render(view: '/help')
    } // fine del metodo

    //--selezione della croce su cui ritornare
    def logoutSelection() {
        String siglaCroce = GenController.SIGLA_CROCE

        if (siglaCroce.equals(Cost.CROCE_ALGOS) || siglaCroce.equals('nessuna')) {
            redirect(action: 'selezionaCroce')
        }// fine del blocco if

        if (siglaCroce.equals(Cost.CROCE_DEMO)) {
            redirect(action: 'selezionaCroceDemo')
        }// fine del blocco if

        if (siglaCroce.equals(Cost.CROCE_PUBBLICA_CASTELLO)) {
            redirect(action: 'selezionaCrocePAVT')
        }// fine del blocco if

        if (siglaCroce.equals(Cost.CROCE_ROSSA_FIDENZA)) {
            redirect(action: 'selezionaCroceRossaFidenza')
        }// fine del blocco if

        if (siglaCroce.equals(Cost.CROCE_ROSSA_PONTETARO)) {
            redirect(action: 'selezionaCroceRossaPonteTaroSecurity')
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
        params.siglaCroce = croceService.getSiglaCroce(request)

        render(controller: 'gen', view: 'home', params: params)
    } // fine del metodo

    //--selezione iniziale della croce su cui operare
    //--seleziona la necessità del login
    //--regola la schermata iniziale
    def boolean selezionaCroceBase(String siglaCroce) {
        String oldCookie

        //--pulizia
        SecurityContextHolder.clearContext()
        rememberMeServices.logout request, response, null

        //--cancella il cookie di Security
        if (false) {
            Cookie cookie = new Cookie('JSESSIONID', '')
            cookie.maxAge = 10
            response.addCookie(cookie)
        }// fine del blocco if

        //--eventuale cookie già esistente per la setssa croce
        oldCookie = g.cookie(name: Cost.COOKIE_SIGLA_CROCE)

        //--selezione iniziale della croce su cui operare
        if (oldCookie && oldCookie.equals(siglaCroce)) {
            //non fa nulla
            return false
        } else {
            Cookie cookie = new Cookie(Cost.COOKIE_SIGLA_CROCE, siglaCroce)
            cookie.maxAge = 100000
            response.addCookie(cookie)
            return true
        }// fine del blocco if-else

    } // fine del metodo

    //--chiamata senza specificazione, parte la croce demo
    //--selezione iniziale della croce su cui operare
    //--seleziona la necessità del login
    //--regola la schermata iniziale
    def selezionaCroce() {
        //--regolazioni generali
        selezionaCroceAlgos()
    } // fine del metodo

    //--chiamata da URL = algos
    //--selezione iniziale della croce interna su cui operare
    //--regola la schermata iniziale
    def selezionaCroceAlgos() {
        boolean primaVolta

        //--regolazioni generali
        primaVolta = selezionaCroceBase(Cost.CROCE_ALGOS)

        selezionaCroceAlgosSicura(primaVolta)
    } // fine del metodo

    //--chiamata da URL = algos
    //--selezione iniziale della croce interna su cui operare
    //--seleziona la necessità del login
    //--regola la schermata iniziale
    @Secured([Cost.ROLE_ADMIN])
    def selezionaCroceAlgosSicura(boolean primaVolta) {
        params.siglaCroce = croceService.getSiglaCroce(request)
        String startController = croceService.getStartController((String) params.siglaCroce)

        if (primaVolta) {
            redirect(url: '/' + Cost.CROCE_ALGOS)
        } else {
            if (startController) {
                //--va alla schermata specifica
                redirect(controller: startController, params: params)
            } else {
                //--va al menu base
                render(controller: 'gen', view: 'home', params: params)
            }// fine del blocco if-else
        }// fine del blocco if-else
    } // fine del metodo

    def logoutdemo() {
        //--regolazioni generali
        boolean primaVolta = selezionaCroceBase(Cost.CROCE_DEMO)

        params.siglaCroce = croceService.getSiglaCroce(request)
        String startController = croceService.getStartController((String) params.siglaCroce)

        if (primaVolta) {
            redirect(url: '/' + Cost.CROCE_DEMO)
        } else {
            if (startController) {
                //--va alla schermata specifica
                redirect(controller: startController, params: params)
            } else {
                //--va al menu base
                render(controller: 'gen', view: 'home', params: params)
            }// fine del blocco if-else
        }// fine del blocco if-else
    } // fine del metodo

    //--chiamata da URL = demo
    //--selezione iniziale della croce dimostrativa su cui operare
    //--seleziona la necessità del login
    //--regola la schermata iniziale
    def selezionaCroceDemo() {
        //--regolazioni generali
        boolean primaVolta = selezionaCroceBase(Cost.CROCE_DEMO)

        springSecurityService.reauthenticate(Cost.DEMO_OSPITE, Cost.DEMO_PASSWORD)

        params.siglaCroce = croceService.getSiglaCroce(request)
        String startController = croceService.getStartController((String) params.siglaCroce)

        if (primaVolta) {
            redirect(url: '/' + Cost.CROCE_DEMO)
        } else {
            if (startController) {
                //--va alla schermata specifica
                redirect(controller: startController, params: params)
            } else {
                //--va al menu base
                render(controller: 'gen', view: 'home', params: params)
            }// fine del blocco if-else
        }// fine del blocco if-else
    } // fine del metodo

    //--chiamata da URL = pubblica
    //--selezione iniziale della croce su cui operare
    //--seleziona la necessità del login
    //--regola la schermata iniziale
    def selezionaCrocePubblicaCastello() {
        //--regolazioni generali
        boolean primaVolta = selezionaCroceBase(Cost.CROCE_PUBBLICA_CASTELLO)

        params.siglaCroce = croceService.getSiglaCroce(request)
        String startController = croceService.getStartController((String) params.siglaCroce)

        if (primaVolta) {
            redirect(url: '/' + Cost.CROCE_PUBBLICA_CASTELLO)
        } else {
            if (startController) {
                //--va alla schermata specifica
                redirect(controller: startController, params: params)
            } else {
                //--va al menu base
                render(controller: 'gen', view: 'home', params: params)
            }// fine del blocco if-else
        }// fine del blocco if-else
    } // fine del metodo

    //--chiamata da URL = croce rossa fidenza
    //--selezione iniziale della croce su cui operare
    //--seleziona la necessità del login
    //--regola la schermata iniziale
    def selezionaCroceRossaFidenza() {
        //--regolazioni generali
        boolean primaVolta = selezionaCroceBase(Cost.CROCE_ROSSA_FIDENZA)

        params.siglaCroce = croceService.getSiglaCroce(request)
        String startController = croceService.getStartController((String) params.siglaCroce)

        if (primaVolta) {
            redirect(url: '/' + Cost.CROCE_ROSSA_FIDENZA)
        } else {
            if (startController) {
                //--va alla schermata specifica
                redirect(controller: startController, params: params)
            } else {
                //--va al menu base
                render(controller: 'gen', view: 'home', params: params)
            }// fine del blocco if-else
        }// fine del blocco if-else
    } // fine del metodo

    //--chiamata da URL = croce rossa ponte taro
    //--selezione iniziale della croce su cui operare
    //--seleziona la necessità del login
    //--regola la schermata iniziale
    def selezionaCroceRossaPonteTaro() {
        //--regolazioni generali
        boolean primaVolta = selezionaCroceBase(Cost.CROCE_ROSSA_PONTETARO)

        //--levato peché dava problemi con Explorer8 (non capisco la differenza con altri brwser, ma pazienza)
        //--non sembra indispensabile, visto che comunque chiede un login
        //      springSecurityService.reauthenticate(Cost.CRPT_OSPITE, Cost.CRPT_PASSWORD)

        params.siglaCroce = croceService.getSiglaCroce(request)
        String startController = croceService.getStartController((String) params.siglaCroce)

        if (primaVolta) {
            redirect(url: '/' + Cost.CROCE_ROSSA_PONTETARO)
        } else {
            if (startController) {
                //--va alla schermata specifica
                redirect(controller: startController, params: params)
            } else {
                //--va al menu base
                render(controller: 'gen', view: 'home', params: params)
            }// fine del blocco if-else
        }// fine del blocco if-else

    } // fine del metodo

} // fine della controller classe
