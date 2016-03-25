package org.regkas.repository.core.model;

import org.boatpos.common.repository.api.model.DomainModel;
import org.boatpos.common.repository.api.values.*;
import org.boatpos.common.repository.core.model.MasterDataCore;
import org.regkas.model.CompanyEntity;
import org.regkas.repository.api.model.Address;
import org.regkas.repository.api.model.CashBox;
import org.regkas.repository.api.model.Company;
import org.regkas.repository.api.model.User;
import org.regkas.repository.api.values.ATU;
import org.regkas.repository.api.values.EMail;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.api.values.Phone;
import org.regkas.repository.core.mapping.CompanyMapping;
import org.regkas.service.api.bean.CompanyBean;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

public class CompanyCore extends MasterDataCore<Company, CompanyEntity> implements Company {

    public CompanyCore(DomainId id,
                       Version version,
                       Enabled enabled,
                       Priority priority,
                       KeyBinding keyBinding,
                       PictureUrl pictureUrl,
                       PictureUrlThumb pictureUrlThumb,
                       Name name,
                       Phone phone,
                       EMail email,
                       ATU atu,
                       Address address,
                       Set<User> users,
                       Set<CashBox> cashBoxes) {
        super(id, version, enabled, priority, keyBinding, pictureUrl, pictureUrlThumb);
        checkNotNull(name, "'name' must not be null");
        checkNotNull(atu, "'atu' must not be null");
        checkNotNull(address, "'address' must not be null");
        checkNotNull(users, "'users' must not be null");
        checkNotNull(cashBoxes, "'cashBoxes' must not be null");
        checkArgument(users.size() > 0, "there must be at least 1 user");
        checkArgument(cashBoxes.size() > 0, "there must be at least 1 cash-box");
        setName(name);
        setPhone(phone);
        setEMail(email);
        setATU(atu);
        setAddress(address);
        addUsers(users);
        addCashBoxes(cashBoxes);
    }

    public CompanyCore(CompanyEntity companyEntity) {
        super(companyEntity);
    }

    @Override
    public Name getName() {
        return new Name(getEntity().getName());
    }

    @Override
    public Company setName(Name name) {
        getEntity().setName(SimpleValueObject.nullSafe(name));
        return this;
    }

    @Override
    public Phone getPhone() {
        return new Phone(getEntity().getPhone());
    }

    @Override
    public Company setPhone(Phone phone) {
        getEntity().setPhone(SimpleValueObject.nullSafe(phone));
        return this;
    }

    @Override
    public EMail getEMail() {
        return new EMail(getEntity().getMail());
    }

    @Override
    public Company setEMail(EMail email) {
        getEntity().setMail(SimpleValueObject.nullSafe(email));
        return this;
    }

    @Override
    public ATU getATU() {
        return new ATU(getEntity().getAtu());
    }

    @Override
    public Company setATU(ATU atu) {
        getEntity().setAtu(SimpleValueObject.nullSafe(atu));
        return this;
    }

    @Override
    public Address getAddress() {
        return new AddressCore(getEntity().getAddress());
    }

    @Override
    public Company setAddress(Address address) {
        if (address != null) getEntity().setAddress(address.asEntity());
        return this;
    }

    @Override
    public Set<User> getUsers() {
        return Collections.unmodifiableSet(getEntity().getUsers().stream().map(UserCore::new).collect(Collectors.toSet()));
    }

    @Override
    public Company addUsers(Set<User> commitments) {
        if (commitments != null) {
            getEntity().setUsers(commitments.stream().map(DomainModel::asEntity).collect(Collectors.toSet()));
        }
        return this;
    }

    @Override
    public Company addUser(User commitment) {
        if (commitment != null) {
            getEntity().getUsers().add(commitment.asEntity());
        }
        return this;
    }

    @Override
    public Company clearUsers() {
        getEntity().getUsers().clear();
        return this;
    }

    @Override
    public Set<CashBox> getCashBoxes() {
        return Collections.unmodifiableSet(getEntity().getCashBoxes().stream().map(CashBoxCore::new).collect(Collectors.toSet()));
    }

    @Override
    public Company addCashBoxes(Set<CashBox> commitments) {
        if (commitments != null) {
            getEntity().setCashBoxes(commitments.stream().map(DomainModel::asEntity).collect(Collectors.toSet()));
        }
        return this;
    }

    @Override
    public Company addCashBox(CashBox commitment) {
        if (commitment != null) {
            getEntity().getCashBoxes().add(commitment.asEntity());
        }
        return this;
    }

    @Override
    public Company clearCashBoxes() {
        getEntity().getCashBoxes().clear();
        return this;
    }

    @Override
    public CompanyBean asDto() {
        return CompanyMapping.fromCDI().mapEntity(getEntity());
    }
}