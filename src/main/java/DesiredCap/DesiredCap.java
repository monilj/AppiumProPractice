package DesiredCap;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.remote.DesiredCapabilities;
import util.StartNStopAppiumProgrammatically;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DesiredCap {
    static String prefix = "appium:";
    static IOSDriver<IOSElement> iosDriver;
    static AndroidDriver<AndroidElement> androidDriver;
    private static AppiumDriverLocalService service;


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
    public static AndroidDriver<AndroidElement> androidCapabilities()  {
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

    public static AndroidDriver<AndroidElement> androidCapForNativeApp() throws IOException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Android Emulator");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("app", "/Users/dev/Documents/monilj/AppiumPro/app/API_Demos.apk");

        try {
            androidDriver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        androidDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        return androidDriver;

    }
    public static AndroidDriver<AndroidElement> androidCapForChromeHybridApp() throws IOException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("chromedriverExecutable","/Users/dev/Documents/monilj/AppiumPro/ChromeDriver81/chromedriver");

        capabilities.setCapability("deviceName", "Android Emulator");
        capabilities.setCapability("automationName", "UiAutomator2");
        capabilities.setCapability("appPackage", "com.android.chrome");
        capabilities.setCapability("appActivity", "org.chromium.chrome.browser.ChromeTabbedActivity");

        try {
            androidDriver = new AndroidDriver<AndroidElement>(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        androidDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        return androidDriver;

    }
    public static IOSDriver<IOSElement> iosSimulatorCapabilities() {
        service= StartNStopAppiumProgrammatically.startAppium();
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("platformVersion", "11.4");
        capabilities.setCapability("deviceName", "iPhone 8 Plus");
        capabilities.setCapability(prefix + MobileCapabilityType.UDID,"13CA0566-1782-4068-B123-E30BEB7C9AA1");
        capabilities.setCapability(prefix + MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
        capabilities.setCapability("connectHardwareKeyboard", false);
        capabilities.setCapability("startIWDP", true);
        capabilities.setCapability("app","/Users/dev/Documents/monilj/AppiumProPractice/app/TheApp-v1.3.0.app.zip");
        //iosDriver = new IOSDriver<IOSElement>(new URL("http://127.0.0.1:8080/wd/hub"),capabilities);
        iosDriver = new IOSDriver<IOSElement>(service.getUrl(),capabilities);
        iosDriver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        return iosDriver;
    }

    public static void stopAppium(){
        boolean serverStatus = StartNStopAppiumProgrammatically.checkIfServerIsRunnning(4723);
        if(serverStatus) {
            service.stop();
        }
    }



    }
