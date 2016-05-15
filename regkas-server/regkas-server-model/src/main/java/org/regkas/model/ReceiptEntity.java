package org.regkas.model;

import com.google.gson.annotations.Expose;
import org.boatpos.common.model.AbstractEntity;
import org.boatpos.common.model.PaymentMethod;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Representation of a receipt.
 */
@SuppressWarnings({"unused", "JpaDataSourceORMInspection"})
@Entity
@Table(name = "receipt")
public class ReceiptEntity extends AbstractEntity {

    @NotNull
    @Size(min = 3, max = 50)
    @Expose
    private String receiptId;

    @NotNull
    @Expose
    private LocalDateTime receiptDate;

    @NotNull
    @Size(max = 50)
    @Expose
    private String encryptedTurnoverValue;

    @NotNull
    @Size(max = 50)
    @Expose
    private String signatureValuePreviousReceipt;

    @NotNull
    @Valid
    @ManyToOne(cascade = CascadeType.ALL)
    @Expose
    private CompanyEntity company;

    @NotNull
    @Valid
    @ManyToOne(cascade = CascadeType.ALL)
    @Expose
    private CashBoxEntity cashBox;

    @NotNull
    @Valid
    @ManyToOne(cascade = CascadeType.ALL)
    @Expose
    private UserEntity user;

    @NotNull
    @Valid
    @ManyToOne(cascade = CascadeType.ALL)
    @Expose
    private ReceiptTypeEntity receiptType;

    @Valid
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Expose
    private List<ReceiptElementEntity> receiptElements;

    @Expose
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Expose
    @Enumerated(EnumType.STRING)
    private TimeType timeType;

    @Size(max = 4096)
    private String dep;

    public ReceiptEntity() {
    }

    public ReceiptEntity(Long id, Integer version, String receiptId, LocalDateTime receiptDate, String encryptedTurnoverValue, String signatureValuePreviousReceipt, CompanyEntity company, CashBoxEntity cashBox, UserEntity user, ReceiptTypeEntity receiptType, PaymentMethod paymentMethod, TimeType timeType, List<ReceiptElementEntity> receiptElements, String dep) {
        super(id, version);
        this.receiptId = receiptId;
        this.receiptDate = receiptDate;
        this.encryptedTurnoverValue = encryptedTurnoverValue;
        this.signatureValuePreviousReceipt = signatureValuePreviousReceipt;
        this.company = company;
        this.cashBox = cashBox;
        this.user = user;
        this.receiptType = receiptType;
        this.receiptElements = receiptElements;
        this.paymentMethod = paymentMethod;
        this.timeType = timeType;
        this.dep = dep;
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

    public CompanyEntity getCompany() {
        return company;
    }

    public void setCompany(CompanyEntity company) {
        this.company = company;
    }

    public CashBoxEntity getCashBox() {
        return cashBox;
    }

    public void setCashBox(CashBoxEntity cashBox) {
        this.cashBox = cashBox;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public ReceiptTypeEntity getReceiptType() {
        return receiptType;
    }

    public void setReceiptType(ReceiptTypeEntity receiptType) {
        this.receiptType = receiptType;
    }

    public List<ReceiptElementEntity> getReceiptElements() {
        return receiptElements;
    }

    public void setReceiptElements(List<ReceiptElementEntity> receiptElements) {
        this.receiptElements = receiptElements;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public TimeType getTimeType() {
        return timeType;
    }

    public void setTimeType(TimeType timeType) {
        this.timeType = timeType;
    }

    public String getDep() {
        return dep;
    }

    public void setDep(String dep) {
        this.dep = dep;
    }
}
