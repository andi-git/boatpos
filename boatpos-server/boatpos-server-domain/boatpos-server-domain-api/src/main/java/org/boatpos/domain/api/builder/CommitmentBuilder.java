package org.boatpos.domain.api.builder;

import org.boatpos.common.domain.api.builder.MasterDataBuilderWithDto;
import org.boatpos.domain.api.model.Rental;
import org.boatpos.domain.api.values.Paper;
import org.boatpos.model.CommitmentEntity;
import org.boatpos.domain.api.model.Commitment;
import org.boatpos.domain.api.values.Name;
import org.boatpos.service.api.bean.CommitmentBean;

import java.util.Set;

/**
 * Builder for {@link Commitment}.
 */
public interface CommitmentBuilder extends MasterDataBuilderWithDto<CommitmentBuilder, Commitment, CommitmentEntity, CommitmentBean> {

    CommitmentBuilder add(Name name);

    CommitmentBuilder add(Paper paper);

    CommitmentBuilder add(Set<Rental> rentals);

    CommitmentBuilder add(Rental rental);
}
