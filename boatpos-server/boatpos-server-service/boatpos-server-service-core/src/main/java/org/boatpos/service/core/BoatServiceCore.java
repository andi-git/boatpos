package org.boatpos.service.core;

import org.boatpos.repository.api.repository.BoatRepository;
import org.boatpos.repository.api.values.DomainId;
import org.boatpos.repository.api.values.Name;
import org.boatpos.repository.api.values.ShortName;
import org.boatpos.service.api.BoatService;
import org.boatpos.service.api.EnabledState;
import org.boatpos.service.api.bean.BoatBean;
import org.boatpos.service.core.util.CrudHelper;
import org.boatpos.service.core.util.MasterDataHelper;
import org.boatpos.util.log.LogWrapper;
import org.boatpos.util.log.SLF4J;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@RequestScoped
public class BoatServiceCore implements BoatService {

    @Inject
    @SLF4J
    private LogWrapper log;

    @Inject
    private BoatRepository boatRepository;

    @Inject
    private CrudHelper crudHelper;

    @Inject
    private MasterDataHelper masterDataHelper;

    @Override
    public List<BoatBean> getAll() {
        return crudHelper.convert(boatRepository.loadAll());
    }

    @Override
    public List<BoatBean> getAll(EnabledState enabledState) {
        return crudHelper.convert(masterDataHelper.loadAll(boatRepository, enabledState));
    }

    @Override
    public Optional<BoatBean> getById(Long id) {
        return crudHelper.convert(boatRepository.loadBy(new DomainId(id)));
    }

    @Override
    public Optional<BoatBean> getByName(String name) {
        return crudHelper.convert(boatRepository.loadBy(new Name(name)));
    }

    @Override
    public Optional<BoatBean> getByShortName(String shortName) {
        return crudHelper.convert(boatRepository.loadBy(new ShortName(shortName)));
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
