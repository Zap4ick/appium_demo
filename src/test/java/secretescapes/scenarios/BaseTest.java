package secretescapes.scenarios;

import com.codeborne.selenide.Selenide;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import lombok.extern.slf4j.Slf4j;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import secretescapes.utils.AppiumDriverService;
import secretescapes.utils.AppiumServerService;
import secretescapes.utils.Properties;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static secretescapes.utils.AdbUtils.stopEmulator;
import static secretescapes.utils.Properties.Key.DEVICE_UDID;

@Slf4j
public class BaseTest {

    private static AppiumDriverLocalService service;

    @BeforeSuite
    public void initServiceBeforeSuite() {
        service = AppiumServerService.getService();
    }

    @BeforeMethod
    public void createDriverBeforeTest() {
        service.start();
        AppiumDriverService.configureAppiumDriver(service.getUrl());
    }

    @AfterMethod(alwaysRun = true)
    public void appiumStopDriverAfterMethod(ITestContext iTestContext) {
        log.info("Screenshot: {}", Selenide.screenshot(iTestContext.getCurrentXmlTest().getName()));
        closeWebDriver();
    }

    @AfterSuite(alwaysRun = true)
    public void appiumServiceStopAfterSuite() {
        service.stop();
        stopEmulator(Properties.getStringProperty(DEVICE_UDID));
    }

}
