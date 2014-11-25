package org.karaf.winecellar.websocketnotifier;

import java.util.HashMap;
import java.util.Map;

public class SimpleEvent {

    private Map<String, Object> properties = new HashMap<String,Object>();
    private String topic;

    public void addProperty(String key, Object value) {
        properties.put(key, value);
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTopic() {
        return topic;
    }

    public Object getProperty(String key) {
        return properties.get(key);
    }

    public Map<String, Object> getProperties() {
        return properties;
    }
}
