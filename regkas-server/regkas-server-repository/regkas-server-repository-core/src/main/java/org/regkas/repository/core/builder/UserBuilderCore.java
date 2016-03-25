package org.regkas.repository.core.builder;

import org.boatpos.common.repository.core.builder.MasterDataBuilderCore;
import org.regkas.model.UserEntity;
import org.regkas.repository.api.builder.UserBuilder;
import org.regkas.repository.api.model.User;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.api.values.Password;
import org.regkas.repository.core.model.UserCore;

import javax.enterprise.context.Dependent;

@Dependent
public class UserBuilderCore
        extends MasterDataBuilderCore<UserBuilder, User, UserCore, UserEntity>
        implements UserBuilder {

    private Name name;

    private Password password;

    @Override
    public User build() {
        return new UserCore(id, version, enabled, priority, keyBinding, pictureUrl, pictureUrlThumb, name, password);
    }

    @Override
    public UserBuilder add(Name name) {
        this.name = name;
        return this;
    }

    @Override
    public UserBuilder add(Password password) {
        this.password = password;
        return this;
    }
}
