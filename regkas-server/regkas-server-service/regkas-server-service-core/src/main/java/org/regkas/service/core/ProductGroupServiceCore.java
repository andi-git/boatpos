package org.regkas.service.core;

import org.boatpos.common.domain.api.repository.MasterDataRepositoryWithDto;
import org.boatpos.common.domain.api.values.Enabled;
import org.boatpos.common.service.api.EnabledState;
import org.boatpos.common.service.core.MasterDataServiceCore;
import org.boatpos.common.service.core.ModelDtoConverter;
import org.boatpos.common.util.qualifiers.Current;
import org.regkas.domain.api.model.CashBox;
import org.regkas.domain.api.model.Product;
import org.regkas.domain.api.model.ProductGroup;
import org.regkas.domain.api.repository.ProductGroupRepository;
import org.regkas.domain.api.repository.ProductRepository;
import org.regkas.domain.api.values.Name;
import org.regkas.service.api.ProductGroupService;
import org.regkas.service.api.bean.ProductBean;
import org.regkas.service.api.bean.ProductGroupBean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@RequestScoped
public class ProductGroupServiceCore extends MasterDataServiceCore<ProductGroupBean> implements ProductGroupService {

    @Inject
    private ProductGroupRepository productGroupRepository;

    @Inject
    private ProductRepository productRepository;

    @Inject
    private ModelDtoConverter modelDtoConverter;

    @Inject
    @Current
    private CashBox cashBox;

    protected MasterDataRepositoryWithDto getRepository() {
        return productGroupRepository;
    }

    @Override
    public ProductGroupBean getForCurrentCompany(String name) {
        Optional<ProductGroup> productGroup = productGroupRepository.loadBy(new Name(name), cashBox);
        Optional<ProductGroupBean> convert = modelDtoConverter.convert(productGroup);
        return convert.orElse(new ProductGroupBean());
    }

    @Override
    public ProductBean getGenericProductFor(String productGroupName) {
        ProductBean result = new ProductBean();
        Optional<ProductGroup> productGroup = productGroupRepository.loadBy(new Name(productGroupName), cashBox);
        if (productGroup.isPresent()) {
            Optional<Product> product = productRepository.loadGenericBy(productGroup.get());
            Optional<ProductBean> productBean = modelDtoConverter.convert(product);
            result = productBean.orElse(new ProductBean());
        }
        return result;
    }

    @Override
    public List<ProductGroupBean> getAllForCurrentCompany() {
        return modelDtoConverter.convert(productGroupRepository.loadBy(cashBox));
    }

    @SuppressWarnings("Duplicates")
    @Override
    public List<ProductGroupBean> getAllForCurrentCompany(EnabledState enabledState) {
        List<ProductGroupBean> productGroupBeans;
        if (EnabledState.Enabled == enabledState) {
            productGroupBeans = modelDtoConverter.convert(productGroupRepository.loadBy(cashBox, Enabled.TRUE));
        } else if (EnabledState.Disabled == enabledState) {
            productGroupBeans = modelDtoConverter.convert(productGroupRepository.loadBy(cashBox, Enabled.FALSE));
        } else {
            productGroupBeans = modelDtoConverter.convert(productGroupRepository.loadBy(cashBox));
        }
        return productGroupBeans;
    }
}
