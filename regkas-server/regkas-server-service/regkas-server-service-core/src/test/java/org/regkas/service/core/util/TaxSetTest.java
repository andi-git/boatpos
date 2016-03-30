package org.regkas.service.core.util;

import org.junit.Test;
import org.regkas.repository.api.model.Product;
import org.regkas.repository.api.model.ProductGroup;
import org.regkas.repository.api.model.ReceiptElement;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.api.values.TotalPrice;
import org.regkas.service.api.bean.BillBean;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TaxSetTest {

    @Test
    public void addToBill() throws Exception {
        BillBean bill = new BillBean();
        ReceiptElement receiptElement = mock(ReceiptElement.class);
        Product product = mock(Product.class);
        ProductGroup productGroup = mock(ProductGroup.class);
        org.regkas.repository.api.model.TaxSet taxSet = mock(org.regkas.repository.api.model.TaxSet.class);
        when(receiptElement.getTotalPrice()).thenReturn(new TotalPrice(BigDecimal.ONE));
        when(receiptElement.getProduct()).thenReturn(product);
        when(product.getProductGroup()).thenReturn(productGroup);
        when(productGroup.getTaxSet()).thenReturn(taxSet);
        when(taxSet.getName()).thenReturn(new Name("Satz-Normal"));

        assertEquals(BigDecimal.ZERO, bill.getSumTaxSetNormal());
        assertEquals(BigDecimal.ZERO, bill.getSumTaxSetErmaessigt1());
        assertEquals(BigDecimal.ZERO, bill.getSumTaxSetErmaessigt2());
        assertEquals(BigDecimal.ZERO, bill.getSumTaxSetNull());
        assertEquals(BigDecimal.ZERO, bill.getSumTaxSetBesonders());

        TaxSet.addToBill(receiptElement, bill);
        assertEquals(BigDecimal.ONE, bill.getSumTaxSetNormal());

        when(taxSet.getName()).thenReturn(new Name("Satz-Ermaessigt-1"));
        TaxSet.addToBill(receiptElement, bill);
        assertEquals(BigDecimal.ONE, bill.getSumTaxSetErmaessigt1());

        when(taxSet.getName()).thenReturn(new Name("Satz-Ermaessigt-2"));
        TaxSet.addToBill(receiptElement, bill);
        assertEquals(BigDecimal.ONE, bill.getSumTaxSetErmaessigt2());

        when(taxSet.getName()).thenReturn(new Name("Satz-Null"));
        TaxSet.addToBill(receiptElement, bill);
        assertEquals(BigDecimal.ONE, bill.getSumTaxSetNull());

        when(taxSet.getName()).thenReturn(new Name("Satz-Besonders"));
        TaxSet.addToBill(receiptElement, bill);
        assertEquals(BigDecimal.ONE, bill.getSumTaxSetBesonders());
    }

    @Test
    public void get() throws Exception {
        assertEquals(TaxSet.Null, TaxSet.get(""));
        assertEquals(TaxSet.Normal, TaxSet.get("Satz-Normal"));
    }
}