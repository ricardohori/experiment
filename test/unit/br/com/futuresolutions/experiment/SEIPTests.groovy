package br.com.futuresolutions.experiment

import grails.test.*

import java.text.SimpleDateFormat

class SEIPTests extends GrailsUnitTestCase {
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testExcelImport2003() {
		def excelFile = this.class.getClassLoader().getResourceAsStream("test2003.xls")
		testWorkbook excelFile
    }
	
	void testExcelImport2007() {
		def excelFile = this.class.getClassLoader().getResourceAsStream("test2007.xlsx")
		testWorkbook excelFile
	}
	
	private void testWorkbook(excelFile){
		def sheetStructureList = []
		def testSheet = [
			name:"CDs",
			header:[
				A:"Album Name",
				B:"Artist",
				C:"Year",
				D:"Sold"
				],
			dateColumns:["Year"],
			startRow:2
		]
		def testSheet2 = [
			name:"Books",
			header:[
				A:"Book Name",
				B:"Author",
				C:"Year"
				],
			dateColumns:["Year"],
			startRow:2
		]
		sheetStructureList << testSheet
		sheetStructureList << testSheet2
		
		def workbook = SimpleExcelImport.excelImport(excelFile, sheetStructureList)
		
		assertNotNull "CDs sheet shouldn't be null!",workbook.CDs
		assertNotNull "Books sheet shouldn't be null!",workbook.Books
		
		def cds = workbook.CDs
		
		assertTrue "Album Name should be a String!",cds[0]["Album Name"] instanceof String
		assertEquals "First album should be Rubber Soul!","Rubber Soul",cds[0]["Album Name"]
		assertEquals "Second album should be Revolver!","Revolver",cds[1]["Album Name"]
		assertEquals "Third album should be Sgt. Pepper's Lonely Hearts Club Band!","Sgt. Pepper's Lonely Hearts Club Band",cds[2]["Album Name"]
		assertEquals "Fourth album should be Magical Mystery Tour!","Magical Mystery Tour",cds[3]["Album Name"]
		assertEquals "Fifth album should be White Album!","White Album",cds[4]["Album Name"]
		
		assertTrue "Artist should be a String!",cds[0]["Artist"] instanceof String
		assertEquals "First artist should be The Beatles!","The Beatles",cds[0]["Artist"]
		assertEquals "Second artist should be The Beatles!","The Beatles",cds[1]["Artist"]
		assertEquals "Third artist should be The Beatles!","The Beatles",cds[2]["Artist"]
		assertEquals "Fourth artist should be The Beatles!","The Beatles",cds[3]["Artist"]
		assertEquals "Fifth artist should be The Beatles!","The Beatles",cds[4]["Artist"]
		
		def sdf = new SimpleDateFormat("dd/MM/yyyy")
		assertTrue "Artist should be a Date!",cds[0]["Year"] instanceof Date
		assertEquals "First year should be 18/05/1965!",sdf.parse("18/05/1965"),cds[0]["Year"]
		assertEquals "Second year should be 18/05/1966!",sdf.parse("19/05/1966"),cds[1]["Year"]
		assertEquals "Third year should be 18/05/1967!",sdf.parse("20/05/1967"),cds[2]["Year"]
		assertEquals "Fourth year should be 18/05/1967!",sdf.parse("20/05/1967"),cds[3]["Year"]
		assertEquals "Fifth year should be 18/05/1968!",sdf.parse("21/05/1968"),cds[4]["Year"]
		
		assertTrue "Sold should be a Numeric value!",cds[0]["Sold"] instanceof Number
		assertEquals "First sold should be 23880!",23880,cds[0]["Sold"]
		assertEquals "Second sold should be 24246!",24246,cds[1]["Sold"]
		assertEquals "Third sold should be 24612!",24612,cds[2]["Sold"]
		assertEquals "Fourth sold should be 24612!",24612,cds[3]["Sold"]
		assertEquals "Fifth sold should be 24979!",24979,cds[4]["Sold"]
		
		def books = workbook.Books
		
		assertTrue "Book Name should be a String!",books[0]["Book Name"] instanceof String
		assertEquals "First book name should be The Gates of Rome!","The Gates of Rome",books[0]["Book Name"]
		assertEquals "Second book name should be The Death of Kings!","The Death of Kings",books[1]["Book Name"]
		assertEquals "Third book name should be The Field of Swords!","The Field of Swords",books[2]["Book Name"]
		assertEquals "Fourth book name should be The Gods of War!","The Gods of War",books[3]["Book Name"]
		
		assertTrue "Author should be a String!",books[0]["Author"] instanceof String
		assertEquals "First author should be Conn Iggulden!","Conn Iggulden",books[0]["Author"]
		assertEquals "Second author should be Conn Iggulden!","Conn Iggulden",books[1]["Author"]
		assertEquals "Third author should be Conn Iggulden!","Conn Iggulden",books[2]["Author"]
		assertEquals "Fourth author should be Conn Iggulden!","Conn Iggulden",books[3]["Author"]
		
		assertTrue "Year should be a Date!",books[0]["Year"] instanceof Date
		assertEquals "First year should be 25/06/2005!",sdf.parse("25/06/2005"),books[0]["Year"]
		assertEquals "Second year should be 26/06/2006!",sdf.parse("26/06/2006"),books[1]["Year"]
		assertEquals "Third year should be 27/06/2007!",sdf.parse("27/06/2007"),books[2]["Year"]
		assertEquals "Fourth year should be 28/06/2008!",sdf.parse("28/06/2008"),books[3]["Year"]
	}
}
