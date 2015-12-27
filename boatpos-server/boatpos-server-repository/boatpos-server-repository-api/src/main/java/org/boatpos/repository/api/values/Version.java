package org.boatpos.repository.api.values;

import org.boatpos.repository.api.model.DomainModel;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * The version for optimistic locking of a {@link DomainModel}.
 */
public class Version extends SimpleValueObject<Version, Integer> {

    public Version(Integer value) {
        super(value);
    }
}
