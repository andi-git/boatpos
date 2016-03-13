package org.boatpos.common.repository.api.values;

import org.boatpos.common.repository.api.model.DomainModel;

/**
 * The version for optimistic locking of a {@link DomainModel}.
 */
public class Version extends SimpleValueObject<Version, Integer> {

    public Version(Integer value) {
        super(value);
    }
}
