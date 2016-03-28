package org.regkas.repository.core.model;

import com.google.common.collect.Sets;
import org.junit.Test;
import org.regkas.repository.api.model.Receipt;
import org.regkas.repository.api.model.ReceiptElement;
import org.regkas.repository.core.builder.ReceiptBuilderCoreTest;
import org.regkas.repository.core.builder.ReceiptElementBuilderCoreTest;
import org.regkas.repository.core.builder.UserBuilderCoreTest;

import java.math.BigDecimal;

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

        receipt.clearReceiptElements();
        receipt.addReceiptElement(ReceiptElementBuilderCoreTest.build());
        assertEquals(1, receipt.getReceiptElements().size());
        receipt.clearReceiptElements();
        receipt.addReceiptElements(Sets.newHashSet(ReceiptElementBuilderCoreTest.build()));
        assertEquals(1, receipt.getReceiptElements().size());
    }
}