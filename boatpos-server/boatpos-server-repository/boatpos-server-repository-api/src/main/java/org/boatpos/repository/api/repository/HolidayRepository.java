package org.boatpos.repository.api.repository;

import org.boatpos.repository.api.builder.HolidayBuilder;
import org.boatpos.repository.api.model.Holiday;
import org.boatpos.repository.api.values.Day;

import java.util.Optional;

/**
 * The repository for the {@link Holiday}.
 */
public interface HolidayRepository extends DomainModelRepository<Holiday> {

    /**
     * Get the {@link HolidayBuilder}.
     *
     * @return the {@link HolidayBuilder}
     */
    HolidayBuilder builder();

    /**
     * Get a {@link Holiday} by {@link Day}.
     *
     * @param day the {@link Day} to check
     * @return the {@link Holiday} for a {@link Day}
     */
    Optional<Holiday> loadBy(Day day);
}
