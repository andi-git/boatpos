package org.boatpos.repository.core.repository;

import org.boatpos.common.repository.core.respository.MasterDataRepositoryCore;
import org.boatpos.model.CommitmentEntity;
import org.boatpos.repository.api.builder.CommitmentBuilder;
import org.boatpos.repository.api.model.Commitment;
import org.boatpos.repository.api.repository.CommitmentRepository;
import org.boatpos.repository.api.values.Name;
import org.boatpos.repository.core.builder.CommitmentBuilderCore;
import org.boatpos.repository.core.model.CommitmentCore;

import javax.enterprise.context.Dependent;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

@Dependent
public class CommitmentRepositoryCore extends MasterDataRepositoryCore<Commitment, CommitmentCore, CommitmentEntity, CommitmentBuilder, CommitmentBuilderCore> implements CommitmentRepository {

    @Override
    public Optional<Commitment> loadBy(Name name) {
        checkNotNull(name, "'name' must not be null");
        return loadByParameter("commitment.getByName", (query) -> query.setParameter("name", name.get()));
    }

    @Override
    protected String namedQueryPrefix() {
        return "commitment";
    }
}
