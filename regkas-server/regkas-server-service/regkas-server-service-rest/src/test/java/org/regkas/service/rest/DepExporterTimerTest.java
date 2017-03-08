package org.regkas.service.rest;

import static org.junit.Assert.assertEquals;

import java.net.URL;

import javax.inject.Inject;
import javax.ws.rs.client.Entity;

import org.boatpos.common.test.rest.FillDatabaseInOtherTransactionTest;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class DepExporterTimerTest extends FillDatabaseInOtherTransactionTest {

    @SuppressWarnings("unused")
    @ArquillianResource
    private URL url;

    @Inject
    private RestTestHelper helper;

    @Test
    public void testDepExporterTimer() throws Exception {
        assertEquals(
            "test",
            helper.createRestCallWithHeaderCredentialsForTestUser(url, (wt) -> wt.path("receipt/environment/test")).put(Entity.json(null)).readEntity(
                String.class));

        Thread.sleep(1);
    }
}
