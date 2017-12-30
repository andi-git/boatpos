package org.regkas.service.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.net.URL;
import java.time.LocalDateTime;

import javax.inject.Inject;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;

import org.boatpos.common.test.rest.FillDatabaseInOtherTransactionTest;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class ReceiptServiceRestTest extends FillDatabaseInOtherTransactionTest {

    @SuppressWarnings("unused")
    @ArquillianResource
    private URL url;

    @Inject
    private RestTestHelper helper;

    @Inject
    private DateTimeHelperMock dateTimeHelper;

    @Test
    public void testPrintStart() throws Exception {
        assertFalse(
            helper.createRestCallWithHeaderCredentialsForTestUser(url, (wt) -> wt.path("receipt/start/check")).get().readEntity(Boolean.class));
    }

    @Test
    public void testPrintSchluss() throws Exception {
        assertTrue(
            helper.createRestCallWithHeaderCredentialsForTestUser(url, (wt) -> wt.path("receipt/schluss/check")).get().readEntity(Boolean.class));
    }

    @Test
    public void testPrintMonth() throws Exception {
        assertFalse(
            helper.createRestCallWithHeaderCredentialsForTestUser(url, (wt) -> wt.path("receipt/month/check")).get().readEntity(Boolean.class));
        dateTimeHelper.setTime(LocalDateTime.of(2015, 8, 1, 12, 0, 0));
        assertTrue(
            helper.createRestCallWithHeaderCredentialsForTestUser(url, (wt) -> wt.path("receipt/month/check")).get().readEntity(Boolean.class));
        dateTimeHelper.resetTime();
    }

    @Test
    public void testGetSetEnvironment() throws Exception {
        assertEquals(
                "test",
                helper
                        .createRestCallWithHeaderCredentialsForTestUser(url, (wt) -> wt.path("receipt/environment/test"))
                        .put(Entity.json(null))
                        .readEntity(String.class));
        assertEquals(
            "test",
            helper
                .createRestCallWithHeaderCredentialsForTestUser(url, (wt) -> wt.path("receipt/environment"))
                .get()
                .readEntity(String.class));
        assertEquals(
                "prod",
                helper
                        .createRestCallWithHeaderCredentialsForTestUser(url, (wt) -> wt.path("receipt/environment/prod"))
                        .put(Entity.json(null))
                        .readEntity(String.class));
        assertEquals(
                "prod",
                helper
                        .createRestCallWithHeaderCredentialsForTestUser(url, (wt) -> wt.path("receipt/environment"))
                        .get()
                        .readEntity(String.class));
    }
}
