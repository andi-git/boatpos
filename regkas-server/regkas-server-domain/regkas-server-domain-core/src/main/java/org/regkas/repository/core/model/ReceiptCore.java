package org.regkas.repository.core.model;

import org.boatpos.common.model.PaymentMethod;
import org.boatpos.common.repository.api.model.DomainModel;
import org.boatpos.common.repository.api.values.DomainId;
import org.boatpos.common.repository.api.values.SimpleValueObject;
import org.boatpos.common.repository.api.values.Version;
import org.boatpos.common.repository.core.model.DomainModelCore;
import org.regkas.model.ReceiptEntity;
import org.regkas.model.ReceiptTypeEntity;
import org.regkas.repository.api.model.CashBox;
import org.regkas.repository.api.model.Company;
import org.regkas.repository.api.model.Receipt;
import org.regkas.repository.api.model.ReceiptElement;
import org.regkas.repository.api.model.ReceiptType;
import org.regkas.repository.api.model.TaxSet;
import org.regkas.repository.api.model.TaxSetBesonders;
import org.regkas.repository.api.model.TaxSetErmaessigt1;
import org.regkas.repository.api.model.TaxSetErmaessigt2;
import org.regkas.repository.api.model.TaxSetNormal;
import org.regkas.repository.api.model.TaxSetNull;
import org.regkas.repository.api.model.User;
import org.regkas.repository.api.serializer.NonPrettyPrintingGson;
import org.regkas.repository.api.serializer.Serializer;
import org.regkas.repository.api.signature.CompactJWSRepresentation;
import org.regkas.repository.api.values.DEPString;
import org.regkas.repository.api.values.EncryptedTurnoverValue;
import org.regkas.repository.api.values.JWSPayload;
import org.regkas.repository.api.values.ReceiptDate;
import org.regkas.repository.api.values.ReceiptId;
import org.regkas.repository.api.values.ReceiptMachineReadableRepresentation;
import org.regkas.repository.api.values.SignatureDeviceAvailable;
import org.regkas.repository.api.values.SignatureValuePreviousReceipt;
import org.regkas.repository.api.values.SuiteId;
import org.regkas.repository.api.values.TotalPrice;
import org.regkas.repository.core.builder.ReceiptTypeBuilderHolder;
import org.regkas.repository.core.crypto.Encoding;
import org.regkas.repository.core.mapping.ReceiptMapping;
import org.regkas.repository.core.signature.CompactJWSRepresentationCore;
import org.regkas.service.api.bean.BillBean;
import org.regkas.service.api.bean.ReceiptBean;

import javax.enterprise.inject.spi.CDI;
import java.lang.annotation.Annotation;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;

public class ReceiptCore extends DomainModelCore<Receipt, ReceiptEntity> implements Receipt {

    public ReceiptCore(DomainId id,
                       Version version,
                       ReceiptId receiptId,
                       ReceiptDate receiptDate,
                       EncryptedTurnoverValue encryptedTurnoverValue,
                       SignatureValuePreviousReceipt signatureValuePreviousReceipt,
                       Company company,
                       User user,
                       ReceiptType receiptType,
                       CashBox cashBox,
                       PaymentMethod paymentMethod,
                       DEPString dep,
                       TotalPrice totalPrice,
                       SuiteId suiteId,
                       CompactJWSRepresentation compactJwsRepresentation,
                       List<ReceiptElement> receiptElements) {
        super(id, version);
        checkNotNull(receiptId, "'receiptId' must not be null");
        checkNotNull(receiptDate, "'receiptDate' must not be null");
        checkNotNull(company, "'company' must not be null");
        checkNotNull(user, "'user' must not be null");
        checkNotNull(receiptType, "'receiptType' must not be null");
        checkNotNull(cashBox, "'cashBox' must not be null");
        checkNotNull(paymentMethod, "'paymentMethod' must not be null");
        checkNotNull(receiptElements, "'receiptElements' must not be null");
        checkNotNull(totalPrice, "'totalPrice' must not be null");
        checkNotNull(suiteId, "'suiteId' must not be null");
        setReceiptId(receiptId);
        setReceiptDate(receiptDate);
        setEncryptedTurnoverValue(encryptedTurnoverValue);
        setSignatureValuePreviousReceipt(signatureValuePreviousReceipt);
        setCompany(company);
        setUser(user);
        setReceiptType(receiptType);
        setCashBox(cashBox);
        setPaymentMethod(paymentMethod);
        setDEP(dep);
        setTotalPrice(totalPrice);
        setSuiteId(suiteId);
        setCompactJWSRepresentation(compactJwsRepresentation);
        addReceiptElements(receiptElements);
    }

    public ReceiptCore(ReceiptEntity receipt) {
        super(receipt);
    }

    @Override
    public ReceiptId getReceiptId() {
        return new ReceiptId(getEntity().getReceiptId());
    }

    @Override
    public Receipt setReceiptId(ReceiptId receiptId) {
        getEntity().setReceiptId(SimpleValueObject.nullSafe(receiptId));
        return this;
    }

    @Override
    public ReceiptDate getReceiptDate() {
        return new ReceiptDate(getEntity().getReceiptDate());
    }

    @Override
    public Receipt setReceiptDate(ReceiptDate receiptDate) {
        getEntity().setReceiptDate(SimpleValueObject.nullSafe(receiptDate));
        return this;
    }

    @Override
    public EncryptedTurnoverValue getEncryptedTurnoverValue() {
        return new EncryptedTurnoverValue(getEntity().getEncryptedTurnoverValue());
    }

    @Override
    public Receipt setEncryptedTurnoverValue(EncryptedTurnoverValue encryptedTurnoverValue) {
        getEntity().setEncryptedTurnoverValue(SimpleValueObject.nullSafe(encryptedTurnoverValue));
        return this;
    }

    @Override
    public SignatureValuePreviousReceipt getSignatureValuePreviousReceipt() {
        return new SignatureValuePreviousReceipt(getEntity().getSignatureValuePreviousReceipt());
    }

    @Override
    public Receipt setSignatureValuePreviousReceipt(SignatureValuePreviousReceipt signatureValuePreviousReceipt) {
        getEntity().setSignatureValuePreviousReceipt(SimpleValueObject.nullSafe(signatureValuePreviousReceipt));
        return this;
    }

    @Override
    public Company getCompany() {
        return new CompanyCore(getEntity().getCompany());
    }

    @Override
    public Receipt setCompany(Company company) {
        if (company != null) getEntity().setCompany(company.asEntity());
        return this;
    }

    @Override
    public User getUser() {
        return new UserCore(getEntity().getUser());
    }

    @Override
    public Receipt setUser(User user) {
        if (user != null) getEntity().setUser(user.asEntity());
        return this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public ReceiptType getReceiptType() {
        return CDI.current().select(ReceiptTypeBuilderHolder.class).get()
                .getReceiptTypeFor(getEntity().getReceiptType())
                .orElseThrow(() -> new RuntimeException("no builder available for " + getEntity().getReceiptType().getClass().getName()));
    }

    @Override
    public Receipt setReceiptType(ReceiptType receiptType) {
        if (receiptType != null) {
            ReceiptTypeEntity entity = (ReceiptTypeEntity) receiptType.asEntity();
            getEntity().setReceiptType(entity);
        }
        return this;
    }

    @Override
    public CashBox getCashBox() {
        return new CashBoxCore(getEntity().getCashBox());
    }

    @Override
    public Receipt setCashBox(CashBox cashBox) {
        if (cashBox != null) getEntity().setCashBox(cashBox.asEntity());
        return this;
    }

    @Override
    public PaymentMethod getPaymentMethod() {
        return getEntity().getPaymentMethod();
    }

    @Override
    public Receipt setPaymentMethod(PaymentMethod paymentMethod) {
        if (paymentMethod != null) getEntity().setPaymentMethod(paymentMethod);
        return this;
    }

    @Override
    public DEPString getDEP() {
        return new DEPString(getEntity().getDep());
    }

    @Override
    public Receipt setDEP(DEPString dep) {
        getEntity().setDep(SimpleValueObject.nullSafe(dep));
        return this;
    }

    @Override
    public TotalPrice getTotalPrice() {
        return new TotalPrice(getEntity().getTotalPrice());
    }

    @Override
    public Receipt setTotalPrice(TotalPrice totalPrice) {
        getEntity().setTotalPrice(SimpleValueObject.nullSafe(totalPrice));
        return this;
    }

    @Override
    public <T extends TaxSet> TotalPrice getTotalPriceAfterTaxOf(Class<T> taxSet) {
        return getTotalPriceOfTax(taxSet, ReceiptElement::getTotalPrice);
    }

    @Override
    public <T extends TaxSet> TotalPrice getTotalTaxOf(Class<T> taxSet) {
        return getTotalPriceOfTax(taxSet, ReceiptElement::getTotalPriceTax);
    }

    @Override
    public <T extends TaxSet> TotalPrice getTotalPriceBeforeTaxOf(Class<T> taxSet) {
        return getTotalPriceOfTax(taxSet, ReceiptElement::getTotalPriceBeforeTax);
    }

    private <T extends TaxSet> TotalPrice getTotalPriceOfTax(Class<T> taxSet, Function<ReceiptElement, TotalPrice> getTotalPrice) {
        TotalPrice totalPrice = TotalPrice.ZERO;
        for (ReceiptElement receiptElement : getReceiptElements()) {
            if (taxSet.isAssignableFrom(receiptElement.getProduct().getProductGroup().getTaxSet().getClass())) {
                totalPrice = totalPrice.add(getTotalPrice.apply(receiptElement));
            }
        }
        return totalPrice;
    }

    @Override
    public SuiteId getSuiteId() {
        return new SuiteId(getEntity().getSuiteId());
    }

    @Override
    public Receipt setSuiteId(SuiteId suiteId) {
        getEntity().setSuiteId(SimpleValueObject.nullSafe(suiteId));
        return this;
    }

    @Override
    public List<ReceiptElement> getReceiptElements() {
        return Collections.unmodifiableList(getEntity().getReceiptElements().stream().map(ReceiptElementCore::new).collect(Collectors.toList()));
    }

    @Override
    public Receipt addReceiptElements(List<ReceiptElement> receiptElements) {
        if (receiptElements != null) {
            receiptElements.stream().map(DomainModel::asEntity).forEach((re) -> re.setReceipt(getEntity()));
            getEntity().setReceiptElements(receiptElements.stream()
                    .map(DomainModel::asEntity)
                    .collect(Collectors.toList()));
        }
        return this;
    }

    @Override
    public Receipt addReceiptElement(ReceiptElement receiptElement) {
        if (receiptElement != null) {
            getEntity().getReceiptElements().add(receiptElement.asEntity());
        }
        return this;
    }

    @Override
    public Receipt clearReceiptElements() {
        getEntity().getReceiptElements().clear();
        return this;
    }

    @Override
    public JWSPayload getDataToBeSigned() {
        return new JWSPayload.Builder()
                .addSuiteId(getSuiteId())
                .addCashBoxName(getCashBox().getName())
                .addReceiptId(getReceiptId())
                .addReceiptDate(getReceiptDate())
                .addTotalPriceTaxNormal(getTotalPriceAfterTaxOf(TaxSetNormal.class))
                .addTotalPriceTaxErmaessigt1(getTotalPriceAfterTaxOf(TaxSetErmaessigt1.class))
                .addTotalPriceTaxErmaessigt2(getTotalPriceAfterTaxOf(TaxSetErmaessigt2.class))
                .addTotalPriceTaxNull(getTotalPriceAfterTaxOf(TaxSetNull.class))
                .addTotalPriceTaxBesonders(getTotalPriceAfterTaxOf(TaxSetBesonders.class))
                .addEncryptedTurnoverValue(getEncryptedTurnoverValue())
                .addSignatureCertificateSerialNumber(getCashBox().getSignatureCertificateSerialNumber())
                .addSignatureValuePreviousReceipt(getSignatureValuePreviousReceipt())
                .build();
    }

    @Override
    public BillBean asBillBean() {
        BillBean bill = new BillBean();
        bill.setCompany(getCompany().asDto());
        bill.setCashBoxID(SimpleValueObject.nullSafe(getCashBox().getName()));
        bill.setReceiptIdentifier(SimpleValueObject.nullSafe(getReceiptId()));
        LocalDateTime receiptDateAndTime = SimpleValueObject.nullSafe(getReceiptDate());
        bill.setReceiptDateAndTime(receiptDateAndTime);
        bill.setReceiptType(SimpleValueObject.nullSafe(getReceiptType().getName()));
        bill.setEncryptedTurnoverValue(SimpleValueObject.nullSafe(getEncryptedTurnoverValue()));
        bill.setSignatureCertificateSerialNumber(SimpleValueObject.nullSafe(getCashBox().getSignatureCertificateSerialNumber()));
        bill.setSignatureValuePreviousReceipt(SimpleValueObject.nullSafe(getSignatureValuePreviousReceipt()));
        bill.setSumTotal(SimpleValueObject.nullSafe(getTotalPrice()));
        bill.setSumTaxSetNormal(getTotalPriceAfterTaxOf(TaxSetNormal.class).get());
        bill.setSumTaxSetErmaessigt1(getTotalPriceAfterTaxOf(TaxSetErmaessigt1.class).get());
        bill.setSumTaxSetErmaessigt2(getTotalPriceAfterTaxOf(TaxSetErmaessigt2.class).get());
        bill.setSumTaxSetNull(getTotalPriceAfterTaxOf(TaxSetNull.class).get());
        bill.setSumTaxSetBesonders(getTotalPriceAfterTaxOf(TaxSetBesonders.class).get());
        for (ReceiptElement receiptElement : getReceiptElements()) {
            bill.getBillTaxSetElements().add(receiptElement.asBillTaxSetElementBean());
        }
        if (getCompactJwsRepresentation() != null) {
            bill.setJwsCompact(getCompactJwsRepresentation().getMachineReadableRepresentation());
        }
        return bill;
    }

    @Override
    public Receipt setCompactJWSRepresentation(CompactJWSRepresentation compactJwsRepresentation) {
        if (compactJwsRepresentation != null) {
            getEntity().setCompactJwsRepresentation(compactJwsRepresentation.getCompactJwsRepresentation());
            getEntity().setMachineReadableRepresentation(compactJwsRepresentation.getMachineReadableRepresentation());
            getEntity().setSignatureDeviceAvailable(compactJwsRepresentation.isSignatureDeviceAvailable());
        }
        return this;
    }

    @Override
    public CompactJWSRepresentation getCompactJwsRepresentation() {
        return CompactJWSRepresentationCore.fromRealCompactJwsRepresentation(getEntity().getCompactJwsRepresentation(), CDI.current().select(Encoding.class).get());
    }

    @Override
    public ReceiptMachineReadableRepresentation getReceiptMachineReadableRepresentation() {
        return new ReceiptMachineReadableRepresentation(getEntity().getMachineReadableRepresentation());
    }

    @Override
    public SignatureDeviceAvailable getSignatureDeviceAvailable() {
        return new SignatureDeviceAvailable(getEntity().getSignatureDeviceAvailable());
    }

    @Override
    public ReceiptBean asDto() {
        return ReceiptMapping.fromCDI().mapEntity(getEntity());
    }

    @Override
    public Receipt persist() {
        if (!getDEP().isPresent() || "".equals(getDEP().get())) {
            setDEP(new DEPString(getSerializer().serialize(asBillBean())));
        }
        return super.persist();
    }

    @Override
    public Receipt persistWithoutCreatingDEP() {
        return super.persist();
    }

    private Serializer getSerializer() {
        return CDI.current().select(Serializer.class, new NonPrettyPrintingGson() {
            @Override
            public Class<? extends Annotation> annotationType() {
                return NonPrettyPrintingGson.class;
            }
        }).get();
    }
}