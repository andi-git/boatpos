package org.regkas.repository.core;

import org.boatpos.common.util.datetime.DateTimeHelper;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.function.Supplier;

@Specializes
@ApplicationScoped
public class DateTimeHelperMock extends DateTimeHelper {

    public static final LocalDate DEFAULT_DATE = LocalDate.of(2015, 7, 1);

    public static final LocalDateTime DEFAULT_TIME = LocalDateTime.of(2015, 7, 1, 15, 0);

    private LocalDate date = DEFAULT_DATE;

    private LocalDateTime time = DEFAULT_TIME;

    private Optional<Supplier<LocalDateTime>> dateTimeSupplier = Optional.empty();

    @Override
    public LocalDate currentDate() {
        return date;
    }

    @Override
    public LocalDateTime currentTime() {
        return dateTimeSupplier.map(Supplier::get).orElse(time);
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public void setTime(Supplier<LocalDateTime> dateTimeSupplier) {
        this.dateTimeSupplier = Optional.of(dateTimeSupplier);
    }

    public void resetDate() {
        date = DEFAULT_DATE;
    }

    public void resetTime() {
        time = DEFAULT_TIME;
        dateTimeSupplier = Optional.empty();
    }
}
