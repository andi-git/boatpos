package org.regkas.service.api.context;

/**
 * Handle the context, e.g. set user, company, chashbox,...
 */
public interface ContextService {

    /**
     * Initialize the context. <br><br><b>Warning</b>: this method does not perform an authentication check!
     *
     * @param username  the current username
     * @param cashBoxId the current cashBoxId
     */
    void initContext(String username, String cashBoxId);

    int getContextId();
}
