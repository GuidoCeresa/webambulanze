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
        margin-top: 1em;
        font-size: 1.4em;
    }

    h2 {
        margin-top: 1em;
        margin-bottom: 0.3em;
        font-size: 1.4em;
    }

    p {
        line-height: 1.5;
        margin: 0.25em 0;
    }

    #algos {
        margin-left: 2em;
        margin-top: 0em;
        margin-bottom: 1em;
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
    <div id="controller-list" role="navigation">
        <h2>Nuovo viaggio</h2>
        <h1>Automezzo impiegato</h1>
    </div>
</div>

<g:form method="post" >
    <g:hiddenField name="id" value="${viaggioInstance?.id}"/>
    <g:hiddenField name="version" value="${viaggioInstance?.version}"/>
    <g:hiddenField name="tipoViaggio" value="${tipoViaggio}"/>

    <div id="algos">
        <g:select id="automezzo" name="automezzo.id" from="${Automezzo.list()}" optionKey="id" required="" value="${viaggioInstance?.automezzo?.id}" class="many-to-one"/>
    </div>
    <fieldset class="buttons">
        <g:actionSubmit class="save" action="nuovoViaggio"
                        value="${message(code: 'default.button.confirm.label', default: 'Conferma')}"/>
    </fieldset>
</g:form>


</body>
</html>