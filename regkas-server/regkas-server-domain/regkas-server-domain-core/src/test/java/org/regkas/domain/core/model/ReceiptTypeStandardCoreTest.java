package org.regkas.domain.core.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.domain.api.model.CashBox;
import org.regkas.domain.api.model.ReceiptType;
import org.regkas.domain.api.receipt.precondition.DayChangedAndDayReceiptPrinted;
import org.regkas.domain.api.receipt.precondition.MonthChangedAndMonthReceiptPrinted;
import org.regkas.domain.api.receipt.precondition.StartReceiptAvailable;
import org.regkas.domain.api.repository.CashBoxRepository;
import org.regkas.domain.api.repository.ReceiptTypeRepository;
import org.regkas.domain.api.values.Name;
import org.regkas.domain.core.turnovercounter.UpdateTurnoverCounterAdd;
import org.regkas.test.model.EntityManagerProviderForRegkas;

@SuppressWarnings({"OptionalGetWithoutIsPresent", "ConstantConditions"})
@RunWith(Arquillian.class)
public class ReceiptTypeStandardCoreTest extends EntityManagerProviderForRegkas {

    @Inject
    private ReceiptTypeRepository receiptTypeRepository;

    @Inject
    private CashBoxRepository cashBoxRepository;

    private ReceiptType receiptType;

    @Before
    public void before() {
        receiptType = receiptTypeRepository.loadBy(new Name("Standard-Beleg")).get();
    }

    @Test
    @Transactional
    public void testGetStandardBelegFromDatabase() {
        assertEquals(ReceiptTypeStandardCore.class, receiptType.getClass());
        assertEquals("Standard-Beleg", receiptType.getName().get());
        assertFalse(receiptType.getSignatureMandatory().get());
    }

    @Test(expected = NullPointerException.class)
    @Transactional
    public void testGetStandardBelegFromDatabaseIllegalArgument() {
        Name name = null;
        // noinspection ConstantConditions
        receiptTypeRepository.loadBy(name).get();
    }

    @Test
    @Transactional
    public void testGetStandardBelegFromDatabaseWrongName() {
        try {
            receiptTypeRepository.loadBy(new Name("_unknown_")).get();
            fail("exception should be thrown");
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("_unknown_"));
        }
    }

    @Test
    @Transactional
    public void getGetUpdateTurnoverCounter() {
        assertEquals(UpdateTurnoverCounterAdd.class, receiptType.getUpdateTurnoverCounter().getClass().getSuperclass());
    }

    @Test
    @Transactional
    public void getGetInputForChainCalculation() {
        CashBox cashBox = cashBoxRepository.loadBy(new Name("RegKas1")).get();
        assertEquals("dpzooBjO1F4=", receiptType.calculateChainValue(cashBox).get());
    }

    @Test
    @Transactional
    public void testPreconditions() throws Exception {
        assertTrue(receiptType.getPreconditions().contains(StartReceiptAvailable.class));
        assertTrue(receiptType.getPreconditions().contains(MonthChangedAndMonthReceiptPrinted.class));
        assertTrue(receiptType.getPreconditions().contains(DayChangedAndDayReceiptPrinted.class));
    }
}
