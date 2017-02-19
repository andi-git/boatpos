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
import org.regkas.repository.core.turnovercounter.UpdateTurnoverCounterNothing;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("OptionalGetWithoutIsPresent")
@RunWith(Arquillian.class)
public class ReceiptTypeStartCoreTest extends EntityManagerProviderForRegkas {

    @Inject
    private ReceiptTypeRepository receiptTypeRepository;

    @Inject
    private CashBoxRepository cashBoxRepository;

    private ReceiptType receiptType;

    @Before
    public void before() {
        receiptType = receiptTypeRepository.loadBy(new Name("Start-Beleg")).get();
    }

    @Test
    @Transactional
    public void testGetStartBelegFromDatabase() {
        assertEquals(ReceiptTypeStartCore.class, receiptType.getClass());
        assertEquals("Start-Beleg", receiptType.getName().get());
    }

    @Test
    @Transactional
    public void getGetUpdateTurnoverCounter() {
        assertEquals(UpdateTurnoverCounterNothing.class, receiptType.getUpdateTurnoverCounter().getClass().getSuperclass());
    }

    @Test
    @Transactional
    public void getGetInputForChainCalculation() {
        CashBox cashBox = cashBoxRepository.loadBy(new Name("RegKas1")).get();
        assertEquals("x1OpVx19rNQ=", receiptType.calculateChainValue(cashBox).get());
    }
}