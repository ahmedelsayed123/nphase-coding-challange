package com.nphase.configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {
    private final Properties configProp = new Properties();

    private Configuration() {
        InputStream in = this.getClass()
                .getClassLoader()
                .getResourceAsStream("config.properties");
        try {
            configProp.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class HelperClas {
        private static final Configuration INSTANCE = new Configuration();
    }

    public static Configuration getInstance() {
        return HelperClas.INSTANCE;
    }

    public String getProperty(String key) {
        return configProp.getProperty(key);
    }

}
