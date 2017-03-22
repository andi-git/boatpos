package org.boatpos.service.core.util;

import org.boatpos.domain.api.values.ArrivalTime;
import org.boatpos.domain.api.values.Day;
import org.boatpos.domain.api.values.DepartureTime;
import org.boatpos.common.util.datetime.DateTimeHelper;
import org.boatpos.common.util.qualifiers.Current;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

/**
 * Helper to get (or inject) the current date or time.
 */
@ApplicationScoped
public class CurrentDateTimeHelper {

    @Inject
    private DateTimeHelper dateTimeHelper;

    @Produces
    @Current
    @Dependent
    public Day currentDay() {
        return new Day(dateTimeHelper.currentDate());
    }

    @Produces
    @Current
    @Dependent
    public DepartureTime currentDepartureTime() {
        return new DepartureTime(dateTimeHelper.currentTime());
    }

    @Produces
    @Current
    @Dependent
    public ArrivalTime currentArrivalTime() {
        return new ArrivalTime(dateTimeHelper.currentTime());
    }
}
