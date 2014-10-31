package org.karaf.winecellar.dao;

import java.util.List;

public interface GeneralDAO {

    <T> List<T> getAll(Class<T> clazz);

    <T> long getCount(Class<T> clazz);

    <T> void removeAll(Class<T> clazz);

    <T> void removeById(Class<T> clazz, long id);

    <T> T getById(Class<T> clazz, long id);

    <T> T add(T object);

    <T> T update(T object);
}
