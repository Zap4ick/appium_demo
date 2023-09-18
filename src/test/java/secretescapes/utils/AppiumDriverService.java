package secretescapes.utils;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.TextCheck;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.connection.ConnectionState;

import java.net.URL;

import static io.appium.java_client.android.connection.ConnectionState.*;
import static secretescapes.utils.Properties.Key.*;

public class AppiumDriverService {

    /**
     * This method configures webdriver, most importantly sets which driver class to use and url to connect
     * Many of the params are configured in core.properties
     * @param url
     */
    public static void configureAppiumDriver(URL url) {
        Configuration.browser = AppiumDriver.class.getName();
        Configuration.baseUrl = url.toString();

        Configuration.browserSize = null;
        Configuration.screenshots = Properties.getBoolProperty(SCREENSHOTS);
        Configuration.textCheck = TextCheck.FULL_TEXT;
        Configuration.timeout = Properties.getIntProperty(IMPLICIT_TIMEOUT);
        Configuration.pollingInterval = Properties.getIntProperty(POLLING_INTERVAL);
        Configuration.browserCapabilities.setCapability("locale", Properties.getStringProperty(DEFAULT_LOCALE));
        Configuration.browserCapabilities.setCapability("language", Properties.getStringProperty(DEFAULT_LANGUAGE));
    }

    public static void turnInternetOn() {
        ((AndroidDriver) Selenide.webdriver().driver().getWebDriver()).setConnection(new ConnectionState(WIFI_MASK + DATA_MASK));
    }

    public static void turnInternetOff() {
        ((AndroidDriver) Selenide.webdriver().driver().getWebDriver()).setConnection(new ConnectionState(AIRPLANE_MODE_MASK));
    }
}
