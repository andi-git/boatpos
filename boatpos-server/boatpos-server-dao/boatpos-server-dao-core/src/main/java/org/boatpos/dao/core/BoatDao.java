package org.boatpos.dao.core;

import org.boatpos.model.Boat;

import javax.enterprise.context.Dependent;

/**
 * The DAO for {@link Boat}.
 */
@Dependent
public class BoatDao extends GenericDaoCore<Boat> {

    @Override
    public Class<Boat> getType() {
        return Boat.class;
    }
}
