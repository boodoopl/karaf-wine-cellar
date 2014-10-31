package org.karaf.winecellar.dataendpoint.impl;

import org.karaf.winecellar.dao.GeneralDAO;
import org.karaf.winecellar.dataendpoint.CRUDEndpoint;

import javax.ws.rs.PathParam;
import java.util.List;

public class CRUDEndpointImpl <E> implements CRUDEndpoint {

    private Class<E> clazz;
    GeneralDAO generalDAO;

    public CRUDEndpointImpl(Class<E> clazz) {
        this.clazz = clazz;
    }

    public void setGeneralDAO(GeneralDAO generalDAO) {
        this.generalDAO = generalDAO;
    }

    @Override
    public List getAll() {
        return generalDAO.getAll(clazz);
    }

    @Override
    public Object getById(String id) {
        return generalDAO.getById(clazz, Integer.parseInt(id));
    }

    @Override
    public Object add(Object entity) {
        return generalDAO.add(entity);
    }

    @Override
    public Object update(Object entity) {
        return generalDAO.update(entity);
    }

    @Override
    public void delete(String id) {
        generalDAO.removeById(clazz, Integer.parseInt(id));
    }
}
