package org.regkas.domain.api.builder;

import org.boatpos.common.domain.api.builder.MasterDataBuilderWithDto;
import org.regkas.domain.api.model.Address;
import org.regkas.domain.api.model.CashBox;
import org.regkas.domain.api.model.Company;
import org.regkas.domain.api.model.ProductGroup;
import org.regkas.domain.api.model.User;
import org.regkas.domain.api.values.ATU;
import org.regkas.domain.api.values.EMail;
import org.regkas.domain.api.values.Name;
import org.regkas.domain.api.values.Phone;
import org.regkas.model.CompanyEntity;
import org.regkas.service.api.bean.CompanyBean;

/**
 * Builder for {@link Company}.
 */
public interface CompanyBuilder extends MasterDataBuilderWithDto<CompanyBuilder, Company, CompanyEntity, CompanyBean> {

    CompanyBuilder add(Name name);

    CompanyBuilder add(Phone phone);

    CompanyBuilder add(EMail email);

    CompanyBuilder add(ATU atu);

    CompanyBuilder add(Address address);

    CompanyBuilder add(CashBox cashBox);

    CompanyBuilder add(User user);

    CompanyBuilder add(ProductGroup productGroup);
}
