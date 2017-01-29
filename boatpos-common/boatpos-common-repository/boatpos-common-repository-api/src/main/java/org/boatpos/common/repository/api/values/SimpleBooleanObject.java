package org.boatpos.common.repository.api.values;

/**
 * Abstract simple value object.
 */
public abstract class SimpleBooleanObject<SVO extends SimpleBooleanObject> extends SimpleValueObject<SVO, Boolean> {

    public SimpleBooleanObject(Boolean value) {
        super(value);
    }

    @Override
    public Boolean get() {
        return value == null ? Boolean.FALSE : value;
    }
}
