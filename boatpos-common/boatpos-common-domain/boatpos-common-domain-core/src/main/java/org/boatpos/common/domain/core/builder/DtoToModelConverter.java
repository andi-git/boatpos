package org.boatpos.common.domain.core.builder;

import org.boatpos.common.domain.api.model.DomainModel;
import org.boatpos.common.domain.core.model.DomainModelCore;
import org.boatpos.common.service.api.bean.AbstractBeanBasedOnEntity;

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
