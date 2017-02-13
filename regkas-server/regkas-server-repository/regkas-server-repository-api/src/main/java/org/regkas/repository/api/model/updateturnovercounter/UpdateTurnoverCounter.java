package org.regkas.repository.api.model.updateturnovercounter;

import org.regkas.repository.api.model.CashBox;
import org.regkas.repository.api.values.TotalPrice;

/**
 * Update the turnover-count for a {@link CashBox} by a {@link TotalPrice}.
 */
public interface UpdateTurnoverCounter {

    void updateTurnOver(CashBox cashBox, TotalPrice totalPrice);
}
