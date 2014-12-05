package org.karaf.winecellar.dataendpoint.impl;

import org.karaf.winecellar.dataendpoint.WineEndpoint;
import org.karaf.winecellar.model.Wine;

public class WineEndpointImpl extends CRUDEndpointImpl<Wine> implements WineEndpoint {

    public WineEndpointImpl() {
        super(Wine.class);
    }
}
