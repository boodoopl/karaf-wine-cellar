package org.karaf.winecellar.dataendpoint.impl;

import org.karaf.winecellar.dao.GeneralDAO;
import org.karaf.winecellar.dataendpoint.ImageEndpoint;
import org.karaf.winecellar.model.Image;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import java.io.IOException;
import java.io.OutputStream;

public class ImageEndpointImpl implements ImageEndpoint {

    private GeneralDAO generalDAO;

    public void setGeneralDAO(GeneralDAO generalDAO) {
        this.generalDAO = generalDAO;
    }

    @Override
    public Response getById(String id) {
        final Image image = generalDAO.getById(Image.class, Integer.parseInt(id));

        StreamingOutput streamingOutput = new StreamingOutput() {
            @Override
            public void write(OutputStream outputStream) throws IOException, WebApplicationException {
                outputStream.write(image.getData());
            }
        };

        return Response.ok(streamingOutput).build();
    }
}
