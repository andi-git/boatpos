package org.regkas.repository.api.repository;

import org.boatpos.common.repository.api.repository.MasterDataRepository;
import org.regkas.repository.api.builder.UserBuilder;
import org.regkas.repository.api.model.User;
import org.regkas.repository.api.values.Name;

import java.util.Optional;

/**
 * The repository for the {@link User}.
 */
public interface UserRepository extends MasterDataRepository<User> {

    /**
     * Get the {@link UserBuilder}.
     *
     * @return the {@link UserBuilder}
     */
    UserBuilder builder();

    /**
     * Load all attributes based on the {@link Name}.
     *
     * @param name the {@link Name} of the {@link User}
     * @return the current {@link User} with all attributes from the repository
     */
    Optional<User> loadBy(Name name);
}
