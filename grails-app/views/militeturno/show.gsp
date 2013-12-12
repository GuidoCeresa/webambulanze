<!--Created by Algos s.r.l.-->
<!--Date: mag 2012-->
<!--Questo file è stato installato dal plugin AlgosBase-->
<!--Tipicamente NON verrà più sovrascritto dalle successive release del plugin-->
<!--in quanto POTREBBE essere personalizzato in questa applicazione-->
<!--Se vuoi che le prossime release del plugin sovrascrivano questo file,-->
<!--perdendo tutte le modifiche effettuate qui,-->
<!--regola a true il flag di controllo flagOverwrite©-->
<!--flagOverwrite = false-->


<%@ page import="webambulanze.Militeturno" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'militeturno.label', default: 'Militeturno')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<amb:titoloPagina></amb:titoloPagina>
<a href="#show-militeturno" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                                  default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><g:link class="home" controller="Gen" action="home"><g:message code="ambulanze.home.label"/></g:link></li>
        <li><g:link class="list" action="list">
            <g:message code="militeturno.list.label"
                       default="Elenco militeturno"/>
        </g:link></li>

        <li><g:link class="create" action="create">
            <g:message code="militeturno.new.label"
                       default="Nuovo militeturno"/>
        </g:link></li>

        <li><g:link class="edit" action="edit" id="${militeturnoInstance?.id}">
            <g:message code="militeturno.edit.label" default="Modifica militeturno"/>
        </g:link></li>
        <sec:ifNotLoggedIn>
            <li><g:link class="login" controller="login">Login</g:link></li>
        </sec:ifNotLoggedIn>
        <sec:ifLoggedIn>
            <li><g:link class="logout" controller="logout">Logout</g:link></li>
        </sec:ifLoggedIn>
    </ul>
</div>

<div id="show-militeturno" class="content scaffold-show" role="main">
    <h1><g:message code="militeturno.show.label" default="Mostra militeturno"/></h1>
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
    <ol class="property-list militeturno">
        
        <li class="fieldcontain">
            <span id="croce-label" class="property-label"><g:message
                    code="militeturno.croce.labelform" default="Croce"/></span>
            
            <span class="property-value" aria-labelledby="croce-label"><g:link
                    controller="croce" action="show"
                    id="${militeturnoInstance?.croce?.id}">${militeturnoInstance?.croce?.encodeAsHTML()}</g:link></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="milite-label" class="property-label"><g:message
                    code="militeturno.milite.labelform" default="Milite"/></span>
            
            <span class="property-value" aria-labelledby="milite-label"><g:link
                    controller="milite" action="show"
                    id="${militeturnoInstance?.milite?.id}">${militeturnoInstance?.milite?.encodeAsHTML()}</g:link></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="giorno-label" class="property-label"><g:message
                    code="militeturno.giorno.labelform" default="Giorno"/></span>
            
            <span class="property-value" aria-labelledby="giorno-label"><amb:formatDate
                    date="${militeturnoInstance?.giorno}"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="turno-label" class="property-label"><g:message
                    code="militeturno.turno.labelform" default="Turno"/></span>
            
            <span class="property-value" aria-labelledby="turno-label"><g:link
                    controller="turno" action="show"
                    id="${militeturnoInstance?.turno?.id}">${militeturnoInstance?.turno?.encodeAsHTML()}</g:link></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="funzione-label" class="property-label"><g:message
                    code="militeturno.funzione.labelform" default="Funzione"/></span>
            
            <span class="property-value" aria-labelledby="funzione-label"><g:link
                    controller="funzione" action="show"
                    id="${militeturnoInstance?.funzione?.id}">${militeturnoInstance?.funzione?.encodeAsHTML()}</g:link></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="ore-label" class="property-label"><g:message
                    code="militeturno.ore.labelform" default="Ore"/></span>
            
            <span class="property-value" aria-labelledby="ore-label"><g:fieldValue bean="${militeturnoInstance}"
                                                                                         field="ore"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="dettaglio-label" class="property-label"><g:message
                    code="militeturno.dettaglio.labelform" default="Dettaglio"/></span>
            
            <span class="property-value" aria-labelledby="dettaglio-label"><g:fieldValue bean="${militeturnoInstance}"
                                                                                         field="dettaglio"/></span>
            
        </li>
        
    </ol>

    <g:if test="${campiExtra}">
        <amb:extraScheda rec="${militeturnoInstance}" campiExtra="${campiExtra}"></amb:extraScheda>
    </g:if>

    <g:form>
        <fieldset class="buttons">
            <g:hiddenField name="id" value="${militeturnoInstance?.id}"/>
            <g:link class="edit" action="edit" id="${militeturnoInstance?.id}">
                <g:message code="militeturno.edit.label"
                           default="Modifica militeturno"/>
            </g:link>
            <g:actionSubmit class="delete" action="delete"
                            value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                            onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
