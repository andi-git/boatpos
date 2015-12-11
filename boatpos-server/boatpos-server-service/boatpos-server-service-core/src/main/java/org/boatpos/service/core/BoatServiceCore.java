package org.boatpos.service.core;

import org.boatpos.dao.api.BoatDao;
import org.boatpos.model.Boat;
import org.boatpos.service.api.BoatService;
import org.boatpos.service.api.EnabledState;
import org.boatpos.service.api.bean.BoatBean;
import org.boatpos.service.core.mapping.BoatMapping;
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
    private BoatDao boatDao;

    @Inject
    private BoatMapping boatMapping;

    @Inject
    private CrudHelper crudHelper;

    @Inject
    private MasterDataHelper masterDataHelper;

    @Override
    public List<BoatBean> getAll() {
        return getAll(EnabledState.All);
    }

    @Override
    public List<BoatBean> getAll(EnabledState enabledState) {
        return masterDataHelper.getAll(boatDao, boatMapping, enabledState);
    }

    @Override
    public Optional<BoatBean> getById(Long id) {
        return boatMapping.mapEntity(boatDao.getById(id));
    }

    @Override
    public Optional<BoatBean> getByName(String name) {
        return boatMapping.mapEntity(boatDao.getByName(name));
    }

    @Override
    public Optional<BoatBean> getByShortName(String shortName) {
        return boatMapping.mapEntity(boatDao.getByShortName(shortName));
    }

    @Override
    public BoatBean save(BoatBean boatBean) {
        return boatMapping.mapEntity(boatDao.save(boatMapping.mapDto(boatBean)));
    }

    @Override
    public BoatBean update(BoatBean boatBean) {
        Optional<BoatBean> updatedDto = crudHelper.update(boatBean, boatDao, boatMapping, () -> log.error("unable to update {} {}", Boat.class.getName(), boatBean));
        return crudHelper.getOrNull(updatedDto, () -> {
        });
    }

    @Override
    public void enable(Long id) {
        masterDataHelper.enable(id, boatDao);
    }

    @Override
    public void disable(Long id) {
        masterDataHelper.disable(id, boatDao);
    }
}
