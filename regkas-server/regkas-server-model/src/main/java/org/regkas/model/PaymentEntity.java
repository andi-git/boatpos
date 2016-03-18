package org.regkas.model;

import com.google.gson.annotations.Expose;
import org.boatpos.common.model.AbstractEntity;
import org.boatpos.common.model.AbstractMasterDataEntity;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Representation of a payment.
 */
@SuppressWarnings({"unused", "JpaDataSourceORMInspection"})
@Entity
@Table(name = "company")
public class PaymentEntity extends AbstractEntity {

    @NotNull
    @Size(min = 3, max = 50)
    @Expose
    private String name;

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

    @Valid
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Expose
    private Set<PaymentElementEntity> paymentElement;

    public PaymentEntity() {
    }

    public PaymentEntity(Long id, Integer version, String name, LocalDateTime dateTime, String encryptedTurnoverValue, String signatureValuePreviousReceipt, CompanyEntity company, CashBoxEntity cashbox, UserEntity user, Set<PaymentElementEntity> paymentElement) {
        super(id, version);
        this.name = name;
        this.dateTime = dateTime;
        this.encryptedTurnoverValue = encryptedTurnoverValue;
        this.signatureValuePreviousReceipt = signatureValuePreviousReceipt;
        this.company = company;
        this.cashbox = cashbox;
        this.user = user;
        this.paymentElement = paymentElement;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Set<PaymentElementEntity> getPaymentElement() {
        return paymentElement;
    }

    public void setPaymentElement(Set<PaymentElementEntity> paymentElement) {
        this.paymentElement = paymentElement;
    }
}
