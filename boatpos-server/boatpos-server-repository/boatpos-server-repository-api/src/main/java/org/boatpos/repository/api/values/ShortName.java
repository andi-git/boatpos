package org.boatpos.repository.api.values;

import org.boatpos.common.repository.api.model.DomainModel;
import org.boatpos.common.repository.api.values.SimpleValueObject;

/**
 * The short name of a {@link DomainModel}.
 */
public class ShortName extends SimpleValueObject<ShortName, String> {

    public ShortName(String value) {
        super(value);
    }
}
