package org.regkas.service.core;

import org.boatpos.common.model.PaymentMethod;
import org.boatpos.common.util.datetime.DateTimeHelper;
import org.boatpos.common.util.qualifiers.Current;
import org.regkas.repository.api.builder.ReceiptBuilder;
import org.regkas.repository.api.model.*;
import org.regkas.repository.api.repository.*;
import org.regkas.repository.api.values.*;
import org.regkas.service.api.SaleService;
import org.regkas.service.api.UserService;
import org.regkas.service.api.bean.BillBean;
import org.regkas.service.api.bean.CredentialsBean;
import org.regkas.service.api.bean.ReceiptElementBean;
import org.regkas.service.api.bean.SaleBean;
import org.regkas.service.core.util.ReceiptIdCalculator;
import org.regkas.service.core.util.TaxSet;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.Optional;

@RequestScoped
public class UserServiceCore implements UserService {

    @Inject
    private UserRepository userRepository;

    @Override
    public Boolean authenticate(CredentialsBean credentialsBean) {
        return userRepository.authenticate(new Name(credentialsBean.getUsername()), new PasswordPlain(credentialsBean.getPassword()));
    }
}
