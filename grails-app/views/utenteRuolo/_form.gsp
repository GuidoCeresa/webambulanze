<!--Created by Algos s.r.l.-->
<!--Date: mag 2012-->
<!--Questo file è stato installato dal plugin AlgosBase-->
<!--Tipicamente NON verrà più sovrascritto dalle successive release del plugin-->
<!--in quanto POTREBBE essere personalizzato in questa applicazione-->
<!--Se vuoi che le prossime release del plugin sovrascrivano questo file,-->
<!--perdendo tutte le modifiche effettuate qui,-->
<!--regola a true il flag di controllo flagOverwrite©-->
<!--flagOverwrite = false-->

<%@ page import="webambulanze.UtenteRuolo" %>



<div class="fieldcontain ${hasErrors(bean: utenteRuoloInstance, field: 'ruolo', 'error')} required">
	<label for="ruolo">
		<g:message code="utenteRuolo.ruolo.labelform" default="Ruolo" />
		<span class="required-indicator">*</span>
	</label>
	









<g:select id="ruolo" name="ruolo.id" from="${webambulanze.Ruolo.list()}" optionKey="id" required="" value="${utenteRuoloInstance?.ruolo?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: utenteRuoloInstance, field: 'utente', 'error')} required">
	<label for="utente">
		<g:message code="utenteRuolo.utente.labelform" default="Utente" />
		<span class="required-indicator">*</span>
	</label>
	









<g:select id="utente" name="utente.id" from="${webambulanze.Utente.list()}" optionKey="id" required="" value="${utenteRuoloInstance?.utente?.id}" class="many-to-one"/>
</div>

<g:if test="${campiExtra}">
    <amb:extraSchedaForm rec="${utenteRuoloInstance}" campiExtra="${campiExtra}"></amb:extraSchedaForm>
</g:if>
