package test;

import DesiredCap.DesiredCap;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.testng.annotations.Test;

public class SwitchingBetweenTheIosApps {
    static IOSDriver<IOSElement> iosDriver;

    @Test
    public void openSimulator()
    {
        iosDriver = DesiredCap.iosSimulatorCapabilities();

    }

}
