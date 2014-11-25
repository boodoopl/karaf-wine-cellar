package org.karaf.winecellar.itests;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.websocket.WebSocket;
import com.ning.http.client.websocket.WebSocketTextListener;
import com.ning.http.client.websocket.WebSocketUpgradeHandler;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.karaf.winecellar.dao.GeneralDAO;
import org.karaf.winecellar.eventbroker.impl.model.ModelHandler;
import org.karaf.winecellar.eventbroker.impl.model.ModelListener;
import org.karaf.winecellar.eventbroker.impl.model.ModelPublisherImpl;
import org.karaf.winecellar.model.Wine;
import org.karaf.winecellar.websocketnotifier.SimpleEvent;
import org.ops4j.pax.exam.ProbeBuilder;
import org.ops4j.pax.exam.TestProbeBuilder;
import org.ops4j.pax.exam.junit.ExamReactorStrategy;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.spi.reactors.PerClass;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.service.event.EventAdmin;

import javax.inject.Inject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;

import static org.junit.Assert.assertEquals;

@RunWith(PaxExam.class)
@ExamReactorStrategy(PerClass.class)
public class NotificationTest extends WineCellarTestSupport {

    @Inject
    GeneralDAO generalDAO;

    @Inject
    EventAdmin eventAdmin;

    @Inject
    BundleContext bc;

    /*
    @ProbeBuilder
    public TestProbeBuilder probeConfiguration(TestProbeBuilder probe) {
        probe.setHeader(Constants.DYNAMICIMPORT_PACKAGE, "com.ning.http.client,com.ning.http.client.websocket,org.glassfish.grizzly.websockets.rfc6455");
        return probe;
    }
    */

    @Test(timeout = 5000)
    public void checkModelNotifications() throws Exception {
        final CyclicBarrier gate = new CyclicBarrier(3);

        final Wine newWine = new Wine("New wine", "ENG", "", "Some wine");
        final String[] receivedClassName = new String[1];
        final Object[] receivedEntity = new Object[1];
        final String[] modifiedClassName = new String[1];
        final Object[] modifiedEntity = new Object[1];
        final String[] deletedClassName = new String[1];
        final long[] deletedId = new long[1];

        ModelHandler modelHandler = new ModelHandler(bc, new ModelListener() {
            @Override
            public void entityAdded(String className, Object entity) {
                try {
                    receivedClassName[0] = className;
                    receivedEntity[0] = entity;
                    gate.await();
                } catch (Exception e) {}
            }
            @Override
            public void entityUpdated(String className, Object entity) {
                try {
                    modifiedClassName[0] = className;
                    modifiedEntity[0] = entity;
                    gate.await();
                } catch (Exception e) {}
            }
            @Override
            public void entityDeleted(String className, long entityId) {
                try {
                    deletedClassName[0] = className;
                    deletedId[0] = entityId;
                    gate.await();
                } catch (Exception e) {}
            }
        }, Wine.class.getName());

        final List<SimpleEvent> receivedWebsocketData = new ArrayList<SimpleEvent>();
        AsyncHttpClient c = new AsyncHttpClient();
        WebSocket websocket = c.prepareGet("ws://127.0.0.1:9090/events").execute(
                new WebSocketUpgradeHandler.Builder()
                        .addWebSocketListener(new WebSocketTextListener() {
                            @Override
                            public void onMessage(String message) {
                                receivedWebsocketData.clear();
                                ObjectMapper mapper = new ObjectMapper();
                                SimpleEvent event = null;
                                try {
                                    event = mapper.readValue(message, SimpleEvent.class);
                                } catch (IOException e) {
                                    new RuntimeException("Unable to parse notification data", e);
                                }
                                String eventTopic = event.getTopic();
                                if (eventTopic.equals(ModelPublisherImpl.MODEL_TOPIC_ENTITY_ADDED) ||
                                        eventTopic.equals(ModelPublisherImpl.MODEL_TOPIC_ENTITY_UPDATED) ||
                                        eventTopic.equals(ModelPublisherImpl.MODEL_TOPIC_ENTITY_DELETED)) {
                                    receivedWebsocketData.add(event);
                                    try {
                                        gate.await();
                                    } catch (Exception e) {
                                    }
                                }
                            }
                            @Override
                            public void onFragment(String fragment, boolean last) {
                            }
                            @Override
                            public void onOpen(WebSocket websocket) {
                            }
                            @Override
                            public void onClose(WebSocket websocket) {
                            }
                            @Override
                            public void onError(Throwable t) {
                                t.printStackTrace();
                            }
                        }).build()).get();

        generalDAO.add(newWine);
        gate.await();
        assertEquals(receivedClassName[0], Wine.class.getName());
        assertEquals(((Wine) receivedEntity[0]).getName(), newWine.getName());

        assertEquals(receivedWebsocketData.size(), 1);
        SimpleEvent event = receivedWebsocketData.get(0);
        assertEquals(event.getTopic(), "org/karaf/winecellar/Model/ADDED");
        assertEquals(event.getProperty("class"), Wine.class.getName());
        assertEquals(((Wine)event.getProperty("entity")).getName(), newWine.getName());

        gate.reset();
        newWine.setName("New name");
        generalDAO.update(newWine);
        gate.await();
        assertEquals(modifiedClassName[0], Wine.class.getName());
        assertEquals(((Wine) modifiedEntity[0]).getName(), "New name");

        assertEquals(receivedWebsocketData.size(), 1);
        event = receivedWebsocketData.get(0);
        assertEquals(event.getTopic(), "org/karaf/winecellar/Model/UPDATED");
        assertEquals(event.getProperty("class"), Wine.class.getName());
        assertEquals(((Wine)event.getProperty("entity")).getName(), newWine.getName());

        gate.reset();
        long wineId = newWine.getId();
        generalDAO.removeById(Wine.class, wineId);
        gate.await();
        assertEquals(deletedClassName[0], Wine.class.getName());
        assertEquals(deletedId[0], wineId);

        assertEquals(receivedWebsocketData.size(), 1);
        event = receivedWebsocketData.get(0);
        assertEquals(event.getTopic(), "org/karaf/winecellar/Model/DELETED");
        assertEquals(event.getProperty("class"), Wine.class.getName());
        assertEquals(((Long)event.getProperty("entityId")).longValue(), wineId);

    }
}
