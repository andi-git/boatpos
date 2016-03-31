package org.regkas.service.rest.filter;

import org.boatpos.common.util.log.LogWrapper;
import org.boatpos.common.util.log.SLF4J;
import org.regkas.service.api.AuthenticationService;
import org.regkas.service.api.bean.CredentialsBean;
import org.regkas.service.api.context.ContextService;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * Filter to authenticate the user.
 */
@Authenticated
@Provider
public class AuthenticateUser implements ContainerRequestFilter {

    @Inject
    private AuthenticationService authenticationService;

    @Inject
    private ContextService contextService;

    @Inject
    @SLF4J
    private LogWrapper log;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        if (authenticationService.authenticate(new CredentialsBean(getUsername(requestContext), getPassword(requestContext), getCashBox(requestContext)))) {
            contextService.initContext(getUsername(requestContext), getCashBox(requestContext));
        } else {
            log.warn("Authorization failure for user {} and cash-box {}", getUsername(requestContext), getCashBox(requestContext));
        }
    }

    private String getUsername(ContainerRequestContext requestContext) {
        return String.valueOf(requestContext.getHeaderString("username"));
    }

    private String getPassword(ContainerRequestContext requestContext) {
        return String.valueOf(requestContext.getHeaderString("password"));
    }

    private String getCashBox(ContainerRequestContext requestContext) {
        return String.valueOf(requestContext.getHeaderString("cashbox"));
    }
}
