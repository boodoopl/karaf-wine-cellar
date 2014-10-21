package org.karaf.winecellar.dao.impl;

import org.karaf.winecellar.dao.GenericDAO;

import javax.persistence.EntityManager;
import java.util.List;

public class GenericDAOImpl<T> implements GenericDAO<T> {

    private EntityManager entityManager;
    private Class<T> type;

    public GenericDAOImpl(Class<T> type) {
        this.type = type;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<T> getAll() {
        return DAOCommon.getAll(entityManager, type);
    }

    @Override
    public long getCount() {
        return DAOCommon.getCount(entityManager, type);
    }

    @Override
    public void removeAll() {
        DAOCommon.removeAll(entityManager, type);
    }

    @Override
    public T getById(long id) {
        return entityManager.find(type, id);
    }

    @Override
    public void add(T object) {
        entityManager.persist(object);
    }

    @Override
    public void update(T object) {
        entityManager.merge(object);
    }

    protected List<T> getByField(String field, Object value) {
        return DAOCommon.getByField(entityManager, type, field, value);
    }
}