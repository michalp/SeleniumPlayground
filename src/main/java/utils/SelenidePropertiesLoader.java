package utils;

import com.codeborne.selenide.Configuration;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by mpaszek on 9/28/2017.
 */
public class SelenidePropertiesLoader {

    private static final String PROPERTY_BASE_URL = "selenide.baseUrl";
    private static final String PROPERTY_BROWSER = "selenide.browser";

    private static final String PROPERTY_WEBDRIVER_GECKO_DRIVER = "webdriver.gecko.driver";
    private static final String PROPERTY_WEBDRIVER_CHROME_DRIVER = "webdriver.chrome.driver";

    private String baseUrl;
    private String browser;

    public SelenidePropertiesLoader() {
        readPropertiesFile(false);
    }

    public SelenidePropertiesLoader(boolean initializeSelenide) {
        readPropertiesFile(initializeSelenide);
        if (initializeSelenide) {
            setGlobalSelenideProperties();
        }
    }

    private void readPropertiesFile(boolean initializeSelenide) {
        final String fileName = "selenide.properties";

        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(fileName))) {
            Properties properties = new Properties();
            properties.load(bufferedReader);

            if (initializeSelenide) {
                setSelenideProperties(properties);
            }
            setCustomProperties(properties);
        } catch (IOException e) {
            throw new RuntimeException("There was an issue while reading 'selenide.properties' file!", e);
        }
    }

    private void setGlobalSelenideProperties() {
        Configuration.baseUrl = checkNotNull(System.getProperty(PROPERTY_BASE_URL, baseUrl));
        Configuration.browser = checkNotNull(System.getProperty(PROPERTY_BROWSER, browser));
    }

    private void setSelenideProperties(Properties selenideProperties) {
        baseUrl = selenideProperties.getProperty(PROPERTY_BASE_URL);
        browser = selenideProperties.getProperty(PROPERTY_BROWSER);
    }

    private void setCustomProperties(Properties otherProperties) {
        System.setProperty(PROPERTY_WEBDRIVER_GECKO_DRIVER, otherProperties.getProperty(PROPERTY_WEBDRIVER_GECKO_DRIVER));
        System.setProperty(PROPERTY_WEBDRIVER_CHROME_DRIVER, otherProperties.getProperty(PROPERTY_WEBDRIVER_CHROME_DRIVER));
    }

}
