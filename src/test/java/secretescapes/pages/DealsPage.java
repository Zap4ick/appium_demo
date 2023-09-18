package secretescapes.pages;

import com.codeborne.selenide.appium.SelenideAppiumElement;
import org.openqa.selenium.By;
import secretescapes.models.DealsPreference;

import static com.codeborne.selenide.appium.SelenideAppium.$;

public class DealsPage extends BasePage {

    private static final String ID_TEMPLATE = "com.secretescapes.mobile.staging:id/%s";

    public final SelenideAppiumElement btnContinue = $(By.id("com.secretescapes.mobile.staging:id/continue_cta"));
    public final SelenideAppiumElement seProgressBar = $(By.className("android.widget.ProgressBar"));

    public SelenideAppiumElement btnPreference(DealsPreference preference) {
        return $(By.id(String.format(ID_TEMPLATE, preference.getId())));
    }
}
