package org.karaf.winecellar.dataendpoint;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

public interface CRUDEndpoint <E> {

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    public List<E> getAll();

    @GET
    @Path("/test")
    @Produces(MediaType.TEXT_PLAIN)
    String test();
}
