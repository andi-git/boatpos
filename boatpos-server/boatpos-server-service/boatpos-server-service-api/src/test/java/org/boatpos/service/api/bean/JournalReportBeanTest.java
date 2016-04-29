package org.boatpos.service.api.bean;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

public class JournalReportBeanTest {

    @Test
    public void testConstructorAndSomeMethods() {
        new JournalReportBean();
        JournalReportBean journalReportBean = new JournalReportBean(LocalDate.now(), LocalDate.now(), Lists.newArrayList());
        journalReportBean.setJournalReportItemBeans(Lists.newArrayList());
        journalReportBean.getJournalReportItemBeans();
        journalReportBean.addJournalReportItemBean(new JournalReportItemBean("boat1", BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, 0));
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @Test
    public void testOther() {
        new JournalReportBean().toString();
        new JournalReportBean().hashCode();
        new JournalReportBean().equals(new JournalReportBean());
        new JournalReportBean().setStart(LocalDate.now());
        new JournalReportBean().getStart();
        new JournalReportBean().setEnd(LocalDate.now());
        new JournalReportBean().getEnd();
    }
}