package experiment.listener

import grails.test.*
import br.com.futuresolutions.experiment.MercadoriaCliente

class MercadoriaIntegrationTests extends GroovyTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testListener() {
		def mercadoria = new MercadoriaCliente(codigo:'01', descricao:'Descrição', ncm:'0101.10.10').save(flush:true)
		
		mercadoria.codigo = '02'
		mercadoria.save(flush:true)
		
		mercadoria.ncm = '0101.10.20'
		mercadoria.save(flush:true)
    }
}
