package org.regkas.domain.core.signature;

import org.boatpos.common.util.datetime.DateTimeHelper;
import org.regkas.domain.api.context.CashBoxContext;
import org.regkas.domain.api.signature.Environment;
import org.regkas.domain.api.signature.RkOnlineContext;
import org.regkas.domain.api.values.RkOnlinePassword;
import org.regkas.domain.api.values.RkOnlineSession;
import org.regkas.domain.api.values.RkOnlineUsername;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Context for the environment of RK Online.
 */
@ApplicationScoped
public class RkOnlineContextCore implements RkOnlineContext {

    private Environment environment = Environment.PROD;

    @Inject
    private CashBoxContext cashBoxContext;

    @Inject
    private DateTimeHelper dateTimeHelper;

    /**
     * Multiple cash-boxes (within the same company) can share the same rk-online-credentials.
     * So the caching of the session must be application-scoped.
     */
    private final Map<RkOnlineUsername, RkOnlineSession> rkOnlineSessions = new ConcurrentHashMap<>();

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public Environment getEnvironment() {
        String property = System.getProperty("boatpos.regkas.environment");
        if (property != null && !"".equals(property)) {
            return Environment.get(property);
        } else {
            return environment;
        }
    }

    @Override
    public RkOnlineUsername getRkOnlineUsername() {
        return cashBoxContext.get().getRkOnlineUsername();
    }

    @Override
    public RkOnlinePassword getRkOnlinePassword() {
        return cashBoxContext.get().getRkOnlinePassword();
    }

    @Override
    public void setSession(RkOnlineSession session) {
        rkOnlineSessions.put(getRkOnlineUsername(), session);
    }

    @Override
    public Optional<RkOnlineSession.Id> getRkOnlineSessionId() {
        RkOnlineSession rkOnlineSession = rkOnlineSessions.get(getRkOnlineUsername());
        return rkOnlineSession != null ? Optional.of(rkOnlineSession.getId()) : Optional.empty();
    }

    @Override
    public Optional<RkOnlineSession.Key> getRkOnlineSessionKey() {
        RkOnlineSession rkOnlineSession = rkOnlineSessions.get(getRkOnlineUsername());
        return rkOnlineSession != null ? Optional.of(rkOnlineSession.getKey()) : Optional.empty();
    }

    @Override
    public void action() {
        RkOnlineSession rkOnlineSession = rkOnlineSessions.get(getRkOnlineUsername());
        if (rkOnlineSession != null) {
            rkOnlineSession.setLastAction(new RkOnlineSession.LastAction(dateTimeHelper.currentTime()));
        }
    }

    @Override
    public Optional<RkOnlineSession.LastAction> getLastAction() {
        RkOnlineSession rkOnlineSession = rkOnlineSessions.get(getRkOnlineUsername());
        return rkOnlineSession != null ? Optional.of(rkOnlineSession.getLastAction()) : Optional.empty();
    }

    @Override
    public void resetSessions() {
        rkOnlineSessions.clear();
    }
}
