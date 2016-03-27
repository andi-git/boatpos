package org.regkas.repository.api.model;

import org.boatpos.common.repository.api.model.MasterDataWithDto;
import org.regkas.model.CompanyEntity;
import org.regkas.repository.api.values.ATU;
import org.regkas.repository.api.values.EMail;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.api.values.Phone;
import org.regkas.service.api.bean.CompanyBean;

import java.util.Set;

/**
 * The domain model for a company.
 */
public interface Company extends MasterDataWithDto<Company, CompanyEntity, CompanyBean> {

    Name getName();

    Company setName(Name name);

    Phone getPhone();

    Company setPhone(Phone phone);

    EMail getEMail();

    Company setEMail(EMail email);

    ATU getATU();

    Company setATU(ATU atu);

    Address getAddress();

    Company setAddress(Address address);

    Set<CashBox> getCashBoxes();

    Company addCashBoxes(Set<CashBox> cashBoxes);

    Company addCashBox(CashBox cashBoxes);

    Company clearCashBoxes();

    Set<User> getUsers();

    Company addUsers(Set<User> users);

    Company addUser(User user);

    Company clearUsers();

    Set<ProductGroup> getProductGroups();

    Company addProductGroups(Set<ProductGroup> productGroups);

    Company addProductGroup(ProductGroup productGroup);

    Company clearProductGroups();
}
