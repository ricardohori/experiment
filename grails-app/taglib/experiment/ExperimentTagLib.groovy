package experiment

import grails.converters.JSON

class ExperimentTagLib {
	static namespace="exp"
	
	def jqGrid={attrs, body ->
		def url = attrs.url

		def postData = attrs.params?:[]
		def treeGrid = attrs.treeGrid?: false
		def paginate =  Boolean.parseBoolean(attrs.paginate)
		def rowNum = paginate? "10": "0"
		def scrollOffset = attrs.scrollOffset? attrs.scrollOffset as int:0
		def altRows = attrs.altRows?:true
		def multiSelect = attrs.multiSelect?: false
		def shrinkToFit = attrs.shrinkToFit ?: true
		def forceFit = attrs.forceFit ?: true

		out << render(template: "/grid/jqGrid", model: [idGrid:attrs.id, treeGrid:treeGrid,expandColumn:attrs.expandColumn, url:url, postData:postData as JSON, colLabels:attrs.colLabels,
			columns:attrs.columns, multiSelect:multiSelect, width:attrs.width, title:attrs.title, height:attrs.height, rowNum:rowNum, scrollOffset:scrollOffset,
			altRows:altRows, shrinkToFit:shrinkToFit, forceFit:forceFit], encoding:"UTF-8")
	}

}
