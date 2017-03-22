package org.boatpos.repository.api.values;

import org.boatpos.common.repository.api.values.SimpleBooleanObject;
import org.boatpos.repository.api.model.Rental;

/**
 * Flag, if a {@link Rental} is finished or not.
 */
public class Finished extends SimpleBooleanObject<Finished> {

    public static Finished TRUE = new Finished(true);

    public static Finished FALSE = new Finished(false);

    public Finished(Boolean value) {
        super(value);
    }
}
