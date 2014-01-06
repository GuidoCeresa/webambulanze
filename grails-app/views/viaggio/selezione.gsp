<%@ page import="webambulanze.Automezzo" %>
<%@ page import="webambulanze.Viaggio" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <title>Ambulanze</title>
    <style type="text/css" media="screen">
    #status {
        background-color: #eee;
        border: .2em solid #fff;
        margin: 2em 2em 1em;
        padding: 1em;
        width: 12em;
        float: left;
        -moz-box-shadow: 0px 0px 1.25em #ccc;
        -webkit-box-shadow: 0px 0px 1.25em #ccc;
        box-shadow: 0px 0px 1.25em #ccc;
        -moz-border-radius: 0.6em;
        -webkit-border-radius: 0.6em;
        border-radius: 0.6em;
    }

    .ie6 #status {
        display: inline; /* float double margin fix http://www.positioniseverything.net/explorer/doubled-margin.html */
    }

    #status ul {
        font-size: 0.9em;
        list-style-type: none;
        margin-bottom: 0.6em;
        padding: 0;
    }

    #status li {
        line-height: 1.3;
    }

    #status h1 {
        text-transform: uppercase;
        font-size: 1.1em;
        margin: 0 0 0.3em;
    }

    #page-body {
        margin: 2em 1em 1.25em 2em;
    }

    h1 {
        margin-left: 0em;
        margin-top: 1em;
        margin-bottom: 0em;
        font-size: 1.4em;
    }

    h2 {
        margin-top: 0em;
        margin-bottom: 0.3em;
        font-size: 1.4em;
    }

    h3 {
        margin-top: 0em;
        margin-bottom: 0.7em;
        font-size: 0.8em;
        color: #ff3300;
    }

    .algoslabel {
        margin-left: 0em;
        margin-top: 1em;
        margin-bottom: 0em;
        color: #48802c;
        font-weight: bold;
        font-size: 1.25em;
    }

    p {
        line-height: 1.5;
        margin: 0.25em 0;
    }

    #controller-list ul {
    }

    #controller-list li {
        font-size: 1.4em;
        line-height: 1.5;
        margin: 0.25em 0 0 1em;
    }

    @media screen and (max-width: 480px) {
        #status {
            display: none;
        }

        #page-body {
            margin: 0 1em 1em;
        }

        #page-body h1 {
            margin-top: 0;
        }
    }

    #algosform, #algosservizio, #algosmezzo, #algosturno {
        font-size: 1.2em;
        line-height: 1.3;
        margin: 1em 1em 1em 2em;
    }

    </style>
</head>

<body>
<amb:titoloPagina></amb:titoloPagina>

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
        <sec:ifNotLoggedIn>
            <li><g:link class="login" controller="login">Login</g:link></li>
        </sec:ifNotLoggedIn>
        <sec:ifLoggedIn>
            <li><g:link class="logout" controller="logout">Logout</g:link></li>
        </sec:ifLoggedIn>
    </ul>
</div>

<div id="page-body" role="main">

</div>
<g:form method="post">
    <g:hiddenField name="id" value="${viaggioInstance?.id}"/>
    <g:hiddenField name="version" value="${viaggioInstance?.version}"/>
    <g:hiddenField name="tipoViaggio" value="${tipoViaggio}"/>

    <fieldset class="form">
        <h2>Nuovo viaggio</h2>

        <label class="algoslabel">Selezione del tipo di viaggio effettuato:</label>
        <g:select name="tipoViaggio" from="${listaTipologieViaggi}" required=""
                  value="${tipoSelezionato}" noSelection="['null': '']"/>
        <h3>(Prepara il form adeguato)</h3>

        <label class="algoslabel">Automezzo impiegato nel servizio:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
        <g:select name="auto" from="${listaAutomezzi}" required="" noSelection="['null': '']"/>
        <h3>(Recupera i chilometri ed i viaggi effettuati dal mezzo)</h3>

        <label class="algoslabel">Turno di riferimento:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>
        <g:select name="turno" keys="${listaTurniId}" from="${listaTurni}" noSelection="['null': '']"/>
        <h3>(Recupera i militi presenti nel turno)</h3>

    </fieldset>

    <fieldset class="buttons">
        <g:actionSubmit class="save" action="nuovoViaggio"
                        value="${message(code: 'default.button.confirm.label', default: 'Conferma')}"/>
        <g:submitButton name="list" class="list" annulla=""
                        value="${message(code: 'default.button.cancel.label', default: 'Annulla')}"/>
    </fieldset>
</g:form>

</body>
</html>