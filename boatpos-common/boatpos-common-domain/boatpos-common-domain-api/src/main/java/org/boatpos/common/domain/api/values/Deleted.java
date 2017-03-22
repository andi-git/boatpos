package org.boatpos.common.domain.api.values;

/**
 * Flag, if a domain-model is deleted or not.
 */
public class Deleted extends SimpleBooleanObject<Deleted> {

    public static Deleted TRUE = new Deleted(true);

    public static Deleted FALSE = new Deleted(false);

    public Deleted(Boolean value) {
        super(value);
    }
}
