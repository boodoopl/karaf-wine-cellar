package org.karaf.winecellar.eventbroker.impl.model;

public interface ModelListener {

    public void entityAdded(String className, Object entity);

    public void entityUpdated(String className, Object entity);

    public void entityDeleted(String className, long entityId);
}