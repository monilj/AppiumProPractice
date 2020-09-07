package util;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;

public class StartNStopAppiumProgrammatically {
    //    private static AppiumDriverLocalService localService;
    private static AppiumDriverLocalService server;

    public static AppiumDriverLocalService startAppium() {
        boolean serverStatus = StartNStopAppiumProgrammatically.checkIfServerIsRunnning(4723);
        if (!serverStatus) {
            AppiumServiceBuilder serviceBuilder = new AppiumServiceBuilder();
            serviceBuilder.usingAnyFreePort();
            serviceBuilder.usingDriverExecutable(new File("/Users/dev/.nvm/versions/node/v10.22.0/bin/node"));
            serviceBuilder.withAppiumJS(new File("/usr/local/bin/appium"));
            HashMap<String, String> environment = new HashMap();
            environment.put("PATH", "/usr/local/bin:" + System.getenv("PATH"));
            serviceBuilder.withEnvironment(environment);
            server = AppiumDriverLocalService.buildService(serviceBuilder);
            server.start();
//            localService= AppiumDriverLocalService.buildDefaultService();
//        localService.start();
        }
        return server;

    }


    public static boolean checkIfServerIsRunnning(int port) {

        boolean isServerRunning = false;
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
            serverSocket.close();
        } catch (IOException e) {
            //If control comes here, then it means that the port is in use
            isServerRunning = true;
        } finally {
            serverSocket = null;
        }
        return isServerRunning;
    }
}
