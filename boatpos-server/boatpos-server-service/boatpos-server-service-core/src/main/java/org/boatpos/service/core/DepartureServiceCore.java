package org.boatpos.service.core;

import org.boatpos.dao.api.BoatDao;
import org.boatpos.dao.api.CommitmentDao;
import org.boatpos.dao.api.PromotionDao;
import org.boatpos.dao.api.RentalDao;
import org.boatpos.model.Boat;
import org.boatpos.model.Commitment;
import org.boatpos.model.Promotion;
import org.boatpos.model.Rental;
import org.boatpos.service.api.DepartureService;
import org.boatpos.service.api.bean.DepartureBean;
import org.boatpos.service.api.bean.RentalBean;
import org.boatpos.service.core.mapping.RentalMapping;
import org.boatpos.util.datetime.DateTimeHelper;

import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RequestScoped
public class DepartureServiceCore implements DepartureService {

    @Inject
    private BoatDao boatDao;

    @Inject
    private CommitmentDao commitmentDao;

    @Inject
    private PromotionDao promotionDao;

    @Inject
    private RentalDao rentalDao;

    @Inject
    private RentalMapping rentalMapping;

    @Inject
    private DateTimeHelper dateTimeHelper;

    @Override
    public RentalBean depart(DepartureBean departureBean) {
        Long boatId = departureBean.getBoatBean().getId();
        Boat boat = getOrThrowException(boatDao.getById(boatId), createExceptionMessage(Boat.class, boatId));
        Set<Commitment> commitments = new HashSet<>();
        if (!departureBean.getCommitmentBeans().isEmpty()) {
            departureBean.getCommitmentBeans().forEach(cb -> commitments.add(getOrThrowException(commitmentDao.getById(cb.getId()), createExceptionMessage(Commitment.class, cb.getId()))));
        }
        Optional<Promotion> promotion = null;
        if (departureBean.getPromotionBean() != null) {
            Long promotionId = departureBean.getPromotionBean().getId();
            promotion = promotionDao.getById(promotionId);
            if (!promotion.isPresent()) {
                throw new RuntimeException(createExceptionMessage(Promotion.class, promotionId));
            }
        }
        return rentalMapping.mapEntity(rentalDao.depart(boat, commitments, promotion, dateTimeHelper.currentTime()));
    }

    private <T> T getOrThrowException(Optional<T> optional, String message) {
        return optional.orElseGet(() -> {
            throw new RuntimeException(message);
        });
    }

    private String createExceptionMessage(Class<?> type, Long id) {
        return "no " + type.getName() + " available with id " + id;
    }
}
