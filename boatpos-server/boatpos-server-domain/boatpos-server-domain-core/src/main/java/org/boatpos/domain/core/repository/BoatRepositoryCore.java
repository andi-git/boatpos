package org.boatpos.domain.core.repository;

import org.boatpos.common.domain.core.respository.MasterDataRepositoryCore;
import org.boatpos.model.BoatEntity;
import org.boatpos.domain.api.builder.BoatBuilder;
import org.boatpos.domain.api.model.Boat;
import org.boatpos.domain.api.repository.BoatRepository;
import org.boatpos.domain.api.values.Name;
import org.boatpos.domain.api.values.ShortName;
import org.boatpos.domain.core.builder.BoatBuilderCore;
import org.boatpos.domain.core.model.BoatCore;

import javax.enterprise.context.Dependent;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

@Dependent
public class BoatRepositoryCore extends MasterDataRepositoryCore<Boat, BoatCore, BoatEntity, BoatBuilder, BoatBuilderCore> implements BoatRepository {

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
