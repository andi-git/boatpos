package org.regkas.repository.core.repository;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.repository.api.model.CashBox;
import org.regkas.repository.api.model.Receipt;
import org.regkas.repository.api.repository.CashBoxRepository;
import org.regkas.repository.api.repository.ReceiptRepository;
import org.regkas.repository.api.values.DEPString;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.api.values.ReceiptId;
import org.regkas.repository.core.DateTimeHelperMock;
import org.regkas.service.api.bean.Period;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class ReceiptRepositoryCoreTest extends EntityManagerProviderForRegkas {

    @Inject
    private ReceiptRepository receiptRepository;

    @Inject
    private CashBoxRepository cashBoxRepository;

    @Inject
    private DateTimeHelperMock dateTimeHelper;

    @Test
    @Transactional
    public void testLoadByPeriod() {
        CashBox cashBox = cashBoxRepository.loadBy(new Name("RegKas1")).get();
        assertEquals(2, receiptRepository.loadBy(Period.day(dateTimeHelper.currentTime()), cashBox).size());
        assertEquals(2, receiptRepository.loadBy(Period.month(dateTimeHelper.currentTime()), cashBox).size());
        assertEquals(0, receiptRepository.loadBy(Period.day(LocalDateTime.of(1970, 1, 1, 0, 0)), cashBox).size());
    }

    @Test
    @Transactional
    public void testLoadLastReceipt() {
        CashBox cashBox = cashBoxRepository.loadBy(new Name("RegKas1")).get();
        assertEquals("2015-0000002", receiptRepository.loadLastReceipt(cashBox).get().getReceiptId().get());
    }

    @Test
    @Transactional
    public void tesLoadByReceiptId() {
        CashBox cashBox = cashBoxRepository.loadBy(new Name("RegKas1")).get();
        assertEquals("2015-0000002", receiptRepository.loadBy(new ReceiptId("2015-0000002"), cashBox).get().getReceiptId().get());
    }

    @Test
    @Transactional
    public void tesLoadDEPFor() {
        CashBox cashBox = cashBoxRepository.loadBy(new Name("RegKas1")).get();
        assertEquals(2, receiptRepository.loadDEPFor(Period.day(dateTimeHelper.currentTime()), cashBox).size());
    }

    @Test
    @Transactional
    public void testLoadAllWithoutDEP() {
        assertEquals(0, receiptRepository.loadAllWithoutDEP().size());

        CashBox cashBox = cashBoxRepository.loadBy(new Name("RegKas1")).get();
        Optional<Receipt> receipt = receiptRepository.loadBy(new ReceiptId("2015-0000002"), cashBox);
        receipt.get().setDEP(new DEPString("")).persist();
        assertEquals(1, receiptRepository.loadAllWithoutDEP().size());

        receipt.get().setDEP(null).persist();
        assertEquals(1, receiptRepository.loadAllWithoutDEP().size());
    }
}
