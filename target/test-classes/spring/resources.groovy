// Place your Spring DSL code here
beans = {
	
	mercadoriaListener(experiment.listener.MercadoriaListener){
		eventBroker = ref("eventBroker")
	}
}
