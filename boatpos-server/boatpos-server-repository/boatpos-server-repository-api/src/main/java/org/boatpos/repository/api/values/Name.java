package org.boatpos.repository.api.values;

import org.boatpos.repository.api.model.DomainModel;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * A good listening (unique) name of a {@link DomainModel}.
 */
public class Name extends SimpleValueObject<Name, String> {

    public Name(String value) {
        super(value);
    }
}
