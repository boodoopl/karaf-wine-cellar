package org.karaf.winecellar.datainitializer.impl;

import org.karaf.winecellar.dao.GeneralDAO;
import org.karaf.winecellar.model.Wine;

public class DataInitializer {

    private GeneralDAO generalDAO;

    public void setGeneralDAO(GeneralDAO generalDAO) {
        this.generalDAO = generalDAO;
    }

    public void init() {
        long wineCount = generalDAO.getCount(Wine.class);
        if (wineCount == 0) {
            generalDAO.add(new Wine("Komandos", "Poland", "IMG", "Polish typical pseudo wine"));
        }
    }
}
