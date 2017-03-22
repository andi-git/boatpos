package org.boatpos.repository.api.values;

import org.boatpos.common.repository.api.values.SimpleValueObject;
import org.boatpos.repository.api.model.PromotionBefore;

/**
 * The time-credit of a {@link PromotionBefore}.
 */
public class TimeCredit extends SimpleValueObject<TimeCredit, Integer> {

    public TimeCredit(Integer value) {
        super(value);
    }
}
