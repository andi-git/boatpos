package org.boatpos.service.core.mapping;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class MappingHelperDozer implements MappingHelper {

    private Mapper mapper;

    @PostConstruct
    private void postConstruct() {
        List<String> mappingFiles = new ArrayList<String>();
        mappingFiles.add("dozerBeanMapping.xml");
        mapper = new DozerBeanMapper(mappingFiles);
    }

    @Override
    public <T> T map(Object object, Class<T> clazz) {
        return mapper.map(object, clazz);
    }
}
