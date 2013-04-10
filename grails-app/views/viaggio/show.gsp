<!--Created by Algos s.r.l.-->
<!--Date: mag 2012-->
<!--Questo file è stato installato dal plugin AlgosBase-->
<!--Tipicamente NON verrà più sovrascritto dalle successive release del plugin-->
<!--in quanto POTREBBE essere personalizzato in questa applicazione-->
<!--Se vuoi che le prossime release del plugin sovrascrivano questo file,-->
<!--perdendo tutte le modifiche effettuate qui,-->
<!--regola a true il flag di controllo flagOverwrite©-->
<!--flagOverwrite = false-->


<%@ page import="webambulanze.Viaggio" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'viaggio.label', default: 'Viaggio')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<amb:titoloPagina></amb:titoloPagina>
<a href="#show-viaggio" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                                  default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><g:link class="home" controller="Gen" action="home"><g:message code="ambulanze.home.label"/></g:link></li>
        <li><g:link class="list" action="list">
            <g:message code="viaggio.list.label"
                       default="Elenco viaggio"/>
        </g:link></li>

        <li><g:link class="create" action="create">
            <g:message code="viaggio.new.label"
                       default="Nuovo viaggio"/>
        </g:link></li>

        <li><g:link class="edit" action="edit" id="${viaggioInstance?.id}">
            <g:message code="viaggio.edit.label" default="Modifica viaggio"/>
        </g:link></li>
    </ul>
</div>

<div id="show-viaggio" class="content scaffold-show" role="main">
    <h1><g:message code="viaggio.show.label" default="Mostra viaggio"/></h1>
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
    <ol class="property-list viaggio">
        
        <li class="fieldcontain">
            <span id="croce-label" class="property-label"><g:message
                    code="viaggio.croce.labelform" default="Croce"/></span>
            
            <span class="property-value" aria-labelledby="croce-label"><g:link
                    controller="croce" action="show"
                    id="${viaggioInstance?.croce?.id}">${viaggioInstance?.croce?.encodeAsHTML()}</g:link></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="giorno-label" class="property-label"><g:message
                    code="viaggio.giorno.labelform" default="Giorno"/></span>
            
            <span class="property-value" aria-labelledby="giorno-label"><amb:formatDate
                    date="${viaggioInstance?.giorno}"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="inizio-label" class="property-label"><g:message
                    code="viaggio.inizio.labelform" default="Inizio"/></span>
            
            <span class="property-value" aria-labelledby="inizio-label"><amb:formatDate
                    date="${viaggioInstance?.inizio}"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="fine-label" class="property-label"><g:message
                    code="viaggio.fine.labelform" default="Fine"/></span>
            
            <span class="property-value" aria-labelledby="fine-label"><amb:formatDate
                    date="${viaggioInstance?.fine}"/></span>
            
        </li>
        
    </ol>

    <g:if test="${campiExtra}">
        <amb:extraScheda rec="${viaggioInstance}" campiExtra="${campiExtra}"></amb:extraScheda>
    </g:if>

    <g:form>
        <fieldset class="buttons">
            <g:hiddenField name="id" value="${viaggioInstance?.id}"/>
            <g:link class="edit" action="edit" id="${viaggioInstance?.id}">
                <g:message code="viaggio.edit.label"
                           default="Modifica viaggio"/>
            </g:link>
            <g:actionSubmit class="delete" action="delete"
                            value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                            onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
