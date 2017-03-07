package org.regkas.repository.core.signature;

import java.util.function.Function;

import org.boatpos.common.repository.api.values.SimpleValueObject;
import org.regkas.repository.api.signature.RkOnlineContext;

public enum RkOnlineRestResource {

        SignJWS((rkOnlineContext) -> "Session/" + SimpleValueObject.nullSafe(rkOnlineContext.getRkOnlineSessionId().get()) + "/Sign/JWS"),
        Session((rkOnlineContext) -> "Session/" + SimpleValueObject.nullSafe(rkOnlineContext.getRkOnlineUsername())),
        Certificate((rkOnlineContext) -> SimpleValueObject.nullSafe(rkOnlineContext.getRkOnlineUsername()) + "/Certificate");

    private final Function<RkOnlineContext, String> subPath;

    RkOnlineRestResource(Function<RkOnlineContext, String> subPath) {
        this.subPath = subPath;
    }

    public String getURL(RkOnlineContext rkOnlineContext) {
        return "https://" + rkOnlineContext.getEnvironment().getDomain().get() + "/asignrkonline/v2/" + subPath.apply(rkOnlineContext);
    }
}
