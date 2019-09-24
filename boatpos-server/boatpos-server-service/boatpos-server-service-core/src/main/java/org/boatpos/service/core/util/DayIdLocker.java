package org.boatpos.service.core.util;

import org.boatpos.common.util.datetime.DateTimeHelper;
import org.boatpos.domain.api.values.DayId;

import javax.ejb.*;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Lock a rental based on the day-id. The same day-id must not be paid twice!
 * <p>
 * The problem was: the receipt took too long and the user finished the same day-id twice when the first execution was
 * not finished. So there where 2 receipts (RKSV) for one rental.
 */
@Singleton
@Startup
@ConcurrencyManagement(ConcurrencyManagementType.CONTAINER)
@Lock(LockType.WRITE)
public class DayIdLocker {

    private final Map<DayId, LocalDateTime> locks = new ConcurrentHashMap<>();

    @Inject
    private DateTimeHelper dateTimeHelper;

    public void lock(DayId dayId) {
        locks.put(dayId, dateTimeHelper.currentTime());
    }

    public void release(DayId dayId) {
        locks.remove(dayId);
    }

    public boolean isLocked(DayId dayId) {
        return locks.containsKey(dayId);
    }

    @Schedule(hour = "*", minute = "*", persistent = false)
    public void releaseByTime() {
        LocalDateTime now = dateTimeHelper.currentTime();
        locks.entrySet().stream()
                .filter(entry -> ChronoUnit.MINUTES.between(entry.getValue(), now) >= 2)
                .forEach(entry -> locks.remove(entry.getKey()));
    }
}
