<!--Created by Algos s.r.l.-->
<!--Date: mag 2012-->
<!--Questo file è stato installato dal plugin AlgosBase-->
<!--Tipicamente NON verrà più sovrascritto dalle successive release del plugin-->
<!--in quanto POTREBBE essere personalizzato in questa applicazione-->
<!--Se vuoi che le prossime release del plugin sovrascrivano questo file,-->
<!--perdendo tutte le modifiche effettuate qui,-->
<!--regola a true il flag di controllo flagOverwrite©-->
<!--flagOverwrite = false-->

<%@ page import="webambulanze.Croce" %>



<div class="fieldcontain ${hasErrors(bean: croceInstance, field: 'sigla', 'error')} required">
	<label for="sigla">
		<g:message code="croce.sigla.labelform" default="Sigla" />
		<span class="required-indicator">*</span>
	</label>
	









<g:textField name="sigla" required="" value="${croceInstance?.sigla}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: croceInstance, field: 'organizzazione', 'error')} ">
	<label for="organizzazione">
		<g:message code="croce.organizzazione.labelform" default="Organizzazione" />
		
	</label>
	









<g:select name="organizzazione" from="${webambulanze.Organizzazione?.values()}" keys="${webambulanze.Organizzazione.values()*.name()}" value="${croceInstance?.organizzazione?.name()}" noSelection="['': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: croceInstance, field: 'descrizione', 'error')} required">
	<label for="descrizione">
		<g:message code="croce.descrizione.labelform" default="Descrizione" />
		<span class="required-indicator">*</span>
	</label>
	









<g:textField name="descrizione" required="" value="${croceInstance?.descrizione}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: croceInstance, field: 'presidente', 'error')} ">
	<label for="presidente">
		<g:message code="croce.presidente.labelform" default="Presidente" />
		
	</label>
	









<g:textField name="presidente" value="${croceInstance?.presidente}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: croceInstance, field: 'riferimento', 'error')} ">
	<label for="riferimento">
		<g:message code="croce.riferimento.labelform" default="Riferimento" />
		
	</label>
	









<g:textField name="riferimento" value="${croceInstance?.riferimento}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: croceInstance, field: 'indirizzo', 'error')} ">
	<label for="indirizzo">
		<g:message code="croce.indirizzo.labelform" default="Indirizzo" />
		
	</label>
	









<g:textField name="indirizzo" value="${croceInstance?.indirizzo}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: croceInstance, field: 'telefono', 'error')} ">
	<label for="telefono">
		<g:message code="croce.telefono.labelform" default="Telefono" />
		
	</label>
	









<g:textField name="telefono" value="${croceInstance?.telefono}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: croceInstance, field: 'email', 'error')} ">
	<label for="email">
		<g:message code="croce.email.labelform" default="Email" />
		
	</label>
	









<g:field type="email" name="email" value="${croceInstance?.email}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: croceInstance, field: 'custode', 'error')} ">
	<label for="custode">
		<g:message code="croce.custode.labelform" default="Custode" />
		
	</label>
	









<g:textField name="custode" value="${croceInstance?.custode}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: croceInstance, field: 'amministratori', 'error')} ">
	<label for="amministratori">
		<g:message code="croce.amministratori.labelform" default="Amministratori" />
		
	</label>
	









<g:textArea name="amministratori" cols="40" rows="5" value="${croceInstance?.amministratori}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: croceInstance, field: 'note', 'error')} ">
	<label for="note">
		<g:message code="croce.note.labelform" default="Note" />
		
	</label>
	









<g:textArea name="note" cols="40" rows="5" value="${croceInstance?.note}"/>
</div>

<g:if test="${campiExtra}">
    <amb:extraSchedaForm rec="${croceInstance}" campiExtra="${campiExtra}"></amb:extraSchedaForm>
</g:if>
