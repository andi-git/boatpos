package org.regkas.service.core;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.boatpos.common.domain.api.values.DomainId;
import org.boatpos.common.domain.api.values.Enabled;
import org.boatpos.common.util.log.LogWrapper;
import org.boatpos.common.util.log.SLF4J;
import org.boatpos.common.util.qualifiers.Current;
import org.regkas.domain.api.dep.DepExporter;
import org.regkas.domain.api.model.CashBox;
import org.regkas.domain.api.model.ProductGroup;
import org.regkas.domain.api.repository.ProductGroupRepository;
import org.regkas.domain.api.repository.ReceiptElementRepository;
import org.regkas.domain.api.repository.TaxSetRepository;
import org.regkas.domain.api.values.TaxPercent;
import org.regkas.service.api.JournalService;
import org.regkas.service.api.bean.IncomeBean;
import org.regkas.service.api.bean.Period;
import org.regkas.service.api.bean.ProductGroupIncomeBean;
import org.regkas.service.api.bean.TaxElementBean;

@RequestScoped
public class JournalServiceCore implements JournalService {

    private static final BigDecimal PRICE_ZERO = new BigDecimal("0.00");

    @Inject
    private ReceiptElementRepository receiptElementRepository;

    @Inject
    private TaxSetRepository taxSetRepository;

    @Inject
    private ProductGroupRepository productGroupRepository;

    @Inject
    @Current
    private CashBox cashBox;

    @Inject
    @SLF4J
    private LogWrapper log;

    @Inject
    private DepExporter depExporter;

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

    @Override
    public File datenErfassungsProtokollRKV2012(Integer year) {
        return depExporter.exportBasedOnRKV2012(Period.year(LocalDateTime.of(year, 1, 1, 0, 0)));
    }

    @Override
    public File datenErfassungsProtokollRKV2012(Integer year, Integer month) {
        return depExporter.exportBasedOnRKV2012(Period.month(LocalDateTime.of(year, month, 1, 0, 0)));
    }

    @Override
    public File datenErfassungsProtokollRKV2012(Integer year, Integer month, Integer dayOfMonth) {
        return depExporter.exportBasedOnRKV2012(Period.day(LocalDateTime.of(year, month, dayOfMonth, 0, 0)));
    }

    @Override
    public File datenErfassungsProtokollRKSV() {
        return depExporter.exportBasedOnRKSV(Period.untilNow());
    }

    private IncomeBean totalIncomeFor(Period period) {
        checkNotNull(period, "'period' must not be null");
        log.info("calculate total income for {} - {}", period.getStartDay(), period.getEndDay());

        // add all product-groups
        List<ProductGroup> productGroups = productGroupRepository.loadBy(cashBox, Enabled.TRUE);
        Map<DomainId, ProductGroupIncomeBean> productGroupIncomes = new HashMap<>();
        productGroups.stream().forEach(
            pg -> productGroupIncomes.put(
                pg.getId(),
                new ProductGroupIncomeBean(pg.getName().get(), PRICE_ZERO, pg.getTaxSet().getTaxPercent().get(), pg.getPriority().get())));

        // add income for every product-group
        receiptElementRepository
            .incomeByProductGroupFor(period, cashBox)
            .stream()
            .forEach(e -> productGroupIncomes.get(e.getId()).setIncome(e.getPricePaid().get()));

        // add total-income
        List<ProductGroupIncomeBean> productGroupIncomeBean = productGroupIncomes
            .values()
            .stream()
            .sorted((o1, o2) -> o1.getPriority().compareTo(o2.getPriority()))
            .collect(Collectors.toList());
        BigDecimal sum = productGroupIncomes.values().stream().map(ProductGroupIncomeBean::getIncome).reduce(PRICE_ZERO, BigDecimal::add);

        // add prices split by tax-sets
        List<TaxElementBean> taxElements = new ArrayList<>();
        taxSetRepository.loadAll(Enabled.TRUE).stream().forEach(taxSet -> {
            BigDecimal incomeForTaxSet = productGroupIncomes
                .values()
                .stream()
                .filter(pgi -> pgi.getTaxPercent() == taxSet.getTaxPercent().get().intValue())
                .map(ProductGroupIncomeBean::getIncome)
                .reduce(PRICE_ZERO, BigDecimal::add);
            taxElements.add(
                new TaxElementBean(
                    taxSet.getTaxPercent().get(),
                    taxSet.getPriority().get(),
                    incomeForTaxSet,
                    getBeforeTax(incomeForTaxSet, taxSet.getTaxPercent()),
                    getTax(incomeForTaxSet, taxSet.getTaxPercent())));
        });

        return new IncomeBean(period.getStartDay().toLocalDate(), period.getEndDay().toLocalDate(), productGroupIncomeBean, sum, taxElements);
    }

    private BigDecimal getTax(BigDecimal price, TaxPercent taxPercent) {
        if ( !taxPercent.isPresent() || taxPercent.get() == 0) {
            return PRICE_ZERO;
        }
        return price.subtract(getBeforeTax(price, taxPercent));
    }

    private BigDecimal getBeforeTax(BigDecimal price, TaxPercent taxPercent) {
        if ( !taxPercent.isPresent() || taxPercent.get() == 0) {
            return price;
        }
        return price.divide(taxPercent.asDivisor(), 2, BigDecimal.ROUND_HALF_UP);
    }
}
