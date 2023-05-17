package poc.ui;


import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import poc.ui.listeners.TestResultListener;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

@Listeners(TestResultListener.class)
public class WikipediaAndroidTest extends BaseTest {

    @Test
    public void skipAndroidTest() {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) WebDriverRunner.getWebDriver();
        $x("//*[@resource-id='org.wikipedia:id/fragment_onboarding_skip_button']").click();
        $$x("//*[@class='android.widget.TextView']").findBy(Condition.exactText("Search Wikipedia")).shouldBe(Condition.visible);
    }

    @Test
    public void skipAndroidTestFailed() {
        $x("//*[@resource-id='org.wikipedia:id/fragment_onboarding_skip_button']").click();
        $$x("//*[@class='android.widget.TextView']").findBy(Condition.exactText("Search Wikipedia")).shouldBe(Condition.exactText("Search Google"));
    }

}
