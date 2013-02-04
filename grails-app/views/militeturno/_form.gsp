<!--Created by Algos s.r.l.-->
<!--Date: mag 2012-->
<!--Questo file è stato installato dal plugin AlgosBase-->
<!--Tipicamente NON verrà più sovrascritto dalle successive release del plugin-->
<!--in quanto POTREBBE essere personalizzato in questa applicazione-->
<!--Se vuoi che le prossime release del plugin sovrascrivano questo file,-->
<!--perdendo tutte le modifiche effettuate qui,-->
<!--regola a true il flag di controllo flagOverwrite©-->
<!--flagOverwrite = false-->

<%@ page import="webambulanze.Militeturno" %>



<div class="fieldcontain ${hasErrors(bean: militeturnoInstance, field: 'croce', 'error')} required">
	<label for="croce">
		<g:message code="militeturno.croce.labelform" default="Croce" />
		<span class="required-indicator">*</span>
	</label>
	









<g:select id="croce" name="croce.id" from="${webambulanze.Croce.list()}" optionKey="id" required="" value="${militeturnoInstance?.croce?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: militeturnoInstance, field: 'milite', 'error')} required">
	<label for="milite">
		<g:message code="militeturno.milite.labelform" default="Milite" />
		<span class="required-indicator">*</span>
	</label>
	









<g:select id="milite" name="milite.id" from="${webambulanze.Milite.list()}" optionKey="id" required="" value="${militeturnoInstance?.milite?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: militeturnoInstance, field: 'turno', 'error')} required">
	<label for="turno">
		<g:message code="militeturno.turno.labelform" default="Turno" />
		<span class="required-indicator">*</span>
	</label>
	









<g:select id="turno" name="turno.id" from="${webambulanze.Turno.list()}" optionKey="id" required="" value="${militeturnoInstance?.turno?.id}" class="many-to-one"/>
</div>

<g:if test="${campiExtra}">
    <amb:extraSchedaForm rec="${militeturnoInstance}" campiExtra="${campiExtra}"></amb:extraSchedaForm>
</g:if>
