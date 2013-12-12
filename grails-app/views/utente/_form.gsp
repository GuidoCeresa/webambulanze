<!--Created by Algos s.r.l.-->
<!--Date: mag 2012-->
<!--Questo file è stato installato dal plugin AlgosBase-->
<!--Tipicamente NON verrà più sovrascritto dalle successive release del plugin-->
<!--in quanto POTREBBE essere personalizzato in questa applicazione-->
<!--Se vuoi che le prossime release del plugin sovrascrivano questo file,-->
<!--perdendo tutte le modifiche effettuate qui,-->
<!--regola a true il flag di controllo flagOverwrite©-->
<!--flagOverwrite = false-->

<%@ page import="webambulanze.Utente" %>



<div class="fieldcontain ${hasErrors(bean: utenteInstance, field: 'milite', 'error')} ">
	<label for="milite">
		<g:message code="utente.milite.labelform" default="Milite" />
		
	</label>









<g:select id="milite" name="milite.id" from="${lista}" optionKey="id" value="${utenteInstance?.milite?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: utenteInstance, field: 'nickname', 'error')} ">
	<label for="nickname">
		<g:message code="utente.nickname.labelform" default="Nickname" />
		
	</label>
	









<g:textField name="nickname" value="${utenteInstance?.nickname}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: utenteInstance, field: 'password', 'error')} required">
	<label for="password">
		<g:message code="utente.password.labelform" default="Password" />
		<span class="required-indicator">*</span>
	</label>
	









<g:textField name="password" required="" value="${utenteInstance?.password}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: utenteInstance, field: 'pass', 'error')} ">
	<label for="pass">
		<g:message code="utente.pass.labelform" default="Pass" />
		
	</label>
	









<g:textField name="pass" value="${utenteInstance?.pass}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: utenteInstance, field: 'enabled', 'error')} ">
	<label for="enabled">
		<g:message code="utente.enabled.labelform" default="Enabled" />
		
	</label>
	









<g:checkBox name="enabled" value="${utenteInstance?.enabled}" />
</div>

<div class="fieldcontain ${hasErrors(bean: utenteInstance, field: 'accountExpired', 'error')} ">
	<label for="accountExpired">
		<g:message code="utente.accountExpired.labelform" default="Account Expired" />
		
	</label>
	









<g:checkBox name="accountExpired" value="${utenteInstance?.accountExpired}" />
</div>

<div class="fieldcontain ${hasErrors(bean: utenteInstance, field: 'accountLocked', 'error')} ">
	<label for="accountLocked">
		<g:message code="utente.accountLocked.labelform" default="Account Locked" />
		
	</label>
	









<g:checkBox name="accountLocked" value="${utenteInstance?.accountLocked}" />
</div>

<div class="fieldcontain ${hasErrors(bean: utenteInstance, field: 'passwordExpired', 'error')} ">
	<label for="passwordExpired">
		<g:message code="utente.passwordExpired.labelform" default="Password Expired" />
		
	</label>
	









<g:checkBox name="passwordExpired" value="${utenteInstance?.passwordExpired}" />
</div>

<g:if test="${campiExtra}">
    <amb:extraSchedaForm rec="${utenteInstance}" campiExtra="${campiExtra}"></amb:extraSchedaForm>
</g:if>
