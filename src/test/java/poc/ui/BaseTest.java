package poc.ui;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.appium.SelenideAppium;
import org.example.MobileDriverProviderLambdaTest;
import org.testng.annotations.BeforeMethod;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class BaseTest {

    @BeforeMethod
    public void setUp() {
        closeWebDriver();
        closeApp();
        Configuration.browserSize = null;
        Configuration.timeout = 5000;
        Configuration.browser = MobileDriverProviderLambdaTest.class.getName();
        SelenideAppium.launchApp();
    }

    public void closeApp() {
        try {
            SelenideAppium.terminateApp("org.wikipedia");
        } catch (Exception ignored) {

        }
    }
}
