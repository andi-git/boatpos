package org.regkas.domain.core.turnovercounter;

import org.regkas.domain.api.model.CashBox;
import org.regkas.domain.api.turnovercounter.UpdateTurnoverCounter;
import org.regkas.domain.api.util.TotalPriceEuroToCentConverter;
import org.regkas.domain.api.values.TotalPrice;

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
