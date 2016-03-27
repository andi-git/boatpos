package org.regkas.repository.core.repository;

import org.boatpos.common.repository.core.respository.MasterDataRepositoryCore;
import org.regkas.model.CashBoxEntity;
import org.regkas.model.TaxSetEntity;
import org.regkas.repository.api.builder.CashBoxBuilder;
import org.regkas.repository.api.builder.TaxSetBuilder;
import org.regkas.repository.api.model.CashBox;
import org.regkas.repository.api.model.TaxSet;
import org.regkas.repository.api.repository.CashBoxRepository;
import org.regkas.repository.api.repository.TaxSetRepository;
import org.regkas.repository.api.values.Name;
import org.regkas.repository.core.builder.CashBoxBuilderCore;
import org.regkas.repository.core.builder.TaxSetBuilderCore;
import org.regkas.repository.core.model.CashBoxCore;
import org.regkas.repository.core.model.TaxSetCore;

import javax.enterprise.context.Dependent;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

@Dependent
public class TaxSetRepositoryCore extends MasterDataRepositoryCore<TaxSet, TaxSetCore, TaxSetEntity, TaxSetBuilder, TaxSetBuilderCore> implements TaxSetRepository {

    @Override
    public Optional<TaxSet> loadBy(Name name) {
        checkNotNull(name, "'name' must not be null");
        return loadByParameter("taxset.getByName", (query) -> query.setParameter("name", name.get()));
    }

    @Override
    protected String namedQueryPrefix() {
        return "taxset";
    }
}
