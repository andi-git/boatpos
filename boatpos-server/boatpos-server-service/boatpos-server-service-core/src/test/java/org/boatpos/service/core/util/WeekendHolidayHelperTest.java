package org.boatpos.service.core.util;

import org.boatpos.common.domain.api.values.Enabled;
import org.boatpos.common.service.core.ModelDtoConverter;
import org.boatpos.domain.api.repository.PromotionBeforeRepository;
import org.boatpos.domain.api.values.Name;
import org.boatpos.service.api.bean.PromotionBeforeBean;
import org.boatpos.service.core.DateTimeHelperMock;
import org.boatpos.test.model.EntityManagerProviderForBoatpos;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(Arquillian.class)
public class WeekendHolidayHelperTest extends EntityManagerProviderForBoatpos {

    @Inject
    private PromotionBeforeRepository promotionBeforeRepository;

    @Inject
    private WeekendHolidayHelper weekendHolidayHelper;

    @Inject
    private DateTimeHelperMock dateTimeHelper;

    @Inject
    private ModelDtoConverter modelDtoConverter;

    @Test
    @Transactional
    public void testHolidayHelper() {
        assertFalse(weekendHolidayHelper.modify(Optional.empty()).isPresent());
        Optional<PromotionBeforeBean> promotionBefore = modelDtoConverter.convert(promotionBeforeRepository.loadBy(new Name("Fahr 3 zahl 2")));
        assertTrue(weekendHolidayHelper.modify(promotionBefore).get().isEnabled());
        dateTimeHelper.setDate(LocalDate.of(2015, 8, 15));
        assertFalse(weekendHolidayHelper.modify(promotionBefore).get().isEnabled());

        List<PromotionBeforeBean> promotionsBefore = modelDtoConverter.convert(promotionBeforeRepository.loadAll(Enabled.TRUE));
        promotionsBefore.forEach((pb) -> assertTrue(pb.isEnabled()));
        promotionsBefore = weekendHolidayHelper.modify(promotionsBefore);
        promotionsBefore.forEach((pb) -> assertFalse(pb.isEnabled()));

        dateTimeHelper.reset();
    }
}