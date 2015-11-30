package org.boatpos.dao.core;

import org.boatpos.dao.api.BoatDao;
import org.boatpos.dao.api.RentalDao;
import org.boatpos.model.Boat;
import org.boatpos.model.Rental;

import javax.enterprise.context.Dependent;

@Dependent
public class RentalDaoCore extends GenericDaoCore<Rental> implements RentalDao {

    @Override
    public Class<Rental> getType() {
        return Rental.class;
    }
}
