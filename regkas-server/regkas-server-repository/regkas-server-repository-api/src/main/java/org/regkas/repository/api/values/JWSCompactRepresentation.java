package org.regkas.repository.api.values;

import org.boatpos.common.repository.api.values.SimpleValueObject;

/**
 * The compact representation of JSON Web Signature.
 */
public class JWSCompactRepresentation extends SimpleValueObject<JWSCompactRepresentation, String> {

    public JWSCompactRepresentation(String value) {
        super(value);
    }
}
