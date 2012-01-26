package experiment.listener

import org.springframework.beans.factory.InitializingBean

import br.com.futuresolutions.experiment.MercadoriaCliente

import com.infusion.util.event.EventBroker

class MercadoriaListener implements InitializingBean{

	EventBroker eventBroker
	Boolean alterouNcm
	void afterPropertiesSet(){
		eventBroker.subscribe ("hibernate.preUpdate.MercadoriaCliente"){event, broker->
			MercadoriaCliente mercadoria = event.entity
			if(mercadoria.isDirty('ncm')) alterouNcm = true
		}
		
		eventBroker.subscribe ("hibernate.postUpdate.MercadoriaCliente"){event, broker->
			if(alterouNcm){
				println "Alterou a NCM!!!"
				alterouNcm = null
			}
		}
	}
}
