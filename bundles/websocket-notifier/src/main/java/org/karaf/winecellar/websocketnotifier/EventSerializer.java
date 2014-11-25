package org.karaf.winecellar.websocketnotifier;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.karaf.winecellar.eventbroker.impl.model.ModelPublisherImpl;
import org.osgi.service.event.Event;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

public class EventSerializer {

    private List<String> eventPropertyFilter = new ArrayList<String>();

    public EventSerializer() {
        eventPropertyFilter.add(ModelPublisherImpl.ENTITY_CLASS_PROPERTY_NAME);
        eventPropertyFilter.add(ModelPublisherImpl.ENTITY_ID_PROPERTY_NAME);
        eventPropertyFilter.add(ModelPublisherImpl.ENTITY_PROPERTY_NAME);
    }

    public void eventToJSON(Event event, Exchange exchange) {

        SimpleEvent simpleEvent = new SimpleEvent();
        simpleEvent.setTopic(event.getTopic());

        for(String propertyName : event.getPropertyNames()) {
            if (eventPropertyFilter.contains(propertyName)) {
                simpleEvent.addProperty(propertyName, event.getProperty(propertyName));
            }
        }

        ObjectMapper mapper = new ObjectMapper();

        StringWriter stringWriter = new StringWriter();
        try {
            mapper.writeValue(stringWriter, simpleEvent);
        } catch (IOException e) {
            throw new RuntimeException("Unable to convert Event to JSON", e);
        }

        exchange.getOut().setBody(stringWriter.toString());
    }
}
