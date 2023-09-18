package secretescapes.scenarios;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.connection.ConnectionState;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import secretescapes.pages.EnterEmailPage;
import secretescapes.pages.LoginPage;
import secretescapes.utils.AppiumDriverService;

import java.time.Duration;
import java.time.temporal.ChronoUnit;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.open;
import static io.appium.java_client.android.connection.ConnectionState.AIRPLANE_MODE_MASK;
import static secretescapes.utils.UserUtils.getRandomEmail;

@Slf4j
public class NoInternetLoginTest extends BaseTest {

    @BeforeMethod
    public void openDriverBeforeMethod() {
        open();
        new LoginPage().btnLoginByEmail.should(appear);
        AppiumDriverService.turnInternetOff();
    }

    @Test
    public void noInternetLoginTest() {
        log.info("Start login");
        new LoginPage().btnLoginByEmail.click();

        log.info("Enter email and continue");
        EnterEmailPage enterEmailPage = new EnterEmailPage();
        enterEmailPage.txbEmail.setValue(getRandomEmail());
        enterEmailPage.btnContinue.click();

        log.info("Check alert appeared");
        enterEmailPage.somethingWrongAlert.seSomethingWrongAlertForm.should(Condition.appear);
        enterEmailPage.somethingWrongAlert.txtSomethingWrongHeader.should(Condition.text("Oops! Something went wrong"));
        enterEmailPage.somethingWrongAlert.txtSomethingWrongText.should(Condition.text("Something has gone wrong with the application. Please try again."));

        log.info("Check alert can be closed");
        enterEmailPage.somethingWrongAlert.btnAlertOk.click();
        enterEmailPage.somethingWrongAlert.seSomethingWrongAlertForm.should(Condition.hidden, Duration.of(5, ChronoUnit.SECONDS));
        enterEmailPage.txbEmail.should(Condition.appear);
    }
}
