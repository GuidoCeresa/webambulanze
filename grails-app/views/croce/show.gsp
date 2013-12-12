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
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'croce.label', default: 'Croce')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<amb:titoloPagina></amb:titoloPagina>
<a href="#show-croce" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                                  default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><g:link class="home" controller="Gen" action="home"><g:message code="ambulanze.home.label"/></g:link></li>
        <li><g:link class="list" action="list">
            <g:message code="croce.list.label"
                       default="Elenco croce"/>
        </g:link></li>

        <li><g:link class="create" action="create">
            <g:message code="croce.new.label"
                       default="Nuovo croce"/>
        </g:link></li>

        <li><g:link class="edit" action="edit" id="${croceInstance?.id}">
            <g:message code="croce.edit.label" default="Modifica croce"/>
        </g:link></li>
        <sec:ifNotLoggedIn>
            <li><g:link class="login" controller="login">Login</g:link></li>
        </sec:ifNotLoggedIn>
        <sec:ifLoggedIn>
            <li><g:link class="logout" controller="logout">Logout</g:link></li>
        </sec:ifLoggedIn>
    </ul>
</div>

<div id="show-croce" class="content scaffold-show" role="main">
    <h1><g:message code="croce.show.label" default="Mostra croce"/></h1>
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
    <ol class="property-list croce">
        
        <li class="fieldcontain">
            <span id="sigla-label" class="property-label"><g:message
                    code="croce.sigla.labelform" default="Sigla"/></span>
            
            <span class="property-value" aria-labelledby="sigla-label"><g:fieldValue bean="${croceInstance}"
                                                                                         field="sigla"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="organizzazione-label" class="property-label"><g:message
                    code="croce.organizzazione.labelform" default="Organizzazione"/></span>
            
            <span class="property-value" aria-labelledby="organizzazione-label"><g:fieldValue bean="${croceInstance}"
                                                                                         field="organizzazione"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="descrizione-label" class="property-label"><g:message
                    code="croce.descrizione.labelform" default="Descrizione"/></span>
            
            <span class="property-value" aria-labelledby="descrizione-label"><g:fieldValue bean="${croceInstance}"
                                                                                         field="descrizione"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="presidente-label" class="property-label"><g:message
                    code="croce.presidente.labelform" default="Presidente"/></span>
            
            <span class="property-value" aria-labelledby="presidente-label"><g:fieldValue bean="${croceInstance}"
                                                                                         field="presidente"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="riferimento-label" class="property-label"><g:message
                    code="croce.riferimento.labelform" default="Riferimento"/></span>
            
            <span class="property-value" aria-labelledby="riferimento-label"><g:fieldValue bean="${croceInstance}"
                                                                                         field="riferimento"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="indirizzo-label" class="property-label"><g:message
                    code="croce.indirizzo.labelform" default="Indirizzo"/></span>
            
            <span class="property-value" aria-labelledby="indirizzo-label"><g:fieldValue bean="${croceInstance}"
                                                                                         field="indirizzo"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="telefono-label" class="property-label"><g:message
                    code="croce.telefono.labelform" default="Telefono"/></span>
            
            <span class="property-value" aria-labelledby="telefono-label"><g:fieldValue bean="${croceInstance}"
                                                                                         field="telefono"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="email-label" class="property-label"><g:message
                    code="croce.email.labelform" default="Email"/></span>
            
            <span class="property-value" aria-labelledby="email-label"><g:fieldValue bean="${croceInstance}"
                                                                                         field="email"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="settings-label" class="property-label"><g:message
                    code="croce.settings.labelform" default="Settings"/></span>
            
            <span class="property-value" aria-labelledby="settings-label"><g:link
                    controller="settings" action="show"
                    id="${croceInstance?.settings?.id}">${croceInstance?.settings?.encodeAsHTML()}</g:link></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="custode-label" class="property-label"><g:message
                    code="croce.custode.labelform" default="Custode"/></span>
            
            <span class="property-value" aria-labelledby="custode-label"><g:fieldValue bean="${croceInstance}"
                                                                                         field="custode"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="amministratori-label" class="property-label"><g:message
                    code="croce.amministratori.labelform" default="Amministratori"/></span>
            
            <span class="property-value" aria-labelledby="amministratori-label"><g:fieldValue bean="${croceInstance}"
                                                                                         field="amministratori"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="note-label" class="property-label"><g:message
                    code="croce.note.labelform" default="Note"/></span>
            
            <span class="property-value" aria-labelledby="note-label"><g:fieldValue bean="${croceInstance}"
                                                                                         field="note"/></span>
            
        </li>
        
    </ol>

    <g:if test="${campiExtra}">
        <amb:extraScheda rec="${croceInstance}" campiExtra="${campiExtra}"></amb:extraScheda>
    </g:if>

    <g:form>
        <fieldset class="buttons">
            <g:hiddenField name="id" value="${croceInstance?.id}"/>
            <g:link class="edit" action="edit" id="${croceInstance?.id}">
                <g:message code="croce.edit.label"
                           default="Modifica croce"/>
            </g:link>
            <g:actionSubmit class="delete" action="delete"
                            value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                            onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
