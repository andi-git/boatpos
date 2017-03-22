package org.regkas.repository.core.builder;

import org.boatpos.common.repository.core.builder.MasterDataBuilderCoreWithDto;
import org.regkas.model.CompanyEntity;
import org.regkas.repository.api.builder.CompanyBuilder;
import org.regkas.repository.api.model.*;
import org.regkas.repository.api.values.ATU;
import org.regkas.repository.api.values.EMail;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.api.values.Phone;
import org.regkas.repository.core.model.CompanyCore;
import org.regkas.service.api.bean.CompanyBean;

import javax.enterprise.context.Dependent;
import java.util.HashSet;
import java.util.Set;

@Dependent
public class CompanyBuilderCore
        extends MasterDataBuilderCoreWithDto<CompanyBuilder, Company, CompanyCore, CompanyEntity, CompanyBean>
        implements CompanyBuilder {

    private Name name;
    private Phone phone;
    private EMail email;
    private ATU atu;
    private Address address;
    private Set<CashBox> cashBoxes = new HashSet<>();
    private Set<User> users = new HashSet<>();
    private Set<ProductGroup> productGroups = new HashSet<>();

    @Override
    public Company build() {
        return new CompanyCore(id, version, enabled, priority, keyBinding, pictureUrl, pictureUrlThumb, name, phone, email, atu, address, users, cashBoxes, productGroups);
    }

    @Override
    public CompanyBuilder add(Name name) {
        this.name = name;
        return this;
    }

    @Override
    public CompanyBuilder add(Phone phone) {
        this.phone = phone;
        return this;
    }

    @Override
    public CompanyBuilder add(EMail email) {
        this.email = email;
        return this;
    }

    @Override
    public CompanyBuilder add(ATU atu) {
        this.atu = atu;
        return this;
    }

    @Override
    public CompanyBuilder add(Address address) {
        this.address = address;
        return this;
    }

    @Override
    public CompanyBuilder add(CashBox cashBox) {
        this.cashBoxes.add(cashBox);
        return this;
    }

    @Override
    public CompanyBuilder add(User user) {
        this.users.add(user);
        return this;
    }

    @Override
    public CompanyBuilder add(ProductGroup productGroup) {
        this.productGroups.add(productGroup);
        return this;
    }
}
