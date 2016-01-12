package org.boatpos.service.core;

import org.boatpos.service.api.JournalService;
import org.boatpos.service.api.bean.JournalReportBean;
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
        JournalReportBean journalReportBean = journalService.totalIncomeForYear(2015);
        assertEquals(new BigDecimal("113.60"), journalReportBean.getJournalReportItemBeans().get(0).getSum());
        assertEquals(new BigDecimal("0.00"), journalReportBean.getJournalReportItemBeans().get(1).getSum());
        assertEquals(new BigDecimal("12.60"), journalReportBean.getJournalReportItemBeans().get(2).getSum());
    }

    @Test
    @Transactional
    public void testTotalIncomeForMonth() throws Exception {
        JournalReportBean journalReportBean = journalService.totalIncomeForMonth(2015, 7);
        assertEquals(new BigDecimal("73.60"), journalReportBean.getJournalReportItemBeans().get(0).getSum());
        assertEquals(new BigDecimal("0.00"), journalReportBean.getJournalReportItemBeans().get(1).getSum());
        assertEquals(new BigDecimal("12.60"), journalReportBean.getJournalReportItemBeans().get(2).getSum());
    }

    @Test
    @Transactional
    public void testTotalIncomeForDay() throws Exception {
        JournalReportBean journalReportBean = journalService.totalIncomeForDay(2015, 7, 1);
        assertEquals(new BigDecimal("33.60"), journalReportBean.getJournalReportItemBeans().get(0).getSum());
        assertEquals(new BigDecimal("0.00"), journalReportBean.getJournalReportItemBeans().get(1).getSum());
        assertEquals(new BigDecimal("12.60"), journalReportBean.getJournalReportItemBeans().get(2).getSum());
    }
}