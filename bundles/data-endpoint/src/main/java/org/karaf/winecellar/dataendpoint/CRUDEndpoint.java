package org.karaf.winecellar.dataendpoint;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

public interface CRUDEndpoint <E> {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<E> getAll();

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public E getById(@PathParam("id") String id);

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public E add(E entity);

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public E update(E entity);

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") String id);

}
