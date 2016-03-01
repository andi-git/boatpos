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
        assertYear(journalReportBean);
    }

    @Test
    public void testTotalIncomeForCurrentYear() throws Exception {
        JournalReportBean journalReportBean = helper.createRestCall(url, (wt) -> wt.path("journal/income/year")).get().readEntity(JournalReportBean.class);
        assertYear(journalReportBean);
    }

    private void assertYear(JournalReportBean journalReportBean) {
        assertEquals("E-Boot", journalReportBean.getJournalReportItemBeans().get(0).getBoatName());
        assertEquals(new BigDecimal("32.00"), journalReportBean.getJournalReportItemBeans().get(0).getPricePaidBeforeCard());
        assertEquals(new BigDecimal("81.60"), journalReportBean.getJournalReportItemBeans().get(0).getPricePaidAfterCash());
        assertEquals(5, journalReportBean.getJournalReportItemBeans().get(0).getCount().intValue());
        assertEquals(0, journalReportBean.getJournalReportItemBeans().get(1).getCount().intValue());
        assertEquals(new BigDecimal("12.60"), journalReportBean.getJournalReportItemBeans().get(2).getPricePaidAfterCash());
        assertEquals(1, journalReportBean.getJournalReportItemBeans().get(2).getCount().intValue());
    }

    @Test
    public void testTotalIncomeForMonth() throws Exception {
        JournalReportBean journalReportBean = helper.createRestCall(url, (wt) -> wt.path("journal/income/2015/7")).get().readEntity(JournalReportBean.class);
        assertMonth(journalReportBean);
    }

    @Test
    public void testTotalIncomeForCurrentMonth() throws Exception {
        JournalReportBean journalReportBean = helper.createRestCall(url, (wt) -> wt.path("journal/income/month")).get().readEntity(JournalReportBean.class);
        assertMonth(journalReportBean);
    }

    private void assertMonth(JournalReportBean journalReportBean) {
        assertEquals("E-Boot", journalReportBean.getJournalReportItemBeans().get(0).getBoatName());
        assertEquals(new BigDecimal("32.00"), journalReportBean.getJournalReportItemBeans().get(0).getPricePaidBeforeCard());
        assertEquals(new BigDecimal("41.60"), journalReportBean.getJournalReportItemBeans().get(0).getPricePaidAfterCash());
        assertEquals(3, journalReportBean.getJournalReportItemBeans().get(0).getCount().intValue());
        assertEquals(0, journalReportBean.getJournalReportItemBeans().get(1).getCount().intValue());
        assertEquals(new BigDecimal("12.60"), journalReportBean.getJournalReportItemBeans().get(2).getPricePaidAfterCash());
        assertEquals(1, journalReportBean.getJournalReportItemBeans().get(2).getCount().intValue());
    }

    @Test
    public void testTotalIncomeForDay() throws Exception {
        JournalReportBean journalReportBean = helper.createRestCall(url, (wt) -> wt.path("journal/income/2015/7/1")).get().readEntity(JournalReportBean.class);
        assertDay(journalReportBean);
    }

    @Test
    public void testTotalIncomeForCurrentDay() throws Exception {
        JournalReportBean journalReportBean = helper.createRestCall(url, (wt) -> wt.path("journal/income/day")).get().readEntity(JournalReportBean.class);
        assertDay(journalReportBean);
    }

    private void assertDay(JournalReportBean journalReportBean) {
        assertEquals("E-Boot", journalReportBean.getJournalReportItemBeans().get(0).getBoatName());
        assertEquals(new BigDecimal("32.00"), journalReportBean.getJournalReportItemBeans().get(0).getPricePaidBeforeCard());
        assertEquals(new BigDecimal("1.60"), journalReportBean.getJournalReportItemBeans().get(0).getPricePaidAfterCash());
        assertEquals(1, journalReportBean.getJournalReportItemBeans().get(0).getCount().intValue());
        assertEquals(0, journalReportBean.getJournalReportItemBeans().get(1).getCount().intValue());
        assertEquals(new BigDecimal("12.60"), journalReportBean.getJournalReportItemBeans().get(2).getPricePaidAfterCash());
        assertEquals(1, journalReportBean.getJournalReportItemBeans().get(2).getCount().intValue());
    }
}
