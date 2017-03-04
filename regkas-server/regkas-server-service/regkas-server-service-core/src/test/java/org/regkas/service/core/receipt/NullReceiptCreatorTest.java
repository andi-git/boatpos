package org.regkas.service.core.receipt;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.service.api.bean.SaleBean;

import javax.inject.Inject;

import static org.junit.Assert.*;

@RunWith(Arquillian.class)
public class NullReceiptCreatorTest {

    @Inject
    private NullReceiptCreator nullReceiptCreator;

    @Test
    public void testCreateNullSale() {
        assertEquals("Null-Beleg", nullReceiptCreator.createNullSale().getReceiptType());
    }
}