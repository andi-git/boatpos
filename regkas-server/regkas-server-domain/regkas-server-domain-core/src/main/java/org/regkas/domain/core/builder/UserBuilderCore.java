package org.regkas.domain.core.builder;

import org.boatpos.common.domain.core.builder.MasterDataBuilderCore;
import org.regkas.model.UserEntity;
import org.regkas.domain.api.builder.UserBuilder;
import org.regkas.domain.api.model.User;
import org.regkas.domain.api.values.Name;
import org.regkas.domain.api.values.PasswordPlain;
import org.regkas.domain.core.model.UserCore;

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
