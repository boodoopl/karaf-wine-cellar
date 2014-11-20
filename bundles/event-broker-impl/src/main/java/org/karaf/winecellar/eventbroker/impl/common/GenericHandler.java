package org.karaf.winecellar.eventbroker.impl.common;

import org.osgi.framework.BundleContext;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;

import java.util.Dictionary;
import java.util.Hashtable;

abstract public class GenericHandler<T> implements EventHandler {

    protected T listener;

    public GenericHandler(BundleContext bundleContext, T listener, String[] topics, String filter) {
        Dictionary props = new Hashtable();
        props.put(EventConstants.EVENT_TOPIC, topics);
        props.put(EventConstants.EVENT_FILTER, filter);
        bundleContext.registerService(EventHandler.class, this, props);
        this.listener = listener;
    }
}
