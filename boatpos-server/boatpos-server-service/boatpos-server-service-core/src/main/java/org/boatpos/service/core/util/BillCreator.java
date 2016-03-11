package org.boatpos.service.core.util;

import com.google.common.collect.Lists;
import org.boatpos.repository.api.model.Rental;
import org.boatpos.service.api.bean.BillBean;
import org.boatpos.service.api.bean.CompanyBean;
import org.boatpos.service.api.bean.TaxSetBean;
import org.boatpos.common.util.datetime.DateTimeHelper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * Create a bill.
 */
@ApplicationScoped
public class BillCreator {

    @Inject
    private DateTimeHelper dateTimeHelper;

    public BillBean create(Rental rental) {
        return new BillBean(new CompanyBean("Bootsvermietung EPPEL", "Wagramerstr. 48a", "1220", "Wien", "+4312633530", "office@eppel-boote.at", "1234567890"),
                "Kassa_01",
                createIdentifier(rental),
                dateTimeHelper.currentTime(),
                Lists.newArrayList(new TaxSetBean.TaxSetNormalBean(rental.getBoat().getName().get(), rental.getPricePaidComplete().get(), 1)),
                Lists.newArrayList(),
                Lists.newArrayList(),
                Lists.newArrayList(),
                Lists.newArrayList());
    }

    private String createIdentifier(Rental rental) {
        return rental.getDay().get() + "-" + rental.getDayId().get();
    }
}
