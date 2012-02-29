<table id="${idGrid}"></table>
<div id="pager${idGrid.capitalize()}"></div>
<g:javascript>
  	$(document).ready(function(){
	  jQuery("#${idGrid}").jqGrid({
		    treeGrid: ${treeGrid},
		    treeGridModel: 'adjacency',
   			ExpandColumn : '${expandColumn}',
			mtype: "POST",
		   	url:"${url}",
		   	postData:${postData},
			datatype: "json",
		   	colNames:${colLabels },
		   	colModel:${columns },
		   	multiselect:${multiSelect },
		   	rowNum:${rowNum},
		   	pager: '#pager${idGrid.capitalize()}',
			loadtext: "Carregando...",
		    viewrecords: true,
		    width: ${width},
		    height: ${height},
		    caption:"${title}",
			pgtext : "Página {0} de {1}",
			scrollOffset:${scrollOffset},
			altRows:${altRows},
			shrinkToFit:${shrinkToFit},
			forceFit:${forceFit},
			gridComplete: function(){
		       ${idGrid}_gridComplete()
		    },
            onSelectRow: function(rowid, status){
               ${idGrid}_rowSelected(rowid, status)
            }
		});
		jQuery("#${idGrid}").jqGrid('navGrid',"#pager${idGrid.capitalize()}",{edit:false,add:false,del:false,search:false,refresh:false});
		jQuery(".ui-jqgrid-titlebar-close").remove();
	});
	
	function ${idGrid}_gridComplete(){
		<!-- por padrão não faz nada -->
	}
</g:javascript>
<link type="text/css" href="${resource(dir: 'css/jqGrid/custom-theme', file: 'jquery-ui-1.8.14.custom.css')}" rel="stylesheet" />
<style type="text/css">
/* JQGRID */
/* Sobrescreve o css do jqGrid para quebra de linha automática */
th{
	font: 11px verdana, arial, helvetica, sans-serif;
}

.ui-jqgrid-titlebar{
	font-size: 12px;
}

.ui-paging-info{
	font-size: 12px;
}

.ui-jqgrid tr.jqgrow td{
	white-space: normal;
	font: 11px verdana, arial, helvetica, sans-serif;
}

.ui-widget-content a { 
	color: #0000BB; 
	font-weight: bold;
}

/* Sobrescreve o css do jqGrid para não aparecer a seleção no grid */
.ui-state-highlight, .ui-widget-content .ui-state-highlight, .ui-widget-header .ui-state-highlight{
	border: 1px solid #AAAAAA;
	background: none;
}

.ui-priority-secondary {
	background: #EEEEEE;
	opacity: 1 !important;
}

.tabela-normas td {
	border: 0px none;
}
</style>