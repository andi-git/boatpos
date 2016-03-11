package org.boatpos.service.core;

import org.boatpos.repository.api.repository.PromotionAfterRepository;
import org.boatpos.repository.api.values.DomainId;
import org.boatpos.repository.api.values.Name;
import org.boatpos.service.api.EnabledState;
import org.boatpos.service.api.PromotionAfterService;
import org.boatpos.service.api.bean.PromotionAfterBean;
import org.boatpos.service.core.util.ModelDtoConverter;
import org.boatpos.service.core.util.MasterDataHelper;
import org.boatpos.common.util.log.LogWrapper;
import org.boatpos.common.util.log.SLF4J;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@RequestScoped
public class PromotionAfterServiceCore implements PromotionAfterService {

    @Inject
    @SLF4J
    private LogWrapper log;

    @Inject
    private PromotionAfterRepository promotionAfterRepository;

    @Inject
    private ModelDtoConverter modelDtoConverter;

    @Inject
    private MasterDataHelper masterDataHelper;

    @Override
    public List<PromotionAfterBean> getAll() {
        return getAll(EnabledState.All);
    }

    @Override
    public List<PromotionAfterBean> getAll(EnabledState enabledState) {
        return modelDtoConverter.convert(masterDataHelper.loadAll(promotionAfterRepository, enabledState));
    }

    @Override
    public Optional<PromotionAfterBean> getById(Long id) {
        return modelDtoConverter.convert(promotionAfterRepository.loadBy(new DomainId(id)));
    }

    @Override
    public Optional<PromotionAfterBean> getByName(String name) {
        return modelDtoConverter.convert(promotionAfterRepository.loadBy(new Name(name)));
    }

    @Override
    public PromotionAfterBean save(PromotionAfterBean promotionAfterBean) {
        return promotionAfterRepository.builder().from(promotionAfterBean).persist().asDto();
    }

    @Override
    public PromotionAfterBean update(PromotionAfterBean promotionAfterBean) {
        return promotionAfterRepository.builder().from(promotionAfterBean).persist().asDto();
    }


    @Override
    public void enable(Long id) {
        masterDataHelper.enable(promotionAfterRepository, new DomainId(id));
    }

    @Override
    public void disable(Long id) {
        masterDataHelper.disable(promotionAfterRepository, new DomainId(id));
    }

}
