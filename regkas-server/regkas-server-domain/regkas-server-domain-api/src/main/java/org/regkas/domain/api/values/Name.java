package org.regkas.domain.api.values;

import org.boatpos.common.domain.api.model.DomainModel;
import org.boatpos.common.domain.api.values.SimpleValueObject;

/**
 * A good listening (unique) name of a {@link DomainModel}.
 */
public class Name extends SimpleValueObject<Name, String> {

    public Name(String value) {
        super(value);
    }
}
