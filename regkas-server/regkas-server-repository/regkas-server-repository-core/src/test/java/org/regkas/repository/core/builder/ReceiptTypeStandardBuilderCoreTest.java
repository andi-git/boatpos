package org.regkas.repository.core.builder;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.regkas.model.ReceiptTypeEntity;
import org.regkas.repository.api.builder.ReceiptTypeBuilder;
import org.regkas.repository.api.model.ReceiptType;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertNull;

@RunWith(Arquillian.class)
public class ReceiptTypeStandardBuilderCoreTest {

    @Test
    public void testBuild() throws Exception {
        assertNull(new ReceiptTypeStandardBuilderCore().build());
    }
}