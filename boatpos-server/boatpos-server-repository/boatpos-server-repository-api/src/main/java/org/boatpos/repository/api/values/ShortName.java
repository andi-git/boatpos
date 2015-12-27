package org.boatpos.repository.api.values;

import org.boatpos.repository.api.model.DomainModel;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * The short name of a {@link DomainModel}.
 */
public class ShortName extends SimpleValueObject<ShortName, String> {

    public ShortName(String value) {
        super(value);
    }
}
