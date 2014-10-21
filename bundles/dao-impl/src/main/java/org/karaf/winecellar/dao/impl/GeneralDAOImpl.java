package org.karaf.winecellar.dao.impl;

import org.karaf.winecellar.dao.GeneralDAO;

import javax.persistence.EntityManager;
import java.util.List;

public class GeneralDAOImpl implements GeneralDAO {

    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public <T> List getAll(Class<T> clazz) {
        return DAOCommon.getAll(entityManager, clazz);
    }

    @Override
    public <T> long getCount(Class<T> clazz) {
        return DAOCommon.getCount(entityManager, clazz);
    }

    @Override
    public <T> void removeAll(Class<T> clazz) {
        DAOCommon.removeAll(entityManager, clazz);
    }

    @Override
    public <T> T getById(Class<T> clazz, long id) {
        return entityManager.find(clazz, id);
    }

    @Override
    public void add(Object object) {
        entityManager.persist(object);
    }

    @Override
    public void update(Object object) {
        entityManager.merge(object);
    }
}
