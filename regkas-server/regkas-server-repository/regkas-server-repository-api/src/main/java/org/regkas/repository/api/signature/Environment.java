package org.regkas.repository.api.signature;

import org.regkas.repository.api.values.RkOnlineDomain;

public enum Environment {

    TEST("hs-abnahme"),
    PROD("www");

    private final String domainPrefix;

    Environment(String domainPrefix) {
        this.domainPrefix = domainPrefix;
    }

    public RkOnlineDomain getDomain() {
        return new RkOnlineDomain(domainPrefix + ".a-trust.at");
    }

}
