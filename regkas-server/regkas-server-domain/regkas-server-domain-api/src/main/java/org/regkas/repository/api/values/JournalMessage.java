package org.regkas.repository.api.values;

import org.boatpos.common.repository.api.values.SimpleValueObject;

public class JournalMessage extends SimpleValueObject<JournalMessage, String> {

    public JournalMessage(String value) {
        super(value);
    }
}
