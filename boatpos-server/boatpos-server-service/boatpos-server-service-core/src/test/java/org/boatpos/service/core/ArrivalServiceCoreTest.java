package org.boatpos.service.core;

import org.boatpos.service.api.ArrivalService;
import org.boatpos.service.api.bean.ArrivalBean;
import org.boatpos.service.api.bean.RentalBean;
import org.boatpos.test.model.EntityManagerProviderForBoatpos;
import org.boatpos.util.datetime.DateTimeHelper;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SuppressWarnings({"SqlDialectInspection", "SqlNoDataSourceInspection"})
@RunWith(Arquillian.class)
public class ArrivalServiceCoreTest extends EntityManagerProviderForBoatpos {

    @Inject
    private ArrivalService arrivalService;

    @Inject
    private DateTimeHelper dateTimeHelper;

    @Test
    @Transactional
    public void testArrive() {
        assertEquals(new BigInteger("11"), getEntityManager().createNativeQuery("SELECT COUNT(*) FROM rental").getSingleResult());
        RentalBean rental = arrivalService.arrive(new ArrivalBean(2));
        assertEquals(dateTimeHelper.currentTime(), rental.getArrival());
        assertTrue(rental.isFinished());
        assertEquals(new BigDecimal("34.10"), rental.getPriceCalculated());
        assertEquals(new BigInteger("11"), getEntityManager().createNativeQuery("SELECT COUNT(*) FROM rental").getSingleResult());
    }
}
