package org.boatpos.dao.core;

import org.boatpos.dao.api.Period;
import org.boatpos.dao.api.RentalDao;
import org.boatpos.model.*;
import org.boatpos.util.datetime.DateTimeHelper;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;

@Dependent
public class RentalDaoCore extends GenericDaoCore<Rental> implements RentalDao {

    @Inject
    private DateTimeHelper dateTimeHelper;

    @Override
    public Class<Rental> getType() {
        return Rental.class;
    }

    @Override
    public Optional<Rental> getByDayId(Integer dayId, LocalDate day) {
        return getSingleResult(createTypedNamedQuery("rental.getByDayId")
                .setParameter("dayId", dayId)
                .setParameter("day", day)
                .getResultList());
    }

    @Override
    public List<Rental> getAllForCurrentDay() {
        return getAllFor(dateTimeHelper.currentDate(), Period.DAY);
    }

    @Override
    public List<Rental> getAllFor(LocalDate day, Period period) {
        LocalDate start = LocalDate.from(day);
        LocalDate end = LocalDate.from(day);
        if (period == Period.YEAR) {
            start = LocalDate.of(day.getYear(), 1, 1);
            end = LocalDate.of(day.getYear(), 12, 31);
        } else if (period == Period.MONTH) {
            start = LocalDate.of(day.getYear(), day.getMonth(), 1);
            end = LocalDate.of(day.getYear(), day.getMonth(), day.lengthOfMonth());
        }
        return createTypedNamedQuery("rental.getBetweenDate")
                .setParameter("start", start)
                .setParameter("end", end)
                .getResultList();
    }

    @Override
    public Integer nextDayId(LocalDate date) {
        checkNotNull(date, "date " + LocalDate.class.getName() + "must not be null");
        Optional<Integer> maxId = getSingleResult(
                createTypedNamedQuery("rental.maxDayId", Integer.class)
                        .setParameter("date", date)
                        .getResultList());
        return maxId.orElseGet(() -> 0) + 1;
    }

    @Override
    public Rental depart(Boat boat, Set<Commitment> commitments, Optional<Promotion> promotion, LocalDateTime time) {
        return save(Rental.builder()
                .setBoat(boat)
                .setCommitments(commitments)
                .setPromotion(promotion)
                .setDayId(nextDayId(LocalDate.from(time)))
                .setDepartTime(time)
                .build());
    }

    @Override
    public Rental arrive(Integer dayId, LocalDateTime time, Optional<Promotion> promotion) {
        Rental rental = getRentalByDayIdOrThrowException(dayId, LocalDate.from(time));
        rental.setArrival(time);
        if (promotion.isPresent()) {
            rental.setPromotion(promotion.get());
        }
        return update(rental);
    }

    @Override
    public Rental pay(Integer dayId, LocalDate day, BigDecimal price, PaymentMethod paymentMethod, boolean coupon) {
        Rental rental = getRentalByDayIdOrThrowException(dayId, day);
        rental.setPrice(price);
        rental.setPaymentMethod(paymentMethod);
        rental.setFinished(true);
        rental.setCoupon(coupon);
        return update(rental);
    }

    private Rental getRentalByDayIdOrThrowException(Integer dayId, LocalDate date) {
        return getByDayId(dayId, date).orElseGet(() -> {
            throw new RuntimeException("unable to get " + Rental.class.getName() + " with dayId " + dayId + " and date " + date);
        });
    }

    @Override
    public Rental save(Rental entity) {
        checkNotNull(entity, "entity " + Rental.class.getName() + " must not be null");
        return super.save(entity);
    }

    @Override
    public void delete(Integer dayId, LocalDate day) {
        changeDeleteTo(dayId, day, true);
    }

    @Override
    public void undoDelete(Integer dayId, LocalDate day) {
        changeDeleteTo(dayId, day, false);
    }

    private void changeDeleteTo(Integer dayId, LocalDate day, boolean value) {
        Optional<Rental> rentalOptional = getByDayId(dayId, day);
        if (rentalOptional.isPresent()) {
            Rental rental = rentalOptional.get();
            rental.setDeleted(value);
            update(rental);
        }
    }

    @Override
    public void delete(Long id) {
        // not possible
    }

    @Override
    public void delete(Rental entity) {
        // not possible
    }
}
