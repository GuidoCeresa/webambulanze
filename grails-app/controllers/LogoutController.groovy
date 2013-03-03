import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import webambulanze.Cost
import webambulanze.GenController

class LogoutController {

    /**
     * Index action. Redirects to the Spring security logout uri.
     */
    def index = {
        // TODO put any pre-logout code here
        GenController.SIGLA_CROCE = session[Cost.SESSIONE_SIGLA_CROCE]

        redirect uri: SpringSecurityUtils.securityConfig.logout.filterProcessesUrl // '/j_spring_security_logout'
    }
}
