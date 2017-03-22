package org.boatpos.domain.api.model;

import org.boatpos.common.domain.api.model.DomainModelWithDto;
import org.boatpos.domain.api.values.Day;
import org.boatpos.domain.api.values.Name;
import org.boatpos.model.HolidayEntity;
import org.boatpos.service.api.bean.HolidayBean;

/**
 * The domain model for a holiday.
 */
public interface Holiday extends DomainModelWithDto<Holiday, HolidayEntity, HolidayBean> {

    Day getDay();

    Holiday setDay(Day day);

    Name getName();

    Holiday setName(Name name);
}
