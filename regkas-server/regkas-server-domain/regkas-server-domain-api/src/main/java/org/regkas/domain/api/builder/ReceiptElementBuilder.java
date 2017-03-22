package org.regkas.domain.api.builder;

import org.boatpos.common.domain.api.builder.DomainModelBuilder;
import org.regkas.domain.api.values.Amount;
import org.regkas.domain.api.values.TotalPrice;
import org.regkas.model.ReceiptElementEntity;
import org.regkas.domain.api.model.Product;
import org.regkas.domain.api.model.ReceiptElement;

/**
 * Builder for {@link ReceiptElement}.
 */
public interface ReceiptElementBuilder extends DomainModelBuilder<ReceiptElementBuilder, ReceiptElement, ReceiptElementEntity> {

    ReceiptElementBuilder add(Product product);

    ReceiptElementBuilder add(TotalPrice totalPrice);

    ReceiptElementBuilder add(Amount amount);
}
