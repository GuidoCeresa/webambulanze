<!--Created by Algos s.r.l.-->
<!--Date: mag 2012-->
<!--Questo file è stato installato dal plugin AlgosBase-->
<!--Tipicamente NON verrà più sovrascritto dalle successive release del plugin-->
<!--in quanto POTREBBE essere personalizzato in questa applicazione-->
<!--Se vuoi che le prossime release del plugin sovrascrivano questo file,-->
<!--perdendo tutte le modifiche effettuate qui,-->
<!--regola a true il flag di controllo flagOverwrite©-->
<!--flagOverwrite = false-->


<%@ page import="webambulanze.Militestatistiche" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'militestatistiche.label', default: 'Militestatistiche')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<amb:titoloPagina></amb:titoloPagina>
<a href="#show-militestatistiche" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                                  default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><g:link class="home" controller="Gen" action="home"><g:message code="ambulanze.home.label"/></g:link></li>
        <li><g:link class="list" action="list">
            <g:message code="militestatistiche.list.label"
                       default="Elenco militestatistiche"/>
        </g:link></li>

        <li><g:link class="create" action="create">
            <g:message code="militestatistiche.new.label"
                       default="Nuovo militestatistiche"/>
        </g:link></li>

        <li><g:link class="edit" action="edit" id="${militestatisticheInstance?.id}">
            <g:message code="militestatistiche.edit.label" default="Modifica militestatistiche"/>
        </g:link></li>
        <sec:ifNotLoggedIn>
            <li><g:link class="login" controller="login">Login</g:link></li>
        </sec:ifNotLoggedIn>
        <sec:ifLoggedIn>
            <li><g:link class="logout" controller="logout">Logout</g:link></li>
        </sec:ifLoggedIn>
    </ul>
</div>

<div id="show-militestatistiche" class="content scaffold-show" role="main">
    <h1><g:message code="militestatistiche.show.label" default="Mostra militestatistiche"/></h1>
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
    <ol class="property-list militestatistiche">
        
        <li class="fieldcontain">
            <span id="croce-label" class="property-label"><g:message
                    code="militestatistiche.croce.labelform" default="Croce"/></span>
            
            <span class="property-value" aria-labelledby="croce-label"><g:link
                    controller="croce" action="show"
                    id="${militestatisticheInstance?.croce?.id}">${militestatisticheInstance?.croce?.encodeAsHTML()}</g:link></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="milite-label" class="property-label"><g:message
                    code="militestatistiche.milite.labelform" default="Milite"/></span>
            
            <span class="property-value" aria-labelledby="milite-label"><g:link
                    controller="milite" action="show"
                    id="${militestatisticheInstance?.milite?.id}">${militestatisticheInstance?.milite?.encodeAsHTML()}</g:link></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="status-label" class="property-label"><g:message
                    code="militestatistiche.status.labelform" default="Status"/></span>
            
            <span class="property-value" aria-labelledby="status-label"><g:fieldValue bean="${militestatisticheInstance}"
                                                                                         field="status"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="turni-label" class="property-label"><g:message
                    code="militestatistiche.turni.labelform" default="Turni"/></span>
            
            <span class="property-value" aria-labelledby="turni-label"><g:fieldValue bean="${militestatisticheInstance}"
                                                                                         field="turni"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="ore-label" class="property-label"><g:message
                    code="militestatistiche.ore.labelform" default="Ore"/></span>
            
            <span class="property-value" aria-labelledby="ore-label"><g:fieldValue bean="${militestatisticheInstance}"
                                                                                         field="ore"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="funz1-label" class="property-label"><g:message
                    code="militestatistiche.funz1.labelform" default="Funz1"/></span>
            
            <span class="property-value" aria-labelledby="funz1-label"><g:fieldValue bean="${militestatisticheInstance}"
                                                                                         field="funz1"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="funz2-label" class="property-label"><g:message
                    code="militestatistiche.funz2.labelform" default="Funz2"/></span>
            
            <span class="property-value" aria-labelledby="funz2-label"><g:fieldValue bean="${militestatisticheInstance}"
                                                                                         field="funz2"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="funz3-label" class="property-label"><g:message
                    code="militestatistiche.funz3.labelform" default="Funz3"/></span>
            
            <span class="property-value" aria-labelledby="funz3-label"><g:fieldValue bean="${militestatisticheInstance}"
                                                                                         field="funz3"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="funz4-label" class="property-label"><g:message
                    code="militestatistiche.funz4.labelform" default="Funz4"/></span>
            
            <span class="property-value" aria-labelledby="funz4-label"><g:fieldValue bean="${militestatisticheInstance}"
                                                                                         field="funz4"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="funz5-label" class="property-label"><g:message
                    code="militestatistiche.funz5.labelform" default="Funz5"/></span>
            
            <span class="property-value" aria-labelledby="funz5-label"><g:fieldValue bean="${militestatisticheInstance}"
                                                                                         field="funz5"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="funz6-label" class="property-label"><g:message
                    code="militestatistiche.funz6.labelform" default="Funz6"/></span>
            
            <span class="property-value" aria-labelledby="funz6-label"><g:fieldValue bean="${militestatisticheInstance}"
                                                                                         field="funz6"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="funz7-label" class="property-label"><g:message
                    code="militestatistiche.funz7.labelform" default="Funz7"/></span>
            
            <span class="property-value" aria-labelledby="funz7-label"><g:fieldValue bean="${militestatisticheInstance}"
                                                                                         field="funz7"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="funz8-label" class="property-label"><g:message
                    code="militestatistiche.funz8.labelform" default="Funz8"/></span>
            
            <span class="property-value" aria-labelledby="funz8-label"><g:fieldValue bean="${militestatisticheInstance}"
                                                                                         field="funz8"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="funz9-label" class="property-label"><g:message
                    code="militestatistiche.funz9.labelform" default="Funz9"/></span>
            
            <span class="property-value" aria-labelledby="funz9-label"><g:fieldValue bean="${militestatisticheInstance}"
                                                                                         field="funz9"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="funz10-label" class="property-label"><g:message
                    code="militestatistiche.funz10.labelform" default="Funz10"/></span>
            
            <span class="property-value" aria-labelledby="funz10-label"><g:fieldValue bean="${militestatisticheInstance}"
                                                                                         field="funz10"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="funz11-label" class="property-label"><g:message
                    code="militestatistiche.funz11.labelform" default="Funz11"/></span>
            
            <span class="property-value" aria-labelledby="funz11-label"><g:fieldValue bean="${militestatisticheInstance}"
                                                                                         field="funz11"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="funz12-label" class="property-label"><g:message
                    code="militestatistiche.funz12.labelform" default="Funz12"/></span>
            
            <span class="property-value" aria-labelledby="funz12-label"><g:fieldValue bean="${militestatisticheInstance}"
                                                                                         field="funz12"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="funz13-label" class="property-label"><g:message
                    code="militestatistiche.funz13.labelform" default="Funz13"/></span>
            
            <span class="property-value" aria-labelledby="funz13-label"><g:fieldValue bean="${militestatisticheInstance}"
                                                                                         field="funz13"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="funz14-label" class="property-label"><g:message
                    code="militestatistiche.funz14.labelform" default="Funz14"/></span>
            
            <span class="property-value" aria-labelledby="funz14-label"><g:fieldValue bean="${militestatisticheInstance}"
                                                                                         field="funz14"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="funz15-label" class="property-label"><g:message
                    code="militestatistiche.funz15.labelform" default="Funz15"/></span>
            
            <span class="property-value" aria-labelledby="funz15-label"><g:fieldValue bean="${militestatisticheInstance}"
                                                                                         field="funz15"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="funz16-label" class="property-label"><g:message
                    code="militestatistiche.funz16.labelform" default="Funz16"/></span>
            
            <span class="property-value" aria-labelledby="funz16-label"><g:fieldValue bean="${militestatisticheInstance}"
                                                                                         field="funz16"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="funz17-label" class="property-label"><g:message
                    code="militestatistiche.funz17.labelform" default="Funz17"/></span>
            
            <span class="property-value" aria-labelledby="funz17-label"><g:fieldValue bean="${militestatisticheInstance}"
                                                                                         field="funz17"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="funz18-label" class="property-label"><g:message
                    code="militestatistiche.funz18.labelform" default="Funz18"/></span>
            
            <span class="property-value" aria-labelledby="funz18-label"><g:fieldValue bean="${militestatisticheInstance}"
                                                                                         field="funz18"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="funz19-label" class="property-label"><g:message
                    code="militestatistiche.funz19.labelform" default="Funz19"/></span>
            
            <span class="property-value" aria-labelledby="funz19-label"><g:fieldValue bean="${militestatisticheInstance}"
                                                                                         field="funz19"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="funz20-label" class="property-label"><g:message
                    code="militestatistiche.funz20.labelform" default="Funz20"/></span>
            
            <span class="property-value" aria-labelledby="funz20-label"><g:fieldValue bean="${militestatisticheInstance}"
                                                                                         field="funz20"/></span>
            
        </li>
        
    </ol>

    <g:if test="${campiExtra}">
        <amb:extraScheda rec="${militestatisticheInstance}" campiExtra="${campiExtra}"></amb:extraScheda>
    </g:if>

    <g:form>
        <fieldset class="buttons">
            <g:hiddenField name="id" value="${militestatisticheInstance?.id}"/>
            <g:link class="edit" action="edit" id="${militestatisticheInstance?.id}">
                <g:message code="militestatistiche.edit.label"
                           default="Modifica militestatistiche"/>
            </g:link>
            <g:actionSubmit class="delete" action="delete"
                            value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                            onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
