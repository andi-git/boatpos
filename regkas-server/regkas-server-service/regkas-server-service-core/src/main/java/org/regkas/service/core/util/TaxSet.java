package org.regkas.service.core.util;

import org.regkas.repository.api.model.ReceiptElement;
import org.regkas.service.api.bean.BillBean;
import org.regkas.service.api.bean.BillTaxSetElementBean;

import java.math.BigDecimal;

/**
 * Representation of tax-set. <br> <br><b>Warning</b>: must fit with the database-entries!
 */
public enum TaxSet {

    Normal("Satz-Normal", BillBean::setSumTaxSetNormal),
    Ermaessigt1("Satz-Ermaessigt-1", BillBean::setSumTaxSetErmaessigt1),
    Ermaessigt2("Satz-Ermaessigt-2", BillBean::setSumTaxSetErmaessigt2),
    Null("Satz-Null", BillBean::setSumTaxSetNull),
    Besonders("Satz-Besonders", BillBean::setSumTaxSetBesonders);

    private final String taxName;

    private final SumTaxSetter sumTaxSetter;

    private final TotalPriceCalculation totalPriceCalculation = ((receiptElement, taxSetElement) -> taxSetElement.getPriceAfterTax().add(receiptElement.getTotalPrice().get()));

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

    TaxSet(String taxName, SumTaxSetter sumTaxSetter) {
        this.taxName = taxName;
        this.sumTaxSetter = sumTaxSetter;
    }

    public String getTaxName() {
        return taxName;
    }

    public TotalPriceCalculation getTotalPriceCalculation() {
        return totalPriceCalculation;
    }

    public SumTaxSetter getSumTaxSetter() {
        return sumTaxSetter;
    }

    public BillPriceSetter getBillPriceSetter() {
        return billPriceSetter;
    }

    public static void addToBill(ReceiptElement receiptElement, BillBean bill) {
        get(receiptElement.getProduct().getProductGroup().getTaxSet().getName().get()).addToBillInternal(receiptElement, bill);
    }

    private void addToBillInternal(ReceiptElement receiptElement, BillBean bill) {
        boolean exists = false;
        // if there is already an existing tax-set-element
        for (BillTaxSetElementBean taxSetElement : bill.getBillTaxSetElements()) {
            if (getTaxName().equals(taxSetElement.getName())) {
                // calculate total price
                BigDecimal newTotalPrice = getTotalPriceCalculation().calculate(receiptElement, taxSetElement);
                // update sum
                getSumTaxSetter().set(bill, newTotalPrice);
                // update tax-set-element
                getBillPriceSetter().set(taxSetElement, receiptElement.getProduct().getProductGroup().getTaxSet().getTaxPercent().get(), newTotalPrice);
                // update total-price
                bill.setSumTotal(bill.getSumTotal().add(receiptElement.getTotalPrice().get()));
                exists = true;
                break;
            }
        }
        // if there is not an existing tax-set-element
        if (!exists) {
            // set the sum
            getSumTaxSetter().set(bill, receiptElement.getTotalPrice().get());
            // set the concrete tax-set-element
            org.regkas.repository.api.model.TaxSet taxSet = receiptElement.getProduct().getProductGroup().getTaxSet();
            BillTaxSetElementBean billTaxSetElementBean = new BillTaxSetElementBean();
            billTaxSetElementBean.setName(taxSet.getName().get());
            billTaxSetElementBean.setTaxPercent(taxSet.getTaxPercent().get());
            billTaxSetElementBean.setPriority(taxSet.getPriority().get());
            getBillPriceSetter().set(billTaxSetElementBean, receiptElement.getProduct().getProductGroup().getTaxSet().getTaxPercent().get(), receiptElement.getTotalPrice().get());
            bill.getBillTaxSetElements().add(billTaxSetElementBean);
            // sort the list
            bill.getBillTaxSetElements().sort((b1, b2) -> b1.getPriority().compareTo(b2.getPriority()));
            // update total-price
            bill.setSumTotal(bill.getSumTotal().add(receiptElement.getTotalPrice().get()));
        }
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
    interface TotalPriceCalculation {

        BigDecimal calculate(ReceiptElement receiptElement, BillTaxSetElementBean taxSetElement);
    }

    @FunctionalInterface
    interface SumTaxSetter {

        void set(BillBean bill, BigDecimal totalPrice);
    }

    @FunctionalInterface
    interface BillPriceSetter {

        void set(BillTaxSetElementBean billTaxSetElementBean, Integer taxPercent, BigDecimal totalPrice);
    }
}