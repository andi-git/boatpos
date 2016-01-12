package org.boatpos.repository.core.repository;

import org.boatpos.repository.api.BoatPosDB;
import org.boatpos.repository.api.model.Boat;
import org.boatpos.repository.api.repository.JournalRepository;
import org.boatpos.repository.api.values.Period;
import org.boatpos.repository.api.values.SumPaid;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

@Dependent
public class JournalRepositoryCore implements JournalRepository {

    @Inject
    private JPAHelper jpaHelper;

    @Override
    public SumPaid sum(Boat boat, Period period) {
        //noinspection JpaQueryApiInspection
        Object[] queryResult = jpaHelper.getEntityManager()
                .createNamedQuery("journal.sumBoatPeriod", Object[].class)
                .setParameter("boat", boat.asEntity())
                .setParameter("start", period.getStart())
                .setParameter("end", period.getEnd())
                .getSingleResult();
        BigDecimal result = new BigDecimal("0.00");
        for (Object qr : queryResult) {
            if (qr != null) {
                result = result.add((BigDecimal) qr);
            }
        }
        return new SumPaid(result);
    }
}
