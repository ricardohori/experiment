package experiment

import br.com.futuresolutions.experiment.MercadoriaCliente
import experiment.especificacao.grid.ClassificacaoSugeridaJqGrid
import grails.converters.JSON

class GridController {

	def grid = new ClassificacaoSugeridaJqGrid(MercadoriaCliente.class, 'classificacaoSugerida')
	
    def index = { 
		render(view:'index',model:[columnLabels:grid.gridLabelsJson, columns:grid.estruturaGridJson])
	}
	
	def conteudoGrid = {
		def dados = []
		def checkIgnorar = "<input type='checkbox' name='ignorar' onChange='selecionarCheck(this)' id='ignorar' />"
		dados << ['01', 'Cavalos nobres', '0101.10.15', '', 'Supressão', 'Decreto RFB nº 6024/2007, art. 1º', '0101.10.10', '', '01/05/2007',checkIgnorar,checkIgnorar]
		dados << ['02', 'Touros nobres', '0101.10.15', '01', 'Supressão', 'Decreto RFB nº 6024/2007, art. 1º', '0101.10.10', '01', '01/05/2007',checkIgnorar,checkIgnorar]
		dados << ['03', 'Cachorros nobres', '0101.10.15', '03', 'Supressão', 'Decreto RFB nº 6024/2007, art. 1º', '0101.10.10', '02', '01/05/2007',checkIgnorar,checkIgnorar]
		
		render grid.montarJsonDados(dados)
	}
}
