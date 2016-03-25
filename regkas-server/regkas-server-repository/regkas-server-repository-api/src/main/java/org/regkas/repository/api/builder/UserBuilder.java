package org.regkas.repository.api.builder;

import org.boatpos.common.repository.api.builder.MasterDataBuilder;
import org.regkas.model.UserEntity;
import org.regkas.repository.api.model.User;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.api.values.Password;

/**
 * Builder for {@link User}.
 */
public interface UserBuilder extends MasterDataBuilder<UserBuilder, User, UserEntity> {

    UserBuilder add(Name name);

    UserBuilder add(Password password);
}
