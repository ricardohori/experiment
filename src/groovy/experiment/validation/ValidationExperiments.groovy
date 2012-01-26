package experiment.validation
import java.text.SimpleDateFormat



class ValidationExperiments {
	
	public static void main(String[] args) {
		def closTotalBefore = 0
		def closTotalAfter = 0
		def ifTotalBefore = 0
		def ifTotalAfter = 0
		def closWinsBefore = 0
		def closWinsAfter = 0
		def ifWinsBefore = 0
		def ifWinsAfter = 0
		def tempClos
		def tempIf
		1.times{index->
			if(index%2==0){
				println "-----${index} time!-----"
				tempIf = validationWithoutElse()
				tempClos = validation()
			}else{
				println "-----${index} time!-----"
				tempClos = validation()
				tempIf = validationWithoutElse()
			}
			closTotalBefore += tempClos.before
			closTotalAfter += tempClos.after
			ifTotalBefore += tempIf.before
			ifTotalAfter += tempIf.after
			
			if(tempClos.before > tempIf.before){
				ifWinsBefore++
			}else{
				closWinsBefore++
			}
			
			if(tempClos.after > tempIf.after){
				ifWinsAfter++
			}else{
				closWinsAfter++
			}
			println ""
		}
		println "Clos before total time = ${closTotalBefore}"
		println "Clos after total time = ${closTotalAfter}"
		println "If before total time = ${ifTotalBefore}"
		println "If after total time = ${ifTotalAfter}"
		println "Clos total wins before = ${closWinsBefore}"
		println "Clos total wins after = ${closWinsAfter}"
		println "If total wins before = ${ifWinsBefore}"
		println "If total wins after = ${ifWinsAfter}"
		
		println "--------End----------"
	}
	
	def static sdf = new SimpleDateFormat("sSSS")

	def static validation(){
		def validacoes = []
		
		def init = 	Integer.parseInt(sdf.format(new Date()).toString())
		5000.times{
			validacoes << {false?{throw new RuntimeException()}:null}
		}
		validacoes << {true?{throw new IllegalStateException()}:null}
		
		def beforeTry = Integer.parseInt(sdf.format(new Date()))
		def difBeforeTry = beforeTry-init
		println "Difference init-beforeTry with else = ${difBeforeTry}ms"
		def difAfterTry
		try{
			validacoes.each{
				it()?.call()
			}
		}catch(all){
			def afterTry = Integer.parseInt(sdf.format(new Date()))
			difAfterTry = afterTry-beforeTry
			println "Difference beforeTry-afterTry with else = ${difAfterTry}ms"
		}
		[before:difBeforeTry,after:difAfterTry]
	}
	
	def static validationWithoutElse(){
		def validacoes = []
		
		def init = 	Integer.parseInt(sdf.format(new Date()))
		5000.times{
			validacoes << {if(false){throw new RuntimeException()}}
		}
		validacoes << {if(true){throw new IllegalStateException()}}
		
		def beforeTry = Integer.parseInt(sdf.format(new Date()))
		def difBeforeTry = beforeTry-init
		println "Difference init-beforeTry = ${difBeforeTry}ms"
		def difAfterTry
		try{
			validacoes.each{
				it()
			}
		}catch(all){
			def afterTry = Integer.parseInt(sdf.format(new Date()))
			difAfterTry = afterTry-beforeTry
			println "Difference beforeTry-afterTry = ${difAfterTry}ms"
		}
		[before:difBeforeTry,after:difAfterTry]
	}
	
}
