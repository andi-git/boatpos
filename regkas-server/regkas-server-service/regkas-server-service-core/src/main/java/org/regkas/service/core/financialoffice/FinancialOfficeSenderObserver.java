package org.regkas.service.core.financialoffice;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@ApplicationScoped
public class FinancialOfficeSenderObserver {

    @Inject
    private FinancialOfficeSenderFactory financialOfficeSenderFactory;

    public void observeSignatureDeviceAvailableAgainEvent(@Observes SignatureDeviceAvailableAgainEvent signatureDeviceAvailableAgainEvent) {
        financialOfficeSenderFactory.getFinancialOfficeSender().signatureDeviceAvailableAgain();
    }

    public void observeSignatureDeviceDamagedEvent(@Observes SignatureDeviceDamagedEvent signatureDeviceDamagedEvent) {
        financialOfficeSenderFactory.getFinancialOfficeSender().signatureDeviceDamaged();
    }
}
