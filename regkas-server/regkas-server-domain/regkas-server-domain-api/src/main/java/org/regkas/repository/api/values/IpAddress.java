package org.regkas.repository.api.values;

import org.boatpos.common.repository.api.values.SimpleValueObject;

/**
 * An ip-address.
 */
public class IpAddress extends SimpleValueObject<IpAddress, String> {

    public IpAddress(String value) {
        super(value);
    }
}
