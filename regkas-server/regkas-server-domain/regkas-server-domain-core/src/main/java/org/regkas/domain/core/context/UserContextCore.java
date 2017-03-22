package org.regkas.domain.core.context;

import org.boatpos.common.util.qualifiers.Current;
import org.regkas.domain.api.context.UserContext;
import org.regkas.domain.api.model.User;
import org.regkas.domain.api.repository.UserRepository;

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
