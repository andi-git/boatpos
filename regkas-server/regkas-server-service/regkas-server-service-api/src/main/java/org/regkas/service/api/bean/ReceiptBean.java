package org.regkas.service.api.bean;

import com.google.gson.annotations.Expose;
import org.boatpos.common.service.api.bean.AbstractBeanBasedOnEntity;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;

public class ReceiptBean extends AbstractBeanBasedOnEntity {

    @NotNull
    @Size(min = 3, max = 50)
    @Expose
    private String receiptId;

    @NotNull
    @Expose
    private LocalDateTime receiptDate;

    @NotNull
    @Size(min = 3, max = 50)
    @Expose
    private String encryptedTurnoverValue;

    @NotNull
    @Size(min = 3, max = 50)
    @Expose
    private String signatureValuePreviousReceipt;

    @NotNull
    @Valid
    @Expose
    private CompanyBean company;

    @NotNull
    @Valid
    @Expose
    private String cashbox;

    @NotNull
    @Size(min = 3, max = 100)
    @Expose
    private String receiptType;

    @Valid
    @Expose
    private Set<ReceiptElementBean> receiptElements;

    public ReceiptBean() {
    }

    public ReceiptBean(String receiptId, LocalDateTime receiptDate, String encryptedTurnoverValue, String signatureValuePreviousReceipt, CompanyBean company, String cashbox, String receiptType, Set<ReceiptElementBean> receiptElements) {
        this.receiptId = receiptId;
        this.receiptDate = receiptDate;
        this.encryptedTurnoverValue = encryptedTurnoverValue;
        this.signatureValuePreviousReceipt = signatureValuePreviousReceipt;
        this.company = company;
        this.cashbox = cashbox;
        this.receiptType = receiptType;
        this.receiptElements = receiptElements;
    }

    public String getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(String receiptId) {
        this.receiptId = receiptId;
    }

    public LocalDateTime getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(LocalDateTime receiptDate) {
        this.receiptDate = receiptDate;
    }

    public String getEncryptedTurnoverValue() {
        return encryptedTurnoverValue;
    }

    public void setEncryptedTurnoverValue(String encryptedTurnoverValue) {
        this.encryptedTurnoverValue = encryptedTurnoverValue;
    }

    public String getSignatureValuePreviousReceipt() {
        return signatureValuePreviousReceipt;
    }

    public void setSignatureValuePreviousReceipt(String signatureValuePreviousReceipt) {
        this.signatureValuePreviousReceipt = signatureValuePreviousReceipt;
    }

    public CompanyBean getCompany() {
        return company;
    }

    public void setCompany(CompanyBean company) {
        this.company = company;
    }

    public String getCashbox() {
        return cashbox;
    }

    public void setCashbox(String cashbox) {
        this.cashbox = cashbox;
    }

    public String getReceiptType() {
        return receiptType;
    }

    public void setReceiptType(String receiptType) {
        this.receiptType = receiptType;
    }

    public Set<ReceiptElementBean> getReceiptElements() {
        return receiptElements;
    }

    public void setReceiptElements(Set<ReceiptElementBean> receiptElements) {
        this.receiptElements = receiptElements;
    }
}
