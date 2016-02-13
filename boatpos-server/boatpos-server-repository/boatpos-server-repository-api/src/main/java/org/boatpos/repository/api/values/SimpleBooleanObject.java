package org.boatpos.repository.api.values;

/**
 * Abstract simple value object.
 */
public abstract class SimpleBooleanObject<SVO extends SimpleBooleanObject> extends SimpleValueObject<SVO, Boolean> {

    public SimpleBooleanObject(Boolean value) {
        super(value);
    }

    public Boolean get() {
        return value == null ? Boolean.FALSE : value;
    }
}
