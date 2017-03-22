package org.boatpos.common.repository.core.builder;

import org.boatpos.common.repository.api.model.DomainModel;
import org.boatpos.common.repository.core.model.DomainModelCore;
import org.boatpos.common.service.api.bean.AbstractBeanBasedOnEntity;

import javax.enterprise.context.ApplicationScoped;

public class DtoToModelConverter {

    private DtoToModelConverter() {

    }

    public static <MODEL extends DomainModel, DTO extends AbstractBeanBasedOnEntity, MODELCORE extends DomainModelCore> MODEL from(DTO dto, Class<MODELCORE> domainModelCoreClass) {
        try {
            //noinspection unchecked
            return (MODEL) domainModelCoreClass.getDeclaredConstructor(dto.getClass()).newInstance(dto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
