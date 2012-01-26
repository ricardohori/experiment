package experiment.parser

class CategoriaParser {

	def static parseCategoria(file){
		def inicioCategoria = false
		def ulCount = 0
		def firstUl = false
		def nivelFinal = false
		def lastCount = 0
		def levelChangeDown = false
		def levelChangeUp = false
		def appendedTitle = false
		def endCategoria
		def countCategoriaMae = 1
		def timesOpened = 0
		def noChange = true
		def output = new File("test/unit/resources/categoria/DicionarioCategorias.groovy")
		file.readLines().each{line->
			if((line =~ /<h4>/) || (line =~ /<ul>/) || (line =~ /<\/ul>/) || (line =~ /title=".+"/)){

				if((line =~ /<h4>/)){
					timesOpened++
					if(timesOpened == 2 && noChange){
						output.append "\n]\n\n"
						timesOpened = 1
						countCategoriaMae++
					}else{
						timesOpened = 1
						noChange = true
					}
					inicioCategoria = true
				}


				if((line =~ /<ul>/)){
					noChange = false
					firstUl = ulCount?false:true
					ulCount++
					output.append ":[\n"
					levelChangeDown = true
				}

				if((line =~ /<\/ul>/)){
					ulCount--
					nivelFinal = true
					if(appendedTitle){
						output.append ":\"\""
						appendedTitle = false
					}
					output.append "\n]"
					levelChangeUp = true
					if(!ulCount) endCategoria = true
				}

				if(!(levelChangeDown||levelChangeUp) && appendedTitle){
					output.append ":\"\","
					appendedTitle = false
				}

				if(ulCount){
					def titleMatcher = (line =~ /title=".+"/)
					if(titleMatcher){
						levelChangeDown = false
						if(lastCount == ulCount){
							def title = titleMatcher[0].toString()
							title = title.replaceAll("title=","").replaceAll("\"","").trim()
							if(levelChangeUp) output.append ",\n"
							output.append "'${title}'"
							appendedTitle = true
							levelChangeUp = false
						}
					}
					lastCount = ulCount
				}

				if(inicioCategoria){
					def titleMatcher = (line =~ /title=".+"/)
					if(titleMatcher) {
						def title = titleMatcher[0].toString()
						title = title.replaceAll("title=","").replaceAll("\"","").trim()
						output.append "def categorias${countCategoriaMae} = [\n'${title}'"
						inicioCategoria = false
					}
				}

				if(!inicioCategoria && endCategoria){
					output.append "\n]\n\n"
					endCategoria = false
					countCategoriaMae++
					inicioCategoria = false
					ulCount = 0
					firstUl = false
					nivelFinal = false
					lastCount = 0
					levelChangeDown = false
					levelChangeUp = false
					appendedTitle = false
				}
			}
		}
	}
}
