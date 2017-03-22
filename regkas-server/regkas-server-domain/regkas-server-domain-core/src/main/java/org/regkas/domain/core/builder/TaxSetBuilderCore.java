package org.regkas.domain.core.builder;

import org.boatpos.common.domain.core.builder.MasterDataBuilderCoreWithDto;
import org.regkas.model.TaxSetEntity;
import org.regkas.domain.api.builder.TaxSetBuilder;
import org.regkas.domain.api.model.TaxSet;
import org.regkas.domain.core.model.TaxSetCore;
import org.regkas.service.api.bean.TaxSetBean;

public abstract class TaxSetBuilderCore<BUILDER extends TaxSetBuilder, MODEL extends TaxSet, MODELCORE extends TaxSetCore, ENTITY extends TaxSetEntity>
        extends MasterDataBuilderCoreWithDto<TaxSetBuilder, TaxSet, MODELCORE, ENTITY, TaxSetBean>
        implements TaxSetBuilder<TaxSet, ENTITY> {

    @Override
    public TaxSet build() {
        return null;
    }

    @Override
    public boolean canHandle(Class<ENTITY> taxSetEntityClass) {
        return getEntityClass() == taxSetEntityClass;
    }
}
