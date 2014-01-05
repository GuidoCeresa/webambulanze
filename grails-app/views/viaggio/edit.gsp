<!--Created by Algos s.r.l.-->
<!--Date: mag 2012-->
<!--Questo file è stato installato dal plugin AlgosBase-->
<!--Tipicamente NON verrà più sovrascritto dalle successive release del plugin-->
<!--in quanto POTREBBE essere personalizzato in questa applicazione-->
<!--Se vuoi che le prossime release del plugin sovrascrivano questo file,-->
<!--perdendo tutte le modifiche effettuate qui,-->
<!--regola a true il flag di controllo flagOverwrite©-->
<!--flagOverwrite = false-->

<%@ page import="org.springframework.validation.FieldError; webambulanze.Viaggio" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'viaggio.label', default: 'Viaggio')}"/>
    <title><g:message code="default.edit.label" args="[entityName]"/></title>
</head>

<body>
<amb:titoloPagina></amb:titoloPagina>
<a href="#edit-viaggio" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
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
    </ul>
</div>

<div id="edit-viaggio" class="content scaffold-edit" role="main">
    <h1><g:message code="viaggio.edit.label" default="Modifica viaggio"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${viaggioInstance}">
        <ul class="errors" role="alert">
            <g:eachError bean="${viaggioInstance}" var="error">
                <li <g:if test="${error in FieldError}">data-field-id="${error.field}"</g:if>><g:message
                        error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
    <g:form method="post">
        <g:hiddenField name="id" value="${viaggioInstance?.id}"/>
        <g:hiddenField name="version" value="${viaggioInstance?.version}"/>
        <fieldset class="form">
            <amb:editViaggio> </amb:editViaggio>
        </fieldset>
        <fieldset class="buttons">
            <g:actionSubmit class="save" action="update"
                            value="${message(code: 'default.button.update.label', default: 'Update')}"/>
            <g:submitButton name="list" class="list" annulla=""
                            formnovalidate=""
                            value="${message(code: 'default.button.cancel.label', default: 'Annulla')}"/>
            <g:actionSubmit class="delete" action="delete"
                            value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                            formnovalidate=""
                            onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Sei sicuro?')}');"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
