package org.regkas.repository.api.values;

import org.boatpos.common.repository.api.values.SimpleValueObject;

/**
 * The SignatureCertifacteSerialNumber of a cash-box.
 */
public class SignatureCertificateSerialNumber extends SimpleValueObject<SignatureCertificateSerialNumber, String> {

    public SignatureCertificateSerialNumber(String value) {
        super(value);
    }
}
