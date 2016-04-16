package org.regkas.repository.core.model;

import com.google.common.collect.Lists;
import org.boatpos.common.model.PaymentMethod;
import org.junit.Test;
import org.regkas.model.TimeType;
import org.regkas.repository.api.model.Receipt;
import org.regkas.repository.core.builder.ReceiptBuilderCoreTest;
import org.regkas.repository.core.builder.ReceiptElementBuilderCoreTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ReceiptCoreTest {

    @Test
    public void testGetter() {
        Receipt receipt = ReceiptBuilderCoreTest.build();
        assertEquals("receipt-id", receipt.getReceiptId().get());
        assertNotNull(receipt.getReceiptDate().get());
        assertEquals("etv", receipt.getEncryptedTurnoverValue().get());
        assertEquals("svpr", receipt.getSignatureValuePreviousReceipt().get());
        assertEquals("receipttype-name", receipt.getReceiptType().getName().get());
        assertEquals("company-name", receipt.getCompany().getName().get());
        assertEquals("user-name", receipt.getUser().getName().get());
        assertEquals("cashbox-id", receipt.getCashBox().getName().get());
        assertEquals(PaymentMethod.CASH, receipt.getPaymentMethod());
        assertEquals(TimeType.Current, receipt.getTimeType());

        receipt.clearReceiptElements();
        receipt.addReceiptElement(ReceiptElementBuilderCoreTest.build());
        assertEquals(1, receipt.getReceiptElements().size());
        receipt.clearReceiptElements();
        receipt.addReceiptElements(Lists.newArrayList(ReceiptElementBuilderCoreTest.build()));
        assertEquals(1, receipt.getReceiptElements().size());
    }
}