package org.regkas.repository.api.values;

import org.boatpos.common.repository.api.values.SimpleValueObject;

/**
 * The suite-id.
 */
public class SuiteId extends SimpleValueObject<SuiteId, String> {

    public SuiteId(String value) {
        super(value);
    }

    public SuiteId(CertificationServiceProvider certificationServiceProvider) {
        this("R1-" + nullSafe(certificationServiceProvider));
    }
}
