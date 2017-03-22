package org.boatpos.domain.api.model;

import java.util.Set;

/**
 * {@link Rental}s are available in many models.
 */
public interface ContainsRentals <MODEL extends ContainsRentals> {

    Set<Rental> getRentals();

    MODEL setRentals(Set<Rental> rentals);

    MODEL addRental(Rental rental);
}
