package org.regkas.service.rest;

import org.boatpos.common.test.rest.FillDatabaseInOtherTransactionTest;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.net.URL;
import java.time.LocalDateTime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
        assertTrue(helper.createRestCallWithHeaderCredentialsForTestUser(url, (wt) -> wt.path("receipt/start/check")).get().readEntity(Boolean.class));
    }

    @Test
    public void testPrintMonth() throws Exception {
        assertFalse(helper.createRestCallWithHeaderCredentialsForTestUser(url, (wt) -> wt.path("receipt/month/check")).get().readEntity(Boolean.class));
        dateTimeHelper.setTime(LocalDateTime.of(2015, 8, 1, 12, 0, 0));
        assertTrue(helper.createRestCallWithHeaderCredentialsForTestUser(url, (wt) -> wt.path("receipt/month/check")).get().readEntity(Boolean.class));
    }
}
