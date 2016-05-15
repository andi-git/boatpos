package org.boatpos.service.core.util;

import com.google.common.io.Files;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.net.URL;
import java.nio.charset.Charset;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class RegkasServiceTest {

    private RegkasService regkasService;

    @ArquillianResource
    private URL url;

    @Before
    public void before() {
        regkasService = new RegkasService();
    }

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
        System.setProperty("boatpos.regkas.service.username", "x");
        System.setProperty("boatpos.regkas.service.password", "x");
        System.setProperty("boatpos.regkas.service.cashbox", "x");
        regkasService.addCredentials(ClientBuilder.newClient().target("").request());
        System.clearProperty("boatpos.regkas.service.username");
        System.clearProperty("boatpos.regkas.service.password");
        System.clearProperty("boatpos.regkas.service.cashbox");
    }

    @Test
    public void createRestCall() throws Exception {
        System.setProperty("boatpos.regkas.service.username", "x");
        System.setProperty("boatpos.regkas.service.password", "x");
        System.setProperty("boatpos.regkas.service.cashbox", "x");
        System.setProperty("boatpos.regkas.service.rest", "http://localhost:8280/regkas-service/rest");
        regkasService.createRestCall(webTarget -> webTarget);
        System.clearProperty("boatpos.regkas.service.rest");
        System.clearProperty("boatpos.regkas.service.username");
        System.clearProperty("boatpos.regkas.service.password");
        System.clearProperty("boatpos.regkas.service.cashbox");
    }

    @Test
    public void testConvertResponse() throws Exception {
        File file = regkasService.convert(callRESTService());
        assertEquals("test.zip", file.getName());
        assertEquals(119, file.length());
    }

    @Test
    public void testGetFileNameFromContentDisposition() throws Exception {
        assertEquals("test.zip", regkasService.getFileNameFromContentDisposition(callRESTService()));
    }

    @Test
    public void testWriteToFile() throws Exception {
        File file = new File(System.getProperty("java.io.tmpdir"), "test.txt");
        file = regkasService.writeToFile(new ByteArrayInputStream("test".getBytes()), file);
        assertEquals("test", Files.toString(file, Charset.defaultCharset()));
    }

    private Response callRESTService() throws Exception {
        return ClientBuilder.newClient().target(url.toURI()).path("rest/regkas/zip").request().accept(MediaType.APPLICATION_JSON).get();
    }
}