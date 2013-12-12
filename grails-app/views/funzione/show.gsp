<!--Created by Algos s.r.l.-->
<!--Date: mag 2012-->
<!--Questo file è stato installato dal plugin AlgosBase-->
<!--Tipicamente NON verrà più sovrascritto dalle successive release del plugin-->
<!--in quanto POTREBBE essere personalizzato in questa applicazione-->
<!--Se vuoi che le prossime release del plugin sovrascrivano questo file,-->
<!--perdendo tutte le modifiche effettuate qui,-->
<!--regola a true il flag di controllo flagOverwrite©-->
<!--flagOverwrite = false-->


<%@ page import="webambulanze.Funzione" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'funzione.label', default: 'Funzione')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<amb:titoloPagina></amb:titoloPagina>
<a href="#show-funzione" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                                  default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><g:link class="home" controller="Gen" action="home"><g:message code="ambulanze.home.label"/></g:link></li>
        <li><g:link class="list" action="list">
            <g:message code="funzione.list.label"
                       default="Elenco funzione"/>
        </g:link></li>

        <li><g:link class="create" action="create">
            <g:message code="funzione.new.label"
                       default="Nuovo funzione"/>
        </g:link></li>

        <li><g:link class="edit" action="edit" id="${funzioneInstance?.id}">
            <g:message code="funzione.edit.label" default="Modifica funzione"/>
        </g:link></li>
        <sec:ifNotLoggedIn>
            <li><g:link class="login" controller="login">Login</g:link></li>
        </sec:ifNotLoggedIn>
        <sec:ifLoggedIn>
            <li><g:link class="logout" controller="logout">Logout</g:link></li>
        </sec:ifLoggedIn>
    </ul>
</div>

<div id="show-funzione" class="content scaffold-show" role="main">
    <h1><g:message code="funzione.show.label" default="Mostra funzione"/></h1>
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
    <ol class="property-list funzione">
        
        <li class="fieldcontain">
            <span id="croce-label" class="property-label"><g:message
                    code="funzione.croce.labelform" default="Croce"/></span>
            
            <span class="property-value" aria-labelledby="croce-label"><g:link
                    controller="croce" action="show"
                    id="${funzioneInstance?.croce?.id}">${funzioneInstance?.croce?.encodeAsHTML()}</g:link></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="ordine-label" class="property-label"><g:message
                    code="funzione.ordine.labelform" default="Ordine"/></span>
            
            <span class="property-value" aria-labelledby="ordine-label"><g:fieldValue bean="${funzioneInstance}"
                                                                                         field="ordine"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="sigla-label" class="property-label"><g:message
                    code="funzione.sigla.labelform" default="Sigla"/></span>
            
            <span class="property-value" aria-labelledby="sigla-label"><g:fieldValue bean="${funzioneInstance}"
                                                                                         field="sigla"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="siglaVisibile-label" class="property-label"><g:message
                    code="funzione.siglaVisibile.labelform" default="Sigla Visibile"/></span>
            
            <span class="property-value" aria-labelledby="siglaVisibile-label"><g:fieldValue bean="${funzioneInstance}"
                                                                                         field="siglaVisibile"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="descrizione-label" class="property-label"><g:message
                    code="funzione.descrizione.labelform" default="Descrizione"/></span>
            
            <span class="property-value" aria-labelledby="descrizione-label"><g:fieldValue bean="${funzioneInstance}"
                                                                                         field="descrizione"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="funzioniDipendenti-label" class="property-label"><g:message
                    code="funzione.funzioniDipendenti.labelform" default="Funzioni Dipendenti"/></span>
            
            <span class="property-value" aria-labelledby="funzioniDipendenti-label"><g:fieldValue bean="${funzioneInstance}"
                                                                                         field="funzioniDipendenti"/></span>
            
        </li>
        
    </ol>

    <g:if test="${campiExtra}">
        <amb:extraScheda rec="${funzioneInstance}" campiExtra="${campiExtra}"></amb:extraScheda>
    </g:if>

    <g:form>
        <fieldset class="buttons">
            <g:hiddenField name="id" value="${funzioneInstance?.id}"/>
            <g:link class="edit" action="edit" id="${funzioneInstance?.id}">
                <g:message code="funzione.edit.label"
                           default="Modifica funzione"/>
            </g:link>
            <g:actionSubmit class="delete" action="delete"
                            value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                            onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
