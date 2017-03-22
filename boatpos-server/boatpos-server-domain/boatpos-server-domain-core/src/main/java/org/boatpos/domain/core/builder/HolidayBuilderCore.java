package org.boatpos.domain.core.builder;

import org.boatpos.common.domain.core.builder.DomainModelBuilderWithDtoCore;
import org.boatpos.domain.core.model.HolidayCore;
import org.boatpos.model.HolidayEntity;
import org.boatpos.domain.api.builder.HolidayBuilder;
import org.boatpos.domain.api.model.Holiday;
import org.boatpos.domain.api.values.Day;
import org.boatpos.domain.api.values.Name;
import org.boatpos.service.api.bean.HolidayBean;

import javax.enterprise.context.Dependent;

@Dependent
public class HolidayBuilderCore extends DomainModelBuilderWithDtoCore<HolidayBuilder, Holiday, HolidayCore, HolidayEntity, HolidayBean> implements HolidayBuilder {

    protected Day day;
    protected Name name;

    @Override
    public HolidayBuilder add(Day day) {
        this.day = day;
        return this;
    }

    @Override
    public HolidayBuilder add(Name name) {
        this.name = name;
        return this;
    }

    @Override
    public Holiday build() {
        return new HolidayCore(id, version, day, name);
    }
}
