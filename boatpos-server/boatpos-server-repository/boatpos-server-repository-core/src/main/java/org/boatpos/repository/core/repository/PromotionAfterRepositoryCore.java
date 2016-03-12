package org.boatpos.repository.core.repository;

import org.boatpos.common.repository.api.values.Enabled;
import org.boatpos.model.PromotionAfterEntity;
import org.boatpos.model.PromotionEntity;
import org.boatpos.repository.api.builder.PromotionAfterBuilder;
import org.boatpos.repository.api.model.PromotionAfter;
import org.boatpos.repository.api.repository.PromotionAfterRepository;
import org.boatpos.repository.api.values.Name;
import org.boatpos.repository.core.builder.PromotionAfterBuilderCore;
import org.boatpos.repository.core.model.PromotionAfterCore;

import javax.enterprise.context.Dependent;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;

@Dependent
public class PromotionAfterRepositoryCore extends MasterDataRepositoryCore<PromotionAfter, PromotionAfterCore, PromotionAfterEntity> implements PromotionAfterRepository {

    @Override
    public PromotionAfterBuilder builder() {
        return new PromotionAfterBuilderCore();
    }

    @Override
    public Optional<PromotionAfter> loadBy(Name name) {
        checkNotNull(name, "'name' must not be null");
        return loadByParameter("promotion.getByName", (query) -> query.setParameter("name", name.get()), PromotionEntity.class, PromotionAfterEntity.class);
    }

    @Override
    public List<PromotionAfter> loadAll() {
        return jpaHelper().createNamedQuery("promotion.getAll", PromotionEntity.class).getResultList().stream()
                .filter((p) -> p instanceof PromotionAfterEntity)
                .map((p) -> (PromotionAfterEntity) p)
                .map(PromotionAfterCore::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<PromotionAfter> loadAll(Enabled enabled) {
        checkNotNull(enabled, "'enabled' must not be null");
        String namedQuery = "promotion.getAll" + (enabled.get() ? "Enabled" : "Disabled");
        return jpaHelper().createNamedQuery(namedQuery, PromotionEntity.class).getResultList().stream()
                .filter((p) -> p instanceof PromotionAfterEntity)
                .map((p) -> (PromotionAfterEntity) p)
                .map(PromotionAfterCore::new)
                .collect(Collectors.toList());
    }

    @Override
    protected String namedQueryPrefix() {
        return "promotion";
    }
}
