package org.karaf.winecellar.dataendpoint.impl;

import org.karaf.winecellar.dao.GeneralDAO;
import org.karaf.winecellar.dataendpoint.CRUDEndpoint;

import java.util.List;

public abstract class CRUDEndpointImpl <E> implements CRUDEndpoint<E> {

    private Class<E> clazz;
    GeneralDAO generalDAO;

    public CRUDEndpointImpl(Class<E> clazz) {
        this.clazz = clazz;
    }

    public void setGeneralDAO(GeneralDAO generalDAO) {
        this.generalDAO = generalDAO;
    }

    @Override
    public List<E> getAll() {
        return generalDAO.getAll(clazz);
    }

    @Override
    public E getById(String id) {
        return generalDAO.getById(clazz, Integer.parseInt(id));
    }

    @Override
    public E add(E entity) {
        return generalDAO.add(entity);
    }

    @Override
    public E update(E entity) {
        return generalDAO.update(entity);
    }

    @Override
    public void delete(String id) {
        generalDAO.removeById(clazz, Integer.parseInt(id));
    }
}
