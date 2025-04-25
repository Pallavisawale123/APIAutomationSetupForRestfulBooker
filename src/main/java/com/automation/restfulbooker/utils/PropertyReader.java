package com.automation.restfulbooker.utils;

/**
 * @author psawale
 * @project restfulbooker
 * @date 4/20/2025
 */

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * just for practice purpose ,In spring boot no need to read property file ,we can read by @value annotation of spring
 * boot
 */
public class PropertyReader {

    public static String propertyReader(String filePath, String key) {
        String value = null;
        //Inputstream is required while loading into properties

        try (InputStream input = new FileInputStream(filePath)) {

            // object creation for Property class
            Properties prop = new Properties();

            // load a properties file
            prop.load(input);

            //getProperty will fetch the value according to the key
            value = prop.getProperty(key);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return value;
    }
}