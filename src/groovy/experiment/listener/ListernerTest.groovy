package experiment.listener
import org.hibernate.event.PreUpdateEvent
import org.hibernate.event.PreUpdateEventListener

class ListernerTest implements PreUpdateEventListener {

	@Override
	public boolean onPreUpdate(PreUpdateEvent event){
		println "here i go"
	}
}
