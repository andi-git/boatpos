package org.boatpos.common.service.core;

import org.boatpos.common.test.ArquillianHelper;
import org.eu.ingwar.tools.arquillian.extension.suite.annotations.ArquillianSuiteDeployment;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;

import java.io.File;
import java.io.IOException;

@SuppressWarnings("unused")
@ArquillianSuiteDeployment
public class Deployments {

    private static final String FOLDER_PERSISTENCE_XML_SOURCE = "src/test/resources";
    private static final String FOLDER_PERSISTENCE_XML_TARGET = "META-INF";
    private static final String FILE_DOZER_SOURCE = "src/test/resources/dozerBeanMapping.xml";
    private static final String FILE_DOZER_TARGET = "META-INF/dozerBeanMapping.xml";

    @Deployment
    public static WebArchive deploy() throws IOException {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addAsLibraries(ArquillianHelper.getAllArquillianLibs())
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml")
                .addAsResource(new File(FILE_DOZER_SOURCE))
                .addPackages(true, "org.boatpos.common.test")
                .addPackages(true, "org.boatpos.common.util")
                .addPackages(true, "org.boatpos.common.model")
                .addPackages(true, "org.boatpos.common.repository.api")
                .addPackages(true, "org.boatpos.common.repository.core")
                .addPackages(true, "org.boatpos.common.service.api")
                .addPackages(true, "org.boatpos.common.service.core");
    }
}
