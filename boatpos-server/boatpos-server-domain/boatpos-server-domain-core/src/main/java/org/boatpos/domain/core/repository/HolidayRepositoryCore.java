package org.boatpos.domain.core.repository;

import org.boatpos.common.domain.core.respository.DomainModelRepositoryCore;
import org.boatpos.domain.api.model.Holiday;
import org.boatpos.domain.api.values.Day;
import org.boatpos.domain.core.builder.HolidayBuilderCore;
import org.boatpos.domain.core.model.HolidayCore;
import org.boatpos.model.HolidayEntity;
import org.boatpos.domain.api.builder.HolidayBuilder;
import org.boatpos.domain.api.repository.HolidayRepository;

import javax.enterprise.context.Dependent;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

@Dependent
public class HolidayRepositoryCore extends DomainModelRepositoryCore<Holiday, HolidayCore, HolidayEntity, HolidayBuilder, HolidayBuilderCore> implements HolidayRepository {

    @Override
    public Optional<Holiday> loadBy(Day day) {
        if (day == null || day.get() == null) {
            return Optional.empty();
        }
        return loadByParameter("holiday.getByDay", (query) -> query
                .setParameter("day", day.get()));
    }
}