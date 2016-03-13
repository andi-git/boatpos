package org.boatpos.repository.core.repository;

import org.boatpos.common.repository.core.respository.DomainModelRepositoryCore;
import org.boatpos.model.HolidayEntity;
import org.boatpos.repository.api.builder.HolidayBuilder;
import org.boatpos.repository.api.model.*;
import org.boatpos.repository.api.repository.HolidayRepository;
import org.boatpos.repository.api.values.*;
import org.boatpos.repository.core.builder.HolidayBuilderCore;
import org.boatpos.repository.core.model.HolidayCore;

import javax.enterprise.context.Dependent;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

@Dependent
public class HolidayRepositoryCore extends DomainModelRepositoryCore<Holiday, HolidayCore, HolidayEntity> implements HolidayRepository {

    @Override
    public HolidayBuilder builder() {
        return new HolidayBuilderCore();
    }

    @Override
    public Optional<Holiday> loadBy(Day day) {
        if (day == null || day.get() == null) {
            return Optional.empty();
        }
        return loadByParameter("holiday.getByDay", (query) -> query
                .setParameter("day", day.get()));
    }
}