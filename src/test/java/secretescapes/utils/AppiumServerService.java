package secretescapes.utils;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.openqa.selenium.remote.DesiredCapabilities;

import static io.appium.java_client.service.local.flags.GeneralServerFlag.LOG_LEVEL;

public class AppiumServerService {

    private static AppiumServiceBuilder getAppiumServiceServiceBuilder() {
        final AppiumServiceBuilder builder;
        builder = new AppiumServiceBuilder();
        builder.usingAnyFreePort();
        builder.withArgument(LOG_LEVEL, "error");
        builder.withLogOutput(System.out);
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        builder.withCapabilities(desiredCapabilities);
        return builder;
    }

    public static AppiumDriverLocalService getService(){
        return AppiumDriverLocalService.buildService(getAppiumServiceServiceBuilder());
    }
}
