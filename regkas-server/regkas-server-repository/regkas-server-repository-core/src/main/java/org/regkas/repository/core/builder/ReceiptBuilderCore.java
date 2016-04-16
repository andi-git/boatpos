package org.regkas.repository.core.builder;

import org.boatpos.common.model.PaymentMethod;
import org.boatpos.common.repository.core.builder.DomainModelBuilderCore;
import org.regkas.model.ReceiptEntity;
import org.regkas.model.TimeType;
import org.regkas.repository.api.builder.ReceiptBuilder;
import org.regkas.repository.api.model.*;
import org.regkas.repository.api.values.EncryptedTurnoverValue;
import org.regkas.repository.api.values.ReceiptDate;
import org.regkas.repository.api.values.ReceiptId;
import org.regkas.repository.api.values.SignatureValuePreviousReceipt;
import org.regkas.repository.core.model.ReceiptCore;

import javax.enterprise.context.Dependent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Dependent
public class ReceiptBuilderCore
        extends DomainModelBuilderCore<ReceiptBuilder, Receipt, ReceiptCore, ReceiptEntity>
        implements ReceiptBuilder {

    private ReceiptId receiptId;

    private ReceiptDate receiptDate;

    private EncryptedTurnoverValue encryptedTurnoverValue;

    private SignatureValuePreviousReceipt signatureValuePreviousReceipt;

    private Company company;

    private User user;

    private ReceiptType receiptType;

    private CashBox cashBox;

    private PaymentMethod paymentMethod;

    private TimeType timeType;

    private List<ReceiptElement> receiptElements = new ArrayList<>();

    @Override
    public Receipt build() {
        return new ReceiptCore(id, version, receiptId, receiptDate, encryptedTurnoverValue, signatureValuePreviousReceipt, company, user, receiptType, cashBox, paymentMethod, timeType, receiptElements);
    }

    @Override
    public ReceiptBuilder add(ReceiptId receiptId) {
        this.receiptId = receiptId;
        return this;
    }

    @Override
    public ReceiptBuilder add(ReceiptDate receiptDate) {
        this.receiptDate = receiptDate;
        return this;
    }

    @Override
    public ReceiptBuilder add(EncryptedTurnoverValue encryptedTurnoverValue) {
        this.encryptedTurnoverValue = encryptedTurnoverValue;
        return this;
    }

    @Override
    public ReceiptBuilder add(SignatureValuePreviousReceipt signatureValuePreviousReceipt) {
        this.signatureValuePreviousReceipt = signatureValuePreviousReceipt;
        return this;
    }

    @Override
    public ReceiptBuilder add(Company company) {
        this.company = company;
        return this;
    }

    @Override
    public ReceiptBuilder add(User user) {
        this.user = user;
        return this;
    }

    @Override
    public ReceiptBuilder add(ReceiptType receiptType) {
        this.receiptType = receiptType;
        return this;
    }

    @Override
    public ReceiptBuilder add(CashBox cashBox) {
        this.cashBox = cashBox;
        return this;
    }

    @Override
    public ReceiptBuilder add(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }

    @Override
    public ReceiptBuilder add(TimeType timeType) {
        this.timeType = timeType;
        return this;
    }

    @Override
    public ReceiptBuilder add(ReceiptElement receiptElement) {
        this.receiptElements.add(receiptElement);
        return this;
    }
}
