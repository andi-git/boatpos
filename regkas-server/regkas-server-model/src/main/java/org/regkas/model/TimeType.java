package org.regkas.model;

import java.util.Optional;

/**
 * The type of the tome for the receipt.
 */
public enum TimeType {

    Current("current"),
    Month("month"),
    Year("year");

    private String string;

    TimeType(String string) {
        this.string = string;
    }

    public static TimeType get(String timeTypeString) {
        return getOrNull(timeTypeString).orElse(Current);
    }

    @Override
    public String toString() {
        return string;
    }

    public static Optional<TimeType> getOrNull(String timeTypeString) {
        Optional<TimeType> result = Optional.empty();
        for (TimeType timeType : values()) {
            if (timeType.string.equalsIgnoreCase(timeTypeString)) {
                result = Optional.of(timeType);
                break;
            }
        }
        return result;
    }
}
