package org.regkas.service.core.context;

import org.boatpos.common.repository.api.values.DomainId;
import org.boatpos.common.util.qualifiers.Current;
import org.regkas.repository.api.model.User;
import org.regkas.repository.api.repository.UserRepository;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import java.util.Optional;

/**
 * Context for the {@link User}.
 */
@RequestScoped
public class UserContext {

    @Inject
    private UserRepository userRepository;

    private DomainId id;

    public void set(User user) {
        if (user != null) this.id = user.getId();
    }

    public void set(Optional<User> user) {
        if (user.isPresent()) set(user.get());
    }

    public void clear() {
        this.id = null;
    }

    @Produces
    @Current
    @RequestScoped
    public User get() {
        return id == null ? null : userRepository.loadBy(id).orElse(null);
    }
}
