package org.regkas.repository.api.values;

import org.boatpos.common.repository.api.values.SimpleValueObject;

import java.time.LocalDateTime;

public class RkOnlineSession {

    private final Id id;

    private final Key key;

    private LastAction lastAction;

    public RkOnlineSession(Id id, Key key, LastAction lastAction) {
        this.id = id;
        this.key = key;
        this.lastAction = lastAction;
    }

    public Id getId() {
        return id;
    }

    public Key getKey() {
        return key;
    }

    public LastAction getLastAction() {
        return lastAction;
    }

    public void setLastAction(LastAction lastAction) {
        this.lastAction = lastAction;
    }

    public static class LastAction {

        private final LocalDateTime dateTime;

        public LastAction(LocalDateTime dateTime) {
            this.dateTime = dateTime;
        }

        public LocalDateTime getDateTime() {
            return dateTime;
        }
    }

    public static class Id extends SimpleValueObject<Id, String> {

        public Id(String id) {
            super(id);
        }
    }

    public static class Key extends SimpleValueObject<Key, String> {

        public Key(String key) {
            super(key);
        }
    }
}
