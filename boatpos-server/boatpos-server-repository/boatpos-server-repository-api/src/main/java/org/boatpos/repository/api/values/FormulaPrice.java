package org.boatpos.repository.api.values;

import org.boatpos.common.repository.api.model.DomainModel;
import org.boatpos.common.repository.api.values.SimpleValueObject;

/**
 * The formula for the calculation of a price {@link DomainModel}.
 */
public class FormulaPrice extends SimpleValueObject<FormulaPrice, String> {

    public FormulaPrice(String value) {
        super(value);
    }
}
