package org.boatpos.repository.core.builder;

import org.boatpos.common.repository.core.builder.DomainModelBuilderWithDtoCore;
import org.boatpos.model.HolidayEntity;
import org.boatpos.repository.api.builder.HolidayBuilder;
import org.boatpos.repository.api.model.Holiday;
import org.boatpos.repository.api.values.Day;
import org.boatpos.repository.api.values.Name;
import org.boatpos.repository.core.model.HolidayCore;
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
