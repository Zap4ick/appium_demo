package secretescapes.utils;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

import javax.annotation.CheckReturnValue;
import javax.annotation.Nonnull;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.time.Duration;
import java.util.Objects;

import static java.time.temporal.ChronoUnit.SECONDS;
import static secretescapes.utils.Properties.Key.*;

@Slf4j
public class AppiumDriver implements WebDriverProvider {

    /**
     * This method is run when Selenide calls open() method
     * @param capabilities set of desired capabilities as suggested by Selenide framework; method implementation is
     * recommended to pass this variable to {@link WebDriver}, probably modifying it according to specific needs
     * @return
     */
    @Override
    @CheckReturnValue
    @Nonnull
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        File app;
        try {
            app = new File(Objects.requireNonNull(Thread.currentThread().getContextClassLoader().getResource(Properties.getStringProperty(FILE_PATH))).toURI());
        } catch (URISyntaxException e) {
            log.info("Exception during creating URI for file: {}", e.getMessage());
            throw new RuntimeException(e);
        }

        try {
            return new AndroidDriver(new URL(Configuration.baseUrl), configureUiAutomator2Options(capabilities, app));
        } catch (MalformedURLException e) {
            log.info("MalformedURLException exception during creating AndroidDriver: {}", e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private static UiAutomator2Options configureUiAutomator2Options(Capabilities capabilities, File app) {
        UiAutomator2Options options = new UiAutomator2Options();
        options = options.merge(capabilities);
        options.setUiautomator2ServerLaunchTimeout(Duration.of(Properties.getIntProperty(LAUNCH_TIMEOUT), SECONDS));
        options.setApp(app.getAbsolutePath());
        options.setAutoGrantPermissions(true);
        options.setAvd(Properties.getStringProperty(DEVICE_NAME));
        options.setAvdLaunchTimeout(Duration.of(Properties.getIntProperty(LAUNCH_TIMEOUT), SECONDS));
        options.setUdid(Properties.getStringProperty(DEVICE_UDID));
        options.setAdbExecTimeout(Duration.of(Properties.getIntProperty(ADB_EXEC_TIMEOUT), SECONDS));
        return options;
    }

}