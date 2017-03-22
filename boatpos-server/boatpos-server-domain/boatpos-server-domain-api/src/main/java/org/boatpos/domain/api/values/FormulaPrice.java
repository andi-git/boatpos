package org.boatpos.domain.api.values;

import org.boatpos.common.domain.api.model.DomainModel;
import org.boatpos.common.domain.api.values.SimpleValueObject;

/**
 * The formula for the calculation of a price {@link DomainModel}.
 */
public class FormulaPrice extends SimpleValueObject<FormulaPrice, String> {

    public FormulaPrice(String value) {
        super(value);
    }
}
