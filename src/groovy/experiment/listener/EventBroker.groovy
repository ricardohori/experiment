package experiment.listener

import org.hibernate.event.AbstractEvent

class EventBroker{

	def static Map consumers = [:]

	public void publish(Object event) {
		publish(event.getClass().getSimpleName(), event);
	}

	public void publish(String eventName, Object event) {
		def consumersForEvent = consumers[eventName]
		if(consumersForEvent) consumersForEvent*.consume event
	}
	
	public void subscribe(String eventType, Closure consumer) {
		def eventConsumer = new EventConsumer(){
			public void consume(AbstractEvent event){
				consumer.call(event)
			}
		}
		subscribe(eventType, eventConsumer)
	}

	public void subscribe(String eventType, EventConsumer consumer) {
		if(consumers[eventType]){
			consumers[eventType] << eventConsumer
		}else{
			consumers[eventType] = [consumer]
		}
	}
}
