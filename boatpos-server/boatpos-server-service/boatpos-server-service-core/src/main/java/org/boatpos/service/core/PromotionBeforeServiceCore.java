package org.boatpos.service.core;

import org.boatpos.common.repository.api.values.DomainId;
import org.boatpos.common.service.core.MasterDataHelper;
import org.boatpos.common.service.core.ModelDtoConverter;
import org.boatpos.repository.api.repository.PromotionBeforeRepository;
import org.boatpos.repository.api.values.Name;
import org.boatpos.common.service.api.EnabledState;
import org.boatpos.service.api.PromotionBeforeService;
import org.boatpos.service.api.bean.PromotionBeforeBean;
import org.boatpos.service.core.util.WeekendHolidayHelper;
import org.boatpos.common.util.log.LogWrapper;
import org.boatpos.common.util.log.SLF4J;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@RequestScoped
public class PromotionBeforeServiceCore implements PromotionBeforeService {

    @Inject
    @SLF4J
    private LogWrapper log;

    @Inject
    private PromotionBeforeRepository promotionBeforeRepository;

    @Inject
    private ModelDtoConverter modelDtoConverter;

    @Inject
    private MasterDataHelper masterDataHelper;

    @Inject
    private WeekendHolidayHelper weekendHolidayHelper;

    @Override
    public List<PromotionBeforeBean> getAll() {
        return getAll(EnabledState.All);
    }

    @Override
    public List<PromotionBeforeBean> getAll(EnabledState enabledState) {
        return weekendHolidayHelper.modify(modelDtoConverter.convert(masterDataHelper.loadAll(promotionBeforeRepository, enabledState)));
    }

    @Override
    public Optional<PromotionBeforeBean> getById(Long id) {
        return weekendHolidayHelper.modify(modelDtoConverter.convert(promotionBeforeRepository.loadBy(new DomainId(id))));
    }

    @Override
    public Optional<PromotionBeforeBean> getByName(String name) {
        return weekendHolidayHelper.modify(modelDtoConverter.convert(promotionBeforeRepository.loadBy(new Name(name))));
    }

    @Override
    public PromotionBeforeBean save(PromotionBeforeBean promotionBeforeBean) {
        return promotionBeforeRepository.builder().from(promotionBeforeBean).persist().asDto();
    }

    @Override
    public PromotionBeforeBean update(PromotionBeforeBean promotionBeforeBean) {
        return promotionBeforeRepository.builder().from(promotionBeforeBean).persist().asDto();
    }

    @Override
    public void enable(Long id) {
        masterDataHelper.enable(promotionBeforeRepository, new DomainId(id));
    }

    @Override
    public void disable(Long id) {
        masterDataHelper.disable(promotionBeforeRepository, new DomainId(id));
    }
}
