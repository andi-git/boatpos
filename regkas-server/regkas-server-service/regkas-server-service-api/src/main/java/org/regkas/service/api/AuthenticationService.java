package org.regkas.service.api;

import org.regkas.service.api.bean.CredentialsBean;

/**
 * Service for sales.
 */
public interface AuthenticationService {

    /**
     * Authenticate a user by credentials (username and password).
     *
     * @param credentialsBean the credentials of the user
     * @return {@code true} it the user is authenticated, otherwise {@code false}
     */
    Boolean authenticate(CredentialsBean credentialsBean);
}
