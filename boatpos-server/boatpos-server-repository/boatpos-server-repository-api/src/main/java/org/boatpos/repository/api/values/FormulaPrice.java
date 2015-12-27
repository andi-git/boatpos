package org.boatpos.repository.api.values;

import org.boatpos.repository.api.model.DomainModel;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * The formula for the calculation of a price {@link DomainModel}.
 */
public class FormulaPrice extends SimpleValueObject<FormulaPrice, String> {

    public FormulaPrice(String value) {
        super(value);
    }
}
