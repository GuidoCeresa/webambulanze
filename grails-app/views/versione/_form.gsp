<!--Created by Algos s.r.l.-->
<!--Date: mag 2012-->
<!--Questo file è stato installato dal plugin AlgosBase-->
<!--Tipicamente NON verrà più sovrascritto dalle successive release del plugin-->
<!--in quanto POTREBBE essere personalizzato in questa applicazione-->
<!--Se vuoi che le prossime release del plugin sovrascrivano questo file,-->
<!--perdendo tutte le modifiche effettuate qui,-->
<!--regola a true il flag di controllo flagOverwrite©-->
<!--flagOverwrite = false-->

<%@ page import="webambulanze.Versione" %>



<div class="fieldcontain ${hasErrors(bean: versioneInstance, field: 'numero', 'error')} required">
	<label for="numero">
		<g:message code="versione.numero.labelform" default="Numero" />
		<span class="required-indicator">*</span>
	</label>
	









<g:field name="numero" type="number" value="${versioneInstance.numero}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: versioneInstance, field: 'croce', 'error')} required">
	<label for="croce">
		<g:message code="versione.croce.labelform" default="Croce" />
		<span class="required-indicator">*</span>
	</label>
	









<g:select id="croce" name="croce.id" from="${webambulanze.Croce.list()}" optionKey="id" required="" value="${versioneInstance?.croce?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: versioneInstance, field: 'giorno', 'error')} required">
	<label for="giorno">
		<g:message code="versione.giorno.labelform" default="Giorno" />
		<span class="required-indicator">*</span>
	</label>
	









<g:datePicker name="giorno" precision="day"  value="${versioneInstance?.giorno}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: versioneInstance, field: 'titolo', 'error')} ">
	<label for="titolo">
		<g:message code="versione.titolo.labelform" default="Titolo" />
		
	</label>
	









<g:textField name="titolo" value="${versioneInstance?.titolo}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: versioneInstance, field: 'descrizione', 'error')} ">
	<label for="descrizione">
		<g:message code="versione.descrizione.labelform" default="Descrizione" />
		
	</label>
	









<g:textArea name="descrizione" cols="40" rows="5" value="${versioneInstance?.descrizione}"/>
</div>

<g:if test="${campiExtra}">
    <amb:extraSchedaForm rec="${versioneInstance}" campiExtra="${campiExtra}"></amb:extraSchedaForm>
</g:if>
