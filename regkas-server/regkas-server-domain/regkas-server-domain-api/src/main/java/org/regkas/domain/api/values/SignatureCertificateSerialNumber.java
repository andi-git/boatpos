package org.regkas.domain.api.values;

import org.boatpos.common.domain.api.values.SimpleValueObject;

/**
 * The SignatureCertifacteSerialNumber of a cash-box.
 */
public class SignatureCertificateSerialNumber extends SimpleValueObject<SignatureCertificateSerialNumber, String> {

    public SignatureCertificateSerialNumber(String value) {
        super(value);
    }
}
