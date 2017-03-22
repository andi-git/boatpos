package org.regkas.service.core;

import org.boatpos.common.domain.api.repository.MasterDataRepositoryWithDto;
import org.boatpos.common.domain.api.values.Enabled;
import org.boatpos.common.service.api.EnabledState;
import org.boatpos.common.service.core.MasterDataServiceCore;
import org.boatpos.common.service.core.ModelDtoConverter;
import org.boatpos.common.util.qualifiers.Current;
import org.regkas.domain.api.model.CashBox;
import org.regkas.domain.api.model.Product;
import org.regkas.domain.api.repository.ProductRepository;
import org.regkas.domain.api.values.Name;
import org.regkas.service.api.ProductService;
import org.regkas.service.api.bean.ProductBean;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@RequestScoped
public class ProductServiceCore extends MasterDataServiceCore<ProductBean> implements ProductService {

    @Inject
    private ProductRepository productRepository;

    @Inject
    private ModelDtoConverter modelDtoConverter;

    @Inject
    @Current
    private CashBox cashBox;

    protected MasterDataRepositoryWithDto getRepository() {
        return productRepository;
    }

    @Override
    public ProductBean getForCurrentCompany(String name) {
        Optional<Product> product = productRepository.loadBy(new Name(name), cashBox);
        Optional<ProductBean> convert = modelDtoConverter.convert(product);
        return convert.orElse(new ProductBean());
    }

    @Override
    public List<ProductBean> getAllForCurrentCompany() {
        return modelDtoConverter.convert(productRepository.loadBy(cashBox));
    }

    @SuppressWarnings("Duplicates")
    @Override
    public List<ProductBean> getAllForCurrentCompany(EnabledState enabledState) {
        List<ProductBean> productBeans;
        if (EnabledState.Enabled == enabledState) {
            productBeans = modelDtoConverter.convert(productRepository.loadBy(cashBox, Enabled.TRUE));
        } else if (EnabledState.Disabled == enabledState) {
            productBeans = modelDtoConverter.convert(productRepository.loadBy(cashBox, Enabled.FALSE));
        } else {
            productBeans = modelDtoConverter.convert(productRepository.loadBy(cashBox));
        }
        return productBeans;
    }
}
