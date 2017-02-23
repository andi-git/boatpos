package org.regkas.repository.core.signature;

import org.regkas.repository.api.signature.Environment;
import org.regkas.repository.api.signature.RkOnlineContext;
import org.regkas.repository.api.values.RkOnlinePassword;
import org.regkas.repository.api.values.RkOnlineUsername;

import javax.enterprise.context.RequestScoped;

/**
 * Context for the environment of RK Online.
 */
@RequestScoped
public class RkOnlineContextCore implements RkOnlineContext {

    private Environment environment = Environment.PROD;

    private RkOnlineUsername rkOnlineUsername;

    private RkOnlinePassword rkOnlinePassword;

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public Environment getEnvironment() {
        return environment;
    }

    @Override
    public RkOnlineUsername getRkOnlineUsername() {
        return rkOnlineUsername;
    }

    @Override
    public void setRkOnlineUsername(RkOnlineUsername rkOnlineUsername) {
        this.rkOnlineUsername = rkOnlineUsername;
    }

    @Override
    public RkOnlinePassword getRkOnlinePassword() {
        return rkOnlinePassword;
    }

    @Override
    public void setRkOnlinePassword(RkOnlinePassword rkOnlinePassword) {
        this.rkOnlinePassword = rkOnlinePassword;
    }
}
