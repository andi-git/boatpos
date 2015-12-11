package org.boatpos.service.rest;

import org.boatpos.service.api.bean.BoatBean;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URL;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class BoatServiceRestTest extends FillDatabaseInOtherTransactionTest {

    @ArquillianResource
    private URL deploymentURL;


    @Test
    public void testGetByName() throws Exception {
        Response response = ClientBuilder.newClient().target(deploymentURL.toURI())
                .path("rest/boat/name")
                .path("E")
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .get();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        BoatBean boat = response.readEntity(BoatBean.class);
        assertEquals("E-Boot", boat.getName());

        response = ClientBuilder.newClient().target(deploymentURL.toURI())
                .path("rest/boat/name")
                .path("E-Boot")
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .get();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        boat = response.readEntity(BoatBean.class);
        assertEquals("E", boat.getShortName());

        response = ClientBuilder.newClient().target(deploymentURL.toURI())
                .path("rest/boat/name")
                .path("XXX")
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .get();
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
    }

    @Test
    public void testGetAll() throws Exception {
        Response response = ClientBuilder.newClient().target(deploymentURL.toURI())
                .path("rest/boat")
                .request()
                .accept(MediaType.APPLICATION_JSON)
                .get();

        List<BoatBean> boats = response.readEntity(new GenericType<List<BoatBean>>() {
        });
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(6, boats.size());
        assertEquals("E", boats.get(0).getShortName());
    }
}