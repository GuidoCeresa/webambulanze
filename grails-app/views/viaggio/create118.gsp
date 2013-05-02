<%@ page import="webambulanze.Viaggio" %>
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

            <div class="fieldcontain ${hasErrors(bean: viaggioInstance, field: 'automezzo', 'error')} required">
                <label for="automezzo">
                    <g:message code="viaggio.automezzo.labelform" default="Automezzo"/>
                    <span class="required-indicator">*</span>
                </label>
                <g:select id="automezzo" name="automezzo.id" from="${webambulanze.Automezzo.list()}" optionKey="id"
                          required=""
                          value="${viaggioInstance?.automezzo?.id}" class="many-to-one"/>
            </div>

            <div class="fieldcontain ${hasErrors(bean: viaggioInstance, field: 'inizio', 'error')} required">
                <label for="inizio">
                    <g:message code="viaggio.inizio.labelform" default="Inizio"/>
                    <span class="required-indicator">*</span>
                </label>
                <g:datePicker name="inizio" precision="day" value="${viaggioInstance?.inizio}"/>
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
                <g:datePicker name="fine" precision="day" value="${viaggioInstance?.fine}"/>
            </div>

            <div class="fieldcontain ${hasErrors(bean: viaggioInstance, field: 'numeroCartellino', 'error')} ">
                <label for="numeroCartellino">
                    <g:message code="viaggio.numeroCartellino.labelform" default="Numero Cartellino"/>
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

            <div class="fieldcontain ${hasErrors(bean: viaggioInstance, field: 'autista', 'error')} required">
                <label for="autista">
                    <g:message code="viaggio.autista.labelform" default="Autista"/>
                    <span class="required-indicator">*</span>
                </label>
                <g:select id="autista" name="autista.id" from="${webambulanze.Milite.list()}" optionKey="id" required=""
                          value="${viaggioInstance?.autista?.id}" class="many-to-one"/>
            </div>

            <div class="fieldcontain ${hasErrors(bean: viaggioInstance, field: 'soccorritore', 'error')} required">
                <label for="soccorritore">
                    <g:message code="viaggio.soccorritore.labelform" default="Soccorritore"/>
                    <span class="required-indicator">*</span>
                </label>
                <g:select id="soccorritore" name="soccorritore.id" from="${webambulanze.Milite.list()}" optionKey="id"
                          required="" value="${viaggioInstance?.soccorritore?.id}" class="many-to-one"/>
            </div>

            <div class="fieldcontain ${hasErrors(bean: viaggioInstance, field: 'barelliere', 'error')} required">
                <label for="barelliere">
                    <g:message code="viaggio.barelliere.labelform" default="Barelliere"/>
                    <span class="required-indicator">*</span>
                </label>
                <g:select id="barelliere" name="barelliere.id" from="${webambulanze.Milite.list()}" optionKey="id"
                          required="" value="${viaggioInstance?.barelliere?.id}" class="many-to-one"/>
            </div>

        </fieldset>
        <fieldset class="buttons">
            <g:submitButton name="create" class="save"
                            value="${message(code: 'default.button.create.label', default: 'Create')}"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
