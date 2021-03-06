<%--Created by Algos s.r.l.--%>
<%--Date: mag 2012--%>
<%--Questo file è stato installato dal plugin AlgosBase--%>
<%--Tipicamente NON verrà più sovrascritto dalle successive release del plugin--%>
<%--in quanto POTREBBE essere personalizzato in questa applicazione--%>
<%--Se vuoi che le prossime release del plugin sovrascrivano questo file,--%>
<%--perdendo tutte le modifiche effettuate qui,--%>
<%--regola a true il flag di controllo flagOverwrite©--%>
<%--flagOverwrite = false--%>

<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Welcome to Grails</title>
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
        margin: 2em 1em 1.25em 18em;
    }

    h2 {
        margin-top: 1em;
        margin-bottom: 0.3em;
        font-size: 1em;
    }

    p {
        line-height: 1.5;
        margin: 0.25em 0;
    }

    #controller-list ul {
        list-style-position: inside;
    }

    #controller-list li {
        line-height: 1.3;
        list-style-position: inside;
        margin: 0.25em 0;
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

<g:if test="${application.loginObbligatorio}">
    <sec:ifNotLoggedIn>
        ${response.sendRedirect("Login")}
    </sec:ifNotLoggedIn>
</g:if>

<%--
Se nel bootstrap è stata definita la variabile servletContext.startController
rimanda al metodo di default del controller definito nella variabile,
altrimenti costruisce un elenco di tutti i controller.
Se la variabile non è il nome di un controller esistente va in errore.
--%>
<g:if test="${application.startController}">
<%--   ${response.sendRedirect(application.startController)}   --%>
</g:if>
<g:else>

   <a href="#page-body" class="skip"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

   <div id="status" role="complementary">
       <h1>Application Status</h1>
       <ul>
           <li>App version: <g:meta name="app.version"/></li>
           <li>Grails version: <g:meta name="app.grails.version"/></li>
           <li>Groovy version: ${GroovySystem.getVersion()}</li>
           <li>JVM version: ${System.getProperty('java.version')}</li>
           <li>Reloading active: ${grails.util.Environment.reloadingAgentEnabled}</li>
           <li>Controllers: ${grailsApplication.controllerClasses.size()}</li>
           <li>Domains: ${grailsApplication.domainClasses.size()}</li>
           <li>Services: ${grailsApplication.serviceClasses.size()}</li>
           <li>Tag Libraries: ${grailsApplication.tagLibClasses.size()}</li>
       </ul>

       <h1>Installed Plugins</h1>
       <ul>
           <g:each var="plugin" in="${applicationContext.getBean('pluginManager').allPlugins}">
               <li>${plugin.name} - ${plugin.version}</li>
           </g:each>
       </ul>
   </div>

   <div id="page-body" role="main">
       <h1>Welcome to <g:meta name="app.name"/></h1>

       <div id="controller-list" role="navigation">
           <h2>Moduli disponibili:</h2>
           <g:if test="${application.mostraTuttiControlli}">
               <ul>
                   <g:each var="c" in="${grailsApplication.controllerClasses.findAll { it.fullName != 'Dbdoc' }}">
                       <li class="controller"><g:link
                               controller="${c.logicalPropertyName}">${c.getName()}</g:link></li>
                   </g:each>
               </ul>
           </g:if>
           <g:else>
               <li class="controller"><g:link controller="Croce">Croce</g:link></li>
               <li class="controller"><g:link controller="Settings">Settings</g:link></li>
               <li class="controller"><g:link controller="Funzione">Funzione</g:link></li>
               <li class="controller"><g:link controller="Milite">Milite</g:link></li>
               <li class="controller"><g:link controller="Ruolo">Ruolo</g:link></li>
               <li class="controller"><g:link controller="TipoTurno">TipoTurno</g:link></li>
               <br>
               <li class="controller"><g:link controller="User">User</g:link></li>
               <li class="controller"><g:link controller="Role">Role</g:link></li>
               <li class="controller"><g:link controller="Login">Login</g:link></li>
               <li class="controller"><g:link controller="Logout">Logout</g:link></li>
           </g:else>
       </div>
   </div>
</g:else>

</body>
</html>
