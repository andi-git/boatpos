package org.regkas.domain.api.values;

import org.boatpos.common.domain.api.values.SimpleValueObject;

/**
 * The id of a receipt.
 */
public class ReceiptId extends SimpleValueObject<ReceiptId, String> {

    public ReceiptId(String value) {
        super(value);
    }

    public ReceiptId(int year, int number) {
        super(createId(year, number));
    }

    public int getYear() {
        return Integer.valueOf(value.substring(0, 4));
    }

    public int getNumber() {
        return Integer.valueOf(value.substring(5));
    }

    private static String createId(int year, int number) {
        return year + "-" + ppNumber(number, 7);
    }

    private static String ppNumber(int number, int size) {
        String numberAsString = String.valueOf(number);
        for (int i = numberAsString.length(); i < size; i++) {
            numberAsString = "0" + numberAsString;
        }
        return numberAsString;
    }
}
