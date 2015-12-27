package org.boatpos.model;

import java.util.Set;

/**
 * {@link RentalEntity}s are available in many entities.
 */
public interface ContainsRentals {

    Set<RentalEntity> getRentals();

    void setRentals(Set<RentalEntity> rentals);
}
