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
    <g:set var="entityName" value="${message(code: 'turno.label', default: 'Turno')}"/>
    <title>Nuovo viaggio</title>
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
    </ul>
</div>

<div id="edit-viaggio" class="content scaffold-edit" role="main">
    <h1>${tipoForm}</h1>
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
    <g:form action="save">
        <g:hiddenField name="tipoViaggio" value="${tipoViaggio}"/>
        <g:hiddenField name="automezzoId" value="${automezzoId}"/>
        <g:hiddenField name="turnoId" value="${turnoId}"/>
        <fieldset class="form">
            <amb:newViaggio tipoViaggio="${tipoViaggio}" automezzoId="${automezzoId}"
                             turnoId="${turnoId}"> </amb:newViaggio>
        </fieldset>
        <fieldset class="buttons">
            <g:submitButton name="create" class="save"
                            value="${message(code: 'default.button.confirm.label', default: 'Create')}"/>
            <g:submitButton name="list" class="list" annulla=""
                            formnovalidate=""
                            value="${message(code: 'default.button.cancel.label', default: 'Annulla')}"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
