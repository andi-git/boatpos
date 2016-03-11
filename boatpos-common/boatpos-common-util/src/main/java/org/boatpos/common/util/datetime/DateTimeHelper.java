package org.boatpos.common.util.datetime;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Helper for date- and time-operations.
 */
@ApplicationScoped
public class DateTimeHelper {

    public static final ZoneId ZONE_ID = ZoneId.of("Europe/Berlin");

    public LocalDate currentDate() {
        return LocalDate.now(ZONE_ID);
    }

    public LocalDateTime currentTime() {
        return LocalDateTime.now(ZONE_ID);
    }
}
