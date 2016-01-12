package org.boatpos.service.api.bean;

import org.boatpos.test.JavaBeanTest;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class JournalReportItemBeanTest extends JavaBeanTest<JournalReportItemBean> {

    @Test
    public void testConstructor() {
        new JournalReportItemBean(new BoatBean(), BigDecimal.ZERO);
    }

    @Test
    public void testEqualsAndHashCode() {
        BoatBean boatBean1 = new BoatBean();
        BoatBean boatBean2 = new BoatBean();
        boatBean1.setId(1L);
        boatBean2.setId(2L);
        JournalReportItemBean journalReportItemBean1 = new JournalReportItemBean(boatBean1, BigDecimal.ZERO);
        JournalReportItemBean journalReportItemBean2 = new JournalReportItemBean(boatBean2, BigDecimal.ZERO);
        assertEquals(journalReportItemBean1, journalReportItemBean1);
        assertEquals(journalReportItemBean2, journalReportItemBean2);
        assertNotEquals(journalReportItemBean1, journalReportItemBean2);
        assertEquals(journalReportItemBean1.hashCode(), journalReportItemBean1.hashCode());
        assertEquals(journalReportItemBean2.hashCode(), journalReportItemBean2.hashCode());
        assertNotEquals(journalReportItemBean1.hashCode(), journalReportItemBean2.hashCode());
    }


    @Override
    protected Class<JournalReportItemBean> getType() {
        return JournalReportItemBean.class;
    }
}