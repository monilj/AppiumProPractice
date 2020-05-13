package test;

import DesiredCap.DesiredCap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Set;

public class Test_Hybrid_Apps_On_Mobile {
    static AndroidDriver<AndroidElement> androidDriver;

    @Test(priority = 1)
    public void testOnAndroid() throws IOException {
        androidDriver = DesiredCap.androidCapForChromeHybridApp();
    }

    @Test(priority = 2)
    public void testAcceptChromeTerms() {
        try {
            androidDriver.findElement(By.id("header_status")).click();
        } catch (NoSuchElementException nse) {
            System.out.println("Element is not present");
        }

    }

    @Test(priority = 3)
    private void switchToWebContext() throws InterruptedException {
         Thread.sleep(15000);
        Set<String> contextNames = androidDriver.getContextHandles();
        System.out.println(contextNames.size());
        for (String contextName : contextNames) {
            System.out.println(contextName);
        }
        androidDriver.context((String) contextNames.toArray()[1]); // set context to WEBVIEW_1
        Thread.sleep(Long.parseLong("1000"));
    }

    @Test(priority = 6)
    public void hitURLInBrowser() throws InterruptedException {
        androidDriver.get("http://wwww.google.com");
        System.out.println("you are in chrome web view");
    }
}
