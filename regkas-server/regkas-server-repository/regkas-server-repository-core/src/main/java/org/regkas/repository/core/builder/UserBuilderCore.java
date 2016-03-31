package org.regkas.repository.core.builder;

import org.boatpos.common.repository.core.builder.MasterDataBuilderCore;
import org.regkas.model.UserEntity;
import org.regkas.repository.api.builder.UserBuilder;
import org.regkas.repository.api.model.User;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.api.values.PasswordPlain;
import org.regkas.repository.core.model.UserCore;

import javax.enterprise.context.Dependent;

@Dependent
public class UserBuilderCore
        extends MasterDataBuilderCore<UserBuilder, User, UserCore, UserEntity>
        implements UserBuilder {

    private Name name;

    private PasswordPlain passwordPlain;

    @Override
    public User build() {
        return new UserCore(id, version, enabled, priority, keyBinding, pictureUrl, pictureUrlThumb, name, passwordPlain);
    }

    @Override
    public UserBuilder add(Name name) {
        this.name = name;
        return this;
    }

    @Override
    public UserBuilder add(PasswordPlain passwordPlain) {
        this.passwordPlain = passwordPlain;
        return this;
    }
}
