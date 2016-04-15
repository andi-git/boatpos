package org.regkas.service.core;

import org.boatpos.common.repository.api.values.DomainId;
import org.boatpos.common.repository.api.values.Enabled;
import org.boatpos.common.util.log.LogWrapper;
import org.boatpos.common.util.log.SLF4J;
import org.boatpos.common.util.qualifiers.Current;
import org.regkas.repository.api.model.CashBox;
import org.regkas.repository.api.model.ProductGroup;
import org.regkas.repository.api.repository.ProductGroupRepository;
import org.regkas.repository.api.repository.ReceiptRepository;
import org.regkas.service.api.JournalService;
import org.regkas.service.api.bean.IncomeBean;
import org.regkas.service.api.bean.Period;
import org.regkas.service.api.bean.ProductGroupIncomeBean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.google.common.base.Preconditions.checkNotNull;

@RequestScoped
public class JournalServiceCore implements JournalService {

    @Inject
    private ReceiptRepository receiptRepository;

    @Inject
    private ProductGroupRepository productGroupRepository;

    @Inject
    @Current
    private CashBox cashBox;

    @Inject
    @SLF4J
    private LogWrapper log;

    @Override
    public IncomeBean totalIncomeFor(Integer year) {
        return totalIncomeFor(Period.year(LocalDateTime.of(year, 1, 1, 0, 0)));
    }

    @Override
    public IncomeBean totalIncomeFor(Integer year, Integer month) {
        return totalIncomeFor(Period.month(LocalDateTime.of(year, month, 1, 0, 0)));
    }

    @Override
    public IncomeBean totalIncomeFor(Integer year, Integer month, Integer dayOfMonth) {
        return totalIncomeFor(Period.day(LocalDateTime.of(year, month, dayOfMonth, 0, 0)));
    }

    private IncomeBean totalIncomeFor(Period period) {
        checkNotNull(period, "'period' must not be null");
        log.info("calculate total income for {} - {}", period.getStartDay(), period.getEndDay());
        IncomeBean income = new IncomeBean();

        List<ProductGroup> productGroups = productGroupRepository.loadBy(cashBox, Enabled.TRUE);
        Map<DomainId, ProductGroupIncomeBean> productGroupIncomes = new HashMap<>();
        productGroups.stream().forEach(pg -> productGroupIncomes.put(pg.getId(),
                new ProductGroupIncomeBean(pg.getName().get(), new BigDecimal("0.00"), pg.getTaxSet().getTaxPercent().get(), pg.getPriority().get())
        ));

        receiptRepository.loadBy(period, cashBox).stream().forEach(r -> r.getReceiptElements().stream().forEach(
                re -> {
                    ProductGroupIncomeBean productGroupIncome = productGroupIncomes.get(re.getProduct().getProductGroup().getId());
                    productGroupIncome.setIncome(productGroupIncome.getIncome().add(re.getTotalPrice().get()));
                }
        ));

        List<ProductGroupIncomeBean> productGroupIncomeBean = productGroupIncomes.values().stream().sorted((o1, o2) -> o1.getPriority().compareTo(o2.getPriority())).collect(Collectors.toList());
        final BigDecimal[] sum = {new BigDecimal("0.00")};
        productGroupIncomes.values().stream().forEach(pgi -> sum[0] = sum[0].add(pgi.getIncome()));

        return new IncomeBean(period.getStartDay().toLocalDate(), period.getEndDay().toLocalDate(), productGroupIncomeBean, sum[0]);
    }
}
