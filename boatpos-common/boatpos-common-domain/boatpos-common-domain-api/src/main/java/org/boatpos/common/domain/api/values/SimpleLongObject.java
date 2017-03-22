package org.boatpos.common.domain.api.values;

/**
 * Abstract simple value object.
 */
public abstract class SimpleLongObject<SVO extends SimpleLongObject> extends SimpleValueObject<SVO, Long> {

    public SimpleLongObject(Long value) {
        super(value);
    }

    @Override
    public Long get() {
        return value == null ? 0 : value;
    }

    /**
     * Create a new instance with the added values.
     *
     * @param valueToAdd the value to add
     * @return a new instance where the value is added.
     */
    public SVO add(SVO valueToAdd) {
        return newInstance(get() + valueToAdd.get());
    }

    /**
     * Create a new instance with the subtracted values.
     *
     * @param valueToAdd the value to subtract
     * @return a new instance where the value is subtracted.
     */
    public SVO subtract(SVO valueToAdd) {
        return newInstance(get() - valueToAdd.get());
    }
}
