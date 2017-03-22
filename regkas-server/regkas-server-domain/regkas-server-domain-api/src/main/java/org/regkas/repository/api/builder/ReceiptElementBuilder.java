package org.regkas.repository.api.builder;

import org.boatpos.common.repository.api.builder.DomainModelBuilder;
import org.regkas.model.ReceiptElementEntity;
import org.regkas.repository.api.model.Product;
import org.regkas.repository.api.model.ReceiptElement;
import org.regkas.repository.api.values.Amount;
import org.regkas.repository.api.values.TotalPrice;

/**
 * Builder for {@link ReceiptElement}.
 */
public interface ReceiptElementBuilder extends DomainModelBuilder<ReceiptElementBuilder, ReceiptElement, ReceiptElementEntity> {

    ReceiptElementBuilder add(Product product);

    ReceiptElementBuilder add(TotalPrice totalPrice);

    ReceiptElementBuilder add(Amount amount);
}
