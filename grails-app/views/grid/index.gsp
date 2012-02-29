<html>
  <head>
	  <meta name='layout' content='main' />
	  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
	  <title><g:message code="analiseImpacto.title"/></title>
  </head>
  <body>	
		<div id="divTabela_Nomenclatura"  class="tabela-normas" style="margin:40px">
			<exp:jqGrid id="grid" url="${createLink(controller:'grid', action:'conteudoGrid')}" colLabels="${columnLabels}"
				paginate="true" columns="${columns}" altRows="true"
				multiSelect="false" treeGrid="false" width="1500" title="Classificação sugerida" height="460" ></exp:jqGrid>
		</div>
		<script type='text/javascript'>
			$(document).ready(function(){
				/* Faz unbind do evento do jqGrid para esse header, pois ele estava impedindo a seleção do checkbox */
				$("[id='grid_ignorarCritica']").each(function(index,value){
					$(value).unbind('click')
				});
			});
		</script>
	</body>
</html>
