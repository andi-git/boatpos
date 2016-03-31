package org.regkas.repository.core.repository;

import org.apache.commons.codec.digest.DigestUtils;
import org.boatpos.common.repository.core.respository.MasterDataRepositoryCore;
import org.regkas.model.UserEntity;
import org.regkas.repository.api.builder.UserBuilder;
import org.regkas.repository.api.model.User;
import org.regkas.repository.api.repository.UserRepository;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.api.values.PasswordPlain;
import org.regkas.repository.core.builder.UserBuilderCore;
import org.regkas.repository.core.model.UserCore;

import javax.enterprise.context.Dependent;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

@Dependent
public class UserRepositoryCore extends MasterDataRepositoryCore<User, UserCore, UserEntity, UserBuilder, UserBuilderCore> implements UserRepository {

    @Override
    public Optional<User> loadBy(Name name) {
        checkNotNull(name, "'name' must not be null");
        return loadByParameter("user.getByName", (query) -> query.setParameter("name", name.get()));
    }

    @Override
    public Optional<User> authenticate(Name name, PasswordPlain passwordPlain) {
        checkNotNull(name, "'name' must not be null");
        checkNotNull(passwordPlain, "'password' must not be null");
        return loadByParameter("user.authenticate", (query) -> query.setParameter("name", name.get()).setParameter("password", asSHA1(passwordPlain)));
    }

    @Override
    protected String namedQueryPrefix() {
        return "user";
    }

    private String asSHA1(PasswordPlain passwordPlain) {
        return DigestUtils.sha1Hex(passwordPlain.get());
    }
}
