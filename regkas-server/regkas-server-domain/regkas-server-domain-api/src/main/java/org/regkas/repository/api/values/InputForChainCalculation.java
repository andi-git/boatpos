package org.regkas.repository.api.values;

import org.boatpos.common.repository.api.values.SimpleValueObject;

/**
 * The input for the chain-calculation.
 */
public class InputForChainCalculation extends SimpleValueObject<InputForChainCalculation, String> {

    public InputForChainCalculation(String value) {
        super(value);
    }
}
