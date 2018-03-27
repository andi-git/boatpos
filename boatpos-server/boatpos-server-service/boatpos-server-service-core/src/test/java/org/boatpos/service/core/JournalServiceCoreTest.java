package org.boatpos.service.core;

import org.boatpos.service.api.JournalService;
import org.boatpos.service.api.bean.JournalReportBean;
import org.boatpos.service.core.mail.MailSenderFactory;
import org.boatpos.service.core.mail.MailSenderMock;
import org.boatpos.service.core.util.RegkasServiceMock;
import org.boatpos.test.model.EntityManagerProviderForBoatpos;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.service.api.bean.IncomeBean;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class JournalServiceCoreTest extends EntityManagerProviderForBoatpos {

    @Inject
    private JournalService journalService;

    @Inject
    private MailSenderFactory mailSenderFactory;

    private MailSenderMock mailSenderMock = new MailSenderMock();

    @Inject
    private RegkasServiceMock regkasServiceMock;

    @Before
    public void before() {
        mailSenderMock.reset();
        mailSenderFactory.setMailSender(mailSenderMock);
    }

    @After
    public void after() {
        mailSenderMock.reset();
        regkasServiceMock.resetMockIncomeBean();
    }

    @Test
    @Transactional
    public void testTotalIncomeForYear() {
        regkasServiceMock.setMockIncomeBean(new IncomeBean(null, null, new ArrayList<>(), new BigDecimal("126.20"), new ArrayList<>()));
        JournalReportBean journalReportBean = journalService.totalIncomeFor(2015);
        assertEquals("E-Boot", journalReportBean.getJournalReportItemBeans().get(0).getBoatName());
        assertEquals(new BigDecimal("32.00"), journalReportBean.getJournalReportItemBeans().get(0).getPricePaidBeforeCard());
        assertEquals(new BigDecimal("26.67"), journalReportBean.getJournalReportItemBeans().get(0).getPricePaidBeforeCardBeforeTax());
        assertEquals(new BigDecimal("5.33"), journalReportBean.getJournalReportItemBeans().get(0).getPricePaidBeforeCardTax());
        assertEquals(new BigDecimal("81.60"), journalReportBean.getJournalReportItemBeans().get(0).getPricePaidAfterCash());
        assertEquals(new BigDecimal("68.00"), journalReportBean.getJournalReportItemBeans().get(0).getPricePaidAfterCashBeforeTax());
        assertEquals(new BigDecimal("13.60"), journalReportBean.getJournalReportItemBeans().get(0).getPricePaidAfterCashTax());
        assertEquals(5, journalReportBean.getJournalReportItemBeans().get(0).getCount().intValue());
        assertEquals(0, journalReportBean.getJournalReportItemBeans().get(1).getCount().intValue());
        assertEquals(new BigDecimal("12.60"), journalReportBean.getJournalReportItemBeans().get(2).getPricePaidAfterCash());
        assertEquals(1, journalReportBean.getJournalReportItemBeans().get(2).getCount().intValue());
        assertTrue(mailSenderMock.getMailSendList().isEmpty());
    }

    @Test
    @Transactional
    public void testTotalIncomeForMonth() {
        regkasServiceMock.setMockIncomeBean(new IncomeBean(null, null, new ArrayList<>(), new BigDecimal("86.20"), new ArrayList<>()));
        JournalReportBean journalReportBean = journalService.totalIncomeFor(2015, 7);
        assertEquals("E-Boot", journalReportBean.getJournalReportItemBeans().get(0).getBoatName());
        assertEquals(new BigDecimal("32.00"), journalReportBean.getJournalReportItemBeans().get(0).getPricePaidBeforeCard());
        assertEquals(new BigDecimal("26.67"), journalReportBean.getJournalReportItemBeans().get(0).getPricePaidBeforeCardBeforeTax());
        assertEquals(new BigDecimal("5.33"), journalReportBean.getJournalReportItemBeans().get(0).getPricePaidBeforeCardTax());
        assertEquals(new BigDecimal("41.60"), journalReportBean.getJournalReportItemBeans().get(0).getPricePaidAfterCash());
        assertEquals(new BigDecimal("34.67"), journalReportBean.getJournalReportItemBeans().get(0).getPricePaidAfterCashBeforeTax());
        assertEquals(new BigDecimal("6.93"), journalReportBean.getJournalReportItemBeans().get(0).getPricePaidAfterCashTax());
        assertEquals(3, journalReportBean.getJournalReportItemBeans().get(0).getCount().intValue());
        assertEquals(0, journalReportBean.getJournalReportItemBeans().get(1).getCount().intValue());
        assertEquals(new BigDecimal("12.60"), journalReportBean.getJournalReportItemBeans().get(2).getPricePaidAfterCash());
        assertEquals(1, journalReportBean.getJournalReportItemBeans().get(2).getCount().intValue());
        assertTrue(mailSenderMock.getMailSendList().isEmpty());
    }

    @Test
    @Transactional
    public void testTotalIncomeForDay() {
        regkasServiceMock.setMockIncomeBean(new IncomeBean(null, null, new ArrayList<>(), new BigDecimal("46.20"), new ArrayList<>()));
        JournalReportBean journalReportBean = journalService.totalIncomeFor(2015, 7, 1);
        assertEquals("E-Boot", journalReportBean.getJournalReportItemBeans().get(0).getBoatName());
        assertEquals(new BigDecimal("32.00"), journalReportBean.getJournalReportItemBeans().get(0).getPricePaidBeforeCard());
        assertEquals(new BigDecimal("26.67"), journalReportBean.getJournalReportItemBeans().get(0).getPricePaidBeforeCardBeforeTax());
        assertEquals(new BigDecimal("5.33"), journalReportBean.getJournalReportItemBeans().get(0).getPricePaidBeforeCardTax());
        assertEquals(new BigDecimal("1.60"), journalReportBean.getJournalReportItemBeans().get(0).getPricePaidAfterCash());
        assertEquals(new BigDecimal("1.33"), journalReportBean.getJournalReportItemBeans().get(0).getPricePaidAfterCashBeforeTax());
        assertEquals(new BigDecimal("0.27"), journalReportBean.getJournalReportItemBeans().get(0).getPricePaidAfterCashTax());
        assertEquals(1, journalReportBean.getJournalReportItemBeans().get(0).getCount().intValue());
        assertEquals(0, journalReportBean.getJournalReportItemBeans().get(1).getCount().intValue());
        assertEquals(new BigDecimal("12.60"), journalReportBean.getJournalReportItemBeans().get(2).getPricePaidAfterCash());
        assertEquals(1, journalReportBean.getJournalReportItemBeans().get(2).getCount().intValue());
        assertTrue(mailSenderMock.getMailSendList().isEmpty());
    }

    @Test
    @Transactional
    public void testTotalIncomeForDayWithDifferentRegkasBoatposIncome() {
        regkasServiceMock.setMockIncomeBean(new IncomeBean(null, null, new ArrayList<>(), new BigDecimal("12.30"), new ArrayList<>()));
        journalService.totalIncomeFor(2015, 7, 1);
        assertFalse(mailSenderMock.getMailSendList().isEmpty());
    }

    @Test
    @Transactional
    public void testDatenErfassungsProtokollRKSV() {
        assertEquals("depRKSV", journalService.datenErfassungsProtokollRKSV().getName());
    }

    @Test
    @Transactional
    public void testDatenErfassungsProtokoll() {
        assertEquals("depYear", journalService.datenErfassungsProtokoll(2015).getName());
        assertEquals("depYearMonth", journalService.datenErfassungsProtokoll(2015, 7).getName());
        assertEquals("depYearMonthDay", journalService.datenErfassungsProtokoll(2015, 7, 1).getName());
    }
}