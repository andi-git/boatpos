package org.boatpos.repository.core.mapping;

import org.boatpos.model.RentalEntity;
import org.boatpos.service.api.bean.RentalBean;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Arquillian.class)
public class RentalMappingTest {

    @Inject
    private RentalMapping rentalMapping;

    @Test
    public void testMappingOfDateLocal() {
        RentalEntity entity = new RentalEntity();
        entity.setDate(LocalDate.of(2015, 7, 1));
        RentalBean dto = rentalMapping.mapEntity(entity);
        assertEquals(LocalDate.of(2015, 7, 1), dto.getDate());
    }

    @Test
    public void testMapFinished() {
        RentalEntity entity = new RentalEntity();
        entity.setId(99L);
        entity.setDayId(5);
        entity.setFinished(true);
        RentalBean dto = rentalMapping.mapEntity(entity);
        assertEquals(99L, entity.getId().longValue());
        assertEquals(5, entity.getDayId().intValue());
        assertTrue(dto.isFinished());
    }
}