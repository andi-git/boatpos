package org.boatpos.common.domain.api.values;

import org.boatpos.common.domain.api.model.DomainModel;

/**
 * The version for optimistic locking of a {@link DomainModel}.
 */
public class Version extends SimpleValueObject<Version, Integer> {

    public Version(Integer value) {
        super(value);
    }
}
