package org.regkas.domain.core.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.domain.api.model.ReceiptType;
import org.regkas.domain.api.receipt.precondition.DayChangedAndDayReceiptPrinted;
import org.regkas.domain.api.receipt.precondition.MonthChangedAndMonthReceiptPrinted;
import org.regkas.domain.api.receipt.precondition.StartReceiptAvailable;
import org.regkas.domain.api.repository.ReceiptTypeRepository;
import org.regkas.domain.api.values.Name;
import org.regkas.domain.core.turnovercounter.UpdateTurnoverCounterNothing;
import org.regkas.test.model.EntityManagerProviderForRegkas;

@SuppressWarnings("OptionalGetWithoutIsPresent")
@RunWith(Arquillian.class)
public class ReceiptTypeTrainingCoreTest extends EntityManagerProviderForRegkas {

    @Inject
    private ReceiptTypeRepository receiptTypeRepository;

    private ReceiptType receiptType;

    @Before
    public void before() {
        receiptType = receiptTypeRepository.loadBy(new Name("Training-Beleg")).get();
    }

    @Test
    @Transactional
    public void testGetTrainingBelegFromDatabase() {
        assertEquals(ReceiptTypeTrainingCore.class, receiptType.getClass());
        assertEquals("Training-Beleg", receiptType.getName().get());
    }

    @Test
    @Transactional
    public void getGetUpdateTurnoverCounter() {
        assertEquals(UpdateTurnoverCounterNothing.class, receiptType.getUpdateTurnoverCounter().getClass().getSuperclass());
    }

    @Test
    @Transactional
    public void testPreconditions() throws Exception {
        assertTrue(receiptType.getPreconditions().contains(StartReceiptAvailable.class));
        assertTrue(receiptType.getPreconditions().contains(MonthChangedAndMonthReceiptPrinted.class));
        assertTrue(receiptType.getPreconditions().contains(DayChangedAndDayReceiptPrinted.class));
    }
}
