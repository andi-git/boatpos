package org.boatpos.domain.core.model;

import org.boatpos.common.domain.api.model.DomainModel;
import org.boatpos.model.ContainsRentals;
import org.boatpos.model.RentalEntity;
import org.boatpos.domain.api.model.Rental;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.spi.CDI;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Helper-class for rental-operations.
 */
@ApplicationScoped
public class RentalConverter {

    public static RentalConverter getViaCDI() {
        return CDI.current().select(RentalConverter.class).get();
    }

    public Set<Rental> convert(Set<RentalEntity> rentalEntities) {
        return Collections.unmodifiableSet(rentalEntities.stream().map(RentalCore::new).collect(Collectors.toSet()));
    }

    public void setRentals(ContainsRentals entity, Set<Rental> rentals) {
        checkNotNull(entity, "entity' must not be null");
        if (rentals != null) {
            entity.setRentals(rentals.stream().map(DomainModel::asEntity).collect(Collectors.toSet()));
        }
    }

    public void addRental(ContainsRentals entity, Rental rental) {
        if (rental != null) {
            entity.getRentals().add(rental.asEntity());
        }
    }
}
