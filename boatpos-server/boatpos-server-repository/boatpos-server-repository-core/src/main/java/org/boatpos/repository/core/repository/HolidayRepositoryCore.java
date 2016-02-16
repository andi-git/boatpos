package org.boatpos.repository.core.repository;

import org.boatpos.model.HolidayEntity;
import org.boatpos.model.PaymentMethod;
import org.boatpos.model.RentalEntity;
import org.boatpos.repository.api.builder.HolidayBuilder;
import org.boatpos.repository.api.builder.RentalBuilder;
import org.boatpos.repository.api.model.*;
import org.boatpos.repository.api.repository.HolidayRepository;
import org.boatpos.repository.api.repository.RentalRepository;
import org.boatpos.repository.api.values.*;
import org.boatpos.repository.core.builder.HolidayBuilderCore;
import org.boatpos.repository.core.builder.RentalBuilderCore;
import org.boatpos.repository.core.model.HolidayCore;
import org.boatpos.repository.core.model.RentalCore;
import org.boatpos.util.datetime.DateTimeHelper;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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