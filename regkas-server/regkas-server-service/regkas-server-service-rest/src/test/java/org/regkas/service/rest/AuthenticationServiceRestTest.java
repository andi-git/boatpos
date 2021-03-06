package org.regkas.service.rest;

import org.boatpos.common.test.rest.FillDatabaseInOtherTransactionTest;
import org.boatpos.common.test.rest.RestTestHelper;
import org.boatpos.common.util.qualifiers.Current;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.domain.api.repository.UserRepository;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.core.Response;
import java.net.URL;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class AuthenticationServiceRestTest extends FillDatabaseInOtherTransactionTest {

    @ArquillianResource
    private URL url;

    @Inject
    private RestTestHelper helper;

    @Inject
    private UserRepository userRepository;

    @Inject
    @Current
    private EntityManager entityManager;

    @Test
    public void testAuthenticate() throws Exception {
        assertEquals(Response.Status.OK.getStatusCode(), helper.createRestCall(url, (wt) -> wt.path("authenticate/Maria%20Musterfrau/abc123/RegKas1")).get().getStatus());
        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), helper.createRestCall(url, (wt) -> wt.path("authenticate/Maria/abc/RegKas1")).get().getStatus());
        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), helper.createRestCall(url, (wt) -> wt.path("authenticate/Maria%20Musterfrau/abc/RegKas1")).get().getStatus());
        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), helper.createRestCall(url, (wt) -> wt.path("authenticate/Maria%20Musterfrau/abc123/Reg")).get().getStatus());
    }
}
