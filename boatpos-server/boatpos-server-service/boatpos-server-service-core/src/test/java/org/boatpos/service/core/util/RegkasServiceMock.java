package org.boatpos.service.core.util;

import org.boatpos.service.api.bean.PaymentBean;
import org.regkas.service.api.bean.BillBean;
import org.regkas.service.api.bean.CompanyBean;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Specializes;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Dependent
@Specializes
public class RegkasServiceMock extends RegkasService {

    @Override
    public BillBean sale(PaymentBean paymentBean) throws Exception {
        return new BillBean(
                new CompanyBean(),
                "cashboxId",
                "receipt-id",
                LocalDateTime.now(),
                paymentBean.getValue(),
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                BigDecimal.ZERO,
                new ArrayList<>(),
                paymentBean.getValue(),
                "Standard-Beleg",
                "");
    }
}
