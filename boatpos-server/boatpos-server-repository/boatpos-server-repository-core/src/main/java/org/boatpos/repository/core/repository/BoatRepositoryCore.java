package org.boatpos.repository.core.repository;

import org.boatpos.model.BoatEntity;
import org.boatpos.repository.api.builder.BoatBuilder;
import org.boatpos.repository.api.model.Boat;
import org.boatpos.repository.api.repository.BoatRepository;
import org.boatpos.repository.api.values.Name;
import org.boatpos.repository.api.values.ShortName;
import org.boatpos.repository.core.builder.BoatBuilderCore;
import org.boatpos.repository.core.model.BoatCore;

import javax.enterprise.context.Dependent;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

@Dependent
public class BoatRepositoryCore extends MasterDataRepositoryCore<Boat, BoatCore, BoatEntity> implements BoatRepository {

    @Override
    public BoatBuilder builder() {
        return new BoatBuilderCore();
    }

    @Override
    public Optional<Boat> loadBy(Name name) {
        checkNotNull(name, "'name' must not be null");
        return loadByParameter("boat.getByName", (query) -> query.setParameter("name", name.get()));
    }

    @Override
    public Optional<Boat> loadBy(ShortName shortName) {
        checkNotNull(shortName, "'shortName' must not be null");
        return loadByParameter("boat.getByShortName", (query) -> query.setParameter("shortName", shortName.get()));
    }

    @Override
    protected String namedQueryPrefix() {
        return "boat";
    }
}
