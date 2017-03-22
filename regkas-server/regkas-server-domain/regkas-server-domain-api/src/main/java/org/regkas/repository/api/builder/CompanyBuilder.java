package org.regkas.repository.api.builder;

import org.boatpos.common.repository.api.builder.MasterDataBuilderWithDto;
import org.regkas.model.CompanyEntity;
import org.regkas.repository.api.model.*;
import org.regkas.repository.api.values.ATU;
import org.regkas.repository.api.values.EMail;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.api.values.Phone;
import org.regkas.service.api.bean.CompanyBean;

import java.util.Set;

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
