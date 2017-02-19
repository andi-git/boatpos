package org.regkas.repository.core.model;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.repository.api.model.CashBox;
import org.regkas.repository.api.model.ReceiptType;
import org.regkas.repository.api.repository.CashBoxRepository;
import org.regkas.repository.api.repository.ReceiptTypeRepository;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.core.turnovercounter.UpdateTurnoverCounterAdd;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

@SuppressWarnings("OptionalGetWithoutIsPresent")
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
    }

    @Test(expected = NullPointerException.class)
    @Transactional
    public void testGetStandardBelegFromDatabaseIllegalArgument() {
        Name name = null;
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
        assertEquals("9jDAZ9vSoqA=", receiptType.calculateChainValue(cashBox).get());
    }

}