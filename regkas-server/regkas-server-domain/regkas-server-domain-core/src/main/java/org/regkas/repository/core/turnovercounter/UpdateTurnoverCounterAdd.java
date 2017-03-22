package org.regkas.repository.core.turnovercounter;

import org.regkas.repository.api.model.CashBox;
import org.regkas.repository.api.turnovercounter.UpdateTurnoverCounter;
import org.regkas.repository.api.util.TotalPriceEuroToCentConverter;
import org.regkas.repository.api.values.TotalPrice;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class UpdateTurnoverCounterAdd implements UpdateTurnoverCounter {

    @Inject
    private TotalPriceEuroToCentConverter totalPriceEuroToCentConverter;

    @Override
    public void updateTurnOver(CashBox cashBox, TotalPrice totalPrice) {
        cashBox.addCentsToTurnoverCount(totalPriceEuroToCentConverter.convert(totalPrice));
    }
}
