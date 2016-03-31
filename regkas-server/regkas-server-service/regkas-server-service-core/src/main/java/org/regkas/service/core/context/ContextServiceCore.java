package org.regkas.service.core.context;

import org.regkas.repository.api.model.CashBox;
import org.regkas.repository.api.model.User;
import org.regkas.repository.api.repository.CashBoxRepository;
import org.regkas.repository.api.repository.UserRepository;
import org.regkas.repository.api.values.Name;
import org.regkas.service.api.context.ContextService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class ContextServiceCore implements ContextService {

    @Inject
    private UserContext userContext;

    @Inject
    private CompanyContext companyContext;

    @Inject
    private CashBoxContext cashBoxContext;

    @Inject
    private UserRepository userRepository;

    @Inject
    private CashBoxRepository cashBoxRepository;

    @Override
    public void initContext(String username, String cashBoxId) {
        User user = userRepository.loadBy(new Name(username)).orElseGet(() -> {
            throw new RuntimeException("unable to load user with name " + username);
        });
        CashBox cashBox = cashBoxRepository.loadBy(new Name(cashBoxId)).orElseGet(() -> {
            throw new RuntimeException("unable to load cashbox with name " + cashBoxId);
        });
        userContext.set(user);
        companyContext.set(user.getCompany());
        cashBoxContext.set(cashBox);
    }
}
