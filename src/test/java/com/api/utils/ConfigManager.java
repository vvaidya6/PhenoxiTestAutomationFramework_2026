package com.api.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

    private static Properties prop = new Properties();
    private static String path = "config/config.properties";
    private static String env;

    private ConfigManager() {

    }
    static{
        env = System.getProperty("env","qa");
        env = env.toLowerCase().trim();
        //This arrow operator supports Java 14 onwards for switch case
        path = switch (env) {
            case "dev" -> "config/config.dev.properties";
            case "qa" -> "config/config.qa.properties";
            case "uat" -> "config/config.uat.properties";
            default -> "config/config.properties";
        };
        InputStream inputStream =  Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
      if(inputStream==null){
          throw new RuntimeException("Cannot find the file at the path");
      }
      try {
            prop.load(inputStream);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public final static String getProperty(String key) throws IOException {
        return prop.getProperty(key);

    }
}
