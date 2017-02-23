package org.regkas.repository.core.signature;

import org.boatpos.common.repository.api.values.SimpleValueObject;
import org.regkas.repository.api.signature.RkOnlineContext;
import org.regkas.repository.api.values.RkOnlineUsername;

import java.util.function.Function;

public enum RkOnlineRestRessource {

    SignJWS((username) -> SimpleValueObject.nullSafe(username) + "/Sign/JWS"),
    Session((username) -> "Session/" + SimpleValueObject.nullSafe(username));

    private final Function<RkOnlineUsername, String> subPath;

    RkOnlineRestRessource(Function<RkOnlineUsername, String> subPath) {
        this.subPath = subPath;
    }

    public String getURL(RkOnlineContext rkOnlineContext) {
        return "https://" + rkOnlineContext.getEnvironment().getDomain().get() + "/asignrkonline/v2/" + subPath.apply(rkOnlineContext.getRkOnlineUsername());
    }


}
