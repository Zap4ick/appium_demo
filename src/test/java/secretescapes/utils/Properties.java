package secretescapes.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

@Slf4j
public class Properties {

    public enum Key {
        ADB_EXEC_TIMEOUT,
        DEFAULT_LOCALE,
        DEFAULT_LANGUAGE,
        DEVICE_NAME,
        DEVICE_UDID,
        FILE_PATH,
        IMPLICIT_TIMEOUT,
        LAUNCH_TIMEOUT,
        POLLING_INTERVAL,
        SCREENSHOTS,
        UIAUTOMATOR_LAUNCH_TIMEOUT;

        @Override
        public String toString() {
            return this.name().toLowerCase().replace("_", ".");
        }
    }

    private static final java.util.Properties PROPERTIES = new java.util.Properties();

    static {
        readProperties();
    }

    private static void readProperties() {
        try {
            PROPERTIES.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("core.properties"));

            //Optional.ofNullable(System.getenv("TRAVIS")).ifPresent(value -> PROPERTIES.setProperty("ci", value));
            //Optional.ofNullable(System.getenv("CI")).ifPresent(value -> PROPERTIES.setProperty("ci", value));
        } catch (IOException e) {
            log.warn("Properties not loaded:", e);
        }
    }

    public static String getStringProperty(Key key) {
        return getStringProperty(key.toString(), "");
    }

    public static String getStringProperty(String key) {
        return getStringProperty(key, "");
    }

    public static Integer getIntProperty(String key) {
        return Integer.parseInt(getStringProperty(key));
    }

    public static Integer getIntProperty(Key key) {
        return Integer.parseInt(getStringProperty(key));
    }

    public static Boolean getBoolProperty(Key key) {
        return Boolean.parseBoolean(getStringProperty(key));
    }

    public static String getStringProperty(String key, String defaultValue) {
        String systemValue = System.getProperty(key);
        return StringUtils.isNotEmpty(systemValue) ? systemValue : PROPERTIES.getProperty(key, defaultValue);
    }
}
