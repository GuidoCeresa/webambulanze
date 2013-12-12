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
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'logo.label', default: 'Logo')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<amb:titoloPagina></amb:titoloPagina>
<a href="#show-logo" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                                  default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><g:link class="home" controller="Gen" action="home"><g:message code="ambulanze.home.label"/></g:link></li>
        <li><g:link class="list" action="list">
            <g:message code="logo.list.label"
                       default="Elenco logo"/>
        </g:link></li>

        <li><g:link class="create" action="create">
            <g:message code="logo.new.label"
                       default="Nuovo logo"/>
        </g:link></li>

        <li><g:link class="edit" action="edit" id="${logoInstance?.id}">
            <g:message code="logo.edit.label" default="Modifica logo"/>
        </g:link></li>
        <sec:ifNotLoggedIn>
            <li><g:link class="login" controller="login">Login</g:link></li>
        </sec:ifNotLoggedIn>
        <sec:ifLoggedIn>
            <li><g:link class="logout" controller="logout">Logout</g:link></li>
        </sec:ifLoggedIn>
    </ul>
</div>

<div id="show-logo" class="content scaffold-show" role="main">
    <h1><g:message code="logo.show.label" default="Mostra logo"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:if test="${flash.errors}">
        <div class="errors" role="status">${flash.errors}</div>
    </g:if>
    <g:if test="${flash.listaMessaggi}">
        <ul><g:each in="${flash.listaMessaggi}" var="messaggio"><li><div class="message">${messaggio}</div>
        </li></g:each></ul>
    </g:if>
    <g:if test="${flash.listaErrori}">
        <ul><g:each in="${flash.listaErrori}" var="errore"><li class="errors"><div>${errore}</div></li></g:each></ul>
    </g:if>
    <ol class="property-list logo">
        
        <li class="fieldcontain">
            <span id="croceLogo-label" class="property-label"><g:message
                    code="logo.croceLogo.labelform" default="Croce Logo"/></span>
            
            <span class="property-value" aria-labelledby="croceLogo-label"><g:link
                    controller="croce" action="show"
                    id="${logoInstance?.croceLogo?.id}">${logoInstance?.croceLogo?.encodeAsHTML()}</g:link></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="time-label" class="property-label"><g:message
                    code="logo.time.labelform" default="Time"/></span>
            
            <span class="property-value" aria-labelledby="time-label"><g:fieldValue bean="${logoInstance}"
                                                                                         field="time"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="utente-label" class="property-label"><g:message
                    code="logo.utente.labelform" default="Utente"/></span>
            
            <span class="property-value" aria-labelledby="utente-label"><g:link
                    controller="utente" action="show"
                    id="${logoInstance?.utente?.id}">${logoInstance?.utente?.encodeAsHTML()}</g:link></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="ruolo-label" class="property-label"><g:message
                    code="logo.ruolo.labelform" default="Ruolo"/></span>
            
            <span class="property-value" aria-labelledby="ruolo-label"><g:link
                    controller="ruolo" action="show"
                    id="${logoInstance?.ruolo?.id}">${logoInstance?.ruolo?.encodeAsHTML()}</g:link></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="evento-label" class="property-label"><g:message
                    code="logo.evento.labelform" default="Evento"/></span>
            
            <span class="property-value" aria-labelledby="evento-label"><g:fieldValue bean="${logoInstance}"
                                                                                         field="evento"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="livello-label" class="property-label"><g:message
                    code="logo.livello.labelform" default="Livello"/></span>
            
            <span class="property-value" aria-labelledby="livello-label"><g:fieldValue bean="${logoInstance}"
                                                                                         field="livello"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="milite-label" class="property-label"><g:message
                    code="logo.milite.labelform" default="Milite"/></span>
            
            <span class="property-value" aria-labelledby="milite-label"><g:link
                    controller="milite" action="show"
                    id="${logoInstance?.milite?.id}">${logoInstance?.milite?.encodeAsHTML()}</g:link></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="tipoTurno-label" class="property-label"><g:message
                    code="logo.tipoTurno.labelform" default="Tipo Turno"/></span>
            
            <span class="property-value" aria-labelledby="tipoTurno-label"><g:link
                    controller="tipoTurno" action="show"
                    id="${logoInstance?.tipoTurno?.id}">${logoInstance?.tipoTurno?.encodeAsHTML()}</g:link></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="turno-label" class="property-label"><g:message
                    code="logo.turno.labelform" default="Turno"/></span>
            
            <span class="property-value" aria-labelledby="turno-label"><g:link
                    controller="turno" action="show"
                    id="${logoInstance?.turno?.id}">${logoInstance?.turno?.encodeAsHTML()}</g:link></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="giorno-label" class="property-label"><g:message
                    code="logo.giorno.labelform" default="Giorno"/></span>
            
            <span class="property-value" aria-labelledby="giorno-label"><amb:formatDate
                    date="${logoInstance?.giorno}"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="dettaglio-label" class="property-label"><g:message
                    code="logo.dettaglio.labelform" default="Dettaglio"/></span>
            
            <span class="property-value" aria-labelledby="dettaglio-label"><g:fieldValue bean="${logoInstance}"
                                                                                         field="dettaglio"/></span>
            
        </li>
        
    </ol>

    <g:if test="${campiExtra}">
        <amb:extraScheda rec="${logoInstance}" campiExtra="${campiExtra}"></amb:extraScheda>
    </g:if>

    <g:form>
        <fieldset class="buttons">
            <g:hiddenField name="id" value="${logoInstance?.id}"/>
            <g:link class="edit" action="edit" id="${logoInstance?.id}">
                <g:message code="logo.edit.label"
                           default="Modifica logo"/>
            </g:link>
            <g:actionSubmit class="delete" action="delete"
                            value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                            onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
