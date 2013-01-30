<!--Created by Algos s.r.l.-->
<!--Date: mag 2012-->
<!--Questo file è stato installato dal plugin AlgosBase-->
<!--Tipicamente NON verrà più sovrascritto dalle successive release del plugin-->
<!--in quanto POTREBBE essere personalizzato in questa applicazione-->
<!--Se vuoi che le prossime release del plugin sovrascrivano questo file,-->
<!--perdendo tutte le modifiche effettuate qui,-->
<!--regola a true il flag di controllo flagOverwrite©-->
<!--flagOverwrite = false-->

<%@ page import="org.codehaus.groovy.grails.plugins.springsecurity.ui.RegistrationCode" %>



<div class="fieldcontain ${hasErrors(bean: registrationCodeInstance, field: 'token', 'error')} ">
	<label for="token">
		<g:message code="registrationCode.token.labelform" default="Token" />
		
	</label>
	









<g:textField name="token" value="${registrationCodeInstance?.token}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: registrationCodeInstance, field: 'username', 'error')} ">
	<label for="username">
		<g:message code="registrationCode.username.labelform" default="Username" />
		
	</label>
	









<g:textField name="username" value="${registrationCodeInstance?.username}"/>
</div>

<g:if test="${campiExtra}">
    <amb:extraSchedaForm rec="${registrationCodeInstance}" campiExtra="${campiExtra}"></amb:extraSchedaForm>
</g:if>
