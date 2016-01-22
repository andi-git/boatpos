package org.boatpos.repository.api.values;

import org.boatpos.repository.api.model.DomainModel;

/**
 * A good listening (unique) name of a {@link DomainModel}.
 */
public class Name extends SimpleValueObject<Name, String> {

    public Name(String value) {
        super(value);
    }
}
