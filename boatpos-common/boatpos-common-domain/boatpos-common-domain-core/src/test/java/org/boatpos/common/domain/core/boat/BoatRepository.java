package org.boatpos.common.domain.core.boat;

import org.boatpos.common.domain.api.repository.MasterDataRepository;

import java.util.Optional;

public interface BoatRepository extends MasterDataRepository<Boat, BoatBuilder> {

    BoatBuilder builder();

    Optional<Boat> loadBy(Name name);

    Boat loadEBoot();
}
