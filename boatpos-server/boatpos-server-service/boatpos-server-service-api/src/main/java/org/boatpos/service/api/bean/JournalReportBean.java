package org.boatpos.service.api.bean;

import java.util.ArrayList;
import java.util.List;

public class JournalReportBean extends AbstractBean {

    private List<JournalReportItemBean> journalReportItemBeans = new ArrayList<>();

    public JournalReportBean() {
    }

    public JournalReportBean(List<JournalReportItemBean> journalReportItemBeans) {
        this.journalReportItemBeans = journalReportItemBeans;
    }

    public List<JournalReportItemBean> getJournalReportItemBeans() {
        return journalReportItemBeans;
    }

    public void setJournalReportItemBeans(List<JournalReportItemBean> journalReportItemBeans) {
        this.journalReportItemBeans.clear();
        this.journalReportItemBeans.addAll(journalReportItemBeans);
    }

    public void addJournalReportItemBean(JournalReportItemBean journalReportItemBean) {
        this.getJournalReportItemBeans().add(journalReportItemBean);
    }
}
