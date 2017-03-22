package org.regkas.repository.core.builder;

import org.boatpos.common.repository.core.builder.MasterDataBuilderCoreWithDto;
import org.regkas.model.TaxSetEntity;
import org.regkas.repository.api.builder.TaxSetBuilder;
import org.regkas.repository.api.model.TaxSet;
import org.regkas.repository.core.model.TaxSetCore;
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
