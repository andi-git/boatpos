package org.boatpos.dao.core;

import org.boatpos.dao.api.GenericMasterDataDao;
import org.boatpos.model.AbstractMasterDataEntity;

import java.util.List;

public abstract class GenericMasterDataDaoCore<ENTITY extends AbstractMasterDataEntity> extends GenericDaoCore<ENTITY> implements GenericMasterDataDao<ENTITY> {

    @Override
    public List<ENTITY> getAll() {
        return createNamedQuery(nameForGetAll()).getResultList();
    }

    @Override
    public List<ENTITY> getAllEnabled() {
        return createNamedQuery(nameForGetAllEnabled()).getResultList();
    }

    @Override
    public List<ENTITY> getAllDisabled() {
        return createNamedQuery(nameForGetAllDisabled()).getResultList();
    }

    @Override
    public void delete(Long id) {
        // delete not supported
    }

    @Override
    public void delete(ENTITY entity) {
        // delete not supported
    }

    protected abstract String nameForGetAll();

    protected abstract String nameForGetAllEnabled();

    protected abstract String nameForGetAllDisabled();
}