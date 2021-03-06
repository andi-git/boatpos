package org.boatpos.service.core;

import org.boatpos.common.domain.api.values.DomainId;
import org.boatpos.common.domain.api.values.Enabled;
import org.boatpos.common.service.core.MasterDataHelper;
import org.boatpos.common.service.core.ModelDtoConverter;
import org.boatpos.domain.api.model.Rental;
import org.boatpos.domain.api.repository.BoatRepository;
import org.boatpos.domain.api.repository.RentalRepository;
import org.boatpos.domain.api.values.Name;
import org.boatpos.domain.api.values.ShortName;
import org.boatpos.service.api.BoatService;
import org.boatpos.common.service.api.EnabledState;
import org.boatpos.service.api.bean.BoatBean;
import org.boatpos.service.api.bean.BoatCountBean;
import org.boatpos.common.util.log.LogWrapper;
import org.boatpos.common.util.log.SLF4J;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestScoped
public class BoatServiceCore implements BoatService {

    @Inject
    @SLF4J
    private LogWrapper log;

    @Inject
    private BoatRepository boatRepository;

    @Inject
    private ModelDtoConverter modelDtoConverter;

    @Inject
    private MasterDataHelper masterDataHelper;

    @Inject
    private RentalRepository rentalRepository;

    @Override
    public List<BoatBean> getAll() {
        return modelDtoConverter.convert(boatRepository.loadAll());
    }

    @Override
    public List<BoatBean> getAll(EnabledState enabledState) {
        return modelDtoConverter.convert(masterDataHelper.loadAll(boatRepository, enabledState));
    }

    @Override
    public Optional<BoatBean> getById(Long id) {
        return modelDtoConverter.convert(boatRepository.loadBy(new DomainId(id)));
    }

    @Override
    public Optional<BoatBean> getByName(String name) {
        return modelDtoConverter.convert(boatRepository.loadBy(new Name(name)));
    }

    @Override
    public Optional<BoatBean> getByShortName(String shortName) {
        return modelDtoConverter.convert(boatRepository.loadBy(new ShortName(shortName)));
    }

    @Override
    public List<BoatCountBean> countBoats() {
        List<BoatCountBean> boatCounts = new ArrayList<>();
        List<Rental> rentals = rentalRepository.loadAllActive();
        boatCounts.addAll(boatRepository.loadAll(Enabled.TRUE).stream().map(boat -> new BoatCountBean(
                boat.getId().get(),
                boat.getName().get(),
                boat.getShortName().get(),
                (int) rentals.stream().filter(r -> r.getBoat().equals(boat)).count(),
                boat.getCount().get())).collect(Collectors.toList()));
        return boatCounts;
    }

    @Override
    public BoatBean save(BoatBean boatBean) {
        return boatRepository.builder().from(boatBean).persist().asDto();
    }

    @Override
    public BoatBean update(BoatBean boatBean) {
        return boatRepository.builder().from(boatBean).persist().asDto();
    }

    @Override
    public void enable(Long id) {
        masterDataHelper.enable(boatRepository, new DomainId(id));
    }

    @Override
    public void disable(Long id) {
        masterDataHelper.disable(boatRepository, new DomainId(id));
    }
}
