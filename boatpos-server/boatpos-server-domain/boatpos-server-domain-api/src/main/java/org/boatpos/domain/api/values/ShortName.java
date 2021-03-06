package org.boatpos.domain.api.values;

import org.boatpos.common.domain.api.model.DomainModel;
import org.boatpos.common.domain.api.values.SimpleValueObject;

/**
 * The short name of a {@link DomainModel}.
 */
public class ShortName extends SimpleValueObject<ShortName, String> {

    public ShortName(String value) {
        super(value);
    }
}
