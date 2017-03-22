package org.boatpos.domain.core.repository;

import org.boatpos.common.domain.api.values.Enabled;
import org.boatpos.common.domain.core.respository.MasterDataRepositoryCore;
import org.boatpos.model.PromotionBeforeEntity;
import org.boatpos.model.PromotionEntity;
import org.boatpos.domain.api.builder.PromotionBeforeBuilder;
import org.boatpos.domain.api.model.PromotionBefore;
import org.boatpos.domain.api.repository.PromotionBeforeRepository;
import org.boatpos.domain.api.values.Name;
import org.boatpos.domain.core.builder.PromotionBeforeBuilderCore;
import org.boatpos.domain.core.model.PromotionBeforeCore;

import javax.enterprise.context.Dependent;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;

@Dependent
public class PromotionBeforeRepositoryCore extends MasterDataRepositoryCore<PromotionBefore, PromotionBeforeCore, PromotionBeforeEntity, PromotionBeforeBuilder, PromotionBeforeBuilderCore> implements PromotionBeforeRepository {

    @Override
    public Optional<PromotionBefore> loadBy(Name name) {
        checkNotNull(name, "'name' must not be null");
        return loadByParameter("promotion.getByName", (query) -> query.setParameter("name", name.get()), PromotionEntity.class, PromotionBeforeEntity.class);
    }

    @Override
    public List<PromotionBefore> loadAll() {
        return jpaHelper().createNamedQuery("promotion.getAll", PromotionEntity.class).getResultList().stream()
                .filter((p) -> p instanceof PromotionBeforeEntity)
                .map((p) -> (PromotionBeforeEntity) p)
                .map(PromotionBeforeCore::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<PromotionBefore> loadAll(Enabled enabled) {
        checkNotNull(enabled, "'enabled' must not be null");
        String namedQuery = "promotion.getAll" + (enabled.get() ? "Enabled" : "Disabled");
        return jpaHelper().createNamedQuery(namedQuery, PromotionEntity.class).getResultList().stream()
                .filter((p) -> p instanceof PromotionBeforeEntity)
                .map((p) -> (PromotionBeforeEntity) p)
                .map(PromotionBeforeCore::new)
                .collect(Collectors.toList());
    }

    @Override
    protected String namedQueryPrefix() {
        return "promotion";
    }
}
