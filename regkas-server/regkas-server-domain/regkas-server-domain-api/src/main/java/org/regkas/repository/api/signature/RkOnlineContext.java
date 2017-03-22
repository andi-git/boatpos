package org.regkas.repository.api.signature;

import org.regkas.repository.api.values.RkOnlinePassword;
import org.regkas.repository.api.values.RkOnlineSession;
import org.regkas.repository.api.values.RkOnlineUsername;

import java.util.Optional;

/**
 * Context for the environment of RK Online.
 */
public interface RkOnlineContext {

    void setEnvironment(Environment environment);

    Environment getEnvironment();

    RkOnlineUsername getRkOnlineUsername();

    RkOnlinePassword getRkOnlinePassword();

    void setSession(RkOnlineSession session);

    Optional<RkOnlineSession.Id> getRkOnlineSessionId();

    Optional<RkOnlineSession.Key> getRkOnlineSessionKey();

    void action();

    Optional<RkOnlineSession.LastAction> getLastAction();

    void resetSessions();
}
