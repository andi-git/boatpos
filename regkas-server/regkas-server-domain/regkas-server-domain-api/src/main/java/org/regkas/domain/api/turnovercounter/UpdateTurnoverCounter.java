package org.regkas.domain.api.turnovercounter;

import org.regkas.domain.api.model.CashBox;
import org.regkas.domain.api.values.TotalPrice;

/**
 * Update the turnover-count for a {@link CashBox} by a {@link TotalPrice}.
 */
public interface UpdateTurnoverCounter {

    void updateTurnOver(CashBox cashBox, TotalPrice totalPrice);
}
