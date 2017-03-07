package org.regkas.repository.core.dep;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.regkas.service.api.bean.BillBean;

import com.google.gson.annotations.Expose;

/**
 * Representation of the DEP-export.
 */
@SuppressWarnings("unused")
public class DepExportRKV2012 {

    @Expose
    private String company;

    @Expose
    private String atu;

    @Expose
    private String cashBoxId;

    @Expose
    private LocalDateTime created;

    @Expose
    private LocalDateTime from;

    @Expose
    private LocalDateTime to;

    @Expose
    private List<BillBean> cashBoxInstructionList = new ArrayList<>();

    public DepExportRKV2012() {
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAtu() {
        return atu;
    }

    public void setAtu(String atu) {
        this.atu = atu;
    }

    public String getCashBoxId() {
        return cashBoxId;
    }

    public void setCashBoxId(String cashBoxId) {
        this.cashBoxId = cashBoxId;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getFrom() {
        return from;
    }

    public void setFrom(LocalDateTime from) {
        this.from = from;
    }

    public LocalDateTime getTo() {
        return to;
    }

    public void setTo(LocalDateTime to) {
        this.to = to;
    }

    public List<BillBean> getCashBoxInstructionList() {
        return cashBoxInstructionList;
    }

    public void setCashBoxInstructionList(List<BillBean> cashBoxInstructionList) {
        this.cashBoxInstructionList = cashBoxInstructionList;
    }
}
