package org.boatpos.repository.api.repository;

import org.boatpos.repository.api.model.Boat;
import org.boatpos.repository.api.values.Period;
import org.boatpos.repository.api.values.SumPaid;

/**
 * Produce some journals.
 */
public interface JournalRepository {

    /**
     * Get the sum of all payments for a {@link Boat} within a {@link Period}.
     *
     * @param boat   the {@link Boat} to get the sum for
     * @param period the {@link Period} to get the sum for
     * @return the sum of the payments
     */
    SumPaid sum(Boat boat, Period period);
}
