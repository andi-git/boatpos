package org.boatpos.service.core.util;

import org.boatpos.repository.api.model.PromotionBefore;
import org.boatpos.repository.api.repository.HolidayRepository;
import org.boatpos.repository.api.values.Day;
import org.boatpos.repository.api.values.Enabled;
import org.boatpos.util.datetime.DateTimeHelper;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;

@Dependent
public class WeekendHolidayHelper {

    @Inject
    private HolidayRepository holidayRepository;

    @Inject
    private DateTimeHelper dateTimeHelper;

    public List<PromotionBefore> check(List<PromotionBefore> promotionsBefore) {
        promotionsBefore.forEach(this::check);
        return promotionsBefore;
    }

    public Optional<PromotionBefore> check(Optional<PromotionBefore> promotionBefore) {
        if (promotionBefore.isPresent()) {
            return Optional.of(check(promotionBefore.get()));
        }
        return promotionBefore;
    }

    public PromotionBefore check(PromotionBefore promotionBefore) {
        if (isWeekendOrHoliday()) {
            promotionBefore.setEnabled(Enabled.FALSE);
        }
        return promotionBefore;
    }

    public boolean isWeekendOrHoliday() {
        return isCurrentDayWeekend() || isCurrentDayHoliday();
    }

    private boolean isCurrentDayWeekend() {
        DayOfWeek dayOfWeek = dateTimeHelper.currentDate().getDayOfWeek();
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }

    private boolean isCurrentDayHoliday() {
        return holidayRepository.loadBy(new Day(dateTimeHelper.currentDate())).isPresent();
    }
}
