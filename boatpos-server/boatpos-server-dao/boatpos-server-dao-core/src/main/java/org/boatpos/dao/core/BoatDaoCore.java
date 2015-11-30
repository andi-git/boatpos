package org.boatpos.dao.core;

import org.boatpos.dao.api.BoatDao;
import org.boatpos.model.Boat;

import javax.enterprise.context.Dependent;

@Dependent
public class BoatDaoCore extends GenericDaoCore<Boat> implements BoatDao {

    @Override
    public Class<Boat> getType() {
        return Boat.class;
    }
}
