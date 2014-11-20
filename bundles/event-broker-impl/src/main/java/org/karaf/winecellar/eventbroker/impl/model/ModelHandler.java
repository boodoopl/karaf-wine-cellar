package org.karaf.winecellar.eventbroker.impl.model;

import org.karaf.winecellar.eventbroker.impl.common.GenericHandler;
import org.osgi.framework.BundleContext;
import org.osgi.service.event.Event;

public class ModelHandler extends GenericHandler<ModelListener> {

    public ModelHandler(BundleContext bundleContext, ModelListener listener, String className) {
        super(bundleContext, listener, new String[] {
                ModelPublisherImpl.MODEL_TOPIC_ENTITY_ADDED,
                ModelPublisherImpl.MODEL_TOPIC_ENTITY_UPDATED,
                ModelPublisherImpl.MODEL_TOPIC_ENTITY_DELETED},
                String.format("(%s=%s)", ModelPublisherImpl.ENTITY_CLASS_PROPERTY_NAME, className));
    }

    @Override
    public void handleEvent(Event event) {
        if(event.getTopic().contains(ModelPublisherImpl.MODEL_TOPIC_ENTITY_ADDED)) {
            listener.entityAdded(
                    (String)event.getProperty(ModelPublisherImpl.ENTITY_CLASS_PROPERTY_NAME),
                    event.getProperty(ModelPublisherImpl.ENTITY_PROPERTY_NAME));
        }
        else if(event.getTopic().contains(ModelPublisherImpl.MODEL_TOPIC_ENTITY_UPDATED)) {
            listener.entityUpdated(
                    (String)event.getProperty(ModelPublisherImpl.ENTITY_CLASS_PROPERTY_NAME),
                    event.getProperty(ModelPublisherImpl.ENTITY_PROPERTY_NAME));
        }
        else if(event.getTopic().contains(ModelPublisherImpl.MODEL_TOPIC_ENTITY_DELETED)) {
            listener.entityDeleted(
                    (String)event.getProperty(ModelPublisherImpl.ENTITY_CLASS_PROPERTY_NAME),
                    (Long)event.getProperty(ModelPublisherImpl.ENTITY_ID_PROPERTY_NAME));
        }
    }
}
