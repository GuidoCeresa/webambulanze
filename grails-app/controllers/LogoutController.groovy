import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import webambulanze.Croce

class LogoutController {

	/**
	 * Index action. Redirects to the Spring security logout uri.
	 */
	def index = {
		// TODO put any pre-logout code here
        servletContext.croce = Croce.findBySigla('ALGOS')
		redirect uri: SpringSecurityUtils.securityConfig.logout.filterProcessesUrl // '/j_spring_security_logout'
	}
}
