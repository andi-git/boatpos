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
    private Set<ReceiptElementEntity> receiptElements;

    public ReceiptEntity() {
    }

    public ReceiptEntity(Long id, Integer version, String receiptId, LocalDateTime receiptDate, String encryptedTurnoverValue, String signatureValuePreviousReceipt, CompanyEntity company, CashBoxEntity cashBox, UserEntity user, ReceiptTypeEntity receiptType, Set<ReceiptElementEntity> receiptElements) {
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

    public Set<ReceiptElementEntity> getReceiptElements() {
        return receiptElements;
    }

    public void setReceiptElements(Set<ReceiptElementEntity> receiptElements) {
        this.receiptElements = receiptElements;
    }
}