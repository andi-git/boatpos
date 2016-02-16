package org.boatpos.service.core.util;

import org.boatpos.repository.api.repository.HolidayRepository;
import org.boatpos.repository.api.values.Day;
import org.boatpos.service.api.bean.PromotionBeforeBean;
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

    public List<PromotionBeforeBean> modify(List<PromotionBeforeBean> promotionsBefore) {
        promotionsBefore.forEach(this::modify);
        return promotionsBefore;
    }

    public Optional<PromotionBeforeBean> modify(Optional<PromotionBeforeBean> promotionBefore) {
        if (promotionBefore.isPresent()) {
            return Optional.of(modify(promotionBefore.get()));
        }
        return promotionBefore;
    }

    public PromotionBeforeBean modify(PromotionBeforeBean promotionBefore) {
        if (isWeekendOrHoliday()) {
            promotionBefore.setEnabled(false);
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
