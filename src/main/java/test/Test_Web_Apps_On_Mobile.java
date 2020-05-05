package test;

import DesiredCap.DesiredCap;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.testng.annotations.Test;

public class Test_Web_Apps_On_Mobile extends DesiredCap {
    static IOSDriver<IOSElement> iosDriver;
    static AndroidDriver<AndroidElement> androidDriver;

    @Test
    public void testOnIos(){
        iosDriver=DesiredCap.iosCapabilities();
        firstTest(iosDriver);
    }
    @Test
    public void testOnAndroid(){
        androidDriver=DesiredCap.androidCapabilities();
        firstTest(androidDriver);
    }
    public void firstTest(AppiumDriver driver){
        driver.get("http://www.google.com");
        String title= driver.getTitle();
        System.out.printf(title);
    }
}
