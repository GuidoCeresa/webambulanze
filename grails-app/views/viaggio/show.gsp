<!--Created by Algos s.r.l.-->
<!--Date: mag 2012-->
<!--Questo file è stato installato dal plugin AlgosBase-->
<!--Tipicamente NON verrà più sovrascritto dalle successive release del plugin-->
<!--in quanto POTREBBE essere personalizzato in questa applicazione-->
<!--Se vuoi che le prossime release del plugin sovrascrivano questo file,-->
<!--perdendo tutte le modifiche effettuate qui,-->
<!--regola a true il flag di controllo flagOverwrite©-->
<!--flagOverwrite = false-->


<%@ page import="webambulanze.Viaggio" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'viaggio.label', default: 'Viaggio')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<amb:titoloPagina></amb:titoloPagina>
<a href="#show-viaggio" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                              default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><g:link class="home" controller="Gen" action="home"><g:message code="ambulanze.home.label"/></g:link></li>
        <li><g:link class="list" action="list">
            <g:message code="viaggio.list.label"
                       default="Elenco viaggio"/>
        </g:link></li>

        <li><g:link class="create" action="create">
            <g:message code="viaggio.new.label"
                       default="Nuovo viaggio"/>
        </g:link></li>

        <li><g:link class="edit" action="edit" id="${viaggioInstance?.id}">
            <g:message code="viaggio.edit.label" default="Modifica viaggio"/>
        </g:link></li>
        <sec:ifNotLoggedIn>
            <li><g:link class="login" controller="login">Login</g:link></li>
        </sec:ifNotLoggedIn>
        <sec:ifLoggedIn>
            <li><g:link class="logout" controller="logout">Logout</g:link></li>
        </sec:ifLoggedIn>
    </ul>
</div>

<div id="show-viaggio" class="content scaffold-show" role="main">
<h1><g:message code="viaggio.show.label" default="Mostra viaggio"/></h1>
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
<ol class="property-list viaggio">

<li class="fieldcontain">
    <span id="giorno-label" class="property-label"><g:message
            code="viaggio.giorno.labelform" default="Giorno"/></span>
    <span class="property-value" aria-labelledby="giorno-label"><amb:formatDate
            date="${viaggioInstance?.giorno}"/></span>
</li>

<li class="fieldcontain">
    <span id="turno-label" class="property-label"><g:message
            code="viaggio.turno.labelform" default="Turno"/></span>
    <span class="property-value" aria-labelledby="turno-label"><g:link
            controller="turno" action="show"
            id="${viaggioInstance?.turno?.id}">${viaggioInstance?.turno?.encodeAsHTML()}</g:link></span>
</li>

<li class="fieldcontain">
    <span id="automezzo-label" class="property-label"><g:message
            code="viaggio.automezzo.labelform" default="Automezzo"/></span>
    <span class="property-value" aria-labelledby="automezzo-label"><g:link
            controller="automezzo" action="show"
            id="${viaggioInstance?.automezzo?.id}">${viaggioInstance?.automezzo?.encodeAsHTML()}</g:link></span>
</li>

<li class="fieldcontain">
    <span id="chilometriPartenza-label" class="property-label"><g:message
            code="viaggio.chilometriPartenza.labelform" default="Chilometri Partenza"/></span>
    <span class="property-value" aria-labelledby="chilometriPartenza-label"><g:fieldValue bean="${viaggioInstance}"
                                                                                          field="chilometriPartenza"/></span>
</li>

<li class="fieldcontain">
    <span id="chilometriArrivo-label" class="property-label"><g:message
            code="viaggio.chilometriArrivo.labelform" default="Chilometri Arrivo"/></span>
    <span class="property-value" aria-labelledby="chilometriArrivo-label"><g:fieldValue bean="${viaggioInstance}"
                                                                                        field="chilometriArrivo"/></span>
</li>

<li class="fieldcontain">
    <span id="chilometriPercorsi-label" class="property-label"><g:message
            code="viaggio.chilometriPercorsi.labelform" default="Chilometri Percorsi"/></span>
    <span class="property-value" aria-labelledby="chilometriPercorsi-label"><g:fieldValue bean="${viaggioInstance}"
                                                                                          field="chilometriPercorsi"/></span>
</li>

<li class="fieldcontain">
    <span id="inizio-label" class="property-label"><g:message
            code="viaggio.inizio.labelform" default="Inizio"/></span>
    <span class="property-value" aria-labelledby="inizio-label"><amb:formatTempo
            date="${viaggioInstance?.inizio}"/></span>
</li>

<li class="fieldcontain">
    <span id="codiceInvio-label" class="property-label"><g:message
            code="viaggio.codiceInvio.labelform" default="Codice Invio"/></span>
    <span class="property-value" aria-labelledby="codiceInvio-label"><g:fieldValue bean="${viaggioInstance}"
                                                                                   field="codiceInvio"/></span>
</li>

<li class="fieldcontain">
    <span id="luogoEvento-label" class="property-label"><g:message
            code="viaggio.luogoEvento.labelform" default="Luogo Evento"/></span>
    <span class="property-value" aria-labelledby="luogoEvento-label"><g:fieldValue bean="${viaggioInstance}"
                                                                                   field="luogoEvento"/></span>
</li>

<li class="fieldcontain">
    <span id="patologia-label" class="property-label"><g:message
            code="viaggio.patologia.labelform" default="Patologia"/></span>
    <span class="property-value" aria-labelledby="patologia-label"><g:fieldValue bean="${viaggioInstance}"
                                                                                 field="patologia"/></span>
</li>

<li class="fieldcontain">
    <span id="codiceRicovero-label" class="property-label"><g:message
            code="viaggio.codiceRicovero.labelform" default="Codice Ricovero"/></span>
    <span class="property-value" aria-labelledby="codiceRicovero-label"><g:fieldValue bean="${viaggioInstance}"
                                                                                      field="codiceRicovero"/></span>
</li>

<li class="fieldcontain">
    <span id="fine-label" class="property-label"><g:message
            code="viaggio.fine.labelform" default="Fine"/></span>
    <span class="property-value" aria-labelledby="fine-label"><amb:formatTempo
            date="${viaggioInstance?.fine}"/></span>
</li>

<li class="fieldcontain">
    <span id="numeroCartellino-label" class="property-label"><g:message
            code="viaggio.numeroCartellino.labelform" default="Numero Cartellino"/></span>
    <span class="property-value" aria-labelledby="numeroCartellino-label"><g:fieldValue bean="${viaggioInstance}"
                                                                                        field="numeroCartellino"/></span>
</li>

<li class="fieldcontain">
    <span id="nomePaziente-label" class="property-label"><g:message
            code="viaggio.nomePaziente.labelform" default="Nome Paziente"/></span>
    <span class="property-value" aria-labelledby="nomePaziente-label"><g:fieldValue bean="${viaggioInstance}"
                                                                                    field="nomePaziente"/></span>
</li>
<li class="fieldcontain">
    <span id="indirizzoPaziente-label" class="property-label"><g:message
            code="viaggio.indirizzoPaziente.labelform" default="Indirizzo Paziente"/></span>
    <span class="property-value" aria-labelledby="indirizzoPaziente-label"><g:fieldValue bean="${viaggioInstance}"
                                                                                         field="indirizzoPaziente"/></span>
</li>
<li class="fieldcontain">
    <span id="cittaPaziente-label" class="property-label"><g:message
            code="viaggio.cittaPaziente.labelform" default="Citta Paziente"/></span>
    <span class="property-value" aria-labelledby="cittaPaziente-label"><g:fieldValue bean="${viaggioInstance}"
                                                                                     field="cittaPaziente"/></span>
</li>

<li class="fieldcontain">
    <span id="etaPaziente-label" class="property-label"><g:message
            code="viaggio.etaPaziente.labelform" default="Eta Paziente"/></span>
    <span class="property-value" aria-labelledby="etaPaziente-label"><g:fieldValue bean="${viaggioInstance}"
                                                                                   field="etaPaziente"/></span>
</li>

<li class="fieldcontain">
    <span id="prelievo-label" class="property-label"><g:message
            code="viaggio.prelievo.labelform" default="Prelievo"/></span>
    <span class="property-value" aria-labelledby="prelievo-label"><g:fieldValue bean="${viaggioInstance}"
                                                                                field="prelievo"/></span>
</li>

<li class="fieldcontain">
    <span id="ricovero-label" class="property-label"><g:message
            code="viaggio.ricovero.labelform" default="Ricovero"/></span>
    <span class="property-value" aria-labelledby="ricovero-label"><g:fieldValue bean="${viaggioInstance}"
                                                                                field="ricovero"/></span>
</li>

<li class="fieldcontain">
    <span id="numeroBolla-label" class="property-label"><g:message
            code="viaggio.numeroBolla.labelform" default="Numero Bolla"/></span>
    <span class="property-value" aria-labelledby="numeroBolla-label"><g:fieldValue bean="${viaggioInstance}"
                                                                                   field="numeroBolla"/></span>
</li>

<li class="fieldcontain">
    <span id="numeroServizio-label" class="property-label"><g:message
            code="viaggio.numeroServizio.labelform" default="Numero Servizio"/></span>
    <span class="property-value" aria-labelledby="numeroServizio-label"><g:fieldValue bean="${viaggioInstance}"
                                                                                      field="numeroServizio"/></span>
</li>

<li class="fieldcontain">
    <span id="numeroViaggio-label" class="property-label"><g:message
            code="viaggio.numeroViaggio.labelform" default="Numero Viaggio"/></span>
    <span class="property-value" aria-labelledby="numeroViaggio-label"><g:fieldValue bean="${viaggioInstance}"
                                                                                     field="numeroViaggio"/></span>
</li>

<li class="fieldcontain">
    <span id="militeFunzione1-label" class="property-label"><g:message
            code="viaggio.militeFunzione1.labelform" default="Autista Emergenza"/></span>
    <span class="property-value" aria-labelledby="militeFunzione1-label"><g:link
            controller="milite" action="show"
            id="${viaggioInstance?.militeFunzione1?.id}">${viaggioInstance?.militeFunzione1?.encodeAsHTML()}</g:link></span>
</li>

<li class="fieldcontain">
    <span id="militeFunzione2-label" class="property-label"><g:message
            code="viaggio.militeFunzione2.labelform" default="Soccorritore Dae"/></span>
    <span class="property-value" aria-labelledby="militeFunzione2-label"><g:link
            controller="milite" action="show"
            id="${viaggioInstance?.militeFunzione2?.id}">${viaggioInstance?.militeFunzione2?.encodeAsHTML()}</g:link></span>
</li>

<li class="fieldcontain">
    <span id="militeFunzione3-label" class="property-label"><g:message
            code="viaggio.militeFunzione3.labelform" default="Soccorritore"/></span>
    <span class="property-value" aria-labelledby="militeFunzione3-label"><g:link
            controller="milite" action="show"
            id="${viaggioInstance?.militeFunzione3?.id}">${viaggioInstance?.militeFunzione3?.encodeAsHTML()}</g:link></span>
</li>

<li class="fieldcontain">
    <span id="militeFunzione4-label" class="property-label"><g:message
            code="viaggio.militeFunzione4.labelform" default="Barelliere Affiancamento"/></span>
    <span class="property-value" aria-labelledby="militeFunzione4-label"><g:link
            controller="milite" action="show"
            id="${viaggioInstance?.militeFunzione4?.id}">${viaggioInstance?.militeFunzione4?.encodeAsHTML()}</g:link></span>
</li>

</ol>

<g:if test="${campiExtra}">
    <amb:extraScheda rec="${viaggioInstance}" campiExtra="${campiExtra}"> </amb:extraScheda>
</g:if>

<g:form>
    <fieldset class="buttons">
        <g:hiddenField name="id" value="${viaggioInstance?.id}"/>
        <g:link class="edit" action="edit" id="${viaggioInstance?.id}">
            <g:message code="viaggio.edit.label"
                       default="Modifica viaggio"/>
        </g:link>
        <g:actionSubmit class="delete" action="delete"
                        value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                        onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
    </fieldset>
</g:form>
</div>
</body>
</html>
