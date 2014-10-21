package org.karaf.winecellar.dao;

import java.util.List;

public interface GenericDAO<T> {

    List<T> getAll();

    long getCount();

    void removeAll();

    T getById(long id);

    void add(T object);

    void update(T object);
}
