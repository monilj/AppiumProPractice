package DesiredCap;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DesiredCap {
    static String prefix = "appium:";
    static IOSDriver<IOSElement> iosDriver;
    static AndroidDriver<AndroidElement> androidDriver;
    public static IOSDriver<IOSElement> iosCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("platformVersion", "11.4");
        capabilities.setCapability("deviceName", "iPhone 7");
        capabilities.setCapability("browserName", "Safari");
        capabilities.setCapability(prefix + MobileCapabilityType.UDID,"C9100E30-9C94-4345-B9B7-475CDE78A08B");
        capabilities.setCapability(prefix + MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
        capabilities.setCapability("connectHardwareKeyboard", false);
        capabilities.setCapability("startIWDP", true);
        try {
            iosDriver = new IOSDriver<IOSElement>(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        iosDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        return iosDriver;
    }
    public static AndroidDriver<AndroidElement> androidCapabilities() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("chromedriverExecutable","/Users/dev/Documents/monilj/AppiumPro/chromedriver_latest/chromedriver");
        capabilities.setCapability("deviceName", "Android Emulator");
        capabilities.setCapability("browserName", "Chrome");
        capabilities.setCapability("appium:chromeOptions", ImmutableMap.of("w3c", false));

        try {
            androidDriver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        androidDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        return androidDriver;
    }

    }
