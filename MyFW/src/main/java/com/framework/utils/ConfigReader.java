package com.framework.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Logger logger = LogManager.getLogger(ConfigReader.class);
    private static Properties properties;
    private static final String CONFIG_FILE_PATH="src/main/resources/config.properties";


    static {
        loadProperties();
    }

    //private constructor to prevent insatntiation
    private ConfigReader(){

    }

    public static void loadProperties(){
        try{
            properties=new Properties();
            FileInputStream fileInputStream=new FileInputStream(CONFIG_FILE_PATH);
            properties.load(fileInputStream);
            logger.info("File Property has been loaded Successfully "+CONFIG_FILE_PATH);
        }
        catch(Exception e){
            logger.error(e.getMessage());
        }

    }

    public static String getProperty(String key){
        String value=properties.getProperty(key);
        if(value==null){
            logger.info("Key is not present in configuration file");
        }
        return value.trim();
    }


    public static String getBrowser(){
        return getProperty("browser").toLowerCase();
    }

    public static String getBaseURL(){
        return getProperty("base.url");
    }

    public static boolean isScreenshotOnFailure() {
        return Boolean.parseBoolean(getProperty("screenshot.on.failure"));
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(getProperty("headless"));
    }

    public static String getEnvironment() {
        return getProperty("environment").toLowerCase();
    }

    public static int getExplicitWait() {
        return Integer.parseInt(getProperty("explicit.wait"));
    }

    public static int getPageLoadTimeout() {
        return Integer.parseInt(getProperty("page.load.timeout"));
    }

    public static int getImplicitWait() {
        return Integer.parseInt(getProperty("implicit.wait"));
    }

    public static boolean isMaximizeWindow() {
        return Boolean.parseBoolean(getProperty("maximize.window"));
    }

}
