package org.regkas.domain.api.values;

import org.boatpos.common.domain.api.values.SimpleValueObject;

/**
 * The string-representation of the Datenerfassungsprotokoll.
 */
public class DEPString extends SimpleValueObject<DEPString, String> {

    public DEPString(String value) {
        super(value);
    }
}
