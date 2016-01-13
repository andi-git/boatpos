package org.boatpos.service.rest;

import org.boatpos.service.api.bean.JournalReportBean;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
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
    public void testTotalIncomeForYear() throws Exception {
        JournalReportBean journalReportBean = helper.createRestCall(url, (wt) -> wt.path("journal/income/2015")).get().readEntity(JournalReportBean.class);
        assertEquals(new BigDecimal("113.60"), journalReportBean.getJournalReportItemBeans().get(0).getSum());
        assertEquals(new BigDecimal("0.00"), journalReportBean.getJournalReportItemBeans().get(1).getSum());
        assertEquals(new BigDecimal("12.60"), journalReportBean.getJournalReportItemBeans().get(2).getSum());
    }

    @Test
    public void testTotalIncomeForMonth() throws Exception {
        JournalReportBean journalReportBean = helper.createRestCall(url, (wt) -> wt.path("journal/income/2015/7")).get().readEntity(JournalReportBean.class);
        assertEquals(new BigDecimal("73.60"), journalReportBean.getJournalReportItemBeans().get(0).getSum());
        assertEquals(new BigDecimal("0.00"), journalReportBean.getJournalReportItemBeans().get(1).getSum());
        assertEquals(new BigDecimal("12.60"), journalReportBean.getJournalReportItemBeans().get(2).getSum());
    }

    @Test
    public void testTotalIncomeForDay() throws Exception {
        JournalReportBean journalReportBean = helper.createRestCall(url, (wt) -> wt.path("journal/income/2015/7/1")).get().readEntity(JournalReportBean.class);
        assertEquals(new BigDecimal("33.60"), journalReportBean.getJournalReportItemBeans().get(0).getSum());
        assertEquals(new BigDecimal("0.00"), journalReportBean.getJournalReportItemBeans().get(1).getSum());
        assertEquals(new BigDecimal("12.60"), journalReportBean.getJournalReportItemBeans().get(2).getSum());
    }
}
