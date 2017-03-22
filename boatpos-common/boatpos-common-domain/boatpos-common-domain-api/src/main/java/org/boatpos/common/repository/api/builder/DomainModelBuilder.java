package org.boatpos.common.repository.api.builder;

import org.boatpos.common.model.AbstractEntity;
import org.boatpos.common.repository.api.model.DomainModel;
import org.boatpos.common.repository.api.values.DomainId;
import org.boatpos.common.repository.api.values.Version;

/**
 * Builder for {@link DomainModel}.
 */
public interface DomainModelBuilder<BUILDER extends DomainModelBuilder, MODEL extends DomainModel, ENTITY extends AbstractEntity>
        extends
            ModelBuilder<MODEL> {

    MODEL from(ENTITY entity);

    BUILDER add(DomainId id);

    BUILDER add(Version version);
}
