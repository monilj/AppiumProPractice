package test;

import DesiredCap.DesiredCap;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.HashMap;

public class SwitchingBetweenTheIosApps {
    static IOSDriver<IOSElement> iosDriver;
    private String PHOTOS_BUNDLE_ID = "com.apple.mobileslideshow";

    private String BUNDLE_ID = "io.cloudgrey.the-app";

    @BeforeTest
    public void setUp() {
        iosDriver = DesiredCap.iosSimulatorCapabilities();
    }

    @Test
    public void switchAppBetweenTestExecution() {
        try {
            HashMap<String, Object> args = new HashMap<>();
//            args.put("bundleId", PHOTOS_BUNDLE_ID);
//            iosDriver.executeScript("mobile: launchApp", args);
            iosDriver.activateApp(PHOTOS_BUNDLE_ID);
            Thread.sleep(3000);


//            args.put("bundleId", BUNDLE_ID);
            iosDriver.activateApp(BUNDLE_ID);
//            iosDriver.executeScript("mobile : activateApp", args);

            Thread.sleep(3000);

//            args.put("bundleId", PHOTOS_BUNDLE_ID);
//            iosDriver.executeScript("mobile: activateApp", args);
            iosDriver.activateApp(PHOTOS_BUNDLE_ID);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @AfterTest
    public void tearDown()
    {
        DesiredCap.stopAppium();
    }
}
