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
    <title><g:message code="default.edit.label" args="[entityName]"/></title>
</head>

<body>
<a href="#edit-registrationCode" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
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
    </ul>
</div>

<div id="edit-registrationCode" class="content scaffold-edit" role="main">
    <h1><g:message code="registrationCode.edit.label" default="Modifica registrationCode"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${registrationCodeInstance}">
        <ul class="errors" role="alert">
            <g:eachError bean="${registrationCodeInstance}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                        error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
    <g:form method="post" >
    <g:hiddenField name="id" value="${registrationCodeInstance?.id}"/>
    <g:hiddenField name="version" value="${registrationCodeInstance?.version}"/>
    <fieldset class="form">
        <g:render template="form"/>
    </fieldset>
    <fieldset class="buttons">
        <g:actionSubmit class="save" action="update"
                        value="${message(code: 'default.button.update.label', default: 'Update')}"/>
        <g:actionSubmit class="delete" action="delete"
                        value="${message(code: 'default.button.delete.label', default: 'Delete')}" formnovalidate=""
                        onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
    </fieldset>
    </g:form>
</div>
</body>
</html>
