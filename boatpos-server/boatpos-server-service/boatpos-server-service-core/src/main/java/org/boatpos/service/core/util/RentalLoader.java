package org.boatpos.service.core.util;

import org.boatpos.domain.api.model.Rental;
import org.boatpos.domain.api.repository.RentalRepository;
import org.boatpos.domain.api.values.Day;
import org.boatpos.domain.api.values.DayId;
import org.boatpos.common.util.qualifiers.Current;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Load a {@link Rental}
 */
@Dependent
public class RentalLoader {

    @Inject
    private RentalRepository rentalRepository;

    @Inject
    @Current
    private Day currentDay;

    /**
     * Load the {@link Rental} of the current day by it's {@link DayId}. If there is no {@link Rental} available, throw
     * a {@link RuntimeException}.
     *
     * @param dayId the {@link DayId} of the {@link Rental} for the current day
     * @return the {@link Rental} or a {@link RuntimeException}
     */
    public Rental loadOnCurrentDayBy(DayId dayId) {
        checkNotNull(dayId, "'dayId' must not be null");
        checkNotNull(dayId.get(), "'dayId-value' must not be null");
        Optional<Rental> rental = rentalRepository.loadBy(currentDay, dayId);
        if (rental.isPresent()) {
            return rental.get();
        } else {
            throw new RuntimeException("unable to find a " + Rental.class.getName() + " for current day with day-id " + dayId.get());
        }
    }

    public void checkIfRentalIsActive(DayId dayId) {
        Rental rental = loadOnCurrentDayBy(dayId);
        if (rental.isFinished() != null && rental.isFinished().get()) {
            throw new IllegalStateException("rental " + dayId.get() + " is already finished");
        }
        if (rental.isDeleted() != null && rental.isDeleted().get()) {
            throw new IllegalStateException("rental " + dayId.get() + " is deleted");
        }
    }

    public void checkIfRentalIsDeleted(DayId dayId) {
        Rental rental = loadOnCurrentDayBy(dayId);
        if (rental.isDeleted() == null || !rental.isDeleted().get()) {
            throw new IllegalStateException("rental " + dayId.get() + " is NOT deleted");
        }
    }
}
