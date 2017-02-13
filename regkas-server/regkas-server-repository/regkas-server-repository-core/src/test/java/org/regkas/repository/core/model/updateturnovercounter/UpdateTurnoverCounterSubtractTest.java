package org.regkas.repository.core.model.updateturnovercounter;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.repository.api.model.CashBox;
import org.regkas.repository.api.repository.CashBoxRepository;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.api.values.TotalPrice;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class UpdateTurnoverCounterSubtractTest extends EntityManagerProviderForRegkas {

    @Inject
    private UpdateTurnoverCounterSubtract updateTurnoverCounterSubtract;

    @Inject
    private CashBoxRepository cashBoxRepository;

    @Test
    @Transactional
    public void testUpdateTurnOver() throws Exception {
        CashBox cashBox = cashBoxRepository.loadBy(new Name("RegKas1")).get();
        assertEquals(1300, cashBox.getTurnoverCountCent().get().intValue());
        updateTurnoverCounterSubtract.updateTurnOver(cashBox, new TotalPrice("4.00"));
        assertEquals(900, cashBox.getTurnoverCountCent().get().intValue());
    }

}