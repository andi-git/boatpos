package org.regkas.domain.api.model;

import org.boatpos.common.domain.api.model.DomainModelWithDto;
import org.regkas.domain.api.values.Amount;
import org.regkas.domain.api.values.TotalPrice;
import org.regkas.model.ReceiptElementEntity;
import org.regkas.service.api.bean.BillTaxSetElementBean;
import org.regkas.service.api.bean.ReceiptElementBean;

/**
 * The domain model for an element of a receipt.
 */
public interface ReceiptElement extends DomainModelWithDto<ReceiptElement, ReceiptElementEntity, ReceiptElementBean> {

    Product getProduct();

    ReceiptElement setProduct(Product product);

    TotalPrice getTotalPrice();

    TotalPrice getTotalPriceBeforeTax();

    TotalPrice getTotalPriceTax();

    ReceiptElement setTotalPrice(TotalPrice price);

    Amount getAmount();

    ReceiptElement setAmount(Amount amount);

    BillTaxSetElementBean asBillTaxSetElementBean();
}
