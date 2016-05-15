package org.regkas.service.rest;

import org.boatpos.common.test.rest.FillDatabaseInOtherTransactionTest;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.service.api.bean.IncomeBean;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.io.*;
import java.math.BigDecimal;
import java.net.URL;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class JournalServiceRestTest extends FillDatabaseInOtherTransactionTest {

    @ArquillianResource
    private URL url;

    @Inject
    private RestTestHelper helper;

    @Test
    public void testIncomeCurrentYear() throws Exception {
        testIncome("journal/income/year");
    }

    @Test
    public void testIncomeCurrentMonth() throws Exception {
        testIncome("journal/income/month");
    }

    @Test
    public void testIncomeCurrentDay() throws Exception {
        testIncome("journal/income/day");
    }

    @Test
    public void testIncomeYear() throws Exception {
        testIncome("journal/income/2015");
    }

    @Test
    public void testIncomeMonth() throws Exception {
        testIncome("journal/income/2015/7");
    }

    @Test
    public void testIncomeDay() throws Exception {
        testIncome("journal/income/2015/7/1");
    }

    private void testIncome(String path) throws Exception {
        IncomeBean income = helper
                .createRestCallWithCredentialsForTestUser(url, (wt) -> wt.path(path))
                .get()
                .readEntity(IncomeBean.class);
        assertEquals(7, income.getIncomeElements().size());
        assertEquals(new BigDecimal("11.00"), income.getIncomeElements().get(0).getIncome());
        assertEquals(10, income.getIncomeElements().get(0).getTaxPercent().intValue());
        assertEquals(new BigDecimal("22.00"), income.getTotalIncome());
    }

    @Test
    public void testDEPYear() throws Exception {
        assertDEP(helper.createRestCallWithCredentialsForTestUser(url, (wt) -> wt.path("journal/dep/2015")).get(), 605);
    }

    @Test
    public void testDEPMonth() throws Exception {
        assertDEP(helper.createRestCallWithCredentialsForTestUser(url, (wt) -> wt.path("journal/dep/2015/7")).get(), 602);
    }

    @Test
    public void testDEPDay() throws Exception {
        assertDEP(helper.createRestCallWithCredentialsForTestUser(url, (wt) -> wt.path("journal/dep/2015/7/1")).get(), 599);
    }

    private void assertDEP(Response response, int length) throws Exception {
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

        InputStream inputStream = response.readEntity(InputStream.class);
        String contentDisposition = (String) response.getHeaders().get("Content-Disposition").get(0);
        String fileName = contentDisposition.substring(contentDisposition.indexOf('"') + 1, contentDisposition.length() - 1);

        File file = new File(System.getProperty("java.io.tmpdir"), "copy_of_" + fileName);
        OutputStream outputStream = new FileOutputStream(file);
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        assertEquals(length, file.length());
    }
}
