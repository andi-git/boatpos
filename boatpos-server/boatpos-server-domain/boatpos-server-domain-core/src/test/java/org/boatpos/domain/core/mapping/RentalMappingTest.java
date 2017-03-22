package org.boatpos.domain.core.mapping;

import com.google.common.collect.Sets;
import org.boatpos.model.*;
import org.boatpos.service.api.bean.RentalBean;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Arquillian.class)
public class RentalMappingTest {

    @Inject
    private RentalMapping rentalMapping;

    @Test
    public void testMapping() {
        PromotionEntity promotionEntity = new PromotionBeforeEntity();
        promotionEntity.setId(2L);
        promotionEntity.setName("promotion");
        CommitmentEntity commitmentEntity1 = new CommitmentEntity();
        commitmentEntity1.setId(3L);
        commitmentEntity1.setName("commitment1");
        CommitmentEntity commitmentEntity2 = new CommitmentEntity();
        commitmentEntity2.setId(4L);
        commitmentEntity2.setName("commitment2");
        BoatEntity boatEntity = new BoatEntity();
        boatEntity.setId(5L);
        boatEntity.setName("boat");
        RentalEntity rentalEntity = new RentalEntity();
        rentalEntity.setId(1L);
        rentalEntity.setPromotion(promotionEntity);
        rentalEntity.setCommitments(Sets.newHashSet(commitmentEntity1, commitmentEntity2));
        rentalEntity.setBoat(boatEntity);
        rentalEntity.setPricePaidBefore(new BigDecimal("20.0"));

        RentalBean rentalBean = rentalMapping.mapEntity(rentalEntity);
        assertEquals(1L, rentalBean.getId().longValue());
        assertEquals(2, rentalBean.getCommitmentBeans().size());
        assertEquals("boat", rentalBean.getBoatBean().getName());
        assertEquals("promotion", rentalBean.getPromotionBeforeBean().getName());

        rentalEntity = rentalMapping.mapDto(rentalBean);
        assertEquals(1L, rentalEntity.getId().longValue());
        assertEquals(2, rentalEntity.getCommitments().size());
        assertEquals("boat", rentalEntity.getBoat().getName());
        assertEquals("promotion", rentalEntity.getPromotion().getName());
    }

    @Test
    public void testMappingOfDateLocal() {
        RentalEntity entity = new RentalEntity();
        entity.setDay(LocalDate.of(2015, 7, 1));
        RentalBean dto = rentalMapping.mapEntity(entity);
        assertEquals(LocalDate.of(2015, 7, 1), dto.getDay());
    }

    @Test
    public void testMapFinished() {
        RentalEntity entity = new RentalEntity();
        entity.setId(99L);
        entity.setDayId(5);
        entity.setFinished(true);
        RentalBean dto = rentalMapping.mapEntity(entity);
        assertEquals(99L, entity.getId().longValue());
        assertEquals(5, entity.getDayId().intValue());
        assertTrue(dto.isFinished());
    }
}