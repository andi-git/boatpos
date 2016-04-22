package org.boatpos.service.rest;

import org.boatpos.common.test.rest.FillDatabaseInOtherTransactionTest;
import org.boatpos.common.test.rest.RestTestHelper;
import org.boatpos.service.api.bean.IpAddressBean;
import org.boatpos.service.api.bean.PrinterBean;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import java.net.URL;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class PrinterServiceRestTest extends FillDatabaseInOtherTransactionTest {

    @ArquillianResource
    private URL url;

    @Inject
    private RestTestHelper helper;

    @Test
    public void testLoad() throws Exception {
        Response response = helper.createRestCall(url, (wt) -> wt.path("printer")).get();
        assertEquals("192.168.0.11", response.readEntity(PrinterBean.class).getIpAddress());
    }

    @Test
    public void testSave() throws Exception {
        assertEquals("192.168.0.11", helper.createRestCall(url, (wt) -> wt.path("printer")).get().readEntity(PrinterBean.class).getIpAddress());
        helper.createRestCall(url, (wt) -> wt.path("printer")).post(Entity.json(new IpAddressBean("192.168.0.12")));
        assertEquals("192.168.0.12", helper.createRestCall(url, (wt) -> wt.path("printer")).get().readEntity(PrinterBean.class).getIpAddress());
    }
}
