package org.regkas.repository.api.model;

import org.boatpos.common.repository.api.model.MasterData;
import org.boatpos.model.BoatEntity;
import org.boatpos.service.api.bean.BoatBean;
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
public interface Company extends MasterData<Company, CompanyEntity, CompanyBean> {

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

    Company addCashBox(CashBox cashBoxe);

    Company clearCashBoxes();

    Set<User> getUsers();

    Company addUsers(Set<User> users);

    Company addUser(User user);

    Company clearUsers();

}
