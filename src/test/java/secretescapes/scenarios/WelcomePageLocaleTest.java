package secretescapes.scenarios;

import com.codeborne.selenide.AssertionMode;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.testng.SoftAsserts;
import lombok.extern.slf4j.Slf4j;
import org.testng.ITestContext;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import secretescapes.models.Territory;
import secretescapes.pages.LoginPage;
import secretescapes.utils.AppiumDriverService;

import java.lang.reflect.Method;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.open;

@Slf4j
@Listeners(SoftAsserts.class)
public class WelcomePageLocaleTest extends BaseTest {

    @BeforeMethod
    public void openDriverBeforeMethod(ITestContext testContext, Method method, Object[] parameters){
        Configuration.assertionMode = AssertionMode.SOFT;
        Configuration.browserCapabilities.setCapability("locale", ((Territory) parameters[0]).name());
        Configuration.browserCapabilities.setCapability("language", ((Territory) parameters[0]).getLanguage());
        open();
        new LoginPage().btnLoginByEmail.should(appear);
    }

    @DataProvider
    public static Object[][] getLocaleTextsDataProvider() {
        return new Object[][]{
                {Territory.GB, "The home of luxury travel without the luxury prices",
                        "By clicking 'continue', you confirm that you accept our membership Terms and Conditions, " +
                                "have read our Privacy Policy, and agree to the use of cookies by us and our partners to provide a personalised service.",
                        "Continue with email", "Continue with Google", "Continue with Facebook"
                },
                {Territory.DE, "Ihr Traumurlaub zu Traumpreisen",
                        "Mit dem Klick auf „Weiter\" stimmen Sie unseren AGB sowie dem Einsatz von Cookies zu und nehmen unsere Datenschutzbestimmungen zur Kenntnis.",
                        "Weiter mit E-Mail", "Weiter mit Google", "Weiter mit Facebook"
                },
                {Territory.IT, "Il mondo dei viaggi di lusso, a prezzi che puoi permetterti",
                        "Cliccando su \"continua\", confermi di accettare i nostri Termini e Condizioni e di aver letto la nostra Informativa sulla Privacy e sull'uso dei cookie per consentire a noi e ai nostri partner di offrirti un servizio personalizzato.",
                        "Continua con l'email", "Continua con Google", "Continua con Facebook"
                }
        };
    }

    @Test(dataProvider = "getLocaleTextsDataProvider")
    public void welcomePageLocaleTest(Territory locale, String mottoString, String termsString, String emailString, String googleString, String facebookString) {
        log.info("Check labels on the welcome page");
        LoginPage loginPage = new LoginPage();
        loginPage.txtMotto.should(text(mottoString));
        loginPage.txtTerms.should(text(termsString));
        loginPage.btnLoginByEmail.should(text(emailString));
        loginPage.btnLoginByGoogle.should(text(googleString));
        loginPage.btnLoginByFacebook.should(text(facebookString));
        loginPage.txtChosenTerritory.should(text(locale.getFullName()));
    }
}
