package org.boatpos.service.core.util;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.ClientBuilder;

@RunWith(Arquillian.class)
public class RegkasServiceTest {

    @Test
    public void getProduct() throws Exception {

    }

    @Test
    public void sale() throws Exception {

    }

    @Test
    public void readEntity() throws Exception {

    }

    @Test
    public void addCredentials() throws Exception {
        new RegkasService().addCredentials(ClientBuilder.newClient().target("").request());
    }

    @Test
    public void createRestCall() throws Exception {
        System.setProperty("boatpos.regkas.service.rest", "http://localhost:8280/regkas-service/rest");
        new RegkasService().createRestCall(webTarget -> webTarget);
        System.clearProperty("boatpos.regkas.service.rest");
    }
}