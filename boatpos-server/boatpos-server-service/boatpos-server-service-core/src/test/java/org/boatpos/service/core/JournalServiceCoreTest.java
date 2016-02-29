package org.boatpos.service.core;

import org.boatpos.service.api.JournalService;
import org.boatpos.service.api.bean.JournalReportBean;
import org.boatpos.service.api.bean.JournalReportItemBean;
import org.boatpos.test.model.EntityManagerProviderForBoatpos;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.math.BigDecimal;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class JournalServiceCoreTest extends EntityManagerProviderForBoatpos {

    @Inject
    private JournalService journalService;

    @Test
    @Transactional
    public void testTotalIncomeForYear() throws Exception {
        JournalReportBean journalReportBean = journalService.totalIncomeFor(2015);
        assertEquals("E-Boot", journalReportBean.getJournalReportItemBeans().get(0).getBoatName());
        assertEquals(new BigDecimal("32.00"), journalReportBean.getJournalReportItemBeans().get(0).getPricePaidBeforeCard());
        assertEquals(new BigDecimal("81.60"), journalReportBean.getJournalReportItemBeans().get(0).getPricePaidAfterCash());
        assertEquals(5, journalReportBean.getJournalReportItemBeans().get(0).getCount().intValue());
        assertEquals(0, journalReportBean.getJournalReportItemBeans().get(1).getCount().intValue());
        assertEquals(new BigDecimal("12.60"), journalReportBean.getJournalReportItemBeans().get(2).getPricePaidAfterCash());
        assertEquals(1, journalReportBean.getJournalReportItemBeans().get(2).getCount().intValue());
    }

    @Test
    @Transactional
    public void testTotalIncomeForMonth() throws Exception {
        JournalReportBean journalReportBean = journalService.totalIncomeFor(2015, 7);
        assertEquals("E-Boot", journalReportBean.getJournalReportItemBeans().get(0).getBoatName());
        assertEquals(new BigDecimal("32.00"), journalReportBean.getJournalReportItemBeans().get(0).getPricePaidBeforeCard());
        assertEquals(new BigDecimal("41.60"), journalReportBean.getJournalReportItemBeans().get(0).getPricePaidAfterCash());
        assertEquals(3, journalReportBean.getJournalReportItemBeans().get(0).getCount().intValue());
        assertEquals(0, journalReportBean.getJournalReportItemBeans().get(1).getCount().intValue());
        assertEquals(new BigDecimal("12.60"), journalReportBean.getJournalReportItemBeans().get(2).getPricePaidAfterCash());
        assertEquals(1, journalReportBean.getJournalReportItemBeans().get(2).getCount().intValue());
    }

    @Test
    @Transactional
    public void testTotalIncomeForDay() throws Exception {
        JournalReportBean journalReportBean = journalService.totalIncomeFor(2015, 7, 1);
        assertEquals("E-Boot", journalReportBean.getJournalReportItemBeans().get(0).getBoatName());
        assertEquals(new BigDecimal("32.00"), journalReportBean.getJournalReportItemBeans().get(0).getPricePaidBeforeCard());
        assertEquals(new BigDecimal("1.60"), journalReportBean.getJournalReportItemBeans().get(0).getPricePaidAfterCash());
        assertEquals(1, journalReportBean.getJournalReportItemBeans().get(0).getCount().intValue());
        assertEquals(0, journalReportBean.getJournalReportItemBeans().get(1).getCount().intValue());
        assertEquals(new BigDecimal("12.60"), journalReportBean.getJournalReportItemBeans().get(2).getPricePaidAfterCash());
        assertEquals(1, journalReportBean.getJournalReportItemBeans().get(2).getCount().intValue());
    }
}