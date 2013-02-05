<!--Created by Algos s.r.l.-->
<!--Date: mag 2012-->
<!--Questo file è stato installato dal plugin AlgosBase-->
<!--Tipicamente NON verrà più sovrascritto dalle successive release del plugin-->
<!--in quanto POTREBBE essere personalizzato in questa applicazione-->
<!--Se vuoi che le prossime release del plugin sovrascrivano questo file,-->
<!--perdendo tutte le modifiche effettuate qui,-->
<!--regola a true il flag di controllo flagOverwrite©-->
<!--flagOverwrite = false-->


<%@ page import="webambulanze.Ruolo" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'ruolo.label', default: 'Ruolo')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-ruolo" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
                <li><g:link class="home" controller="Gen" action="home"><g:message code="ambulanze.home.label"/></g:link></li>
                <li><g:link class="list" action="list">
                    <g:message code="ruolo.list.label"
                               default="Elenco ruolo"/>
                </g:link></li>

                <li><g:link class="create" action="create">
                    <g:message code="ruolo.new.label"
                               default="Nuovo ruolo"/>
                </g:link></li>

                <li><g:link class="edit" action="edit" id="${ruoloInstance?.id}">
                    <g:message code="ruolo.edit.label"
                               default="Modifica ruolo"/>
                </g:link></li>
			</ul>
		</div>
		<div id="show-ruolo" class="content scaffold-show" role="main">
            <h1><g:message code="ruolo.show.label" default="Mostra ruolo"/></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:if test="${flash.errors}">
                <div class="errors" role="status">${flash.errors}</div>
            </g:if>
            <g:if test="${flash.listaMessaggi}">
                <ul><g:each in="${flash.listaMessaggi}" var="messaggio"><li><div class="message">${messaggio}</div></li></g:each></ul>
            </g:if>
            <g:if test="${flash.listaErrori}">
                <ul><g:each in="${flash.listaErrori}" var="errore"><li class="errors"><div>${errore}</div></li></g:each></ul>
            </g:if>
			<ol class="property-list ruolo">
			
				<li class="fieldcontain">
					<span id="authority-label" class="property-label"><g:message code="ruolo.authority.labelform" default="Authority" /></span>
					
						<span class="property-value" aria-labelledby="authority-label"><g:fieldValue bean="${ruoloInstance}" field="authority"/></span>
					
				</li>
			
			</ol>

            <g:if test="${campiExtra}">
                <amb:extraScheda rec="${ruoloInstance}" campiExtra="${campiExtra}"></amb:extraScheda>
            </g:if>

            <g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${ruoloInstance?.id}" />
					<g:link class="edit" action="edit" id="${ruoloInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <g:link class="delete" action="delete" id="${ruoloInstance?.id}" onclick="return confirm('Sei sicuro?');"><g:message code="default.button.delete.label" default="Delete" /></g:link>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
