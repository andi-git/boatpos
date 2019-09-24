package org.boatpos.service.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ArrayList;

import javax.inject.Inject;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.boatpos.common.test.rest.FillDatabaseInOtherTransactionTest;
import org.boatpos.common.test.rest.RestTestHelper;
import org.boatpos.service.api.bean.JournalReportBean;
import org.boatpos.service.core.mail.MailSenderFactory;
import org.boatpos.service.core.util.RegkasServiceMock;
import org.boatpos.service.rest.mail.MailSenderMock;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.service.api.bean.BillBean;
import org.regkas.service.api.bean.IncomeBean;

@RunWith(Arquillian.class)
public class JournalServiceRestTest extends FillDatabaseInOtherTransactionTest {

    @ArquillianResource
    private URL url;

    @Inject
    private RestTestHelper helper;

    @Inject
    private MailSenderFactory mailSenderFactory;

    private MailSenderMock mailSenderMock = new MailSenderMock();

    @Inject
    private RegkasServiceMock regkasServiceMock;

    @Override
    @Before
    public void before() throws Exception {
        super.before();
        mailSenderMock.reset();
        mailSenderFactory.setMailSender(mailSenderMock);
    }

    @Override
    @After
    public void after() throws Exception {
        super.after();
        mailSenderMock.reset();
        regkasServiceMock.resetMockIncomeBean();
    }

    @Test
    public void testTotalIncomeForYear() throws Exception {
        regkasServiceMock.setMockIncomeBean(new IncomeBean(null, null, new ArrayList<>(), new BigDecimal("126.20"), new ArrayList<>()));
        JournalReportBean journalReportBean = helper
            .createRestCall(url, (wt) -> wt.path("journal/income/2015"))
            .get()
            .readEntity(JournalReportBean.class);
        assertYear(journalReportBean);
        assertTrue(mailSenderMock.getMailSendList().isEmpty());
    }

    @Test
    public void testTotalIncomeForCurrentYear() throws Exception {
        regkasServiceMock.setMockIncomeBean(new IncomeBean(null, null, new ArrayList<>(), new BigDecimal("126.20"), new ArrayList<>()));
        JournalReportBean journalReportBean = helper
            .createRestCall(url, (wt) -> wt.path("journal/income/year"))
            .get()
            .readEntity(JournalReportBean.class);
        assertYear(journalReportBean);
        assertTrue(mailSenderMock.getMailSendList().isEmpty());
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
        regkasServiceMock.setMockIncomeBean(new IncomeBean(null, null, new ArrayList<>(), new BigDecimal("86.20"), new ArrayList<>()));
        JournalReportBean journalReportBean = helper
            .createRestCall(url, (wt) -> wt.path("journal/income/2015/7"))
            .get()
            .readEntity(JournalReportBean.class);
        assertMonth(journalReportBean);
        assertTrue(mailSenderMock.getMailSendList().isEmpty());
    }

    @Test
    public void testTotalIncomeForCurrentMonth() throws Exception {
        regkasServiceMock.setMockIncomeBean(new IncomeBean(null, null, new ArrayList<>(), new BigDecimal("86.20"), new ArrayList<>()));
        JournalReportBean journalReportBean = helper
            .createRestCall(url, (wt) -> wt.path("journal/income/month"))
            .get()
            .readEntity(JournalReportBean.class);
        assertMonth(journalReportBean);
        assertTrue(mailSenderMock.getMailSendList().isEmpty());
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
        regkasServiceMock.setMockIncomeBean(new IncomeBean(null, null, new ArrayList<>(), new BigDecimal("46.20"), new ArrayList<>()));
        JournalReportBean journalReportBean = helper
            .createRestCall(url, (wt) -> wt.path("journal/income/2015/7/1"))
            .get()
            .readEntity(JournalReportBean.class);
        assertDay(journalReportBean);
        assertTrue(mailSenderMock.getMailSendList().isEmpty());
    }

    @Test
    public void testTotalIncomeForCurrentDay() throws Exception {
        regkasServiceMock.setMockIncomeBean(new IncomeBean(null, null, new ArrayList<>(), new BigDecimal("46.20"), new ArrayList<>()));
        JournalReportBean journalReportBean = helper
            .createRestCall(url, (wt) -> wt.path("journal/income/day"))
            .get()
            .readEntity(JournalReportBean.class);
        assertDay(journalReportBean);
        assertTrue(mailSenderMock.getMailSendList().isEmpty());
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

    @Test
    public void testDatenerfassungprotokollRKSV() throws Exception {
        assertEquals(
            Response.Status.OK,
            helper
                .createRestCall(
                    url,
                    (wt) -> wt.path("journal/dep/rksv").queryParam("username", "boatpos").queryParam("password", "test123"),
                    new MediaType("application", "zip"))
                .get()
                .getStatusInfo());
    }

    @Test
    public void testDatenerfassungprotokoll() throws Exception {
        assertEquals(
            Response.Status.OK,
            helper
                .createRestCall(
                    url,
                    (wt) -> wt.path("journal/dep/2015").queryParam("username", "boatpos").queryParam("password", "test123"),
                    new MediaType("application", "zip"))
                .get()
                .getStatusInfo());
        assertEquals(
            Response.Status.OK,
            helper
                .createRestCall(
                    url,
                    (wt) -> wt.path("journal/dep/2015/7").queryParam("username", "boatpos").queryParam("password", "test123"),
                    new MediaType("application", "zip"))
                .get()
                .getStatusInfo());
        assertEquals(
            Response.Status.OK,
            helper
                .createRestCall(
                    url,
                    (wt) -> wt.path("journal/dep/2015/7/1").queryParam("username", "boatpos").queryParam("password", "test123"),
                    new MediaType("application", "zip"))
                .get()
                .getStatusInfo());
    }

    @Test
    public void getGetReceiptById() throws Exception {
        regkasServiceMock.setMockIncomeBean(new IncomeBean(null, null, new ArrayList<>(), new BigDecimal("126.20"), new ArrayList<>()));
        BillBean billBean = helper
                .createRestCall(url, (wt) -> wt.path("journal/receipt/id/2015-0000002"))
                .get()
                .readEntity(BillBean.class);
        assertEquals(BigDecimal.ONE, billBean.getSumTotal());
        assertEquals("cashboxId", billBean.getCashBoxID());
    }

}
