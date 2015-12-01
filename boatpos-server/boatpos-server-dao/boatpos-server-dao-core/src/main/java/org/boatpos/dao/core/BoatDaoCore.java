package org.boatpos.dao.core;

import org.boatpos.dao.api.BoatDao;
import org.boatpos.model.Boat;

import javax.enterprise.context.Dependent;
import java.util.List;
import java.util.Optional;

@Dependent
public class BoatDaoCore extends GenericDaoCore<Boat> implements BoatDao {

    @Override
    public Class<Boat> getType() {
        return Boat.class;
    }

    @Override
    public Optional<Boat> getByName(String name) {
        return getSingleResult(createNamedQuery("boat.getByName")
                .setParameter("name", name));
    }

    @Override
    public Optional<Boat> getByShortName(String shortName) {
        return getSingleResult(createNamedQuery("boat.getByShortName")
                .setParameter("shortName", shortName));
    }

    @Override
    public List<Boat> getAll() {
        return createNamedQuery("boat.getAll").getResultList();
    }
}
