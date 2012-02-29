package experiment.especificacao.grid

import grails.converters.JSON

import java.math.RoundingMode

class DefaultJqGrid {
	def private domainClass
	def private idGrid
	def private columns = []

	DefaultJqGrid(Class domainClass, idGrid){
		this.domainClass = domainClass
		this.idGrid = idGrid
	}
	
	/**
	 * Recebe o conteúdo necessário para cada coluna do grid.
	 * Opcionalmente utiliza um formatador, recebido como último parâmetro.
	 *
	 * @param valueProperty propriedade que segura o valor que deve aparecer no grid
	 * @param label label da da coluna
	 * @param structure estrutura de dados no grid
	 * @param opcional, define como exibir o valor na célula. Ver {@link GridFormatter}
	 */
	def addColumn(valueProperty, label, structure, formatter=null){
		columns << [valueProperty:valueProperty, label:label, structure:structure, formatter:formatter]
	}

	def getNames(){
		this.columns.collect{it.structure.name}
	}

	def getValueProperties(){
		this.columns.collect{it.valueProperty}
	}

	def getGridLabelsJson(){
		this.columns.collect{ it.label } as JSON
	}

	def getEstruturaGridJson(){
		this.columns.collect{ it.structure } as JSON
	}

	def montarJsonDados(params, Closure criteria){
		def qtdPaginas = 0
		def total = domainClass.createCriteria().count{
			criteria.delegate = delegate
			criteria()
		}

		def parametrosDaBusca = [:]

		if(params.sidx){
			parametrosDaBusca.sort = params.sidx - "${idGrid}_"
			parametrosDaBusca.order = params.sord
		}

		def rows = params.rows as Integer
		def page = params.page as Integer

		if(rows){
			parametrosDaBusca.max = rows
			parametrosDaBusca.offset = page * rows - rows
			qtdPaginas = new BigDecimal(total/rows).setScale(0, RoundingMode.UP)
		}

		def dados = domainClass.createCriteria().list(parametrosDaBusca){
			criteria.delegate = delegate
			criteria()
		}

		dados = montarTabelaDeDados(dados)

		def jsonData = [page: page, rows: dados, total:qtdPaginas, records: total]

		return jsonData as JSON
	}
	
	def montarTabelaDeDados(dados){
		def json = []
		for(dado in dados){
			def cell = []
			for(column in this.columns){
				def property = column.valueProperty
				def val
				def tokens = property.toString().split("\\.")
				if(tokens.size() == 1){
					val = dado.getProperty(property.toString())
				}else if(tokens.size() > 1){
					for(token in tokens){
						val = val? val.getProperty(token.toString()): dado.getProperty(token.toString())
					}
				}
				if(column.formatter){
					val = column.formatter.format(dado, val)
				}
				cell << val
			}
			json << [cell:cell]
		}
		return json
	}
}
