package org.boatpos.service.api.bean;

import com.google.common.base.Objects;
import com.google.gson.annotations.Expose;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class JournalReportBean extends AbstractBean {

    @Expose
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate start;

    @Expose
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate end;

    @Expose
    private List<JournalReportItemBean> journalReportItemBeans = new ArrayList<>();

    public JournalReportBean() {
    }

    public JournalReportBean(LocalDate start, LocalDate end, List<JournalReportItemBean> journalReportItemBeans) {
        this.start = start;
        this.end = end;
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

    public LocalDate getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JournalReportBean that = (JournalReportBean) o;
        return Objects.equal(start, that.start) &&
                Objects.equal(end, that.end) &&
                Objects.equal(journalReportItemBeans, that.journalReportItemBeans);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(start, end, journalReportItemBeans);
    }
}
