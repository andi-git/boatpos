package org.regkas.repository.core;

import org.boatpos.common.util.datetime.DateTimeHelper;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Specializes
@ApplicationScoped
public class DateTimeHelperMock extends DateTimeHelper {

    private static final LocalDate DEFAULT_DATE = LocalDate.of(2015, 7, 1);

    private static final LocalDateTime DEFAULT_TIME = LocalDateTime.of(2015, 7, 1, 15, 0);

    private LocalDate date = DEFAULT_DATE;

    private LocalDateTime time = DEFAULT_TIME;

    @Override
    public LocalDate currentDate() {
        return date;
    }

    @Override
    public LocalDateTime currentTime() {
        return LocalDateTime.of(2015, 7, 1, 15, 0);
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public void resetDate() {
        date = DEFAULT_DATE;
    }

    public void resetTime() {
        time = DEFAULT_TIME;
    }
}
