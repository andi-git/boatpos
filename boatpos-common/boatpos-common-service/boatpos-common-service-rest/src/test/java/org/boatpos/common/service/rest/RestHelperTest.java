package org.boatpos.common.service.rest;

import org.boatpos.common.service.api.bean.AbstractBean;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class RestHelperTest {

    @Inject
    private RestHelper restHelper;

    @Test
    public void testResponseOkOrNotFound() throws Exception {
        Response response = restHelper.responseOkOrNotFound(Optional.empty());
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());

        response = restHelper.responseOkOrNotFound(Optional.of(new Foo()));
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(Foo.class, response.getEntity().getClass());
    }

    @Test
    public void testResponseCreated() throws Exception {
        Response response = restHelper.responseCreated(new Foo());
        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
        assertEquals(Foo.class, response.getEntity().getClass());
    }

    private static class Foo extends AbstractBean {
    }
}