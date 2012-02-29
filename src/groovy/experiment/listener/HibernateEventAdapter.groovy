package experiment.listener

import java.util.Map
import java.util.Set

import org.hibernate.HibernateException
import org.hibernate.SessionFactory
import org.hibernate.event.*
import org.hibernate.event.LoadEventListener.LoadType;
import org.springframework.beans.factory.InitializingBean;

public class HibernateEventAdapter implements AutoFlushEventListener, DeleteEventListener, DirtyCheckEventListener, EvictEventListener, FlushEventListener, FlushEntityEventListener, LoadEventListener, InitializeCollectionEventListener, LockEventListener, MergeEventListener, PersistEventListener, PostDeleteEventListener, PostInsertEventListener, PostLoadEventListener, PostUpdateEventListener, PreDeleteEventListener, PreInsertEventListener, PreLoadEventListener, PreUpdateEventListener, RefreshEventListener, ReplicateEventListener, SaveOrUpdateEventListener, InitializingBean  {

	SessionFactory sessionFactory
	
	void afterPropertiesSet(){
		registerListeners()
	}
	
	private void registerListeners() {
		HibernateEventUtil.addListener(sessionFactory, "autoFlush", this);
		HibernateEventUtil.addListener(sessionFactory, "delete", this);
		HibernateEventUtil.addListener(sessionFactory, "dirtyCheck", this);
		HibernateEventUtil.addListener(sessionFactory, "evict", this);
		HibernateEventUtil.addListener(sessionFactory, "flush", this);
		HibernateEventUtil.addListener(sessionFactory, "flushEntity", this);
		HibernateEventUtil.addListener(sessionFactory, "load", this);
		HibernateEventUtil.addListener(sessionFactory, "initializeCollection", this);
		HibernateEventUtil.addListener(sessionFactory, "lock", this);
		HibernateEventUtil.addListener(sessionFactory, "merge", this);
		HibernateEventUtil.addListener(sessionFactory, "persist", this);
		HibernateEventUtil.addListener(sessionFactory, "postDelete", this);
		HibernateEventUtil.addListener(sessionFactory, "postInsert", this);
		HibernateEventUtil.addListener(sessionFactory, "postLoad", this);
		HibernateEventUtil.addListener(sessionFactory, "postUpdate", this);
		HibernateEventUtil.addListener(sessionFactory, "preDelete", this);
		HibernateEventUtil.addListener(sessionFactory, "preInsert", this);
		HibernateEventUtil.addListener(sessionFactory, "preLoad", this);
		HibernateEventUtil.addListener(sessionFactory, "preUpdate", this);
		HibernateEventUtil.addListener(sessionFactory, "refresh", this);
		HibernateEventUtil.addListener(sessionFactory, "replicate", this);
		HibernateEventUtil.addListener(sessionFactory, "saveOrUpdate", this);
	}

	public void onAutoFlush(AutoFlushEvent autoFlushEvent) throws HibernateException {
		handleEvent(autoFlushEvent, "autoFlush")
	}

	public void onDelete(DeleteEvent deleteEvent) throws HibernateException {
		handleEvent(deleteEvent.getObject(), deleteEvent, "delete")
	}

	public void onDelete(DeleteEvent deleteEvent, Set set) throws HibernateException {
		onDelete(deleteEvent)
	}

	public void onDirtyCheck(DirtyCheckEvent dirtyCheckEvent) throws HibernateException {
		handleEvent(dirtyCheckEvent, "dirtyCheck")
	}

	public void onEvict(EvictEvent evictEvent) throws HibernateException {
		handleEvent(evictEvent.getObject(), evictEvent, "evict")
	}

	public void onFlush(FlushEvent flushEvent) throws HibernateException {
		handleEvent(flushEvent, "flush")
	}

	public void onFlushEntity(FlushEntityEvent flushEntityEvent) throws HibernateException {
		handleEvent(flushEntityEvent.getEntity(), flushEntityEvent, "flushEntity")
	}

	public void onInitializeCollection(InitializeCollectionEvent initializeCollectionEvent) throws HibernateException {
		handleEvent(initializeCollectionEvent.getAffectedOwnerEntityName(), initializeCollectionEvent, "initializeCollection")
	}

	public void onLoad(LoadEvent loadEvent, LoadType loadType) throws HibernateException {
		handleEvent(loadEvent.getEntityClassName(), loadEvent, "load")
	}

	public void onLock(LockEvent lockEvent) throws HibernateException {
		handleEvent(lockEvent.getEntityName(), lockEvent, "lock")
	}

	public void onMerge(MergeEvent mergeEvent) throws HibernateException {
		handleEvent(mergeEvent.getEntity(), mergeEvent, "merge")
	}

	public void onMerge(MergeEvent mergeEvent, Map map) throws HibernateException {
		onMerge(mergeEvent)
	}

	public void onPersist(PersistEvent persistEvent) throws HibernateException {
		handleEvent(persistEvent.getObject(), persistEvent, "persist")
	}

	public void onPersist(PersistEvent persistEvent, Map map) throws HibernateException {
		onPersist(persistEvent)
	}

	public void onPostDelete(PostDeleteEvent postDeleteEvent) {
		handleEvent(postDeleteEvent.getEntity(), postDeleteEvent, "postDelete")
	}

	public void onPostInsert(PostInsertEvent postInsertEvent) {
		handleEvent(postInsertEvent.getEntity(), postInsertEvent, "postInsert")
	}

	public void onPostLoad(PostLoadEvent postLoadEvent) {
		handleEvent(postLoadEvent.getEntity(), postLoadEvent, "postLoad")
	}

	public void onPostUpdate(PostUpdateEvent postUpdateEvent) {
		handleEvent(postUpdateEvent.getEntity(), postUpdateEvent, "postUpdate")
	}

	public boolean onPreDelete(PreDeleteEvent preDeleteEvent) {
		return handleEvent(preDeleteEvent.getEntity(), preDeleteEvent, "preDelete")
	}

	public boolean onPreInsert(PreInsertEvent preInsertEvent) {
		return handleEvent(preInsertEvent.getEntity(), preInsertEvent, "preInsert")
	}

	public void onPreLoad(PreLoadEvent preLoadEvent) {
		handleEvent(preLoadEvent.getEntity(), preLoadEvent, "preLoad")
	}

	public boolean onPreUpdate(PreUpdateEvent preUpdateEvent) {
		return handleEvent(preUpdateEvent.getEntity(), preUpdateEvent, "preUpdate")
	}

	public void onRefresh(RefreshEvent refreshEvent) throws HibernateException {
		handleEvent(refreshEvent, "refresh")
	}

	public void onRefresh(RefreshEvent refreshEvent, Map map) throws HibernateException {
		handleEvent(refreshEvent, "refresh")
	}

	public void onReplicate(ReplicateEvent replicateEvent) throws HibernateException {
		handleEvent(replicateEvent.getObject(), replicateEvent, "replicate")
	}

	public void onSaveOrUpdate(SaveOrUpdateEvent saveOrUpdateEvent) throws HibernateException {
		handleEvent(saveOrUpdateEvent.getEntity(), saveOrUpdateEvent, "saveOrUpdate")
	}

	def eventBroker

	private void handleEvent(AbstractEvent event, String eventName) {
		handleEvent(null, event, eventName)
	}

	private boolean handleEvent(String entityName, AbstractEvent event, String eventName) {
		StringBuffer finalEventName = new StringBuffer()
		eventBroker.publish("hibernate", event)
		
		finalEventName.append("hibernate.").append(eventName)
		eventBroker.publish(finalEventName.toString(), event)
		
		if (entityName) {
			finalEventName.append(".").append(entityName)
			eventBroker.publish(finalEventName.toString(), event)
		}


		return false
	}

	private boolean handleEvent(Object entity, AbstractEvent event, String eventName) {
		String entityName = ''
		if (entity != null) {
			entityName = entity.getClass().getSimpleName()
		}
		return handleEvent(entityName, event, eventName)
	}
}
