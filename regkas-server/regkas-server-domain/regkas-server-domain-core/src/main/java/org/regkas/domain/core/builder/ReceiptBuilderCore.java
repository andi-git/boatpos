package org.regkas.domain.core.builder;

import org.boatpos.common.model.PaymentMethod;
import org.boatpos.common.domain.core.builder.DomainModelBuilderCore;
import org.regkas.domain.api.model.CashBox;
import org.regkas.domain.api.model.Company;
import org.regkas.domain.api.model.Receipt;
import org.regkas.domain.api.model.ReceiptElement;
import org.regkas.domain.api.model.ReceiptType;
import org.regkas.domain.api.model.User;
import org.regkas.domain.api.values.DEPString;
import org.regkas.domain.api.values.EncryptedTurnoverValue;
import org.regkas.domain.api.values.ReceiptDate;
import org.regkas.domain.api.values.ReceiptId;
import org.regkas.domain.api.values.SignatureValuePreviousReceipt;
import org.regkas.domain.api.values.SuiteId;
import org.regkas.domain.api.values.TotalPrice;
import org.regkas.model.ReceiptEntity;
import org.regkas.domain.api.builder.ReceiptBuilder;
import org.regkas.domain.api.signature.CompactJWSRepresentation;
import org.regkas.domain.core.model.ReceiptCore;

import javax.enterprise.context.Dependent;
import java.util.ArrayList;
import java.util.List;

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

    private DEPString dep;

    private TotalPrice totalPrice;

    private SuiteId suiteId;

    private CompactJWSRepresentation compactJwsRepresentation;

    private List<ReceiptElement> receiptElements = new ArrayList<>();

    @Override
    public Receipt build() {
        return new ReceiptCore(id, version, receiptId, receiptDate, encryptedTurnoverValue, signatureValuePreviousReceipt, company, user, receiptType, cashBox, paymentMethod, dep, totalPrice, suiteId, compactJwsRepresentation, receiptElements);
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
    public ReceiptBuilder add(DEPString dep) {
        this.dep = dep;
        return this;
    }

    @Override
    public ReceiptBuilder add(TotalPrice totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }

    @Override
    public ReceiptBuilder add(SuiteId suiteId) {
        this.suiteId = suiteId;
        return this;
    }

    @Override
    public ReceiptBuilder add(CompactJWSRepresentation compactJwsRepresentation) {
        this.compactJwsRepresentation = compactJwsRepresentation;
        return this;
    }

    @Override
    public ReceiptBuilder add(ReceiptElement receiptElement) {
        this.receiptElements.add(receiptElement);
        return this;
    }
}
