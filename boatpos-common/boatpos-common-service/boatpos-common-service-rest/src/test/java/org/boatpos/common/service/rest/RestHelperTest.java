package org.boatpos.common.service.rest;

import org.boatpos.common.service.api.bean.AbstractBean;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.io.File;
import java.nio.file.Files;
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

    @Test
    public void testCreateZipOutput() throws Exception {
        File file = new File(System.getProperty("java.io.tmpdir"), "output.zip");
        if (!file.exists()) {
            Files.createFile(file.toPath());
        }
        Response response = restHelper.createZipOutput(file);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals("application", response.getMediaType().getType());
        assertEquals("zip", response.getMediaType().getSubtype());
        assertEquals("attachment; filename=\"output.zip\"", response.getHeaders().get("Content-Disposition").get(0));

        response = restHelper.createZipOutput(null);
        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
    }

    private static class Foo extends AbstractBean {
    }
}