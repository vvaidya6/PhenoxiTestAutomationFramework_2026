package com.api.utils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigManager_old {

    private static Properties prop = new Properties();

    private ConfigManager_old() {

    }
    static{
        //operation of loading properties file in the memory
        //static block will be executed once during class loading time
        File configFile = new File(
                System.getProperty("user.dir")
                        + File.separator + "src"
                        + File.separator + "test"
                        + File.separator + "resources"
                        + File.separator + "config"
                        + File.separator + "config.properties"
        );
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(configFile);
            prop.load(fileReader);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public final static String getProperty(String key) throws IOException {
        return prop.getProperty(key);

    }
}
