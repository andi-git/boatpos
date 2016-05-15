package org.regkas.service.core;

import org.boatpos.common.repository.api.values.DomainId;
import org.boatpos.common.repository.api.values.Enabled;
import org.boatpos.common.util.log.LogWrapper;
import org.boatpos.common.util.log.SLF4J;
import org.boatpos.common.util.qualifiers.Current;
import org.regkas.repository.api.model.CashBox;
import org.regkas.repository.api.model.ProductGroup;
import org.regkas.repository.api.model.Receipt;
import org.regkas.repository.api.repository.ProductGroupRepository;
import org.regkas.repository.api.repository.ReceiptRepository;
import org.regkas.repository.api.repository.TaxSetRepository;
import org.regkas.repository.api.values.DEPString;
import org.regkas.service.api.JournalService;
import org.regkas.service.api.bean.IncomeBean;
import org.regkas.service.api.bean.Period;
import org.regkas.service.api.bean.ProductGroupIncomeBean;
import org.regkas.service.api.bean.TaxElementBean;
import org.regkas.service.core.serializer.DEPExporter;
import org.regkas.service.core.serializer.NonPrettyPrintingGson;
import org.regkas.service.core.serializer.Serializer;
import org.regkas.service.core.util.ReceiptToBillConverter;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;

@RequestScoped
public class JournalServiceCore implements JournalService {

    @Inject
    private ReceiptRepository receiptRepository;

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
    private DEPExporter depExporter;

    @Inject
    @NonPrettyPrintingGson
    private Serializer serializer;

    @Inject
    private ReceiptToBillConverter receiptToBillConverter;

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
    public File datenErfassungsProtokoll(Integer year) {
        return depExporter.export(Period.year(LocalDateTime.of(year, 1, 1, 0, 0)));
    }

    @Override
    public File datenErfassungsProtokoll(Integer year, Integer month) {
        return depExporter.export(Period.month(LocalDateTime.of(year, month, 1, 0, 0)));
    }

    @Override
    public File datenErfassungsProtokoll(Integer year, Integer month, Integer dayOfMonth) {
        return depExporter.export(Period.day(LocalDateTime.of(year, month, dayOfMonth, 0, 0)));
    }

    @Override
    public int updateReceipts() {
        List<Receipt> receipts = receiptRepository.loadAllWithoutDEP();
        int count = 0;
        for (Receipt receipt : receipts) {
            receipt.setDEP(new DEPString(serializer.serialize(receiptToBillConverter.convert(receipt)))).persist();
            count++;
        }
        return count;
    }

    private IncomeBean totalIncomeFor(Period period) {
        checkNotNull(period, "'period' must not be null");
        log.info("calculate total income for {} - {}", period.getStartDay(), period.getEndDay());

        // add all product-groups
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

        // add total-income
        List<ProductGroupIncomeBean> productGroupIncomeBean = productGroupIncomes.values().stream().sorted((o1, o2) -> o1.getPriority().compareTo(o2.getPriority())).collect(Collectors.toList());
        BigDecimal sum = productGroupIncomes.values().stream()
                .map(ProductGroupIncomeBean::getIncome)
                .reduce(new BigDecimal("0.00"), BigDecimal::add);

        // add prices split by tax-sets
        List<TaxElementBean> taxElements = new ArrayList<>();
        taxSetRepository.loadAll(Enabled.TRUE).stream().forEach(taxSet -> {
            BigDecimal incomeForTaxSet = productGroupIncomes.values().stream()
                    .filter(pgi -> pgi.getTaxPercent() == taxSet.getTaxPercent().get().intValue())
                    .map(ProductGroupIncomeBean::getIncome)
                    .reduce(new BigDecimal("0.00"), BigDecimal::add);
            taxElements.add(new TaxElementBean(taxSet.getTaxPercent().get(), taxSet.getPriority().get(), incomeForTaxSet, getBeforeTax(incomeForTaxSet), getTax(incomeForTaxSet)));
        });

        return new IncomeBean(period.getStartDay().toLocalDate(), period.getEndDay().toLocalDate(), productGroupIncomeBean, sum, taxElements);
    }

    private BigDecimal getTax(BigDecimal price) {
        return price.subtract(getBeforeTax(price));
    }

    private BigDecimal getBeforeTax(BigDecimal price) {
        return price.divide(new BigDecimal("1.20"), 2, BigDecimal.ROUND_HALF_UP);
    }

}
