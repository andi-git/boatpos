package org.boatpos.dao.core;

import org.boatpos.dao.api.CommitmentDao;
import org.boatpos.model.Commitment;

import javax.enterprise.context.Dependent;
import java.util.List;
import java.util.Optional;

@Dependent
public class CommitmentDaoCore extends GenericDaoCore<Commitment> implements CommitmentDao {

    @Override
    public Class<Commitment> getType() {
        return Commitment.class;
    }

    @Override
    public Optional<Commitment> getByName(String name) {
        return getSingleResult(createNamedQuery("commitment.getByName")
                .setParameter("name", name));
    }

    @Override
    public List<Commitment> getAll() {
        return createNamedQuery("commitment.getAll").getResultList();
    }
}
