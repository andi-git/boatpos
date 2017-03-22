package org.boatpos.domain.core.repository;

import org.boatpos.common.model.PaymentMethod;
import org.boatpos.common.domain.core.JPAHelper;
import org.boatpos.domain.api.repository.JournalRepository;
import org.boatpos.domain.api.values.BoatCountResult;
import org.boatpos.domain.api.values.IncomeResult;
import org.boatpos.domain.api.values.Period;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import java.util.List;

@Dependent
public class JournalRepositoryCore implements JournalRepository {

    @Inject
    private JPAHelper jpaHelper;

    @Override
    public List<IncomeResult> totalIncomeBeforeFor(Period period, PaymentMethod paymentMethod) {
        return jpaHelper.createNamedQuery("journal.incomeBoatPeriodBefore", IncomeResult.class)
                .setParameter("start", period.getStart())
                .setParameter("end", period.getEnd())
                .setParameter("paymentMethod", paymentMethod)
                .getResultList();
    }

    @Override
    public List<IncomeResult> totalIncomeAfterFor(Period period, PaymentMethod paymentMethod) {
        return jpaHelper.createNamedQuery("journal.incomeBoatPeriodAfter", IncomeResult.class)
                .setParameter("start", period.getStart())
                .setParameter("end", period.getEnd())
                .setParameter("paymentMethod", paymentMethod)
                .getResultList();
    }

    @Override
    public List<BoatCountResult> countBoatFor(Period period) {
        return jpaHelper.createNamedQuery("journal.countBoatPeriod", BoatCountResult.class)
                .setParameter("start", period.getStart())
                .setParameter("end", period.getEnd())
                .getResultList();
    }
}
