package org.boatpos.test;

import java.io.File;
import java.net.URL;

public class ArquillianHelper {

    public static File[] getAllArquillianLibs() {
        URL urlSystemResources = ClassLoader.getSystemResource("");
        File dirArquillianLibs = new File(new File(urlSystemResources.getFile()).getParentFile(), "arquillian-libs");
        return dirArquillianLibs.listFiles();
    }
}
