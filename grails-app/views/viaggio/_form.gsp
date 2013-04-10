<!--Created by Algos s.r.l.-->
<!--Date: mag 2012-->
<!--Questo file è stato installato dal plugin AlgosBase-->
<!--Tipicamente NON verrà più sovrascritto dalle successive release del plugin-->
<!--in quanto POTREBBE essere personalizzato in questa applicazione-->
<!--Se vuoi che le prossime release del plugin sovrascrivano questo file,-->
<!--perdendo tutte le modifiche effettuate qui,-->
<!--regola a true il flag di controllo flagOverwrite©-->
<!--flagOverwrite = false-->

<%@ page import="webambulanze.Viaggio" %>



<div class="fieldcontain ${hasErrors(bean: viaggioInstance, field: 'croce', 'error')} required">
	<label for="croce">
		<g:message code="viaggio.croce.labelform" default="Croce" />
		<span class="required-indicator">*</span>
	</label>
	









<g:select id="croce" name="croce.id" from="${webambulanze.Croce.list()}" optionKey="id" required="" value="${viaggioInstance?.croce?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: viaggioInstance, field: 'giorno', 'error')} required">
	<label for="giorno">
		<g:message code="viaggio.giorno.labelform" default="Giorno" />
		<span class="required-indicator">*</span>
	</label>
	









<g:datePicker name="giorno" precision="day"  value="${viaggioInstance?.giorno}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: viaggioInstance, field: 'inizio', 'error')} required">
	<label for="inizio">
		<g:message code="viaggio.inizio.labelform" default="Inizio" />
		<span class="required-indicator">*</span>
	</label>
	









<g:datePicker name="inizio" precision="day"  value="${viaggioInstance?.inizio}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: viaggioInstance, field: 'fine', 'error')} required">
	<label for="fine">
		<g:message code="viaggio.fine.labelform" default="Fine" />
		<span class="required-indicator">*</span>
	</label>
	









<g:datePicker name="fine" precision="day"  value="${viaggioInstance?.fine}"  />
</div>

<g:if test="${campiExtra}">
    <amb:extraSchedaForm rec="${viaggioInstance}" campiExtra="${campiExtra}"></amb:extraSchedaForm>
</g:if>
