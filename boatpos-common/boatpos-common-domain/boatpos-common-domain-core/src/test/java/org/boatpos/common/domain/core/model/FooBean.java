package org.boatpos.common.domain.core.model;

import org.boatpos.common.service.api.bean.AbstractBeanBasedOnEntity;

@SuppressWarnings("unused")
public class FooBean extends AbstractBeanBasedOnEntity {

    public FooBean() {
    }

    public FooBean(Long id, Integer version) {
        super(id, version);
    }
}
