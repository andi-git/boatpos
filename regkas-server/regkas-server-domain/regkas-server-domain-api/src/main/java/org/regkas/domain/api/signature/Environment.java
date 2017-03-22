package org.regkas.domain.api.signature;

import org.regkas.domain.api.values.RkOnlineDomain;

public enum Environment {

        TEST("hs-abnahme", "test"),
        PROD("www", "prod");

    private final String domainPrefix;

    private final String name;

    Environment(String domainPrefix, String name) {
        this.domainPrefix = domainPrefix;
        this.name = name;
    }

    public RkOnlineDomain getDomain() {
        return new RkOnlineDomain(domainPrefix + ".a-trust.at");
    }

    public String getName() {
        return name;
    }

    public static Environment get(String environment) {
        if (PROD.getName().equalsIgnoreCase(environment)) {
            return PROD;
        } else {
            return TEST;
        }
    }
}
