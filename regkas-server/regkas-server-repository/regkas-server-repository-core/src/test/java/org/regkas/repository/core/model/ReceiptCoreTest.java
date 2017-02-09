package org.regkas.repository.core.model;

import com.google.common.collect.Lists;
import org.boatpos.common.model.PaymentMethod;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.repository.api.model.Receipt;
import org.regkas.repository.core.builder.ReceiptBuilderCoreTest;
import org.regkas.repository.core.builder.ReceiptElementBuilderCoreTest;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(Arquillian.class)
public class ReceiptCoreTest extends EntityManagerProviderForRegkas {

    @Inject
    private ReceiptBuilderCoreTest.ReceiptProducer receiptProducer;

    @Test
    @Transactional
    public void testGetter() {
        Receipt receipt = receiptProducer.getReceipt();
        assertEquals("receipt-id", receipt.getReceiptId().get());
        assertNotNull(receipt.getReceiptDate().get());
        assertEquals("etv", receipt.getEncryptedTurnoverValue().get());
        assertEquals("svpr", receipt.getSignatureValuePreviousReceipt().get());
        assertEquals("Standard-Beleg", receipt.getReceiptType().getName().get());
        assertEquals("company-name", receipt.getCompany().getName().get());
        assertEquals("user-name", receipt.getUser().getName().get());
        assertEquals("cashbox-id", receipt.getCashBox().getName().get());
        assertEquals(PaymentMethod.CASH, receipt.getPaymentMethod());

        receipt.clearReceiptElements();
        receipt.addReceiptElement(ReceiptElementBuilderCoreTest.build());
        assertEquals(1, receipt.getReceiptElements().size());
        receipt.clearReceiptElements();
        receipt.addReceiptElements(Lists.newArrayList(ReceiptElementBuilderCoreTest.build()));
        assertEquals(1, receipt.getReceiptElements().size());
    }
}