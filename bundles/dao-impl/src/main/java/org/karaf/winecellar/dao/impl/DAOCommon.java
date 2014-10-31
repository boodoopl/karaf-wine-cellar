package org.karaf.winecellar.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class DAOCommon {

    private static String getGetAllQueryName(Class clazz) {
        return String.format("%s.GET_ALL", clazz.getSimpleName());
    }

    private static String getCountQueryName(Class clazz) {
        return String.format("%s.GET_COUNT", clazz.getSimpleName());
    }

    private static String getRemoveAllQueryName(Class clazz) {
        return String.format("%s.REMOVE_ALL", clazz.getSimpleName());
    }

    private static String getGetByFieldQueryName(Class clazz, String field) {
        return String.format("%s.GET_BY_%s", clazz.getSimpleName(), field.toUpperCase());
    }

    public static List getAll(EntityManager entityManager, Class clazz) {
        Query getAllQuery = entityManager.createNamedQuery(getGetAllQueryName(clazz));
        return getAllQuery.getResultList();
    }

    public static long getCount(EntityManager entityManager, Class clazz) {
        Query getCountQuery = entityManager.createNamedQuery(getCountQueryName(clazz));
        return ((Number)getCountQuery.getSingleResult()).intValue();
    }

    public static void removeAll(EntityManager entityManager, Class clazz) {
        Query removeAllQuery = entityManager.createNamedQuery(getRemoveAllQueryName(clazz));
        removeAllQuery.executeUpdate();
    }

    public static void removeById(EntityManager entityManager, Class clazz, long id) {
        Object object = entityManager.find(clazz, id);
        entityManager.remove(object);
    }

    public static List getByField(EntityManager entityManager, Class clazz, String fieldName, Object value) {
        Query getByFieldQuery = entityManager.createNamedQuery(getGetByFieldQueryName(clazz, fieldName));
        getByFieldQuery.setParameter(1, value);
        return getByFieldQuery.getResultList();
    }
}
