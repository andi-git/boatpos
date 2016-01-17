package org.boatpos.service.rest.filter;

import org.boatpos.service.rest.FillDatabaseInOtherTransactionTest;
import org.boatpos.service.rest.RestTestHelper;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.net.URL;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class CORSFilterTest extends FillDatabaseInOtherTransactionTest {

    @ArquillianResource
    private URL url;

    @Inject
    private RestTestHelper helper;

    @Test
    public void testFilter() throws Exception {
        Response response = helper.createRestCall(url, (wt) -> wt.path("boat")).get();
        assertEquals("[*]", String.valueOf(response.getHeaders().get("Access-Control-Allow-Origin")));
        assertEquals("[GET, POST, DELETE, PUT, OPTIONS, HEAD]", String.valueOf(response.getHeaders().get("Access-Control-Allow-Methods")));
        assertEquals("[Content-Type, Accept, X-Requested-With]", String.valueOf(response.getHeaders().get("Access-Control-Allow-Headers")));
    }
}