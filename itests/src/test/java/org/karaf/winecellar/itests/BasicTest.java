package org.karaf.winecellar.itests;

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

    @Test
    public void checkWineRESTService() {

        List<Object> providers = new ArrayList<Object>();
        providers.add(new JacksonJsonProvider());

        WebClient client = WebClient.create("http://localhost:8181/cxf", providers);
        client.path("wines");

        List<Wine> winesFromREST = (List<Wine>) client.getCollection(Wine.class);
        assertNotEquals(winesFromREST.size(), 0);
    }

}
