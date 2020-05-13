package test;

import DesiredCap.DesiredCap;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;

public class Test_Performance_Of_Android_App {
    static AndroidDriver<AndroidElement> androidDriver;
    private static int MEMORY_USAGE_WAIT = 30000;
    private static int MEMORY_CAPTURE_WAIT = 10;
    private static String PKG = "io.appium.android.apis";
    private static String PERF_TYPE = "memoryinfo";
    private static String PSS_TYPE = "totalPss";

    @Test
    public void testMemoryUsage() throws Exception {
        androidDriver = DesiredCap.androidCapForNativeApp();
        int totalPss1 = getMemoryInfo(androidDriver).get(PSS_TYPE);
        try {
            Thread.sleep(MEMORY_USAGE_WAIT);
        } catch (InterruptedException ign) {
        }
        int totalPss2 = getMemoryInfo(androidDriver).get(PSS_TYPE);
        // finally, verify that we haven't increased usage more than 5%
        Assert.assertThat((double) totalPss2, Matchers.lessThan(totalPss1 * 1.05));

    }

    private HashMap<String, Integer> getMemoryInfo(AndroidDriver driver) throws Exception {
        List<List<Object>> data = driver.getPerformanceData(PKG, PERF_TYPE, MEMORY_CAPTURE_WAIT);
        HashMap<String, Integer> readableData = new HashMap<>();
        for (int i = 0; i < data.get(0).size(); i++) {
            int val;
            if (data.get(1).get(i) == null) {
                val = 0;
            } else {
                val = Integer.parseInt((String) data.get(1).get(i));
            }
            readableData.put((String) data.get(0).get(i), val);
        }
        return readableData;
    }

}
