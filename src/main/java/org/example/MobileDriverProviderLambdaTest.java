package org.example;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import javax.annotation.Nonnull;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class MobileDriverProviderLambdaTest implements WebDriverProvider {

    public String gridURL = "@mobile-hub.lambdatest.com/wd/hub";
    String userName = "markony647";
    String accessKey = "kMhV5BZsoWTlgtqBbuzRM3Z8zVYSzcw0d1iG1zowXBdJzmmmr2";

    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        return createDriverForPlatform("android");
    }

    private WebDriver createDriverForPlatform(String platform) {
        try {
            String hub = "https://" + userName + ":" + accessKey + gridURL;

            if (platform.equalsIgnoreCase("android")) {
                DesiredCapabilities desiredCapabilities = desiredAndroidCapabilities();
                return new AndroidDriver(new URL(hub), desiredCapabilities);
            }
            if (platform.equalsIgnoreCase("ios")) {
                return new IOSDriver(new URL(hub), desiredAndroidCapabilities());
            } else {
                throw new RuntimeException("Platform not specified");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private DesiredCapabilities desiredAndroidCapabilities() {
        String platform = "Android";
        String device = "Galaxy S21 Ultra 5G";
        String version = "11";

        DesiredCapabilities capabilities = new DesiredCapabilities();

        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("username", "");
        ltOptions.put("accessKey", "");

        ltOptions.put("project", "Test Project");
        ltOptions.put("w3c", true);
        ltOptions.put("plugin", "java-testNG");
        ltOptions.put("platformName", "android");
        ltOptions.put("isRealMobile", true);
        ltOptions.put("deviceName", device);
        ltOptions.put("name", platform + " " +device + " " + version);
        ltOptions.put("platformVersion", version);
        ltOptions.put("app", "lt://APP10160202521675178632638211");
        ltOptions.put("console", true);
        ltOptions.put("network", true);
        ltOptions.put("visual", true);
        ltOptions.put("devicelog", true);

        capabilities.setCapability("LT:Options", ltOptions);
        return capabilities;
    }
}
