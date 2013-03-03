<!--Created by Algos s.r.l.-->
<!--Date: mag 2012-->
<!--Questo file è stato installato dal plugin AlgosBase-->
<!--Tipicamente NON verrà più sovrascritto dalle successive release del plugin-->
<!--in quanto POTREBBE essere personalizzato in questa applicazione-->
<!--Se vuoi che le prossime release del plugin sovrascrivano questo file,-->
<!--perdendo tutte le modifiche effettuate qui,-->
<!--regola a true il flag di controllo flagOverwrite©-->
<!--flagOverwrite = false-->

<%@ page import="webambulanze.Turno" %>



<div class="fieldcontain ${hasErrors(bean: turnoInstance, field: 'tipoTurno', 'error')} required">
	<label for="tipoTurno">
		<g:message code="turno.tipoTurno.labelform" default="Tipo Turno" />
		<span class="required-indicator">*</span>
	</label>
	









<g:select id="tipoTurno" name="tipoTurno.id" from="${webambulanze.TipoTurno.list()}" optionKey="id" required="" value="${turnoInstance?.tipoTurno?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: turnoInstance, field: 'giorno', 'error')} required">
	<label for="giorno">
		<g:message code="turno.giorno.labelform" default="Giorno" />
		<span class="required-indicator">*</span>
	</label>
	









<g:datePicker name="giorno" precision="day"  value="${turnoInstance?.giorno}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: turnoInstance, field: 'inizio', 'error')} required">
	<label for="inizio">
		<g:message code="turno.inizio.labelform" default="Inizio" />
		<span class="required-indicator">*</span>
	</label>
	









<g:datePicker name="inizio" precision="day"  value="${turnoInstance?.inizio}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: turnoInstance, field: 'fine', 'error')} required">
	<label for="fine">
		<g:message code="turno.fine.labelform" default="Fine" />
		<span class="required-indicator">*</span>
	</label>
	









<g:datePicker name="fine" precision="day"  value="${turnoInstance?.fine}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: turnoInstance, field: 'funzione1', 'error')} ">
	<label for="funzione1">
		<g:message code="turno.funzione1.labelform" default="Funzione1" />
		
	</label>
	









<g:select id="funzione1" name="funzione1.id" from="${webambulanze.Funzione.list()}" optionKey="id" value="${turnoInstance?.funzione1?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: turnoInstance, field: 'funzione2', 'error')} ">
	<label for="funzione2">
		<g:message code="turno.funzione2.labelform" default="Funzione2" />
		
	</label>
	









<g:select id="funzione2" name="funzione2.id" from="${webambulanze.Funzione.list()}" optionKey="id" value="${turnoInstance?.funzione2?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: turnoInstance, field: 'funzione3', 'error')} ">
	<label for="funzione3">
		<g:message code="turno.funzione3.labelform" default="Funzione3" />
		
	</label>
	









<g:select id="funzione3" name="funzione3.id" from="${webambulanze.Funzione.list()}" optionKey="id" value="${turnoInstance?.funzione3?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: turnoInstance, field: 'funzione4', 'error')} ">
	<label for="funzione4">
		<g:message code="turno.funzione4.labelform" default="Funzione4" />
		
	</label>
	









<g:select id="funzione4" name="funzione4.id" from="${webambulanze.Funzione.list()}" optionKey="id" value="${turnoInstance?.funzione4?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: turnoInstance, field: 'militeFunzione1', 'error')} ">
	<label for="militeFunzione1">
		<g:message code="turno.militeFunzione1.labelform" default="Milite Funzione1" />
		
	</label>
	









<g:select id="militeFunzione1" name="militeFunzione1.id" from="${webambulanze.Milite.list()}" optionKey="id" value="${turnoInstance?.militeFunzione1?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: turnoInstance, field: 'militeFunzione2', 'error')} ">
	<label for="militeFunzione2">
		<g:message code="turno.militeFunzione2.labelform" default="Milite Funzione2" />
		
	</label>
	









<g:select id="militeFunzione2" name="militeFunzione2.id" from="${webambulanze.Milite.list()}" optionKey="id" value="${turnoInstance?.militeFunzione2?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: turnoInstance, field: 'militeFunzione3', 'error')} ">
	<label for="militeFunzione3">
		<g:message code="turno.militeFunzione3.labelform" default="Milite Funzione3" />
		
	</label>
	









<g:select id="militeFunzione3" name="militeFunzione3.id" from="${webambulanze.Milite.list()}" optionKey="id" value="${turnoInstance?.militeFunzione3?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: turnoInstance, field: 'militeFunzione4', 'error')} ">
	<label for="militeFunzione4">
		<g:message code="turno.militeFunzione4.labelform" default="Milite Funzione4" />
		
	</label>
	









<g:select id="militeFunzione4" name="militeFunzione4.id" from="${webambulanze.Milite.list()}" optionKey="id" value="${turnoInstance?.militeFunzione4?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: turnoInstance, field: 'modificaFunzione1', 'error')} ">
	<label for="modificaFunzione1">
		<g:message code="turno.modificaFunzione1.labelform" default="Modifica Funzione1" />
		
	</label>
	










</div>

<div class="fieldcontain ${hasErrors(bean: turnoInstance, field: 'modificaFunzione2', 'error')} ">
	<label for="modificaFunzione2">
		<g:message code="turno.modificaFunzione2.labelform" default="Modifica Funzione2" />
		
	</label>
	










</div>

<div class="fieldcontain ${hasErrors(bean: turnoInstance, field: 'modificaFunzione3', 'error')} ">
	<label for="modificaFunzione3">
		<g:message code="turno.modificaFunzione3.labelform" default="Modifica Funzione3" />
		
	</label>
	










</div>

<div class="fieldcontain ${hasErrors(bean: turnoInstance, field: 'modificaFunzione4', 'error')} ">
	<label for="modificaFunzione4">
		<g:message code="turno.modificaFunzione4.labelform" default="Modifica Funzione4" />
		
	</label>
	










</div>

<div class="fieldcontain ${hasErrors(bean: turnoInstance, field: 'oreMilite1', 'error')} required">
	<label for="oreMilite1">
		<g:message code="turno.oreMilite1.labelform" default="Ore Milite1" />
		<span class="required-indicator">*</span>
	</label>
	









<g:field name="oreMilite1" type="number" value="${turnoInstance.oreMilite1}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: turnoInstance, field: 'oreMilite2', 'error')} required">
	<label for="oreMilite2">
		<g:message code="turno.oreMilite2.labelform" default="Ore Milite2" />
		<span class="required-indicator">*</span>
	</label>
	









<g:field name="oreMilite2" type="number" value="${turnoInstance.oreMilite2}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: turnoInstance, field: 'oreMilite3', 'error')} required">
	<label for="oreMilite3">
		<g:message code="turno.oreMilite3.labelform" default="Ore Milite3" />
		<span class="required-indicator">*</span>
	</label>
	









<g:field name="oreMilite3" type="number" value="${turnoInstance.oreMilite3}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: turnoInstance, field: 'oreMilite4', 'error')} required">
	<label for="oreMilite4">
		<g:message code="turno.oreMilite4.labelform" default="Ore Milite4" />
		<span class="required-indicator">*</span>
	</label>
	









<g:field name="oreMilite4" type="number" value="${turnoInstance.oreMilite4}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: turnoInstance, field: 'problemiFunzione1', 'error')} ">
	<label for="problemiFunzione1">
		<g:message code="turno.problemiFunzione1.labelform" default="Problemi Funzione1" />
		
	</label>
	









<g:checkBox name="problemiFunzione1" value="${turnoInstance?.problemiFunzione1}" />
</div>

<div class="fieldcontain ${hasErrors(bean: turnoInstance, field: 'problemiFunzione2', 'error')} ">
	<label for="problemiFunzione2">
		<g:message code="turno.problemiFunzione2.labelform" default="Problemi Funzione2" />
		
	</label>
	









<g:checkBox name="problemiFunzione2" value="${turnoInstance?.problemiFunzione2}" />
</div>

<div class="fieldcontain ${hasErrors(bean: turnoInstance, field: 'problemiFunzione3', 'error')} ">
	<label for="problemiFunzione3">
		<g:message code="turno.problemiFunzione3.labelform" default="Problemi Funzione3" />
		
	</label>
	









<g:checkBox name="problemiFunzione3" value="${turnoInstance?.problemiFunzione3}" />
</div>

<div class="fieldcontain ${hasErrors(bean: turnoInstance, field: 'problemiFunzione4', 'error')} ">
	<label for="problemiFunzione4">
		<g:message code="turno.problemiFunzione4.labelform" default="Problemi Funzione4" />
		
	</label>
	









<g:checkBox name="problemiFunzione4" value="${turnoInstance?.problemiFunzione4}" />
</div>

<div class="fieldcontain ${hasErrors(bean: turnoInstance, field: 'titoloExtra', 'error')} ">
	<label for="titoloExtra">
		<g:message code="turno.titoloExtra.labelform" default="Titolo Extra" />
		
	</label>
	









<g:textField name="titoloExtra" value="${turnoInstance?.titoloExtra}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: turnoInstance, field: 'localitàExtra', 'error')} ">
	<label for="localitàExtra">
		<g:message code="turno.localitàExtra.labelform" default="Località Extra" />
		
	</label>
	









<g:textField name="localitàExtra" value="${turnoInstance?.localitàExtra}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: turnoInstance, field: 'note', 'error')} ">
	<label for="note">
		<g:message code="turno.note.labelform" default="Note" />
		
	</label>
	









<g:textArea name="note" cols="40" rows="5" value="${turnoInstance?.note}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: turnoInstance, field: 'assegnato', 'error')} ">
	<label for="assegnato">
		<g:message code="turno.assegnato.labelform" default="Assegnato" />
		
	</label>
	









<g:checkBox name="assegnato" value="${turnoInstance?.assegnato}" />
</div>

<g:if test="${campiExtra}">
    <amb:extraSchedaForm rec="${turnoInstance}" campiExtra="${campiExtra}"></amb:extraSchedaForm>
</g:if>
