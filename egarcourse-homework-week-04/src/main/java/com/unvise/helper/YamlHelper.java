package com.unvise.helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.unvise.config.ApplicationConfig;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class YamlHelper {

    private static final String CONFIG_NAME = "conf.yaml";

    public static File getConfigAsFile() throws URISyntaxException {
        ClassLoader classLoader = YamlHelper.class.getClassLoader();
        URL conf = classLoader.getResource(CONFIG_NAME);
        if (conf == null) {
            throw new IllegalArgumentException("Configuration file not found!");
        }
        return new File(conf.toURI());
    }

    public static ApplicationConfig getApplicationConfigFromYaml(File yamlFile) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        return mapper.readValue(yamlFile, ApplicationConfig.class);
    }

}
