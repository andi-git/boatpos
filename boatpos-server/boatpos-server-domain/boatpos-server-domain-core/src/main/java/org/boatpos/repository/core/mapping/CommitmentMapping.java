package org.boatpos.repository.core.mapping;

import org.boatpos.common.repository.core.mapping.Mapping;
import org.boatpos.model.CommitmentEntity;
import org.boatpos.service.api.bean.CommitmentBean;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.spi.CDI;

/**
 * Mapping between {@link CommitmentEntity} and {@link CommitmentBean}.
 */
@Dependent
public class CommitmentMapping extends Mapping<CommitmentEntity, CommitmentBean> {

    public static CommitmentMapping fromCDI() {
        return CDI.current().select(CommitmentMapping.class).get();
    }
}
