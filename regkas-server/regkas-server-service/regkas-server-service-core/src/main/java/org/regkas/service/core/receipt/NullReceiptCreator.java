package org.regkas.service.core.receipt;

import com.google.common.collect.Lists;
import org.regkas.service.api.bean.SaleBean;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class NullReceiptCreator {

    public SaleBean createNullSale() {
        return new SaleBean("cash", "Null-Beleg", Lists.newArrayList());
    }
}