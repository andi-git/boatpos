package org.boatpos.service.api.bean;

import org.boatpos.common.test.JavaBeanTest;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class JournalReportItemBeanTest extends JavaBeanTest<JournalReportItemBean> {

    @Test
    public void testConstructor() {
        new JournalReportBean();
        new JournalReportItemBean("boat", BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, 0);
    }

    @Test
    public void testEqualsAndHashCode() {
        JournalReportItemBean journalReportItemBean1 = new JournalReportItemBean("boat1", BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, 0);
        JournalReportItemBean journalReportItemBean2 = new JournalReportItemBean("boat2", BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO, 0);
        assertEquals(journalReportItemBean1, journalReportItemBean1);
        assertEquals(journalReportItemBean2, journalReportItemBean2);
        assertNotEquals(journalReportItemBean1, journalReportItemBean2);
        assertEquals(journalReportItemBean1.hashCode(), journalReportItemBean1.hashCode());
        assertEquals(journalReportItemBean2.hashCode(), journalReportItemBean2.hashCode());
        assertNotEquals(journalReportItemBean1.hashCode(), journalReportItemBean2.hashCode());
    }
}