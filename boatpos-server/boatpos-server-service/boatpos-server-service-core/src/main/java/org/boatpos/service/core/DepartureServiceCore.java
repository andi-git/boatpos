package org.boatpos.service.core;

import org.boatpos.repository.api.model.Boat;
import org.boatpos.repository.api.model.Commitment;
import org.boatpos.repository.api.model.PromotionBefore;
import org.boatpos.repository.api.repository.BoatRepository;
import org.boatpos.repository.api.repository.CommitmentRepository;
import org.boatpos.repository.api.repository.PromotionBeforeRepository;
import org.boatpos.repository.api.repository.RentalRepository;
import org.boatpos.repository.api.values.Day;
import org.boatpos.repository.api.values.DepartureTime;
import org.boatpos.repository.api.values.DomainId;
import org.boatpos.repository.api.values.SimpleValueObject;
import org.boatpos.service.api.DepartureService;
import org.boatpos.service.api.bean.DepartureBean;
import org.boatpos.service.api.bean.RentalBean;
import org.boatpos.util.datetime.DateTimeHelper;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RequestScoped
public class DepartureServiceCore implements DepartureService {

    @Inject
    private BoatRepository boatRepository;

    @Inject
    private CommitmentRepository commitmentRepository;

    @Inject
    private PromotionBeforeRepository promotionBeforeRepository;

    @Inject
    private RentalRepository rentalRepository;

    @Inject
    private DateTimeHelper dateTimeHelper;

    @Override
    public RentalBean depart(DepartureBean departureBean) {
        return rentalRepository
                .depart(
                        new Day(dateTimeHelper.currentDate()),
                        new DepartureTime(dateTimeHelper.currentTime()),
                        getBoat(departureBean),
                        getCommitments(departureBean),
                        getPromotionBefore(departureBean))
                .asDto();
    }

    private Optional<PromotionBefore> getPromotionBefore(DepartureBean departureBean) {
        return promotionBeforeRepository.loadBy(new DomainId(departureBean.getPromotionId()));
    }

    private Set<Commitment> getCommitments(DepartureBean departureBean) {
        Set<Commitment> commitments = new HashSet<>();
        if (!departureBean.getCommitmentIds().isEmpty()) {
            departureBean.getCommitmentIds().forEach(
                    cb -> commitments.add(getOrThrowException(commitmentRepository.loadBy(new DomainId(cb)), createExceptionMessage(Commitment.class, new DomainId(cb))))
            );
        }
        return commitments;
    }

    private Boat getBoat(DepartureBean departureBean) {
        DomainId boatId = new DomainId(departureBean.getBoatId());
        return getOrThrowException(boatRepository.loadBy(boatId), createExceptionMessage(Boat.class, boatId));
    }

    private <T> T getOrThrowException(Optional<T> optional, String message) {
        return optional.orElseGet(() -> {
            throw new RuntimeException(message);
        });
    }

    private String createExceptionMessage(Class<?> type, SimpleValueObject simpleValueObject) {
        return "no " + type.getName() + " available with fot " + simpleValueObject;
    }
}
