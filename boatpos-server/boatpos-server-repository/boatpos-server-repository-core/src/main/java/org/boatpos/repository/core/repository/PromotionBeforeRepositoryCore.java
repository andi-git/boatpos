package org.boatpos.repository.core.repository;

import org.boatpos.common.repository.api.values.Enabled;
import org.boatpos.common.repository.core.respository.MasterDataRepositoryCore;
import org.boatpos.model.PromotionBeforeEntity;
import org.boatpos.model.PromotionEntity;
import org.boatpos.repository.api.builder.PromotionBeforeBuilder;
import org.boatpos.repository.api.model.PromotionBefore;
import org.boatpos.repository.api.repository.PromotionBeforeRepository;
import org.boatpos.repository.api.values.Name;
import org.boatpos.repository.core.builder.PromotionAfterBuilderCore;
import org.boatpos.repository.core.builder.PromotionBeforeBuilderCore;
import org.boatpos.repository.core.model.PromotionBeforeCore;

import javax.enterprise.context.Dependent;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;

@Dependent
public class PromotionBeforeRepositoryCore extends MasterDataRepositoryCore<PromotionBefore, PromotionBeforeCore, PromotionBeforeEntity, PromotionBeforeBuilder, PromotionAfterBuilderCore> implements PromotionBeforeRepository {

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
