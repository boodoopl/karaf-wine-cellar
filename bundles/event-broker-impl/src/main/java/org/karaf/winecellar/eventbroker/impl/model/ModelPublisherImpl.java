package org.karaf.winecellar.eventbroker.impl.model;

import org.karaf.winecellar.eventbroker.impl.common.GenericPublisher;
import org.osgi.service.event.EventAdmin;

import java.util.HashMap;
import java.util.Map;

public class ModelPublisherImpl extends GenericPublisher implements ModelPublisher {

    public static final String ENTITY_CLASS_PROPERTY_NAME = "class";
    public static final String ENTITY_PROPERTY_NAME = "entity";
    public static final String ENTITY_ID_PROPERTY_NAME = "entityId";

    public static final String MODEL_TOPIC_BASE  = "MODEL/ENTITY";
    public static final String MODEL_TOPIC_ENTITY_ADDED = MODEL_TOPIC_BASE + "/ADDED";
    public static final String MODEL_TOPIC_ENTITY_UPDATED = MODEL_TOPIC_BASE + "/UPDATED";
    public static final String MODEL_TOPIC_ENTITY_DELETED = MODEL_TOPIC_BASE + "/DELETED";

    public ModelPublisherImpl(EventAdmin eventAdmin) {
        super(eventAdmin);
    }

    @Override
    public void entityAdded(String className, Object entity) {
        Map props = new HashMap();
        props.put(ENTITY_CLASS_PROPERTY_NAME, entity.getClass().getName());
        props.put(ENTITY_PROPERTY_NAME, entity);
        publishMessage(MODEL_TOPIC_ENTITY_ADDED, props);
    }

    @Override
    public void entityUpdated(String className, Object entity) {
        Map props = new HashMap();
        props.put(ENTITY_CLASS_PROPERTY_NAME, entity.getClass().getName());
        props.put(ENTITY_PROPERTY_NAME, entity);
        publishMessage(MODEL_TOPIC_ENTITY_UPDATED, props);
    }

    @Override
    public void entityDeleted(String className, long entityId) {
        Map props = new HashMap();
        props.put(ENTITY_CLASS_PROPERTY_NAME, className);
        props.put(ENTITY_ID_PROPERTY_NAME, entityId);
        publishMessage(MODEL_TOPIC_ENTITY_DELETED, props);
    }
}
