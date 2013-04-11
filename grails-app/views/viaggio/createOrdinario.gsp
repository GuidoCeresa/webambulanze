<%@ page import="webambulanze.Viaggio" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'viaggio.label', default: 'Viaggio')}"/>
    <title>Ordinario</title>
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
    <h1><g:message code="viaggio.create.label" default="Crea viaggio ordinario"/></h1>
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
    <g:form action="save" >
        <fieldset class="form">

            <div class="fieldcontain ${hasErrors(bean: viaggioInstance, field: 'automezzo', 'error')} required">
                <label for="automezzo">
                    <g:message code="viaggio.automezzo.labelform" default="Automezzo"/>
                    <span class="required-indicator">*</span>
                </label>
                <g:select id="automezzo" name="automezzo.id" from="${webambulanze.Automezzo.list()}" optionKey="id" required=""
                          value="${viaggioInstance?.automezzo?.id}" class="many-to-one"/>
            </div>

            <div class="fieldcontain ${hasErrors(bean: viaggioInstance, field: 'giorno', 'error')} required">
                <label for="giorno">
                    <g:message code="viaggio.giorno.labelform" default="Giorno"/>
                    <span class="required-indicator">*</span>
                </label>
                <g:datePicker name="giorno" precision="day" value="${viaggioInstance?.giorno}"/>
            </div>

            <div class="fieldcontain ${hasErrors(bean: viaggioInstance, field: 'inizio', 'error')} required">
                <label for="inizio">
                    <g:message code="viaggio.inizio.labelform" default="Inizio"/>
                    <span class="required-indicator">*</span>
                </label>
                <g:datePicker name="inizio" precision="day" value="${viaggioInstance?.inizio}"/>
            </div>

            <div class="fieldcontain ${hasErrors(bean: viaggioInstance, field: 'fine', 'error')} required">
                <label for="fine">
                    <g:message code="viaggio.fine.labelform" default="Fine"/>
                    <span class="required-indicator">*</span>
                </label>
                <g:datePicker name="fine" precision="day" value="${viaggioInstance?.fine}"/>
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

            <div class="fieldcontain ${hasErrors(bean: viaggioInstance, field: 'indirizzoPaziente', 'error')} ">
                <label for="indirizzoPaziente">
                    <g:message code="viaggio.indirizzoPaziente.labelform" default="Indirizzo Paziente"/>
                </label>
                <g:textField name="indirizzoPaziente" value="${viaggioInstance?.indirizzoPaziente}"/>
            </div>

            <div class="fieldcontain ${hasErrors(bean: viaggioInstance, field: 'nomePaziente', 'error')} ">
                <label for="nomePaziente">
                    <g:message code="viaggio.nomePaziente.labelform" default="Nome Paziente"/>
                </label>
                <g:textField name="nomePaziente" value="${viaggioInstance?.nomePaziente}"/>
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

        </fieldset>
        <fieldset class="buttons">
            <g:submitButton name="create" class="save"
                            value="${message(code: 'default.button.create.label', default: 'Create')}"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
