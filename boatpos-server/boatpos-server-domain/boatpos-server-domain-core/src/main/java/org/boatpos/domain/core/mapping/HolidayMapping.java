package org.boatpos.domain.core.mapping;

import org.boatpos.common.domain.core.mapping.Mapping;
import org.boatpos.model.HolidayEntity;
import org.boatpos.service.api.bean.HolidayBean;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.spi.CDI;

/**
 * Mapping between {@link HolidayEntity} and {@link HolidayBean}.
 */
@Dependent
public class HolidayMapping extends Mapping<HolidayEntity, HolidayBean> {

    public static HolidayMapping fromCDI() {
        return CDI.current().select(HolidayMapping.class).get();
    }
}
