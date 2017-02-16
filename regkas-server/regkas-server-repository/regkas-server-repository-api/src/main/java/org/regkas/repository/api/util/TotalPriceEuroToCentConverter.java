package org.regkas.repository.api.util;

import org.regkas.repository.api.values.TotalPrice;
import org.regkas.repository.api.values.TotalPriceCent;

public interface TotalPriceEuroToCentConverter {

    TotalPriceCent convert(TotalPrice totalPrice);
}
