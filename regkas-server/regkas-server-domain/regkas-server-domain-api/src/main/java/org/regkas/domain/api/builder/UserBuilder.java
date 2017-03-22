package org.regkas.domain.api.builder;

import org.boatpos.common.domain.api.builder.MasterDataBuilder;
import org.regkas.domain.api.model.User;
import org.regkas.domain.api.values.Name;
import org.regkas.model.UserEntity;
import org.regkas.domain.api.values.PasswordPlain;

/**
 * Builder for {@link User}.
 */
public interface UserBuilder extends MasterDataBuilder<UserBuilder, User, UserEntity> {

    UserBuilder add(Name name);

    UserBuilder add(PasswordPlain passwordPlain);
}
