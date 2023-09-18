package secretescapes.pages;

import com.codeborne.selenide.appium.SelenideAppiumElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.appium.SelenideAppium.$;

public class EnterEmailPage extends BasePage {

    public final SelenideAppiumElement txbEmail = $(By.id("com.secretescapes.mobile.staging:id/email"));
    public final SelenideAppiumElement btnContinue = $(By.id("com.secretescapes.mobile.staging:id/continue_cta"));
    public final SelenideAppiumElement seProgressBar = $(By.className("android.widget.ProgressBar"));
}
