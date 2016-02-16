package org.boatpos.service.core.util;

import org.boatpos.repository.api.model.PromotionBefore;
import org.boatpos.repository.api.repository.PromotionBeforeRepository;
import org.boatpos.repository.api.values.Enabled;
import org.boatpos.repository.api.values.Name;
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

    @Test
    @Transactional
    public void testHolidayHelper() {
        assertFalse(weekendHolidayHelper.check(Optional.empty()).isPresent());
        Optional<PromotionBefore> promotionBefore = promotionBeforeRepository.loadBy(new Name("Fahr 3 zahl 2"));
        assertTrue(weekendHolidayHelper.check(promotionBefore).get().isEnabled().get());
        dateTimeHelper.setDate(LocalDate.of(2015, 8, 15));
        assertFalse(weekendHolidayHelper.check(promotionBefore).get().isEnabled().get());
        promotionBefore.get().persist();

        List<PromotionBefore> promotionsBefore = promotionBeforeRepository.loadAll(Enabled.TRUE);
        promotionsBefore.forEach((pb) -> {
            assertTrue(pb.isEnabled().get());
        });
        promotionsBefore = weekendHolidayHelper.check(promotionsBefore);
        promotionsBefore.forEach((pb) -> {
            assertFalse(pb.isEnabled().get());
            pb.persist();
        });

        dateTimeHelper.reset();
    }
}