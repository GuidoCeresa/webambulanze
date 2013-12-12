<!--Created by Algos s.r.l.-->
<!--Date: mag 2012-->
<!--Questo file è stato installato dal plugin AlgosBase-->
<!--Tipicamente NON verrà più sovrascritto dalle successive release del plugin-->
<!--in quanto POTREBBE essere personalizzato in questa applicazione-->
<!--Se vuoi che le prossime release del plugin sovrascrivano questo file,-->
<!--perdendo tutte le modifiche effettuate qui,-->
<!--regola a true il flag di controllo flagOverwrite©-->
<!--flagOverwrite = false-->


<%@ page import="webambulanze.Versione" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'versione.label', default: 'Versione')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<amb:titoloPagina></amb:titoloPagina>
<a href="#show-versione" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                                  default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><g:link class="home" controller="Gen" action="home"><g:message code="ambulanze.home.label"/></g:link></li>
        <li><g:link class="list" action="list">
            <g:message code="versione.list.label"
                       default="Elenco versione"/>
        </g:link></li>

        <li><g:link class="create" action="create">
            <g:message code="versione.new.label"
                       default="Nuovo versione"/>
        </g:link></li>

        <li><g:link class="edit" action="edit" id="${versioneInstance?.id}">
            <g:message code="versione.edit.label" default="Modifica versione"/>
        </g:link></li>
        <sec:ifNotLoggedIn>
            <li><g:link class="login" controller="login">Login</g:link></li>
        </sec:ifNotLoggedIn>
        <sec:ifLoggedIn>
            <li><g:link class="logout" controller="logout">Logout</g:link></li>
        </sec:ifLoggedIn>
    </ul>
</div>

<div id="show-versione" class="content scaffold-show" role="main">
    <h1><g:message code="versione.show.label" default="Mostra versione"/></h1>
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
    <ol class="property-list versione">
        
        <li class="fieldcontain">
            <span id="numero-label" class="property-label"><g:message
                    code="versione.numero.labelform" default="Numero"/></span>
            
            <span class="property-value" aria-labelledby="numero-label"><g:fieldValue bean="${versioneInstance}"
                                                                                         field="numero"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="croce-label" class="property-label"><g:message
                    code="versione.croce.labelform" default="Croce"/></span>
            
            <span class="property-value" aria-labelledby="croce-label"><g:link
                    controller="croce" action="show"
                    id="${versioneInstance?.croce?.id}">${versioneInstance?.croce?.encodeAsHTML()}</g:link></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="giorno-label" class="property-label"><g:message
                    code="versione.giorno.labelform" default="Giorno"/></span>
            
            <span class="property-value" aria-labelledby="giorno-label"><amb:formatDate
                    date="${versioneInstance?.giorno}"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="titolo-label" class="property-label"><g:message
                    code="versione.titolo.labelform" default="Titolo"/></span>
            
            <span class="property-value" aria-labelledby="titolo-label"><g:fieldValue bean="${versioneInstance}"
                                                                                         field="titolo"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="descrizione-label" class="property-label"><g:message
                    code="versione.descrizione.labelform" default="Descrizione"/></span>
            
            <span class="property-value" aria-labelledby="descrizione-label"><g:fieldValue bean="${versioneInstance}"
                                                                                         field="descrizione"/></span>
            
        </li>
        
    </ol>

    <g:if test="${campiExtra}">
        <amb:extraScheda rec="${versioneInstance}" campiExtra="${campiExtra}"></amb:extraScheda>
    </g:if>

    <g:form>
        <fieldset class="buttons">
            <g:hiddenField name="id" value="${versioneInstance?.id}"/>
            <g:link class="edit" action="edit" id="${versioneInstance?.id}">
                <g:message code="versione.edit.label"
                           default="Modifica versione"/>
            </g:link>
            <g:actionSubmit class="delete" action="delete"
                            value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                            onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
