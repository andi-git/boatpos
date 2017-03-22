package org.regkas.domain.api.builder;

import org.boatpos.common.domain.api.builder.MasterDataBuilderWithDto;
import org.regkas.domain.api.model.TaxSet;
import org.regkas.model.TaxSetEntity;
import org.regkas.service.api.bean.TaxSetBean;

/**
 * Builder for {@link TaxSet}.
 */
public interface TaxSetBuilder<MODEL extends TaxSet, ENTITY extends TaxSetEntity> extends MasterDataBuilderWithDto<TaxSetBuilder, MODEL, ENTITY, TaxSetBean> {

    boolean canHandle(Class<ENTITY> taxSetEntityClass);

    TaxSet build(ENTITY taxSetEntity);

}
