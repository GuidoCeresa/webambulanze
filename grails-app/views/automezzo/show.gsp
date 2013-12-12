<!--Created by Algos s.r.l.-->
<!--Date: mag 2012-->
<!--Questo file è stato installato dal plugin AlgosBase-->
<!--Tipicamente NON verrà più sovrascritto dalle successive release del plugin-->
<!--in quanto POTREBBE essere personalizzato in questa applicazione-->
<!--Se vuoi che le prossime release del plugin sovrascrivano questo file,-->
<!--perdendo tutte le modifiche effettuate qui,-->
<!--regola a true il flag di controllo flagOverwrite©-->
<!--flagOverwrite = false-->


<%@ page import="webambulanze.Automezzo" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'automezzo.label', default: 'Automezzo')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<amb:titoloPagina></amb:titoloPagina>
<a href="#show-automezzo" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                                  default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><g:link class="home" controller="Gen" action="home"><g:message code="ambulanze.home.label"/></g:link></li>
        <li><g:link class="list" action="list">
            <g:message code="automezzo.list.label"
                       default="Elenco automezzo"/>
        </g:link></li>

        <li><g:link class="create" action="create">
            <g:message code="automezzo.new.label"
                       default="Nuovo automezzo"/>
        </g:link></li>

        <li><g:link class="edit" action="edit" id="${automezzoInstance?.id}">
            <g:message code="automezzo.edit.label" default="Modifica automezzo"/>
        </g:link></li>
        <g:if test="${menuExtra}">
            <li><amb:menuExtra menuExtra="${menuExtra}" id="${automezzoInstance?.id}"></amb:menuExtra></li>
        </g:if>
        <sec:ifNotLoggedIn>
            <li><g:link class="login" controller="login">Login</g:link></li>
        </sec:ifNotLoggedIn>
        <sec:ifLoggedIn>
            <li><g:link class="logout" controller="logout">Logout</g:link></li>
        </sec:ifLoggedIn>
    </ul>
</div>

<div id="show-automezzo" class="content scaffold-show" role="main">
    <h1><g:message code="automezzo.show.label" default="Mostra automezzo"/></h1>
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
    <ol class="property-list automezzo">
        
        <li class="fieldcontain">
            <span id="croce-label" class="property-label"><g:message
                    code="automezzo.croce.labelform" default="Croce"/></span>
            
            <span class="property-value" aria-labelledby="croce-label"><g:link
                    controller="croce" action="show"
                    id="${automezzoInstance?.croce?.id}">${automezzoInstance?.croce?.encodeAsHTML()}</g:link></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="tipo-label" class="property-label"><g:message
                    code="automezzo.tipo.labelform" default="Tipo"/></span>
            
            <span class="property-value" aria-labelledby="tipo-label"><g:fieldValue bean="${automezzoInstance}"
                                                                                         field="tipo"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="dataAcquisto-label" class="property-label"><g:message
                    code="automezzo.dataAcquisto.labelform" default="Data Acquisto"/></span>
            
            <span class="property-value" aria-labelledby="dataAcquisto-label"><amb:formatDate
                    date="${automezzoInstance?.dataAcquisto}"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="sigla-label" class="property-label"><g:message
                    code="automezzo.sigla.labelform" default="Sigla"/></span>
            
            <span class="property-value" aria-labelledby="sigla-label"><g:fieldValue bean="${automezzoInstance}"
                                                                                         field="sigla"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="targa-label" class="property-label"><g:message
                    code="automezzo.targa.labelform" default="Targa"/></span>
            
            <span class="property-value" aria-labelledby="targa-label"><g:fieldValue bean="${automezzoInstance}"
                                                                                         field="targa"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="descrizione-label" class="property-label"><g:message
                    code="automezzo.descrizione.labelform" default="Descrizione"/></span>
            
            <span class="property-value" aria-labelledby="descrizione-label"><g:fieldValue bean="${automezzoInstance}"
                                                                                         field="descrizione"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="donazione-label" class="property-label"><g:message
                    code="automezzo.donazione.labelform" default="Donazione"/></span>
            
            <span class="property-value" aria-labelledby="donazione-label"><g:fieldValue bean="${automezzoInstance}"
                                                                                         field="donazione"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="chilometriTotaliPercorsi-label" class="property-label"><g:message
                    code="automezzo.chilometriTotaliPercorsi.labelform" default="Chilometri Totali Percorsi"/></span>
            
            <span class="property-value" aria-labelledby="chilometriTotaliPercorsi-label"><g:fieldValue bean="${automezzoInstance}"
                                                                                         field="chilometriTotaliPercorsi"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="numeroViaggiEffettuati-label" class="property-label"><g:message
                    code="automezzo.numeroViaggiEffettuati.labelform" default="Numero Viaggi Effettuati"/></span>
            
            <span class="property-value" aria-labelledby="numeroViaggiEffettuati-label"><g:fieldValue bean="${automezzoInstance}"
                                                                                         field="numeroViaggiEffettuati"/></span>
            
        </li>
        
    </ol>

    <g:if test="${campiExtra}">
        <amb:extraScheda rec="${automezzoInstance}" campiExtra="${campiExtra}"></amb:extraScheda>
    </g:if>

    <g:form>
        <fieldset class="buttons">
            <g:hiddenField name="id" value="${automezzoInstance?.id}"/>
            <g:link class="edit" action="edit" id="${automezzoInstance?.id}">
                <g:message code="automezzo.edit.label"
                           default="Modifica automezzo"/>
            </g:link>
            <g:actionSubmit class="delete" action="delete"
                            value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                            onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
