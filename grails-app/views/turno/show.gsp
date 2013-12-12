<!--Created by Algos s.r.l.-->
<!--Date: mag 2012-->
<!--Questo file è stato installato dal plugin AlgosBase-->
<!--Tipicamente NON verrà più sovrascritto dalle successive release del plugin-->
<!--in quanto POTREBBE essere personalizzato in questa applicazione-->
<!--Se vuoi che le prossime release del plugin sovrascrivano questo file,-->
<!--perdendo tutte le modifiche effettuate qui,-->
<!--regola a true il flag di controllo flagOverwrite©-->
<!--flagOverwrite = false-->


<%@ page import="webambulanze.Turno" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'turno.label', default: 'Turno')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<amb:titoloPagina></amb:titoloPagina>
<a href="#show-turno" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                                  default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><g:link class="home" controller="Gen" action="home"><g:message code="ambulanze.home.label"/></g:link></li>
        <li><g:link class="list" action="list">
            <g:message code="turno.list.label"
                       default="Elenco turno"/>
        </g:link></li>

        <li><g:link class="create" action="create">
            <g:message code="turno.new.label"
                       default="Nuovo turno"/>
        </g:link></li>

        <li><g:link class="edit" action="edit" id="${turnoInstance?.id}">
            <g:message code="turno.edit.label" default="Modifica turno"/>
        </g:link></li>
        <sec:ifNotLoggedIn>
            <li><g:link class="login" controller="login">Login</g:link></li>
        </sec:ifNotLoggedIn>
        <sec:ifLoggedIn>
            <li><g:link class="logout" controller="logout">Logout</g:link></li>
        </sec:ifLoggedIn>
    </ul>
</div>

<div id="show-turno" class="content scaffold-show" role="main">
    <h1><g:message code="turno.show.label" default="Mostra turno"/></h1>
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
    <ol class="property-list turno">
        
        <li class="fieldcontain">
            <span id="croce-label" class="property-label"><g:message
                    code="turno.croce.labelform" default="Croce"/></span>
            
            <span class="property-value" aria-labelledby="croce-label"><g:link
                    controller="croce" action="show"
                    id="${turnoInstance?.croce?.id}">${turnoInstance?.croce?.encodeAsHTML()}</g:link></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="tipoTurno-label" class="property-label"><g:message
                    code="turno.tipoTurno.labelform" default="Tipo Turno"/></span>
            
            <span class="property-value" aria-labelledby="tipoTurno-label"><g:link
                    controller="tipoTurno" action="show"
                    id="${turnoInstance?.tipoTurno?.id}">${turnoInstance?.tipoTurno?.encodeAsHTML()}</g:link></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="giorno-label" class="property-label"><g:message
                    code="turno.giorno.labelform" default="Giorno"/></span>
            
            <span class="property-value" aria-labelledby="giorno-label"><amb:formatDate
                    date="${turnoInstance?.giorno}"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="inizio-label" class="property-label"><g:message
                    code="turno.inizio.labelform" default="Inizio"/></span>
            
            <span class="property-value" aria-labelledby="inizio-label"><amb:formatDate
                    date="${turnoInstance?.inizio}"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="fine-label" class="property-label"><g:message
                    code="turno.fine.labelform" default="Fine"/></span>
            
            <span class="property-value" aria-labelledby="fine-label"><amb:formatDate
                    date="${turnoInstance?.fine}"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="funzione1-label" class="property-label"><g:message
                    code="turno.funzione1.labelform" default="Funzione1"/></span>
            
            <span class="property-value" aria-labelledby="funzione1-label"><g:link
                    controller="funzione" action="show"
                    id="${turnoInstance?.funzione1?.id}">${turnoInstance?.funzione1?.encodeAsHTML()}</g:link></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="funzione2-label" class="property-label"><g:message
                    code="turno.funzione2.labelform" default="Funzione2"/></span>
            
            <span class="property-value" aria-labelledby="funzione2-label"><g:link
                    controller="funzione" action="show"
                    id="${turnoInstance?.funzione2?.id}">${turnoInstance?.funzione2?.encodeAsHTML()}</g:link></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="funzione3-label" class="property-label"><g:message
                    code="turno.funzione3.labelform" default="Funzione3"/></span>
            
            <span class="property-value" aria-labelledby="funzione3-label"><g:link
                    controller="funzione" action="show"
                    id="${turnoInstance?.funzione3?.id}">${turnoInstance?.funzione3?.encodeAsHTML()}</g:link></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="funzione4-label" class="property-label"><g:message
                    code="turno.funzione4.labelform" default="Funzione4"/></span>
            
            <span class="property-value" aria-labelledby="funzione4-label"><g:link
                    controller="funzione" action="show"
                    id="${turnoInstance?.funzione4?.id}">${turnoInstance?.funzione4?.encodeAsHTML()}</g:link></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="militeFunzione1-label" class="property-label"><g:message
                    code="turno.militeFunzione1.labelform" default="Milite Funzione1"/></span>
            
            <span class="property-value" aria-labelledby="militeFunzione1-label"><g:link
                    controller="milite" action="show"
                    id="${turnoInstance?.militeFunzione1?.id}">${turnoInstance?.militeFunzione1?.encodeAsHTML()}</g:link></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="militeFunzione2-label" class="property-label"><g:message
                    code="turno.militeFunzione2.labelform" default="Milite Funzione2"/></span>
            
            <span class="property-value" aria-labelledby="militeFunzione2-label"><g:link
                    controller="milite" action="show"
                    id="${turnoInstance?.militeFunzione2?.id}">${turnoInstance?.militeFunzione2?.encodeAsHTML()}</g:link></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="militeFunzione3-label" class="property-label"><g:message
                    code="turno.militeFunzione3.labelform" default="Milite Funzione3"/></span>
            
            <span class="property-value" aria-labelledby="militeFunzione3-label"><g:link
                    controller="milite" action="show"
                    id="${turnoInstance?.militeFunzione3?.id}">${turnoInstance?.militeFunzione3?.encodeAsHTML()}</g:link></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="militeFunzione4-label" class="property-label"><g:message
                    code="turno.militeFunzione4.labelform" default="Milite Funzione4"/></span>
            
            <span class="property-value" aria-labelledby="militeFunzione4-label"><g:link
                    controller="milite" action="show"
                    id="${turnoInstance?.militeFunzione4?.id}">${turnoInstance?.militeFunzione4?.encodeAsHTML()}</g:link></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="modificaFunzione1-label" class="property-label"><g:message
                    code="turno.modificaFunzione1.labelform" default="Modifica Funzione1"/></span>
            
            <span class="property-value" aria-labelledby="modificaFunzione1-label"><g:fieldValue bean="${turnoInstance}"
                                                                                         field="modificaFunzione1"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="modificaFunzione2-label" class="property-label"><g:message
                    code="turno.modificaFunzione2.labelform" default="Modifica Funzione2"/></span>
            
            <span class="property-value" aria-labelledby="modificaFunzione2-label"><g:fieldValue bean="${turnoInstance}"
                                                                                         field="modificaFunzione2"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="modificaFunzione3-label" class="property-label"><g:message
                    code="turno.modificaFunzione3.labelform" default="Modifica Funzione3"/></span>
            
            <span class="property-value" aria-labelledby="modificaFunzione3-label"><g:fieldValue bean="${turnoInstance}"
                                                                                         field="modificaFunzione3"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="modificaFunzione4-label" class="property-label"><g:message
                    code="turno.modificaFunzione4.labelform" default="Modifica Funzione4"/></span>
            
            <span class="property-value" aria-labelledby="modificaFunzione4-label"><g:fieldValue bean="${turnoInstance}"
                                                                                         field="modificaFunzione4"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="oreMilite1-label" class="property-label"><g:message
                    code="turno.oreMilite1.labelform" default="Ore Milite1"/></span>
            
            <span class="property-value" aria-labelledby="oreMilite1-label"><g:fieldValue bean="${turnoInstance}"
                                                                                         field="oreMilite1"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="oreMilite2-label" class="property-label"><g:message
                    code="turno.oreMilite2.labelform" default="Ore Milite2"/></span>
            
            <span class="property-value" aria-labelledby="oreMilite2-label"><g:fieldValue bean="${turnoInstance}"
                                                                                         field="oreMilite2"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="oreMilite3-label" class="property-label"><g:message
                    code="turno.oreMilite3.labelform" default="Ore Milite3"/></span>
            
            <span class="property-value" aria-labelledby="oreMilite3-label"><g:fieldValue bean="${turnoInstance}"
                                                                                         field="oreMilite3"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="oreMilite4-label" class="property-label"><g:message
                    code="turno.oreMilite4.labelform" default="Ore Milite4"/></span>
            
            <span class="property-value" aria-labelledby="oreMilite4-label"><g:fieldValue bean="${turnoInstance}"
                                                                                         field="oreMilite4"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="problemiFunzione1-label" class="property-label"><g:message
                    code="turno.problemiFunzione1.labelform" default="Problemi Funzione1"/></span>
            
            <span class="property-value" aria-labelledby="problemiFunzione1-label"><g:formatBoolean
                    boolean="${turnoInstance?.problemiFunzione1}"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="problemiFunzione2-label" class="property-label"><g:message
                    code="turno.problemiFunzione2.labelform" default="Problemi Funzione2"/></span>
            
            <span class="property-value" aria-labelledby="problemiFunzione2-label"><g:formatBoolean
                    boolean="${turnoInstance?.problemiFunzione2}"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="problemiFunzione3-label" class="property-label"><g:message
                    code="turno.problemiFunzione3.labelform" default="Problemi Funzione3"/></span>
            
            <span class="property-value" aria-labelledby="problemiFunzione3-label"><g:formatBoolean
                    boolean="${turnoInstance?.problemiFunzione3}"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="problemiFunzione4-label" class="property-label"><g:message
                    code="turno.problemiFunzione4.labelform" default="Problemi Funzione4"/></span>
            
            <span class="property-value" aria-labelledby="problemiFunzione4-label"><g:formatBoolean
                    boolean="${turnoInstance?.problemiFunzione4}"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="titoloExtra-label" class="property-label"><g:message
                    code="turno.titoloExtra.labelform" default="Titolo Extra"/></span>
            
            <span class="property-value" aria-labelledby="titoloExtra-label"><g:fieldValue bean="${turnoInstance}"
                                                                                         field="titoloExtra"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="localitàExtra-label" class="property-label"><g:message
                    code="turno.localitàExtra.labelform" default="Località Extra"/></span>
            
            <span class="property-value" aria-labelledby="localitàExtra-label"><g:fieldValue bean="${turnoInstance}"
                                                                                         field="localitàExtra"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="note-label" class="property-label"><g:message
                    code="turno.note.labelform" default="Note"/></span>
            
            <span class="property-value" aria-labelledby="note-label"><g:fieldValue bean="${turnoInstance}"
                                                                                         field="note"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="assegnato-label" class="property-label"><g:message
                    code="turno.assegnato.labelform" default="Assegnato"/></span>
            
            <span class="property-value" aria-labelledby="assegnato-label"><g:formatBoolean
                    boolean="${turnoInstance?.assegnato}"/></span>
            
        </li>
        
    </ol>

    <g:if test="${campiExtra}">
        <amb:extraScheda rec="${turnoInstance}" campiExtra="${campiExtra}"></amb:extraScheda>
    </g:if>

    <g:form>
        <fieldset class="buttons">
            <g:hiddenField name="id" value="${turnoInstance?.id}"/>
            <g:link class="edit" action="edit" id="${turnoInstance?.id}">
                <g:message code="turno.edit.label"
                           default="Modifica turno"/>
            </g:link>
            <g:actionSubmit class="delete" action="delete"
                            value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                            onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
