package org.regkas.model;

import com.google.gson.annotations.Expose;
import org.boatpos.common.model.AbstractEntity;
import org.boatpos.common.model.PaymentMethod;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
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

    @NotNull
    @Expose
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Size(max = 4096)
    private String dep;

    @Expose
    private BigDecimal totalPrice;

    @NotNull
    @Size(min = 5, max = 6)
    @Expose
    private String suiteId;

    @Size(max = 512)
    @Expose
    private String machineReadableRepresentation;

    @Size(max = 512)
    @Expose
    private String compactJwsRepresentation;

    public ReceiptEntity() {
    }

    public ReceiptEntity(Long id, Integer version, String receiptId, LocalDateTime receiptDate, String encryptedTurnoverValue, String signatureValuePreviousReceipt, CompanyEntity company, CashBoxEntity cashBox, UserEntity user, ReceiptTypeEntity receiptType, PaymentMethod paymentMethod, List<ReceiptElementEntity> receiptElements, String dep, BigDecimal totalPrice, String suiteId, String machineReadableRepresentation, String compactJwsRepresentation) {
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
        this.dep = dep;
        this.totalPrice = totalPrice;
        this.suiteId = suiteId;
        this.machineReadableRepresentation = machineReadableRepresentation;
        this.compactJwsRepresentation = compactJwsRepresentation;
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

    public String getDep() {
        return dep;
    }

    public void setDep(String dep) {
        this.dep = dep;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getSuiteId() {
        return suiteId;
    }

    public void setSuiteId(String suiteId) {
        this.suiteId = suiteId;
    }

    public String getMachineReadableRepresentation() {
        return machineReadableRepresentation;
    }

    public void setMachineReadableRepresentation(String jwsCompactRepresentation) {
        this.machineReadableRepresentation = jwsCompactRepresentation;
    }

    public String getCompactJwsRepresentation() {
        return compactJwsRepresentation;
    }

    public void setCompactJwsRepresentation(String compactJwsRepresentation) {
        this.compactJwsRepresentation = compactJwsRepresentation;
    }
}
