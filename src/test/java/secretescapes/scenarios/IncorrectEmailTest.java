package secretescapes.scenarios;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.connection.ConnectionState;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import secretescapes.pages.DealsPage;
import secretescapes.pages.EnterEmailPage;
import secretescapes.pages.LoginPage;
import secretescapes.utils.AppiumDriverService;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.open;
import static io.appium.java_client.android.connection.ConnectionState.*;
import static secretescapes.models.DealsPreference.*;

@Slf4j
public class IncorrectEmailTest extends BaseTest {

    @BeforeMethod
    public void openDriverBeforeMethod() {
        open();
        new LoginPage().btnLoginByEmail.should(appear);
        AppiumDriverService.turnInternetOn();
    }

    @DataProvider
    public static Object[][] getIncorrectEmailsDataProvider() {
        return new Object[][]{
                {""},
                {RandomStringUtils.randomAlphabetic(5)},
                {RandomStringUtils.randomAlphabetic(5) + "@" + RandomStringUtils.randomAlphabetic(5)},
                {"@" + RandomStringUtils.randomAlphabetic(5)+".com"}
        };
    }

    @Test(dataProvider = "getIncorrectEmailsDataProvider")
    public void firstEmailLoginTest(String email) {
        log.info("Start login");
        new LoginPage().btnLoginByEmail.click();

        log.info("Enter incorrect email");
        EnterEmailPage enterEmailPage = new EnterEmailPage();
        enterEmailPage.txbEmail.setValue(email);
        enterEmailPage.btnContinue.click();

        log.info("Deals page has not appeared");
        DealsPage dealsPage = new DealsPage();
        dealsPage.btnPreference(BASIC).should(Condition.disappear, Duration.of(5, ChronoUnit.SECONDS));
        dealsPage.btnPreference(RECOMMENDED).should(Condition.disappear, Duration.of(5, ChronoUnit.SECONDS));
        dealsPage.btnPreference(REJECT).should(Condition.disappear, Duration.of(5, ChronoUnit.SECONDS));
    }
}
