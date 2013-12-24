<!--Created by Algos s.r.l.-->
<!--Date: mag 2012-->
<!--Questo file è stato installato dal plugin AlgosBase-->
<!--Tipicamente NON verrà più sovrascritto dalle successive release del plugin-->
<!--in quanto POTREBBE essere personalizzato in questa applicazione-->
<!--Se vuoi che le prossime release del plugin sovrascrivano questo file,-->
<!--perdendo tutte le modifiche effettuate qui,-->
<!--regola a true il flag di controllo flagOverwrite©-->
<!--flagOverwrite = false-->

<%@ page import="webambulanze.Turno" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'turno.label', default: 'Turno')}"/>
    <title><g:message code="default.edit.label" args="[entityName]"/></title>
</head>

<body>
<amb:titoloPagina></amb:titoloPagina>
<a href="#edit-turno" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                            default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><g:link class="home" controller="Turno" action="tabCorrente"><g:message
                code="tabellone.ritorna.label"/></g:link></li>
    </ul>
</div>

<div id="edit-turno" class="content scaffold-edit" role="main">
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
    <g:form method="post">
        <g:hiddenField name="id" value="${turnoInstance?.id}"/>
        <g:hiddenField name="version" value="${turnoInstance?.version}"/>
        <g:hiddenField name="nuovoTurno" value="${nuovoTurno}"/>
        <fieldset class="form">
            <amb:fillForm turnoInstance="${turnoInstance?.id}" nuovoTurno="${nuovoTurno}"> </amb:fillForm>
        </fieldset>
        <fieldset class="buttons">

            <g:if test="${turniSecured}">
                <g:actionSubmit class="save" action="updateSecured"
                                value="${message(code: 'tabellone.registra.label', default: 'Update')}"/>
            </g:if>
            <g:else>
                <g:actionSubmit class="save" action="update"
                                value="${message(code: 'tabellone.registra.label', default: 'Update')}"/>
            </g:else>

            <g:actionSubmit class="cancel" action="uscitaSenzaModifiche"
                            value="${message(code: 'tabellone.annulla.label', default: 'Cancel')}"/>
            <sec:ifAnyGranted roles="ROLE_programmatore,ROLE_custode,ROLE_admin">
                <g:actionSubmit class="delete" action="delete"
                                value="${message(code: 'tabellone.delete.label', default: 'Delete')}"
                                onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Sei sicuro?')}');"/>
                <g:actionSubmit class="edit" action="dettaglioTurno" id="${turnoInstance?.id}"
                                value="${message(code: 'tabellone.dettaglio.label', default: 'Dettaglio')}"/>
            </sec:ifAnyGranted>
        </fieldset>
    </g:form>
</div>
</body>
</html>
