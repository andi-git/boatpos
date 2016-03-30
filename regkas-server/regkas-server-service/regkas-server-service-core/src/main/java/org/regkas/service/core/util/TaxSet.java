package org.regkas.service.core.util;

import org.regkas.repository.api.model.ReceiptElement;
import org.regkas.service.api.bean.BillBean;

/**
 * Representation of tax-set. <br> <br><b>Warning</b>: must fit with the database-entries!
 */
@SuppressWarnings("unused")
public enum TaxSet {

    Normal("Satz-Normal") {
        @Override
        void addToBillInternal(ReceiptElement receiptElement, BillBean bill) {
            bill.setSumTaxSetNormal(bill.getSumTaxSetNormal().add(receiptElement.getTotalPrice().get()));
        }
    },
    Ermaessigt1("Satz-Ermaessigt-1") {
        @Override
        void addToBillInternal(ReceiptElement receiptElement, BillBean bill) {
            bill.setSumTaxSetErmaessigt1(bill.getSumTaxSetErmaessigt1().add(receiptElement.getTotalPrice().get()));
        }
    },
    Ermaessigt2("Satz-Ermaessigt-2") {
        @Override
        void addToBillInternal(ReceiptElement receiptElement, BillBean bill) {
            bill.setSumTaxSetErmaessigt2(bill.getSumTaxSetErmaessigt2().add(receiptElement.getTotalPrice().get()));
        }
    },
    Null("Satz-Null") {
        @Override
        void addToBillInternal(ReceiptElement receiptElement, BillBean bill) {
            bill.setSumTaxSetNull(bill.getSumTaxSetNull().add(receiptElement.getTotalPrice().get()));
        }
    },
    Besonders("Satz-Besonders") {
        @Override
        void addToBillInternal(ReceiptElement receiptElement, BillBean bill) {
            bill.setSumTaxSetBesonders(bill.getSumTaxSetBesonders().add(receiptElement.getTotalPrice().get()));
        }
    };

    private String taxName;

    TaxSet(String taxName) {
        this.taxName = taxName;
    }

    public String getTaxName() {
        return taxName;
    }

    public static void addToBill(ReceiptElement receiptElement, BillBean bill) {
        get(receiptElement.getProduct().getProductGroup().getTaxSet().getName().get()).addToBillInternal(receiptElement, bill);
    }

    abstract void addToBillInternal(ReceiptElement receiptElement, BillBean bill);

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
}