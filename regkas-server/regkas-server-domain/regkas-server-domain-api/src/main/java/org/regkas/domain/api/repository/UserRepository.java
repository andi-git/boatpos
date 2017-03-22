package org.regkas.domain.api.repository;

import org.boatpos.common.domain.api.repository.MasterDataRepository;
import org.regkas.domain.api.builder.UserBuilder;
import org.regkas.domain.api.model.User;
import org.regkas.domain.api.values.Name;
import org.regkas.domain.api.values.PasswordPlain;

import java.util.Optional;

/**
 * The repository for the {@link User}.
 */
public interface UserRepository extends MasterDataRepository<User, UserBuilder> {

    /**
     * Load all attributes based on the {@link Name}.
     *
     * @param name the {@link Name} of the {@link User}
     * @return the current {@link User} with all attributes from the repository
     */
    Optional<User> loadBy(Name name);

    /**
     * Authenticate a user.
     *
     * @param name     the {@link Name} of the {@link User}
     * @param passwordPlain the {@link PasswordPlain} of the {@link User}
     * @return {@code true} it the user is authenticated, otherwise {@code false}
     */
    Optional<User> authenticate(Name name, PasswordPlain passwordPlain);
}
