package org.boatpos.domain.core.repository;

import org.boatpos.common.domain.core.respository.MasterDataRepositoryCore;
import org.boatpos.domain.core.builder.CommitmentBuilderCore;
import org.boatpos.model.CommitmentEntity;
import org.boatpos.domain.api.builder.CommitmentBuilder;
import org.boatpos.domain.api.model.Commitment;
import org.boatpos.domain.api.repository.CommitmentRepository;
import org.boatpos.domain.api.values.Name;
import org.boatpos.domain.core.model.CommitmentCore;

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
