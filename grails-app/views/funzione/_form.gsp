<!--Created by Algos s.r.l.-->
<!--Date: mag 2012-->
<!--Questo file è stato installato dal plugin AlgosBase-->
<!--Tipicamente NON verrà più sovrascritto dalle successive release del plugin-->
<!--in quanto POTREBBE essere personalizzato in questa applicazione-->
<!--Se vuoi che le prossime release del plugin sovrascrivano questo file,-->
<!--perdendo tutte le modifiche effettuate qui,-->
<!--regola a true il flag di controllo flagOverwrite©-->
<!--flagOverwrite = false-->

<%@ page import="webambulanze.Funzione" %>



<div class="fieldcontain ${hasErrors(bean: funzioneInstance, field: 'croce', 'error')} required">
	<label for="croce">
		<g:message code="funzione.croce.labelform" default="Croce" />
		<span class="required-indicator">*</span>
	</label>
	









<g:select id="croce" name="croce.id" from="${webambulanze.Croce.list()}" optionKey="id" required="" value="${funzioneInstance?.croce?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: funzioneInstance, field: 'ordine', 'error')} required">
	<label for="ordine">
		<g:message code="funzione.ordine.labelform" default="Ordine" />
		<span class="required-indicator">*</span>
	</label>
	









<g:field name="ordine" type="number" value="${funzioneInstance.ordine}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: funzioneInstance, field: 'sigla', 'error')} required">
	<label for="sigla">
		<g:message code="funzione.sigla.labelform" default="Sigla" />
		<span class="required-indicator">*</span>
	</label>
	









<g:textField name="sigla" required="" value="${funzioneInstance?.sigla}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: funzioneInstance, field: 'siglaVisibile', 'error')} required">
	<label for="siglaVisibile">
		<g:message code="funzione.siglaVisibile.labelform" default="Sigla Visibile" />
		<span class="required-indicator">*</span>
	</label>
	









<g:textField name="siglaVisibile" required="" value="${funzioneInstance?.siglaVisibile}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: funzioneInstance, field: 'descrizione', 'error')} required">
	<label for="descrizione">
		<g:message code="funzione.descrizione.labelform" default="Descrizione" />
		<span class="required-indicator">*</span>
	</label>
	









<g:textField name="descrizione" required="" value="${funzioneInstance?.descrizione}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: funzioneInstance, field: 'funzioniDipendenti', 'error')} ">
	<label for="funzioniDipendenti">
		<g:message code="funzione.funzioniDipendenti.labelform" default="Funzioni Dipendenti" />
		
	</label>
	









<g:textField name="funzioniDipendenti" value="${funzioneInstance?.funzioniDipendenti}"/>
</div>

<g:if test="${campiExtra}">
    <amb:extraSchedaForm rec="${funzioneInstance}" campiExtra="${campiExtra}"></amb:extraSchedaForm>
</g:if>
