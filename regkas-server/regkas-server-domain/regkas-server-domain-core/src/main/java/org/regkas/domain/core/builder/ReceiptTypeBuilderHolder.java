package org.regkas.domain.core.builder;

import org.regkas.model.ReceiptTypeEntity;
import org.regkas.domain.api.builder.ReceiptTypeBuilder;
import org.regkas.domain.api.model.ReceiptType;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Dependent
public class ReceiptTypeBuilderHolder {

    @Inject
    @Any
    private Instance<ReceiptTypeBuilder<? extends ReceiptType, ? extends ReceiptTypeEntity>> receiptTypeBuilders;

    public Set<ReceiptTypeBuilder> getAvaiableBuilders() {
        Set<ReceiptTypeBuilder> result = new HashSet<>();
        receiptTypeBuilders.forEach(result::add);
        return result;
    }

    public Optional<ReceiptType> getReceiptTypeFor(ReceiptTypeEntity receiptTypeEntity) {
        Optional<ReceiptType> receiptType = Optional.empty();
        for (ReceiptTypeBuilder builder : getAvaiableBuilders()) {
            if (builder.canHandle(receiptTypeEntity.getClass())) {
                receiptType = Optional.of(builder.build(receiptTypeEntity));
            }
        }
        return receiptType;
    }
}
