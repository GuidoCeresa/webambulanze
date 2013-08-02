<%@ page import="webambulanze.Turno" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
</head>

<body>
<amb:titoloPagina> </amb:titoloPagina>

<g:if test="${flash.message}">
    <div class="message" role="status">${flash.message}</div>
</g:if>
<g:if test="${flash.errors}">
    <div class="errors" role="status">${flash.errors}</div>
</g:if>
<g:if test="${flash.listaMessaggi}">
    <ul><g:each in="${flash.listaMessaggi}" var="messaggio"><li><div class="message">${messaggio}</div></li></g:each>
    </ul>
</g:if>
<g:if test="${flash.listaErrori}">
    <ul><g:each in="${flash.listaErrori}" var="errore"><li class="errors"><div>${errore}</div></li></g:each></ul>
</g:if>

<div class="nav" role="navigation">
    <ul>
        <sec:ifAnyGranted roles="ROLE_programmatore,ROLE_custode,ROLE_admin,ROLE_milite">
            <li>
                <g:link class="home" controller="Gen" action="home">
                    <g:message code="ambulanze.home.label"/>
                </g:link>
            </li>
        </sec:ifAnyGranted>
        <li>
            <g:link class="tabellonePrima" action="tabellonePrima">
                <g:message code="tabellone.precedente.label" default="Set Prec"/>
            </g:link>
        </li>
        <li>
            <g:link class="tabelloneLunedi" action="tabelloneLunedi">
                <g:message code="tabellone.lunedi.label" default="Set Lun"/>
            </g:link>
        </li>
        <li>
            <g:link class="tabelloneOggi" action="tabelloneOggi">
                <g:message code="tabellone.oggi.label" default="Set Oggi"/>
            </g:link>
        </li>
        <li>
            <g:link class="tabelloneDopo" action="tabelloneDopo">
                <g:message code="tabellone.successiva.label" default="Set Succ"/>
            </g:link>
        </li>
        <amb:ifModuloViaggi>
            <li><g:link class="viaggio" controller="viaggio">Viaggi</g:link></li>
        </amb:ifModuloViaggi>
        <sec:ifNotLoggedIn>
            <li><g:link
                    class="login"
                    controller="accesso"
                    action="selezionaLogin">
                Login</g:link></li>
        </sec:ifNotLoggedIn>
        <sec:ifLoggedIn>
            <li><g:link class="logout" controller="logout">Logout</g:link></li>
        </sec:ifLoggedIn>
    </ul>
</div>

<amb:tabella dataInizio="${dataInizio}" dataFine="${dataFine}"></amb:tabella>

</body>
</html>