<!--Created by Algos s.r.l.-->
<!--Date: mag 2012-->
<!--Questo file è stato installato dal plugin AlgosBase-->
<!--Tipicamente NON verrà più sovrascritto dalle successive release del plugin-->
<!--in quanto POTREBBE essere personalizzato in questa applicazione-->
<!--Se vuoi che le prossime release del plugin sovrascrivano questo file,-->
<!--perdendo tutte le modifiche effettuate qui,-->
<!--regola a true il flag di controllo flagOverwrite©-->
<!--flagOverwrite = false-->

<%@ page import="webambulanze.Milite" %>



<div class="fieldcontain ${hasErrors(bean: militeInstance, field: 'cognome', 'error')} required">
	<label for="cognome">
		<g:message code="milite.cognome.labelform" default="Cognome" />
		<span class="required-indicator">*</span>
	</label>
	









<g:textField name="cognome" required="" value="${militeInstance?.cognome}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: militeInstance, field: 'nome', 'error')} required">
	<label for="nome">
		<g:message code="milite.nome.labelform" default="Nome" />
		<span class="required-indicator">*</span>
	</label>
	









<g:textField name="nome" required="" value="${militeInstance?.nome}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: militeInstance, field: 'dataNascita', 'error')} ">
	<label for="dataNascita">
		<g:message code="milite.dataNascita.labelform" default="Data Nascita" />
		
	</label>
	









<g:datePicker name="dataNascita" precision="day"  value="${militeInstance?.dataNascita}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: militeInstance, field: 'telefonoCellulare', 'error')} ">
	<label for="telefonoCellulare">
		<g:message code="milite.telefonoCellulare.labelform" default="Telefono Cellulare" />
		
	</label>
	









<g:textField name="telefonoCellulare" value="${militeInstance?.telefonoCellulare}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: militeInstance, field: 'telefonoFisso', 'error')} ">
	<label for="telefonoFisso">
		<g:message code="milite.telefonoFisso.labelform" default="Telefono Fisso" />
		
	</label>
	









<g:textField name="telefonoFisso" value="${militeInstance?.telefonoFisso}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: militeInstance, field: 'email', 'error')} ">
	<label for="email">
		<g:message code="milite.email.labelform" default="Email" />
		
	</label>
	









<g:field type="email" name="email" value="${militeInstance?.email}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: militeInstance, field: 'scadenzaBLSD', 'error')} ">
	<label for="scadenzaBLSD">
		<g:message code="milite.scadenzaBLSD.labelform" default="Scadenza BLSD" />
		
	</label>
	









<g:datePicker name="scadenzaBLSD" precision="day"  value="${militeInstance?.scadenzaBLSD}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: militeInstance, field: 'scadenzaTrauma', 'error')} ">
	<label for="scadenzaTrauma">
		<g:message code="milite.scadenzaTrauma.labelform" default="Scadenza Trauma" />
		
	</label>
	









<g:datePicker name="scadenzaTrauma" precision="day"  value="${militeInstance?.scadenzaTrauma}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: militeInstance, field: 'scadenzaNonTrauma', 'error')} ">
	<label for="scadenzaNonTrauma">
		<g:message code="milite.scadenzaNonTrauma.labelform" default="Scadenza Non Trauma" />
		
	</label>
	









<g:datePicker name="scadenzaNonTrauma" precision="day"  value="${militeInstance?.scadenzaNonTrauma}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: militeInstance, field: 'note', 'error')} ">
	<label for="note">
		<g:message code="milite.note.labelform" default="Note" />
		
	</label>
	









<g:textArea name="note" cols="40" rows="5" value="${militeInstance?.note}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: militeInstance, field: 'dipendente', 'error')} ">
	<label for="dipendente">
		<g:message code="milite.dipendente.labelform" default="Dipendente" />
		
	</label>
	









<g:checkBox name="dipendente" value="${militeInstance?.dipendente}" />
</div>

<div class="fieldcontain ${hasErrors(bean: militeInstance, field: 'attivo', 'error')} ">
	<label for="attivo">
		<g:message code="milite.attivo.labelform" default="Attivo" />
		
	</label>
	









<g:checkBox name="attivo" value="${militeInstance?.attivo}" />
</div>

<div class="fieldcontain ${hasErrors(bean: militeInstance, field: 'oreExtra', 'error')} required">
	<label for="oreExtra">
		<g:message code="milite.oreExtra.labelform" default="Ore Extra" />
		<span class="required-indicator">*</span>
	</label>
	









<g:field name="oreExtra" type="number" value="${militeInstance.oreExtra}" required=""/>
</div>

<g:if test="${campiExtra}">
    <amb:extraSchedaForm rec="${militeInstance}" campiExtra="${campiExtra}"></amb:extraSchedaForm>
</g:if>
