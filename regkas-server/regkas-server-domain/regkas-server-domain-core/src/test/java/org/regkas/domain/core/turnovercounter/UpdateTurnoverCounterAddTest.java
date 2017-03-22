package org.regkas.domain.core.turnovercounter;

import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.domain.api.model.CashBox;
import org.regkas.domain.api.repository.CashBoxRepository;
import org.regkas.domain.api.values.Name;
import org.regkas.domain.api.values.TotalPrice;
import org.regkas.test.model.EntityManagerProviderForRegkas;

import javax.inject.Inject;

import static org.junit.Assert.assertEquals;

@RunWith(Arquillian.class)
public class UpdateTurnoverCounterAddTest extends EntityManagerProviderForRegkas {

    @Inject
    private UpdateTurnoverCounterAdd updateTurnoverCounterAdd;

    @Inject
    private CashBoxRepository cashBoxRepository;

    @Test
    @Transactional
    public void testUpdateTurnOver() throws Exception {
        CashBox cashBox = cashBoxRepository.loadBy(new Name("RegKas1")).get();
        assertEquals(1300, cashBox.getTurnoverCountCent().get().intValue());
        updateTurnoverCounterAdd.updateTurnOver(cashBox, new TotalPrice("12.00"));
        assertEquals(2500, cashBox.getTurnoverCountCent().get().intValue());
    }

}