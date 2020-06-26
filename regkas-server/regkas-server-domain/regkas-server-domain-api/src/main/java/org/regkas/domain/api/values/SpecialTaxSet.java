package org.regkas.domain.api.values;

import org.boatpos.common.domain.api.values.SimpleBooleanObject;

/**
 * Defines if is is a special tax-set.
 */
public class SpecialTaxSet extends SimpleBooleanObject<SpecialTaxSet> {

    public static SpecialTaxSet TRUE = new SpecialTaxSet(true);

    public static SpecialTaxSet FALSE = new SpecialTaxSet(false);

    public SpecialTaxSet(Boolean value) {
        super(value);
    }
}
