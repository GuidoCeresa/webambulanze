<%@ page import="webambulanze.Milite; webambulanze.Viaggio" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'viaggio.label', default: 'Viaggio')}"/>
    <title>118</title>
</head>

<body>
<amb:titoloPagina></amb:titoloPagina>
<a href="#create-viaggio" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><g:link class="home" controller="Gen" action="home"><g:message code="ambulanze.home.label"/></g:link></li>
        <li><g:link class="list" action="list">
            <g:message code="viaggio.list.label"
                       default="Elenco viaggio"/>
        </g:link></li>
    </ul>
</div>

<div id="create-viaggio" class="content scaffold-create" role="main">
<h1><g:message code="viaggio.create.label" default="Crea viaggio 118"/></h1>
<g:if test="${flash.message}">
    <div class="message" role="status">${flash.message}</div>
</g:if>
<g:if test="${flash.errors}">
    <div class="errors" role="status">${flash.errors}</div>
</g:if>
<g:hasErrors bean="${viaggioInstance}">
    <ul class="errors" role="alert">
        <g:eachError bean="${viaggioInstance}" var="error">
            <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                    error="${error}"/></li>
        </g:eachError>
    </ul>
</g:hasErrors>

<g:form action="save">
<fieldset class="form">
    <g:hiddenField name="automezzoId" value="${automezzoId}"/>

    <div class="fieldcontain">
        <label>Turno</label>
        <a href="/webambulanze/turno/show/${turnoId}">${siglaTurno}</a>
    </div>

    <div class="fieldcontain">
        <label>
            <g:message code="viaggio.automezzo.labelform" default="Automezzo"/>
        </label>
        <a href="/webambulanze/automezzo/show/${automezzoId}">${siglaAutomezzo}</a>
    </div>

    <div class="fieldcontain ${hasErrors(bean: viaggioInstance, field: 'chilometriPartenza', 'error')} required">
        <label for="chilometriPartenza">
            <g:message code="viaggio.chilometriPartenza.labelform" default="Chilometri Partenza"/>
            <span class="required-indicator">*</span>
        </label>
        <g:field name="chilometriPartenza" type="number" value="${viaggioInstance.chilometriPartenza}" required=""/>
    </div>

    <div class="fieldcontain ${hasErrors(bean: viaggioInstance, field: 'chilometriArrivo', 'error')} required">
        <label for="chilometriArrivo">
            <g:message code="viaggio.chilometriArrivo.labelform" default="Chilometri Arrivo"/>
            <span class="required-indicator">*</span>
        </label>
        <g:field name="chilometriArrivo" type="number" value="${viaggioInstance.chilometriArrivo}" required=""/>
    </div>

    <div class="fieldcontain ${hasErrors(bean: viaggioInstance, field: 'inizio', 'error')} required">
        <label for="inizio">
            <g:message code="viaggio.inizio.labelform" default="Inizio"/>
            <span class="required-indicator">*</span>
        </label>
        <g:datePicker name="inizio" precision="minute" value="${viaggioInstance?.inizio}"/>
    </div>

    <div class="fieldcontain ${hasErrors(bean: viaggioInstance, field: 'codiceInvio', 'error')} required">
        <label for="codiceInvio">
            <g:message code="viaggio.codiceInvio.labelform" default="Codice Invio"/>
            <span class="required-indicator">*</span>
        </label>
        <g:select name="codiceInvio" from="${webambulanze.CodiceInvio?.values()}"
                  keys="${webambulanze.CodiceInvio.values()*.name()}" required=""
                  value="${viaggioInstance?.codiceInvio?.name()}"/>
    </div>

    <div class="fieldcontain ${hasErrors(bean: viaggioInstance, field: 'luogoEvento', 'error')} required">
        <label for="luogoEvento">
            <g:message code="viaggio.luogoEvento.labelform" default="Luogo Evento"/>
            <span class="required-indicator">*</span>
        </label>
        <g:select name="luogoEvento" from="${webambulanze.LuogoEvento?.values()}"
                  keys="${webambulanze.LuogoEvento.values()*.name()}" required=""
                  value="${viaggioInstance?.luogoEvento?.name()}"/>
    </div>

    <div class="fieldcontain ${hasErrors(bean: viaggioInstance, field: 'patologia', 'error')} required">
        <label for="patologia">
            <g:message code="viaggio.patologia.labelform" default="Patologia"/>
            <span class="required-indicator">*</span>
        </label>
        <g:select name="patologia" from="${webambulanze.Patologia?.values()}"
                  keys="${webambulanze.Patologia.values()*.name()}" required=""
                  value="${viaggioInstance?.patologia?.name()}"/>
    </div>

    <div class="fieldcontain ${hasErrors(bean: viaggioInstance, field: 'codiceRicovero', 'error')} required">
        <label for="codiceRicovero">
            <g:message code="viaggio.codiceRicovero.labelform" default="Codice Ricovero"/>
            <span class="required-indicator">*</span>
        </label>
        <g:select name="codiceRicovero" from="${webambulanze.CodiceRicovero?.values()}"
                  keys="${webambulanze.CodiceRicovero.values()*.name()}" required=""
                  value="${viaggioInstance?.codiceRicovero?.name()}"/>
    </div>

    <div class="fieldcontain ${hasErrors(bean: viaggioInstance, field: 'fine', 'error')} required">
        <label for="fine">
            <g:message code="viaggio.fine.labelform" default="Fine"/>
            <span class="required-indicator">*</span>
        </label>
        <g:datePicker name="fine" precision="minute" value="${viaggioInstance?.fine}"/>
    </div>

    <div class="fieldcontain ${hasErrors(bean: viaggioInstance, field: 'numeroCartellino', 'error')} required">
        <label for="numeroCartellino">
            <g:message code="viaggio.numeroCartellino.labelform" default="Numero Cartellino"/>
            <span class="required-indicator">*</span>
        </label>
        <g:textField name="numeroCartellino" value="${viaggioInstance?.numeroCartellino}"/>
    </div>

    <div class="fieldcontain ${hasErrors(bean: viaggioInstance, field: 'nomePaziente', 'error')} ">
        <label for="nomePaziente">
            <g:message code="viaggio.nomePaziente.labelform" default="Nome Paziente"/>
        </label>
        <g:textField name="nomePaziente" value="${viaggioInstance?.nomePaziente}"/>
    </div>

    <div class="fieldcontain ${hasErrors(bean: viaggioInstance, field: 'indirizzoPaziente', 'error')} ">
        <label for="indirizzoPaziente">
            <g:message code="viaggio.indirizzoPaziente.labelform" default="Indirizzo Paziente"/>
        </label>
        <g:textField name="indirizzoPaziente" value="${viaggioInstance?.indirizzoPaziente}"/>
    </div>

    <div class="fieldcontain ${hasErrors(bean: viaggioInstance, field: 'cittaPaziente', 'error')} ">
        <label for="cittaPaziente">
            <g:message code="viaggio.cittaPaziente.labelform" default="Citta Paziente"/>
        </label>
        <g:textField name="cittaPaziente" value="${viaggioInstance?.cittaPaziente}"/>
    </div>

    <div class="fieldcontain ${hasErrors(bean: viaggioInstance, field: 'etaPaziente', 'error')} ">
        <label for="etaPaziente">
            <g:message code="viaggio.etaPaziente.labelform" default="Eta Paziente"/>
        </label>
        <g:textField name="etaPaziente" value="${viaggioInstance?.etaPaziente}"/>
    </div>

    <div class="fieldcontain ${hasErrors(bean: viaggioInstance, field: 'numeroBolla', 'error')} required">
        <label for="numeroBolla">
            <g:message code="viaggio.numeroBolla.labelform" default="Numero Bolla"/>
            <span class="required-indicator">*</span>
        </label>
        <g:field name="numeroBolla" type="number" value="${viaggioInstance.numeroBolla}" required=""/>
    </div>

    <div class="fieldcontain ${hasErrors(bean: viaggioInstance, field: 'numeroServizio', 'error')} required">
        <label for="numeroServizio">
            <g:message code="viaggio.numeroServizio.labelform" default="Numero Servizio"/>
            <span class="required-indicator">*</span>
        </label>
        <g:field name="numeroServizio" type="number" value="${viaggioInstance.numeroServizio}" required=""/>
    </div>

    <div class="fieldcontain ${hasErrors(bean: viaggioInstance, field: 'numeroViaggio', 'error')} required">
        <label for="numeroViaggio">
            <g:message code="viaggio.numeroViaggio.labelform" default="Numero viaggio"/>
            <span class="required-indicator">*</span>
        </label>
        <g:field name="numeroViaggio" type="number" value="${viaggioInstance.numeroViaggio}" required=""/>
    </div>

    <div class="fieldcontain ${hasErrors(bean: viaggioInstance, field: 'prelievo', 'error')} ">
        <label for="prelievo">
            <g:message code="viaggio.prelievo.labelform" default="Prelievo"/>
        </label>
        <g:textField name="prelievo" value="${viaggioInstance?.prelievo}"/>
    </div>

    <div class="fieldcontain ${hasErrors(bean: viaggioInstance, field: 'ricovero', 'error')} ">
        <label for="ricovero">
            <g:message code="viaggio.ricovero.labelform" default="Ricovero"/>
        </label>
        <g:textField name="ricovero" value="${viaggioInstance?.ricovero}"/>
    </div>

    <div class="fieldcontain ${hasErrors(bean: viaggioInstance, field: 'militeFunzione1', 'error')} required">
        <label for="militeFunzione1">
            <g:message code="viaggio.militeFunzione1.labelform" default="Autista Emergenza"/>
            <span class="required-indicator">*</span>
        </label>
        <g:select id="militeFunzione1" name="militeFunzione1.id" from="${listaAutisti}" optionKey="id"
                  required="" value="${viaggioInstance?.militeFunzione1?.id}" class="many-to-one"/>
    </div>

    <div class="fieldcontain ${hasErrors(bean: viaggioInstance, field: 'militeFunzione2', 'error')} required">
        <label for="militeFunzione2">
            <g:message code="viaggio.militeFunzione2.labelform" default="Soccorritore Dae"/>
            <span class="required-indicator">*</span>
        </label>
        <g:select id="militeFunzione2" name="militeFunzione2.id" from="${listaSocDae}" optionKey="id"
                  required="" value="${viaggioInstance?.militeFunzione2?.id}" class="many-to-one"/>
    </div>

    <div class="fieldcontain ${hasErrors(bean: viaggioInstance, field: 'militeFunzione3', 'error')}">
        <label for="militeFunzione3">
            <g:message code="viaggio.militeFunzione3.labelform" default="Soccorritore"/>
        </label>
        <g:select id="militeFunzione3" name="militeFunzione3.id" from="${listaSoccorritori}" optionKey="id"
                  value="${viaggioInstance?.militeFunzione3?.id}" class="many-to-one" noSelection="['null': '']"/>
    </div>

    <div class="fieldcontain ${hasErrors(bean: viaggioInstance, field: 'militeFunzione4', 'error')}">
        <label for="militeFunzione4">
            <g:message code="viaggio.militeFunzione4.labelform" default="Barelliere Affiancamento"/>
        </label>
        <g:select id="militeFunzione4" name="militeFunzione4.id"
                  from="${listaBarellieri}" optionKey="id"
                  value="${viaggioInstance?.militeFunzione4?.id}" class="many-to-one"
                  noSelection="['null': '']"/>
    </div>

</fieldset>
<fieldset class="buttons">
    <g:submitButton name="create" class="save"
                    value="${message(code: 'default.button.confirm.label', default: 'Create')}"/>
    <g:submitButton name="list" class="list" annulla=""
                    value="${message(code: 'default.button.cancel.label', default: 'Annulla')}"/>
</fieldset>
</g:form>
</div>
</body>
</html>
