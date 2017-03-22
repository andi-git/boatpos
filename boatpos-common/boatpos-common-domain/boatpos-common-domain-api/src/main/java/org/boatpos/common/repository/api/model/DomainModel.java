package org.boatpos.common.repository.api.model;

import org.boatpos.common.model.AbstractEntity;
import org.boatpos.common.repository.api.values.DomainId;
import org.boatpos.common.repository.api.values.Version;

public interface DomainModel<MODEL extends DomainModel, ENTITY extends AbstractEntity> extends Model<MODEL, ENTITY> {

    DomainId getId();

    Version getVersion();
}
