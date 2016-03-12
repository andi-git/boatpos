package org.boatpos.repository.api.values;

import org.boatpos.common.repository.api.values.SimpleValueObject;
import org.boatpos.repository.api.model.Boat;
import org.boatpos.repository.api.model.PromotionBefore;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * The time-credit of a {@link PromotionBefore}.
 */
public class TimeCredit extends SimpleValueObject<TimeCredit, Integer> {

    public TimeCredit(Integer value) {
        super(value);
    }
}
