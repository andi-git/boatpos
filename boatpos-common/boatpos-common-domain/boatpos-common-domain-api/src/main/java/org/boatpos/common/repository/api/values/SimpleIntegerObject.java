package org.boatpos.common.repository.api.values;

import java.lang.reflect.ParameterizedType;

/**
 * Abstract simple value object.
 */
public abstract class SimpleIntegerObject<SVO extends SimpleIntegerObject> extends SimpleValueObject<SVO, Integer> {

    public SimpleIntegerObject(Integer value) {
        super(value);
    }

    @Override
    public Integer get() {
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
