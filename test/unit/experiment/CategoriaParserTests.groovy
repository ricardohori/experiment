package experiment

import experiment.parser.CategoriaParser;
import grails.test.*

class CategoriaParserTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testParseCategoria() {
		def categoriaFile = new File("test/unit/resources/categoria/categoria1.html")
		CategoriaParser.parseCategoria categoriaFile

    }
}
