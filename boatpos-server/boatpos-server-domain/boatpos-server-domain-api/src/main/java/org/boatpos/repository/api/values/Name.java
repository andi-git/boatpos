package org.boatpos.repository.api.values;

import org.boatpos.common.repository.api.model.DomainModel;
import org.boatpos.common.repository.api.values.SimpleValueObject;

/**
 * A good listening (unique) name of a {@link DomainModel}.
 */
public class Name extends SimpleValueObject<Name, String> {

    public Name(String value) {
        super(value);
    }
}
