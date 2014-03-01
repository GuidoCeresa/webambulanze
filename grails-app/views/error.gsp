<!DOCTYPE html>
<html>
<head>
    <title><g:if env="development">Grails Runtime Exception</g:if><g:else>Error</g:else></title>
    <meta name="layout" content="main">
    <g:if env="development"><link rel="stylesheet" href="${resource(dir: 'css', file: 'errors.css')}"
                                  type="text/css"></g:if>
</head>

<body>
<g:if env="development">
    <g:renderException exception="${exception}"/>
</g:if>
<g:else>
    <ul class="errors">
        <li>An error has occurred</li>
    </ul>
</g:else>
<div class="footer" role="contentinfo"></div>

<div id="page-body" role="main">
    <h1>Devi specificare l'associazione da utilizzare</h1>
    <g:if test="${flash.errors}">
        <h1>${flash.errors}</h1>
    </g:if>

</div>
</body>
</html>
