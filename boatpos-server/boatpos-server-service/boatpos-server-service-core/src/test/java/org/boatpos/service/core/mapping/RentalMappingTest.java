package org.boatpos.service.core.mapping;

import org.boatpos.model.Rental;
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
        Rental rental = new Rental();
        rental.setDate(LocalDate.of(2015, 7, 1));
        rentalMapping.mapEntity(rental);
    }
}