package secretescapes.scenarios;

import com.codeborne.selenide.Selenide;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import secretescapes.models.DealsPreference;
import secretescapes.pages.DealsPage;
import secretescapes.pages.EnterEmailPage;
import secretescapes.pages.LoginPage;
import secretescapes.pages.MainPage;
import secretescapes.utils.AppiumDriverService;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Selenide.open;
import static secretescapes.models.DealsPreference.*;
import static secretescapes.utils.UserUtils.getRandomEmail;

@Slf4j
public class FirstEmailLoginTest extends BaseTest {

    @BeforeMethod
    public void openDriverBeforeMethod() {
        open();
        new LoginPage().btnLoginByEmail.should(appear);
        AppiumDriverService.turnInternetOn();
    }

    @DataProvider
    public static Object[][] getDealsPreferenceDataProvider() {
        return new Object[][]{
                {getRandomEmail(), BASIC}, {getRandomEmail(), RECOMMENDED}, {getRandomEmail(), REJECT}
        };
    }

    @Test(dataProvider = "getDealsPreferenceDataProvider")
    public void firstEmailLoginTest(String email, DealsPreference dealsPreference) {
        log.info("Start login");
        new LoginPage().btnLoginByEmail.click();

        log.info("Enter email and continue");
        EnterEmailPage enterEmailPage = new EnterEmailPage();
        enterEmailPage.txbEmail.setValue(email);
        enterEmailPage.btnContinue.click();
        enterEmailPage.seProgressBar.should(appear);

        log.info("Choose {} deals preference", dealsPreference);
        DealsPage dealsPage = new DealsPage();
        dealsPage.btnPreference(dealsPreference).click();
        dealsPage.btnContinue.click();
        dealsPage.seProgressBar.should(appear);

        log.info("Check main page is opened");
        new MainPage().imgHeader.should(appear);
    }
}
