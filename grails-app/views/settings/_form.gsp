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



<div class="fieldcontain ${hasErrors(bean: settingsInstance, field: 'croce', 'error')} required">
    <label for="croce">
        <g:message code="settings.croce.labelform" default="Croce" />
        <span class="required-indicator">*</span>
    </label>

    <g:select id="croce" name="croce.id" from="${webambulanze.Croce.list()}" optionKey="id" required="" value="${settingsInstance?.croce?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: settingsInstance, field: 'startLogin', 'error')} ">
    <label for="startLogin">
        <g:message code="settings.startLogin.labelform" default="Start Login" />

    </label>

    <g:checkBox name="startLogin" value="${settingsInstance?.startLogin}" />
</div>

<div class="fieldcontain ${hasErrors(bean: settingsInstance, field: 'startController', 'error')} ">
    <label for="startController">
        <g:message code="settings.startController.labelform" default="Start Controller" />

    </label>

    <g:textField name="startController" value="${settingsInstance?.startController}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: settingsInstance, field: 'allControllers', 'error')} ">
    <label for="allControllers">
        <g:message code="settings.allControllers.labelform" default="All Controllers" />

    </label>

    <g:checkBox name="allControllers" value="${settingsInstance?.allControllers}" />
</div>

<div class="fieldcontain ${hasErrors(bean: settingsInstance, field: 'controlli', 'error')} ">
    <label for="controlli">
        <g:message code="settings.controlli.labelform" default="Controlli" />

    </label>

    <g:textField name="controlli" value="${settingsInstance?.controlli}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: settingsInstance, field: 'mostraSoloMilitiFunzione', 'error')} ">
    <label for="mostraSoloMilitiFunzione">
        <g:message code="settings.mostraSoloMilitiFunzione.labelform" default="Mostra Solo Militi Funzione" />

    </label>

    <g:checkBox name="mostraSoloMilitiFunzione" value="${settingsInstance?.mostraSoloMilitiFunzione}" />
</div>

<div class="fieldcontain ${hasErrors(bean: settingsInstance, field: 'mostraMilitiFunzioneAndAltri', 'error')} ">
    <label for="mostraMilitiFunzioneAndAltri">
        <g:message code="settings.mostraMilitiFunzioneAndAltri.labelform" default="Mostra Militi Funzione And Altri" />

    </label>

    <g:checkBox name="mostraMilitiFunzioneAndAltri" value="${settingsInstance?.mostraMilitiFunzioneAndAltri}" />
</div>

<div class="fieldcontain ${hasErrors(bean: settingsInstance, field: 'militePuoInserireAltri', 'error')} ">
    <label for="militePuoInserireAltri">
        <g:message code="settings.militePuoInserireAltri.labelform" default="Milite Puo Inserire Altri" />

    </label>

    <g:checkBox name="militePuoInserireAltri" value="${settingsInstance?.militePuoInserireAltri}" />
</div>

<div class="fieldcontain ${hasErrors(bean: settingsInstance, field: 'militePuoModificareAltri', 'error')} ">
    <label for="militePuoModificareAltri">
        <g:message code="settings.militePuoModificareAltri.labelform" default="Milite Puo Modificare Altri" />

    </label>

    <g:checkBox name="militePuoModificareAltri" value="${settingsInstance?.militePuoModificareAltri}" />
</div>

<div class="fieldcontain ${hasErrors(bean: settingsInstance, field: 'militePuoCancellareAltri', 'error')} ">
    <label for="militePuoCancellareAltri">
        <g:message code="settings.militePuoCancellareAltri.labelform" default="Milite Puo Cancellare Altri" />

    </label>

    <g:checkBox name="militePuoCancellareAltri" value="${settingsInstance?.militePuoCancellareAltri}" />
</div>

<div class="fieldcontain ${hasErrors(bean: settingsInstance, field: 'tipoControlloModifica', 'error')} required">
    <label for="tipoControlloModifica">
        <g:message code="settings.tipoControlloModifica.labelform" default="Tipo Controllo Modifica" />
        <span class="required-indicator">*</span>
    </label>

    <g:select name="tipoControlloModifica" from="${webambulanze.ControlloTemporale?.values()}" keys="${webambulanze.ControlloTemporale.values()*.name()}" required="" value="${settingsInstance?.tipoControlloModifica?.name()}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: settingsInstance, field: 'maxMinutiTrascorsiModifica', 'error')} required">
    <label for="maxMinutiTrascorsiModifica">
        <g:message code="settings.maxMinutiTrascorsiModifica.labelform" default="Max Minuti Trascorsi Modifica" />
        <span class="required-indicator">*</span>
    </label>

    <g:field name="maxMinutiTrascorsiModifica" type="number" value="${settingsInstance.maxMinutiTrascorsiModifica}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: settingsInstance, field: 'minGiorniMancantiModifica', 'error')} required">
    <label for="minGiorniMancantiModifica">
        <g:message code="settings.minGiorniMancantiModifica.labelform" default="Min Giorni Mancanti Modifica" />
        <span class="required-indicator">*</span>
    </label>

    <g:field name="minGiorniMancantiModifica" type="number" value="${settingsInstance.minGiorniMancantiModifica}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: settingsInstance, field: 'tipoControlloCancellazione', 'error')} required">
    <label for="tipoControlloCancellazione">
        <g:message code="settings.tipoControlloCancellazione.labelform" default="Tipo Controllo Cancellazione" />
        <span class="required-indicator">*</span>
    </label>

    <g:select name="tipoControlloCancellazione" from="${webambulanze.ControlloTemporale?.values()}" keys="${webambulanze.ControlloTemporale.values()*.name()}" required="" value="${settingsInstance?.tipoControlloCancellazione?.name()}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: settingsInstance, field: 'maxMinutiTrascorsiCancellazione', 'error')} required">
    <label for="maxMinutiTrascorsiCancellazione">
        <g:message code="settings.maxMinutiTrascorsiCancellazione.labelform" default="Max Minuti Trascorsi Cancellazione" />
        <span class="required-indicator">*</span>
    </label>

    <g:field name="maxMinutiTrascorsiCancellazione" type="number" value="${settingsInstance.maxMinutiTrascorsiCancellazione}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: settingsInstance, field: 'minGiorniMancantiCancellazione', 'error')} required">
    <label for="minGiorniMancantiCancellazione">
        <g:message code="settings.minGiorniMancantiCancellazione.labelform" default="Min Giorni Mancanti Cancellazione" />
        <span class="required-indicator">*</span>
    </label>

    <g:field name="minGiorniMancantiCancellazione" type="number" value="${settingsInstance.minGiorniMancantiCancellazione}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: settingsInstance, field: 'isOrarioTurnoModificabileForm', 'error')} ">
    <label for="isOrarioTurnoModificabileForm">
        <g:message code="settings.isOrarioTurnoModificabileForm.labelform" default="Is Orario Turno Modificabile Form" />

    </label>

    <g:checkBox name="isOrarioTurnoModificabileForm" value="${settingsInstance?.isOrarioTurnoModificabileForm}" />
</div>

<div class="fieldcontain ${hasErrors(bean: settingsInstance, field: 'isCalcoloNotturnoStatistiche', 'error')} ">
    <label for="isCalcoloNotturnoStatistiche">
        <g:message code="settings.isCalcoloNotturnoStatistiche.labelform" default="Is Calcolo Notturno Statistiche" />

    </label>

    <g:checkBox name="isCalcoloNotturnoStatistiche" value="${settingsInstance?.isCalcoloNotturnoStatistiche}" />
</div>

<div class="fieldcontain ${hasErrors(bean: settingsInstance, field: 'isDisabilitazioneAutomaticaLogin', 'error')} ">
    <label for="isDisabilitazioneAutomaticaLogin">
        <g:message code="settings.isDisabilitazioneAutomaticaLogin.labelform" default="Is Disabilitazione Automatica Login" />

    </label>

    <g:checkBox name="isDisabilitazioneAutomaticaLogin" value="${settingsInstance?.isDisabilitazioneAutomaticaLogin}" />
</div>

<div class="fieldcontain ${hasErrors(bean: settingsInstance, field: 'fissaLimiteMassimoSingoloTurno', 'error')} ">
    <label for="fissaLimiteMassimoSingoloTurno">
        <g:message code="settings.fissaLimiteMassimoSingoloTurno.labelform" default="Fissa Limite Massimo Singolo Turno" />

    </label>

    <g:checkBox name="fissaLimiteMassimoSingoloTurno" value="${settingsInstance?.fissaLimiteMassimoSingoloTurno}" />
</div>

<div class="fieldcontain ${hasErrors(bean: settingsInstance, field: 'oreMassimeSingoloTurno', 'error')} required">
    <label for="oreMassimeSingoloTurno">
        <g:message code="settings.oreMassimeSingoloTurno.labelform" default="Ore Massime Singolo Turno" />
        <span class="required-indicator">*</span>
    </label>

    <g:field name="oreMassimeSingoloTurno" type="number" value="${settingsInstance.oreMassimeSingoloTurno}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: settingsInstance, field: 'usaModuloViaggi', 'error')} ">
    <label for="usaModuloViaggi">
        <g:message code="settings.usaModuloViaggi.labelform" default="Usa Modulo Viaggi" />

    </label>

    <g:checkBox name="usaModuloViaggi" value="${settingsInstance?.usaModuloViaggi}" />
</div>

<div class="fieldcontain ${hasErrors(bean: settingsInstance, field: 'numeroServiziEffettuati', 'error')} required">
    <label for="numeroServiziEffettuati">
        <g:message code="settings.numeroServiziEffettuati.labelform" default="Numero Servizi Effettuati" />
        <span class="required-indicator">*</span>
    </label>

    <g:field name="numeroServiziEffettuati" type="number" value="${settingsInstance.numeroServiziEffettuati}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: settingsInstance, field: 'tabelloneSecured', 'error')} ">
    <label for="tabelloneSecured">
        <g:message code="settings.tabelloneSecured.labelform" default="Tabellone Secured" />

    </label>

    <g:checkBox name="tabelloneSecured" value="${settingsInstance?.tabelloneSecured}" />
</div>

<div class="fieldcontain ${hasErrors(bean: settingsInstance, field: 'turniSecured', 'error')} ">
    <label for="turniSecured">
        <g:message code="settings.turniSecured.labelform" default="Turni Secured" />

    </label>

    <g:checkBox name="turniSecured" value="${settingsInstance?.turniSecured}" />
</div>

<div class="fieldcontain ${hasErrors(bean: settingsInstance, field: 'mostraTabellonePartenza', 'error')} ">
    <label for="mostraTabellonePartenza">
        <g:message code="settings.mostraTabellonePartenza.labelform" default="Mostra Tabellone Partenza" />

    </label>

    <g:checkBox name="mostraTabellonePartenza" value="${settingsInstance?.mostraTabellonePartenza}" />
</div>

<div class="fieldcontain ${hasErrors(bean: settingsInstance, field: 'bloccaSoloFunzioniObbligatorie', 'error')} ">
    <label for="bloccaSoloFunzioniObbligatorie">
        <g:message code="settings.bloccaSoloFunzioniObbligatorie.labelform" default="Blocca Solo Funzioni Obbligatorie" />

    </label>

    <g:checkBox name="bloccaSoloFunzioniObbligatorie" value="${settingsInstance?.bloccaSoloFunzioniObbligatorie}" />
</div>

<div class="fieldcontain ${hasErrors(bean: settingsInstance, field: 'militePuoCreareTurnoStandard', 'error')} ">
    <label for="militePuoCreareTurnoStandard">
        <g:message code="settings.militePuoCreareTurnoStandard.labelform" default="Milite Puo Creare Turno Standard" />

    </label>

    <g:checkBox name="militePuoCreareTurnoStandard" value="${settingsInstance?.militePuoCreareTurnoStandard}" />
</div>

<div class="fieldcontain ${hasErrors(bean: settingsInstance, field: 'militePuoCreareTurnoExtra', 'error')} ">
    <label for="militePuoCreareTurnoExtra">
        <g:message code="settings.militePuoCreareTurnoExtra.labelform" default="Milite Puo Creare Turno Extra" />

    </label>

    <g:checkBox name="militePuoCreareTurnoExtra" value="${settingsInstance?.militePuoCreareTurnoExtra}" />
</div>

<div class="fieldcontain ${hasErrors(bean: settingsInstance, field: 'isTurnoSettimanale', 'error')} ">
    <label for="isTurnoSettimanale">
        <g:message code="settings.isTurnoSettimanale.labelform" default="Is Turno Settimanale" />

    </label>

    <g:checkBox name="isTurnoSettimanale" value="${settingsInstance?.isTurnoSettimanale}" />
</div>

<div class="fieldcontain ${hasErrors(bean: settingsInstance, field: 'usaNomeCognome', 'error')} ">
    <label for="usaNomeCognome">
        <g:message code="settings.usaNomeCognome.labelform" default="Usa Nome Cognome" />

    </label>

    <g:checkBox name="usaNomeCognome" value="${settingsInstance?.usaNomeCognome}" />
</div>

<div class="fieldcontain ${hasErrors(bean: settingsInstance, field: 'usaListaMilitiViaggi', 'error')} ">
    <label for="usaListaMilitiViaggi">
        <g:message code="settings.usaListaMilitiViaggi.labelform" default="Usa Lista Militi Viaggi" />

    </label>

    <g:checkBox name="usaListaMilitiViaggi" value="${settingsInstance?.usaListaMilitiViaggi}" />
</div>

<div class="fieldcontain ${hasErrors(bean: settingsInstance, field: 'suggerisceKilometroViaggio', 'error')} ">
    <label for="suggerisceKilometroViaggio">
        <g:message code="settings.suggerisceKilometroViaggio.labelform" default="Suggerisce Kilometro Viaggio" />

    </label>

    <g:checkBox name="suggerisceKilometroViaggio" value="${settingsInstance?.suggerisceKilometroViaggio}" />
</div>

<g:if test="${campiExtra}">
    <amb:extraSchedaForm rec="${settingsInstance}" campiExtra="${campiExtra}"></amb:extraSchedaForm>
</g:if>
