package org.regkas.domain.api.model;

import org.boatpos.common.domain.api.model.MasterData;
import org.regkas.model.UserEntity;
import org.regkas.domain.api.values.Name;
import org.regkas.domain.api.values.PasswordPlain;

/**
 * The domain model for a user.
 */
public interface User extends MasterData<User, UserEntity> {

    Name getName();

    User setName(Name name);

    PasswordPlain getPassword();

    User setPassword(PasswordPlain passwordPlain);

    Company getCompany();

    User setCompany(Company company);
}
