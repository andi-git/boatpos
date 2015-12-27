package org.boatpos.repository.core.mapping;

import org.boatpos.model.RentalEntity;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.time.LocalDate;

@RunWith(Arquillian.class)
public class RentalMappingTest {

    @Inject
    private RentalMapping rentalMapping;

    @Test
    public void testMappingOfDateLocal() {
        RentalEntity rental = new RentalEntity();
        rental.setDate(LocalDate.of(2015, 7, 1));
        rentalMapping.mapEntity(rental);
    }
}