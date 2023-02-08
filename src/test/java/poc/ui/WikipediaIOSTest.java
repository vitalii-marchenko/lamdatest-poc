package poc.ui;

import org.testng.annotations.Test;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class WikipediaIOSTest extends BaseTest {

    @Test
    public void testIOSSkip() {
        $$x("//*[@type='XCUIElementTypeStaticText']").findBy(attribute("label", "Свободная энциклопедия")).shouldBe(visible);
        $$x("//*[@type='XCUIElementTypeButton']").findBy(attribute("name", "Пропустить")).click();
        $x("//XCUIElementTypeImage[@name='tabbar-explore']").shouldBe(visible);
    }
}
