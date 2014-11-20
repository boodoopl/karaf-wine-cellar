package org.karaf.winecellar.eventbroker.impl.common;

import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;

import java.util.Map;

public class GenericPublisher {

    EventAdmin eventAdmin;

    public GenericPublisher(EventAdmin eventAdmin) {
        this.eventAdmin = eventAdmin;
    }

    protected void publishMessage(String topic, Map<String, ?> properties) {
        Event event = new Event(topic, properties);
        eventAdmin.postEvent(event);
    }
}

