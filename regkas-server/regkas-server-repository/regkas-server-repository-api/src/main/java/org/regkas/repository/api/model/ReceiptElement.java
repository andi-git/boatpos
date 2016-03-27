package org.regkas.repository.api.model;

import org.boatpos.common.repository.api.model.DomainModel;
import org.boatpos.common.repository.api.model.DomainModelWithDto;
import org.regkas.model.ReceiptElementEntity;
import org.regkas.repository.api.values.Amount;
import org.regkas.repository.api.values.Price;
import org.regkas.repository.api.values.TotalPrice;
import org.regkas.service.api.bean.ReceiptElementBean;

/**
 * The domain model for an element of a receipt.
 */
public interface ReceiptElement extends DomainModelWithDto<ReceiptElement, ReceiptElementEntity, ReceiptElementBean> {

    Product getProduct();

    ReceiptElement setProduct(Product product);

    TotalPrice getTotalPrice();

    ReceiptElement setTotalPrice(TotalPrice price);

    Amount getAmount();

    ReceiptElement setAmount(Amount amount);
}
