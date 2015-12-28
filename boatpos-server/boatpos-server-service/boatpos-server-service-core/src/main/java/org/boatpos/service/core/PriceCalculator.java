package org.boatpos.service.core;

import org.boatpos.repository.api.model.Boat;
import org.boatpos.repository.api.model.Promotion;
import org.boatpos.repository.api.model.PromotionAfter;
import org.boatpos.repository.api.model.PromotionBefore;
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

    @Inject
    private PromotionChecker promotionChecker;

    /**
     * Calculate the price.
     *
     * @param departureTime the time of departure
     * @param arrivalTime   the time of arrival
     * @param boat          the {@link Boat} needed to get the price per hour
     * @param promotion     a possible {@link Promotion}
     * @return the calculated price
     */
    public PriceCalculated calculate(DepartureTime departureTime, ArrivalTime arrivalTime, Boat boat, Optional<Promotion> promotion) {
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
        PriceCalculated priceCalculated = calculate(minutes, boat.getPriceOneHour(), boat.getPriceThirtyMinutes(), boat.getPriceFortyFiveMinutes(), promotion);
        // handle promotionAfter
        priceCalculated = handlePromotionAfter(priceCalculated, promotion);
        // round to 0.00 or 0.05
        priceCalculated = round(priceCalculated);
        // return the calculated price
        return priceCalculated;
    }

    /**
     * Perform the {@link PromotionAfter} on a {@link PriceCalculated} to produce a new {@link PriceCalculated}.
     *
     * @param priceCalculated the price calculated before the {@link PromotionAfter}
     * @param promotionAfter  the {@link PromotionAfter} to perform
     * @return the new calculated price
     */
    public PriceCalculated calculate(PriceCalculated priceCalculated, PromotionAfter promotionAfter) {
        checkNotNull(priceCalculated, "'priceCalculated' must not be null");
        checkNotNull(priceCalculated.get(), "'priceCalculated-value' must not be null");
        checkNotNull(promotionAfter, "'promotionAfter' must not be null");
        if (promotionChecker.active(promotionAfter)) {
            JEP parser = new JEP();
            parser.addVariable("price", priceCalculated.get());
            parser.parseExpression(promotionAfter.getFormulaPrice().get());
            return round(new PriceCalculated(scalePrice(new BigDecimal(parser.getValue(), MATH_CONTEXT_CALCULATION))));
        } else {
            return priceCalculated;
        }
    }

    /**
     * Calculate the price for a {@link PromotionBefore}.
     *
     * @param priceOneHour    the price for one hour
     * @param promotionBefore the {@link PromotionBefore} to perform
     * @return the price to pay for the {@link PromotionBefore}
     */
    public PriceCalculated calculate(PriceOneHour priceOneHour, PromotionBefore promotionBefore) {
        checkNotNull(priceOneHour, "'priceOneHour' must not be null");
        checkNotNull(priceOneHour.get(), "'priceOneHour-value' must not be null");
        checkNotNull(promotionBefore, "'promotionBefore' must not be null");
        if (promotionChecker.active(promotionBefore)) {
            JEP parser = new JEP();
            parser.addVariable("priceOneHour", priceOneHour.get());
            parser.parseExpression(promotionBefore.getFormulaPrice().get());
            return round(new PriceCalculated(scalePrice(new BigDecimal(parser.getValue(), MATH_CONTEXT_CALCULATION))));
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

    private PriceCalculated handlePromotionAfter(PriceCalculated priceCalculated, Optional<Promotion> promotion) {
        if (promotion.isPresent() && promotionChecker.active(promotion.get()) && promotion.get() instanceof PromotionAfter) {
            return calculate(priceCalculated, (PromotionAfter) promotion.get());
        } else {
            return priceCalculated;
        }
    }

    private long timeInMinutes(DepartureTime departureTime, ArrivalTime arrivalTime) {
        return Duration.between(departureTime.get(), arrivalTime.get()).toMinutes();
    }

    private long subtractTimeBonus(long minutes) {
        return notMinus(minutes - TIME_BONUS_IN_MINUTES);
    }

    private PriceCalculated calculate(long minutes, PriceOneHour priceOneHour, PriceThirtyMinutes priceThirtyMinutes, PriceFortyFiveMinutes priceFortyFiveMinutes, Optional<Promotion> promotion) {
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
        return new PriceCalculated(scalePrice(priceCalculated));
    }

    private PriceCalculated round(PriceCalculated priceCalculated) {
        return CentAmount.get(priceCalculated).round(priceCalculated);
    }

    private BigDecimal scalePrice(BigDecimal price) {
        return price.setScale(SCALE_PRICE, BigDecimal.ROUND_HALF_UP);
    }

    private long notMinus(long value) {
        return value < 0 ? 0 : value;
    }

    public enum CentAmount {
        C_0K00(new BigDecimal("0.00"), new BigDecimal("0.00")),
        C_0K01(new BigDecimal("0.01"), new BigDecimal("-0.01")),
        C_0K02(new BigDecimal("0.02"), new BigDecimal("-0.02")),
        C_0K03(new BigDecimal("0.03"), new BigDecimal("0.02")),
        C_0K04(new BigDecimal("0.04"), new BigDecimal("0.01")),
        C_0K05(new BigDecimal("0.05"), new BigDecimal("0.00")),
        C_0K06(new BigDecimal("0.06"), new BigDecimal("-0.01")),
        C_0K07(new BigDecimal("0.07"), new BigDecimal("-0.02")),
        C_0K08(new BigDecimal("0.08"), new BigDecimal("0.02")),
        C_0K09(new BigDecimal("0.09"), new BigDecimal("0.01"));

        private final BigDecimal centAmount;

        private final BigDecimal amountToRound;

        CentAmount(BigDecimal centAmount, BigDecimal amountToRound) {
            this.centAmount = centAmount;
            this.amountToRound = amountToRound;
        }

        public BigDecimal getCentAmount() {
            return centAmount;
        }

        public static CentAmount get(PriceCalculated priceCalculated) {
            checkNotNull(priceCalculated, "'priceCalculated' must not be null");
            checkNotNull(priceCalculated.get(), "'priceCalculated-value' must not be null");
            CentAmount result = C_0K00;
            BigDecimal assignedCentAmount = priceCalculated.get().subtract(priceCalculated.get().setScale(1, RoundingMode.DOWN)).setScale(2, BigDecimal.ROUND_HALF_UP);
            for (CentAmount centAmount : values()) {
                if (centAmount.getCentAmount().equals(assignedCentAmount)) {
                    result = centAmount;
                    break;
                }
            }
            return result;
        }

        public PriceCalculated round(PriceCalculated priceCalculated) {
            return new PriceCalculated(priceCalculated.get().add(amountToRound, MATH_CONTEXT_CALCULATION).setScale(SCALE_PRICE, BigDecimal.ROUND_HALF_UP));
        }
    }
}
