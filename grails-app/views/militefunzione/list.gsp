<!--Created by Algos s.r.l.-->
<!--Date: mag 2012-->
<!--Questo file è stato installato dal plugin AlgosBase-->
<!--Tipicamente NON verrà più sovrascritto dalle successive release del plugin-->
<!--in quanto POTREBBE essere personalizzato in questa applicazione-->
<!--Se vuoi che le prossime release del plugin sovrascrivano questo file,-->
<!--perdendo tutte le modifiche effettuate qui,-->
<!--regola a true il flag di controllo flagOverwrite©-->
<!--flagOverwrite = false-->


<%@ page import="webambulanze.Militefunzione" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'militefunzione.label', default: 'Militefunzione')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<amb:titoloPagina></amb:titoloPagina>
<a href="#list-militefunzione" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                                  default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><g:link class="home" controller="Gen" action="home"><g:message code="ambulanze.home.label"/></g:link></li>
        <li><g:link class="create" action="create">
            <g:message code="militefunzione.new.label"
                       default="Nuovo militefunzione"/>
        </g:link></li>
        <sec:ifNotLoggedIn>
            <li><g:link class="login" controller="login">Login</g:link></li>
        </sec:ifNotLoggedIn>
        <sec:ifLoggedIn>
            <li><g:link class="logout" controller="logout">Logout</g:link></li>
        </sec:ifLoggedIn>
    </ul>
</div>

<div id="list-militefunzione" class="content scaffold-list" role="main">
    <h1><g:message code="militefunzione.list.label" default="Elenco militefunzione"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:if test="${flash.errors}">
        <div class="errors" role="status">${flash.errors}</div>
    </g:if>
    <g:if test="${flash.listaMessaggi}">
        <ul><g:each in="${flash.listaMessaggi}" var="messaggio"><li><div class="message">${messaggio}</div></li></g:each></ul>
    </g:if>
    <g:if test="${flash.listaErrori}">
        <ul><g:each in="${flash.listaErrori}" var="errore"><li class="errors"><div>${errore}</div></li></g:each></ul>
    </g:if>
    <table>
        <thead>
        <amb:titoliLista campiLista="${campiLista}"></amb:titoliLista>
        <g:if test="${campiExtra}">
            <amb:titoliExtraLista campiExtra="${campiExtra}"></amb:titoliExtraLista>
        </g:if>
        </thead>
        <tbody>
        <g:each in="${militefunzioneInstanceList}" status="i" var="militefunzioneInstance">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <amb:rigaLista campiLista="${campiLista}" rec="${militefunzioneInstance}" campiExtra="${campiExtra}"></amb:rigaLista>
            </tr>
        </g:each>
        </tbody>
    </table>

    <g:if test="${militefunzioneInstanceTotal}">
        <div class="pagination">
            <g:paginate total="${militefunzioneInstanceTotal}"/>
        </div>
    </g:if>
</div>
</body>
</html>
