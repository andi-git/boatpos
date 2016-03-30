package org.regkas.repository.core.mapping;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.repository.api.model.Receipt;
import org.regkas.repository.api.model.ReceiptElement;
import org.regkas.repository.api.repository.ReceiptRepository;
import org.regkas.repository.core.DateTimeHelperMock;
import org.regkas.service.api.bean.Period;
import org.regkas.service.api.bean.ReceiptBean;
import org.regkas.service.api.bean.ReceiptElementBean;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.inject.Inject;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(Arquillian.class)
public class ReceiptMappingTest extends EntityManagerProviderForRegkas {

    @Inject
    private ReceiptRepository receiptRepository;

    @Inject
    private ReceiptMapping receiptMapping;

    @Inject
    private DateTimeHelperMock dateTimeHelper;

    @Test
    @Transactional
    public void testFromCDI() {
        assertNotNull(ReceiptMapping.fromCDI());
    }

    @Test
    @Transactional
    public void testMappingEntityToDto() {
        Receipt receipt = receiptRepository.loadBy(Period.day(dateTimeHelper.currentTime())).get(0);
        assertEquals("2015-0000001", receipt.getReceiptId().get());
        assertEquals(LocalDateTime.of(2015, 7, 1, 12, 0, 13), receipt.getReceiptDate().get());
        assertEquals("RegKas1", receipt.getCashBox().getName().get());
        assertEquals("company", receipt.getCompany().getName().get());
        assertEquals("Maria Musterfrau", receipt.getUser().getName().get());
        assertEquals("Standard-Beleg", receipt.getReceiptType().getName().get());
        assertEquals(2, receipt.getReceiptElements().size());
        for (ReceiptElement receiptElement : receipt.getReceiptElements()) {
            assertNotNull(receiptElement.getAmount());
            assertNotNull(receiptElement.getAmount().get());
            assertNotNull(receiptElement.getProduct());
            assertNotNull(receiptElement.getTotalPrice());
            assertNotNull(receiptElement.getTotalPrice().get());
        }
        ReceiptBean receiptBean = receiptMapping.mapEntity(receipt.asEntity());
        assertEquals("2015-0000001", receiptBean.getReceiptId());
        assertEquals(LocalDateTime.of(2015, 7, 1, 12, 0, 13), receiptBean.getReceiptDate());
        assertEquals("RegKas1", receiptBean.getCashbox());
        assertEquals("company", receiptBean.getCompany().getName());
        assertEquals("Standard-Beleg", receiptBean.getReceiptType());
        assertEquals(2, receiptBean.getReceiptElements().size());
        for (ReceiptElementBean receiptElementBean : receiptBean.getReceiptElements()) {
            assertNotNull(receiptElementBean.getAmount());
            assertNotNull(receiptElementBean.getProduct());
            assertNotNull(receiptElementBean.getTotalPrice());
        }
    }
}