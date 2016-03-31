package org.regkas.service.rest;

import org.boatpos.common.test.rest.FillDatabaseInOtherTransactionTest;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.service.api.bean.ProductGroupBean;

import javax.inject.Inject;
import javax.ws.rs.core.GenericType;
import java.net.URL;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class ProductGroupServiceRestTest extends FillDatabaseInOtherTransactionTest {

    @ArquillianResource
    private URL url;

    @Inject
    private RestTestHelper helper;

    @Test
    public void testGetAll() throws Exception {
        List<ProductGroupBean> productGroups = helper
                .createRestCallWithCredentialsForTestUser(url, (wt) -> wt.path("productgroup"))
                .get()
                .readEntity(new GenericType<List<ProductGroupBean>>() {
                });
        assertEquals(7, productGroups.size());
    }
}
