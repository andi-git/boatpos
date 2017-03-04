package org.regkas.service.core.financialoffice;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.regkas.service.core.email.MailSender;

@ApplicationScoped
public class FinancialOfficeSenderFactory {

    @Inject
    private FinancialOfficeSender defaultFinancialOfficeSender;

    private FinancialOfficeSender currentFinancialOfficeSender;

    @PostConstruct
    public void reset() {
        currentFinancialOfficeSender = defaultFinancialOfficeSender;
    }

    public void setFinancialOfficeSender(FinancialOfficeSender financialOfficeSender) {
        currentFinancialOfficeSender = financialOfficeSender;
    }

    public FinancialOfficeSender getFinancialOfficeSender() {
        return currentFinancialOfficeSender;
    }
}
