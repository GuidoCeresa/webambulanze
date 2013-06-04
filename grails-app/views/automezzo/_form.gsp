<!--Created by Algos s.r.l.-->
<!--Date: mag 2012-->
<!--Questo file è stato installato dal plugin AlgosBase-->
<!--Tipicamente NON verrà più sovrascritto dalle successive release del plugin-->
<!--in quanto POTREBBE essere personalizzato in questa applicazione-->
<!--Se vuoi che le prossime release del plugin sovrascrivano questo file,-->
<!--perdendo tutte le modifiche effettuate qui,-->
<!--regola a true il flag di controllo flagOverwrite©-->
<!--flagOverwrite = false-->

<%@ page import="webambulanze.Automezzo" %>



<div class="fieldcontain ${hasErrors(bean: automezzoInstance, field: 'tipo', 'error')} required">
	<label for="tipo">
		<g:message code="automezzo.tipo.labelform" default="Tipo" />
		<span class="required-indicator">*</span>
	</label>
	









<g:select name="tipo" from="${webambulanze.TipoAutomezzo?.values()}" keys="${webambulanze.TipoAutomezzo.values()*.name()}" required="" value="${automezzoInstance?.tipo?.name()}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: automezzoInstance, field: 'dataAcquisto', 'error')} ">
	<label for="dataAcquisto">
		<g:message code="automezzo.dataAcquisto.labelform" default="Data Acquisto" />
		
	</label>
	









<g:datePicker name="dataAcquisto" precision="day"  value="${automezzoInstance?.dataAcquisto}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: automezzoInstance, field: 'sigla', 'error')} ">
	<label for="sigla">
		<g:message code="automezzo.sigla.labelform" default="Sigla" />
		
	</label>
	









<g:textField name="sigla" value="${automezzoInstance?.sigla}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: automezzoInstance, field: 'targa', 'error')} required">
	<label for="targa">
		<g:message code="automezzo.targa.labelform" default="Targa" />
		<span class="required-indicator">*</span>
	</label>
	









<g:textField name="targa" required="" value="${automezzoInstance?.targa}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: automezzoInstance, field: 'descrizione', 'error')} ">
	<label for="descrizione">
		<g:message code="automezzo.descrizione.labelform" default="Descrizione" />
		
	</label>
	









<g:textArea name="descrizione" cols="40" rows="5" value="${automezzoInstance?.descrizione}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: automezzoInstance, field: 'donazione', 'error')} ">
	<label for="donazione">
		<g:message code="automezzo.donazione.labelform" default="Donazione" />
		
	</label>
	









<g:textArea name="donazione" cols="40" rows="5" value="${automezzoInstance?.donazione}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: automezzoInstance, field: 'chilometriTotaliPercorsi', 'error')} required">
	<label for="chilometriTotaliPercorsi">
		<g:message code="automezzo.chilometriTotaliPercorsi.labelform" default="Chilometri Totali Percorsi" />
		<span class="required-indicator">*</span>
	</label>
	









<g:field name="chilometriTotaliPercorsi" type="number" value="${automezzoInstance.chilometriTotaliPercorsi}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: automezzoInstance, field: 'numeroViaggiEffettuati', 'error')} required">
	<label for="numeroViaggiEffettuati">
		<g:message code="automezzo.numeroViaggiEffettuati.labelform" default="Numero Viaggi Effettuati" />
		<span class="required-indicator">*</span>
	</label>
	









<g:field name="numeroViaggiEffettuati" type="number" value="${automezzoInstance.numeroViaggiEffettuati}" required=""/>
</div>

<g:if test="${campiExtra}">
    <amb:extraSchedaForm rec="${automezzoInstance}" campiExtra="${campiExtra}"></amb:extraSchedaForm>
</g:if>
