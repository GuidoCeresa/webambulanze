<!--Created by Algos s.r.l.-->
<!--Date: mag 2012-->
<!--Questo file è stato installato dal plugin AlgosBase-->
<!--Tipicamente NON verrà più sovrascritto dalle successive release del plugin-->
<!--in quanto POTREBBE essere personalizzato in questa applicazione-->
<!--Se vuoi che le prossime release del plugin sovrascrivano questo file,-->
<!--perdendo tutte le modifiche effettuate qui,-->
<!--regola a true il flag di controllo flagOverwrite©-->
<!--flagOverwrite = false-->


<%@ page import="webambulanze.Settings" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="${message(code: 'settings.label', default: 'Settings')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<amb:titoloPagina></amb:titoloPagina>
<a href="#show-settings" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                                  default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><g:link class="home" controller="Gen" action="home"><g:message code="ambulanze.home.label"/></g:link></li>
        <li><g:link class="list" action="list">
            <g:message code="settings.list.label"
                       default="Elenco settings"/>
        </g:link></li>

        <li><g:link class="create" action="create">
            <g:message code="settings.new.label"
                       default="Nuovo settings"/>
        </g:link></li>

        <li><g:link class="edit" action="edit" id="${settingsInstance?.id}">
            <g:message code="settings.edit.label" default="Modifica settings"/>
        </g:link></li>
        <g:if test="${menuExtra}">
            <li><amb:menuExtra menuExtra="${menuExtra}"></amb:menuExtra></li>
        </g:if>
        <sec:ifNotLoggedIn>
            <li><g:link class="login" controller="login">Login</g:link></li>
        </sec:ifNotLoggedIn>
        <sec:ifLoggedIn>
            <li><g:link class="logout" controller="logout">Logout</g:link></li>
        </sec:ifLoggedIn>
    </ul>
</div>

<div id="show-settings" class="content scaffold-show" role="main">
    <h1><g:message code="settings.show.label" default="Mostra settings"/></h1>
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
    <ol class="property-list settings">
        
        <li class="fieldcontain">
            <span id="croce-label" class="property-label"><g:message
                    code="settings.croce.labelform" default="Croce"/></span>
            
            <span class="property-value" aria-labelledby="croce-label"><g:link
                    controller="croce" action="show"
                    id="${settingsInstance?.croce?.id}">${settingsInstance?.croce?.encodeAsHTML()}</g:link></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="startLogin-label" class="property-label"><g:message
                    code="settings.startLogin.labelform" default="Start Login"/></span>
            
            <span class="property-value" aria-labelledby="startLogin-label"><g:formatBoolean
                    boolean="${settingsInstance?.startLogin}"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="startController-label" class="property-label"><g:message
                    code="settings.startController.labelform" default="Start Controller"/></span>
            
            <span class="property-value" aria-labelledby="startController-label"><g:fieldValue bean="${settingsInstance}"
                                                                                         field="startController"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="allControllers-label" class="property-label"><g:message
                    code="settings.allControllers.labelform" default="All Controllers"/></span>
            
            <span class="property-value" aria-labelledby="allControllers-label"><g:formatBoolean
                    boolean="${settingsInstance?.allControllers}"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="controlli-label" class="property-label"><g:message
                    code="settings.controlli.labelform" default="Controlli"/></span>
            
            <span class="property-value" aria-labelledby="controlli-label"><g:fieldValue bean="${settingsInstance}"
                                                                                         field="controlli"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="mostraSoloMilitiFunzione-label" class="property-label"><g:message
                    code="settings.mostraSoloMilitiFunzione.labelform" default="Mostra Solo Militi Funzione"/></span>
            
            <span class="property-value" aria-labelledby="mostraSoloMilitiFunzione-label"><g:formatBoolean
                    boolean="${settingsInstance?.mostraSoloMilitiFunzione}"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="mostraMilitiFunzioneAndAltri-label" class="property-label"><g:message
                    code="settings.mostraMilitiFunzioneAndAltri.labelform" default="Mostra Militi Funzione And Altri"/></span>
            
            <span class="property-value" aria-labelledby="mostraMilitiFunzioneAndAltri-label"><g:formatBoolean
                    boolean="${settingsInstance?.mostraMilitiFunzioneAndAltri}"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="militePuoInserireAltri-label" class="property-label"><g:message
                    code="settings.militePuoInserireAltri.labelform" default="Milite Puo Inserire Altri"/></span>
            
            <span class="property-value" aria-labelledby="militePuoInserireAltri-label"><g:formatBoolean
                    boolean="${settingsInstance?.militePuoInserireAltri}"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="militePuoModificareAltri-label" class="property-label"><g:message
                    code="settings.militePuoModificareAltri.labelform" default="Milite Puo Modificare Altri"/></span>
            
            <span class="property-value" aria-labelledby="militePuoModificareAltri-label"><g:formatBoolean
                    boolean="${settingsInstance?.militePuoModificareAltri}"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="militePuoCancellareAltri-label" class="property-label"><g:message
                    code="settings.militePuoCancellareAltri.labelform" default="Milite Puo Cancellare Altri"/></span>
            
            <span class="property-value" aria-labelledby="militePuoCancellareAltri-label"><g:formatBoolean
                    boolean="${settingsInstance?.militePuoCancellareAltri}"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="tipoControlloModifica-label" class="property-label"><g:message
                    code="settings.tipoControlloModifica.labelform" default="Tipo Controllo Modifica"/></span>
            
            <span class="property-value" aria-labelledby="tipoControlloModifica-label"><g:fieldValue bean="${settingsInstance}"
                                                                                         field="tipoControlloModifica"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="maxMinutiTrascorsiModifica-label" class="property-label"><g:message
                    code="settings.maxMinutiTrascorsiModifica.labelform" default="Max Minuti Trascorsi Modifica"/></span>
            
            <span class="property-value" aria-labelledby="maxMinutiTrascorsiModifica-label"><g:fieldValue bean="${settingsInstance}"
                                                                                         field="maxMinutiTrascorsiModifica"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="minGiorniMancantiModifica-label" class="property-label"><g:message
                    code="settings.minGiorniMancantiModifica.labelform" default="Min Giorni Mancanti Modifica"/></span>
            
            <span class="property-value" aria-labelledby="minGiorniMancantiModifica-label"><g:fieldValue bean="${settingsInstance}"
                                                                                         field="minGiorniMancantiModifica"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="tipoControlloCancellazione-label" class="property-label"><g:message
                    code="settings.tipoControlloCancellazione.labelform" default="Tipo Controllo Cancellazione"/></span>
            
            <span class="property-value" aria-labelledby="tipoControlloCancellazione-label"><g:fieldValue bean="${settingsInstance}"
                                                                                         field="tipoControlloCancellazione"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="maxMinutiTrascorsiCancellazione-label" class="property-label"><g:message
                    code="settings.maxMinutiTrascorsiCancellazione.labelform" default="Max Minuti Trascorsi Cancellazione"/></span>
            
            <span class="property-value" aria-labelledby="maxMinutiTrascorsiCancellazione-label"><g:fieldValue bean="${settingsInstance}"
                                                                                         field="maxMinutiTrascorsiCancellazione"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="minGiorniMancantiCancellazione-label" class="property-label"><g:message
                    code="settings.minGiorniMancantiCancellazione.labelform" default="Min Giorni Mancanti Cancellazione"/></span>
            
            <span class="property-value" aria-labelledby="minGiorniMancantiCancellazione-label"><g:fieldValue bean="${settingsInstance}"
                                                                                         field="minGiorniMancantiCancellazione"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="isOrarioTurnoModificabileForm-label" class="property-label"><g:message
                    code="settings.isOrarioTurnoModificabileForm.labelform" default="Is Orario Turno Modificabile Form"/></span>
            
            <span class="property-value" aria-labelledby="isOrarioTurnoModificabileForm-label"><g:formatBoolean
                    boolean="${settingsInstance?.isOrarioTurnoModificabileForm}"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="isCalcoloNotturnoStatistiche-label" class="property-label"><g:message
                    code="settings.isCalcoloNotturnoStatistiche.labelform" default="Is Calcolo Notturno Statistiche"/></span>
            
            <span class="property-value" aria-labelledby="isCalcoloNotturnoStatistiche-label"><g:formatBoolean
                    boolean="${settingsInstance?.isCalcoloNotturnoStatistiche}"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="isDisabilitazioneAutomaticaLogin-label" class="property-label"><g:message
                    code="settings.isDisabilitazioneAutomaticaLogin.labelform" default="Is Disabilitazione Automatica Login"/></span>
            
            <span class="property-value" aria-labelledby="isDisabilitazioneAutomaticaLogin-label"><g:formatBoolean
                    boolean="${settingsInstance?.isDisabilitazioneAutomaticaLogin}"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="fissaLimiteMassimoSingoloTurno-label" class="property-label"><g:message
                    code="settings.fissaLimiteMassimoSingoloTurno.labelform" default="Fissa Limite Massimo Singolo Turno"/></span>
            
            <span class="property-value" aria-labelledby="fissaLimiteMassimoSingoloTurno-label"><g:formatBoolean
                    boolean="${settingsInstance?.fissaLimiteMassimoSingoloTurno}"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="oreMassimeSingoloTurno-label" class="property-label"><g:message
                    code="settings.oreMassimeSingoloTurno.labelform" default="Ore Massime Singolo Turno"/></span>
            
            <span class="property-value" aria-labelledby="oreMassimeSingoloTurno-label"><g:fieldValue bean="${settingsInstance}"
                                                                                         field="oreMassimeSingoloTurno"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="usaModuloViaggi-label" class="property-label"><g:message
                    code="settings.usaModuloViaggi.labelform" default="Usa Modulo Viaggi"/></span>
            
            <span class="property-value" aria-labelledby="usaModuloViaggi-label"><g:formatBoolean
                    boolean="${settingsInstance?.usaModuloViaggi}"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="numeroServiziEffettuati-label" class="property-label"><g:message
                    code="settings.numeroServiziEffettuati.labelform" default="Numero Servizi Effettuati"/></span>
            
            <span class="property-value" aria-labelledby="numeroServiziEffettuati-label"><g:fieldValue bean="${settingsInstance}"
                                                                                         field="numeroServiziEffettuati"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="tabelloneSecured-label" class="property-label"><g:message
                    code="settings.tabelloneSecured.labelform" default="Tabellone Secured"/></span>
            
            <span class="property-value" aria-labelledby="tabelloneSecured-label"><g:formatBoolean
                    boolean="${settingsInstance?.tabelloneSecured}"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="turniSecured-label" class="property-label"><g:message
                    code="settings.turniSecured.labelform" default="Turni Secured"/></span>
            
            <span class="property-value" aria-labelledby="turniSecured-label"><g:formatBoolean
                    boolean="${settingsInstance?.turniSecured}"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="mostraTabellonePartenza-label" class="property-label"><g:message
                    code="settings.mostraTabellonePartenza.labelform" default="Mostra Tabellone Partenza"/></span>
            
            <span class="property-value" aria-labelledby="mostraTabellonePartenza-label"><g:formatBoolean
                    boolean="${settingsInstance?.mostraTabellonePartenza}"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="bloccaSoloFunzioniObbligatorie-label" class="property-label"><g:message
                    code="settings.bloccaSoloFunzioniObbligatorie.labelform" default="Blocca Solo Funzioni Obbligatorie"/></span>
            
            <span class="property-value" aria-labelledby="bloccaSoloFunzioniObbligatorie-label"><g:formatBoolean
                    boolean="${settingsInstance?.bloccaSoloFunzioniObbligatorie}"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="militePuoCreareTurnoStandard-label" class="property-label"><g:message
                    code="settings.militePuoCreareTurnoStandard.labelform" default="Milite Puo Creare Turno Standard"/></span>
            
            <span class="property-value" aria-labelledby="militePuoCreareTurnoStandard-label"><g:formatBoolean
                    boolean="${settingsInstance?.militePuoCreareTurnoStandard}"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="militePuoCreareTurnoExtra-label" class="property-label"><g:message
                    code="settings.militePuoCreareTurnoExtra.labelform" default="Milite Puo Creare Turno Extra"/></span>
            
            <span class="property-value" aria-labelledby="militePuoCreareTurnoExtra-label"><g:formatBoolean
                    boolean="${settingsInstance?.militePuoCreareTurnoExtra}"/></span>
            
        </li>
        
        <li class="fieldcontain">
            <span id="isTurnoSettimanale-label" class="property-label"><g:message
                    code="settings.isTurnoSettimanale.labelform" default="Is Turno Settimanale"/></span>
            
            <span class="property-value" aria-labelledby="isTurnoSettimanale-label"><g:formatBoolean
                    boolean="${settingsInstance?.isTurnoSettimanale}"/></span>
            
        </li>

    <li class="fieldcontain">
        <span id="usaNomeCognome-label" class="property-label"><g:message
                code="settings.usaNomeCognome.labelform" default="Usa Nome Cognome"/></span>

        <span class="property-value" aria-labelledby="usaNomeCognome-label"><g:formatBoolean
                boolean="${settingsInstance?.usaNomeCognome}"/></span>

    </li>

    <li class="fieldcontain">
        <span id="usaListaMilitiViaggi-label" class="property-label"><g:message
                code="settings.usaListaMilitiViaggi.labelform" default="Usa Lista Militi Viaggi"/></span>

        <span class="property-value" aria-labelledby="usaListaMilitiViaggi-label"><g:formatBoolean
                boolean="${settingsInstance?.usaListaMilitiViaggi}"/></span>

    </li>

    <li class="fieldcontain">
        <span id="suggerisceKilometroViaggio-label" class="property-label"><g:message
                code="settings.suggerisceKilometroViaggio.labelform" default="Suggerisce Kilometro Viaggio"/></span>

        <span class="property-value" aria-labelledby="suggerisceKilometroViaggio-label"><g:formatBoolean
                boolean="${settingsInstance?.suggerisceKilometroViaggio}"/></span>

    </li>

    </ol>

    <g:if test="${campiExtra}">
        <amb:extraScheda rec="${settingsInstance}" campiExtra="${campiExtra}"></amb:extraScheda>
    </g:if>

    <g:form>
        <fieldset class="buttons">
            <g:hiddenField name="id" value="${settingsInstance?.id}"/>
            <g:link class="edit" action="edit" id="${settingsInstance?.id}">
                <g:message code="settings.edit.label"
                           default="Modifica settings"/>
            </g:link>
            <g:actionSubmit class="delete" action="delete"
                            value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                            onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
