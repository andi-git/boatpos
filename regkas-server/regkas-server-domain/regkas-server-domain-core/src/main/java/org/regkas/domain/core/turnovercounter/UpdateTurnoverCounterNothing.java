package org.regkas.domain.core.turnovercounter;

import org.regkas.domain.api.model.CashBox;
import org.regkas.domain.api.turnovercounter.UpdateTurnoverCounter;
import org.regkas.domain.api.values.TotalPrice;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UpdateTurnoverCounterNothing implements UpdateTurnoverCounter {

    @Override
    public void updateTurnOver(CashBox cashBox, TotalPrice totalPrice) {
        // nothing to do
    }
}
