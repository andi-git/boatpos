package org.boatpos.service.core;

import com.google.common.collect.Sets;
import org.boatpos.service.api.BoatService;
import org.boatpos.service.api.CommitmentService;
import org.boatpos.service.api.DepartureService;
import org.boatpos.service.api.PromotionService;
import org.boatpos.service.api.bean.DepartureBean;
import org.boatpos.service.api.bean.RentalBean;
import org.boatpos.test.model.EntityManagerProviderForBoatpos;
import org.boatpos.util.datetime.DateTimeHelper;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

@SuppressWarnings({"SqlDialectInspection", "SqlNoDataSourceInspection"})
@RunWith(Arquillian.class)
public class DepartureServiceCoreTest extends EntityManagerProviderForBoatpos {

    @Inject
    private DepartureService departureService;

    @Inject
    private BoatService boatService;

    @Inject
    private PromotionService promotionService;

    @Inject
    private CommitmentService commitmentService;

    @Inject
    private DateTimeHelper dateTimeHelper;

    @Test
    @Transactional
    public void testDepart() {
        assertEquals(new BigInteger("11"), getEntityManager().createNativeQuery("SELECT COUNT(*) FROM rental").getSingleResult());
        DepartureBean departureBean = new DepartureBean();
        departureBean.setBoatBean(boatService.getByShortName("E").get());
        departureBean.setPromotionBean(promotionService.getByName("Fahr 3 zahl 2").get());
        departureBean.setCommitmentBeans(Sets.newHashSet(commitmentService.getByName("Ausweis").get()));
        RentalBean rental = departureService.depart(departureBean);
        assertEquals(dateTimeHelper.currentTime(), rental.getDeparture());
        assertEquals(new BigInteger("12"), getEntityManager().createNativeQuery("SELECT COUNT(*) FROM rental").getSingleResult());
    }
}
