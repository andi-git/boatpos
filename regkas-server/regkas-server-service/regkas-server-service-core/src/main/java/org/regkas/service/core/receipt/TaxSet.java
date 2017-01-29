package org.regkas.service.core.receipt;

import org.regkas.repository.api.model.ReceiptElement;
import org.regkas.service.api.bean.BillBean;
import org.regkas.service.api.bean.BillTaxSetElementBean;

import java.math.BigDecimal;

/**
 * Representation of tax-set. <br> <br><b>Warning</b>: must fit with the database-entries!
 */
public enum TaxSet {

    Normal("Satz-Normal", BillBean::setSumTaxSetNormal, BillBean::getSumTaxSetNormal),
    Ermaessigt1("Satz-Ermaessigt-1", BillBean::setSumTaxSetErmaessigt1, BillBean::getSumTaxSetErmaessigt1),
    Ermaessigt2("Satz-Ermaessigt-2", BillBean::setSumTaxSetErmaessigt2, BillBean::getSumTaxSetErmaessigt2),
    Null("Satz-Null", BillBean::setSumTaxSetNull, BillBean::getSumTaxSetNull),
    Besonders("Satz-Besonders", BillBean::setSumTaxSetBesonders, BillBean::getSumTaxSetBesonders);

    private final String taxName;

    private final SumTaxSetter sumTaxSetter;

    private final SumTaxGetter sumTaxGetter;

    private final BillPriceSetter billPriceSetter = (billTaxSetElementBean, taxPercent, totalPrice) -> {
        billTaxSetElementBean.setPriceAfterTax(totalPrice);
        if (taxPercent > 0) {
            BigDecimal divisor = new BigDecimal("1.00").add(new BigDecimal(taxPercent + ".00").divide(new BigDecimal("100.00"), 2, BigDecimal.ROUND_HALF_UP));
            billTaxSetElementBean.setPricePreTax(totalPrice.divide(divisor, 2, BigDecimal.ROUND_HALF_UP));
            billTaxSetElementBean.setPriceTax(billTaxSetElementBean.getPriceAfterTax().subtract(billTaxSetElementBean.getPricePreTax()));
        } else {
            billTaxSetElementBean.setPricePreTax(totalPrice);
            billTaxSetElementBean.setPriceTax(new BigDecimal("0.00"));
        }
    };

    TaxSet(String taxName, SumTaxSetter sumTaxSetter, SumTaxGetter sumTaxGetter) {
        this.taxName = taxName;
        this.sumTaxSetter = sumTaxSetter;
        this.sumTaxGetter = sumTaxGetter;
    }

    public String getTaxName() {
        return taxName;
    }

    public SumTaxSetter sumTaxSetter() {
        return sumTaxSetter;
    }

    public SumTaxGetter sumTaxGetter() {
        return sumTaxGetter;
    }

    public BillPriceSetter getBillPriceSetter() {
        return billPriceSetter;
    }

    public static void addToBill(ReceiptElement receiptElement, BillBean bill) {
        get(receiptElement.getProduct().getProductGroup().getTaxSet().getName().get()).addToBillInternal(receiptElement, bill);
    }

    private void addToBillInternal(ReceiptElement receiptElement, BillBean bill) {
        // add relement to bill
        bill.getBillTaxSetElements().add(convertToTaxSetElement(receiptElement));
        // update tax sum
        sumTaxSetter().set(bill, sumTaxGetter().get(bill).add(receiptElement.getTotalPrice().get()));
        // update total sum
        bill.setSumTotal(bill.getSumTotal().add(receiptElement.getTotalPrice().get()));
    }

    private BillTaxSetElementBean convertToTaxSetElement(ReceiptElement receiptElement) {
        BillTaxSetElementBean taxSetElement = new BillTaxSetElementBean();
        taxSetElement.setName(receiptElement.getProduct().isGeneric().get() ?
                receiptElement.getProduct().getProductGroup().getName().get() :
                receiptElement.getProduct().getName().get());
        taxSetElement.setTaxPercent(receiptElement.getProduct().getProductGroup().getTaxSet().getTaxPercent().get());
        taxSetElement.setAmount(receiptElement.getAmount().get());
        getBillPriceSetter().set(taxSetElement, receiptElement.getProduct().getProductGroup().getTaxSet().getTaxPercent().get(), receiptElement.getTotalPrice().get());
        return taxSetElement;
    }

    public static TaxSet get(String taxName) {
        TaxSet result = TaxSet.Null;
        for (TaxSet taxSet : values()) {
            if (taxSet.getTaxName().equals(taxName)) {
                result = taxSet;
                break;
            }
        }
        return result;
    }

    @FunctionalInterface
    interface SumTaxSetter {

        void set(BillBean bill, BigDecimal totalPrice);
    }

    @FunctionalInterface
    interface SumTaxGetter {

        BigDecimal get(BillBean bill);
    }

    @FunctionalInterface
    interface BillPriceSetter {

        void set(BillTaxSetElementBean billTaxSetElementBean, Integer taxPercent, BigDecimal totalPrice);
    }
}