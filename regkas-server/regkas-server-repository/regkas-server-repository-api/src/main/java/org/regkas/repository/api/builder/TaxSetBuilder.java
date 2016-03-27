package org.regkas.repository.api.builder;

import org.boatpos.common.repository.api.builder.MasterDataBuilder;
import org.boatpos.common.repository.api.builder.MasterDataBuilderWithDto;
import org.regkas.model.TaxSetEntity;
import org.regkas.model.UserEntity;
import org.regkas.repository.api.model.ProductGroup;
import org.regkas.repository.api.model.TaxSet;
import org.regkas.repository.api.model.User;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.api.values.Password;
import org.regkas.repository.api.values.TaxPercent;
import org.regkas.service.api.bean.TaxSetBean;

/**
 * Builder for {@link TaxSet}.
 */
public interface TaxSetBuilder extends MasterDataBuilderWithDto<TaxSetBuilder, TaxSet, TaxSetEntity, TaxSetBean> {

    TaxSetBuilder add(Name name);

    TaxSetBuilder add(TaxPercent taxPercent);
}
