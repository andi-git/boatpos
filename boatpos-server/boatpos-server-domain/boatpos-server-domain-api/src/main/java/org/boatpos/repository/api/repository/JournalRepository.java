package org.boatpos.repository.api.repository;

import org.boatpos.common.model.PaymentMethod;
import org.boatpos.repository.api.model.Rental;
import org.boatpos.repository.api.values.BoatCountResult;
import org.boatpos.repository.api.values.IncomeResult;
import org.boatpos.repository.api.values.Period;

import java.util.List;

/**
 * Produce some journals.
 */
public interface JournalRepository {

    /**
     * Get all {@link List} of all before-incomes of the {@link Rental}s for a {@link Period}.
     *
     * @param period        the {@link Period} to get the total income for
     * @param paymentMethod the type of the payment
     * @return the before-income for a certain {@link Period}
     */
    List<IncomeResult> totalIncomeBeforeFor(Period period, PaymentMethod paymentMethod);

    /**
     * Get all {@link List} of all after-incomes of the {@link Rental}s for a {@link Period}.
     *
     * @param period        the {@link Period} to get the total income for
     * @param paymentMethod the type of the payment
     * @return the after-income for a certain {@link Period}
     */
    List<IncomeResult> totalIncomeAfterFor(Period period, PaymentMethod paymentMethod);

    /**
     * Get the boat-count for a {@link Period}.
     *
     * @param period the {@link Period} to get the total income for
     * @return the boat-count for a certain {@link Period}
     */
    List<BoatCountResult> countBoatFor(Period period);
}
