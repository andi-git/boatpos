package org.regkas.repository.core.builder;

import org.boatpos.common.repository.core.builder.MasterDataBuilderCoreWithDto;
import org.regkas.model.ProductGroupEntity;
import org.regkas.repository.api.builder.ProductGroupBuilder;
import org.regkas.repository.api.model.ProductGroup;
import org.regkas.repository.api.model.TaxSet;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.core.model.ProductGroupCore;
import org.regkas.service.api.bean.ProductGroupBean;

import javax.enterprise.context.Dependent;

@Dependent
public class ProductGroupBuilderCore
        extends MasterDataBuilderCoreWithDto<ProductGroupBuilder, ProductGroup, ProductGroupCore, ProductGroupEntity, ProductGroupBean>
        implements ProductGroupBuilder {

    private Name name;

    private TaxSet taxSet;

    @Override
    public ProductGroup build() {
        return new ProductGroupCore(id, version, enabled, priority, keyBinding, pictureUrl, pictureUrlThumb, name, taxSet);
    }

    @Override
    public ProductGroupBuilder add(Name name) {
        this.name = name;
        return this;
    }

    @Override
    public ProductGroupBuilder add(TaxSet taxSet) {
        this.taxSet = taxSet;
        return this;
    }
}
