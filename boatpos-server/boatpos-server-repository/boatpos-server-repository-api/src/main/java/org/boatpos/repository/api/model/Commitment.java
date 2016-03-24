package org.boatpos.repository.api.model;

import org.boatpos.common.repository.api.model.MasterData;
import org.boatpos.common.repository.api.model.MasterDataWithDto;
import org.boatpos.model.CommitmentEntity;
import org.boatpos.repository.api.values.Name;
import org.boatpos.repository.api.values.Paper;
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
