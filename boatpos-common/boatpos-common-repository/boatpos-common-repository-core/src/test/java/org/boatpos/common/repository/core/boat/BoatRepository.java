package org.boatpos.common.repository.core.boat;

import org.boatpos.common.repository.api.repository.MasterDataRepository;

import java.util.Optional;

public interface BoatRepository extends MasterDataRepository<Boat> {

    BoatBuilder builder();

    Optional<Boat> loadBy(Name name);
}
