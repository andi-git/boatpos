package org.boatpos.common.repository.core.boat;

import org.boatpos.common.repository.core.respository.MasterDataRepositoryCore;

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
    protected String namedQueryPrefix() {
        return "boat";
    }
}
