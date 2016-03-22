package org.regkas.repository.api.model;

import org.boatpos.common.repository.api.model.DomainModel;
import org.boatpos.common.repository.api.model.MasterData;
import org.regkas.model.AddressEntity;
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
public interface Address extends DomainModel<Address, AddressEntity, AddressBean> {

    Street getStreet();

    Address setStreet(Street street);

    ZIP getZIP();

    Address setZIP(ZIP zip);

    Country getCountry();

    Address setCountry(Country country);
}
