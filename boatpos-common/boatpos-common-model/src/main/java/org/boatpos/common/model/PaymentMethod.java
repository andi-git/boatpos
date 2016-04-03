package org.boatpos.common.model;

/**
 * The method of the payment.
 */
public enum PaymentMethod {

    Cash("cash"),
    Card("card");

    private final String string;

    PaymentMethod(String string) {
        this.string = string;
    }

    public static PaymentMethod get(String paymentMethodString) {
        PaymentMethod result = getOrNull(paymentMethodString);
        return result != null ? result : PaymentMethod.Cash;
    }

    @Override
    public String toString() {
        return string;
    }

    public static PaymentMethod getOrNull(String paymentMethodString) {
        PaymentMethod result = null;
        for (PaymentMethod paymentMethod : values()) {
            if (paymentMethod.string.equalsIgnoreCase(paymentMethodString)) {
                result = paymentMethod;
                break;
            }
        }
        return result;
    }
}
