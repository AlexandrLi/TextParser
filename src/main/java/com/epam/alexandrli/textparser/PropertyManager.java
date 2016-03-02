package com.epam.alexandrli.textparser;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyManager {
    public static final String REGEX_PROPERTIES = "properties/regex.properties";
    public static final String CHAR_TYPES_PROPERTIES = "properties/charType.properties";

    public PropertyManager() {
    }

    public static Properties getProperties(String fileName) {
        Properties properties = new Properties();
        try (InputStream in = PropertyManager.class.getClassLoader().getResourceAsStream(fileName)) {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

}
