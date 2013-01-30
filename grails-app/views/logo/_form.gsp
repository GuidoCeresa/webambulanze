<!--Created by Algos s.r.l.-->
<!--Date: mag 2012-->
<!--Questo file è stato installato dal plugin AlgosBase-->
<!--Tipicamente NON verrà più sovrascritto dalle successive release del plugin-->
<!--in quanto POTREBBE essere personalizzato in questa applicazione-->
<!--Se vuoi che le prossime release del plugin sovrascrivano questo file,-->
<!--perdendo tutte le modifiche effettuate qui,-->
<!--regola a true il flag di controllo flagOverwrite©-->
<!--flagOverwrite = false-->

<%@ page import="webambulanze.Logo" %>



<div class="fieldcontain ${hasErrors(bean: logoInstance, field: 'croceLogo', 'error')} required">
	<label for="croceLogo">
		<g:message code="logo.croceLogo.labelform" default="Croce Logo" />
		<span class="required-indicator">*</span>
	</label>
	









<g:select id="croceLogo" name="croceLogo.id" from="${webambulanze.Croce.list()}" optionKey="id" required="" value="${logoInstance?.croceLogo?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: logoInstance, field: 'time', 'error')} ">
	<label for="time">
		<g:message code="logo.time.labelform" default="Time" />
		
	</label>
	










</div>

<div class="fieldcontain ${hasErrors(bean: logoInstance, field: 'utente', 'error')} ">
	<label for="utente">
		<g:message code="logo.utente.labelform" default="Utente" />
		
	</label>
	









<g:select id="utente" name="utente.id" from="${webambulanze.Utente.list()}" optionKey="id" value="${logoInstance?.utente?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: logoInstance, field: 'ruolo', 'error')} ">
	<label for="ruolo">
		<g:message code="logo.ruolo.labelform" default="Ruolo" />
		
	</label>
	









<g:select id="ruolo" name="ruolo.id" from="${webambulanze.Ruolo.list()}" optionKey="id" value="${logoInstance?.ruolo?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: logoInstance, field: 'evento', 'error')} required">
	<label for="evento">
		<g:message code="logo.evento.labelform" default="Evento" />
		<span class="required-indicator">*</span>
	</label>
	









<g:select name="evento" from="${webambulanze.Evento?.values()}" keys="${webambulanze.Evento.values()*.name()}" required="" value="${logoInstance?.evento?.name()}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: logoInstance, field: 'livello', 'error')} required">
	<label for="livello">
		<g:message code="logo.livello.labelform" default="Livello" />
		<span class="required-indicator">*</span>
	</label>
	









<g:select name="livello" from="${webambulanze.Livello?.values()}" keys="${webambulanze.Livello.values()*.name()}" required="" value="${logoInstance?.livello?.name()}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: logoInstance, field: 'milite', 'error')} ">
	<label for="milite">
		<g:message code="logo.milite.labelform" default="Milite" />
		
	</label>
	









<g:select id="milite" name="milite.id" from="${webambulanze.Milite.list()}" optionKey="id" value="${logoInstance?.milite?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: logoInstance, field: 'tipoTurno', 'error')} ">
	<label for="tipoTurno">
		<g:message code="logo.tipoTurno.labelform" default="Tipo Turno" />
		
	</label>
	









<g:select id="tipoTurno" name="tipoTurno.id" from="${webambulanze.TipoTurno.list()}" optionKey="id" value="${logoInstance?.tipoTurno?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: logoInstance, field: 'turno', 'error')} ">
	<label for="turno">
		<g:message code="logo.turno.labelform" default="Turno" />
		
	</label>
	









<g:select id="turno" name="turno.id" from="${webambulanze.Turno.list()}" optionKey="id" value="${logoInstance?.turno?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: logoInstance, field: 'giorno', 'error')} ">
	<label for="giorno">
		<g:message code="logo.giorno.labelform" default="Giorno" />
		
	</label>
	









<g:datePicker name="giorno" precision="day"  value="${logoInstance?.giorno}" default="none" noSelection="['': '']" />
</div>

<div class="fieldcontain ${hasErrors(bean: logoInstance, field: 'dettaglio', 'error')} ">
	<label for="dettaglio">
		<g:message code="logo.dettaglio.labelform" default="Dettaglio" />
		
	</label>
	









<g:textField name="dettaglio" value="${logoInstance?.dettaglio}"/>
</div>

<g:if test="${campiExtra}">
    <amb:extraSchedaForm rec="${logoInstance}" campiExtra="${campiExtra}"></amb:extraSchedaForm>
</g:if>
