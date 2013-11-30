<!--Created by Algos s.r.l.-->
<!--Date: mag 2012-->
<!--Questo file è stato installato dal plugin AlgosBase-->
<!--Tipicamente NON verrà più sovrascritto dalle successive release del plugin-->
<!--in quanto POTREBBE essere personalizzato in questa applicazione-->
<!--Se vuoi che le prossime release del plugin sovrascrivano questo file,-->
<!--perdendo tutte le modifiche effettuate qui,-->
<!--regola a true il flag di controllo flagOverwrite©-->
<!--flagOverwrite = false-->

<%@ page import="webambulanze.Militefunzione" %>



<div class="fieldcontain ${hasErrors(bean: militefunzioneInstance, field: 'milite', 'error')} required">
	<label for="milite">
		<g:message code="militefunzione.milite.labelform" default="Milite" />
		<span class="required-indicator">*</span>
	</label>





    <g:if test="${listaMiliti}">
        <g:select id="milite" name="milite.id" from="${listaMiliti}" optionKey="id" required="" value="${militefunzioneInstance?.milite?.id}" class="many-to-one"/>
    </g:if>
    <g:else>
        <g:select id="milite" name="milite.id" from="${webambulanze.Milite.list()}" optionKey="id" required="" value="${militefunzioneInstance?.milite?.id}" class="many-to-one"/>
    </g:else>



</div>

<div class="fieldcontain ${hasErrors(bean: militefunzioneInstance, field: 'funzione', 'error')} required">
	<label for="funzione">
		<g:message code="militefunzione.funzione.labelform" default="Funzione" />
		<span class="required-indicator">*</span>
	</label>




    <g:if test="${listaFunzioni}">
        <g:select id="funzione" name="funzione.id" from="${listaFunzioni}" optionKey="id" required="" value="${militefunzioneInstance?.funzione?.id}" class="many-to-one"/>
    </g:if>
    <g:else>
        <g:select id="funzione" name="funzione.id" from="${webambulanze.Funzione.list()}" optionKey="id" required="" value="${militefunzioneInstance?.funzione?.id}" class="many-to-one"/>
    </g:else>






</div>

<g:if test="${campiExtra}">
    <amb:extraSchedaForm rec="${militefunzioneInstance}" campiExtra="${campiExtra}"></amb:extraSchedaForm>
</g:if>
