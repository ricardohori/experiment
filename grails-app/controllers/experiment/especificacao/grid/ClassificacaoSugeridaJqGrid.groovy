package experiment.especificacao.grid

import grails.converters.JSON
import groovy.lang.Closure;


class ClassificacaoSugeridaJqGrid extends DefaultJqGrid {

	ClassificacaoSugeridaJqGrid(domain, idGrid){
		super(domain, idGrid)
		addColumn('codigoMercadoria','Código da mercadoria', [name:'codigoMercadoria', sortable:true, align:'center', title:false, width: 10])
		addColumn('descricaoCompleta','Descrição completa', [name:'descricaoCompleta', sortable:true, align:'center', title:false, width: 14])
		addColumn('ncmEmUso','NCM em uso', [name:'ncmEmUso', sortable:true, align:'center', title:false, width: 9])
		addColumn('exIpiEmUso','Ex de IPI em uso', [name:'exIpiEmUso', sortable:true, align:'center', title:false, width: 10])
		addColumn('situacao','Situação', [name:'exIpiEmUso', sortable:true, align:'center', title:false, width: 10])
		addColumn('baseLegal','Base legal', [name:'exIpiEmUso', sortable:true, align:'center', title:false, width: 10])
		addColumn('sugestaoNcm','NCM sugerida', [name:'dtCritica', sortable:true, align:'center', title:false, width: 9])
		addColumn('sugestaoExIpi','Ex de IPI sugerido', [name:'dtCritica', sortable:true, align:'center', title:false, width: 9])
		addColumn('dtSugestao','Data da sugestão', [name:'dtCritica', sortable:true, align:'center', title:false, width: 9])
		def labelAceitar = "<span style='valign:top'>Aceitar sugestão</span><input style='margin-left:5px' type='checkbox' id='ignorarTodos'/>"
		addColumn('',labelAceitar, [name:'ignorarCritica', sortable:false, align:'center', title:false, width: 10])
		def labelRejeitar = "<span style='valign:top'>Rejeitar sugestão</span><input style='margin-left:5px' type='checkbox' id='ignorarTodos'/>"
		addColumn('',labelRejeitar, [name:'ignorarCritica', sortable:false, align:'center', title:false, width: 10])
	}
	
	@Override
	def montarJsonDados(dados){
		dados = montarTabelaDeDados(dados)
		def jsonData = [rows: dados]

		return jsonData as JSON
	}
	
	@Override
	def montarTabelaDeDados(dados){
		def json = []
		dados.each{dado->
			json << [cell:dado]
		}
		return json
	}
} 
