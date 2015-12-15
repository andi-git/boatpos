package org.boatpos.dao.core;

import org.boatpos.util.datetime.DateTimeHelper;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Specializes;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Specializes
@ApplicationScoped
public class DateTimeHelperMock extends DateTimeHelper {

    @Override
    public LocalDate currentDate() {
        return LocalDate.of(2015, 7, 1);
    }

    @Override
    public LocalDateTime currentTime() {
        return LocalDateTime.of(2015, 7, 1, 15, 0);
    }
}
