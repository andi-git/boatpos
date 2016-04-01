package org.regkas.service.core;

import org.boatpos.common.repository.api.repository.MasterDataRepositoryWithDto;
import org.boatpos.common.repository.api.values.Enabled;
import org.boatpos.common.service.api.EnabledState;
import org.boatpos.common.service.core.MasterDataServiceCore;
import org.boatpos.common.service.core.ModelDtoConverter;
import org.boatpos.common.util.qualifiers.Current;
import org.regkas.repository.api.model.Company;
import org.regkas.repository.api.model.Product;
import org.regkas.repository.api.repository.ProductRepository;
import org.regkas.repository.api.values.Name;
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
    private Company company;

    protected MasterDataRepositoryWithDto getRepository() {
        return productRepository;
    }

    @Override
    public ProductBean getForCurrentCompany(String name) {
        Optional<Product> product = productRepository.loadBy(new Name(name), company);
        Optional<ProductBean> convert = modelDtoConverter.convert(product);
        return convert.orElse(new ProductBean());
    }

    @Override
    public List<ProductBean> getAllForCurrentCompany() {
        return modelDtoConverter.convert(productRepository.loadBy(company));
    }

    @SuppressWarnings("Duplicates")
    @Override
    public List<ProductBean> getAllForCurrentCompany(EnabledState enabledState) {
        List<ProductBean> productBeans;
        if (EnabledState.Enabled == enabledState) {
            productBeans = modelDtoConverter.convert(productRepository.loadBy(company, Enabled.TRUE));
        } else if (EnabledState.Disabled == enabledState) {
            productBeans = modelDtoConverter.convert(productRepository.loadBy(company, Enabled.FALSE));
        } else {
            productBeans = modelDtoConverter.convert(productRepository.loadBy(company));
        }
        return productBeans;
    }
}
