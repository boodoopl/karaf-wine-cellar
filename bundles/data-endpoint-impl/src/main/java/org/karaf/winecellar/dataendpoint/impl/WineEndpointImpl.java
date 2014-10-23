package org.karaf.winecellar.dataendpoint.impl;

import org.karaf.winecellar.model.Wine;

public class WineEndpointImpl extends CRUDEndpointImpl<Wine> {

    public WineEndpointImpl() {
        super(Wine.class);
    }
}
