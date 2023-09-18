package secretescapes.pages;

import com.codeborne.selenide.appium.SelenideAppiumElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.appium.SelenideAppium.$;

public class MainPage extends BasePage {

    public SelenideAppiumElement imgHeader = $(By.id("com.secretescapes.mobile.staging:id/image"));

}
