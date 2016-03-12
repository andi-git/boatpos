package org.boatpos.repository.core.repository;

import org.boatpos.common.model.AbstractEntity;
import org.boatpos.repository.api.model.MasterData;
import org.boatpos.repository.api.repository.MasterDataRepository;
import org.boatpos.repository.api.values.Enabled;
import org.boatpos.repository.core.model.MasterDataCore;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

public abstract class MasterDataRepositoryCore<MODEL extends MasterData, MODELCORE extends MasterDataCore, ENTITY extends AbstractEntity> extends DomainModelRepositoryCore<MODEL, MODELCORE, ENTITY> implements MasterDataRepository<MODEL> {

    @Override
    public List<MODEL> loadAll() {
        return loadAll(namedQueryPrefix() + ".getAll", (entity) -> {
            try {
                //noinspection unchecked
                return (MODEL) getTypeDomainModelCore().getDeclaredConstructor(getTypeEntity()).newInstance(entity);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    @Override
    public List<MODEL> loadAll(Enabled enabled) {
        checkNotNull(enabled, "'enabled' must not be null");
        return loadAll(namedQueryPrefix() + ".getAll" + (enabled.get() ? "Enabled" : "Disabled"), (entity) -> {
            try {
                //noinspection unchecked
                return (MODEL) getTypeDomainModelCore().getDeclaredConstructor(getTypeEntity()).newInstance(entity);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    protected abstract String namedQueryPrefix();
}
