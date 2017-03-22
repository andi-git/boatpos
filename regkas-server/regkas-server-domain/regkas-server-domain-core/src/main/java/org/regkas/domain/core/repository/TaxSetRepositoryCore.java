package org.regkas.domain.core.repository;

import org.boatpos.common.domain.api.values.Enabled;
import org.boatpos.common.domain.core.respository.MasterDataRepositoryCore;
import org.regkas.model.TaxSetEntity;
import org.regkas.domain.api.builder.TaxSetBuilder;
import org.regkas.domain.api.model.TaxSet;
import org.regkas.domain.api.repository.TaxSetRepository;
import org.regkas.domain.api.values.Name;
import org.regkas.domain.core.builder.TaxSetBuilderCore;
import org.regkas.domain.core.builder.TaxSetBuilderHolder;
import org.regkas.domain.core.model.TaxSetCore;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

@Dependent
public class TaxSetRepositoryCore extends MasterDataRepositoryCore<TaxSet, TaxSetCore, TaxSetEntity, TaxSetBuilder, TaxSetBuilderCore> implements TaxSetRepository {

    @Inject
    private TaxSetBuilderHolder taxSetBuilderHolder;

    @Override
    public Optional<TaxSet> loadBy(Name name) {
        checkNotNull(name, "'name' must not be null");
        return taxSetBuilderHolder.getTaxSetFor(
                jpaHelper().getSingleResult(
                        jpaHelper()
                                .createNamedQuery("taxset.getByName", TaxSetEntity.class)
                                .setParameter("name", name.get())
                                .getResultList())
                        .orElseThrow(() -> new RuntimeException("unable to get tax-set '" + name.get() + "' from database")));
    }

    @Override
    public List<TaxSet> loadAll() {
        return loadAll(namedQueryPrefix() + ".getAll");
    }

    @Override
    public List<TaxSet> loadAll(Enabled enabled) {
        checkNotNull(enabled, "'enabled' must not be null");
        return loadAll(namedQueryPrefix() + ".getAll" + (enabled.get() ? "Enabled" : "Disabled"));
    }

    private List<TaxSet> loadAll(String namedQuery) {
        return loadAll(namedQuery, (entity) -> {
            try {
                Optional<TaxSet> taxSet = taxSetBuilderHolder.getTaxSetFor(entity);
                if (taxSet.isPresent()) {
                    return taxSet.get();
                } else {
                    throw new RuntimeException("no tax-set-builder for " + entity.getClass().getName() + " avialable");
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    protected String namedQueryPrefix() {
        return "taxset";
    }
}
