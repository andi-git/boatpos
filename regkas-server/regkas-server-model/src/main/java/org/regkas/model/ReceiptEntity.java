package org.regkas.model;

import com.google.gson.annotations.Expose;
import org.boatpos.common.model.AbstractEntity;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;

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
    private LocalDateTime dateTime;

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
    @ManyToOne(cascade = CascadeType.ALL)
    @Expose
    private CompanyEntity company;

    @NotNull
    @Valid
    @ManyToOne(cascade = CascadeType.ALL)
    @Expose
    private CashBoxEntity cashbox;

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
    private Set<ReceiptElementEntity> paymentElement;

    public ReceiptEntity() {
    }

    public ReceiptEntity(Long id, Integer version, String receiptId, LocalDateTime dateTime, String encryptedTurnoverValue, String signatureValuePreviousReceipt, CompanyEntity company, CashBoxEntity cashbox, UserEntity user, ReceiptTypeEntity receiptType, Set<ReceiptElementEntity> paymentElement) {
        super(id, version);
        this.receiptId = receiptId;
        this.dateTime = dateTime;
        this.encryptedTurnoverValue = encryptedTurnoverValue;
        this.signatureValuePreviousReceipt = signatureValuePreviousReceipt;
        this.company = company;
        this.cashbox = cashbox;
        this.user = user;
        this.receiptType = receiptType;
        this.paymentElement = paymentElement;
    }

    public String getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(String receiptId) {
        this.receiptId = receiptId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
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

    public CashBoxEntity getCashbox() {
        return cashbox;
    }

    public void setCashbox(CashBoxEntity cashbox) {
        this.cashbox = cashbox;
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

    public Set<ReceiptElementEntity> getPaymentElement() {
        return paymentElement;
    }

    public void setPaymentElement(Set<ReceiptElementEntity> paymentElement) {
        this.paymentElement = paymentElement;
    }
}
