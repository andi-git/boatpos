package org.regkas.service.core.journal;

public class SystemJournalEvent {

    private final String message;

    public SystemJournalEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
