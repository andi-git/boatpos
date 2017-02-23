package org.regkas.repository.api.signature;

import org.regkas.repository.api.values.RkOnlinePassword;
import org.regkas.repository.api.values.RkOnlineUsername;

/**
 * Context for the environment of RK Online.
 */
public interface RkOnlineContext {

    void setEnvironment(Environment environment);

    Environment getEnvironment();

    RkOnlineUsername getRkOnlineUsername();

    void setRkOnlineUsername(RkOnlineUsername rkOnlineUsername);

    RkOnlinePassword getRkOnlinePassword();

    void setRkOnlinePassword(RkOnlinePassword rkOnlinePassword);
}
