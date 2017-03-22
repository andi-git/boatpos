package org.boatpos.repository.api.builder;

import org.boatpos.common.repository.api.builder.MasterDataBuilder;
import org.boatpos.common.repository.api.builder.MasterDataBuilderWithDto;
import org.boatpos.model.CommitmentEntity;
import org.boatpos.repository.api.model.Commitment;
import org.boatpos.repository.api.model.Rental;
import org.boatpos.repository.api.values.Name;
import org.boatpos.repository.api.values.Paper;
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
