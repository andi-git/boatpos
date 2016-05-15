package org.regkas.repository.api.values;

import org.boatpos.common.repository.api.values.SimpleValueObject;

/**
 * The string-representation of the Datenerfassungsprotokoll.
 */
public class DEPString extends SimpleValueObject<DEPString, String> {

    public DEPString(String value) {
        super(value);
    }
}
