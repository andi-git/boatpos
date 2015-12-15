package org.boatpos.dao.core;

import org.boatpos.dao.api.CommitmentDao;
import org.boatpos.model.Commitment;

import javax.enterprise.context.Dependent;
import java.util.Optional;

@Dependent
public class CommitmentDaoCore extends GenericMasterDataDaoCore<Commitment> implements CommitmentDao {

    @Override
    public Class<Commitment> getType() {
        return Commitment.class;
    }

    @Override
    public Optional<Commitment> getByName(String name) {
        return getSingleResult(createTypedNamedQuery("commitment.getByName")
                .setParameter("name", name));
    }

    @Override
    protected String nameForGetAll() {
        return "commitment.getAll";
    }

    @Override
    protected String nameForGetAllEnabled() {
        return "commitment.getAllEnabled";
    }

    @Override
    protected String nameForGetAllDisabled() {
        return "commitment.getAllDisabled";
    }
}
