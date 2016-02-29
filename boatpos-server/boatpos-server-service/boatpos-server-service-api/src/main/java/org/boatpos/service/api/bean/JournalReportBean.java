package org.boatpos.service.api.bean;

import com.google.common.base.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JournalReportBean that = (JournalReportBean) o;
        return Objects.equal(journalReportItemBeans, that.journalReportItemBeans);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(journalReportItemBeans);
    }

    @Override
    public String toString() {
        return "JournalReportBean{" +
                "journalReportItemBeans=" + journalReportItemBeans +
                '}';
    }
}
