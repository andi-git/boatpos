package org.boatpos.common.repository.api.values;

import java.math.BigDecimal;

/**
 * Abstract simple value object.
 */
public abstract class SimpleBigDecimalObject<SVO extends SimpleBigDecimalObject> extends SimpleValueObject<SVO, BigDecimal> {

    public static final BigDecimal ZERO = new BigDecimal("0.00");

    public SimpleBigDecimalObject(BigDecimal value) {
        super(value);
    }

    public SimpleBigDecimalObject(String value) {
        this(new BigDecimal(value));
    }

    @Override
    public BigDecimal get() {
        return value == null ? ZERO : value;
    }

    /**
     * Create a new instance with the added values.
     *
     * @param valueToAdd the value to add
     * @return a new instance where the value is added.
     */
    public SVO add(SVO valueToAdd) {
        return newInstance(get().add(valueToAdd.get()));
    }

    @Override
    public String asStringToBeSigned() {
        return String.valueOf(value).replaceAll("\\.", ",");
    }
}
