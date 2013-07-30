<%--Created by Algos s.r.l.--%>
<%--Date: mag 2012--%>
<%--Questo file è stato installato dal plugin AlgosBase--%>
<%--Tipicamente NON verrà più sovrascritto dalle successive release del plugin--%>
<%--in quanto POTREBBE essere personalizzato in questa applicazione--%>
<%--Se vuoi che le prossime release del plugin sovrascrivano questo file,--%>
<%--perdendo tutte le modifiche effettuate qui,--%>
<%--regola a true il flag di controllo flagOverwrite©--%>
<%--flagOverwrite = false--%>

<%@ page import="webambulanze.GenController" %>
<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
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
        margin-left: 2em;
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

<g:if test="${application.loginObbligatorio}">
    <sec:ifNotLoggedIn>
        ${response.sendRedirect("Login")}
    </sec:ifNotLoggedIn>
</g:if>


<a href="#page-body" class="skip"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

<div id="page-body" role="main">
    <div id="controller-list" role="navigation">
        <amb:listaControllers></amb:listaControllers>
    </div>
</div>

</body>
</html>
