package org.regkas.domain.api.values;

import org.boatpos.common.domain.api.values.SimpleBooleanObject;

/**
 * Flag, if the signature device is available or not.
 */
public class SignatureDeviceAvailable extends SimpleBooleanObject<SignatureDeviceAvailable> {

    public static SignatureDeviceAvailable TRUE = new SignatureDeviceAvailable(true);

    public static SignatureDeviceAvailable FALSE = new SignatureDeviceAvailable(false);

    public SignatureDeviceAvailable(Boolean value) {
        super(value);
    }
}
