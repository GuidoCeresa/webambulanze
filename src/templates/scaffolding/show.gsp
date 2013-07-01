<!--Created by Algos s.r.l.-->
<!--Date: mag 2012-->
<!--Questo file è stato installato dal plugin AlgosBase-->
<!--Tipicamente NON verrà più sovrascritto dalle successive release del plugin-->
<!--in quanto POTREBBE essere personalizzato in questa applicazione-->
<!--Se vuoi che le prossime release del plugin sovrascrivano questo file,-->
<!--perdendo tutte le modifiche effettuate qui,-->
<!--regola a true il flag di controllo flagOverwrite©-->
<!--flagOverwrite = false-->

<%=packageName%>
<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main">
    <g:set var="entityName" value="\${message(code: '${domainClass.propertyName}.label', default: '${className}')}"/>
    <title><g:message code="default.show.label" args="[entityName]"/></title>
</head>

<body>
<amb:titoloPagina></amb:titoloPagina>
<a href="#show-${domainClass.propertyName}" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                                  default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><g:link class="home" controller="Gen" action="home"><g:message code="ambulanze.home.label"/></g:link></li>
        <li><g:link class="list" action="list">
            <g:message code="${domainClass.propertyName}.list.label"
                       default="Elenco ${domainClass.propertyName}"/>
        </g:link></li>

        <li><g:link class="create" action="create">
            <g:message code="${domainClass.propertyName}.new.label"
                       default="Nuovo ${domainClass.propertyName}"/>
        </g:link></li>

        <li><g:link class="edit" action="edit" id="\${${propertyName}?.id}">
            <g:message code="${domainClass.propertyName}.edit.label" default="Modifica ${domainClass.propertyName}"/>
        </g:link></li>
        <g:if test="\${menuExtra}">
            <li><amb:menuExtra menuExtra="\${menuExtra}"></amb:menuExtra></li>
        </g:if>
    </ul>
</div>

<div id="show-${domainClass.propertyName}" class="content scaffold-show" role="main">
    <h1><g:message code="${domainClass.propertyName}.show.label" default="Mostra ${domainClass.propertyName}"/></h1>
    <g:if test="\${flash.message}">
        <div class="message" role="status">\${flash.message}</div>
    </g:if>
    <g:if test="\${flash.errors}">
        <div class="errors" role="status">\${flash.errors}</div>
    </g:if>
    <g:if test="\${flash.listaMessaggi}">
        <ul><g:each in="\${flash.listaMessaggi}" var="messaggio"><li><div class="message">\${messaggio}</div>
        </li></g:each></ul>
    </g:if>
    <g:if test="\${flash.listaErrori}">
        <ul><g:each in="\${flash.listaErrori}" var="errore"><li class="errors"><div>\${errore}</div></li></g:each></ul>
    </g:if>
    <ol class="property-list ${domainClass.propertyName}">
        <% excludedProps = Event.allEvents.toList() << 'id' << 'version'
        allowedNames = domainClass.persistentProperties*.name << 'dateCreated' << 'lastUpdated'
        props = domainClass.properties.findAll { allowedNames.contains(it.name) && !excludedProps.contains(it.name) }
        Collections.sort(props, comparator.constructors[0].newInstance([domainClass] as Object[]))
        props.each { p -> %>
        <li class="fieldcontain">
            <span id="${p.name}-label" class="property-label"><g:message
                    code="${domainClass.propertyName}.${p.name}.labelform" default="${p.naturalName}"/></span>
            <% if (p.isEnum()) { %>
            <span class="property-value" aria-labelledby="${p.name}-label"><g:fieldValue bean="\${${propertyName}}"
                                                                                         field="${p.name}"/></span>
            <% } else if (p.oneToMany || p.manyToMany) { %>
            <g:each in="\${${propertyName}.${p.name}}" var="${p.name[0]}">
                <span class="property-value" aria-labelledby="${p.name}-label"><g:link
                        controller="${p.referencedDomainClass?.propertyName}" action="show"
                        id="\${${p.name[0]}.id}">\${${p.name[0]}?.encodeAsHTML()}</g:link></span>
            </g:each>
            <% } else if (p.manyToOne || p.oneToOne) { %>
            <span class="property-value" aria-labelledby="${p.name}-label"><g:link
                    controller="${p.referencedDomainClass?.propertyName}" action="show"
                    id="\${${propertyName}?.${p.name}?.id}">\${${propertyName}?.${p.name}?.encodeAsHTML()}</g:link></span>
            <% } else if (p.type == Boolean || p.type == boolean) { %>
            <span class="property-value" aria-labelledby="${p.name}-label"><g:formatBoolean
                    boolean="\${${propertyName}?.${p.name}}"/></span>
            <% } else if (p.type == Date || p.type == java.sql.Date || p.type == java.sql.Time || p.type == Calendar) { %>
            <span class="property-value" aria-labelledby="${p.name}-label"><amb:formatDate
                    date="\${${propertyName}?.${p.name}}"/></span>
            <% } else if (!p.type.isArray()) { %>
            <span class="property-value" aria-labelledby="${p.name}-label"><g:fieldValue bean="\${${propertyName}}"
                                                                                         field="${p.name}"/></span>
            <% } %>
        </li>
        <% } %>
    </ol>

    <g:if test="\${campiExtra}">
        <amb:extraScheda rec="\${${propertyName}}" campiExtra="\${campiExtra}"></amb:extraScheda>
    </g:if>

    <g:form>
        <fieldset class="buttons">
            <g:hiddenField name="id" value="\${${propertyName}?.id}"/>
            <g:link class="edit" action="edit" id="\${${propertyName}?.id}">
                <g:message code="${domainClass.propertyName}.edit.label"
                           default="Modifica ${domainClass.propertyName}"/>
            </g:link>
            <g:actionSubmit class="delete" action="delete"
                            value="\${message(code: 'default.button.delete.label', default: 'Delete')}"
                            onclick="return confirm('\${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
