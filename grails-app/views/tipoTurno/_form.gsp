<!--Created by Algos s.r.l.-->
<!--Date: mag 2012-->
<!--Questo file è stato installato dal plugin AlgosBase-->
<!--Tipicamente NON verrà più sovrascritto dalle successive release del plugin-->
<!--in quanto POTREBBE essere personalizzato in questa applicazione-->
<!--Se vuoi che le prossime release del plugin sovrascrivano questo file,-->
<!--perdendo tutte le modifiche effettuate qui,-->
<!--regola a true il flag di controllo flagOverwrite©-->
<!--flagOverwrite = false-->

<%@ page import="webambulanze.TipoTurno" %>



<div class="fieldcontain ${hasErrors(bean: tipoTurnoInstance, field: 'croce', 'error')} required">
	<label for="croce">
		<g:message code="tipoTurno.croce.labelform" default="Croce" />
		<span class="required-indicator">*</span>
	</label>
	









<g:select id="croce" name="croce.id" from="${webambulanze.Croce.list()}" optionKey="id" required="" value="${tipoTurnoInstance?.croce?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tipoTurnoInstance, field: 'sigla', 'error')} required">
	<label for="sigla">
		<g:message code="tipoTurno.sigla.labelform" default="Sigla" />
		<span class="required-indicator">*</span>
	</label>
	









<g:textField name="sigla" required="" value="${tipoTurnoInstance?.sigla}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tipoTurnoInstance, field: 'descrizione', 'error')} ">
	<label for="descrizione">
		<g:message code="tipoTurno.descrizione.labelform" default="Descrizione" />
		
	</label>
	









<g:textField name="descrizione" value="${tipoTurnoInstance?.descrizione}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tipoTurnoInstance, field: 'ordine', 'error')} required">
	<label for="ordine">
		<g:message code="tipoTurno.ordine.labelform" default="Ordine" />
		<span class="required-indicator">*</span>
	</label>
	









<g:field name="ordine" type="number" value="${tipoTurnoInstance.ordine}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: tipoTurnoInstance, field: 'oraInizio', 'error')} required">
	<label for="oraInizio">
		<g:message code="tipoTurno.oraInizio.labelform" default="Ora Inizio" />
		<span class="required-indicator">*</span>
	</label>
	









<g:field name="oraInizio" type="number" value="${tipoTurnoInstance.oraInizio}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: tipoTurnoInstance, field: 'oraFine', 'error')} required">
	<label for="oraFine">
		<g:message code="tipoTurno.oraFine.labelform" default="Ora Fine" />
		<span class="required-indicator">*</span>
	</label>
	









<g:field name="oraFine" type="number" value="${tipoTurnoInstance.oraFine}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: tipoTurnoInstance, field: 'primo', 'error')} ">
	<label for="primo">
		<g:message code="tipoTurno.primo.labelform" default="Primo" />
		
	</label>
	









<g:checkBox name="primo" value="${tipoTurnoInstance?.primo}" />
</div>

<div class="fieldcontain ${hasErrors(bean: tipoTurnoInstance, field: 'fineGiornoSuccessivo', 'error')} ">
	<label for="fineGiornoSuccessivo">
		<g:message code="tipoTurno.fineGiornoSuccessivo.labelform" default="Fine Giorno Successivo" />
		
	</label>
	









<g:checkBox name="fineGiornoSuccessivo" value="${tipoTurnoInstance?.fineGiornoSuccessivo}" />
</div>

<div class="fieldcontain ${hasErrors(bean: tipoTurnoInstance, field: 'visibile', 'error')} ">
	<label for="visibile">
		<g:message code="tipoTurno.visibile.labelform" default="Visibile" />
		
	</label>
	









<g:checkBox name="visibile" value="${tipoTurnoInstance?.visibile}" />
</div>

<div class="fieldcontain ${hasErrors(bean: tipoTurnoInstance, field: 'orario', 'error')} ">
	<label for="orario">
		<g:message code="tipoTurno.orario.labelform" default="Orario" />
		
	</label>
	









<g:checkBox name="orario" value="${tipoTurnoInstance?.orario}" />
</div>

<div class="fieldcontain ${hasErrors(bean: tipoTurnoInstance, field: 'multiplo', 'error')} ">
	<label for="multiplo">
		<g:message code="tipoTurno.multiplo.labelform" default="Multiplo" />
		
	</label>
	









<g:checkBox name="multiplo" value="${tipoTurnoInstance?.multiplo}" />
</div>

<div class="fieldcontain ${hasErrors(bean: tipoTurnoInstance, field: 'funzioniObbligatorie', 'error')} required">
	<label for="funzioniObbligatorie">
		<g:message code="tipoTurno.funzioniObbligatorie.labelform" default="Funzioni Obbligatorie" />
		<span class="required-indicator">*</span>
	</label>
	









<g:field name="funzioniObbligatorie" type="number" value="${tipoTurnoInstance.funzioniObbligatorie}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: tipoTurnoInstance, field: 'funzione1', 'error')} ">
	<label for="funzione1">
		<g:message code="tipoTurno.funzione1.labelform" default="Funzione1" />
		
	</label>
	









<g:select id="funzione1" name="funzione1.id" from="${webambulanze.Funzione.list()}" optionKey="id" value="${tipoTurnoInstance?.funzione1?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tipoTurnoInstance, field: 'funzione2', 'error')} ">
	<label for="funzione2">
		<g:message code="tipoTurno.funzione2.labelform" default="Funzione2" />
		
	</label>
	









<g:select id="funzione2" name="funzione2.id" from="${webambulanze.Funzione.list()}" optionKey="id" value="${tipoTurnoInstance?.funzione2?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tipoTurnoInstance, field: 'funzione3', 'error')} ">
	<label for="funzione3">
		<g:message code="tipoTurno.funzione3.labelform" default="Funzione3" />
		
	</label>
	









<g:select id="funzione3" name="funzione3.id" from="${webambulanze.Funzione.list()}" optionKey="id" value="${tipoTurnoInstance?.funzione3?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: tipoTurnoInstance, field: 'funzione4', 'error')} ">
	<label for="funzione4">
		<g:message code="tipoTurno.funzione4.labelform" default="Funzione4" />
		
	</label>
	









<g:select id="funzione4" name="funzione4.id" from="${webambulanze.Funzione.list()}" optionKey="id" value="${tipoTurnoInstance?.funzione4?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<g:if test="${campiExtra}">
    <amb:extraSchedaForm rec="${tipoTurnoInstance}" campiExtra="${campiExtra}"></amb:extraSchedaForm>
</g:if>
