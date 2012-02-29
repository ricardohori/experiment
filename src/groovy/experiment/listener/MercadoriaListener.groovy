package experiment.listener

import org.springframework.beans.factory.InitializingBean;

class MercadoriaListener implements InitializingBean {
	
	def eventBroker
	
	@Override
	void afterPropertiesSet(){
		eventBroker.subscribe('hibernate.postInsert.MercadoriaCliente'){event->
			println event
		}
	}
}
