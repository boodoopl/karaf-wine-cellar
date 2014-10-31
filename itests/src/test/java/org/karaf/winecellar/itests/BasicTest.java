package org.karaf.winecellar.itests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.apache.cxf.jaxrs.client.WebClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.karaf.winecellar.dao.GeneralDAO;
import org.karaf.winecellar.model.Wine;
import org.ops4j.pax.exam.junit.ExamReactorStrategy;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.spi.reactors.PerClass;
import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import java.util.*;

@RunWith(PaxExam.class)
@ExamReactorStrategy(PerClass.class)
public class BasicTest extends WineCellarTestSupport {

    @Inject
    GeneralDAO generalDAO;

    @Test
    public void checkWinesExist() {
        assertNotEquals(generalDAO.getCount(Wine.class), 0);
    }

    private static void prepareWebClientForPath(WebClient client, String path) {
        client.reset();
        client.accept(MediaType.APPLICATION_JSON);
        client.type(MediaType.APPLICATION_JSON);
        client.path(path);
    }

    @Test
    public void checkWineRESTService() {

        List<Object> providers = new ArrayList<Object>();
        providers.add(new JacksonJsonProvider());

        WebClient client = WebClient.create("http://localhost:8181/cxf", providers);

        prepareWebClientForPath(client, "wines");
        Wine newWine = new Wine("Test Wine", "Country", "IMG", "Description");
        Wine persistedWine = client.post(newWine, Wine.class);

        long wineId = persistedWine.getId();
        assertNotEquals(wineId, 0);

        String idPath =  String.format("wines/%d", wineId);
        prepareWebClientForPath(client, idPath);
        Wine persistedWineById = client.get(Wine.class);

        assertEquals(wineId, persistedWineById.getId());
        assertEquals(persistedWine.getName(), persistedWineById.getName());

        persistedWine.setName("Updated name");
        prepareWebClientForPath(client, idPath);
        Wine updatedWine = client.put(persistedWine, Wine.class);
        prepareWebClientForPath(client, idPath);
        persistedWineById = client.get(Wine.class);

        assertEquals(updatedWine.getName(), persistedWine.getName());
        assertEquals(persistedWineById.getName(), persistedWine.getName());

        prepareWebClientForPath(client, idPath);
        client.delete();
        prepareWebClientForPath(client, idPath);
        Response response = client.get();

        assertEquals(response.getStatus(), 204);
    }

}
