package org.regkas.domain.core.builder;

import org.regkas.model.TaxSetEntity;
import org.regkas.domain.api.builder.TaxSetBuilder;
import org.regkas.domain.api.model.TaxSet;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Dependent
public class TaxSetBuilderHolder {

    @Inject
    @Any
    private Instance<TaxSetBuilder<? extends TaxSet, ? extends TaxSetEntity>> taxSetBuilders;

    public Set<TaxSetBuilder> getAvaiableBuilders() {
        Set<TaxSetBuilder> result = new HashSet<>();
        taxSetBuilders.forEach(result::add);
        return result;
    }

    public Optional<TaxSet> getTaxSetFor(TaxSetEntity taxSetEntity) {
        Optional<TaxSet> taxSet = Optional.empty();
        for (TaxSetBuilder builder : getAvaiableBuilders()) {
            if (builder.canHandle(taxSetEntity.getClass())) {
                taxSet = Optional.of(builder.build(taxSetEntity));
            }
        }
        return taxSet;
    }
}
