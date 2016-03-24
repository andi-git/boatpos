package org.boatpos.common.repository.core.model;

import org.boatpos.common.service.api.bean.AbstractBeanBasedOnEntity;

@SuppressWarnings("unused")
public class BarBean extends AbstractBeanBasedOnEntity {

    public BarBean() {
    }

    public BarBean(Long id, Integer version) {
        super(id, version);
    }
}
