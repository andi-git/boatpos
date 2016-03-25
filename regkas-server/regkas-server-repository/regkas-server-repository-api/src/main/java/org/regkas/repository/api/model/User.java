package org.regkas.repository.api.model;

import org.boatpos.common.repository.api.model.MasterData;
import org.regkas.model.UserEntity;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.api.values.Password;

/**
 * The domain model for a user.
 */
public interface User extends MasterData<User, UserEntity> {

    Name getName();

    User setName(Name name);

    Password getPassword();

    User setPassword(Password password);
}
