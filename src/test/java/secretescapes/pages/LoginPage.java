package secretescapes.pages;

import com.codeborne.selenide.appium.SelenideAppiumElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.appium.SelenideAppium.$;
import static com.codeborne.selenide.appium.SelenideAppium.$x;

public class LoginPage extends BasePage {

    public final SelenideAppiumElement txtMotto = $x("//android.view.ViewGroup[@resource-id='com.secretescapes.mobile.staging:id/swipe_refresh_layout']//android.widget.TextView[1]");
    public final SelenideAppiumElement txtTerms = $x("//android.view.ViewGroup[@resource-id='com.secretescapes.mobile.staging:id/swipe_refresh_layout']//android.widget.TextView[last()]");
    public final SelenideAppiumElement txtChosenTerritory = $(By.id("com.secretescapes.mobile.staging:id/territory_selection_text"));

    public final SelenideAppiumElement btnLoginByEmail = $(By.id("com.secretescapes.mobile.staging:id/email"));
    public final SelenideAppiumElement btnLoginByGoogle = $(By.id("com.secretescapes.mobile.staging:id/google"));
    public final SelenideAppiumElement btnLoginByFacebook = $(By.id("com.secretescapes.mobile.staging:id/facebook"));
}
