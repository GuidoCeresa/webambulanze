<!--Created by Algos s.r.l.-->
<!--Date: mag 2012-->
<!--Questo file è stato installato dal plugin AlgosBase-->
<!--Tipicamente NON verrà più sovrascritto dalle successive release del plugin-->
<!--in quanto POTREBBE essere personalizzato in questa applicazione-->
<!--Se vuoi che le prossime release del plugin sovrascrivano questo file,-->
<!--perdendo tutte le modifiche effettuate qui,-->
<!--regola a true il flag di controllo flagOverwrite©-->
<!--flagOverwrite = false-->


<%@ page import="webambulanze.Milite" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'milite.label', default: 'Milite')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<amb:titoloPagina></amb:titoloPagina>
<a href="#show-milite" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                                  default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><g:link class="home" controller="Gen" action="home"><g:message code="ambulanze.home.label"/></g:link></li>
        <li><g:link class="list" action="list">
            <g:message code="milite.list.label"
                       default="Elenco milite"/>
        </g:link></li>

        <li><g:link class="create" action="create">
            <g:message code="milite.new.label"
                       default="Nuovo milite"/>
        </g:link></li>

        <li><g:link class="edit" action="edit" id="${militeInstance?.id}">
            <g:message code="milite.edit.label" default="Modifica milite"/>
        </g:link></li>
        <sec:ifNotLoggedIn>
            <li><g:link class="login" controller="login">Login</g:link></li>
        </sec:ifNotLoggedIn>
        <sec:ifLoggedIn>
            <li><g:link class="logout" controller="logout">Logout</g:link></li>
        </sec:ifLoggedIn>
    </ul>
</div>

<div id="show-milite" class="content scaffold-show" role="main">
    <h1><g:message code="milite.show.label" default="Mostra milite"/></h1>
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
    <ol class="property-list milite">
        
        <li class="fieldcontain">
            <span id="croce-label" class="property-label"><g:message
                    code="milite.croce.labelform" default="Croce"/></span>
            
            <span class="property-value" aria-labelledby="croce-label"><g:link
                    controller="croce" action="show"
                    id="${militeInstance?.croce?.id}">${militeInstance?.croce?.encodeAsHTML()}</g:link></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="cognome-label" class="property-label"><g:message
                    code="milite.cognome.labelform" default="Cognome"/></span>
            
            <span class="property-value" aria-labelledby="cognome-label"><g:fieldValue bean="${militeInstance}"
                                                                                         field="cognome"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="nome-label" class="property-label"><g:message
                    code="milite.nome.labelform" default="Nome"/></span>
            
            <span class="property-value" aria-labelledby="nome-label"><g:fieldValue bean="${militeInstance}"
                                                                                         field="nome"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="dataNascita-label" class="property-label"><g:message
                    code="milite.dataNascita.labelform" default="Data Nascita"/></span>
            
            <span class="property-value" aria-labelledby="dataNascita-label"><amb:formatDate
                    date="${militeInstance?.dataNascita}"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="telefonoCellulare-label" class="property-label"><g:message
                    code="milite.telefonoCellulare.labelform" default="Telefono Cellulare"/></span>
            
            <span class="property-value" aria-labelledby="telefonoCellulare-label"><g:fieldValue bean="${militeInstance}"
                                                                                         field="telefonoCellulare"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="telefonoFisso-label" class="property-label"><g:message
                    code="milite.telefonoFisso.labelform" default="Telefono Fisso"/></span>
            
            <span class="property-value" aria-labelledby="telefonoFisso-label"><g:fieldValue bean="${militeInstance}"
                                                                                         field="telefonoFisso"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="email-label" class="property-label"><g:message
                    code="milite.email.labelform" default="Email"/></span>
            
            <span class="property-value" aria-labelledby="email-label"><g:fieldValue bean="${militeInstance}"
                                                                                         field="email"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="scadenzaBLSD-label" class="property-label"><g:message
                    code="milite.scadenzaBLSD.labelform" default="Scadenza BLSD"/></span>
            
            <span class="property-value" aria-labelledby="scadenzaBLSD-label"><amb:formatDate
                    date="${militeInstance?.scadenzaBLSD}"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="scadenzaTrauma-label" class="property-label"><g:message
                    code="milite.scadenzaTrauma.labelform" default="Scadenza Trauma"/></span>
            
            <span class="property-value" aria-labelledby="scadenzaTrauma-label"><amb:formatDate
                    date="${militeInstance?.scadenzaTrauma}"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="scadenzaNonTrauma-label" class="property-label"><g:message
                    code="milite.scadenzaNonTrauma.labelform" default="Scadenza Non Trauma"/></span>
            
            <span class="property-value" aria-labelledby="scadenzaNonTrauma-label"><amb:formatDate
                    date="${militeInstance?.scadenzaNonTrauma}"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="note-label" class="property-label"><g:message
                    code="milite.note.labelform" default="Note"/></span>
            
            <span class="property-value" aria-labelledby="note-label"><g:fieldValue bean="${militeInstance}"
                                                                                         field="note"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="dipendente-label" class="property-label"><g:message
                    code="milite.dipendente.labelform" default="Dipendente"/></span>
            
            <span class="property-value" aria-labelledby="dipendente-label"><g:formatBoolean
                    boolean="${militeInstance?.dipendente}"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="attivo-label" class="property-label"><g:message
                    code="milite.attivo.labelform" default="Attivo"/></span>
            
            <span class="property-value" aria-labelledby="attivo-label"><g:formatBoolean
                    boolean="${militeInstance?.attivo}"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="oreAnno-label" class="property-label"><g:message
                    code="milite.oreAnno.labelform" default="Ore Anno"/></span>
            
            <span class="property-value" aria-labelledby="oreAnno-label"><g:fieldValue bean="${militeInstance}"
                                                                                         field="oreAnno"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="turniAnno-label" class="property-label"><g:message
                    code="milite.turniAnno.labelform" default="Turni Anno"/></span>
            
            <span class="property-value" aria-labelledby="turniAnno-label"><g:fieldValue bean="${militeInstance}"
                                                                                         field="turniAnno"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="oreExtra-label" class="property-label"><g:message
                    code="milite.oreExtra.labelform" default="Ore Extra"/></span>
            
            <span class="property-value" aria-labelledby="oreExtra-label"><g:fieldValue bean="${militeInstance}"
                                                                                         field="oreExtra"/></span>
            
        </li>
        
    </ol>

    <g:if test="${campiExtra}">
        <amb:extraScheda rec="${militeInstance}" campiExtra="${campiExtra}"></amb:extraScheda>
    </g:if>

    <g:form>
        <fieldset class="buttons">
            <g:hiddenField name="id" value="${militeInstance?.id}"/>
            <g:link class="edit" action="edit" id="${militeInstance?.id}">
                <g:message code="milite.edit.label"
                           default="Modifica milite"/>
            </g:link>
            <g:actionSubmit class="delete" action="delete"
                            value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                            onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
