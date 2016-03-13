package org.boatpos.repository.api.model;

import org.boatpos.common.repository.api.model.DomainModel;
import org.boatpos.model.HolidayEntity;
import org.boatpos.repository.api.values.*;
import org.boatpos.service.api.bean.HolidayBean;

/**
 * The domain model for a holiday.
 */
public interface Holiday extends DomainModel<Holiday, HolidayEntity, HolidayBean> {

    Day getDay();

    Holiday setDay(Day day);

    Name getName();

    Holiday setName(Name name);
}
