package org.boatpos.model;

import org.boatpos.common.test.ArquillianHelper;
import org.eu.ingwar.tools.arquillian.extension.suite.annotations.ArquillianSuiteDeployment;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;

import java.io.IOException;

@ArquillianSuiteDeployment
public class Deployments {

    @Deployment
    public static WebArchive deploy() throws IOException {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addAsLibraries(ArquillianHelper.getAllArquillianLibs())
                .addAsWebInfResource("META-INF/beans.xml", "beans.xml")
                .addPackages(true, "org.boatpos.common.util")
                .addPackages(true, "org.boatpos.common.test")
                .addPackages(true, "org.boatpos.common.model")
                .addPackages(true, "org.boatpos.model");
    }
}
