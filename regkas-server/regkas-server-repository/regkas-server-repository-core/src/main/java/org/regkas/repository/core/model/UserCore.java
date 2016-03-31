package org.regkas.repository.core.model;

import org.boatpos.common.repository.api.values.*;
import org.boatpos.common.repository.core.model.MasterDataCore;
import org.regkas.model.UserEntity;
import org.regkas.repository.api.model.User;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.api.values.PasswordPlain;

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
                    PasswordPlain passwordPlain) {
        super(id, version, enabled, priority, keyBinding, pictureUrl, pictureUrlThumb);
        checkNotNull(name, "'name' must not be null");
        checkNotNull(passwordPlain, "'password' must not be null");
        setName(name);
        setPassword(passwordPlain);
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
    public PasswordPlain getPassword() {
        return new PasswordPlain(getEntity().getPassword());
    }

    @Override
    public User setPassword(PasswordPlain passwordPlain) {
        getEntity().setPassword(SimpleValueObject.nullSafe(passwordPlain));
        return this;
    }
}