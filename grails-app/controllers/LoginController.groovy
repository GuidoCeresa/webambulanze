import grails.converters.JSON
import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import org.springframework.security.authentication.AccountExpiredException
import org.springframework.security.authentication.CredentialsExpiredException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.LockedException
import org.springframework.security.core.context.SecurityContextHolder as SCH
import org.springframework.security.web.WebAttributes
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import webambulanze.Cost
import webambulanze.Croce
import webambulanze.Utente

import javax.servlet.http.HttpServletResponse

class LoginController {

    /**
     * Dependency injection for the authenticationTrustResolver.
     */
    def authenticationTrustResolver

    /**
     * Dependency injection for the springSecurityService.
     */
    def springSecurityService

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def croceService

    // utilizzo di un service con la businessLogic per l'elaborazione dei dati
    // il service viene iniettato automaticamente
    def utenteService

    /**
     * Default action; redirects to 'defaultTargetUrl' if logged in, /login/auth otherwise.
     */
    def index = {
        params.siglaCroce = session[Cost.SESSIONE_SIGLA_CROCE]

        if (springSecurityService.isLoggedIn()) {
            redirect uri: SpringSecurityUtils.securityConfig.successHandler.defaultTargetUrl
        } else {
            redirect action: 'auth', params: params
        }
    }

    /**
     * Show the login page.
     */
    def auth = {
        Croce croce
        def listaGrezza
        Utente utente
        String username
        ArrayList<String> listaUtenti
        def config = SpringSecurityUtils.securityConfig

        croce = croceService.getCroce(session)
        if (croce) {
            params.siglaCroce = croce.sigla
            listaGrezza = Utente.findAllByCroce(croce, [sort: 'username'])
        } else {
            listaGrezza = Utente.findAll([sort: 'username'])
        }// fine del blocco if-else

        if (listaGrezza) {
            listaUtenti = new ArrayList<String>()
            listaGrezza?.each {
                utente = (Utente) it
                username = utente.username
                listaUtenti.add(username)
            } // fine del ciclo each
        }// fine del blocco if

        //--sposta in fondo un eventuale nome del programmatore
        if (listaUtenti) {
            listaUtenti = utenteService.spostaProgrammatoreInFondo(listaUtenti)
            listaUtenti = utenteService.spostaOspiteInFondo(listaUtenti)
        }// fine del blocco if

//        listaUtenti = Utente.executeQuery('select username from Utente order by username')

        if (springSecurityService.isLoggedIn()) {
            redirect uri: config.successHandler.defaultTargetUrl
            return
        }
        String view = 'auth'
        String postUrl = "${request.contextPath}${config.apf.filterProcessesUrl}"
        render view: view, model: [postUrl: postUrl,
                rememberMeParameter: config.rememberMe.parameter, listaUtenti: listaUtenti]
    }

    /**
     * The redirect action for Ajax requests.
     */
    def authAjax = {
        response.setHeader 'Location', SpringSecurityUtils.securityConfig.auth.ajaxLoginFormUrl
        response.sendError HttpServletResponse.SC_UNAUTHORIZED
    }

    /**
     * Show denied page.
     */
    def denied = {
        if (springSecurityService.isLoggedIn() &&
                authenticationTrustResolver.isRememberMe(SCH.context?.authentication)) {
            // have cookie but the page is guarded with IS_AUTHENTICATED_FULLY
            redirect action: 'full', params: params
        }
    }

    /**
     * Login page for users with a remember-me cookie but accessing a IS_AUTHENTICATED_FULLY page.
     */
    def full = {
        params.siglaCroce = session[Cost.SESSIONE_SIGLA_CROCE]
        def config = SpringSecurityUtils.securityConfig
        render view: 'auth', params: params,
                model: [hasCookie: authenticationTrustResolver.isRememberMe(SCH.context?.authentication),
                        postUrl: "${request.contextPath}${config.apf.filterProcessesUrl}"]
    }

    /**
     * Callback after a failed login. Redirects to the auth page with a warning message.
     */
    def authfail = {
        params.siglaCroce = session[Cost.SESSIONE_SIGLA_CROCE]

        def username = session[UsernamePasswordAuthenticationFilter.SPRING_SECURITY_LAST_USERNAME_KEY]
        String msg = ''
        def exception = session[WebAttributes.AUTHENTICATION_EXCEPTION]
        if (exception) {
            if (exception instanceof AccountExpiredException) {
                msg = g.message(code: "springSecurity.errors.login.expired")
            } else if (exception instanceof CredentialsExpiredException) {
                msg = g.message(code: "springSecurity.errors.login.passwordExpired")
            } else if (exception instanceof DisabledException) {
                msg = g.message(code: "springSecurity.errors.login.disabled")
            } else if (exception instanceof LockedException) {
                msg = g.message(code: "springSecurity.errors.login.locked")
            } else {
                msg = g.message(code: "springSecurity.errors.login.fail")
            }
        }

        if (springSecurityService.isAjax(request)) {
            render([error: msg] as JSON)
        } else {
            flash.message = msg
            redirect action: 'auth', params: params
        }
    }

    /**
     * The Ajax success redirect url.
     */
    def ajaxSuccess = {
        render([success: true, username: springSecurityService.authentication.name] as JSON)
    }

    /**
     * The Ajax denied redirect url.
     */
    def ajaxDenied = {
        render([error: 'access denied'] as JSON)
    }
}
