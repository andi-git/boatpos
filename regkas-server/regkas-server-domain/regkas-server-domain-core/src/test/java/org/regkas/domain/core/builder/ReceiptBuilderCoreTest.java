package org.regkas.domain.core.builder;

import org.boatpos.common.model.PaymentMethod;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.domain.api.model.Receipt;
import org.regkas.domain.api.repository.ReceiptTypeRepository;
import org.regkas.domain.api.values.EncryptedTurnoverValue;
import org.regkas.domain.api.values.Name;
import org.regkas.domain.api.values.ReceiptDate;
import org.regkas.domain.api.values.ReceiptId;
import org.regkas.domain.api.values.SignatureValuePreviousReceipt;
import org.regkas.domain.api.values.SuiteId;
import org.regkas.domain.api.values.TotalPrice;
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

        @Inject
        private ReceiptElementBuilderCoreTest.ReceiptElementProducer receiptElementProducer;

        public Receipt getReceipt() {
            return new ReceiptBuilderCore()
                    .add(new ReceiptId("receipt-id"))
                    .add(new ReceiptDate(LocalDateTime.now()))
                    .add(new EncryptedTurnoverValue("etv"))
                    .add(new SignatureValuePreviousReceipt("svpr"))
                    .add(CompanyBuilderCoreTest.build())
                    .add(UserBuilderCoreTest.build())
                    .add(receiptTypeRepository.loadBy(new Name("Standard-Beleg")).get())
                    .add(CashBoxBuilderCoreTest.build())
                    .add(PaymentMethod.CASH)
                    .add(receiptElementProducer.getReceiptElement())
                    .add(new TotalPrice("0.00"))
                    .add(new SuiteId("R1-AT0"))
                    .build();
        }
    }
}