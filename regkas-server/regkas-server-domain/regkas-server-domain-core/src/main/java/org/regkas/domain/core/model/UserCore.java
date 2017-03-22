package org.regkas.domain.core.model;

import org.boatpos.common.domain.api.values.DomainId;
import org.boatpos.common.domain.api.values.Enabled;
import org.boatpos.common.domain.api.values.KeyBinding;
import org.boatpos.common.domain.api.values.PictureUrl;
import org.boatpos.common.domain.api.values.PictureUrlThumb;
import org.boatpos.common.domain.api.values.Priority;
import org.boatpos.common.domain.api.values.SimpleValueObject;
import org.boatpos.common.domain.api.values.Version;
import org.boatpos.common.domain.core.model.MasterDataCore;
import org.regkas.model.UserEntity;
import org.regkas.domain.api.model.Company;
import org.regkas.domain.api.model.User;
import org.regkas.domain.api.values.Name;
import org.regkas.domain.api.values.PasswordPlain;

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

    @Override
    public Company getCompany() {
        return new CompanyCore(getEntity().getCompany());
    }

    @Override
    public User setCompany(Company company) {
        if (company != null) getEntity().setCompany(company.asEntity());
        return this;
    }
}