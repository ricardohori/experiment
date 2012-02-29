<!DOCTYPE html>
<html>
    <head>
        <title><g:layoutTitle default="Grails" /></title>
        <link rel="stylesheet" href="${resource(dir:'css',file:'main.css')}" />
        <link rel="shortcut icon" href="${resource(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        <script src="${resource(dir: 'js/jquery', file: 'jquery-1.4.4.js')}" type="text/javascript"></script>
        <script src="${resource(dir: 'js/jquery', file: 'jquery-ui-1.8.13.custom.min.js')}" type="text/javascript"></script>
        <link rel="stylesheet" type=text/css href="${resource(dir: 'js/jqGrid/css', file: 'ui.multiselect.css')}" />
		<link rel="stylesheet" type=text/css href="${resource(dir: 'js/jqGrid/css', file: 'ui.jqgrid.css')}" />
		<script type="text/javascript" src="${resource(dir: 'js/jqGrid/js/i18n', file: 'grid.locale-pt-br.js')}"></script>
		<script type="text/javascript" src="${resource(dir: 'js/jqGrid/plugins', file: 'ui.multiselect.js')}"></script>
		<script type="text/javascript" src="${resource(dir: 'js/jqGrid/js', file: 'jquery.jqGrid.src.js')}"></script>
		<script type="text/javascript" src="${resource(dir: 'js/jqGrid/plugins', file: 'jquery.tablednd.js')}"></script>
		<script type="text/javascript" src="${resource(dir: 'js/jqGrid/plugins', file: 'jquery.contextmenu.js')}"></script>
		<script type="text/javascript" src="${resource(dir: 'js/jqGrid/plugins', file: 'jquery.searchFilter.js')}"></script>
		<script type="text/javascript" src="${resource(dir: 'js/jqGrid/src', file: 'grid.treegrid.js')}"></script>
		
		<script type="text/javascript" src="${resource(dir: 'js/jqGrid/js', file: 'jquery.jqGrid.fluid.js')}"></script>
        <g:layoutHead />
        <g:javascript library="application" />
    </head>
    <body>
        <div id="spinner" class="spinner" style="display:none;">
            <img src="${resource(dir:'images',file:'spinner.gif')}" alt="${message(code:'spinner.alt',default:'Loading...')}" />
        </div>
        <div id="grailsLogo"><a href="http://grails.org"><img src="${resource(dir:'images',file:'grails_logo.png')}" alt="Grails" border="0" /></a></div>
        <g:layoutBody />
    </body>
</html>