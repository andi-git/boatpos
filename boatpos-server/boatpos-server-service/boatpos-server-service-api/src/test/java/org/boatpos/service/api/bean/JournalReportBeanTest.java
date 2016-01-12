package org.boatpos.service.api.bean;

import com.google.common.collect.Lists;
import org.junit.Test;

public class JournalReportBeanTest {

    @Test
    public void testConstructorAndSomeMethods() {
        new JournalReportBean();
        JournalReportBean journalReportBean = new JournalReportBean(Lists.newArrayList());
        journalReportBean.setJournalReportItemBeans(Lists.newArrayList());
        journalReportBean.getJournalReportItemBeans();
        journalReportBean.addJournalReportItemBean(new JournalReportItemBean());
    }
}