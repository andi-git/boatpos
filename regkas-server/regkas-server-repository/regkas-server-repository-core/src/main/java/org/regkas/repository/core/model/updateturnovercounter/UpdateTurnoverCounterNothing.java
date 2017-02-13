package org.regkas.repository.core.model.updateturnovercounter;

import org.regkas.repository.api.model.CashBox;
import org.regkas.repository.api.model.updateturnovercounter.UpdateTurnoverCounter;
import org.regkas.repository.api.values.TotalPrice;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UpdateTurnoverCounterNothing implements UpdateTurnoverCounter {

    @Override
    public void updateTurnOver(CashBox cashBox, TotalPrice totalPrice) {
        // nothing to do
    }
}
