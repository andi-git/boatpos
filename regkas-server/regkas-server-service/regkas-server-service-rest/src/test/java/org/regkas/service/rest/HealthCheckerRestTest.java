package org.regkas.service.rest;

import org.boatpos.common.test.rest.FillDatabaseInOtherTransactionTest;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URL;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class HealthCheckerRestTest extends FillDatabaseInOtherTransactionTest {

    @ArquillianResource
    private URL url;

    @Inject
    private RestTestHelper helper;

    @Test
    public void testPing() throws Exception {
        Response response = helper.createRestCall(url, wt -> wt.path("/ping"), MediaType.TEXT_PLAIN_TYPE).get();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals("OK", response.readEntity(String.class));
    }

    @Test
    public void testUpdateReceipts() throws Exception {
        Response response = helper.createRestCall(url, wt -> wt.path("/update/receipts"), MediaType.TEXT_PLAIN_TYPE).get();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals("updated receipts: 0", response.readEntity(String.class));
    }
}