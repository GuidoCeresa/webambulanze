<!--Created by Algos s.r.l.-->
<!--Date: mag 2012-->
<!--Questo file è stato installato dal plugin AlgosBase-->
<!--Tipicamente NON verrà più sovrascritto dalle successive release del plugin-->
<!--in quanto POTREBBE essere personalizzato in questa applicazione-->
<!--Se vuoi che le prossime release del plugin sovrascrivano questo file,-->
<!--perdendo tutte le modifiche effettuate qui,-->
<!--regola a true il flag di controllo flagOverwrite©-->
<!--flagOverwrite = false-->


<%@ page import="org.codehaus.groovy.grails.plugins.springsecurity.ui.RegistrationCode" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'registrationCode.label', default: 'RegistrationCode')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<amb:titoloPagina></amb:titoloPagina>
<a href="#show-registrationCode" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                                  default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><g:link class="home" controller="Gen" action="home"><g:message code="ambulanze.home.label"/></g:link></li>
        <li><g:link class="list" action="list">
            <g:message code="registrationCode.list.label"
                       default="Elenco registrationCode"/>
        </g:link></li>

        <li><g:link class="create" action="create">
            <g:message code="registrationCode.new.label"
                       default="Nuovo registrationCode"/>
        </g:link></li>

        <li><g:link class="edit" action="edit" id="${registrationCodeInstance?.id}">
            <g:message code="registrationCode.edit.label" default="Modifica registrationCode"/>
        </g:link></li>
    </ul>
</div>

<div id="show-registrationCode" class="content scaffold-show" role="main">
    <h1><g:message code="registrationCode.show.label" default="Mostra registrationCode"/></h1>
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
    <ol class="property-list registrationCode">
        
        <li class="fieldcontain">
            <span id="dateCreated-label" class="property-label"><g:message
                    code="registrationCode.dateCreated.labelform" default="Date Created"/></span>
            
            <span class="property-value" aria-labelledby="dateCreated-label"><amb:formatDate
                    date="${registrationCodeInstance?.dateCreated}"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="token-label" class="property-label"><g:message
                    code="registrationCode.token.labelform" default="Token"/></span>
            
            <span class="property-value" aria-labelledby="token-label"><g:fieldValue bean="${registrationCodeInstance}"
                                                                                         field="token"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="username-label" class="property-label"><g:message
                    code="registrationCode.username.labelform" default="Username"/></span>
            
            <span class="property-value" aria-labelledby="username-label"><g:fieldValue bean="${registrationCodeInstance}"
                                                                                         field="username"/></span>
            
        </li>
        
    </ol>

    <g:if test="${campiExtra}">
        <amb:extraScheda rec="${registrationCodeInstance}" campiExtra="${campiExtra}"></amb:extraScheda>
    </g:if>

    <g:form>
        <fieldset class="buttons">
            <g:hiddenField name="id" value="${registrationCodeInstance?.id}"/>
            <g:link class="edit" action="edit" id="${registrationCodeInstance?.id}">
                <g:message code="registrationCode.edit.label"
                           default="Modifica registrationCode"/>
            </g:link>
            <g:actionSubmit class="delete" action="delete"
                            value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                            onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
