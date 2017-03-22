package org.boatpos.service.core.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.boatpos.common.util.log.LogWrapper;
import org.boatpos.common.util.log.SLF4J;

import com.google.common.io.Files;

@Stateless
@Path("/regkas")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RegkasServiceRestMock {

    @Inject
    @SLF4J
    private LogWrapper log;

    @GET
    @Path("/zip")
    public Response getZip() throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("test.zip");
        File file = new File(System.getProperty("java.io.tmpdir"), "test.zip");
        OutputStream outputStream = new FileOutputStream(file);
        int read;
        byte[] bytes = new byte[1024];
        while ((read = inputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, read);
        }
        return createZipOutput(file);
    }

    private Response createZipOutput(File file) {
        try {
            return Response
                .ok(Files.toByteArray(file))
                .type("application/zip")
                .header("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"")
                .build();
        } catch (IOException e) {
            log.error(e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
