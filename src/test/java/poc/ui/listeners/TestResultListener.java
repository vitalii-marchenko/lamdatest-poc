package poc.ui.listeners;

import com.codeborne.selenide.WebDriverRunner;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.ArrayList;
import java.util.List;

public class TestResultListener implements ITestListener {

    @Override
    public void onTestSuccess(ITestResult result) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) WebDriverRunner.getWebDriver();
        javascriptExecutor.executeScript("lambda-status=passed");
        String testName = result.getName();
        javascriptExecutor.executeScript("lambda-name=" + testName);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) WebDriverRunner.getWebDriver();
        String testName = result.getName();
        javascriptExecutor.executeScript("lambda-status=failed");
        javascriptExecutor.executeScript("lambda-name=" + testName);

        List<String> exceptions = new ArrayList<>();
        String errorMessage = result.getThrowable().getMessage();
        exceptions.add(errorMessage);
        javascriptExecutor.executeScript("lambda-exceptions", exceptions);
    }
}
