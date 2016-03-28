package org.regkas.repository.core.builder;

import org.junit.Test;
import org.regkas.repository.api.model.Receipt;
import org.regkas.repository.api.model.ReceiptElement;
import org.regkas.repository.api.values.*;
import org.regkas.repository.core.model.CashBoxCoreTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class ReceiptBuilderCoreTest {

    @Test
    public void testBuild() throws Exception {
        assertEquals("receipt-id", build().getReceiptId().get());
    }

    public static Receipt build() {
        return new ReceiptBuilderCore()
                .add(new ReceiptId("receipt-id"))
                .add(new ReceiptDate(LocalDateTime.now()))
                .add(new EncryptedTurnoverValue("etv"))
                .add(new SignatureValuePreviousReceipt("svpr"))
                .add(CompanyBuilderCoreTest.build())
                .add(UserBuilderCoreTest.build())
                .add(ReceiptTypeBuilderCoreTest.build())
                .add(CashBoxBuilderCoreTest.build())
                .add(ReceiptElementBuilderCoreTest.build())
                .build();
    }
}