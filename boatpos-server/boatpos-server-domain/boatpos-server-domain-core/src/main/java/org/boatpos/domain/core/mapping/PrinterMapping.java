package org.boatpos.domain.core.mapping;

import org.boatpos.common.domain.core.mapping.Mapping;
import org.boatpos.model.PrinterEntity;
import org.boatpos.service.api.bean.PrinterBean;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.spi.CDI;

/**
 * Mapping between {@link PrinterEntity} and {@link PrinterBean}.
 */
@Dependent
public class PrinterMapping extends Mapping<PrinterEntity, PrinterBean> {

    public static PrinterMapping fromCDI() {
        return CDI.current().select(PrinterMapping.class).get();
    }
}
