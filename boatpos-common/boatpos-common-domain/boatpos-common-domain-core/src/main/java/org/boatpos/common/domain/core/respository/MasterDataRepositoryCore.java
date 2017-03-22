package org.boatpos.common.domain.core.respository;

import org.boatpos.common.model.AbstractEntity;
import org.boatpos.common.domain.api.builder.MasterDataBuilder;
import org.boatpos.common.domain.api.model.MasterData;
import org.boatpos.common.domain.api.repository.MasterDataRepository;
import org.boatpos.common.domain.api.values.Enabled;
import org.boatpos.common.domain.core.builder.MasterDataBuilderCore;
import org.boatpos.common.domain.core.model.MasterDataCore;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public abstract class MasterDataRepositoryCore<MODEL extends MasterData, MODELCORE extends MasterDataCore, ENTITY extends AbstractEntity, BUILDER extends MasterDataBuilder, BUILDERCORE extends MasterDataBuilderCore>
        extends DomainModelRepositoryCore<MODEL, MODELCORE, ENTITY, BUILDER, BUILDERCORE>
        implements MasterDataRepository<MODEL, BUILDER> {

    @SuppressWarnings("unchecked")
    @Override
    public List<MODEL> loadAll() {
        return loadAll(namedQueryPrefix() + ".getAll", (entity) -> {
            try {
                return (MODEL) getTypeDomainModelCore().getDeclaredConstructor(getTypeEntity()).newInstance(entity);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<MODEL> loadAll(Enabled enabled) {
        checkNotNull(enabled, "'enabled' must not be null");
        return loadAll(namedQueryPrefix() + ".getAll" + (enabled.get() ? "Enabled" : "Disabled"), (entity) -> {
            try {
                return (MODEL) getTypeDomainModelCore().getDeclaredConstructor(getTypeEntity()).newInstance(entity);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    protected abstract String namedQueryPrefix();

    protected String queryName(String suffix) {
        return namedQueryPrefix() + "." + suffix;
    }
}
