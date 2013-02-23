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
            <span id="giorno-label" class="property-label"><g:message
                    code="militestatistiche.giorno.labelform" default="Giorno"/></span>
            
            <span class="property-value" aria-labelledby="giorno-label"><amb:formatDate
                    date="${militestatisticheInstance?.giorno}"/></span>
            
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
