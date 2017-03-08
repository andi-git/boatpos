package org.regkas.repository.core.signature;

import java.util.Optional;

import org.regkas.repository.api.signature.RkOnlineResourceSession;
import org.regkas.repository.api.signature.SignatureDeviceNotAvailableException;

public class MockRkOnlineResourceSession implements RkOnlineResourceSession {

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private Optional<RunInLoginSession> runInLoginSession = Optional.empty();

    @Override
    public void loginSession() throws SignatureDeviceNotAvailableException {
        if (runInLoginSession.isPresent()) {
            runInLoginSession.get().run();
        }
    }

    public void setRunInLoginSession(RunInLoginSession runInLoginSession) {
        this.runInLoginSession = Optional.of(runInLoginSession);
    }

    @FunctionalInterface
    public interface RunInLoginSession {

        void run() throws SignatureDeviceNotAvailableException;
    }
}
