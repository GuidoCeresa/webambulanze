package webambulanze

import grails.plugins.springsecurity.Secured
import org.springframework.security.core.context.SecurityContextHolder

import javax.servlet.http.Cookie

class AccessoController {

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

    //--controller che gestisce il tabellone
    public static String START_CONTROLLER = 'turno'

    //--tabellone turni
    @Secured([Cost.ROLE_MILITE])
    def index() {
        redirect(controller: 'turno', action: 'tabellone')
    } // fine del metodo

    //--chiamata dai menu delle liste e form
    //--va al menu base
    def home() {
        params.siglaCroce = croceService.getSiglaCroce(request)
        render(controller: 'accesso', view: 'home', params: params)
    } // fine del metodo

    def help() {
        render(view: '/help')
    } // fine del metodo

    def seleziona() {
        String possibileSiglaCroce = ''
        Croce croce = null
        String siglaCroce = ''
        boolean primaVolta
        boolean mostraTabellonePartenza = false
        boolean isTabelloneSecured = true

        if (params.siglaCroce) {
            possibileSiglaCroce = params.siglaCroce
            croce = Croce.findBySigla(possibileSiglaCroce)
            if (croce) {
                siglaCroce = croce.sigla
            }// fine del blocco if
        } else {
            render(view: '/error')
            return
        }// fine del blocco if-else

        //--regolazioni generali
        primaVolta = selezionaCroceBase(siglaCroce)

        if (croceService) {
            mostraTabellonePartenza = croceService.mostraTabellonePartenza(siglaCroce)
            isTabelloneSecured = croceService.isTabelloneSecured(siglaCroce)
        }// fine del blocco if

        if (primaVolta) {
            redirect(url: '/' + siglaCroce)
        } else {
            if (mostraTabellonePartenza) {
                //--va alla schermata specifica
                if (isTabelloneSecured) {
                    redirect(controller: START_CONTROLLER, action: 'indexSecured', params: params)
                } else {
                    redirect(controller: START_CONTROLLER, action: 'index', params: params)
                }// fine del blocco if-else
            } else {
                //--va al menu base
                render(controller: 'accesso', view: 'home', params: params)
            }// fine del blocco if-else
        }// fine del blocco if-else
    } // fine del metodo

    def selezionaLogin = {
//        Croce croce
//        String siglaCroce
//
//        //--recupera la croce
//        if (croceService) {
//            croce = croceService.getCroce(request)
//        }// fine del blocco if
//        if (croce) {
//            siglaCroce = croce.sigla
//        } else {
//            render(view: '/error')
//            return
//        }// fine del blocco if-else
//         def a=params
        redirect(controller: START_CONTROLLER, action: 'indexSecured', params: params)
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

} // fine della controller classe
