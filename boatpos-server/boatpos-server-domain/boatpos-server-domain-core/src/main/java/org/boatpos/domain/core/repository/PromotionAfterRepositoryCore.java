package org.boatpos.domain.core.repository;

import org.boatpos.common.domain.api.values.Enabled;
import org.boatpos.common.domain.core.respository.MasterDataRepositoryCore;
import org.boatpos.domain.core.builder.PromotionAfterBuilderCore;
import org.boatpos.domain.core.model.PromotionAfterCore;
import org.boatpos.model.PromotionAfterEntity;
import org.boatpos.model.PromotionEntity;
import org.boatpos.domain.api.builder.PromotionAfterBuilder;
import org.boatpos.domain.api.model.PromotionAfter;
import org.boatpos.domain.api.repository.PromotionAfterRepository;
import org.boatpos.domain.api.values.Name;

import javax.enterprise.context.Dependent;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;

@Dependent
public class PromotionAfterRepositoryCore extends MasterDataRepositoryCore<PromotionAfter, PromotionAfterCore, PromotionAfterEntity, PromotionAfterBuilder, PromotionAfterBuilderCore> implements PromotionAfterRepository {

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
