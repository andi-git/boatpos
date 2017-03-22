package org.boatpos.common.domain.api.values;

import org.boatpos.common.domain.api.model.DomainModel;

/**
 * A good listening (unique) name of a {@link DomainModel}.
 */
public class DomainId extends SimpleValueObject<DomainId, Long> {

    public DomainId(Long value) {
        super(value);
    }
}
