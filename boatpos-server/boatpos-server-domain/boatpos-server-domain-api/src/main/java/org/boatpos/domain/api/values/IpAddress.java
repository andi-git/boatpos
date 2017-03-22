package org.boatpos.domain.api.values;

import org.boatpos.common.domain.api.values.SimpleValueObject;

/**
 * An ip-address.
 */
public class IpAddress extends SimpleValueObject<IpAddress, String> {

    public IpAddress(String value) {
        super(value);
    }
}
