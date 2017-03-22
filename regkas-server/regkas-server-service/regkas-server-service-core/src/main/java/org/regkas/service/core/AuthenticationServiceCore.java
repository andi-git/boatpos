package org.regkas.service.core;

import org.regkas.domain.api.model.User;
import org.regkas.domain.api.repository.CashBoxRepository;
import org.regkas.domain.api.repository.UserRepository;
import org.regkas.domain.api.values.Name;
import org.regkas.domain.api.values.PasswordPlain;
import org.regkas.service.api.AuthenticationService;
import org.regkas.service.api.bean.CredentialsBean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.Optional;

@RequestScoped
public class AuthenticationServiceCore implements AuthenticationService {

    @Inject
    private UserRepository userRepository;

    @Inject
    private CashBoxRepository cashBoxRepository;

    @Override
    public Boolean authenticate(CredentialsBean credentialsBean) {
        boolean authenticated = false;
        Optional<User> userOptional = userRepository.authenticate(new Name(credentialsBean.getUsername()), new PasswordPlain(credentialsBean.getPassword()));
        if (userOptional.isPresent()) {
            authenticated = userOptional.get().getCompany().getCashBoxes().stream().anyMatch(cashBox -> cashBox.getName().get().equals(credentialsBean.getCashBoxId()));
        }
        return authenticated;
    }
}
