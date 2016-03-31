package org.regkas.service.core.util;

import org.boatpos.common.repository.api.values.Priority;
import org.junit.Test;
import org.regkas.repository.api.model.Product;
import org.regkas.repository.api.model.ProductGroup;
import org.regkas.repository.api.model.ReceiptElement;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.api.values.TaxPercent;
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
        when(receiptElement.getTotalPrice()).thenReturn(new TotalPrice(new BigDecimal("7.20")));
        when(receiptElement.getProduct()).thenReturn(product);
        when(product.getProductGroup()).thenReturn(productGroup);
        when(productGroup.getTaxSet()).thenReturn(taxSet);
        when(taxSet.getName()).thenReturn(new Name("Satz-Normal"));
        when(taxSet.getTaxPercent()).thenReturn(new TaxPercent(20));
        when(taxSet.getPriority()).thenReturn(new Priority(1));

        assertEquals(new BigDecimal("0.00"), bill.getSumTaxSetNormal());
        assertEquals(new BigDecimal("0.00"), bill.getSumTaxSetErmaessigt1());
        assertEquals(new BigDecimal("0.00"), bill.getSumTaxSetErmaessigt2());
        assertEquals(new BigDecimal("0.00"), bill.getSumTaxSetNull());
        assertEquals(new BigDecimal("0.00"), bill.getSumTaxSetBesonders());
        assertEquals(0, bill.getBillTaxSetElements().size());
        assertEquals(new BigDecimal("0.00"), bill.getSumTotal());

        TaxSet.addToBill(receiptElement, bill);
        assertEquals(new BigDecimal("7.20"), bill.getSumTaxSetNormal());
        assertEquals(1, bill.getBillTaxSetElements().size());
        assertEquals(20, bill.getBillTaxSetElements().get(0).getTaxPercent().intValue());
        assertEquals(1, bill.getBillTaxSetElements().get(0).getPriority().intValue());
        assertEquals(new BigDecimal("7.20"), bill.getBillTaxSetElements().get(0).getPriceAfterTax());
        assertEquals(new BigDecimal("6.00"), bill.getBillTaxSetElements().get(0).getPricePreTax());
        assertEquals(new BigDecimal("1.20"), bill.getBillTaxSetElements().get(0).getPriceTax());
        TaxSet.addToBill(receiptElement, bill);
        assertEquals(new BigDecimal("14.40"), bill.getSumTaxSetNormal());
        assertEquals(1, bill.getBillTaxSetElements().size());
        assertEquals(20, bill.getBillTaxSetElements().get(0).getTaxPercent().intValue());
        assertEquals(1, bill.getBillTaxSetElements().get(0).getPriority().intValue());
        assertEquals(new BigDecimal("14.40"), bill.getBillTaxSetElements().get(0).getPriceAfterTax());
        assertEquals(new BigDecimal("12.00"), bill.getBillTaxSetElements().get(0).getPricePreTax());
        assertEquals(new BigDecimal("2.40"), bill.getBillTaxSetElements().get(0).getPriceTax());
        assertEquals(new BigDecimal("14.40"), bill.getSumTotal());

        when(taxSet.getName()).thenReturn(new Name("Satz-Ermaessigt-1"));
        when(taxSet.getTaxPercent()).thenReturn(new TaxPercent(10));
        when(taxSet.getPriority()).thenReturn(new Priority(2));
        TaxSet.addToBill(receiptElement, bill);
        assertEquals(new BigDecimal("7.20"), bill.getSumTaxSetErmaessigt1());
        assertEquals(2, bill.getBillTaxSetElements().size());
        assertEquals(10, bill.getBillTaxSetElements().get(1).getTaxPercent().intValue());
        assertEquals(2, bill.getBillTaxSetElements().get(1).getPriority().intValue());
        assertEquals(new BigDecimal("7.20"), bill.getBillTaxSetElements().get(1).getPriceAfterTax());
        assertEquals(new BigDecimal("6.55"), bill.getBillTaxSetElements().get(1).getPricePreTax());
        assertEquals(new BigDecimal("0.65"), bill.getBillTaxSetElements().get(1).getPriceTax());
        assertEquals(new BigDecimal("21.60"), bill.getSumTotal());

        when(taxSet.getName()).thenReturn(new Name("Satz-Ermaessigt-2"));
        when(taxSet.getTaxPercent()).thenReturn(new TaxPercent(13));
        when(taxSet.getPriority()).thenReturn(new Priority(3));
        TaxSet.addToBill(receiptElement, bill);
        assertEquals(new BigDecimal("7.20"), bill.getSumTaxSetErmaessigt2());
        assertEquals(3, bill.getBillTaxSetElements().size());
        assertEquals(13, bill.getBillTaxSetElements().get(2).getTaxPercent().intValue());
        assertEquals(3, bill.getBillTaxSetElements().get(2).getPriority().intValue());
        assertEquals(new BigDecimal("7.20"), bill.getBillTaxSetElements().get(2).getPriceAfterTax());
        assertEquals(new BigDecimal("6.37"), bill.getBillTaxSetElements().get(2).getPricePreTax());
        assertEquals(new BigDecimal("0.83"), bill.getBillTaxSetElements().get(2).getPriceTax());
        assertEquals(new BigDecimal("28.80"), bill.getSumTotal());

        when(taxSet.getName()).thenReturn(new Name("Satz-Null"));
        when(taxSet.getTaxPercent()).thenReturn(new TaxPercent(0));
        when(taxSet.getPriority()).thenReturn(new Priority(4));
        TaxSet.addToBill(receiptElement, bill);
        assertEquals(new BigDecimal("7.20"), bill.getSumTaxSetNull());
        assertEquals(4, bill.getBillTaxSetElements().size());
        assertEquals(0, bill.getBillTaxSetElements().get(3).getTaxPercent().intValue());
        assertEquals(4, bill.getBillTaxSetElements().get(3).getPriority().intValue());
        assertEquals(new BigDecimal("7.20"), bill.getBillTaxSetElements().get(3).getPriceAfterTax());
        assertEquals(new BigDecimal("7.20"), bill.getBillTaxSetElements().get(3).getPricePreTax());
        assertEquals(new BigDecimal("0.00"), bill.getBillTaxSetElements().get(3).getPriceTax());
        assertEquals(new BigDecimal("36.00"), bill.getSumTotal());

        when(taxSet.getName()).thenReturn(new Name("Satz-Besonders"));
        when(taxSet.getTaxPercent()).thenReturn(new TaxPercent(19));
        when(taxSet.getPriority()).thenReturn(new Priority(5));
        TaxSet.addToBill(receiptElement, bill);
        assertEquals(new BigDecimal("7.20"), bill.getSumTaxSetBesonders());
        assertEquals(5, bill.getBillTaxSetElements().size());
        assertEquals(19, bill.getBillTaxSetElements().get(4).getTaxPercent().intValue());
        assertEquals(5, bill.getBillTaxSetElements().get(4).getPriority().intValue());
        assertEquals(new BigDecimal("7.20"), bill.getBillTaxSetElements().get(4).getPriceAfterTax());
        assertEquals(new BigDecimal("6.05"), bill.getBillTaxSetElements().get(4).getPricePreTax());
        assertEquals(new BigDecimal("1.15"), bill.getBillTaxSetElements().get(4).getPriceTax());
        assertEquals(new BigDecimal("43.20"), bill.getSumTotal());
    }

    @Test
    public void get() throws Exception {
        assertEquals(TaxSet.Null, TaxSet.get(""));
        assertEquals(TaxSet.Normal, TaxSet.get("Satz-Normal"));
    }
}