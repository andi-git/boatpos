package org.boatpos.service.core.mapping;

import org.boatpos.model.Boat;
import org.boatpos.model.Commitment;
import org.boatpos.service.api.bean.BoatBean;
import org.boatpos.service.api.bean.CommitmentBean;

import javax.enterprise.context.Dependent;

/**
 * Mapping between {@link Commitment} and {@link CommitmentBean}.
 */
@Dependent
public class CommitmentMapping extends Mapping<Commitment, CommitmentBean> {

    @Override
    protected Class<CommitmentBean> getMappedDtoClass() {
        return CommitmentBean.class;
    }

    @Override
    protected Class<Commitment> getMappedEntityClass() {
        return Commitment.class;
    }
}
