<!--Created by Algos s.r.l.-->
<!--Date: mag 2012-->
<!--Questo file è stato installato dal plugin AlgosBase-->
<!--Tipicamente NON verrà più sovrascritto dalle successive release del plugin-->
<!--in quanto POTREBBE essere personalizzato in questa applicazione-->
<!--Se vuoi che le prossime release del plugin sovrascrivano questo file,-->
<!--perdendo tutte le modifiche effettuate qui,-->
<!--regola a true il flag di controllo flagOverwrite©-->
<!--flagOverwrite = false-->


<%@ page import="webambulanze.Utente" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'utente.label', default: 'Utente')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<amb:titoloPagina></amb:titoloPagina>
<a href="#show-utente" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                             default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><g:link class="home" controller="Gen" action="home"><g:message code="ambulanze.home.label"/></g:link></li>
        <li><g:link class="list" action="list">
            <g:message code="utente.list.label"
                       default="Elenco utente"/>
        </g:link></li>

        <li><g:link class="create" action="create">
            <g:message code="utente.new.label"
                       default="Nuovo utente"/>
        </g:link></li>

        <li><g:link class="edit" action="edit" id="${utenteInstance?.id}">
            <g:message code="utente.edit.label" default="Modifica utente"/>
        </g:link></li>
        <sec:ifNotLoggedIn>
            <li><g:link class="login" controller="login">Login</g:link></li>
        </sec:ifNotLoggedIn>
        <sec:ifLoggedIn>
            <li><g:link class="logout" controller="logout">Logout</g:link></li>
        </sec:ifLoggedIn>
    </ul>
</div>

<div id="show-utente" class="content scaffold-show" role="main">
    <h1><g:message code="utente.show.label" default="Mostra utente"/></h1>
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
    <ol class="property-list utente">

        <li class="fieldcontain">
            <span id="croce-label" class="property-label"><g:message
                    code="utente.croce.labelform" default="Croce"/></span>

            <span class="property-value" aria-labelledby="croce-label"><g:link
                    controller="croce" action="show"
                    id="${utenteInstance?.croce?.id}">${utenteInstance?.croce?.encodeAsHTML()}</g:link></span>

        </li>

        <li class="fieldcontain">
            <span id="milite-label" class="property-label"><g:message
                    code="utente.milite.labelform" default="Milite"/></span>

            <span class="property-value" aria-labelledby="milite-label"><g:link
                    controller="milite" action="show"
                    id="${utenteInstance?.milite?.id}">${utenteInstance?.milite?.encodeAsHTML()}</g:link></span>

        </li>

        <li class="fieldcontain">
            <span id="username-label" class="property-label"><g:message
                    code="utente.username.labelform" default="Username"/></span>

            <span class="property-value" aria-labelledby="username-label"><g:fieldValue bean="${utenteInstance}"
                                                                                        field="username"/></span>

        </li>

        <li class="fieldcontain">
            <span id="nickname-label" class="property-label"><g:message
                    code="utente.nickname.labelform" default="Nickname"/></span>

            <span class="property-value" aria-labelledby="nickname-label"><g:fieldValue bean="${utenteInstance}"
                                                                                        field="nickname"/></span>

        </li>

        <li class="fieldcontain">
            <span id="password-label" class="property-label"><g:message
                    code="utente.password.labelform" default="Password"/></span>

            <span class="property-value" aria-labelledby="password-label"><g:fieldValue bean="${utenteInstance}"
                                                                                        field="password"/></span>

        </li>

        <li class="fieldcontain">
            <span id="pass-label" class="property-label"><g:message
                    code="utente.pass.labelform" default="Pass"/></span>

            <span class="property-value" aria-labelledby="pass-label"><g:fieldValue bean="${utenteInstance}"
                                                                                    field="pass"/></span>

        </li>

        <li class="fieldcontain">
            <span id="enabled-label" class="property-label"><g:message
                    code="utente.enabled.labelform" default="Enabled"/></span>

            <span class="property-value" aria-labelledby="enabled-label"><g:formatBoolean
                    boolean="${utenteInstance?.enabled}"/></span>

        </li>

        <li class="fieldcontain">
            <span id="accountExpired-label" class="property-label"><g:message
                    code="utente.accountExpired.labelform" default="Account Expired"/></span>

            <span class="property-value" aria-labelledby="accountExpired-label"><g:formatBoolean
                    boolean="${utenteInstance?.accountExpired}"/></span>

        </li>

        <li class="fieldcontain">
            <span id="accountLocked-label" class="property-label"><g:message
                    code="utente.accountLocked.labelform" default="Account Locked"/></span>

            <span class="property-value" aria-labelledby="accountLocked-label"><g:formatBoolean
                    boolean="${utenteInstance?.accountLocked}"/></span>

        </li>

        <li class="fieldcontain">
            <span id="passwordExpired-label" class="property-label"><g:message
                    code="utente.passwordExpired.labelform" default="Password Expired"/></span>

            <span class="property-value" aria-labelledby="passwordExpired-label"><g:formatBoolean
                    boolean="${utenteInstance?.passwordExpired}"/></span>

        </li>

    </ol>

    <g:if test="${campiExtra}">
        <amb:extraScheda rec="${utenteInstance}" campiExtra="${campiExtra}"></amb:extraScheda>
    </g:if>

    <g:form>
        <fieldset class="buttons">
            <g:hiddenField name="id" value="${utenteInstance?.id}"/>
            <g:link class="edit" action="edit" id="${utenteInstance?.id}">
                <g:message code="utente.edit.label"
                           default="Modifica utente"/>
            </g:link>
            <g:actionSubmit class="delete" action="delete"
                            value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                            onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
