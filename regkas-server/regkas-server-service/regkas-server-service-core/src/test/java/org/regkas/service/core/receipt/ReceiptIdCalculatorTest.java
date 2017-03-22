package org.regkas.service.core.receipt;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.domain.api.context.CashBoxContext;
import org.regkas.domain.api.repository.CashBoxRepository;
import org.regkas.domain.api.values.Name;
import org.regkas.domain.api.values.ReceiptId;
import org.regkas.service.core.DateTimeHelperMock;
import org.regkas.test.model.EntityManagerProviderForRegkas;

@RunWith(Arquillian.class)
public class ReceiptIdCalculatorTest extends EntityManagerProviderForRegkas {

    @Inject
    private ReceiptIdCalculatorFactory receiptIdCalculatorFactory;

    @Inject
    private CashBoxContext cashBoxContext;

    @Inject
    private CashBoxRepository cashBoxRepository;

    @Inject
    private DateTimeHelperMock dateTimeHelperMock;

    @Test
    @Transactional
    public void testGetNextReceiptId() throws Exception {
        cashBoxContext.set(cashBoxRepository.loadBy(new Name("RegKas1")));
        dateTimeHelperMock.setDate(LocalDate.of(2016, 1, 1));
        assertEquals("2016-0000001", receiptIdCalculatorFactory.get().getNextReceiptId().get());
        dateTimeHelperMock.resetDate();
        assertEquals("2015-0000003", receiptIdCalculatorFactory.get().getNextReceiptId().get());
        cashBoxContext.clear();
    }

    @Test
    @Transactional
    public void testReceiptId() {
        ReceiptId receiptId = new ReceiptId("2015-0000011");
        assertEquals("2015-0000011", receiptId.get());
        assertEquals(12, receiptId.get().length());
        assertEquals(2015, receiptId.getYear());
        assertEquals(11, receiptId.getNumber());

        receiptId = new ReceiptId(2015, 11);
        assertEquals("2015-0000011", receiptId.get());
        assertEquals(12, receiptId.get().length());
    }
}
