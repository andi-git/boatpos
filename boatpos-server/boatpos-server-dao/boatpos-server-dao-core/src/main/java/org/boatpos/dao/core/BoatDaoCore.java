package org.boatpos.dao.core;

import org.boatpos.dao.api.BoatDao;
import org.boatpos.model.Boat;

import javax.enterprise.context.Dependent;
import java.util.List;
import java.util.Optional;

@Dependent
public class BoatDaoCore extends GenericMasterDataDaoCore<Boat> implements BoatDao {

    @Override
    public Class<Boat> getType() {
        return Boat.class;
    }

    @Override
    public Optional<Boat> getByName(String name) {
        return getSingleResult(createTypedNamedQuery("boat.getByName")
                .setParameter("name", name));
    }

    @Override
    public Optional<Boat> getByShortName(String shortName) {
        return getSingleResult(createTypedNamedQuery("boat.getByShortName")
                .setParameter("shortName", shortName));
    }

    @Override
    public List<Boat> getAll() {
        return createTypedNamedQuery("boat.getAll").getResultList();
    }

    @Override
    public List<Boat> getAllEnabled() {
        return createTypedNamedQuery("boat.getAllEnabled").getResultList();
    }

    @Override
    public List<Boat> getAllDisabled() {
        return createTypedNamedQuery("boat.getAllDisabled").getResultList();
    }

    @Override
    protected String nameForGetAll() {
        return "boat.getAll";
    }

    @Override
    protected String nameForGetAllEnabled() {
        return "boat.getAllEnabled";
    }

    @Override
    protected String nameForGetAllDisabled() {
        return "boat.getAllDisabled";
    }
}
