package org.boatpos.common.domain.api.model;

import org.boatpos.common.model.AbstractEntity;
import org.boatpos.common.domain.api.values.DomainId;
import org.boatpos.common.domain.api.values.Version;

public interface DomainModel<MODEL extends DomainModel, ENTITY extends AbstractEntity> extends Model<MODEL, ENTITY> {

    DomainId getId();

    Version getVersion();
}
