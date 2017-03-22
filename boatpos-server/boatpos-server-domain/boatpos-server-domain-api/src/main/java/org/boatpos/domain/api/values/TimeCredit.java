package org.boatpos.domain.api.values;

import org.boatpos.common.domain.api.values.SimpleValueObject;
import org.boatpos.domain.api.model.PromotionBefore;

/**
 * The time-credit of a {@link PromotionBefore}.
 */
public class TimeCredit extends SimpleValueObject<TimeCredit, Integer> {

    public TimeCredit(Integer value) {
        super(value);
    }
}
