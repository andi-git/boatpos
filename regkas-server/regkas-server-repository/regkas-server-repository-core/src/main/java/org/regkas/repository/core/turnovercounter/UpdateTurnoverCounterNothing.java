package org.regkas.repository.core.turnovercounter;

import org.regkas.repository.api.model.CashBox;
import org.regkas.repository.api.turnovercounter.UpdateTurnoverCounter;
import org.regkas.repository.api.values.TotalPrice;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UpdateTurnoverCounterNothing implements UpdateTurnoverCounter {

    @Override
    public void updateTurnOver(CashBox cashBox, TotalPrice totalPrice) {
        // nothing to do
    }
}
