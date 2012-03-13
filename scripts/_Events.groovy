eventAllTestsStart = {
	def easybTestTypeClass = loadEasybTestTypeClass()
	functionalTests << easybTestTypeClass.newInstance('selenium-easyb', 'selenium')
}
