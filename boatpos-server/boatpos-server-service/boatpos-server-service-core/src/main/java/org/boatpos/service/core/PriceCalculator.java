package org.boatpos.service.core;

import org.boatpos.repository.api.model.*;
import org.boatpos.repository.api.values.*;
import org.nfunk.jep.JEP;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.Duration;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Component to calculate the price.
 */
@ApplicationScoped
public class PriceCalculator {

    private static final long TIME_BONUS_IN_MINUTES = 5;
    private static final int SCALE_PRICE = 2;
    private static final int SCALE_CALCULATION = 10;
    private static final MathContext MATH_CONTEXT_PRICE = new MathContext(SCALE_PRICE, RoundingMode.HALF_UP);
    private static final MathContext MATH_CONTEXT_CALCULATION = new MathContext(SCALE_CALCULATION, RoundingMode.HALF_UP);
    private static final BigDecimal ZERO = new BigDecimal("0.00");

    @Inject
    private PromotionChecker promotionChecker;

    /**
     * Calculate the price.
     *
     * @param rental the {@link Rental}
     * @return the calculated price
     */
    public PriceCalculatedAfter calculate(Rental rental) {
        return calculate(rental.getDepartureTime(), rental.getArrivalTime(), rental.getBoat(), rental.getPromotion());
    }

    /**
     * Calculate the price.
     *
     * @param departureTime the time of departure
     * @param arrivalTime   the time of arrival
     * @param boat          the {@link Boat} needed to get the price per hour
     * @param promotion     a possible {@link Promotion}
     * @return the calculated price
     */
    public PriceCalculatedAfter calculate(DepartureTime departureTime, ArrivalTime arrivalTime, Boat boat, Optional<Promotion> promotion) {
        checkNotNull(departureTime, "'departureTime' must not be null");
        checkNotNull(departureTime.get(), "'departureTime-value' must not be null");
        checkNotNull(arrivalTime, "'arrivalTime' must not be null");
        checkNotNull(arrivalTime.get(), "'arrivalTime-value' must not be null");
        checkNotNull(boat, "'boat' must not be null");
        // the minutes of the rental
        long minutes = timeInMinutes(departureTime, arrivalTime);
        // 5 minutes bonus
        minutes = subtractTimeBonus(minutes);
        // handle promotionBefore
        minutes = handlePromotionBefore(minutes, promotion);
        // calculate the price
        PriceCalculatedAfter priceCalculatedAfter = calculate(minutes, boat.getPriceOneHour(), boat.getPriceThirtyMinutes(), boat.getPriceFortyFiveMinutes(), promotion);
        // handle promotionAfter
        priceCalculatedAfter = handlePromotionAfter(priceCalculatedAfter, promotion);
        // round to 0.00 or 0.05
        priceCalculatedAfter = round(priceCalculatedAfter);
        // return the calculated price
        return priceCalculatedAfter;
    }

    /**
     * Perform the {@link PromotionAfter} on a {@link PriceCalculatedAfter} to produce a new {@link
     * PriceCalculatedAfter}.
     *
     * @param priceCalculatedAfter the price calculated before the {@link PromotionAfter}
     * @param promotionAfter       the {@link PromotionAfter} to perform
     * @return the new calculated price
     */
    public PriceCalculatedAfter calculate(PriceCalculatedAfter priceCalculatedAfter, PromotionAfter promotionAfter) {
        checkNotNull(priceCalculatedAfter, "'priceCalculated' must not be null");
        checkNotNull(priceCalculatedAfter.get(), "'priceCalculated-value' must not be null");
        checkNotNull(promotionAfter, "'promotionAfter' must not be null");
        if (promotionChecker.active(promotionAfter)) {
            JEP parser = new JEP();
            parser.addVariable("price", priceCalculatedAfter.get());
            parser.parseExpression(promotionAfter.getFormulaPrice().get());
            return round(new PriceCalculatedAfter(scalePrice(new BigDecimal(parser.getValue(), MATH_CONTEXT_CALCULATION))));
        } else {
            return priceCalculatedAfter;
        }
    }

    /**
     * Calculate the price for a {@link PromotionBefore}.
     *
     * @param priceOneHour    the price for one hour
     * @param promotionBefore the {@link PromotionBefore} to perform
     * @return the price to pay for the {@link PromotionBefore}
     */
    public PriceCalculatedBefore calculate(PriceOneHour priceOneHour, PromotionBefore promotionBefore) {
        checkNotNull(priceOneHour, "'priceOneHour' must not be null");
        checkNotNull(priceOneHour.get(), "'priceOneHour-value' must not be null");
        checkNotNull(promotionBefore, "'promotionBefore' must not be null");
        if (promotionChecker.active(promotionBefore)) {
            JEP parser = new JEP();
            parser.addVariable("priceOneHour", priceOneHour.get());
            parser.parseExpression(promotionBefore.getFormulaPrice().get());
            return round(new PriceCalculatedBefore(scalePrice(new BigDecimal(parser.getValue(), MATH_CONTEXT_CALCULATION))));
        } else {
            throw new RuntimeException("Promotion " + promotionBefore + " is not active!");
        }
    }

    private long handlePromotionBefore(long minutes, Optional<Promotion> promotion) {
        long minutesCalculated = minutes;
        if (promotion.isPresent() && promotionChecker.active(promotion.get()) && promotion.get() instanceof PromotionBefore) {
            PromotionBefore promotionBefore = (PromotionBefore) promotion.get();
            minutesCalculated = notMinus(minutes - promotionBefore.getTimeCredit().get());
        }
        return minutesCalculated;
    }

    private PriceCalculatedAfter handlePromotionAfter(PriceCalculatedAfter priceCalculatedAfter, Optional<Promotion> promotion) {
        if (promotion.isPresent() && promotionChecker.active(promotion.get()) && promotion.get() instanceof PromotionAfter) {
            return calculate(priceCalculatedAfter, (PromotionAfter) promotion.get());
        } else {
            return priceCalculatedAfter;
        }
    }

    private long timeInMinutes(DepartureTime departureTime, ArrivalTime arrivalTime) {
        return Duration.between(departureTime.get(), arrivalTime.get()).toMinutes();
    }

    private long subtractTimeBonus(long minutes) {
        return notMinus(minutes - TIME_BONUS_IN_MINUTES);
    }

    private PriceCalculatedAfter calculate(long minutes, PriceOneHour priceOneHour, PriceThirtyMinutes priceThirtyMinutes, PriceFortyFiveMinutes priceFortyFiveMinutes, Optional<Promotion> promotion) {
        BigDecimal priceCalculated;
        boolean promotionBefore = promotion.isPresent() && promotion.get() instanceof PromotionBefore;
        if (!promotionBefore && minutes <= 0) {
            priceCalculated = new BigDecimal("0.00");
        } else if (!promotionBefore && minutes <= 30) {
            priceCalculated = priceThirtyMinutes.get();
        } else if (!promotionBefore && minutes > 30 && minutes <= 45) {
            priceCalculated = priceFortyFiveMinutes.get();
        } else if (!promotionBefore && minutes > 45 && minutes <= 60) {
            priceCalculated = priceOneHour.get();
        } else {
            BigDecimal priceOneMinute = priceOneHour.get().divide(new BigDecimal("60"), SCALE_CALCULATION, BigDecimal.ROUND_HALF_UP);
            priceCalculated = priceOneMinute.multiply(new BigDecimal(minutes, MATH_CONTEXT_PRICE), MATH_CONTEXT_CALCULATION);
        }
        return new PriceCalculatedAfter(scalePrice(priceCalculated));
    }

    /**
     * Round to 10-cents.
     *
     * @param priceCalculatedAfter the calculated price
     * @return the rounded calculated price
     */
    public PriceCalculatedAfter round(PriceCalculatedAfter priceCalculatedAfter) {
        if (priceCalculatedAfter == null || priceCalculatedAfter.get() == null) {
            return new PriceCalculatedAfter(ZERO);
        }
        return new PriceCalculatedAfter(round(priceCalculatedAfter.get()));
    }

    /**
     * Round to 10-cents.
     *
     * @param priceCalculatedBefore the calculated price
     * @return the rounded calculated price
     */
    public PriceCalculatedBefore round(PriceCalculatedBefore priceCalculatedBefore) {
        if (priceCalculatedBefore == null || priceCalculatedBefore.get() == null) {
            return new PriceCalculatedBefore(ZERO);
        }
        return new PriceCalculatedBefore(round(priceCalculatedBefore.get()));
    }

    private BigDecimal round(BigDecimal price) {
        return scalePrice(price.setScale(1, BigDecimal.ROUND_HALF_UP));
    }

    private BigDecimal scalePrice(BigDecimal price) {
        return price.setScale(SCALE_PRICE, BigDecimal.ROUND_HALF_UP);
    }

    private long notMinus(long value) {
        return value < 0 ? 0 : value;
    }
}
