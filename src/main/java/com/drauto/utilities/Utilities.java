package com.drauto.utilities;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * Generic Utilities class
 * 
 * @author Deepak
 *
 */
public class Utilities {

    private static final Logger log = LoggerFactory.getLogger(Utilities.class);

    /**
     * This method will return current time
     * 
     * @return current time
     */
    public static String getTime() {
        Date today = new Date();
        return String.valueOf(today.getTime());
    }

    /**
     * 
     * @param encoded encoded String
     * @return decoded value
     */
    public static String decodeBase64(String encoded) {
        byte[] byteArray = Base64.decodeBase64(encoded);
        String decodedString = new String(byteArray);
        return decodedString;
    }

    /**
     * @param path path of the properties file
     * @return {@link Properties} object based on the given path
     */
    public static Properties readProperties(String path) {
        Properties properties = new Properties();
        try {
            properties.load(new FileReader(new File(path)));
        } catch (IOException e) {
            log.error("Exception occured", e);
        }
        return properties;
    }

}
