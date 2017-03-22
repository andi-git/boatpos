package org.regkas.domain.api.util;

import org.regkas.domain.api.values.TotalPrice;
import org.regkas.domain.api.values.TotalPriceCent;

public interface TotalPriceEuroToCentConverter {

    TotalPriceCent convert(TotalPrice totalPrice);
}
