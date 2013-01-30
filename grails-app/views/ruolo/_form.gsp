<!--Created by Algos s.r.l.-->
<!--Date: mag 2012-->
<!--Questo file è stato installato dal plugin AlgosBase-->
<!--Tipicamente NON verrà più sovrascritto dalle successive release del plugin-->
<!--in quanto POTREBBE essere personalizzato in questa applicazione-->
<!--Se vuoi che le prossime release del plugin sovrascrivano questo file,-->
<!--perdendo tutte le modifiche effettuate qui,-->
<!--regola a true il flag di controllo flagOverwrite©-->
<!--flagOverwrite = false-->

<%@ page import="webambulanze.Ruolo" %>



<div class="fieldcontain ${hasErrors(bean: ruoloInstance, field: 'authority', 'error')} required">
	<label for="authority">
		<g:message code="ruolo.authority.labelform" default="Authority" />
		<span class="required-indicator">*</span>
	</label>
	









<g:textField name="authority" required="" value="${ruoloInstance?.authority}"/>
</div>

<g:if test="${campiExtra}">
    <amb:extraSchedaForm rec="${ruoloInstance}" campiExtra="${campiExtra}"></amb:extraSchedaForm>
</g:if>
