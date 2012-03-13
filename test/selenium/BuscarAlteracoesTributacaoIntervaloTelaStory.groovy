import static junit.framework.Assert.*
holder  = grails.plugins.selenium.SeleniumHolder.selenium

def selenium = holder.selenium

scenario "Poder consultar alterações de tributação informando intervalo", {
	given "que eu esteja na tela de consulta",{
		selenium.open "http://localhost:8080/experiment/grid"
	}
	then "devo poder informar um intervalo para busca",{
		println selenium.isTextPresent('Al�quota')
	}
}
