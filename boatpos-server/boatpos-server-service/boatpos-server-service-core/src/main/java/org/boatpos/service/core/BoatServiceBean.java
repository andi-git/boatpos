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

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@Stateless
public class BoatServiceBean implements BoatService {

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
    public BoatBean getById(Long id) {
        return crudHelper.getOrNull(boatDao.getById(id), boatMapping, () -> log.error("no {} available with id {}", Boat.class.getName(), id));
    }

    @Override
    public BoatBean getByName(String name) {
        return crudHelper.getOrNull(boatDao.getByName(name), boatMapping, () -> log.error("no {} available with name {}", Boat.class.getName(), name));
    }

    @Override
    public BoatBean getByShortName(String shortName) {
        return crudHelper.getOrNull(boatDao.getByShortName(shortName), boatMapping, () -> log.error("no {} available with shortName {}", Boat.class.getName(), shortName));
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
