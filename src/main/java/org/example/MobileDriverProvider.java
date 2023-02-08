package org.example;

import com.codeborne.selenide.WebDriverProvider;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import javax.annotation.Nonnull;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public class MobileDriverProvider implements WebDriverProvider {
    @Nonnull
    @Override
    public WebDriver createDriver(@Nonnull Capabilities capabilities) {
        String platform = System.getenv("PLATFORM");
        return createDriverForPlatform("android");
    }

    private WebDriver createDriverForPlatform(String platform) {
        try {
            String appiumUrl1 = "http://127.0.0.1:4723";
            String username = "";
            String accessKey = "";
            String server = "hub-cloud.browserstack.com";
            String appiumUrl = "http://" + username + ":" + accessKey + "@" + server + "/wd/hub";

            if (platform.equalsIgnoreCase("android")) {
                return new AndroidDriver(new URL(appiumUrl), desiredAndroidCapabilities());
            }
            if (platform.equalsIgnoreCase("ios")) {
                return new IOSDriver(new URL(appiumUrl), desiredAndroidCapabilities());
            } else {
                throw new RuntimeException("Platform not specified");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private UiAutomator2Options androidOptions() {
        UiAutomator2Options options = new UiAutomator2Options();
//        options.setPlatformName("Android");
//        options.setAutomationName("UiAutomator2");
//        options.setUdid("emulator-5554");
//        options.setAppPackage("org.wikipedia");
//        options.setAppActivity(".main.MainActivity");
//        options.setApp(new File("src/test/resources/app/org.wikipedia.apk").getAbsolutePath());

        options.setCapability("app", "bs://c1a0aa3717176b1df61c5cfda2c77661f1916476");
        options.setCapability("browserstack.debug", true);
        options.setCapability("build", "vitalii-poc-build");
        options.setCapability("device", "Google Pixel 3");
        options.setCapability("name", "vitalii_test_name");
        options.setCapability("os_version", "9.0");
        options.setCapability("project", "My First Project");
        options.setCapability("browserstack.appium_version", "2.0.0-beta.46");


        return options;
    }

    private DesiredCapabilities desiredAndroidCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        HashMap<String, Object> browserstackOptions = new HashMap<String, Object>();
        browserstackOptions.put("projectName", "vitalii-poc");
        browserstackOptions.put("buildName", "vitalii-poc-build");
        browserstackOptions.put("appiumVersion", "2.0.0");

        capabilities.setCapability("bstack:options", browserstackOptions);
        capabilities.setCapability("platformName", "android");
        capabilities.setCapability("platformVersion", "9.0");
        capabilities.setCapability("deviceName", "Google Pixel 3");
        capabilities.setCapability("app", "bs://c1a0aa3717176b1df61c5cfda2c77661f1916476");
        return capabilities;
    }

    private XCUITestOptions iOSOptions() {
        XCUITestOptions options = new XCUITestOptions();
        options.setPlatformName("iOS");
        options.setAutomationName("XCUITest");
        options.setUdid("F8A79F55-B4BF-42F8-91CC-4AE17B02A8D5");
        options.setNoReset(false);
        options.setFullReset(false);
        options.autoAcceptAlerts();
        options.setApp(new File("src/test/resources/app/Wikipedia.app").getAbsolutePath());
        return options;
    }
}