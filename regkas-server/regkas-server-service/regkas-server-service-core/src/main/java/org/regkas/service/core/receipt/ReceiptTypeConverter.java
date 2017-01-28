package org.regkas.service.core.receipt;

import org.regkas.repository.api.model.ReceiptType;
import org.regkas.repository.api.repository.ReceiptTypeRepository;
import org.regkas.repository.api.values.Name;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

@Dependent
public class ReceiptTypeConverter {

    @Inject
    private ReceiptTypeRepository receiptTypeRepository;

    public ReceiptType convertToReceiptType(Name name) {
        checkNotNull(name, "name must not be null");
        return receiptTypeRepository.loadBy(name).orElseThrow(() -> new IllegalArgumentException("unknown receipt-type '" + name.get() + "'"));
    }
}
