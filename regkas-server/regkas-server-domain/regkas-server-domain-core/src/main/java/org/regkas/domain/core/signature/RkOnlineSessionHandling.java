package org.regkas.domain.core.signature;

import org.boatpos.common.util.datetime.DateTimeHelper;
import org.boatpos.common.util.log.LogWrapper;
import org.boatpos.common.util.log.SLF4J;
import org.boatpos.common.util.qualifiers.Current;
import org.regkas.domain.api.model.CashBox;
import org.regkas.domain.api.signature.RkOnlineContext;
import org.regkas.domain.api.signature.RkOnlineResourceFactory;
import org.regkas.domain.api.signature.SignatureDeviceNotAvailableException;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static com.google.common.base.Preconditions.checkNotNull;

@Dependent
public class RkOnlineSessionHandling {

    @Inject
    private SessionActiveChecker sessionActiveChecker;

    @Inject
    private RkOnlineContext rkOnlineContext;

    @Inject
    private RkOnlineResourceFactory rkOnlineResourceFactory;

    @Inject
    private ResponseStateChecker checkResponseState;

    @Inject
    @SLF4J
    private LogWrapper log;

    public Response withinActiveSession(WithinActiveSession withinActiveSession) throws SignatureDeviceNotAvailableException {
        checkNotNull(withinActiveSession, "'withinActiveSession' must not be null");
        createSessionIfNotActive();
        Response response = withinActiveSession.run();
        response = retryIfSessionIsMaybeExpired(withinActiveSession, response);
        return response;
    }

    private Response retryIfSessionIsMaybeExpired(WithinActiveSession withinActiveSession, Response oldResponse) throws SignatureDeviceNotAvailableException {
        Response response = oldResponse;
        if (sessionActiveChecker.isSessionMaybeExpired(oldResponse)) {
            createNewSession();
            response = withinActiveSession.run();
            checkResponseState.checkResponseState(response);
        }
        return response;
    }

    private void createSessionIfNotActive() throws SignatureDeviceNotAvailableException {
        if (!sessionActiveChecker.isSessionActive()) {
            createNewSession();
        }
    }

    private void createNewSession() throws SignatureDeviceNotAvailableException {
        log.info("create new session");
        rkOnlineResourceFactory.getRkOnlineResourceSession().loginSession();
    }

    @ApplicationScoped
    public static class SessionActiveChecker {

        @Inject
        @Current
        private CashBox cashBox;

        @Inject
        private RkOnlineContext rkOnlineContext;

        @Inject
        private DateTimeHelper dateTimeHelper;

        @Inject
        @SLF4J
        private LogWrapper log;

        public boolean isSessionActive() {
            boolean isSessionActive = rkOnlineContext.getLastAction().isPresent() &&
                    rkOnlineContext.getLastAction().get().getDateTime() != null &&
                    !isOlderThan30Minutes(rkOnlineContext.getLastAction().get().getDateTime());
            log.info("session is active: " + isSessionActive);
            return isSessionActive;
        }

        public boolean isOlderThan30Minutes(LocalDateTime dateTime) {
            boolean isOlderThan30Minutes = dateTime.until(dateTimeHelper.currentTime(), ChronoUnit.MINUTES) > 30;
            log.info("is older than 30 minutes: " + isOlderThan30Minutes + " (" + dateTime + " - " + dateTimeHelper.currentTime() + ")");
            return isOlderThan30Minutes;
        }

        public boolean isSessionMaybeExpired(Response response) {
            boolean isSessionMaybeExpired = response.getStatusInfo() == Response.Status.UNAUTHORIZED;
            log.info("is session maybe expired: " + isSessionMaybeExpired);
            return isSessionMaybeExpired;
        }
    }

    @FunctionalInterface
    public interface WithinActiveSession {

        Response run() throws SignatureDeviceNotAvailableException;
    }
}
