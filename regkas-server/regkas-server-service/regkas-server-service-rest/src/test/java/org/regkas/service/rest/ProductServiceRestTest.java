package org.regkas.service.rest;

import org.boatpos.common.test.rest.FillDatabaseInOtherTransactionTest;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.service.api.bean.ProductBean;

import javax.inject.Inject;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import java.net.URL;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class ProductServiceRestTest extends FillDatabaseInOtherTransactionTest {

    @ArquillianResource
    private URL url;

    @Inject
    private RestTestHelper helper;

    @Test
    public void testNotAuthorized() throws Exception {
        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), helper.createRestCall(url, (wt) -> wt.path("product")).get().getStatus());
    }

    @Test
    public void testGetAll() throws Exception {
        List<ProductBean> products = helper
                .createRestCallWithCredentialsForTestUser(url, (wt) -> wt.path("product"))
                .get()
                .readEntity(new GenericType<List<ProductBean>>() {
                });
        assertEquals(4, products.size());
    }

    @Test
    public void testGetByNameAndCompany() throws Exception {
        ProductBean product = helper
                .createRestCallWithCredentialsForTestUser(url, (wt) -> wt.path("product/Cola"))
                .get()
                .readEntity(ProductBean.class);
        assertEquals("Cola", product.getName());
    }
}
