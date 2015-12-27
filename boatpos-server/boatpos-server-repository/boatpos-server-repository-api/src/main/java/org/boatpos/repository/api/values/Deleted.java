package org.boatpos.repository.api.values;

import org.boatpos.repository.api.model.Rental;

/**
 * Flag, if a {@link Rental} is deleted or not.
 */
public class Deleted extends SimpleValueObject<Deleted, Boolean> {

    public static Deleted TRUE = new Deleted(true);

    public static Deleted FALSE = new Deleted(false);

    public Deleted(Boolean value) {
        super(value);
    }
}
