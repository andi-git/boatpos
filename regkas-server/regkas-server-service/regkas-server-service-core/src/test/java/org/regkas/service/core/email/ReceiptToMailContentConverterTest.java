package org.regkas.service.core.email;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.domain.api.model.Receipt;
import org.regkas.domain.api.repository.CashBoxRepository;
import org.regkas.domain.api.repository.ReceiptRepository;
import org.regkas.domain.api.values.Name;
import org.regkas.domain.api.values.ReceiptId;
import org.regkas.service.api.bean.BillBean;
import org.regkas.service.core.DateTimeHelperMock;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.inject.Inject;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SuppressWarnings("ConstantConditions")
@RunWith(Arquillian.class)
public class ReceiptToMailContentConverterTest extends EntityManagerProviderForRegkas{

    @Inject
    private ReceiptToMailContentConverter receiptToMailContentConverter;

    @Inject
    private DateTimeHelperMock dateTimeHelper;

    @Inject
    private CashBoxRepository cashBoxRepository;

    @Inject
    private ReceiptRepository receiptRepository;

    @Test
    @Transactional
    public void testConvertToMailContent() throws Exception {
        Receipt receipt = receiptRepository.loadBy(new ReceiptId("2015-0000001"), cashBoxRepository.loadBy(new Name("RegKas1")).get()).get();
        String mailContent = receiptToMailContentConverter.convertToMailContent(receipt);
        System.out.println(mailContent);
        assertTrue(mailContent.contains("cash-box: RegKas1"));
        assertTrue(mailContent.contains("receipt-id: 2015-0000001"));
        assertTrue(mailContent.contains("receipt-time: 2015-07-01T12:00:13"));
        assertTrue(mailContent.contains("receipt-type: Standard-Beleg"));
    }

}
