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

    @NotNull
    @Size(min = 3, max = 50)
    private String cashBoxId;

    public CredentialsBean() {
    }

    public CredentialsBean(String username, String password, String cashBoxId) {
        this.username = username;
        this.password = password;
        this.cashBoxId = cashBoxId;
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

    public String getCashBoxId() {
        return cashBoxId;
    }

    public void setCashBoxId(String cashBoxId) {
        this.cashBoxId = cashBoxId;
    }
}
