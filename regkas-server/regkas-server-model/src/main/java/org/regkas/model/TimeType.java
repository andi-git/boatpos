package org.regkas.model;

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
        TimeType result = getOrNull(timeTypeString);
        return result != null ? result : TimeType.Current;
    }

    @Override
    public String toString() {
        return string;
    }

    public static TimeType getOrNull(String timeTypeString) {
        TimeType result = null;
        for (TimeType timeType : values()) {
            if (timeType.string.equalsIgnoreCase(timeTypeString)) {
                result = timeType;
                break;
            }
        }
        return result;
    }
}
