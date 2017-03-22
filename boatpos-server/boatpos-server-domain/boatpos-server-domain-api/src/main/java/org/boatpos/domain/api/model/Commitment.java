package org.boatpos.domain.api.model;

import org.boatpos.common.domain.api.model.MasterDataWithDto;
import org.boatpos.domain.api.values.Name;
import org.boatpos.domain.api.values.Paper;
import org.boatpos.model.CommitmentEntity;
import org.boatpos.service.api.bean.CommitmentBean;

/**
 * The domain model for a commitment.
 */
public interface Commitment extends MasterDataWithDto<Commitment, CommitmentEntity, CommitmentBean>, ContainsRentals<Commitment> {

    Name getName();

    Commitment setName(Name name);

    Paper needPaper();

    Commitment setPaper(Paper paper);
}
