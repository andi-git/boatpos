package org.regkas.service.core.receipt.precondition;

import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.domain.api.model.ReceiptType;
import org.regkas.domain.api.receipt.precondition.DayChangedAndDayReceiptPrinted;
import org.regkas.domain.api.receipt.precondition.MonthChangedAndMonthReceiptPrinted;
import org.regkas.domain.api.receipt.precondition.Precondition;
import org.regkas.domain.api.receipt.precondition.SignatureDeviceAvailable;
import org.regkas.domain.api.receipt.precondition.StartReceiptAvailable;
import org.regkas.domain.api.receipt.precondition.StartReceiptNotAvailable;
import org.regkas.domain.api.repository.ReceiptTypeRepository;
import org.regkas.domain.api.values.Name;
import org.regkas.test.model.EntityManagerProviderForRegkas;

@SuppressWarnings("ConstantConditions")
@RunWith(Arquillian.class)
public class PreconditionCheckerTest extends EntityManagerProviderForRegkas {

    @Inject
    private PreconditionChecker preconditionChecker;

    @Inject
    private ReceiptTypeRepository receiptTypeRepository;

    @Test
    @Transactional
    public void testGetOrderedPreconditions() throws Exception {
        ReceiptType receiptTypeStart = receiptTypeRepository.loadBy(new Name("Start-Beleg")).get();
        List<Precondition> orderedPreconditions = preconditionChecker.getOrderedPreconditions(receiptTypeStart);
        assertTrue(orderedPreconditions.get(0) instanceof StartReceiptNotAvailable);
        assertTrue(orderedPreconditions.get(1) instanceof SignatureDeviceAvailable);

        ReceiptType receiptTypeStandard = receiptTypeRepository.loadBy(new Name("Standard-Beleg")).get();
        orderedPreconditions = preconditionChecker.getOrderedPreconditions(receiptTypeStandard);
        assertTrue(orderedPreconditions.get(0) instanceof StartReceiptAvailable);
        assertTrue(orderedPreconditions.get(1) instanceof MonthChangedAndMonthReceiptPrinted);
        assertTrue(orderedPreconditions.get(2) instanceof DayChangedAndDayReceiptPrinted);
    }

}
