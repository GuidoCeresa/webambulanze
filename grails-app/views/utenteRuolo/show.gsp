<!--Created by Algos s.r.l.-->
<!--Date: mag 2012-->
<!--Questo file è stato installato dal plugin AlgosBase-->
<!--Tipicamente NON verrà più sovrascritto dalle successive release del plugin-->
<!--in quanto POTREBBE essere personalizzato in questa applicazione-->
<!--Se vuoi che le prossime release del plugin sovrascrivano questo file,-->
<!--perdendo tutte le modifiche effettuate qui,-->
<!--regola a true il flag di controllo flagOverwrite©-->
<!--flagOverwrite = false-->


<%@ page import="webambulanze.UtenteRuolo" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'utenteRuolo.label', default: 'UtenteRuolo')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<amb:titoloPagina></amb:titoloPagina>
<a href="#show-utenteRuolo" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                                  default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><g:link class="home" controller="Gen" action="home"><g:message code="ambulanze.home.label"/></g:link></li>
        <li><g:link class="list" action="list">
            <g:message code="utenteRuolo.list.label"
                       default="Elenco utenteRuolo"/>
        </g:link></li>

        <li><g:link class="create" action="create">
            <g:message code="utenteRuolo.new.label"
                       default="Nuovo utenteRuolo"/>
        </g:link></li>

        <li><g:link class="edit" action="edit" id="${utenteRuoloInstance?.id}">
            <g:message code="utenteRuolo.edit.label" default="Modifica utenteRuolo"/>
        </g:link></li>
        <sec:ifNotLoggedIn>
            <li><g:link class="login" controller="login">Login</g:link></li>
        </sec:ifNotLoggedIn>
        <sec:ifLoggedIn>
            <li><g:link class="logout" controller="logout">Logout</g:link></li>
        </sec:ifLoggedIn>
    </ul>
</div>

<div id="show-utenteRuolo" class="content scaffold-show" role="main">
    <h1><g:message code="utenteRuolo.show.label" default="Mostra utenteRuolo"/></h1>
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
    <ol class="property-list utenteRuolo">
        
        <li class="fieldcontain">
            <span id="ruolo-label" class="property-label"><g:message
                    code="utenteRuolo.ruolo.labelform" default="Ruolo"/></span>
            
            <span class="property-value" aria-labelledby="ruolo-label"><g:link
                    controller="ruolo" action="show"
                    id="${utenteRuoloInstance?.ruolo?.id}">${utenteRuoloInstance?.ruolo?.encodeAsHTML()}</g:link></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="utente-label" class="property-label"><g:message
                    code="utenteRuolo.utente.labelform" default="Utente"/></span>
            
            <span class="property-value" aria-labelledby="utente-label"><g:link
                    controller="utente" action="show"
                    id="${utenteRuoloInstance?.utente?.id}">${utenteRuoloInstance?.utente?.encodeAsHTML()}</g:link></span>
            
        </li>
        
    </ol>

    <g:if test="${campiExtra}">
        <amb:extraScheda rec="${utenteRuoloInstance}" campiExtra="${campiExtra}"></amb:extraScheda>
    </g:if>

    <g:form>
        <fieldset class="buttons">
            <g:hiddenField name="id" value="${utenteRuoloInstance?.id}"/>
            <g:link class="edit" action="edit" id="${utenteRuoloInstance?.id}">
                <g:message code="utenteRuolo.edit.label"
                           default="Modifica utenteRuolo"/>
            </g:link>
            <g:actionSubmit class="delete" action="delete"
                            value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                            onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
