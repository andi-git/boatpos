package org.regkas.service.core.financialoffice;

import javax.enterprise.inject.Alternative;

@Alternative
public class FinancialOfficeSenderMock implements FinancialOfficeSender {

    private boolean signatureDeviceIsAvailableAgainCalled = false;

    private boolean signatureDeviceIsNotLongerAvailableCalled = false;

    @Override
    public void signatureDeviceAvailableAgain() {
        signatureDeviceIsAvailableAgainCalled = true;
    }

    @Override
    public void signatureDeviceDamaged() {
        signatureDeviceIsNotLongerAvailableCalled = true;
    }

    public void reset() {
        signatureDeviceIsAvailableAgainCalled = false;
        signatureDeviceIsNotLongerAvailableCalled = false;
    }

    public boolean isSignatureDeviceIsAvailableAgainCalled() {
        return signatureDeviceIsAvailableAgainCalled;
    }

    public boolean isSignatureDeviceIsNotLongerAvailableCalled() {
        return signatureDeviceIsNotLongerAvailableCalled;
    }
}
