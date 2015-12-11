package org.boatpos.service.core.util;

import org.boatpos.dao.api.GenericDao;
import org.boatpos.dao.api.GenericMasterDataDao;
import org.boatpos.model.AbstractMasterDataEntity;
import org.boatpos.service.api.EnabledState;
import org.boatpos.service.api.bean.AbstractMasterDataBean;
import org.boatpos.service.core.mapping.Mapping;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

/**
 * Component with some methods to handle master-data.
 */
@Dependent
public class MasterDataHelper {

    @Inject
    private CrudHelper crudHelper;

    public <ENTITY extends AbstractMasterDataEntity, DTO extends AbstractMasterDataBean> List<DTO> getAll(GenericMasterDataDao<ENTITY> dao, Mapping<ENTITY, DTO> mapping, EnabledState enabledState) {
        List<ENTITY> entities;
        if (EnabledState.Enabled == enabledState) {
            entities = dao.getAllEnabled();
        } else if (EnabledState.Disabled == enabledState) {
            entities = dao.getAllDisabled();
        } else {
            entities = dao.getAll();
        }
        return mapping.mapEntities(entities);
    }

    /**
     * Set {@link AbstractMasterDataEntity#enabled} to {@code true}.
     *
     * @param id       the {@link org.boatpos.model.AbstractEntity#id} of the entity
     * @param dao      the dao to perform the database-operations
     * @param <ENTITY> the type of the entity
     */
    public <ENTITY extends AbstractMasterDataEntity> void enable(Long id, GenericDao<ENTITY> dao) {
        changeEnabledState(id, dao, EnabledState.Enabled);
    }

    /**
     * Set {@link AbstractMasterDataEntity#enabled} to {@code false}.
     *
     * @param id       the {@link org.boatpos.model.AbstractEntity#id} of the entity
     * @param dao      the dao to perform the database-operations
     * @param <ENTITY> the type of the entity
     */
    public <ENTITY extends AbstractMasterDataEntity> void disable(Long id, GenericDao<ENTITY> dao) {
        changeEnabledState(id, dao, EnabledState.Disabled);
    }

    /**
     * Change the enabled- / disabled-state of the {@link ENTITY}.
     *
     * @param id           the id of the {@link ENTITY} to change
     * @param dao          the {@link GenericDao} to perform the search- and update-operation
     * @param enabledState the state to change to
     * @param <ENTITY>     the type of the entity
     */
    protected <ENTITY extends AbstractMasterDataEntity> void changeEnabledState(Long id, GenericDao<ENTITY> dao, EnabledState enabledState) {
        Optional<ENTITY> optional = dao.getById(id);
        if (optional.isPresent()) {
            ENTITY entity = optional.get();
            if (enabledState == EnabledState.Enabled) {
                entity.setEnabled(true);
                dao.update(entity);
            } else if (enabledState == EnabledState.Disabled) {
                entity.setEnabled(false);
                dao.update(entity);
            }
        }
    }

}
