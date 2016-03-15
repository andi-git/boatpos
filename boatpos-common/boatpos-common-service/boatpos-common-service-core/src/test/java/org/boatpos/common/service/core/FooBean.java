package org.boatpos.common.service.core;

import org.boatpos.common.service.api.bean.AbstractMasterDataBean;

@SuppressWarnings("unused")
public class FooBean extends AbstractMasterDataBean {

    public FooBean() {
    }

    public FooBean(Long id, Integer version, boolean enabled, Integer priority, Character keyBinding, String pictureUrl, String pictureUrlThumb) {
        super(id, version, enabled, priority, keyBinding, pictureUrl, pictureUrlThumb);
    }
}
