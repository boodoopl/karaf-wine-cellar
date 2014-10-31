package org.karaf.winecellar.dao;

import java.util.List;

public interface GenericDAO<T> {

    List<T> getAll();

    long getCount();

    void removeAll();

    void removeById(long id);

    T getById(long id);

    T add(T object);

    T update(T object);
}
