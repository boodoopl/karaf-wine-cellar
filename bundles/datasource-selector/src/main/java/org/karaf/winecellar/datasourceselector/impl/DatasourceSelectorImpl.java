package org.karaf.winecellar.datasourceselector.impl;

import org.apache.karaf.jdbc.JdbcService;

public class DatasourceSelectorImpl {

    private JdbcService jdbcService;

    public void setJdbcService(JdbcService jdbcService) {
        this.jdbcService = jdbcService;
    }

    public void init() throws Exception {
        if (jdbcService.datasources().isEmpty()) {
            jdbcService.create("derbyds", "derby", null, null, null, null, null, true);
        }
    }

}
