package org.regkas.service.core;

import org.boatpos.common.repository.api.repository.MasterDataRepositoryWithDto;
import org.boatpos.common.repository.api.values.Enabled;
import org.boatpos.common.service.api.EnabledState;
import org.boatpos.common.service.core.MasterDataServiceCore;
import org.boatpos.common.service.core.ModelDtoConverter;
import org.boatpos.common.util.qualifiers.Current;
import org.regkas.repository.api.model.Company;
import org.regkas.repository.api.repository.ProductGroupRepository;
import org.regkas.service.api.ProductGroupService;
import org.regkas.service.api.bean.ProductGroupBean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@RequestScoped
public class ProductGroupServiceCore extends MasterDataServiceCore<ProductGroupBean> implements ProductGroupService {

    @Inject
    private ProductGroupRepository productGroupRepository;

    @Inject
    private ModelDtoConverter modelDtoConverter;

    @Inject
    @Current
    private Company company;

    protected MasterDataRepositoryWithDto getRepository() {
        return productGroupRepository;
    }

    @Override
    public List<ProductGroupBean> getAllForCurrentCompany() {
        return modelDtoConverter.convert(productGroupRepository.loadBy(company));
    }

    @SuppressWarnings("Duplicates")
    @Override
    public List<ProductGroupBean> getAllForCurrentCompany(EnabledState enabledState) {
        List<ProductGroupBean> productGroupBeans;
        if (EnabledState.Enabled == enabledState) {
            productGroupBeans = modelDtoConverter.convert(productGroupRepository.loadBy(company, Enabled.TRUE));
        } else if (EnabledState.Disabled == enabledState) {
            productGroupBeans = modelDtoConverter.convert(productGroupRepository.loadBy(company, Enabled.FALSE));
        } else {
            productGroupBeans = modelDtoConverter.convert(productGroupRepository.loadBy(company));
        }
        return productGroupBeans;
    }
}
