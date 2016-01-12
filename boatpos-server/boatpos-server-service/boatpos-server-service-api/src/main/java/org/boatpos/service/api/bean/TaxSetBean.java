package org.boatpos.service.api.bean;

import java.math.BigDecimal;

public abstract class TaxSetBean {

    private String name;

    private BigDecimal value;

    private int amount;

    public TaxSetBean() {
    }

    public TaxSetBean(String name, BigDecimal value, int amount) {
        this.name = name;
        this.value = value;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public static class TaxSetNormalBean extends TaxSetBean {

        public TaxSetNormalBean() {
        }

        public TaxSetNormalBean(String name, BigDecimal value, int amount) {
            super(name, value, amount);
        }
    }

    public static class TaxSetErmaessigt1Bean extends TaxSetBean {

        public TaxSetErmaessigt1Bean() {
        }

        public TaxSetErmaessigt1Bean(String name, BigDecimal value, int amount) {
            super(name, value, amount);
        }
    }

    public static class TaxSetErmaessigt2Bean extends TaxSetBean {

        public TaxSetErmaessigt2Bean() {
        }

        public TaxSetErmaessigt2Bean(String name, BigDecimal value, int amount) {
            super(name, value, amount);
        }
    }

    public static class TaxSetBesonderesBean extends TaxSetBean {

        public TaxSetBesonderesBean() {
        }

        public TaxSetBesonderesBean(String name, BigDecimal value, int amount) {
            super(name, value, amount);
        }
    }

    public static class TaxSetNullBean extends TaxSetBean {

        public TaxSetNullBean() {
        }

        public TaxSetNullBean(String name, BigDecimal value, int amount) {
            super(name, value, amount);
        }
    }
}
