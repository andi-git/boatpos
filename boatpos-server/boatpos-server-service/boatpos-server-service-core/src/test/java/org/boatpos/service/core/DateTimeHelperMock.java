package org.boatpos.service.core;

import org.boatpos.util.datetime.DateTimeHelper;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Specializes
@ApplicationScoped
public class DateTimeHelperMock extends DateTimeHelper {

    private static final LocalDate DEFAULT_DATE = LocalDate.of(2015, 7, 1);

    private static final LocalDateTime DEFAULT_TIME = LocalDateTime.of(2015, 7, 1, 15, 0);

    private LocalDate currentDate = DEFAULT_DATE;

    private LocalDateTime currentTime = DEFAULT_TIME;

    @Override
    public LocalDate currentDate() {
        return currentDate;
    }

    @Override
    public LocalDateTime currentTime() {
        return currentTime;
    }

    public void setDate(LocalDate localDate) {
        currentDate = localDate;
    }

    public void setTime(LocalDateTime localDateTime) {
        currentTime = localDateTime;
    }

    public void reset() {
        currentDate = DEFAULT_DATE;
        currentTime = DEFAULT_TIME;
    }
}
