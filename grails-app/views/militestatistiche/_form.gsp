<!--Created by Algos s.r.l.-->
<!--Date: mag 2012-->
<!--Questo file è stato installato dal plugin AlgosBase-->
<!--Tipicamente NON verrà più sovrascritto dalle successive release del plugin-->
<!--in quanto POTREBBE essere personalizzato in questa applicazione-->
<!--Se vuoi che le prossime release del plugin sovrascrivano questo file,-->
<!--perdendo tutte le modifiche effettuate qui,-->
<!--regola a true il flag di controllo flagOverwrite©-->
<!--flagOverwrite = false-->

<%@ page import="webambulanze.Militestatistiche" %>



<div class="fieldcontain ${hasErrors(bean: militestatisticheInstance, field: 'croce', 'error')} required">
	<label for="croce">
		<g:message code="militestatistiche.croce.labelform" default="Croce" />
		<span class="required-indicator">*</span>
	</label>
	









<g:select id="croce" name="croce.id" from="${webambulanze.Croce.list()}" optionKey="id" required="" value="${militestatisticheInstance?.croce?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: militestatisticheInstance, field: 'milite', 'error')} required">
	<label for="milite">
		<g:message code="militestatistiche.milite.labelform" default="Milite" />
		<span class="required-indicator">*</span>
	</label>
	









<g:select id="milite" name="milite.id" from="${webambulanze.Milite.list()}" optionKey="id" required="" value="${militestatisticheInstance?.milite?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: militestatisticheInstance, field: 'status', 'error')} ">
	<label for="status">
		<g:message code="militestatistiche.status.labelform" default="Status" />
		
	</label>
	









<g:textField name="status" value="${militestatisticheInstance?.status}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: militestatisticheInstance, field: 'turni', 'error')} required">
	<label for="turni">
		<g:message code="militestatistiche.turni.labelform" default="Turni" />
		<span class="required-indicator">*</span>
	</label>
	









<g:field name="turni" type="number" value="${militestatisticheInstance.turni}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: militestatisticheInstance, field: 'ore', 'error')} required">
	<label for="ore">
		<g:message code="militestatistiche.ore.labelform" default="Ore" />
		<span class="required-indicator">*</span>
	</label>
	









<g:field name="ore" type="number" value="${militestatisticheInstance.ore}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: militestatisticheInstance, field: 'funz1', 'error')} required">
	<label for="funz1">
		<g:message code="militestatistiche.funz1.labelform" default="Funz1" />
		<span class="required-indicator">*</span>
	</label>
	









<g:field name="funz1" type="number" value="${militestatisticheInstance.funz1}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: militestatisticheInstance, field: 'funz2', 'error')} required">
	<label for="funz2">
		<g:message code="militestatistiche.funz2.labelform" default="Funz2" />
		<span class="required-indicator">*</span>
	</label>
	









<g:field name="funz2" type="number" value="${militestatisticheInstance.funz2}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: militestatisticheInstance, field: 'funz3', 'error')} required">
	<label for="funz3">
		<g:message code="militestatistiche.funz3.labelform" default="Funz3" />
		<span class="required-indicator">*</span>
	</label>
	









<g:field name="funz3" type="number" value="${militestatisticheInstance.funz3}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: militestatisticheInstance, field: 'funz4', 'error')} required">
	<label for="funz4">
		<g:message code="militestatistiche.funz4.labelform" default="Funz4" />
		<span class="required-indicator">*</span>
	</label>
	









<g:field name="funz4" type="number" value="${militestatisticheInstance.funz4}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: militestatisticheInstance, field: 'funz5', 'error')} required">
	<label for="funz5">
		<g:message code="militestatistiche.funz5.labelform" default="Funz5" />
		<span class="required-indicator">*</span>
	</label>
	









<g:field name="funz5" type="number" value="${militestatisticheInstance.funz5}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: militestatisticheInstance, field: 'funz6', 'error')} required">
	<label for="funz6">
		<g:message code="militestatistiche.funz6.labelform" default="Funz6" />
		<span class="required-indicator">*</span>
	</label>
	









<g:field name="funz6" type="number" value="${militestatisticheInstance.funz6}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: militestatisticheInstance, field: 'funz7', 'error')} required">
	<label for="funz7">
		<g:message code="militestatistiche.funz7.labelform" default="Funz7" />
		<span class="required-indicator">*</span>
	</label>
	









<g:field name="funz7" type="number" value="${militestatisticheInstance.funz7}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: militestatisticheInstance, field: 'funz8', 'error')} required">
	<label for="funz8">
		<g:message code="militestatistiche.funz8.labelform" default="Funz8" />
		<span class="required-indicator">*</span>
	</label>
	









<g:field name="funz8" type="number" value="${militestatisticheInstance.funz8}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: militestatisticheInstance, field: 'funz9', 'error')} required">
	<label for="funz9">
		<g:message code="militestatistiche.funz9.labelform" default="Funz9" />
		<span class="required-indicator">*</span>
	</label>
	









<g:field name="funz9" type="number" value="${militestatisticheInstance.funz9}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: militestatisticheInstance, field: 'funz10', 'error')} required">
	<label for="funz10">
		<g:message code="militestatistiche.funz10.labelform" default="Funz10" />
		<span class="required-indicator">*</span>
	</label>
	









<g:field name="funz10" type="number" value="${militestatisticheInstance.funz10}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: militestatisticheInstance, field: 'funz11', 'error')} required">
	<label for="funz11">
		<g:message code="militestatistiche.funz11.labelform" default="Funz11" />
		<span class="required-indicator">*</span>
	</label>
	









<g:field name="funz11" type="number" value="${militestatisticheInstance.funz11}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: militestatisticheInstance, field: 'funz12', 'error')} required">
	<label for="funz12">
		<g:message code="militestatistiche.funz12.labelform" default="Funz12" />
		<span class="required-indicator">*</span>
	</label>
	









<g:field name="funz12" type="number" value="${militestatisticheInstance.funz12}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: militestatisticheInstance, field: 'funz13', 'error')} required">
	<label for="funz13">
		<g:message code="militestatistiche.funz13.labelform" default="Funz13" />
		<span class="required-indicator">*</span>
	</label>
	









<g:field name="funz13" type="number" value="${militestatisticheInstance.funz13}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: militestatisticheInstance, field: 'funz14', 'error')} required">
	<label for="funz14">
		<g:message code="militestatistiche.funz14.labelform" default="Funz14" />
		<span class="required-indicator">*</span>
	</label>
	









<g:field name="funz14" type="number" value="${militestatisticheInstance.funz14}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: militestatisticheInstance, field: 'funz15', 'error')} required">
	<label for="funz15">
		<g:message code="militestatistiche.funz15.labelform" default="Funz15" />
		<span class="required-indicator">*</span>
	</label>
	









<g:field name="funz15" type="number" value="${militestatisticheInstance.funz15}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: militestatisticheInstance, field: 'funz16', 'error')} required">
	<label for="funz16">
		<g:message code="militestatistiche.funz16.labelform" default="Funz16" />
		<span class="required-indicator">*</span>
	</label>
	









<g:field name="funz16" type="number" value="${militestatisticheInstance.funz16}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: militestatisticheInstance, field: 'funz17', 'error')} required">
	<label for="funz17">
		<g:message code="militestatistiche.funz17.labelform" default="Funz17" />
		<span class="required-indicator">*</span>
	</label>
	









<g:field name="funz17" type="number" value="${militestatisticheInstance.funz17}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: militestatisticheInstance, field: 'funz18', 'error')} required">
	<label for="funz18">
		<g:message code="militestatistiche.funz18.labelform" default="Funz18" />
		<span class="required-indicator">*</span>
	</label>
	









<g:field name="funz18" type="number" value="${militestatisticheInstance.funz18}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: militestatisticheInstance, field: 'funz19', 'error')} required">
	<label for="funz19">
		<g:message code="militestatistiche.funz19.labelform" default="Funz19" />
		<span class="required-indicator">*</span>
	</label>
	









<g:field name="funz19" type="number" value="${militestatisticheInstance.funz19}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: militestatisticheInstance, field: 'funz20', 'error')} required">
	<label for="funz20">
		<g:message code="militestatistiche.funz20.labelform" default="Funz20" />
		<span class="required-indicator">*</span>
	</label>
	









<g:field name="funz20" type="number" value="${militestatisticheInstance.funz20}" required=""/>
</div>

<g:if test="${campiExtra}">
    <amb:extraSchedaForm rec="${militestatisticheInstance}" campiExtra="${campiExtra}"></amb:extraSchedaForm>
</g:if>
