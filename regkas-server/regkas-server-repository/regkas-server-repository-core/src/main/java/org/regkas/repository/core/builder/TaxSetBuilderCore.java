package org.regkas.repository.core.builder;

import org.boatpos.common.repository.core.builder.MasterDataBuilderCoreWithDto;
import org.regkas.model.TaxSetEntity;
import org.regkas.repository.api.builder.TaxSetBuilder;
import org.regkas.repository.api.model.TaxSet;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.api.values.TaxPercent;
import org.regkas.repository.core.model.TaxSetCore;
import org.regkas.service.api.bean.TaxSetBean;

import javax.enterprise.context.Dependent;

@Dependent
public class TaxSetBuilderCore
        extends MasterDataBuilderCoreWithDto<TaxSetBuilder, TaxSet, TaxSetCore, TaxSetEntity, TaxSetBean>
        implements TaxSetBuilder {

    private Name name;

    private TaxPercent taxPercent;

    @Override
    public TaxSet build() {
        return new TaxSetCore(id, version, enabled, priority, keyBinding, pictureUrl, pictureUrlThumb, name, taxPercent);
    }

    @Override
    public TaxSetBuilder add(Name name) {
        this.name = name;
        return this;
    }

    @Override
    public TaxSetBuilder add(TaxPercent taxPercent) {
        this.taxPercent = taxPercent;
        return this;
    }
}
