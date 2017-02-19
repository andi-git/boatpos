package org.regkas.repository.core.model;

import org.boatpos.common.repository.api.values.DomainId;
import org.boatpos.common.repository.api.values.SimpleValueObject;
import org.boatpos.common.repository.api.values.Version;
import org.boatpos.common.repository.core.model.DomainModelCore;
import org.regkas.model.ReceiptElementEntity;
import org.regkas.repository.api.model.Product;
import org.regkas.repository.api.model.ReceiptElement;
import org.regkas.repository.api.values.Amount;
import org.regkas.repository.api.values.TotalPrice;
import org.regkas.repository.core.mapping.ReceiptElementMapping;
import org.regkas.service.api.bean.BillTaxSetElementBean;
import org.regkas.service.api.bean.ReceiptElementBean;

import static com.google.common.base.Preconditions.checkNotNull;

public class ReceiptElementCore extends DomainModelCore<ReceiptElement, ReceiptElementEntity> implements ReceiptElement {

    public ReceiptElementCore(DomainId id,
                              Version version,
                              Product product,
                              Amount amount,
                              TotalPrice totalPrice) {
        super(id, version);
        checkNotNull(product, "'product' must not be null");
        checkNotNull(amount, "'amount' must not be null");
        checkNotNull(totalPrice, "'totalPrice' must not be null");
        setProduct(product);
        setAmount(amount);
        setTotalPrice(totalPrice);
    }

    public ReceiptElementCore(ReceiptElementEntity receiptElement) {
        super(receiptElement);
    }

    @Override
    public Product getProduct() {
        return new ProductCore(getEntity().getProduct());
    }

    @Override
    public ReceiptElement setProduct(Product product) {
        if (product != null) getEntity().setProduct(product.asEntity());
        return this;
    }

    @Override
    public Amount getAmount() {
        return new Amount(getEntity().getAmount());
    }

    @Override
    public ReceiptElement setAmount(Amount amount) {
        getEntity().setAmount(SimpleValueObject.nullSafe(amount));
        return this;
    }

    @Override
    public BillTaxSetElementBean asBillTaxSetElementBean() {
        BillTaxSetElementBean taxSetElement = new BillTaxSetElementBean();
        taxSetElement.setName(getProduct().isGeneric().get() ? getProduct().getProductGroup().getName().get() : getProduct().getName().get());
        taxSetElement.setAmount(getAmount().get());
        taxSetElement.setTaxPercent(getProduct().getProductGroup().getTaxSet().getTaxPercent().get());
        taxSetElement.setPriceAfterTax(getTotalPrice().get());
        taxSetElement.setPriceTax(getTotalPriceTax().get());
        taxSetElement.setPricePreTax(getTotalPriceBeforeTax().get());
        return taxSetElement;
    }

    @Override
    public TotalPrice getTotalPrice() {
        return new TotalPrice(getEntity().getTotalPrice());
    }

    @Override
    public TotalPrice getTotalPriceBeforeTax() {
        return getProduct().getProductGroup().getTaxSet().getPriceWithoutTaxOf(getTotalPrice());
    }

    @Override
    public TotalPrice getTotalPriceTax() {
        return getProduct().getProductGroup().getTaxSet().getTaxOf(getTotalPrice());
    }

    @Override
    public ReceiptElement setTotalPrice(TotalPrice totalPrice) {
        getEntity().setTotalPrice(SimpleValueObject.nullSafe(totalPrice));
        return this;
    }

    @Override
    public ReceiptElementBean asDto() {
        return ReceiptElementMapping.fromCDI().mapEntity(getEntity());
    }
}