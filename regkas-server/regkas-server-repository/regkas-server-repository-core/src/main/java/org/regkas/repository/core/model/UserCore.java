package org.regkas.repository.core.model;

import org.boatpos.common.repository.api.values.*;
import org.boatpos.common.repository.core.model.MasterDataCore;
import org.regkas.model.UserEntity;
import org.regkas.repository.api.model.User;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.api.values.Password;

import static com.google.common.base.Preconditions.checkNotNull;

public class UserCore extends MasterDataCore<User, UserEntity> implements User {

    public UserCore(DomainId id,
                    Version version,
                    Enabled enabled,
                    Priority priority,
                    KeyBinding keyBinding,
                    PictureUrl pictureUrl,
                    PictureUrlThumb pictureUrlThumb,
                    Name name,
                    Password password) {
        super(id, version, enabled, priority, keyBinding, pictureUrl, pictureUrlThumb);
        checkNotNull(name, "'name' must not be null");
        checkNotNull(password, "'password' must not be null");
        setName(name);
        setPassword(password);
    }

    public UserCore(UserEntity user) {
        super(user);
    }

    @Override
    public Name getName() {
        return new Name(getEntity().getName());
    }

    @Override
    public User setName(Name name) {
        getEntity().setName(SimpleValueObject.nullSafe(name));
        return this;
    }

    @Override
    public Password getPassword() {
        return new Password(getEntity().getPassword());
    }

    @Override
    public User setPassword(Password password) {
        getEntity().setPassword(SimpleValueObject.nullSafe(password));
        return this;
    }
}