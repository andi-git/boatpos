package org.boatpos.repository.api.model;

import org.boatpos.model.CommitmentEntity;
import org.boatpos.repository.api.values.Name;
import org.boatpos.repository.api.values.Paper;
import org.boatpos.service.api.bean.CommitmentBean;

import java.util.Set;

/**
 * The domain model for a commitment.
 */
public interface Commitment extends MasterData<Commitment, CommitmentEntity, CommitmentBean>, ContainsRentals<Commitment> {

    Name getName();

    Commitment setName(Name name);

    Paper needPaper();

    Commitment setPaper(Paper paper);
}
