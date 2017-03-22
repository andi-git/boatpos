package org.boatpos.domain.api.values;

import org.boatpos.common.domain.api.values.SimpleBooleanObject;
import org.boatpos.domain.api.model.Rental;

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
