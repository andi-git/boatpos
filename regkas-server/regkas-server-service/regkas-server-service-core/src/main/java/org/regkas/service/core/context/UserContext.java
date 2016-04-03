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
public class UserContext extends DomainModelContext<User> {

    @Inject
    private UserRepository userRepository;

    @Override
    @Produces
    @Current
    @RequestScoped
    public User get() {
        return getId().isPresent() ? userRepository.loadBy(getId().get()).orElse(null) : null;
    }
}
