package secretescapes.pages;

import com.codeborne.selenide.appium.SelenideAppiumElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.appium.SelenideAppium.$;

public abstract class BasePage {

    public SomethingWrongAlert somethingWrongAlert = new SomethingWrongAlert();

    public static class SomethingWrongAlert {

        public final SelenideAppiumElement seSomethingWrongAlertForm = $(By.id("com.secretescapes.mobile.staging:id/parentPanel"));

        public final SelenideAppiumElement txtSomethingWrongHeader = $(By.id("com.secretescapes.mobile.staging:id/alertTitle"));
        public final SelenideAppiumElement txtSomethingWrongText = $(By.id("android:id/message"));

        public final SelenideAppiumElement btnAlertOk = $(By.id("android:id/button1"));
    }

}
