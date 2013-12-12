<!--Created by Algos s.r.l.-->
<!--Date: mag 2012-->
<!--Questo file è stato installato dal plugin AlgosBase-->
<!--Tipicamente NON verrà più sovrascritto dalle successive release del plugin-->
<!--in quanto POTREBBE essere personalizzato in questa applicazione-->
<!--Se vuoi che le prossime release del plugin sovrascrivano questo file,-->
<!--perdendo tutte le modifiche effettuate qui,-->
<!--regola a true il flag di controllo flagOverwrite©-->
<!--flagOverwrite = false-->


<%@ page import="webambulanze.TipoTurno" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'tipoTurno.label', default: 'TipoTurno')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<amb:titoloPagina></amb:titoloPagina>
<a href="#show-tipoTurno" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                                  default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><g:link class="home" controller="Gen" action="home"><g:message code="ambulanze.home.label"/></g:link></li>
        <li><g:link class="list" action="list">
            <g:message code="tipoTurno.list.label"
                       default="Elenco tipoTurno"/>
        </g:link></li>

        <li><g:link class="create" action="create">
            <g:message code="tipoTurno.new.label"
                       default="Nuovo tipoTurno"/>
        </g:link></li>

        <li><g:link class="edit" action="edit" id="${tipoTurnoInstance?.id}">
            <g:message code="tipoTurno.edit.label" default="Modifica tipoTurno"/>
        </g:link></li>
        <sec:ifNotLoggedIn>
            <li><g:link class="login" controller="login">Login</g:link></li>
        </sec:ifNotLoggedIn>
        <sec:ifLoggedIn>
            <li><g:link class="logout" controller="logout">Logout</g:link></li>
        </sec:ifLoggedIn>
    </ul>
</div>

<div id="show-tipoTurno" class="content scaffold-show" role="main">
    <h1><g:message code="tipoTurno.show.label" default="Mostra tipoTurno"/></h1>
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
    <ol class="property-list tipoTurno">
        
        <li class="fieldcontain">
            <span id="croce-label" class="property-label"><g:message
                    code="tipoTurno.croce.labelform" default="Croce"/></span>
            
            <span class="property-value" aria-labelledby="croce-label"><g:link
                    controller="croce" action="show"
                    id="${tipoTurnoInstance?.croce?.id}">${tipoTurnoInstance?.croce?.encodeAsHTML()}</g:link></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="sigla-label" class="property-label"><g:message
                    code="tipoTurno.sigla.labelform" default="Sigla"/></span>
            
            <span class="property-value" aria-labelledby="sigla-label"><g:fieldValue bean="${tipoTurnoInstance}"
                                                                                         field="sigla"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="descrizione-label" class="property-label"><g:message
                    code="tipoTurno.descrizione.labelform" default="Descrizione"/></span>
            
            <span class="property-value" aria-labelledby="descrizione-label"><g:fieldValue bean="${tipoTurnoInstance}"
                                                                                         field="descrizione"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="ordine-label" class="property-label"><g:message
                    code="tipoTurno.ordine.labelform" default="Ordine"/></span>
            
            <span class="property-value" aria-labelledby="ordine-label"><g:fieldValue bean="${tipoTurnoInstance}"
                                                                                         field="ordine"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="durata-label" class="property-label"><g:message
                    code="tipoTurno.durata.labelform" default="Durata"/></span>
            
            <span class="property-value" aria-labelledby="durata-label"><g:fieldValue bean="${tipoTurnoInstance}"
                                                                                         field="durata"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="oraInizio-label" class="property-label"><g:message
                    code="tipoTurno.oraInizio.labelform" default="Ora Inizio"/></span>
            
            <span class="property-value" aria-labelledby="oraInizio-label"><g:fieldValue bean="${tipoTurnoInstance}"
                                                                                         field="oraInizio"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="minutiInizio-label" class="property-label"><g:message
                    code="tipoTurno.minutiInizio.labelform" default="Minuti Inizio"/></span>
            
            <span class="property-value" aria-labelledby="minutiInizio-label"><g:fieldValue bean="${tipoTurnoInstance}"
                                                                                         field="minutiInizio"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="oraFine-label" class="property-label"><g:message
                    code="tipoTurno.oraFine.labelform" default="Ora Fine"/></span>
            
            <span class="property-value" aria-labelledby="oraFine-label"><g:fieldValue bean="${tipoTurnoInstance}"
                                                                                         field="oraFine"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="minutiFine-label" class="property-label"><g:message
                    code="tipoTurno.minutiFine.labelform" default="Minuti Fine"/></span>
            
            <span class="property-value" aria-labelledby="minutiFine-label"><g:fieldValue bean="${tipoTurnoInstance}"
                                                                                         field="minutiFine"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="primo-label" class="property-label"><g:message
                    code="tipoTurno.primo.labelform" default="Primo"/></span>
            
            <span class="property-value" aria-labelledby="primo-label"><g:formatBoolean
                    boolean="${tipoTurnoInstance?.primo}"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="fineGiornoSuccessivo-label" class="property-label"><g:message
                    code="tipoTurno.fineGiornoSuccessivo.labelform" default="Fine Giorno Successivo"/></span>
            
            <span class="property-value" aria-labelledby="fineGiornoSuccessivo-label"><g:formatBoolean
                    boolean="${tipoTurnoInstance?.fineGiornoSuccessivo}"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="visibile-label" class="property-label"><g:message
                    code="tipoTurno.visibile.labelform" default="Visibile"/></span>
            
            <span class="property-value" aria-labelledby="visibile-label"><g:formatBoolean
                    boolean="${tipoTurnoInstance?.visibile}"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="orario-label" class="property-label"><g:message
                    code="tipoTurno.orario.labelform" default="Orario"/></span>
            
            <span class="property-value" aria-labelledby="orario-label"><g:formatBoolean
                    boolean="${tipoTurnoInstance?.orario}"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="multiplo-label" class="property-label"><g:message
                    code="tipoTurno.multiplo.labelform" default="Multiplo"/></span>
            
            <span class="property-value" aria-labelledby="multiplo-label"><g:formatBoolean
                    boolean="${tipoTurnoInstance?.multiplo}"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="funzioniObbligatorie-label" class="property-label"><g:message
                    code="tipoTurno.funzioniObbligatorie.labelform" default="Funzioni Obbligatorie"/></span>
            
            <span class="property-value" aria-labelledby="funzioniObbligatorie-label"><g:fieldValue bean="${tipoTurnoInstance}"
                                                                                         field="funzioniObbligatorie"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="funzione1-label" class="property-label"><g:message
                    code="tipoTurno.funzione1.labelform" default="Funzione1"/></span>
            
            <span class="property-value" aria-labelledby="funzione1-label"><g:link
                    controller="funzione" action="show"
                    id="${tipoTurnoInstance?.funzione1?.id}">${tipoTurnoInstance?.funzione1?.encodeAsHTML()}</g:link></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="funzione2-label" class="property-label"><g:message
                    code="tipoTurno.funzione2.labelform" default="Funzione2"/></span>
            
            <span class="property-value" aria-labelledby="funzione2-label"><g:link
                    controller="funzione" action="show"
                    id="${tipoTurnoInstance?.funzione2?.id}">${tipoTurnoInstance?.funzione2?.encodeAsHTML()}</g:link></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="funzione3-label" class="property-label"><g:message
                    code="tipoTurno.funzione3.labelform" default="Funzione3"/></span>
            
            <span class="property-value" aria-labelledby="funzione3-label"><g:link
                    controller="funzione" action="show"
                    id="${tipoTurnoInstance?.funzione3?.id}">${tipoTurnoInstance?.funzione3?.encodeAsHTML()}</g:link></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="funzione4-label" class="property-label"><g:message
                    code="tipoTurno.funzione4.labelform" default="Funzione4"/></span>
            
            <span class="property-value" aria-labelledby="funzione4-label"><g:link
                    controller="funzione" action="show"
                    id="${tipoTurnoInstance?.funzione4?.id}">${tipoTurnoInstance?.funzione4?.encodeAsHTML()}</g:link></span>
            
        </li>
        
    </ol>

    <g:if test="${campiExtra}">
        <amb:extraScheda rec="${tipoTurnoInstance}" campiExtra="${campiExtra}"></amb:extraScheda>
    </g:if>

    <g:form>
        <fieldset class="buttons">
            <g:hiddenField name="id" value="${tipoTurnoInstance?.id}"/>
            <g:link class="edit" action="edit" id="${tipoTurnoInstance?.id}">
                <g:message code="tipoTurno.edit.label"
                           default="Modifica tipoTurno"/>
            </g:link>
            <g:actionSubmit class="delete" action="delete"
                            value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                            onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
