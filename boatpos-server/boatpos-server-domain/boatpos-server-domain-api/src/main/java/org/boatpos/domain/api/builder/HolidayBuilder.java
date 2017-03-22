package org.boatpos.domain.api.builder;

import org.boatpos.common.domain.api.builder.DomainModelBuilderWithDto;
import org.boatpos.domain.api.model.Holiday;
import org.boatpos.domain.api.model.Rental;
import org.boatpos.model.HolidayEntity;
import org.boatpos.domain.api.values.Day;
import org.boatpos.domain.api.values.Name;
import org.boatpos.service.api.bean.HolidayBean;

/**
 * Builder for {@link Rental}.
 */
public interface HolidayBuilder extends DomainModelBuilderWithDto<HolidayBuilder, Holiday, HolidayEntity, HolidayBean> {

    HolidayBuilder add(Day day);

    HolidayBuilder add(Name name);
}
