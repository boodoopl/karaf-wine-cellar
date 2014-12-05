package org.karaf.winecellar.dataendpoint;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

public interface ImageEndpoint {

    @GET
    @Path("{id}")
    @Produces({"image/png"})
    public Response getById(@PathParam("id") String id);
}
