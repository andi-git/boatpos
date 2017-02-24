package org.regkas.repository.api.context;

import org.regkas.repository.api.model.User;

import java.util.Optional;

/**
 * Context for the {@link User}.
 */
public interface UserContext {

    User get();

    void set(User user);

    void set(Optional<User> user);

    void clear();
}
