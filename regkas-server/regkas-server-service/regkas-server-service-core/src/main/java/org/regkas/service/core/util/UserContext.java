package org.regkas.service.core.util;

import org.boatpos.common.util.qualifiers.Current;
import org.regkas.repository.api.model.CashBox;
import org.regkas.repository.api.model.User;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import java.util.Optional;

/**
 * Context for the {@link User}.
 */
@RequestScoped
public class UserContext {

    private User user;

    public void set(User user) {
        this.user = user;
    }

    public void set(Optional<User> user) {
        if (user.isPresent()) this.user= user.get();
    }

    public void clear() {
        this.user = null;
    }

    @Produces
    @Current
    @RequestScoped
    public User get() {
        return this.user;
    }
}
