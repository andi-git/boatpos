package org.regkas.repository.core.builder;

import org.boatpos.common.model.PaymentMethod;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.repository.api.model.Receipt;
import org.regkas.repository.api.model.ReceiptType;
import org.regkas.repository.api.repository.ReceiptTypeRepository;
import org.regkas.repository.api.values.*;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class ReceiptBuilderCoreTest extends EntityManagerProviderForRegkas {

    @Inject
    private ReceiptProducer receiptProducer;

    @Test
    @Transactional
    public void testBuild() throws Exception {
        assertEquals("receipt-id", receiptProducer.getReceipt().getReceiptId().get());
    }

    @Dependent
    public static class ReceiptProducer {

        @Inject
        private ReceiptTypeRepository receiptTypeRepository;

        public Receipt getReceipt() {
            ReceiptType receiptType = receiptTypeRepository.loadBy(new Name("Standard-Beleg")).get();
            return new ReceiptBuilderCore()
                    .add(new ReceiptId("receipt-id"))
                    .add(new ReceiptDate(LocalDateTime.now()))
                    .add(new EncryptedTurnoverValue("etv"))
                    .add(new SignatureValuePreviousReceipt("svpr"))
                    .add(CompanyBuilderCoreTest.build())
                    .add(UserBuilderCoreTest.build())
                    .add(receiptType)
                    .add(CashBoxBuilderCoreTest.build())
                    .add(PaymentMethod.CASH)
                    .add(ReceiptElementBuilderCoreTest.build())
                    .add(new TotalPrice("0.00"))
                    .build();
        }
    }
}