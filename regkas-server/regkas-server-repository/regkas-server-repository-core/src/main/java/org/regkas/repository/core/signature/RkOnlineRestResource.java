package org.regkas.repository.core.signature;

import org.boatpos.common.repository.api.values.SimpleValueObject;
import org.regkas.repository.api.signature.RkOnlineContext;

import java.util.function.Function;

public enum RkOnlineRestResource {

    SignJWS((rkOnlineContext) -> "Session/" + SimpleValueObject.nullSafe(rkOnlineContext.getRkOnlineSessionId().get()) + "/Sign/JWS"),
    Session((rkOnlineContext) -> "Session/" + SimpleValueObject.nullSafe(rkOnlineContext.getRkOnlineUsername()));

    private final Function<RkOnlineContext, String> subPath;

    RkOnlineRestResource(Function<RkOnlineContext, String> subPath) {
        this.subPath = subPath;
    }

    public String getURL(RkOnlineContext rkOnlineContext) {
        return "https://" + rkOnlineContext.getEnvironment().getDomain().get() + "/asignrkonline/v2/" + subPath.apply(rkOnlineContext);
    }
}
