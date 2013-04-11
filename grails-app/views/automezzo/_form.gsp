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



<div class="fieldcontain ${hasErrors(bean: automezzoInstance, field: 'croce', 'error')} required">
	<label for="croce">
		<g:message code="automezzo.croce.labelform" default="Croce" />
		<span class="required-indicator">*</span>
	</label>
	









<g:select id="croce" name="croce.id" from="${webambulanze.Croce.list()}" optionKey="id" required="" value="${automezzoInstance?.croce?.id}" class="many-to-one"/>
</div>

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

<div class="fieldcontain ${hasErrors(bean: automezzoInstance, field: 'targa', 'error')} required">
	<label for="targa">
		<g:message code="automezzo.targa.labelform" default="Targa" />
		<span class="required-indicator">*</span>
	</label>
	









<g:textField name="targa" required="" value="${automezzoInstance?.targa}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: automezzoInstance, field: 'sigla', 'error')} ">
	<label for="sigla">
		<g:message code="automezzo.sigla.labelform" default="Sigla" />
		
	</label>
	









<g:textField name="sigla" value="${automezzoInstance?.sigla}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: automezzoInstance, field: 'descrizione', 'error')} ">
	<label for="descrizione">
		<g:message code="automezzo.descrizione.labelform" default="Descrizione" />
		
	</label>
	









<g:textField name="descrizione" value="${automezzoInstance?.descrizione}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: automezzoInstance, field: 'donazione', 'error')} ">
	<label for="donazione">
		<g:message code="automezzo.donazione.labelform" default="Donazione" />
		
	</label>
	









<g:textField name="donazione" value="${automezzoInstance?.donazione}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: automezzoInstance, field: 'contakilometri', 'error')} required">
	<label for="contakilometri">
		<g:message code="automezzo.contakilometri.labelform" default="Contakilometri" />
		<span class="required-indicator">*</span>
	</label>
	









<g:field name="contakilometri" type="number" value="${automezzoInstance.contakilometri}" required=""/>
</div>

<g:if test="${campiExtra}">
    <amb:extraSchedaForm rec="${automezzoInstance}" campiExtra="${campiExtra}"></amb:extraSchedaForm>
</g:if>
