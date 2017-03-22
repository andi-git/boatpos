package org.regkas.domain.api.values;

import org.boatpos.common.domain.api.values.SimpleValueObject;

public class JournalMessage extends SimpleValueObject<JournalMessage, String> {

    public JournalMessage(String value) {
        super(value);
    }
}
