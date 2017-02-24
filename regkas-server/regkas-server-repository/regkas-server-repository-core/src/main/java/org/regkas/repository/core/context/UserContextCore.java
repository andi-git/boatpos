package org.regkas.repository.core.context;

import org.boatpos.common.util.qualifiers.Current;
import org.regkas.repository.api.context.UserContext;
import org.regkas.repository.api.model.User;
import org.regkas.repository.api.repository.UserRepository;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

@RequestScoped
public class UserContextCore extends DomainModelContext<User> implements UserContext {

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
