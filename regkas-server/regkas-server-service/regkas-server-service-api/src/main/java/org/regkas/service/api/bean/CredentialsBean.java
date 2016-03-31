package org.regkas.service.api.bean;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * The credentials for the user to perform a log-in.
 */
public class CredentialsBean {

    @NotNull
    @Size(min = 3, max = 50)
    private String username;

    @NotNull
    @Size(min = 3, max = 50)
    private String password;

    public CredentialsBean() {
    }

    public CredentialsBean(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
