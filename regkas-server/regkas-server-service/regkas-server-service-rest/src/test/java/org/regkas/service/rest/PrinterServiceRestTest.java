package org.regkas.service.rest;

import org.boatpos.common.test.rest.FillDatabaseInOtherTransactionTest;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.service.api.bean.IpAddressBean;

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
    public void testLoadIp() throws Exception {
        Response response = helper.createRestCallWithHeaderCredentialsForTestUser(url, (wt) -> wt.path("printer/ip")).get();
        assertEquals("192.168.0.11", response.readEntity(IpAddressBean.class).getIpAddress());
    }

    @Test
    public void testSaveIp() throws Exception {
        assertEquals("192.168.0.11", helper.createRestCallWithHeaderCredentialsForTestUser(url, (wt) -> wt.path("printer/ip")).get().readEntity(IpAddressBean.class).getIpAddress());
        helper.createRestCallWithHeaderCredentialsForTestUser(url, (wt) -> wt.path("printer/ip")).post(Entity.json(new IpAddressBean("192.168.0.12")));
        assertEquals("192.168.0.12", helper.createRestCallWithHeaderCredentialsForTestUser(url, (wt) -> wt.path("printer/ip")).get().readEntity(IpAddressBean.class).getIpAddress());
    }
}
