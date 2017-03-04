package org.regkas.service.core.financialoffice;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FinancialOfficeSenderCore implements FinancialOfficeSender {

    @Override
    public void signatureDeviceAvailableAgain() {
        // TODO call web-service
    }

    @Override
    public void signatureDeviceDamaged() {
        // TODO call web-service
    }
}
