package experiment.listener

import org.hibernate.event.AbstractEvent;

interface EventConsumer {
	public void consume(AbstractEvent event);
}
