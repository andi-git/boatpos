package org.boatpos.service.rest;

import org.boatpos.service.api.EnabledState;
import org.boatpos.service.api.bean.BoatBean;
import org.boatpos.service.api.bean.BoatCountBean;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.net.URL;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(Arquillian.class)
public class BoatServiceRestTest extends FillDatabaseInOtherTransactionTest {

    @ArquillianResource
    private URL url;

    @Inject
    private RestTestHelper helper;

    @Test
    public void testGetById() throws Exception {
        Response response = helper.createRestCall(url, (wt) -> wt.path("boat/name/E")).get();
        Long id = response.readEntity(BoatBean.class).getId();

        response = helper.createRestCall(url, (wt) -> wt.path("boat/id/" + id)).get();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals("E-Boot", response.readEntity(BoatBean.class).getName());

        response = helper.createRestCall(url, (wt) -> wt.path("boat/id/999")).get();
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }

    @Test
    public void testGetByName() throws Exception {
        Response response = helper.createRestCall(url, (wt) -> wt.path("boat/name/E")).get();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals("E-Boot", response.readEntity(BoatBean.class).getName());

        response = helper.createRestCall(url, (wt) -> wt.path("boat/name/E-Boot")).get();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals("E", response.readEntity(BoatBean.class).getShortName());

        response = helper.createRestCall(url, (wt) -> wt.path("boat/name/XXX")).get();
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }

    @Test
    public void testGetAll() throws Exception {
        List<BoatBean> boats = helper.createRestCall(url, (wt) -> wt.path("boat")).get().readEntity(new GenericType<List<BoatBean>>() {
        });
        assertEquals(6, boats.size());
        assertEquals("E", boats.get(0).getShortName());
        helper.assertCount(url, "boat", 5, EnabledState.Enabled);
        helper.assertCount(url, "boat", 1, EnabledState.Disabled);
    }

    @Test
    public void testSave() throws Exception {
        helper.assertCount(url, "boat", 6);

        BoatBean boat = new BoatBean(null, null, "TG", "Tretboot groß", new BigDecimal("10.1"), new BigDecimal("6.1"), new BigDecimal("8.1"), 10, 5, true, "s_________", "m_________", "l_________", 'a');
        Response response = helper.createRestCall(url, (wt) -> wt.path("boat")).post(Entity.json(boat));
        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
        BoatBean result = response.readEntity(BoatBean.class);
        assertNotNull(result.getId());
        assertEquals(0, result.getVersion().intValue());
        helper.assertCount(url, "boat", 7);

        boat = new BoatBean(-1L, null, "xxxx", "Tretboot groß", new BigDecimal("10.1"), new BigDecimal("6.1"), new BigDecimal("8.1"), 10, 5, true, "s_________", "m_________", "l_________", 'a');
        response = helper.createRestCall(url, (wt) -> wt.path("boat")).post(Entity.json(boat));
        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());

        helper.assertCount(url, "boat", 7);
    }

    @Test
    public void testUpdate() throws Exception {
        helper.assertCount(url, "boat", 6);

        Response response = helper.createRestCall(url, (wt) -> wt.path("boat/name/E")).get();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        BoatBean boat = response.readEntity(BoatBean.class);
        assertEquals("E-Boot", boat.getName());
        assertEquals(1, boat.getVersion().intValue());

        boat.setName("EBOOT");
        response = helper.createRestCall(url, (wt) -> wt.path("boat")).put(Entity.json(boat));
        boat = response.readEntity(BoatBean.class);
        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
        assertEquals("EBOOT", boat.getName());
        assertEquals(2, boat.getVersion().intValue());

        response = helper.createRestCall(url, (wt) -> wt.path("boat/name/E")).get();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        boat = response.readEntity(BoatBean.class);
        assertEquals("EBOOT", boat.getName());
        assertEquals(2, boat.getVersion().intValue());

        helper.assertCount(url, "boat", 6);
    }

    @Test
    public void testEnable() throws Exception {
        helper.assertCount(url, "boat", 5, EnabledState.Enabled);
        Long idToEnable = helper.createRestCall(url, (webTarget) -> webTarget.path("boat/name/P")).get().readEntity(BoatBean.class).getId();
        helper.createRestCall(url, (wt) -> wt.path("boat/enable/" + idToEnable)).put(null);
        helper.assertCount(url, "boat", 6, EnabledState.Enabled);
    }

    @Test
    public void testDisable() throws Exception {
        helper.assertCount(url, "boat", 1, EnabledState.Disabled);
        Long idToDisable = helper.createRestCall(url, (webTarget) -> webTarget.path("boat/name/E")).get().readEntity(BoatBean.class).getId();
        helper.createRestCall(url, (wt) -> wt.path("boat/disable/" + idToDisable)).put(null);
        helper.assertCount(url, "boat", 4, EnabledState.Enabled);
    }

    @Test
    public void testCountBoats() throws Exception {
        List<BoatCountBean> boatCounts = helper.createRestCall(url, (webTarget) -> webTarget.path("boat/count")).get().readEntity(new GenericType<List<BoatCountBean>>() {
        });
        assertEquals("E", boatCounts.get(0).getShortName());
        assertEquals(1, boatCounts.get(0).getCount());
        assertEquals(22, boatCounts.get(0).getMax());
        assertEquals("L", boatCounts.get(4).getShortName());
        assertEquals(0, boatCounts.get(4).getCount());
        assertEquals(5, boatCounts.get(4).getMax());
    }
}
