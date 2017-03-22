package org.boatpos.domain.api.repository;

import org.boatpos.common.domain.api.repository.DomainModelRepository;
import org.boatpos.domain.api.model.Holiday;
import org.boatpos.domain.api.builder.HolidayBuilder;
import org.boatpos.domain.api.values.Day;

import java.util.Optional;

/**
 * The repository for the {@link Holiday}.
 */
public interface HolidayRepository extends DomainModelRepository<Holiday, HolidayBuilder> {

    /**
     * Get a {@link Holiday} by {@link Day}.
     *
     * @param day the {@link Day} to check
     * @return the {@link Holiday} for a {@link Day}
     */
    Optional<Holiday> loadBy(Day day);
}
