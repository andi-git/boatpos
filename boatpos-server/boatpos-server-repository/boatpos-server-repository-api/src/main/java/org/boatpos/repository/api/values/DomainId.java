package org.boatpos.repository.api.values;

import org.boatpos.repository.api.model.DomainModel;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * A good listening (unique) name of a {@link DomainModel}.
 */
public class DomainId extends SimpleValueObject<DomainId, Long> {

    public DomainId(Long value) {
        super(value);
    }
}
