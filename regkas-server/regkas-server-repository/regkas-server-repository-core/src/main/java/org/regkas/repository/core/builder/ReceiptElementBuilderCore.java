package org.regkas.repository.core.builder;

import org.boatpos.common.repository.core.builder.DomainModelBuilderCore;
import org.regkas.model.ReceiptElementEntity;
import org.regkas.repository.api.builder.ReceiptElementBuilder;
import org.regkas.repository.api.model.Product;
import org.regkas.repository.api.model.ReceiptElement;
import org.regkas.repository.api.values.Amount;
import org.regkas.repository.api.values.TotalPrice;
import org.regkas.repository.core.model.ReceiptElementCore;

import javax.enterprise.context.Dependent;

@Dependent
public class ReceiptElementBuilderCore
        extends DomainModelBuilderCore<ReceiptElementBuilder, ReceiptElement, ReceiptElementCore, ReceiptElementEntity>
        implements ReceiptElementBuilder {

    private Product product;

    private Amount amount;

    private TotalPrice totalPrice;

    @Override
    public ReceiptElement build() {
        return new ReceiptElementCore(id, version, product, amount, totalPrice);
    }

    @Override
    public ReceiptElementBuilder add(Product product) {
        this.product = product;
        return this;
    }

    @Override
    public ReceiptElementBuilder add(Amount amount) {
        this.amount = amount;
        return this;
    }

    @Override
    public ReceiptElementBuilder add(TotalPrice totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }
}
